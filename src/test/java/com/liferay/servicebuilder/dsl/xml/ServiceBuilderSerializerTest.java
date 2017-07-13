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

		Finder finder =
			new Finder.Builder("CompanyId", "Collection", finderColumn).build();

		Entity.BuilderImpl builder = new Entity.BuilderImpl("Foo");

		Entity.BuilderWithoutFilterPrimary builderWithoutFilterPrimary =
			builder;

		Entity entity =
			builderWithoutFilterPrimary
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

		StringBuilder sb = new StringBuilder(35);

		sb.append("<?xml version=\"1.0\"?>\n");
		sb.append("<!DOCTYPE service-builder ");
		sb.append("PUBLIC \"-//Liferay//DTD Service ");
		sb.append("Builder 7.0.0//EN\" \"http://www.liferay.com/dtd/liferay-");
		sb.append("service-builder_7_0_0.dtd\">\n");
		sb.append("<service-builder auto-import-references=\"false\" ");
		sb.append("auto-namespace-tables=\"false\" mvcc-enabled=\"false\" ");
		sb.append("package-path=\"\">\n");
		sb.append("  <author>Manuel de la Peña</author>\n");
		sb.append("  <entity cache-enabled=\"false\" deprecated=\"false\" ");
		sb.append("dynamic-update-enabled=\"true\" json-enabled=\"true\" ");
		sb.append("local-service=\"true\" mvcc-enabled=\"false\" ");
		sb.append("name=\"Foo\" remote-service=\"true\" ");
		sb.append("trash-enabled=\"false\" tx-manager=\"none\" ");
		sb.append("uuid=\"false\" uuid-accessor=\"false\">\n");
		sb.append("    <column accessor=\"false\" container-model=\"false\" ");
		sb.append("convert-null=\"false\" filter-primary=\"false\" ");
		sb.append("json-enabled=\"false\" lazy=\"true\" localized=\"false\" ");
		sb.append("name=\"companyId\" parent-container-model=\"false\" ");
		sb.append("primary=\"true\" type=\"long\"/>\n");
		sb.append("    <finder db-index=\"true\" name=\"CompanyId\" ");
		sb.append("return-type=\"Collection\" unique=\"false\">\n");
		sb.append("      <finder-column case-sensitive=\"true\" ");
		sb.append("name=\"companyId\"/>\n");
		sb.append("    </finder>\n");
		sb.append("    <order>\n");
		sb.append("      <order-column case-sensitive=\"true\" ");
		sb.append("name=\"companyId\" order-by=\"DESC\"/>\n");
		sb.append("    </order>\n");
		sb.append("  </entity>\n");
		sb.append("  <exceptions>\n");
		sb.append("    <exception>FooException</exception>\n");
		sb.append("  </exceptions>\n");
		sb.append("  <namespace></namespace>\n");
		sb.append("</service-builder>");

		Assert.assertEquals(sb.toString(), xml);
	}

}