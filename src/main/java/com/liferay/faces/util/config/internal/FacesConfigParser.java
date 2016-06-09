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
package com.liferay.faces.util.config.internal;

import java.io.IOException;
import java.io.InputStream;

import com.liferay.faces.util.config.FacesConfig;


/**
 * @author  Neil Griffin
 */
public interface FacesConfigParser {

	/**
	 * Parses the specified InputStream and returns a new FacesConfig that contains parsed data that has been appended
	 * to the specified FacesConfig. Closing the specified InputStream is the responsibility of the caller.
	 */
	public FacesConfig parse(InputStream inputStream, FacesConfig facesConfig) throws IOException;
}