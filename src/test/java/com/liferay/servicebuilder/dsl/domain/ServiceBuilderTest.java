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

import java.util.List;

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
		Assert.assertEquals("Journal", serviceBuilder.getNamespace());
		Assert.assertNull(serviceBuilder.getAuthor());
		Assert.assertTrue(serviceBuilder.getEntities().isEmpty());
		Assert.assertTrue(serviceBuilder.getExceptions().isEmpty());
		Assert.assertTrue(serviceBuilder.getServiceBuilderImports().isEmpty());
		Assert.assertFalse(serviceBuilder.hasAutoImportDefaultReferences());
		Assert.assertFalse(serviceBuilder.hasAutoNamespaceTables());
		Assert.assertFalse(serviceBuilder.isMvccEnabled());
	}

	@Test
	public void testBuildAutoImportDefaultReferences() {
		ServiceBuilder serviceBuilder = builder
			.autoImportDefaultReferences()
			.build();

		Assert.assertTrue(serviceBuilder.hasAutoImportDefaultReferences());
	}

	@Test
	public void testBuildAutoNamespaceTables() {
		ServiceBuilder serviceBuilder = builder.autoNamespaceTables().build();

		Assert.assertTrue(serviceBuilder.hasAutoNamespaceTables());
	}

	@Test
	public void testBuildImportServiceBuilderFile() {
		ServiceBuilder serviceBuilder = builder
			.importServiceBuilderFile("../foo/service.xml")
			.build();

		List<String> serviceBuilderImports =
			serviceBuilder.getServiceBuilderImports();

		Assert.assertEquals(
			serviceBuilderImports.toString(), 1, serviceBuilderImports.size());
	}

	@Test
	public void testBuildImportServiceBuilderFileDuplicatedDoesNotAddIt() {
		ServiceBuilder serviceBuilder = builder
			.importServiceBuilderFile("../foo/service.xml")
			.importServiceBuilderFile("../foo/service.xml")
			.build();

		List<String> serviceBuilderImports =
			serviceBuilder.getServiceBuilderImports();

		Assert.assertEquals(
			serviceBuilderImports.toString(), 1, serviceBuilderImports.size());
	}

	@Test
	public void testBuildImportServiceBuilderFiles() {
		ServiceBuilder serviceBuilder = builder
			.importServiceBuilderFile("../foo/service.xml")
			.importServiceBuilderFile("../bar/service.xml")
			.build();

		List<String> serviceBuilderImports =
			serviceBuilder.getServiceBuilderImports();

		Assert.assertEquals(
			serviceBuilderImports.toString(), 2, serviceBuilderImports.size());
	}

	@Test
	public void testBuildWithAuthor() {
		ServiceBuilder serviceBuilder = builder
			.withAuthor("Manuel de la Peña")
			.build();

		Assert.assertEquals("Manuel de la Peña", serviceBuilder.getAuthor());
	}

	@Test
	public void testBuildWithEntities() {
		Entity journalArticleEntity = new Entity.BuilderImpl(
			"JournalArticle").build();

		Entity journalArticleLocalizationEntity = new Entity.BuilderImpl(
			"JournalArticleLocalization").build();

		ServiceBuilder serviceBuilder = builder
			.withEntity(journalArticleEntity)
			.withEntity(journalArticleLocalizationEntity)
			.build();

		List<Entity> entities = serviceBuilder.getEntities();

		Assert.assertEquals(entities.toString(), 2, entities.size());
	}

	@Test
	public void testBuildWithEntity() {
		Entity journalArticleEntity = new Entity.BuilderImpl(
			"JournalArticle").build();

		ServiceBuilder serviceBuilder = builder
			.withEntity(journalArticleEntity)
			.build();

		List<Entity> entities = serviceBuilder.getEntities();

		Assert.assertEquals(entities.toString(), 1, entities.size());
	}

	@Test
	public void testBuildWithEntityDuplicatedDoesNotAddIt() {
		Entity journalArticleEntity = new Entity.BuilderImpl(
			"JournalArticle").build();

		ServiceBuilder serviceBuilder = builder
			.withEntity(journalArticleEntity)
			.withEntity(journalArticleEntity)
			.build();

		List<Entity> entities = serviceBuilder.getEntities();

		Assert.assertEquals(entities.toString(), 1, entities.size());
	}

	@Test
	public void testBuildWithException() {
		ServiceBuilder serviceBuilder = builder
			.withException("ArticleContent")
			.build();

		List<String> exceptions = serviceBuilder.getExceptions();

		Assert.assertEquals(exceptions.toString(), 1, exceptions.size());
	}

	@Test
	public void testBuildWithExceptionDuplicatedDoesNotAddIt() {
		ServiceBuilder serviceBuilder = builder
			.withException("ArticleContent")
			.withException("ArticleContent")
			.build();

		List<String> exceptions = serviceBuilder.getExceptions();

		Assert.assertEquals(exceptions.toString(), 1, exceptions.size());
	}

	@Test
	public void testBuildWithExceptions() {
		ServiceBuilder serviceBuilder = builder
			.withExceptions("ArticleContent", "ArticleDisplayDate")
			.build();

		List<String> exceptions = serviceBuilder.getExceptions();

		Assert.assertEquals(exceptions.toString(), 2, exceptions.size());
	}

	@Test
	public void testBuildWithMvccEnabled() {
		ServiceBuilder serviceBuilder = builder.enableMvcc().build();

		Assert.assertTrue(serviceBuilder.isMvccEnabled());
	}

	@Test
	public void testBuildWithTwoAuthors() {
		ServiceBuilder serviceBuilder = builder
			.withAuthor("Manuel de la Peña")
			.withAuthor("Leo Messi")
			.build();

		Assert.assertEquals("Leo Messi", serviceBuilder.getAuthor());
	}

	private ServiceBuilder.Builder builder = new ServiceBuilder.Builder(
		"com.liferay.foo", "Journal");

}