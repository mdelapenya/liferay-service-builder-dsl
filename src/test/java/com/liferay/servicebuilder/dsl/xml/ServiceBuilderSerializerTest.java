package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.liferay.servicebuilder.dsl.domain.Column;
import com.liferay.servicebuilder.dsl.domain.Entity;
import com.liferay.servicebuilder.dsl.domain.Finder;
import com.liferay.servicebuilder.dsl.domain.FinderColumn;

import com.liferay.servicebuilder.dsl.domain.Order;
import com.liferay.servicebuilder.dsl.domain.OrderColumn;
import com.liferay.servicebuilder.dsl.domain.ServiceBuilder;
import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ServiceBuilderSerializerTest {

	@Test
	public void testSerialize() throws JsonProcessingException {
		Column column = new Column.Builder("companyId", ServiceBuilderType.LONG)
			.asPrimaryKey()
			.build();

		OrderColumn orderColumn = new OrderColumn.Builder("companyId")
			.descending()
			.build();

		Order order = new Order.Builder()
			.withOrderColumn(orderColumn)
			.build();

		FinderColumn finderColumn = new FinderColumn.Builder("companyId")
			.build();

		Finder finder = new Finder.Builder("CompanyId", "Collection")
			.withFinderColumn(finderColumn)
			.build();

		Entity entity =
			new Entity.Builder("Foo")
				.withDynamicUpdate(true)
				.disableCache()
				.withLocalServices()
				.withJsonSerialization()
				.disableTxManager()
				.withColumn(column)
				.withOrder(order)
				.withFinder(finder)
				.build();

		ServiceBuilder serviceBuilder = new ServiceBuilder.Builder("", "")
			.withEntity(entity)
			.withAuthor("Manuel de la Peña")
			.withException("FooException")
			.build();

		XMLSerializer serializer = new ServiceBuilderSerializer(serviceBuilder);

		String xml = serializer.serialize();

		Assert.assertEquals(
			"<?xml version=\"1.0\"?>" +
				"<!DOCTYPE service-builder PUBLIC \"-//Liferay//DTD Service " +
				"Builder 7.0.0//EN\" \"http://www.liferay.com/dtd/liferay-" +
				"service-builder_7_0_0.dtd\"><service-builder " +
				"auto-import-references=\"false\" " +
				"auto-namespace-tables=\"false\" mvcc-enabled=\"false\" " +
				"package-path=\"\"><author>Manuel de la Peña</author>" +
				"<entity cache-enabled=\"false\" deprecated=\"false\" " +
				"dynamic-update-enabled=\"true\" json-enabled=\"true\" " +
				"local-service=\"true\" mvcc-enabled=\"false\" name=\"Foo\" " +
				"remote-service=\"true\" trash-enabled=\"false\" " +
				"tx-manager=\"none\" uuid=\"false\" uuid-accessor=\"false\">" +
				"<column accessor=\"false\" container-model=\"false\" " +
				"convert-null=\"false\" filter-primary=\"false\" " +
				"json-enabled=\"false\" lazy=\"true\" localized=\"false\" " +
				"name=\"companyId\" parent-container-model=\"false\" " +
				"primary=\"true\" type=\"long\"/><finder db-index=\"true\" " +
				"name=\"CompanyId\" return-type=\"Collection\" " +
				"unique=\"false\"><finder-column case-sensitive=\"true\" " +
				"name=\"companyId\"/></finder><order><order-column " +
				"case-sensitive=\"true\" name=\"companyId\" " +
				"order-by=\"DESC\"/></order></entity><exceptions>" +
				"<exception>FooException</exception></exceptions>" +
				"<namespace></namespace></service-builder>",
			xml);
	}

}