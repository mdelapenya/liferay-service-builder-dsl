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

package com.liferay.servicebuilder.dsl.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The reference element allows you to inject services from another service.xml
 * within the same class loader. For example, if you inject the Resource entity,
 * then you'll be able to reference the Resource services from your service
 * implementation via the methods getResourceLocalService and
 * getResourceService. You'll also be able to reference the Resource services
 * via the variables resourceLocalService and resourceService.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "reference")
@JsonPropertyOrder(alphabetic = true)
public class Reference implements ServiceBuilderElement {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Reference)) {
			return false;
		}

		Reference that = (Reference)obj;

		if (_entity.equals(that._entity) &&
			_packagePath.equals(that._packagePath)) {

			return true;
		}

		return false;
	}

	@JacksonXmlProperty(localName = "entity")
	public String getEntity() {
		return _entity;
	}

	@JacksonXmlProperty(localName = "package-path")
	public String getPackagePath() {
		return _packagePath;
	}

	public static class Builder {

		/**
		 * Allows you to inject services from another service.xml within the
		 * same class loader.
		 *
		 * @param entity the name of the entity
		 * @param packagePath the package path to the service.xml
		 */
		public Builder(String entity, String packagePath) {
			_reference = new Reference();

			_reference._entity = entity;
			_reference._packagePath = packagePath;
		}

		/**
		 * Builds the Reference object from the previous composition operations.
		 *
		 * @return the Reference model
		 */
		public Reference build() {
			Reference reference = _reference;

			_reference = new Reference();

			return reference;
		}

		private Reference _reference;

	}

	private Reference() {
	}

	private String _entity;
	private String _packagePath;

}