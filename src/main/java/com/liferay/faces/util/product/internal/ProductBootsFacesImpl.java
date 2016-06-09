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

/**
 * @author  Kyle Stiemann
 */
public class ProductBootsFacesImpl extends ProductBaseImpl {

	public ProductBootsFacesImpl() {

		try {
			this.title = "BootsFaces";

			Class<?> clazz = Class.forName("net.bootsfaces.C");
			init(clazz, "BootsFaces", "META-INF/maven/net.bootsfaces/bootsfaces/pom.properties");
		}
		catch (Exception e) {
			// Ignore -- BootsFaces is likely not present.
		}
	}
}