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
		FinderColumn companyIdFinderColumn =
			new FinderColumn.Builder("companyId").build();
		FinderColumn versionFinderColumn =
			new FinderColumn.Builder("version").build();

		Finder finder =
			new Finder.Builder("C_V", "Collection")
				.unique()
				.withoutSQLIndex()
				.withFinderColumn(companyIdFinderColumn)
				.withFinderColumn(versionFinderColumn)
				.build();

		XMLSerializer serializer = new FinderSerializer(finder);

		String xml = serializer.serialize();

		Assert.assertEquals(
			"<finder db-index=\"false\" name=\"C_V\" " +
				"return-type=\"Collection\" unique=\"true\">" +
				"<finder-column case-sensitive=\"true\" " +
				"name=\"companyId\"/><finder-column case-sensitive=\"true\" " +
				"name=\"version\"/></finder>",
			xml);
	}

}