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
				.withoutSQLIndex()
				.withFinderColumns(versionFinderColumn)
				.build();

		XMLSerializer serializer = new FinderSerializer(finder);

		String xml = serializer.serialize();

		StringBuilder sb = new StringBuilder(6);

		sb.append("<finder db-index=\"false\" name=\"C_V\" ");
		sb.append("return-type=\"Collection\" unique=\"true\">\n");
		sb.append("  <finder-column case-sensitive=\"true\" ");
		sb.append("name=\"companyId\"/>\n");
		sb.append("  <finder-column case-sensitive=\"true\" ");
		sb.append("name=\"version\"/>\n</finder>");

		Assert.assertEquals(sb.toString(), xml);
	}

}