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

		Finder finder = builder.withFinderColumn(finderColumn).build();

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
			.withFinderColumn(finderColumn1)
			.withFinderColumn(finderColumn2)
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