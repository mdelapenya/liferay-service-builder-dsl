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
	public void testBuildAutogeneratePrimaryKeyFromClass() {
		Column column = builder
			.autogeneratePrimaryKeyFromClass("Integer", "com.liferay.Foo")
			.build();

		Assert.assertEquals("Integer", column.getType());
		Assert.assertEquals("com.liferay.Foo", column.getIdParam());
		Assert.assertEquals("class", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromIdentity() {
		Column column = builder
			.autogeneratePrimaryKeyFromIdentity("Integer")
			.build();

		Assert.assertEquals("Integer", column.getType());
		Assert.assertNull(column.getIdParam());
		Assert.assertEquals("identity", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromIncrement() {
		Column column = builder
			.autogeneratePrimaryKeyFromIncrement("Integer")
			.build();

		Assert.assertEquals("Integer", column.getType());
		Assert.assertNull(column.getIdParam());
		Assert.assertEquals("increment", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromSequence() {
		Column column = builder
			.autogeneratePrimaryKeyFromSequence("Integer", "id_sequence")
			.build();

		Assert.assertEquals("Integer", column.getType());
		Assert.assertEquals("id_sequence", column.getIdParam());
		Assert.assertEquals("sequence", column.getIdType());
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