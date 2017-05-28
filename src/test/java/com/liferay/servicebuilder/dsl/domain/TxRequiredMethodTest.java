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

		Assert.assertNull(txRequiredMethod.getMethodName());
	}

	@Test
	public void testBuildAddMethod() {
		TxRequiredMethod txRequiredMethod = builder
			.addMethodRequiringTxPropagation("getFoo")
			.build();

		Assert.assertEquals("getFoo", txRequiredMethod.getMethodName());
	}

	private TxRequiredMethod.Builder builder = new TxRequiredMethod.Builder();

}