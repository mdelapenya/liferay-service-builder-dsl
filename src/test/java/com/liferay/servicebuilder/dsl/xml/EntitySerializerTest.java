package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.liferay.servicebuilder.dsl.domain.Column;
import com.liferay.servicebuilder.dsl.domain.Entity;
import com.liferay.servicebuilder.dsl.domain.Finder;
import com.liferay.servicebuilder.dsl.domain.FinderColumn;
import com.liferay.servicebuilder.dsl.domain.Order;
import com.liferay.servicebuilder.dsl.domain.OrderColumn;
import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class EntitySerializerTest {

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
			new Entity.Builder("JournalArticle")
				.withDynamicUpdate(true)
				.disableCache()
				.withLocalServices()
				.withJsonSerialization()
				.disableTxManager()
				.withColumn(column)
				.withOrder(order)
				.withFinder(finder)
				.build();

		XMLSerializer serializer = new EntitySerializer(entity);

		String xml = serializer.serialize();

		Assert.assertEquals(
			"<entity cache-enabled=\"false\" deprecated=\"false\" " +
				"dynamic-update-enabled=\"true\" json-enabled=\"true\" " +
				"local-service=\"true\" mvcc-enabled=\"false\" " +
				"name=\"JournalArticle\" remote-service=\"true\" " +
				"trash-enabled=\"false\" tx-manager=\"none\" uuid=\"false\" " +
				"uuid-accessor=\"false\">\n" +
				"  <column accessor=\"false\" container-model=\"false\" " +
				"convert-null=\"false\" filter-primary=\"false\" " +
				"json-enabled=\"false\" lazy=\"true\" localized=\"false\" " +
				"name=\"companyId\" parent-container-model=\"false\" " +
				"primary=\"true\" type=\"long\"/>\n" +
				"  <finder db-index=\"true\" name=\"CompanyId\" " +
				"return-type=\"Collection\" unique=\"false\">\n" +
				"    <finder-column case-sensitive=\"true\" " +
				"name=\"companyId\"/>\n" +
				"  </finder>\n" +
				"  <order>\n" +
				"    <order-column case-sensitive=\"true\" " +
				"name=\"companyId\" order-by=\"DESC\"/>\n" +
				"  </order>\n" +
				"</entity>",
			xml);
	}

}
