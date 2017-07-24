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
public class FilteredPrimaryColumnTest {

	@Test
	public void testBuild() {
		Column column = builder.build();

		Assert.assertEquals("groupId", column.getName());
		Assert.assertEquals(ServiceBuilderType.LONG, column.getType());
		Assert.assertFalse(column.isConvertNull());
		Assert.assertFalse(column.isContainerModel());
		Assert.assertNull(column.getDbName());
		Assert.assertNull(column.getEntity());
		Assert.assertNull(column.getMappingTable());
		Assert.assertFalse(column.hasAccessor());
		Assert.assertFalse(column.hasJsonSerialization());
		Assert.assertTrue(column.isFilterPrimary());
		Assert.assertTrue(column.isLazy());
		Assert.assertFalse(column.isLocalized());
		Assert.assertFalse(column.isParentContainerModel());
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
			.autogeneratePrimaryKeyFromClass(
				ServiceBuilderType.INTEGER, "com.liferay.Foo")
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
		Assert.assertEquals("com.liferay.Foo", column.getIdParam());
		Assert.assertEquals("class", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromIdentity() {
		Column column = builder
			.autogeneratePrimaryKeyFromIdentity(ServiceBuilderType.INTEGER)
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
		Assert.assertNull(column.getIdParam());
		Assert.assertEquals("identity", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromIncrement() {
		Column column = builder
			.autogeneratePrimaryKeyFromIncrement(ServiceBuilderType.INTEGER)
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
		Assert.assertNull(column.getIdParam());
		Assert.assertEquals("increment", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromSequence() {
		Column column = builder
			.autogeneratePrimaryKeyFromSequence(
				ServiceBuilderType.INTEGER, "id_sequence")
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
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
		Column column = builder.build();

		Assert.assertTrue(column.isFilterPrimary());
	}

	@Test
	public void testBuildLocalized() {
		Column column = builder.localized().build();

		Assert.assertTrue(column.isLocalized());
	}

	@Test
	public void testBuildParentContainerModel() {
		Column column = builder.parentContainerModel().build();

		Assert.assertTrue(column.isParentContainerModel());
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

	@Test
	public void testBuildWithoutLazyFetchFromBlob() {
		Column column = builder.withoutLazyFetch().build();

		Assert.assertTrue(column.isLazy());
	}

	@Test
	public void testBuildWithoutLazyFetchFromNonBlobField() {
		ColumnBuilder builder = new ColumnBuilder(
			"blobField", ServiceBuilderType.BLOB);

		Column column = builder.withoutLazyFetch().build();

		Assert.assertFalse(column.isLazy());
	}

	@Test
	public void testEquals() {
		Column column1 = new ColumnBuilder(
			"companyId", ServiceBuilderType.LONG).build();
		Column column2 = new ColumnBuilder(
			"companyId", ServiceBuilderType.LONG).build();

		Assert.assertTrue(column1.equals(column2));
		Assert.assertTrue(column2.equals(column1));
	}

	@Test
	public void testEqualsNotEquals() {
		Column column1 = builder.build();
		Column column2 = new ColumnBuilder(
			"companyId", ServiceBuilderType.LONG).build();

		Assert.assertFalse(column1.equals(column2));
		Assert.assertFalse(column2.equals(column1));
	}

	@Test
	public void testEqualsSameInstance() {
		Column column1 = builder.build();

		Assert.assertTrue(column1.equals(column1));
	}

	private FilterPrimaryColumnBuilder builder = new FilterPrimaryColumnBuilder(
		"groupId", ServiceBuilderType.LONG);

}