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

package com.liferay.servicebuilder.dsl.io;

import com.liferay.servicebuilder.dsl.domain.ServiceBuilder;

import java.io.File;

import java.net.URL;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Manuel de la Peña
 */
public class FileIOTest {

	@Test
	public void shouldCreateAnXMLFile() throws Exception {
		File tempFile = new File(_temporaryFolder.getRoot(), "tempFile");

		Path path = Paths.get(tempFile.toURI());

		ServiceBuilder serviceBuilder = new ServiceBuilder.Builder(
			"com.liferay.foo", "Foo").build();

		File file = new FileWriter().createXMLFile(serviceBuilder, path);

		Assert.assertTrue(file.exists());
	}

	@Test
	public void shouldReadServiceXMLFile() throws Exception {
		URL url = getClass().getResource("/service.xml");

		File xmlFile = new File(url.toURI());

		Document document = new FileReader().readXMLFile(xmlFile);

		NodeList elementsByTagName = document.getElementsByTagName(
			"service-builder");

		Assert.assertTrue(elementsByTagName.getLength() == 1);

		elementsByTagName = document.getElementsByTagName("namespace");

		Assert.assertTrue(elementsByTagName.getLength() == 1);
		Assert.assertEquals(
			"Journal", elementsByTagName.item(0).getTextContent());

		elementsByTagName = document.getElementsByTagName("entity");

		Node item = elementsByTagName.item(0);

		NamedNodeMap attributes = item.getAttributes();

		Node namedItem = attributes.getNamedItem("name");

		Assert.assertEquals("JournalArticle", namedItem.getNodeValue());
	}

	@Rule
	public TemporaryFolder _temporaryFolder = new TemporaryFolder();

}