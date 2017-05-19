package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
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
		Assert.assertTrue(entity.getDatasource() == null);
		Assert.assertTrue(entity.getHumanName() == null);
		Assert.assertTrue(entity.getPersistenceClass() == null);
		Assert.assertFalse(entity.hasRemoteService());
		Assert.assertTrue(entity.getSessionFactory() == null);
		Assert.assertTrue(entity.getTable() == null);
		Assert.assertFalse(entity.hasTrashEnabled());
		Assert.assertFalse(entity.hasUuid());
		Assert.assertFalse(entity.hasUuidAccessor());
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
	public void testBuildWithColumns() {
		Column companyIdColumn = new Column.Builder("companyId", "long")
			.build();
		Column groupIdColumn = new Column.Builder("groupId", "long").build();

		Entity entity = builder
			.withColumn(companyIdColumn)
			.withColumn(groupIdColumn)
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
	public void testBuildWithHumanName() {
		Entity entity = builder.withHumanName("human-name").build();

		Assert.assertEquals("human-name", entity.getHumanName());
	}

	@Test
	public void testBuildWithLocalServices() {
		Entity entity = builder.withLocalServices().build();

		Assert.assertTrue(entity.hasLocalService());
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