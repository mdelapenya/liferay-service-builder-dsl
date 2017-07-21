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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class FinderTest {

	@Test
	public void testBuild() {
		Finder finder = builder.build();

		Assert.assertEquals("G_C_DDMSK", finder.getName());
		Assert.assertEquals("JournalArticle", finder.getReturnType());
		Assert.assertTrue(finder.hasSQLIndex());
		Assert.assertFalse(finder.isUnique());

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertFalse(finderColumns.isEmpty());

		FinderColumn finderColumn = finderColumns.get(0);

		Assert.assertEquals("groupId", finderColumn.getName());
	}

	@Test
	public void testBuildCollection() {
		FinderColumn finderColumn = new FinderColumn.Builder(
			"groupId").build();

		Finder.Builder collectionBuilder = new Finder.Builder(
			"ResourcePrimKey", "Collection", finderColumn);

		Finder finder = collectionBuilder.build();

		Assert.assertEquals("ResourcePrimKey", finder.getName());
		Assert.assertEquals("Collection", finder.getReturnType());
		Assert.assertTrue(finder.hasSQLIndex());
		Assert.assertFalse(finder.isUnique());

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertFalse(finderColumns.isEmpty());

		FinderColumn finderColumn1 = finderColumns.get(0);

		Assert.assertEquals("groupId", finderColumn1.getName());
	}

	@Test
	public void testBuildUnique() {
		Finder finder = builder.unique().build();

		Assert.assertTrue(finder.isUnique());
	}

	@Test
	public void testBuildWithFinderColumn() {
		FinderColumn finderColumn = new FinderColumn.Builder("groupId").build();

		Finder finder = builder.withFinderColumns(finderColumn).build();

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertEquals(finderColumns.toString(), 1, finderColumns.size());
	}

	@Test
	public void testBuildWithFinderColumns() {
		FinderColumn finderColumn1 = new FinderColumn.Builder(
			"groupId").build();
		FinderColumn finderColumn2 = new FinderColumn.Builder(
			"companyId").build();

		Finder finder = builder
			.withFinderColumns(finderColumn1, finderColumn2)
			.build();

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertEquals(finderColumns.toString(), 2, finderColumns.size());
	}

	@Test
	public void testBuildWithoutSQLIndex() {
		Finder finder = builder.withoutSQLIndex().build();

		Assert.assertFalse(finder.hasSQLIndex());
	}

	@Test
	public void testEquals() {
		FinderColumn finderColumn = new FinderColumn.Builder(
			"groupId").build();

		Finder finder1 = new Finder.Builder("f1", "long", finderColumn).build();
		Finder finder2 = new Finder.Builder("f1", "long", finderColumn).build();

		Assert.assertTrue(finder1.equals(finder2));
		Assert.assertTrue(finder2.equals(finder1));
	}

	@Test
	public void testEqualsNotEquals() {
		FinderColumn finderColumn = new FinderColumn.Builder(
			"groupId").build();

		Finder finder1 = new Finder.Builder("f1", "long", finderColumn).build();
		Finder finder2 = new Finder.Builder("f2", "long", finderColumn).build();

		Assert.assertFalse(finder1.equals(finder2));
		Assert.assertFalse(finder2.equals(finder1));
	}

	@Test
	public void testEqualsSameInstance() {
		FinderColumn finderColumn = new FinderColumn.Builder(
			"groupId").build();

		Finder finder = new Finder.Builder("f1", "long", finderColumn).build();

		Assert.assertTrue(finder.equals(finder));
	}

	private Finder.Builder builder = new Finder.Builder(
		"G_C_DDMSK", "JournalArticle",
		new FinderColumn.Builder("groupId").build());

}