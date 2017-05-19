package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class ServiceBuilderTest {

	@Test
	public void testBuild() {
		ServiceBuilder serviceBuilder = builder.build();

		Assert.assertEquals("com.liferay.foo", serviceBuilder.getPackagePath());
		Assert.assertEquals("Journal", serviceBuilder.getNamespace());
		Assert.assertFalse(serviceBuilder.hasAutoImportDefaultReferences());
		Assert.assertFalse(serviceBuilder.hasAutoNamespaceTables());
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
	public void testBuildWithEntity() {
		Entity journalArticleEntity =
			new Entity.Builder("JournalArticle").build();

		ServiceBuilder serviceBuilder = builder
			.withEntity(journalArticleEntity)
			.build();

		List<Entity> entities = serviceBuilder.getEntities();

		Assert.assertEquals(1, entities.size());
	}

	@Test
	public void testBuildWithEntities() {
		Entity journalArticleEntity =
			new Entity.Builder("JournalArticle").build();

		Entity journalArticleLocalizationEntity =
			new Entity.Builder("JournalArticleLocalization").build();

		ServiceBuilder serviceBuilder = builder
			.withEntity(journalArticleEntity)
			.withEntity(journalArticleLocalizationEntity)
			.build();

		List<Entity> entities = serviceBuilder.getEntities();

		Assert.assertEquals(2, entities.size());
	}

	@Test
	public void testBuildWithException() {
		ServiceBuilder serviceBuilder = builder
			.withException("ArticleContent")
			.build();

		List<String> exceptions = serviceBuilder.getExceptions();

		Assert.assertEquals(1, exceptions.size());
	}

	@Test
	public void testBuildWithExceptions() {
		ServiceBuilder serviceBuilder = builder
			.withException("ArticleContent")
			.withException("ArticleDisplayDate")
			.build();

		List<String> exceptions = serviceBuilder.getExceptions();

		Assert.assertEquals(2, exceptions.size());
	}

	private ServiceBuilder.Builder builder = new ServiceBuilder.Builder(
		"com.liferay.foo", "Journal");

}