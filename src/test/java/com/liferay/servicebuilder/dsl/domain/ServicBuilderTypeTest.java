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
public class ServicBuilderTypeTest {

	@Test
	public void testToString() {
		Assert.assertEquals("Blob", ServiceBuilderType.BLOB.toString());
		Assert.assertEquals("Boolean", ServiceBuilderType.BOOLEAN.toString());
		Assert.assertEquals(
			"Collection", ServiceBuilderType.COLLECTION.toString());
		Assert.assertEquals("Date", ServiceBuilderType.DATE.toString());
		Assert.assertEquals("double", ServiceBuilderType.DOUBLE.toString());
		Assert.assertEquals("int", ServiceBuilderType.INTEGER.toString());
		Assert.assertEquals("long", ServiceBuilderType.LONG.toString());
		Assert.assertEquals("String", ServiceBuilderType.STRING.toString());
	}

}