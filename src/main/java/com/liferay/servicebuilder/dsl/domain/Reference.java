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
		if(!(obj instanceof Reference)) {
			return false;
		}

		Reference that = (Reference)obj;

		return (_entity.equals(that._entity) &&
			(_packagePath.equals(that._packagePath)));
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
		 * @return
		 */
		public Builder(String entity, String packagePath) {
			_reference = new Reference();

			_reference._entity = entity;
			_reference._packagePath = packagePath;
		}

		public Reference build() {
			Reference reference = _reference;

			_reference = new Reference();

			return reference;
		}

		private Reference _reference;

	}

	private Reference() {}

	private String _entity;
	private String _packagePath;

}