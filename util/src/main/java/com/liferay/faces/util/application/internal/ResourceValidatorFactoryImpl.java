/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.util.application.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.liferay.faces.util.application.ResourceValidator;
import com.liferay.faces.util.application.ResourceValidatorFactory;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Neil Griffin
 */
public class ResourceValidatorFactoryImpl extends ResourceValidatorFactory {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(ResourceValidatorFactoryImpl.class);

	// Private Data Members
	private List<Pattern> excludeResourcePatterns;
	private List<Pattern> excludeLibraryPatterns;

	public ResourceValidatorFactoryImpl() {

		String excludeResourceExtensions = null;
		FacesContext startupFacesContext = FacesContext.getCurrentInstance();

		if (startupFacesContext != null) {

			ExternalContext externalContext = startupFacesContext.getExternalContext();

			String resourceExcludes = externalContext.getInitParameter("javax.faces.RESOURCE_EXCLUDES");

			if ((resourceExcludes == null) || (resourceExcludes.trim().length() == 0)) {
				excludeResourceExtensions = ".class .jsp .jspx .properties .xhtml .groovy";
			}
		}

		if (excludeResourceExtensions != null) {

			String[] extensions = excludeResourceExtensions.split(" ");

			this.excludeResourcePatterns = new ArrayList<Pattern>(extensions.length + 1);

			for (String extension : extensions) {
				Pattern pattern = Pattern.compile(".*\\" + extension + ".*");
				excludeResourcePatterns.add(pattern);
				logger.debug("Excluding resource pattern=[{0}]", pattern);
			}

			// Prevent for a leading dot character for resource names and library names
			Pattern pattern = Pattern.compile("^\\..*");
			this.excludeResourcePatterns.add(pattern);
			logger.debug("Excluding resource pattern=[{0}]", pattern);
			this.excludeLibraryPatterns = new ArrayList<Pattern>(1);
			this.excludeLibraryPatterns.add(pattern);
			logger.debug("Excluding library pattern=[{0}]", pattern);
		}
	}

	@Override
	public ResourceValidator getResourceValidator() {
		return new ResourceValidatorImpl(excludeResourcePatterns, excludeLibraryPatterns);
	}

	@Override
	public ResourceValidatorFactory getWrapped() {

		// Since this is the default factory instance, it will never wrap another factory.
		return null;
	}
}
