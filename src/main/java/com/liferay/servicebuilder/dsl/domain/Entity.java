package com.liferay.servicebuilder.dsl.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity usually represents a business facade and a table in the database.
 * If an entity does not have any columns, then it only represents a business
 * facade. The Service Builder will always generate an empty business facade
 * POJO if it does not exist. Upon subsequent generations, the Service Builder
 * will check to see if the business facade already exists. If it exists and has
 * additional methods, then the Service Builder will also update the SOAP
 * wrappers.
 *
 * If an entity does have columns, then the value object, the POJO class that
 * is mapped to the database, and other persistence utilities are also generated
 * based on the order and finder elements.
 *
 * @author Manuel de la Peña
 */
public class Entity {

	public Entity(Builder builder) {
		_name = builder._name;

		_columns = builder._columns;
		_localService = builder._localService;
		_remoteService = builder._remoteService;
		_trashEnabled = builder._trashEnabled;
	}

	public List<Column> getColumns() {
		return _columns;
	}

	public boolean hasLocalService() {
		return _localService;
	}

	public boolean hasRemoteService() {
		return _remoteService;
	}

	public boolean hasTrashEnabled() {
		return _trashEnabled;
	}

	public String getName() {
		return _name;
	}

	public static class Builder {

		public Builder(String name) {
			_name = name;
		}

		public Entity build() {
			return new Entity(this);
		}

		public Builder withColumn(Column column) {
			_columns.add(column);

			return this;
		}

		public Builder withLocalServices() {
			_localService = true;

			return this;
		}

		public Builder withRemoteServices() {
			_remoteService = true;

			return this;
		}

		public Builder withTrashEnabled() {
			_trashEnabled = true;

			return this;
		}

		private List<Column> _columns = new ArrayList<>();
		private boolean _localService;
		private final String _name;
		private boolean _remoteService;
		private boolean _trashEnabled;
	}

	private List<Column> _columns;
	/**
	 * If the local-service value is true, then the service will generate the
	 * local interfaces for the service. The default value is false.
	 */
	private boolean _localService;
	/**
	 * The name value specifies the name of the entity.
	 */
	private String _name;
	/**
	 * If the remote-service value is true, then the service will generate
	 * remote interfaces for the service. The default value is true.
	 */
	private boolean _remoteService;
	/**
	 * The trash-enabled value specifies whether trash related methods should be
	 * generated or not.
	 */
	private boolean _trashEnabled;

}