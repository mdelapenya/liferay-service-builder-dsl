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

		StringBuilder sb = new StringBuilder(3);

		sb.append("<reference>\n  <entity>JournalFolder</entity>\n  ");
		sb.append("<package-path>com.liferay.journal</package-path>\n");
		sb.append("</reference>");

		Assert.assertEquals(sb.toString(), xml);
	}

}