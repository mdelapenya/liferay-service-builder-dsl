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
		Assert.assertTrue(finder.getFinderColumns().isEmpty());
	}

	@Test
	public void testBuildCollection() {
		Finder.Builder collectionBuilder = new Finder.Builder(
			"ResourcePrimKey", "Collection");

		Finder finder = collectionBuilder.build();

		Assert.assertEquals("ResourcePrimKey", finder.getName());
		Assert.assertEquals("Collection", finder.getReturnType());
		Assert.assertTrue(finder.hasSQLIndex());
		Assert.assertFalse(finder.isUnique());
		Assert.assertTrue(finder.getFinderColumns().isEmpty());
	}

	@Test
	public void testBuildUnique() {
		Finder finder = builder.unique().build();

		Assert.assertTrue(finder.isUnique());
	}

	@Test
	public void testBuildWithFinderColumn() {
		FinderColumn finderColumn = new FinderColumn.Builder("groupId").build();

		Finder finder = builder.withFinderColumn(finderColumn).build();

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertEquals(1, finderColumns.size());
	}

	@Test
	public void testBuildWithFinderColumns() {
		FinderColumn finderColumn1 = new FinderColumn.Builder(
			"groupId").build();
		FinderColumn finderColumn2 = new FinderColumn.Builder(
			"companyId").build();

		Finder finder = builder
			.withFinderColumn(finderColumn1)
			.withFinderColumn(finderColumn2)
			.build();

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertEquals(2, finderColumns.size());
	}

	@Test
	public void testBuildWithoutSQLIndex() {
		Finder finder = builder.withoutSQLIndex().build();

		Assert.assertFalse(finder.hasSQLIndex());
	}

	@Test
	public void testEquals() {
		Finder finder1 = new Finder.Builder("f1", "long").build();
		Finder finder2 = new Finder.Builder("f1", "long").build();

		Assert.assertTrue(finder1.equals(finder2));
		Assert.assertTrue(finder2.equals(finder1));
	}

	@Test
	public void testEqualsNotEquals() {
		Finder finder1 = new Finder.Builder("f1", "long").build();
		Finder finder2 = new Finder.Builder("f2", "long").build();

		Assert.assertFalse(finder1.equals(finder2));
		Assert.assertFalse(finder2.equals(finder1));
	}

	@Test
	public void testEqualsSameInstance() {
		Finder finder = new Finder.Builder("f1", "long").build();

		Assert.assertTrue(finder.equals(finder));
	}

	private Finder.Builder builder = new Finder.Builder(
		"G_C_DDMSK", "JournalArticle");

}