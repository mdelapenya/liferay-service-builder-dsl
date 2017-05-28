package com.liferay.servicebuilder.dsl.domain;

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
public class Reference {

	public String getEntity() {
		return _entity;
	}

	public String getPackagePath() {
		return _packagePath;
	}

	public static class Builder {

		Builder() {
			_reference = new Reference();
		}

		public Reference build() {
			Reference reference = _reference;

			_reference = new Reference();

			return reference;
		}

		public Builder inject(String entity, String packagePath) {
			_reference._entity = entity;
			_reference._packagePath = packagePath;

			return this;
		}

		private Reference _reference;

	}

	private Reference() {}

	private String _entity;
	private String _packagePath;

}