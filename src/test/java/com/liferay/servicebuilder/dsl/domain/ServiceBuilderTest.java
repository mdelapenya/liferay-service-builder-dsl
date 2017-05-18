package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ServiceBuilderTest {

	@Test
	public void testBuild() {
		ServiceBuilder serviceBuilder = builder.build();

		Assert.assertEquals("com.liferay.foo", serviceBuilder.getPackagePath());
		Assert.assertFalse(serviceBuilder.isAutoImportDefaultReferences());
		Assert.assertFalse(serviceBuilder.isAutoNamespaceTables());
	}

	@Test
	public void testBuildAutoImportDefaultReferences() {
		ServiceBuilder serviceBuilder = builder
			.autoImportDefaultReferences()
			.build();

		Assert.assertTrue(serviceBuilder.isAutoImportDefaultReferences());
	}

	@Test
	public void testBuildAutoNamespaceTables() {
		ServiceBuilder serviceBuilder = builder.autoNamespaceTables().build();

		Assert.assertTrue(serviceBuilder.isAutoNamespaceTables());
	}

	private ServiceBuilder.Builder builder = new ServiceBuilder.Builder(
		"com.liferay.foo");

}