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
package com.liferay.faces.util.product.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Kyle Stiemann
 */
public class PomProperties {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(PomProperties.class);

	// Private Data Members
	private String version;

	public PomProperties(Class<?> clazz, String pomPropertiesFile) {

		InputStream inputStream = null;

		try {

			ClassLoader classLoader = clazz.getClassLoader();
			inputStream = classLoader.getResourceAsStream(pomPropertiesFile);

			if (inputStream != null) {

				Properties pomProperties = new Properties();
				pomProperties.load(inputStream);
				version = pomProperties.getProperty("version");
			}
		}
		catch (IOException e) {
			logger.error(e);
		}
		finally {

			if (inputStream != null) {

				try {
					inputStream.close();
				}
				catch (IOException e) {
					// do nothing.
				}
			}
		}
	}

	public String getVersion() {
		return version;
	}
}