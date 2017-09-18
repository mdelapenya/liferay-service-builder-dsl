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
		FinderColumn finderColumn = builder.and().build();

		Assert.assertEquals(
			ArrayableOperator.AND, finderColumn.getArrayableOperator());

		finderColumn = builder.or().build();

		Assert.assertEquals(
			ArrayableOperator.OR, finderColumn.getArrayableOperator());
	}

	@Test
	public void testBuildWithComparator() {
		FinderColumn finderColumn = builder.equalsTo().build();

		Assert.assertEquals(
			FinderComparator.EQUALS, finderColumn.getComparator());
	}

	@Test
	public void testEquals() {
		FinderColumn finderColumn1 = new FinderColumn.Builder("f1").build();
		FinderColumn finderColumn2 = new FinderColumn.Builder("f1").build();

		Assert.assertTrue(finderColumn1.equals(finderColumn2));
		Assert.assertTrue(finderColumn2.equals(finderColumn1));
	}

	@Test
	public void testEqualsNotEquals() {
		FinderColumn finderColumn1 = new FinderColumn.Builder("f1").build();
		FinderColumn finderColumn2 = new FinderColumn.Builder("f2").build();

		Assert.assertFalse(finderColumn1.equals(finderColumn2));
		Assert.assertFalse(finderColumn2.equals(finderColumn1));
	}

	@Test
	public void testEqualsSameInstance() {
		FinderColumn finderColumn = new FinderColumn.Builder("f1").build();

		Assert.assertTrue(finderColumn.equals(finderColumn));
	}

	private FinderColumn.Builder builder = new FinderColumn.Builder(
		"companyId");

}