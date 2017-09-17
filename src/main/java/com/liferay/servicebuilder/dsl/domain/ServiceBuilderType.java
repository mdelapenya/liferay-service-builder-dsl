/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public enum ServiceBuilderType {

	BLOB("Blob"), BOOLEAN("Boolean"), COLLECTION("Collection"), DATE("Date"),
	DOUBLE("double"), INTEGER("int"), LONG("long"), STRING("String");

	ServiceBuilderType(String type) {
		_type = type;
	}

	public String getType() {
		return _type;
	}

	@Override
	public String toString() {
		return _type;
	}

	private String _type;

}