package com.liferay.servicebuilder.dsl.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The service-builder element is the root of the deployment descriptor for
 * a Service Builder descriptor that is used to generate services available to
 * portlets. The Service Builder saves the developer time by generating Spring
 * utilities, SOAP utilities, and Hibernate persistence classes to ease the
 * development of services.
 *
 * @author Manuel de la Peña
 */
public class ServiceBuilder {

	public ServiceBuilder(Builder builder) {
		_packagePath = builder._packagePath;
		_namespace = builder._namespace;

		_autoImportDefaultReferences =
			builder._autoImportDefaultReferences;
		_autoNamespaceTables = builder._autoNamespaceTables;
		_entities = builder._entities;
		_exceptions = builder._exceptions;
		_mvccEnabled = builder._mvccEnabled;
	}

	public List<Entity> getEntities() {
		return _entities;
	}

	public List<String> getExceptions() {
		return _exceptions;
	}

	public String getNamespace() {
		return _namespace;
	}

	public String getPackagePath() {
		return _packagePath;
	}

	public boolean hasAutoImportDefaultReferences() {
		return _autoImportDefaultReferences;
	}

	public boolean hasAutoNamespaceTables() {
		return _autoNamespaceTables;
	}

	public boolean isMvccEnabled() {
		return _mvccEnabled;
	}

	public static class Builder {

		public Builder(String packagePath, String namespace) {
			_packagePath = packagePath;
			_namespace = namespace;
		}

		public Builder autoImportDefaultReferences() {
			_autoImportDefaultReferences = true;

			return this;
		}

		public Builder autoNamespaceTables() {
			_autoNamespaceTables = true;

			return this;
		}

		public Builder enableMvcc() {
			_mvccEnabled = true;

			return this;
		}

		public ServiceBuilder build() {
			return new ServiceBuilder(this);
		}

		public Builder withEntity(Entity entity) {
			_entities.add(entity);

			return this;
		}

		public Builder withException(String exception) {
			_exceptions.add(exception);

			return this;
		}

		private boolean _autoImportDefaultReferences;
		private boolean _autoNamespaceTables;
		private List<Entity> _entities = new ArrayList<>();
		private List<String> _exceptions = new ArrayList<>();
		private boolean _mvccEnabled;
		private final String _namespace;
		private final String _packagePath;

	}

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
	private List<Entity> _entities;
	private List<String> _exceptions;
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

}