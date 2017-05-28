package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ReferenceTest {

	@Test
	public void testBuild() {
		Reference reference = builder.build();

		Assert.assertNull(reference.getEntity());
		Assert.assertNull(reference.getPackagePath());
	}

	@Test
	public void testBuildInject() {
		Reference reference = builder
			.inject("JournalFolder", "com.liferay.journal")
			.build();

		Assert.assertEquals("JournalFolder", reference.getEntity());
		Assert.assertEquals("com.liferay.journal", reference.getPackagePath());
	}

	private Reference.Builder builder = new Reference.Builder();
}