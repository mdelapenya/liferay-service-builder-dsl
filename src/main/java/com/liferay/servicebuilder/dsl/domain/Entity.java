package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public class Entity {

	public Entity(Builder builder) {
		_name = builder._name;

		_localService = builder._localService;
		_remoteService = builder._remoteService;
		_trashEnabled = builder._trashEnabled;
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

		private boolean _localService;
		private final String _name;
		private boolean _remoteService;
		private boolean _trashEnabled;
	}

	private boolean _localService;
	private String _name;
	private boolean _remoteService;
	private boolean _trashEnabled;

}