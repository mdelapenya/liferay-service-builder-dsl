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

import com.liferay.servicebuilder.dsl.domain.ServiceBuilder;

/**
 * @author Manuel de la Peña
 */
public class ServiceBuilderSerializer extends BaseXMLSerializer {

	public ServiceBuilderSerializer(ServiceBuilder serviceBuilder) {
		super(serviceBuilder);
	}

	@Override
	public String serialize() throws JsonProcessingException {
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\"?>\n");
		sb.append("<!DOCTYPE service-builder ");
		sb.append("PUBLIC \"-//Liferay//DTD Service Builder ");
		sb.append(MAJOR_VERSION);
		sb.append(".");
		sb.append(MINOR_VERSION);
		sb.append(".");
		sb.append(REVISION_VERSION);
		sb.append("//EN\" ");
		sb.append("\"http://www.liferay.com/dtd/liferay-service-builder_");
		sb.append(MAJOR_VERSION);
		sb.append("_");
		sb.append(MINOR_VERSION);
		sb.append("_");
		sb.append(REVISION_VERSION);
		sb.append(".dtd\">\n");

		sb.append(super.serialize());

		return sb.toString();
	}

	private static final int MAJOR_VERSION = 7;

	private static final int MINOR_VERSION = 0;

	private static final int REVISION_VERSION = 0;

}