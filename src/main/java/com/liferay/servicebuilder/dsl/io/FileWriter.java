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
import com.liferay.servicebuilder.dsl.xml.ServiceBuilderSerializer;
import com.liferay.servicebuilder.dsl.xml.XMLSerializer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import java.nio.file.Path;

/**
 * @author Manuel de la Peña
 */
public class FileWriter {

	public File createXMLFile(
			ServiceBuilder serviceBuilder, Path outputFilePath)
		throws IOException {

		XMLSerializer serializer = new ServiceBuilderSerializer(serviceBuilder);

		String xml = serializer.serialize();

		File file = outputFilePath.toFile();

		try (Writer out = new PrintWriter(file)) {
			out.write(xml);
		}

		return file;
	}

}