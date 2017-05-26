package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class OrderColumnTest {

	@Test
	public void testBuild() {
		OrderColumn orderColumn = builder.build();

		Assert.assertEquals("name", orderColumn.getName());
		Assert.assertEquals(OrderBy.ASC, orderColumn.getOrderBy());
		Assert.assertTrue(orderColumn.isCaseSensitive());
	}

	@Test
	public void testBuildCaseInsensitive() {
		OrderColumn orderColumn = builder.caseInsensitive().build();

		Assert.assertFalse(orderColumn.isCaseSensitive());
	}

	@Test
	public void testBuildDescending() {
		OrderColumn orderColumn = builder.descending().build();

		Assert.assertEquals(OrderBy.DESC, orderColumn.getOrderBy());
	}

	private OrderColumn.Builder builder = new OrderColumn.Builder("name");

}