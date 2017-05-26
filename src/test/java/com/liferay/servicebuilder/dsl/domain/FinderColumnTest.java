package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class FinderColumnTest {

	@Test
	public void testBuild() {
		FinderColumn finderColumn = builder.build();

		Assert.assertEquals("companyId", finderColumn.getName());
		Assert.assertNull(finderColumn.getArrayableOperator());
		Assert.assertNull(finderColumn.getComparator());
		Assert.assertTrue(finderColumn.isCaseSensitive());
	}

	@Test
	public void testBuildCaseInsensitive() {
		FinderColumn finderColumn = builder.caseInsensitive().build();

		Assert.assertFalse(finderColumn.isCaseSensitive());
	}

	@Test
	public void testBuildWithArrayableOperator() {
		FinderColumn finderColumn = builder
			.withArrayableOperator(ArrayableOperator.AND)
			.build();

		Assert.assertEquals(
			ArrayableOperator.AND, finderColumn.getArrayableOperator());

		finderColumn = builder
			.withArrayableOperator(ArrayableOperator.OR)
			.build();

		Assert.assertEquals(
			ArrayableOperator.OR, finderColumn.getArrayableOperator());
	}

	@Test
	public void testBuildWithComparator() {
		FinderColumn finderColumn = builder
			.withComparator(FinderComparator.EQUALS)
			.build();

		Assert.assertEquals(FinderComparator.EQUALS, finderColumn.getComparator());
	}

	private FinderColumn.Builder builder = new FinderColumn.Builder(
		"companyId");

}