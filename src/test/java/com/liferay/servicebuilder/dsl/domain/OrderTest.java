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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class OrderTest {

	@Test
	public void testBuild() {
		Order order = builder.build();

		Assert.assertNull(order.getBy());
		Assert.assertTrue(order.getOrderColumns().isEmpty());
	}

	@Test
	public void testBuildByAsc() {
		Order order = builder.by(OrderBy.ASC).build();

		Assert.assertEquals(OrderBy.ASC, order.getBy());
	}

	@Test
	public void testBuildByDesc() {
		Order order = builder.by(OrderBy.DESC).build();

		Assert.assertEquals(OrderBy.DESC, order.getBy());
	}

	@Test
	public void testBuildWithOrderColumn() {
		OrderColumn orderColumn = new OrderColumn.Builder("name").build();

		Order order = builder.withOrderColumn(orderColumn).build();

		Assert.assertEquals(1, order.getOrderColumns().size());
	}

	@Test
	public void testBuildWithOrderColumnsDuplicatedDoesNotAddIt() {
		OrderColumn orderColumn = new OrderColumn.Builder("name").build();

		Order order = builder
			.withOrderColumns(orderColumn, orderColumn)
			.build();

		Assert.assertEquals(1, order.getOrderColumns().size());
	}

	@Test
	public void testBuildWithOrderColumns() {
		OrderColumn orderColumn1 = new OrderColumn.Builder("name1").build();
		OrderColumn orderColumn2 = new OrderColumn.Builder("name2").build();

		Order order = builder
			.withOrderColumns(orderColumn1, orderColumn2)
			.build();

		Assert.assertEquals(2, order.getOrderColumns().size());
	}

	private Order.Builder builder = new Order.Builder();

}