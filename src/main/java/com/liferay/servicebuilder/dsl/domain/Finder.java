package com.liferay.servicebuilder.dsl.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * The finder element represents a generated finder method.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "finder")
@JsonPropertyOrder(alphabetic = true)
public class Finder implements ServiceBuilderElement {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Finder)) {
			return false;
		}

		Finder that = (Finder)obj;

		return _name.equals(that._name);
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "finder-column")
	public List<FinderColumn> getFinderColumns() {
		return _finderColumns;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "name")
	public String getName() {
		return _name;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "return-type")
	public String getReturnType() {
		return _returnType;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "db-index")
	public boolean hasSQLIndex() {
		return _dbIndex;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "unique")
	public boolean isUnique() {
		return _unique;
	}

	public static class Builder {

		/**
		 * @param name Specifies the name of the finder method.
		 * @param returnType Specifies the return type of the finder. Valid
		 *                   values are "Collection" or the name of the entity.
		 *                   If the value is "Collection", then this finder
		 *                   returns a list of entities. If the value is the
		 *                   name of the entity, then this finder returns at
		 *                   most one entity.
		 */
		public Builder(String name, String returnType) {
			_finder = new Finder();

			_finder._name = name;
			_finder._returnType = returnType;
		}

		public Finder build() {
			Finder finder = _finder;

			_finder = new Finder();

			return finder;
		}

		/**
		 * If the unique value is true, then the finder must return a unique
		 * entity.
		 */
		public Builder unique() {
			_finder._unique = true;

			return this;
		}

		public Builder withFinderColumn(FinderColumn finderColumn) {
			if (!_finder._finderColumns.contains(finderColumn)) {
				_finder._finderColumns.add(finderColumn);
			}

			return this;
		}

		/**
		 * If the db-index value is true, then the service will automatically
		 * generate a SQL index for this finder. The default value is true.
		 */
		public Builder withoutSQLIndex() {
			_finder._dbIndex = false;

			return this;
		}

		private Finder _finder;

	}

	private Finder() {
	}

	/**
	 * If the db-index value is true, then the service will automatically
	 * generate a SQL index for this finder. The default value is true.
	 */
	private boolean _dbIndex = true;

	private List<FinderColumn> _finderColumns = new ArrayList<>();

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