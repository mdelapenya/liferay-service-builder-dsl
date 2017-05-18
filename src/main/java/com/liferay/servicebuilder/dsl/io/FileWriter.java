package com.liferay.servicebuilder.dsl.io;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.nio.file.Path;

/**
 * @author Manuel de la Peña
 */
public class FileWriter {

	public void createXMLFile(Document document, Path outputFilePath)
		throws TransformerException {

		TransformerFactory transformerFactory =
			TransformerFactory.newInstance();

		Transformer transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(outputFilePath.toFile());

		transformer.transform(source, result);
	}

}