package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class EntityTest {

	@Test
	public void testBuild() {
		Entity entity = builder.build();

		Assert.assertEquals("JournalArticle", entity.getName());

		List<Column> columns = entity.getColumns();

		Assert.assertTrue(columns.isEmpty());

		Assert.assertFalse(entity.hasLocalService());
		Assert.assertNull(entity.getDatasource());
		Assert.assertNull(entity.getHumanName());
		Assert.assertNull(entity.getOrder());
		Assert.assertNull(entity.getPersistenceClass());
		Assert.assertTrue(entity.hasCacheEnabled());
		Assert.assertFalse(entity.hasDynamicUpdate());
		Assert.assertFalse(entity.hasJsonSerialization());
		Assert.assertFalse(entity.hasMvccEnabled());
		Assert.assertFalse(entity.hasRemoteService());
		Assert.assertFalse(entity.isDeprecated());
		Assert.assertNull(entity.getSessionFactory());
		Assert.assertNull(entity.getTable());
		Assert.assertNull(entity.getTxManager());
		Assert.assertFalse(entity.hasTrashEnabled());
		Assert.assertFalse(entity.hasUuid());
		Assert.assertFalse(entity.hasUuidAccessor());
	}

	@Test
	public void testBuildWithCacheDisabled() {
		Entity entity = builder.disableCache().build();

		Assert.assertFalse(entity.hasCacheEnabled());
	}

	@Test
	public void testBuildWithColumn() {
		Column companyIdColumn = new Column.Builder("companyId", "long")
			.build();

		Entity entity = builder.withColumn(companyIdColumn).build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(1, columns.size());
	}

	@Test
	public void testBuildWithColumnDuplicatedDoesNotAddIt() {
		Column companyIdColumn = new Column.Builder("companyId", "long")
			.build();

		Entity entity = builder
			.withColumn(companyIdColumn)
			.withColumn(companyIdColumn)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(1, columns.size());
	}

	@Test
	public void testBuildWithColumns() {
		Column companyIdColumn = new Column.Builder("companyId", "long")
			.build();
		Column groupIdColumn = new Column.Builder("groupId", "long").build();

		Entity entity = builder
			.withColumns(companyIdColumn, groupIdColumn)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(2, columns.size());
	}

	@Test
	public void testBuildWithDatasource() {
		Entity entity = builder.withDatasource("datasource").build();

		Assert.assertEquals("datasource", entity.getDatasource());
	}

	@Test
	public void testBuildWithDeprecation() {
		Entity entity = builder.deprecate().build();

		Assert.assertTrue(entity.isDeprecated());
	}

	@Test
	public void testBuildWithDynamicUpdate() {
		Entity entity = builder.withDynamicUpdate(true).build();

		Assert.assertTrue(entity.hasDynamicUpdate());
	}

	@Test
	public void testBuildWithDynamicUpdateFromMvcc() {
		Entity entity = builder.withMvcc(true).build();

		Assert.assertEquals(entity.hasMvccEnabled(), entity.hasDynamicUpdate());
	}

	@Ignore
	@Test
	public void testBuildWithDynamicUpdateFromMvccAfter() {
		Entity entity = builder
			.withDynamicUpdate(true)
			.withMvcc(false)
			.build();

		Assert.assertTrue(entity.hasDynamicUpdate());
	}

	@Test
	public void testBuildWithFinder() {
		Finder.Builder finderBuilder = new Finder.Builder(
			"G_C_DDMSK", "JournalArticle");

		Finder finder = finderBuilder.build();

		Entity entity = builder.withFinder(finder).build();

		Assert.assertEquals(1, entity.getFinders().size());
	}

	@Test
	public void testBuildWithFinderDuplicatedDoesNotAddIt() {
		Finder.Builder finderBuilder = new Finder.Builder(
			"G_C_DDMSK", "JournalArticle");

		Finder finder = finderBuilder.build();

		Entity entity = builder
			.withFinder(finder)
			.withFinder(finder)
			.build();

		Assert.assertEquals(1, entity.getFinders().size());
	}

	@Test
	public void testBuildWithFinders() {
		Finder.Builder finderBuilder1 = new Finder.Builder(
			"G_C_DDMSK", "JournalArticle");

		Finder finder1 = finderBuilder1.build();

		Finder.Builder finderBuilder2 = new Finder.Builder(
			"G_C", "JournalArticle");

		Finder finder2 = finderBuilder2.build();

		Entity entity = builder.withFinders(finder1, finder2).build();

		Assert.assertEquals(2, entity.getFinders().size());
	}

	@Test
	public void testBuildWithHumanName() {
		Entity entity = builder.withHumanName("human-name").build();

		Assert.assertEquals("human-name", entity.getHumanName());
	}

	@Test
	public void testBuildWithJsonSerialization() {
		Entity entity = builder.withJsonSerialization().build();

		Assert.assertTrue(entity.hasJsonSerialization());
	}

	@Test
	public void testBuildWithJsonSerializationFromRemoteService() {
		Entity entity = builder.withRemoteServices().build();

		Assert.assertTrue(entity.hasJsonSerialization());
	}

	@Test
	public void testBuildWithLocalServices() {
		Entity entity = builder.withLocalServices().build();

		Assert.assertTrue(entity.hasLocalService());
	}

	@Test
	public void testBuildWithMvccDisabled() {
		Entity entity = builder.withMvcc(false).build();

		Assert.assertFalse(entity.hasMvccEnabled());
	}

	@Test
	public void testBuildWithMvccEnabled() {
		Entity entity = builder.withMvcc(true).build();

		Assert.assertTrue(entity.hasMvccEnabled());
	}

	@Test
	public void testBuildWithOrder() {
		Order order = new Order.Builder().build();

		Entity entity = builder.withOrder(order).build();

		Assert.assertNotNull(entity.getOrder());
	}

	@Test
	public void testBuildWithPersistenceClass() {
		Entity entity = builder
			.withPersistenceClass("com.liferay.foo.Foo")
			.build();

		Assert.assertEquals(
			"com.liferay.foo.Foo", entity.getPersistenceClass());
	}

	@Test
	public void testBuildWithReference() {
		Reference reference = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();

		Entity entity = builder.withReference(reference).build();

		List<Reference> references = entity.getReferences();

		Assert.assertEquals(1, references.size());
	}

	@Test
	public void testBuildWithReferenceDuplicatedDoesNotAddIt() {
		Reference reference = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();

		Entity entity = builder
			.withReference(reference)
			.withReference(reference)
			.build();

		List<Reference> references = entity.getReferences();

		Assert.assertEquals(1, references.size());
	}

	@Test
	public void testBuildWithReferences() {
		Reference reference1 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();
		Reference reference2 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();

		Entity entity = builder
			.withReferences(reference1, reference2)
			.build();

		List<Reference> references = entity.getReferences();

		Assert.assertEquals(2, references.size());
	}

	@Test
	public void testBuildWithRemoteServices() {
		Entity entity = builder.withRemoteServices().build();

		Assert.assertTrue(entity.hasRemoteService());
	}

	@Test
	public void testBuildWithSessionFactory() {
		Entity entity = builder.withSessionFactory("sessionFactory").build();

		Assert.assertEquals("sessionFactory", entity.getSessionFactory());
	}

	@Test
	public void testBuildWithTable() {
		Entity entity = builder.withTable("table").build();

		Assert.assertNotEquals(entity.getName(), entity.getTable());
		Assert.assertEquals("table", entity.getTable());
	}

	@Test
	public void testBuildWithTxManager() {
		Entity entity = builder.withTxManager("txManager").build();

		Assert.assertEquals("txManager", entity.getTxManager());
	}

	@Test
	public void testBuildWithTxRequiredMethod() {
		TxRequiredMethod txRequiredMethod =
			new TxRequiredMethod.Builder("getFoo").build();

		Entity entity = builder.withTxRequiredMethod(txRequiredMethod).build();

		List<TxRequiredMethod> txRequiredMethods = entity.getTxRequiredMethods();

		Assert.assertEquals(1, txRequiredMethods.size());
	}

	@Test
	public void testBuildWithTxRequiredMethodDuplicatedDoesNotAddIt() {
		TxRequiredMethod txRequiredMethod =
			new TxRequiredMethod.Builder("getFoo").build();

		Entity entity = builder
			.withTxRequiredMethod(txRequiredMethod)
			.withTxRequiredMethod(txRequiredMethod)
			.build();

		List<TxRequiredMethod> txRequiredMethods = entity.getTxRequiredMethods();

		Assert.assertEquals(1, txRequiredMethods.size());
	}

	@Test
	public void testBuildWithTxRequiredMethods() {
		TxRequiredMethod txRequiredMethod1 =
			new TxRequiredMethod.Builder("getFoo").build();
		TxRequiredMethod txRequiredMethod2 =
			new TxRequiredMethod.Builder("getBar").build();

		Entity entity = builder
			.withTxRequiredMethods(txRequiredMethod1, txRequiredMethod2)
			.build();

		List<TxRequiredMethod> txRequiredMethods = entity.getTxRequiredMethods();

		Assert.assertEquals(2, txRequiredMethods.size());
	}

	@Test
	public void testBuildWithTrashEnabled() {
		Entity entity = builder.withTrashEnabled().build();

		Assert.assertTrue(entity.hasTrashEnabled());
	}

	@Test
	public void testBuildWithUuid() {
		Entity entity = builder.withUuid().build();

		Assert.assertTrue(entity.hasUuid());
	}

	@Test
	public void testBuildWithUuidAccessor() {
		Entity entity = builder.withUuidAccessor().build();

		Assert.assertTrue(entity.hasUuidAccessor());
	}

	private Entity.Builder builder = new Entity.Builder("JournalArticle");

}