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

import com.liferay.servicebuilder.dsl.domain.Finder;
import com.liferay.servicebuilder.dsl.domain.FinderColumn;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class FinderSerializerTest {

	@Test
	public void testSerialize() throws JsonProcessingException {
		FinderColumn companyIdFinderColumn = new FinderColumn.Builder(
			"companyId").build();
		FinderColumn versionFinderColumn = new FinderColumn.Builder(
			"version").build();

		Finder finder =
			new Finder.Builder("C_V", "Collection", companyIdFinderColumn)
				.unique()
				.where("status != 5")
				.withoutSQLIndex()
				.withFinderColumns(versionFinderColumn)
				.build();

		XMLSerializer serializer = new FinderSerializer(finder);

		String xml = serializer.serialize();

		StringBuilder sb = new StringBuilder(6);

		sb.append("<finder db-index=\"false\" name=\"C_V\" ");
		sb.append("return-type=\"Collection\" unique=\"true\" ");
		sb.append("where=\"status != 5\">\n");
		sb.append("  <finder-column case-sensitive=\"true\" ");
		sb.append("name=\"companyId\"/>\n");
		sb.append("  <finder-column case-sensitive=\"true\" ");
		sb.append("name=\"version\"/>\n</finder>");

		Assert.assertEquals(sb.toString(), xml);
	}

}