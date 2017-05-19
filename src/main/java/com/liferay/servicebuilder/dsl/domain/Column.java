package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public class Column {

	public Column(Builder builder) {
		_name = builder._name;
		_type = builder._type;

		_accesor = builder._accesor;
		_filterPrimary = builder._filterPrimary;
		_primary = builder._primary;
	}

	public boolean hasAccesor() {
		return _accesor;
	}

	public boolean isFilterPrimary() {
		return _filterPrimary;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public String getName() {
		return _name;
	}

	public String getType() {
		return _type;
	}

	public static class Builder {

		public Builder(String name, String type) {
			_name = name;
			_type = type;
		}

		public Column build() {
			return new Column(this);
		}

		public Builder filterPrimary() {
			_filterPrimary = true;

			return this;
		}

		public Builder primary() {
			_primary = true;

			return this;
		}

		public Builder withAccesor() {
			_accesor = true;

			return this;
		}

		private boolean _accesor;
		private boolean _filterPrimary;
		private String _name;
		private boolean _primary;
		private String _type;

	}

	private boolean _accesor;
	private boolean _filterPrimary;
	private String _name;
	private boolean _primary;
	private String _type;

}