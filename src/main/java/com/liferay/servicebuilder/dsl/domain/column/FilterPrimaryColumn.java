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

package com.liferay.servicebuilder.dsl.domain.column;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The column element represents a column in the database.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "column")
@JsonPropertyOrder(alphabetic = true)
public class FilterPrimaryColumn extends NonFilterPrimaryColumn {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FilterPrimaryColumn)) {
			return false;
		}

		FilterPrimaryColumn that = (FilterPrimaryColumn)obj;

		return name.equals(that.name);
	}

	@JacksonXmlProperty(isAttribute = true, localName = "filter-primary")
	public boolean isFilterPrimary() {
		return _filterPrimary;
	}

	public void setFilterPrimary(boolean filterPrimary) {
		_filterPrimary = filterPrimary;
	}

	protected FilterPrimaryColumn() {
		super();
	}

	/**
	 * The filter-primary value specifies the column to use as the primary key
	 * column when using filter finders. Only one column should ever have this
	 * value set to true. If no column has this set to true, then the default
	 * primary column be used.
	 */
	private boolean _filterPrimary;

}