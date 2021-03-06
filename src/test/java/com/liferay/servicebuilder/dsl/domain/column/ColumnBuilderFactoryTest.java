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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ColumnBuilderFactoryTest {

	@Test
	public void testGetColumnBuilder() {
		ColumnBuilder columnBuilder = ColumnBuilderFactory.getColumnBuilder(
			"name", ServiceBuilderType.LONG);

		Assert.assertTrue(
			columnBuilder instanceof NonFilterPrimaryColumnBuilder);
	}

	@Test
	public void testGetFilterPrimaryColumnBuilder() {
		ColumnBuilder columnBuilder =
			ColumnBuilderFactory.getFilterPrimaryColumnBuilder(
				"name", ServiceBuilderType.LONG);

		Assert.assertTrue(columnBuilder instanceof FilterPrimaryColumnBuilder);
	}

}