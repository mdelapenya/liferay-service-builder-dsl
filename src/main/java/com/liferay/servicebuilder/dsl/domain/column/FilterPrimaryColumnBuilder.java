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

import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;

/**
 * @author Manuel de la Peña
 */
public class FilterPrimaryColumnBuilder extends BaseColumnBuilder {

	/**
	 * @param name Specifies the getter and setter name in the entity.
	 * @param type Specifies whether the column is a String, Boolean, or
	 *             int, etc.
	 *             For example:
	 *             <column name="companyId" db-name="companyId"
	 *                     type="String" />
	 *             The above column specifies that there will be a getter
	 *             called pojo.getCompanyId() that will return a String.
	 */
	public FilterPrimaryColumnBuilder(String name, ServiceBuilderType type) {
		super(name, type);

		((FilterPrimaryColumn)column).setFilterPrimary(true);
	}

	/**
	 * Builds the FilterPrimaryColumn object from the previous composition
	 * operations.
	 *
	 * @return the FilterPrimaryColumn model
	 */
	@Override
	public FilterPrimaryColumn build() {
		FilterPrimaryColumn column = (FilterPrimaryColumn)this.column;

		this.column = new FilterPrimaryColumn();

		return column;
	}

	protected Column newColumn() {
		return new FilterPrimaryColumn();
	}

}