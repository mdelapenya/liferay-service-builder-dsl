package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

		FinderColumn finderColumn1 = finderColumns.get(0);

		Assert.assertEquals("groupId", finderColumn1.getName());
	}

	@Test
	public void testBuildWithFinderColumns() {
		FinderColumn finderColumn1 =
			new FinderColumn.Builder("groupId").build();
		FinderColumn finderColumn2 =
			new FinderColumn.Builder("companyId").build();

		Finder finder = builder
			.withFinderColumn(finderColumn1)
			.withFinderColumn(finderColumn2)
			.build();

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertEquals(2, finderColumns.size());

		FinderColumn finderColumn11 = finderColumns.get(0);

		Assert.assertEquals("groupId", finderColumn11.getName());

		FinderColumn finderColumn21 = finderColumns.get(1);

		Assert.assertEquals("companyId", finderColumn21.getName());
	}

	@Test
	public void testBuildWithoutSQLIndex() {
		Finder finder = builder.withoutSQLIndex().build();

		Assert.assertFalse(finder.hasSQLIndex());
	}

	private Finder.Builder builder = new Finder.Builder(
		"G_C_DDMSK", "JournalArticle");
	private Finder.Builder collectionBuilder = new Finder.Builder(
		"ResourcePrimKey", "Collection");

}