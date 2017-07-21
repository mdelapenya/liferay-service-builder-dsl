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

import com.liferay.servicebuilder.dsl.domain.Order;
import com.liferay.servicebuilder.dsl.domain.OrderColumn;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class OrderSerializerTest {

	@Test
	public void testSerialize() throws JsonProcessingException {
		OrderColumn articleIdOrderColumn =
			new OrderColumn.Builder("articleId")
				.descending()
				.build();
		OrderColumn versionOrderColumn = new OrderColumn.Builder(
			"version") .build();

		Order order =
			new Order.Builder()
				.withOrderColumn(articleIdOrderColumn)
				.withOrderColumn(versionOrderColumn)
				.build();

		XMLSerializer serializer = new OrderSerializer(order);

		String xml = serializer.serialize();

		StringBuilder sb = new StringBuilder(5);

		sb.append("<order>\n");
		sb.append("  <order-column case-sensitive=\"true\" ");
		sb.append("name=\"articleId\" order-by=\"DESC\"/>\n");
		sb.append("  <order-column case-sensitive=\"true\" ");
		sb.append("name=\"version\" order-by=\"ASC\"/>\n</order>");

		Assert.assertEquals(sb.toString(), xml);
	}

}