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