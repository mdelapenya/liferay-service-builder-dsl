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

	@Test
	public void testEquals() {
		OrderColumn orderColumn1 = new OrderColumn.Builder("o1").build();
		OrderColumn orderColumn2 = new OrderColumn.Builder("o1").build();

		Assert.assertTrue(orderColumn1.equals(orderColumn2));
		Assert.assertTrue(orderColumn2.equals(orderColumn1));
	}

	@Test
	public void testEqualsNotEquals() {
		OrderColumn orderColumn1 = new OrderColumn.Builder("o1").build();
		OrderColumn orderColumn2 = new OrderColumn.Builder("o2").build();

		Assert.assertFalse(orderColumn1.equals(orderColumn2));
		Assert.assertFalse(orderColumn2.equals(orderColumn1));
	}

	@Test
	public void testEqualsSameInstance() {
		OrderColumn orderColumn = new OrderColumn.Builder("o1").build();

		Assert.assertTrue(orderColumn.equals(orderColumn));
	}

	private OrderColumn.Builder builder = new OrderColumn.Builder("name");

}