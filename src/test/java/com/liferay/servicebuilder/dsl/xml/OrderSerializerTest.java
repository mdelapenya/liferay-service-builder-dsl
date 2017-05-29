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
		OrderColumn versionOrderColumn =
			new OrderColumn.Builder("version")
				.build();

		Order order =
			new Order.Builder()
				.withOrderColumn(articleIdOrderColumn)
				.withOrderColumn(versionOrderColumn)
				.build();

		XMLSerializer serializer = new OrderSerializer(order);

		String xml = serializer.serialize();

		Assert.assertEquals(
			"<order><order-column case-sensitive=\"true\" name=\"articleId\" " +
				"order-by=\"DESC\"/><order-column case-sensitive=\"true\" " +
				"name=\"version\" order-by=\"ASC\"/></order>",
			xml);
	}

}