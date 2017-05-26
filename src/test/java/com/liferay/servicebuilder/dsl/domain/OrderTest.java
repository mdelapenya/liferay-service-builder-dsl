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
	public void testBuildWithOrderColumns() {
		OrderColumn orderColumn1 = new OrderColumn.Builder("name1").build();
		OrderColumn orderColumn2 = new OrderColumn.Builder("name2").build();

		Order order = builder
			.withOrderColumn(orderColumn1)
			.withOrderColumn(orderColumn2)
			.build();

		Assert.assertEquals(2, order.getOrderColumns().size());
	}

	private Order.Builder builder = new Order.Builder();

}