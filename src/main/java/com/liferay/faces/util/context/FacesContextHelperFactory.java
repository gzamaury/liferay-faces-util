/**
 * Copyright (c) 2000-2017 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.util.context;

import com.liferay.faces.util.factory.FactoryExtensionFinder;
import com.liferay.faces.util.helper.Wrapper;


/**
 * @author  Neil Griffin
 */
public abstract class FacesContextHelperFactory implements Wrapper<FacesContextHelperFactory> {

	/**
	 * Returns a stateless, thread-safe singleton instance of {@link FacesContextHelper} from the {@link
	 * FacesContextHelperFactory} found by the {@link FactoryExtensionFinder}.
	 */
	public static FacesContextHelper getFacesContextHelperInstance() {

		FacesContextHelperFactory facesContextHelperFactory = (FacesContextHelperFactory) FactoryExtensionFinder
			.getFactory(FacesContextHelperFactory.class);

		return facesContextHelperFactory.getFacesContextHelper();
	}

	/**
	 * Returns a stateless, thread-safe singleton instance of {@link FacesContextHelper}.
	 */
	public abstract FacesContextHelper getFacesContextHelper();

	/**
	 * Returns the wrapped factory instance if this factory decorates another. Otherwise, this method returns null.
	 */
	@Override
	public abstract FacesContextHelperFactory getWrapped();
}
