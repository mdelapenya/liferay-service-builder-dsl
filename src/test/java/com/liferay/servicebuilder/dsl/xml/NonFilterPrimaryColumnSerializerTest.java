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
import com.liferay.servicebuilder.dsl.domain.ColumnBuilderFactory;
import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class NonFilterPrimaryColumnSerializerTest {

	@Test
	public void testSerialize() throws JsonProcessingException {
		Column column = ColumnBuilderFactory.getColumnBuilder(
			"companyId", ServiceBuilderType.LONG).build();

		XMLSerializer serializer = new ColumnSerializer(column);

		String xml = serializer.serialize();

		StringBuilder sb = new StringBuilder(5);

		sb.append("<column accessor=\"false\" container-model=\"false\" ");
		sb.append("convert-null=\"false\" filter-primary=\"false\" ");
		sb.append("json-enabled=\"false\" lazy=\"true\" localized=\"false\" ");
		sb.append("name=\"companyId\" parent-container-model=\"false\" ");
		sb.append("primary=\"false\" type=\"long\"/>");

		Assert.assertEquals(sb.toString(), xml);
	}

}