package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.liferay.servicebuilder.dsl.domain.Reference;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ReferenceSerializerTest {

	@Test
	public void testSerialize() throws JsonProcessingException {
		Reference reference =
			new Reference.Builder("JournalFolder", "com.liferay.journal")
				.build();

		XMLSerializer serializer = new ReferenceSerializer(reference);

		String xml = serializer.serialize();

		Assert.assertEquals(
			"<reference>\n  <entity>JournalFolder</entity>\n  <package-path>" +
				"com.liferay.journal</package-path>\n</reference>",
			xml);
	}

}