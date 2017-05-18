package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public class ServiceBuilder {

	public ServiceBuilder(Builder builder) {
		_packagePath = builder._packagePath;
		_namespace = builder._namespace;

		_autoImportDefaultReferences =
			builder._autoImportDefaultReferences;
		_autoNamespaceTables = builder._autoNamespaceTables;
	}

	public boolean isAutoImportDefaultReferences() {
		return _autoImportDefaultReferences;
	}

	public boolean isAutoNamespaceTables() {
		return _autoNamespaceTables;
	}

	public String getNamespace() {
		return _namespace;
	}

	public String getPackagePath() {
		return _packagePath;
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

		public ServiceBuilder build() {
			return new ServiceBuilder(this);
		}

		private boolean _autoImportDefaultReferences;
		private boolean _autoNamespaceTables;
		private final String _namespace;
		private final String _packagePath;

	}

	private boolean _autoImportDefaultReferences;
	private boolean _autoNamespaceTables;
	private String _namespace;
	private String _packagePath;

}