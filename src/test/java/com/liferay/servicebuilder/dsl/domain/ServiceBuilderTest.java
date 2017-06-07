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
	public void testBuildAutoWithAuthor() {
		ServiceBuilder serviceBuilder = builder
			.withAuthor("Manuel de la Peña")
			.build();

		Assert.assertEquals("Manuel de la Peña", serviceBuilder.getAuthor());
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
	public void testBuildWithEntities() {
		Entity journalArticleEntity = new Entity.Builder(
			"JournalArticle").build();

		Entity journalArticleLocalizationEntity = new Entity.Builder(
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
		Entity journalArticleEntity = new Entity.Builder(
			"JournalArticle").build();

		ServiceBuilder serviceBuilder = builder
			.withEntity(journalArticleEntity)
			.build();

		List<Entity> entities = serviceBuilder.getEntities();

		Assert.assertEquals(entities.toString(), 1, entities.size());
	}

	@Test
	public void testBuildWithEntityDuplicatedDoesNotAddIt() {
		Entity journalArticleEntity = new Entity.Builder(
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
			.withException("ArticleContent")
			.withException("ArticleDisplayDate")
			.build();

		List<String> exceptions = serviceBuilder.getExceptions();

		Assert.assertEquals(exceptions.toString(), 2, exceptions.size());
	}

	@Test
	public void testBuildWithMvccEnabled() {
		ServiceBuilder serviceBuilder = builder.enableMvcc().build();

		Assert.assertTrue(serviceBuilder.isMvccEnabled());
	}

	private ServiceBuilder.Builder builder = new ServiceBuilder.Builder(
		"com.liferay.foo", "Journal");

}