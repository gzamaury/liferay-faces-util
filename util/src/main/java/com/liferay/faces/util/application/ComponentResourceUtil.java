/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
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
package com.liferay.faces.util.application;

import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;


/**
 * @author  Neil Griffin
 */
public class ComponentResourceUtil {

	public static String getComponentValue(UIComponent uiComponentResource) {
		String componentResourceValue = null;

		if (uiComponentResource instanceof ValueHolder) {
			ValueHolder valueHolder = (ValueHolder) uiComponentResource;
			Object valueAsObject = valueHolder.getValue();

			if (valueAsObject != null) {
				componentResourceValue = valueAsObject.toString();
			}
		}

		return componentResourceValue;
	}

	public static String getId(String library, String name) {
		String id = null;

		if (library == null) {
			id = name;
		}
		else {

			if (name == null) {
				id = library;
			}
			else {
				id = library + ":" + name;
			}
		}

		return id;
	}
}
