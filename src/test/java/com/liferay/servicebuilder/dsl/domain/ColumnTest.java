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
		Assert.assertNull(column.getDbName());
		Assert.assertNull(column.getEntity());
		Assert.assertNull(column.getMappingTable());
		Assert.assertFalse(column.hasAccessor());
		Assert.assertFalse(column.isFilterPrimary());
		Assert.assertFalse(column.isPrimary());
	}

	@Test
	public void testBuildAsPrimaryKey() {
		Column column = builder.asPrimaryKey().build();

		Assert.assertTrue(column.isPrimary());
	}

	@Test
	public void testBuildFilterPrimary() {
		Column column = builder.filterPrimary().build();

		Assert.assertTrue(column.isFilterPrimary());
	}

	@Test
	public void testBuildWithAccessor() {
		Column column = builder.withAccessor().build();

		Assert.assertTrue(column.hasAccessor());
	}

	@Test
	public void testBuildWithDbName() {
		Column column = builder.withDbName("dbName").build();

		Assert.assertEquals("dbName", column.getDbName());
	}

	@Test
	public void testBuildWithManyToManyRelationship() {
		Column column = builder
			.withManyToManyRelationship("Role", "Groups_Roles")
			.build();

		Assert.assertEquals("Role", column.getEntity());
		Assert.assertEquals("Groups_Roles", column.getMappingTable());
	}

	@Test
	public void testBuildWithManyToManyRelationshipOtherServiceXML() {
		Column column = builder
			.withManyToManyRelationship(
				"com.liferay.portal.Organization", "Foo_Organizations")
			.build();

		Assert.assertEquals(
			"com.liferay.portal.Organization", column.getEntity());
		Assert.assertEquals("Foo_Organizations", column.getMappingTable());
	}

	private Column.Builder builder = new Column.Builder("groupId", "long");

}