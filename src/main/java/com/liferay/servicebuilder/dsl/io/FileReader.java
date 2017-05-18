package com.liferay.servicebuilder.dsl.io;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

/**
 * @author Manuel de la Peña
 */
public class FileReader {

	public Document readXMLFile(File xmlFile)
		throws ParserConfigurationException, IOException, SAXException {

		DocumentBuilderFactory documentBuilderFactory =
			DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder =
			documentBuilderFactory.newDocumentBuilder();

		return documentBuilder.parse(xmlFile);
	}

}