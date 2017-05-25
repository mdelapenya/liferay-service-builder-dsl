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
		Assert.assertFalse(column.isConvertNull());
		Assert.assertFalse(column.isContainerModel());
		Assert.assertNull(column.getDbName());
		Assert.assertNull(column.getEntity());
		Assert.assertNull(column.getMappingTable());
		Assert.assertFalse(column.hasAccessor());
		Assert.assertFalse(column.hasJsonSerialization());
		Assert.assertFalse(column.isFilterPrimary());
		Assert.assertTrue(column.isLazy());
		Assert.assertFalse(column.isLocalized());
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
	public void testBuildContainerModel() {
		Column column = builder.containerModel().build();

		Assert.assertTrue(column.isContainerModel());
	}

	@Test
	public void testBuildConvertNull() {
		Column column = builder.convertsNull().build();

		Assert.assertTrue(column.isConvertNull());
	}

	@Test
	public void testBuildFilterPrimary() {
		Column column = builder.filterPrimary().build();

		Assert.assertTrue(column.isFilterPrimary());
	}

	@Test
	public void testBuildLocalized() {
		Column column = builder.localized().build();

		Assert.assertTrue(column.isLocalized());
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
	public void testBuildWithJsonSerialization() {
		Column column = builder.withJsonSerialization().build();

		Assert.assertTrue(column.hasJsonSerialization());
	}

	@Test
	public void testBuildWithoutLazyFetchFromBlob() {
		Column column = builder.withoutLazyFetch().build();

		Assert.assertTrue(column.isLazy());
	}

	@Test
	public void testBuildWithoutLazyFetchFromNonBlobField() {
		Column.Builder builder = new Column.Builder("blobField", "Blob");

		Column column = builder.withoutLazyFetch().build();

		Assert.assertFalse(column.isLazy());
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