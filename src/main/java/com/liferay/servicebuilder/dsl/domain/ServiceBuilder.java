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
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The service-builder element is the root of the deployment descriptor for
 * a Service Builder descriptor that is used to generate services available to
 * portlets. The Service Builder saves the developer time by generating Spring
 * utilities, SOAP utilities, and Hibernate persistence classes to ease the
 * development of services.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "service-builder")
@JsonPropertyOrder(alphabetic = true)
public class ServiceBuilder implements ServiceBuilderElement {

	@JacksonXmlProperty(localName = "author")
	public String getAuthor() {
		return _author;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "entity")
	public List<Entity> getEntities() {
		return _entities;
	}

	@JacksonXmlElementWrapper(localName = "exceptions")
	@JacksonXmlProperty(localName = "exception")
	public List<String> getExceptions() {
		return _exceptions;
	}

	@JacksonXmlProperty(localName = "namespace")
	public String getNamespace() {
		return _namespace;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "package-path")
	public String getPackagePath() {
		return _packagePath;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "service-builder-import")
	public List<String> getServiceBuilderImports() {
		return _serviceBuilderImports;
	}

	@JacksonXmlProperty(
		isAttribute = true, localName = "auto-import-references"
	)
	public boolean hasAutoImportDefaultReferences() {
		return _autoImportDefaultReferences;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "auto-namespace-tables")
	public boolean hasAutoNamespaceTables() {
		return _autoNamespaceTables;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "mvcc-enabled")
	public boolean isMvccEnabled() {
		return _mvccEnabled;
	}

	public static class Builder {

		/**
		 * @param packagePath The package-path value specifies the package of
		 *                    the generated code.
		 * @param namespace The namespace element must be a unique namespace for
		 *                  this component. Table names will be prepended with
		 *                  this namespace. Generated JSON JavaScript will be
		 *                  scoped to this namespace as well (i.e.,
		 *                  Liferay.Service.Test.* if the namespace is Test).
		 */
		public Builder(String packagePath, String namespace) {
			_serviceBuilder = new ServiceBuilder();

			_serviceBuilder._packagePath = packagePath;
			_serviceBuilder._namespace = namespace;
		}

		/**
		 * The auto-import-default-references value specifies whether or not to
		 * automatically default references. The default value is true.
		 */
		public Builder autoImportDefaultReferences() {
			_serviceBuilder._autoImportDefaultReferences = true;

			return this;
		}

		/**
		 * The auto-namespace-tables value specifies whether or not to
		 * automatically namespace tables. The default value is false for core
		 * services and true for plugin services.
		 */
		public Builder autoNamespaceTables() {
			_serviceBuilder._autoNamespaceTables = true;

			return this;
		}

		public ServiceBuilder build() {
			ServiceBuilder serviceBuilder = _serviceBuilder;

			_serviceBuilder = new ServiceBuilder();

			return serviceBuilder;
		}

		/**
		 * The mvcc-enabled value specifies whether or not to enable MVCC by
		 * default for entities to prevent lost updates. The default value is
		 * false.
		 */
		public Builder enableMvcc() {
			_serviceBuilder._mvccEnabled = true;

			return this;
		}

		/**
		 * The service-builder-import allows you to split up a large Service
		 * Builder file into smaller files by aggregrating the smaller Service
		 * Builder into one file.
		 *
		 * Note that there can be at most one author element among all the
		 * files. There can also only be one and only one namespace element
		 * among all the files.
		 *
		 * @param file The file is interpreted as relative to the file that is
		 *             importing it.
		 * @return the ServiceBuilder Builder object
		 */
		public Builder importServiceBuilderFile(String file) {
			if (!_serviceBuilder._serviceBuilderImports.contains(file)) {
				_serviceBuilder._serviceBuilderImports.add(file);
			}
			else {
				if (_logger.isWarnEnabled()) {
					_logger.warn(
						"Not adding " + file +
							" Service Builder file because it already exists");
				}
			}

			return this;
		}

		/**
		 * The author element is the name of the user associated with the
		 * generated code.
		 */
		public Builder withAuthor(String author) {
			_serviceBuilder._author = author;

			return this;
		}

		public Builder withEntity(Entity entity) {
			if (!_serviceBuilder._entities.contains(entity)) {
				_serviceBuilder._entities.add(entity);
			}
			else {
				if (_logger.isWarnEnabled()) {
					_logger.warn(
						"Not adding " + entity.getName() +
							" entity because it already exists");
				}
			}

			return this;
		}

		public Builder withException(String exception) {
			if (!_serviceBuilder._exceptions.contains(exception)) {
				_serviceBuilder._exceptions.add(exception);
			}
			else {
				if (_logger.isWarnEnabled()) {
					_logger.warn(
						"Not adding " + exception +
							" exception because it already exists");
				}
			}

			return this;
		}

		private ServiceBuilder _serviceBuilder;

	}

	private ServiceBuilder() {
	}

	/**
	 * The author element is the name of the user associated with the generated
	 * code.
	 */
	private String _author;

	/**
	 * The auto-import-default-references value specifies whether or not to
	 * automatically default references. The default value is true.
	 */
	private boolean _autoImportDefaultReferences;

	/**
	 * The auto-namespace-tables value specifies whether or not to automatically
	 * namespace tables. The default value is false for core services and true
	 * for plugin services.
	 */
	private boolean _autoNamespaceTables;

	private List<Entity> _entities = new ArrayList<>();
	private List<String> _exceptions = new ArrayList<>();

	/**
	 * The mvcc-enabled value specifies whether or not to enable MVCC by default
	 * for entities to prevent lost updates. The default value is false.
	 */
	private boolean _mvccEnabled;

	/**
	 * The namespace element must be a unique namespace for this component.
	 * Table names will be prepended with this namespace. Generated JSON
	 * JavaScript will be scoped to this namespace as well
	 * (i.e., Liferay.Service.Test.* if the namespace is Test).
	 */
	private String _namespace;

	/**
	 * The package-path value specifies the package of the generated code.
	 */
	private String _packagePath;

	/**
	 * The service-builder-import allows you to split up a large Service Builder
	 * file into smaller files by aggregrating the smaller Service Builder into
	 * one file.
	 *
	 * Note that there can be at most one author element among all the files.
	 * There can also only be one and only one namespace element among all the
	 * files.
	 *
	 * The attribute file is interpreted as relative to the file that is
	 * importing it.
	 */
	private List<String> _serviceBuilderImports = new ArrayList<>();

	private static final Logger _logger = LogManager.getLogger(
		ServiceBuilder.class);

}