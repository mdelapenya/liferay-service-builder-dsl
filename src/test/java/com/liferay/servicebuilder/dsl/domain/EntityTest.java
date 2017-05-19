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
		Assert.assertFalse(entity.hasRemoteService());
		Assert.assertFalse(entity.hasTrashEnabled());
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
	public void testBuildWithLocalServices() {
		Entity entity = builder.withLocalServices().build();

		Assert.assertTrue(entity.hasLocalService());
	}

	@Test
	public void testBuildWithRemoteServices() {
		Entity entity = builder.withRemoteServices().build();

		Assert.assertTrue(entity.hasRemoteService());
	}

	@Test
	public void testBuildWithTrashEnabled() {
		Entity entity = builder.withTrashEnabled().build();

		Assert.assertTrue(entity.hasTrashEnabled());
	}

	private Entity.Builder builder = new Entity.Builder("JournalArticle");

}