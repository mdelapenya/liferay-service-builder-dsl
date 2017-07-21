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
public class TxRequiredMethodTest {

	@Test
	public void testBuild() {
		TxRequiredMethod txRequiredMethod = builder.build();

		Assert.assertEquals("getFoo", txRequiredMethod.getMethodName());
	}

	@Test
	public void testBuildAddMethod() {
		TxRequiredMethod txRequiredMethod = builder.build();

		Assert.assertEquals("getFoo", txRequiredMethod.getMethodName());
	}

	@Test
	public void testEquals() {
		TxRequiredMethod txRequiredMethod1 = new TxRequiredMethod.Builder(
			"method1").build();
		TxRequiredMethod txRequiredMethod2 = new TxRequiredMethod.Builder(
			"method1").build();

		Assert.assertTrue(txRequiredMethod1.equals(txRequiredMethod2));
		Assert.assertTrue(txRequiredMethod2.equals(txRequiredMethod1));
	}

	@Test
	public void testEqualsNotEquals() {
		TxRequiredMethod txRequiredMethod1 = new TxRequiredMethod.Builder(
			"method1").build();
		TxRequiredMethod txRequiredMethod2 = new TxRequiredMethod.Builder(
			"method2").build();

		Assert.assertFalse(txRequiredMethod1.equals(txRequiredMethod2));
		Assert.assertFalse(txRequiredMethod2.equals(txRequiredMethod1));
	}

	@Test
	public void testEqualsSameInstance() {
		TxRequiredMethod txRequiredMethod = new TxRequiredMethod.Builder(
			"method1").build();

		Assert.assertTrue(txRequiredMethod.equals(txRequiredMethod));
	}

	private TxRequiredMethod.Builder builder = new TxRequiredMethod.Builder(
		"getFoo");

}