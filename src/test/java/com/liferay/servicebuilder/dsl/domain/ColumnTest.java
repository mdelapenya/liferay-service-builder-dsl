package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ColumnTest {

	@Test
	public void testBuild() {
		Column column = builder.build();

		Assert.assertEquals("groupId", column.getName());
		Assert.assertEquals("long", column.getType());
		Assert.assertFalse(column.isAccesor());
		Assert.assertFalse(column.isFilterPrimary());
		Assert.assertFalse(column.isPrimary());
	}

	@Test
	public void testBuildFilterPrimary() {
		Column column = builder.filterPrimary().build();

		Assert.assertTrue(column.isFilterPrimary());
	}

	@Test
	public void testBuildPrimary() {
		Column column = builder.primary().build();

		Assert.assertTrue(column.isPrimary());
	}

	@Test
	public void testBuildWithAccessor() {
		Column column = builder.withAccesor().build();

		Assert.assertTrue(column.isAccesor());
	}

	private Column.Builder builder = new Column.Builder("groupId", "long");

}