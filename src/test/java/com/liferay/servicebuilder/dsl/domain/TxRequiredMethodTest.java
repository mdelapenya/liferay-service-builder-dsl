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