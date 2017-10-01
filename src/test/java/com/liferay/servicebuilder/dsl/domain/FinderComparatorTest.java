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
public class FinderComparatorTest {

	@Test
	public void testToString() {
		Assert.assertEquals("!=", FinderComparator.DISTINCT.toString());
		Assert.assertEquals("=", FinderComparator.EQUALS.toString());
		Assert.assertEquals(">", FinderComparator.GREATER.toString());
		Assert.assertEquals(">=", FinderComparator.GREATER_EQUALS.toString());
		Assert.assertEquals("<", FinderComparator.LESS.toString());
		Assert.assertEquals("<=", FinderComparator.LESS_EQUALS.toString());
		Assert.assertEquals("LIKE", FinderComparator.LIKE.toString());
	}

}