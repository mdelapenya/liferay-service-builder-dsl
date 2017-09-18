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
public enum FinderComparator {

	DISTINCT("!="), EQUALS("="), GREATER(">"), GREATER_EQUALS(">="), LESS("<"),
	LESS_EQUALS("<="), LIKE("LIKE");

	FinderComparator(String comparator) {
		_comparator = comparator;
	}

	public String getComparator() {
		return _comparator;
	}

	private String _comparator;

}