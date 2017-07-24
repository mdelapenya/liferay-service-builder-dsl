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

package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.liferay.servicebuilder.dsl.domain.Column;
import com.liferay.servicebuilder.dsl.domain.ColumnBuilder;
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
		Column column = new ColumnBuilder("companyId", ServiceBuilderType.LONG)
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

		Entity entity =
			new Entity.BuilderImpl("JournalArticle")
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

		StringBuilder sb = new StringBuilder(21);

		sb.append("<entity cache-enabled=\"false\" deprecated=\"false\" ");
		sb.append("dynamic-update-enabled=\"true\" json-enabled=\"true\" ");
		sb.append("local-service=\"true\" mvcc-enabled=\"false\" ");
		sb.append("name=\"JournalArticle\" remote-service=\"true\" ");
		sb.append("trash-enabled=\"false\" tx-manager=\"none\" ");
		sb.append("uuid=\"false\" uuid-accessor=\"false\">\n");
		sb.append("  <column accessor=\"false\" container-model=\"false\" ");
		sb.append("convert-null=\"false\" filter-primary=\"false\" ");
		sb.append("json-enabled=\"false\" lazy=\"true\" localized=\"false\" ");
		sb.append("name=\"companyId\" parent-container-model=\"false\" ");
		sb.append("primary=\"true\" type=\"long\"/>\n");
		sb.append("  <finder db-index=\"true\" name=\"CompanyId\" ");
		sb.append("return-type=\"Collection\" unique=\"false\">\n");
		sb.append("    <finder-column case-sensitive=\"true\" ");
		sb.append("name=\"companyId\"/>\n");
		sb.append("  </finder>\n");
		sb.append("  <order>\n");
		sb.append("    <order-column case-sensitive=\"true\" ");
		sb.append("name=\"companyId\" order-by=\"DESC\"/>\n");
		sb.append("  </order>\n");
		sb.append("</entity>");

		Assert.assertEquals(sb.toString(), xml);
	}

}