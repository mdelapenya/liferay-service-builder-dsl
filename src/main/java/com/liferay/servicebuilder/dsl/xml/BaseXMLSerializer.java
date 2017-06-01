package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.liferay.servicebuilder.dsl.domain.ServiceBuilderElement;

/**
 * @author Manuel de la Peña
 */
public abstract class BaseXMLSerializer implements XMLSerializer {

	public BaseXMLSerializer(ServiceBuilderElement serviceBuilderElement) {
		_serviceBuilderElement = serviceBuilderElement;
	}

	@Override
	public String serialize() throws JsonProcessingException {
		ObjectMapper xmlMapper = new XmlMapper();

		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		xmlMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

		return xmlMapper.writeValueAsString(_serviceBuilderElement);
	}

	private ServiceBuilderElement _serviceBuilderElement;

}