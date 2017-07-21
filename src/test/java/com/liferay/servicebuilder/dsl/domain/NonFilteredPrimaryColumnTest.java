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
public class NonFilteredPrimaryColumnTest {

	@Test
	public void testBuild() {
		NonFilteredPrimaryColumn column = builder.build();

		Assert.assertEquals("groupId", column.getName());
		Assert.assertEquals(ServiceBuilderType.LONG, column.getType());
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
		Assert.assertFalse(column.isParentContainerModel());
		Assert.assertFalse(column.isPrimary());
	}

	@Test
	public void testBuildAsPrimaryKey() {
		NonFilteredPrimaryColumn column = builder.asPrimaryKey().build();

		Assert.assertTrue(column.isPrimary());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromClass() {
		NonFilteredPrimaryColumn column = builder
			.autogeneratePrimaryKeyFromClass(
				ServiceBuilderType.INTEGER, "com.liferay.Foo")
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
		Assert.assertEquals("com.liferay.Foo", column.getIdParam());
		Assert.assertEquals("class", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromIdentity() {
		NonFilteredPrimaryColumn column = builder
			.autogeneratePrimaryKeyFromIdentity(ServiceBuilderType.INTEGER)
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
		Assert.assertNull(column.getIdParam());
		Assert.assertEquals("identity", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromIncrement() {
		NonFilteredPrimaryColumn column = builder
			.autogeneratePrimaryKeyFromIncrement(ServiceBuilderType.INTEGER)
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
		Assert.assertNull(column.getIdParam());
		Assert.assertEquals("increment", column.getIdType());
	}

	@Test
	public void testBuildAutogeneratePrimaryKeyFromSequence() {
		NonFilteredPrimaryColumn column = builder
			.autogeneratePrimaryKeyFromSequence(
				ServiceBuilderType.INTEGER, "id_sequence")
			.build();

		Assert.assertEquals(ServiceBuilderType.INTEGER, column.getType());
		Assert.assertEquals("id_sequence", column.getIdParam());
		Assert.assertEquals("sequence", column.getIdType());
	}

	@Test
	public void testBuildContainerModel() {
		NonFilteredPrimaryColumn column = builder.containerModel().build();

		Assert.assertTrue(column.isContainerModel());
	}

	@Test
	public void testBuildConvertNull() {
		NonFilteredPrimaryColumn column = builder.convertsNull().build();

		Assert.assertTrue(column.isConvertNull());
	}

	@Test
	public void testBuildFilterPrimary() {
		NonFilteredPrimaryColumn column = builder.filterPrimary().build();

		Assert.assertTrue(column.isFilterPrimary());
	}

	@Test
	public void testBuildLocalized() {
		NonFilteredPrimaryColumn column = builder.localized().build();

		Assert.assertTrue(column.isLocalized());
	}

	@Test
	public void testBuildParentContainerModel() {
		NonFilteredPrimaryColumn column = builder.parentContainerModel().build();

		Assert.assertTrue(column.isParentContainerModel());
	}

	@Test
	public void testBuildWithAccessor() {
		NonFilteredPrimaryColumn column = builder.withAccessor().build();

		Assert.assertTrue(column.hasAccessor());
	}

	@Test
	public void testBuildWithDbName() {
		NonFilteredPrimaryColumn column = builder.withDbName("dbName").build();

		Assert.assertEquals("dbName", column.getDbName());
	}

	@Test
	public void testBuildWithJsonSerialization() {
		NonFilteredPrimaryColumn column = builder.withJsonSerialization().build();

		Assert.assertTrue(column.hasJsonSerialization());
	}

	@Test
	public void testBuildWithManyToManyRelationship() {
		NonFilteredPrimaryColumn column = builder
			.withManyToManyRelationship("Role", "Groups_Roles")
			.build();

		Assert.assertEquals("Role", column.getEntity());
		Assert.assertEquals("Groups_Roles", column.getMappingTable());
	}

	@Test
	public void testBuildWithManyToManyRelationshipOtherServiceXML() {
		NonFilteredPrimaryColumn column = builder
			.withManyToManyRelationship(
				"com.liferay.portal.Organization", "Foo_Organizations")
			.build();

		Assert.assertEquals(
			"com.liferay.portal.Organization", column.getEntity());
		Assert.assertEquals("Foo_Organizations", column.getMappingTable());
	}

	@Test
	public void testBuildWithoutLazyFetchFromBlob() {
		NonFilteredPrimaryColumn column = builder.withoutLazyFetch().build();

		Assert.assertTrue(column.isLazy());
	}

	@Test
	public void testBuildWithoutLazyFetchFromNonBlobField() {
		NonFilteredPrimaryColumn.Builder builder = new NonFilteredPrimaryColumn.Builder(
			"blobField", ServiceBuilderType.BLOB);

		NonFilteredPrimaryColumn column = builder.withoutLazyFetch().build();

		Assert.assertFalse(column.isLazy());
	}

	@Test
	public void testEquals() {
		NonFilteredPrimaryColumn column1 = new NonFilteredPrimaryColumn.Builder(
			"companyId", ServiceBuilderType.LONG).build();
		NonFilteredPrimaryColumn column2 = new NonFilteredPrimaryColumn.Builder(
			"companyId", ServiceBuilderType.LONG).build();

		Assert.assertTrue(column1.equals(column2));
		Assert.assertTrue(column2.equals(column1));
	}

	@Test
	public void testEqualsNotEquals() {
		NonFilteredPrimaryColumn column1 = builder.build();
		NonFilteredPrimaryColumn column2 = new NonFilteredPrimaryColumn.Builder(
			"companyId", ServiceBuilderType.LONG).build();

		Assert.assertFalse(column1.equals(column2));
		Assert.assertFalse(column2.equals(column1));
	}

	@Test
	public void testEqualsSameInstance() {
		NonFilteredPrimaryColumn column1 = builder.build();

		Assert.assertTrue(column1.equals(column1));
	}

	private NonFilteredPrimaryColumn.Builder builder = new NonFilteredPrimaryColumn.Builder(
		"groupId", ServiceBuilderType.LONG);

}