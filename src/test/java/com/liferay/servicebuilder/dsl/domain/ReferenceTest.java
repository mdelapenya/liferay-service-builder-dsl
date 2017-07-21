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
public class ReferenceTest {

	@Test
	public void testBuild() {
		Reference reference = builder.build();

		Assert.assertEquals("JournalFolder", reference.getEntity());
		Assert.assertEquals("com.liferay.journal", reference.getPackagePath());
	}

	@Test
	public void testBuildInjectService() {
		Reference reference = builder .build();

		Assert.assertEquals("JournalFolder", reference.getEntity());
		Assert.assertEquals("com.liferay.journal", reference.getPackagePath());
	}

	@Test
	public void testEquals() {
		Reference reference1 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();
		Reference reference2 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();

		Assert.assertTrue(reference1.equals(reference2));
		Assert.assertTrue(reference2.equals(reference1));
	}

	@Test
	public void testEqualsNotEquals() {
		Reference reference1 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal.folder").build();
		Reference reference2 = new Reference.Builder(
			"JournalArticle", "com.liferay.journal.article").build();

		Assert.assertFalse(reference1.equals(reference2));
		Assert.assertFalse(reference2.equals(reference1));
	}

	@Test
	public void testEqualsNotEqualsDifferentEntity() {
		Reference reference1 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();
		Reference reference2 = new Reference.Builder(
			"JournalArticle", "com.liferay.journal").build();

		Assert.assertFalse(reference1.equals(reference2));
		Assert.assertFalse(reference2.equals(reference1));
	}

	@Test
	public void testEqualsNotEqualsDifferentPackagePath() {
		Reference reference1 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal.folder").build();
		Reference reference2 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal.article").build();

		Assert.assertFalse(reference1.equals(reference2));
		Assert.assertFalse(reference2.equals(reference1));
	}

	@Test
	public void testEqualsSameInstance() {
		Reference reference = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();

		Assert.assertTrue(reference.equals(reference));
	}

	private Reference.Builder builder = new Reference.Builder(
		"JournalFolder", "com.liferay.journal");

}