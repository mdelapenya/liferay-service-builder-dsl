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