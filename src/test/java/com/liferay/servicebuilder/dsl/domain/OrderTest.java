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

	private Order.Builder builder = new Order.Builder();

}