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

import com.liferay.servicebuilder.dsl.domain.column.Column;
import com.liferay.servicebuilder.dsl.domain.column.ColumnBuilderFactory;
import com.liferay.servicebuilder.dsl.domain.column.FilterPrimaryColumn;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

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
		Assert.assertEquals(entity.getName(), entity.getHumanName());
		Assert.assertNull(entity.getOrder());
		Assert.assertNull(entity.getPersistenceClass());
		Assert.assertTrue(entity.hasCacheEnabled());

		Assert.assertFalse(entity.hasMvccEnabled());
		Assert.assertEquals(entity.hasMvccEnabled(), entity.hasDynamicUpdate());

		Assert.assertTrue(entity.hasRemoteService());
		Assert.assertEquals(
			entity.hasRemoteService(), entity.hasJsonSerialization());

		Assert.assertFalse(entity.isDeprecated());
		Assert.assertNull(entity.getSessionFactory());
		Assert.assertEquals(entity.getName(), entity.getTable());
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
		Column companyIdColumn = ColumnBuilderFactory.getColumnBuilder(
			"companyId", ServiceBuilderType.LONG).build();

		Entity entity = builder.withColumn(companyIdColumn).build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 1, columns.size());
	}

	@Test
	public void testBuildWithColumnDuplicatedDoesNotAddIt() {
		Column companyIdColumn = ColumnBuilderFactory.getColumnBuilder(
			"companyId", ServiceBuilderType.LONG).build();

		Entity entity = builder
			.withColumn(companyIdColumn)
			.withColumn(companyIdColumn)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 1, columns.size());
	}

	@Test
	public void testBuildWithColumnFilterPrimaryAllowsOtherColumn() {
		FilterPrimaryColumn companyIdColumn =
			(FilterPrimaryColumn)ColumnBuilderFactory
				.getFilterPrimaryColumnBuilder(
					"companyId", ServiceBuilderType.LONG)
				.build();

		Column groupIdColumn = ColumnBuilderFactory.getColumnBuilder(
				"groupId", ServiceBuilderType.LONG)
			.build();

		Entity entity = builder
			.withFilterPrimaryColumn(companyIdColumn)
			.withColumn(groupIdColumn)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 2, columns.size());
	}

	@Test
	public void testBuildWithColumnFilterPrimaryDoesNotAllowColumnsWithSameName() {
		FilterPrimaryColumn companyIdColumn =
			(FilterPrimaryColumn)ColumnBuilderFactory
				.getFilterPrimaryColumnBuilder(
					"companyId", ServiceBuilderType.LONG)
				.build();

		Column groupIdColumn = ColumnBuilderFactory.getColumnBuilder(
				"companyId", ServiceBuilderType.LONG)
			.build();

		Entity entity = builder
			.withFilterPrimaryColumn(companyIdColumn)
			.withColumn(groupIdColumn)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 1, columns.size());
	}

	@Test
	public void testBuildWithColumnFilterPrimaryDoesNotAllowOtherFilterPrimaryColumn() {
		FilterPrimaryColumn companyIdColumn =
			(FilterPrimaryColumn)ColumnBuilderFactory
				.getFilterPrimaryColumnBuilder(
					"companyId", ServiceBuilderType.LONG)
				.build();

		Entity entity = builder
			.withFilterPrimaryColumn(companyIdColumn)

			// compiler does not allow invoking withFilterPrimaryColumn twice

			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 1, columns.size());
	}

	@Test
	public void testBuildWithColumns() {
		Column companyIdColumn = ColumnBuilderFactory.getColumnBuilder(
			"companyId", ServiceBuilderType.LONG).build();
		Column groupIdColumn = ColumnBuilderFactory.getColumnBuilder(
			"groupId", ServiceBuilderType.LONG).build();

		Entity entity = builder
			.withColumns(companyIdColumn, groupIdColumn)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 2, columns.size());
	}

	@Test
	public void testBuildWithColumnUsingName() {
		Entity entity = builder.withColumn("companyId", ServiceBuilderType.LONG)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 1, columns.size());
	}


	@Test
	public void testBuildWithColumnUsingNameDuplicatedDoesNotAddIt() {
		Entity entity = builder
			.withColumn("companyId", ServiceBuilderType.LONG)
			.withColumn("companyId", ServiceBuilderType.LONG)
			.build();

		List<Column> columns = entity.getColumns();

		Assert.assertEquals(columns.toString(), 1, columns.size());
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
	public void testBuildWithDynamicUpdateIsNotModifiedByMvcc() {
		Entity entity = builder
			.withDynamicUpdate(true)
			.withMvcc(false)
			.build();

		Assert.assertTrue(entity.hasDynamicUpdate());

		entity = builder
			.withDynamicUpdate(false)
			.withMvcc(true)
			.build();

		Assert.assertFalse(entity.hasDynamicUpdate());
	}

	@Test
	public void testBuildWithDynamicUpdateIsNotNull() {
		Entity entity = builder.build();

		Assert.assertFalse(entity.hasDynamicUpdate());
	}

	@Test
	public void testBuildWithDynamicUpdateWhenIsNotSetTakesMvccValue() {
		Entity entity = builder.withMvcc(true).build();

		Assert.assertEquals(entity.hasMvccEnabled(), entity.hasDynamicUpdate());
		Assert.assertTrue(entity.hasDynamicUpdate());

		entity = builder.withMvcc(false).build();

		Assert.assertEquals(entity.hasMvccEnabled(), entity.hasDynamicUpdate());
		Assert.assertFalse(entity.hasDynamicUpdate());
	}

	@Test
	public void testBuildWithFinder() {
		FinderColumn finderColumn = new FinderColumn.Builder("groupId").build();

		Finder.Builder finderBuilder = new Finder.Builder(
			"G_C_DDMSK", "JournalArticle", finderColumn);

		Finder finder = finderBuilder.build();

		Entity entity = builder.withFinder(finder).build();

		Assert.assertEquals(1, entity.getFinders().size());
	}

	@Test
	public void testBuildWithFinderDuplicatedDoesNotAddIt() {
		FinderColumn finderColumn = new FinderColumn.Builder("groupId").build();

		Finder.Builder finderBuilder = new Finder.Builder(
			"G_C_DDMSK", "JournalArticle", finderColumn);

		Finder finder = finderBuilder.build();

		Entity entity = builder
			.withFinder(finder)
			.withFinder(finder)
			.build();

		Assert.assertEquals(1, entity.getFinders().size());
	}

	@Test
	public void testBuildWithFinderFromSimpleMethod() {
		Entity entity = builder.withFinder(
			"G_C_DDMSK", "JournalArticle", "groupId").build();

		Assert.assertEquals(1, entity.getFinders().size());

		Finder finder = entity.getFinders().get(0);

		Assert.assertEquals("G_C_DDMSK", finder.getName());
		Assert.assertEquals("JournalArticle", finder.getReturnType());

		List<FinderColumn> finderColumns = finder.getFinderColumns();

		Assert.assertEquals(1, finderColumns.size());

		FinderColumn finderColumn = finderColumns.get(0);

		Assert.assertEquals("groupId", finderColumn.getName());
		Assert.assertTrue(finderColumn.isCaseSensitive());
		Assert.assertNull(finderColumn.getComparator());
		Assert.assertNull(finderColumn.getArrayableOperator());
	}

	@Test
	public void testBuildWithFinders() {
		FinderColumn finderColumn = new FinderColumn.Builder("groupId").build();

		Finder.Builder finderBuilder1 = new Finder.Builder(
			"G_C_DDMSK", "JournalArticle", finderColumn);

		Finder finder1 = finderBuilder1.build();

		Finder.Builder finderBuilder2 = new Finder.Builder(
			"G_C", "JournalArticle", finderColumn);

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

		Assert.assertEquals(references.toString(), 1, references.size());
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

		Assert.assertEquals(references.toString(), 1, references.size());
	}

	@Test
	public void testBuildWithReferences() {
		Reference reference1 = new Reference.Builder(
			"JournalArticle", "com.liferay.journal").build();
		Reference reference2 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();

		Entity entity = builder
			.withReferences(reference1, reference2)
			.build();

		List<Reference> references = entity.getReferences();

		Assert.assertEquals(references.toString(), 2, references.size());
	}

	@Test
	public void testBuildWithReferencesDuplicatedDoesNotAddIt() {
		Reference reference1 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();
		Reference reference2 = new Reference.Builder(
			"JournalFolder", "com.liferay.journal").build();

		Entity entity = builder
			.withReferences(reference1, reference2)
			.build();

		List<Reference> references = entity.getReferences();

		Assert.assertEquals(references.toString(), 1, references.size());
	}

	@Test
	public void testBuildWithReferenceUsingName() {
		Entity entity = builder.withReference(
			"JournalFolder", "com.liferay.journal").build();

		List<Reference> references = entity.getReferences();

		Assert.assertEquals(references.toString(), 1, references.size());
	}

	@Test
	public void testBuildWithReferenceUsingNameDuplicatedDoesNotAddIt() {
		Entity entity = builder
			.withReference("JournalFolder", "com.liferay.journal")
			.withReference("JournalFolder", "com.liferay.journal")
			.build();

		List<Reference> references = entity.getReferences();

		Assert.assertEquals(references.toString(), 1, references.size());
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
	public void testBuildWithTxManager() {
		Entity entity = builder.withTxManager("txManager").build();

		Assert.assertEquals("txManager", entity.getTxManager());
	}

	@Test
	public void testBuildWithTxManagerDisabled() {
		Entity entity = builder.disableTxManager().build();

		Assert.assertEquals("none", entity.getTxManager());
	}

	@Test
	public void testBuildWithTxRequiredMethod() {
		TxRequiredMethod txRequiredMethod = new TxRequiredMethod.Builder(
			"getFoo").build();

		Entity entity = builder.withTxRequiredMethod(txRequiredMethod).build();

		List<TxRequiredMethod> txRequiredMethods =
			entity.getTxRequiredMethods();

		Assert.assertEquals(
			txRequiredMethods.toString(), 1, txRequiredMethods.size());
	}

	@Test
	public void testBuildWithTxRequiredMethodDuplicatedDoesNotAddIt() {
		TxRequiredMethod txRequiredMethod = new TxRequiredMethod.Builder(
			"getFoo").build();

		Entity entity = builder
			.withTxRequiredMethod(txRequiredMethod)
			.withTxRequiredMethod(txRequiredMethod)
			.build();

		List<TxRequiredMethod> txRequiredMethods =
			entity.getTxRequiredMethods();

		Assert.assertEquals(
			txRequiredMethods.toString(), 1, txRequiredMethods.size());
	}

	@Test
	public void testBuildWithTxRequiredMethods() {
		TxRequiredMethod txRequiredMethod1 = new TxRequiredMethod.Builder(
			"getFoo").build();
		TxRequiredMethod txRequiredMethod2 = new TxRequiredMethod.Builder(
			"getBar").build();

		Entity entity = builder
			.withTxRequiredMethods(txRequiredMethod1, txRequiredMethod2)
			.build();

		List<TxRequiredMethod> txRequiredMethods =
			entity.getTxRequiredMethods();

		Assert.assertEquals(
			txRequiredMethods.toString(), 2, txRequiredMethods.size());
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

	@Test
	public void testEquals() {
		Entity entity1 = new Entity.BuilderImpl("JournalArticle").build();
		Entity entity2 = new Entity.BuilderImpl("JournalArticle").build();

		Assert.assertTrue(entity1.equals(entity2));
		Assert.assertTrue(entity2.equals(entity1));
	}

	@Test
	public void testEqualsNotEquals() {
		Entity entity1 = new Entity.BuilderImpl("JournalArticle").build();
		Entity entity2 = new Entity.BuilderImpl(
			"JournalArticleVersion").build();

		Assert.assertFalse(entity1.equals(entity2));
		Assert.assertFalse(entity2.equals(entity1));
	}

	@Test
	public void testEqualsSameInstance() {
		Entity entity = new Entity.BuilderImpl("JournalArticle").build();

		Assert.assertTrue(entity.equals(entity));
	}

	private Entity.EntityBuilder builder = new Entity.BuilderImpl(
		"JournalArticle");

}