package com.liferay.servicebuilder.dsl.domain;

/**
 * The column element represents a column in the database.
 *
 * @author Manuel de la Peña
 */
public class Column {

	public Column(Builder builder) {
		_name = builder._name;
		_type = builder._type;

		_accessor = builder._accessor;
		_entity = builder._entity;
		_dbName = builder._dbName;
		_filterPrimary = builder._filterPrimary;
		_mappingTable = builder._mappingTable;
		_primary = builder._primary;
	}

	public boolean hasAccessor() {
		return _accessor;
	}

	public boolean isFilterPrimary() {
		return _filterPrimary;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public String getDbName() {
		return _dbName;
	}

	public String getEntity() {
		return _entity;
	}

	public String getMappingTable() {
		return _mappingTable;
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

		public Builder asPrimaryKey() {
			_primary = true;

			return this;
		}

		public Column build() {
			return new Column(this);
		}

		public Builder filterPrimary() {
			_filterPrimary = true;

			return this;
		}

		public Builder withAccessor() {
			_accessor = true;

			return this;
		}

		public Builder withDbName(String dbName) {
			_dbName = dbName;

			return this;
		}

		public Builder withManyToManyRelationship(
			String entity, String mappingTable) {

			_entity = entity;
			_mappingTable = mappingTable;

			return this;
		}

		private boolean _accessor;
		private String _dbName;
		private String _entity;
		private boolean _filterPrimary;
		private String _mappingTable;
		private String _name;
		private boolean _primary;
		private String _type;

	}

	/**
	 * This accessor value specifies whether or not to generate an accessor for
	 * this column. This accessor will provide a fast and type-safe way to
	 * access column value.
	 */
	private boolean _accessor;
	/**
	 * Set db-name to map the field to a physical database column that is
	 * different from the column name.
	 */
	private String _dbName;
	/**
	 * @see _mappingTable
	 */
	private String _entity;
	/**
	 * The filter-primary value specifies the column to use as the primary key
	 * column when using filter finders. Only one column should ever have this
	 * value set to true. If no column has this set to true, then the default
	 * primary column be used.
	 */
	private boolean _filterPrimary;
	/**
	 * If the entity and mapping-table attributes are specified, then the
	 * Service Builder will assume you are specifying a many to many
	 * relationship.
	 * 
	 * For example:
	 * 
	 * <column
	 *    name="roles"
	 *    type="Collection"
	 *    entity="Role"
	 *    mapping-table="Groups_Roles"
	 * />
	 *
	 * The above column specifies that there will be a getter called
	 * pojo.getRoles() that will return a collection. It will use a mapping
	 * table called Groups_Roles to give a many to many relationship between
	 * groups and roles.
	 *
	 * If you are creating a mapping table for an entity defined in another
	 * service.xml, you need to specify the full package path.
	 *
	 * For example:
	 *
	 * <column
	 *     name="organizations"
	 *     type="Collection"
	 *     entity="com.liferay.portal.Organization"
	 *     mapping-table="Foo_Organizations"
	 * />
	 */
	private String _mappingTable;
	/**
	 * The name value specifies the getter and setter name in the entity.
	 */
	private String _name;
	/**
	 * If the primary value is set to true, then this column is part of the
	 * primary key of the entity. If multiple columns have the primary value set
	 * to true, then a compound key will be created.
	 */
	private boolean _primary;
	/**
	 * The type value specifies whether the column is a String, Boolean, or int,
	 * etc.
	 *
	 * For example:
	 *
	 * <column name="companyId" db-name="companyId" type="String" />
	 *
	 * The above column specifies that there will be a getter called
	 * pojo.getCompanyId() that will return a String.
	 */
	private String _type;

}