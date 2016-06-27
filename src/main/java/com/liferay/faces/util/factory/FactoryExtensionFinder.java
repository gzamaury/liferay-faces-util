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
package com.liferay.faces.util.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.faces.FacesException;

import com.liferay.faces.util.config.ConfiguredElement;


/**
 * @author  Neil Griffin
 */
public abstract class FactoryExtensionFinder {

	// Private Static Data Members
	private static FactoryExtensionFinder instance;

	public static Object getFactory(Class<?> clazz) {
		return getInstance().getFactoryInstance(clazz);
	}

	public static FactoryExtensionFinder getInstance() throws FacesException {

		if (instance == null) {

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			try {
				String facesFactoryFinderService =
					"META-INF/services/com.liferay.faces.util.factory.FactoryExtensionFinder";
				String facesFactoryFinderClassName = getClassPathResourceAsString(facesFactoryFinderService);

				if (facesFactoryFinderClassName != null) {
					Class<?> facesFactoryFinderClass = classLoader.loadClass(facesFactoryFinderClassName);
					instance = (FactoryExtensionFinder) facesFactoryFinderClass.newInstance();
				}
				else {
					throw new FacesException("Unable to load resource=[" + facesFactoryFinderService + "]");
				}
			}
			catch (Exception e) {
				throw new FacesException(e);
			}
		}

		return instance;
	}

	private static String getClassPathResourceAsString(String resourcePath) {
		String classPathResourceAsString = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		if (classLoader != null) {
			InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				try {
					classPathResourceAsString = bufferedReader.readLine();
				}
				catch (IOException e) {

					// Since the API can't use a logging system like SLF4J the best we can do is print to stderr.
					System.err.println("Unable to read contents of resourcePath=[" + resourcePath + "]");
				}
				finally {

					try {
						bufferedReader.close();
						inputStreamReader.close();
						inputStream.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return classPathResourceAsString;
	}

	public abstract Object getFactoryInstance(Class<?> clazz);

	public abstract void registerFactory(ConfiguredElement configuredFactoryExtension);
}
