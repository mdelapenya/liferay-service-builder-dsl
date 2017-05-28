package com.liferay.servicebuilder.dsl.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The finder element represents a generated finder method.
 *
 * @author Manuel de la Peña
 */
public class Finder {

	public Finder(Builder builder) {
		_dbIndex = builder._dbIndex;
		_finderColumns = builder._finderColumns;
		_name = builder._name;
		_returnType = builder._returnType;
		_unique = builder._unique;
	}

	public List<FinderColumn> getFinderColumns() {
		return _finderColumns;
	}

	public String getName() {
		return _name;
	}

	public String getReturnType() {
		return _returnType;
	}

	public boolean hasSQLIndex() {
		return _dbIndex;
	}

	public boolean isUnique() {
		return _unique;
	}

	public static class Builder {

		public Builder(String name, String returnType) {
			_name = name;
			_returnType = returnType;
		}

		public Finder build() {
			return new Finder(this);
		}

		public Builder unique() {
			_unique = true;

			return this;
		}

		public Builder withFinderColumn(FinderColumn finderColumn) {
			_finderColumns.add(finderColumn);

			return this;
		}

		public Builder withoutSQLIndex() {
			_dbIndex = false;

			return this;
		}

		private boolean _dbIndex = true;
		private List<FinderColumn> _finderColumns = new ArrayList<>();
		private String _name;
		private String _returnType;
		private boolean _unique;

	}

	/**
	 * If the db-index value is true, then the service will automatically
	 * generate a SQL index for this finder. The default value is true.
	 */
	private boolean _dbIndex = true;
	private List<FinderColumn> _finderColumns;
	/**
	 * Specifies the name of the finder method.
	 */
	private String _name;
	/**
	 * The return-type value specifies the return type of the finder. Valid
	 * values are "Collection" or the name of the entity. If the value is
	 * "Collection", then this finder returns a list of entities. If the value
	 * is the name of the entity, then this finder returns at most one entity.
	 */
	private String _returnType;
	/**
	 * If the unique value is true, then the finder must return a unique entity.
	 */
	private boolean _unique;

}