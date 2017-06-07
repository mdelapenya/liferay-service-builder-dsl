package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.liferay.servicebuilder.dsl.domain.Column;
import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ColumnSerializerTest {

	@Test
	public void testSerialize() throws JsonProcessingException {
		Column column = new Column.Builder(
			"companyId", ServiceBuilderType.LONG) .build();

		XMLSerializer serializer = new ColumnSerializer(column);

		String xml = serializer.serialize();

		Assert.assertEquals(
			"<column accessor=\"false\" container-model=\"false\" " +
				"convert-null=\"false\" filter-primary=\"false\" " +
				"json-enabled=\"false\" lazy=\"true\" localized=\"false\" " +
				"name=\"companyId\" parent-container-model=\"false\" " +
				"primary=\"false\" type=\"long\"/>",
			xml);
	}

}