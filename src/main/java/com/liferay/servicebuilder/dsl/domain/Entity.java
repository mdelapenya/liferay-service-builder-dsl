package com.liferay.servicebuilder.dsl.domain;

import java.util.ArrayList;
import java.util.List;

/**
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
	private boolean _localService;
	private String _name;
	private boolean _remoteService;
	private boolean _trashEnabled;

}