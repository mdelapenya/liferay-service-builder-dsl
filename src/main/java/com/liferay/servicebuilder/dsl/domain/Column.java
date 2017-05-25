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
		_convertNull = builder._convertNull;
		_entity = builder._entity;
		_dbName = builder._dbName;
		_filterPrimary = builder._filterPrimary;
		_idParam = builder._idParam;
		_idType = builder._idType;
		_lazy = builder._lazy;
		_mappingTable = builder._mappingTable;
		_primary = builder._primary;
	}

	public boolean hasAccessor() {
		return _accessor;
	}

	public boolean isConvertNull() {
		return _convertNull;
	}

	public boolean isFilterPrimary() {
		return _filterPrimary;
	}

	public boolean isLazy() {
		return _lazy;
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

	public String getIdParam() {
		return _idParam;
	}

	public String getIdType() {
		return _idType;
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

		public Builder convertsNull() {
			_convertNull = true;

			return this;
		}

		public Builder filterPrimary() {
			_filterPrimary = true;

			return this;
		}

		public Builder withAccessor() {
			_accessor = true;

			return this;
		}

		/**
		 * Uses a class to generate a primary key. For example:
		 *
		 * <column
		 *     name="id"
		 *     type="Integer"
		 *     primary="true"
		 *     id-type="class"
		 *     id-param="com.liferay.counter.service.persistence.IDGenerator"
		 * />
		 *
		 * The class specified in the id-param value will be called to retrieve
		 * a unique identifier (in the example above, an Integer) that will be
		 * used as the primary key for the new record. This implementation works
		 * for all supported databases.
		 *
		 * @param columnType
		 * @param className the class name that retrieves a unique identifier
		 * @return
		 */
		public Builder autogeneratePrimaryKeyFromClass(
			String columnType, String className) {

			_idParam = className;
			_idType = "class";
			_type = columnType;

			return this;
		}

		/**
		 * Uses an identity column to generate a primary key.
		 *
		 * For example:
		 *
		 * <column
		 *     name="id"
		 *     type="Integer"
		 *     primary="true"
		 *     id-type="identity"
		 * />
		 *
		 * In this implementation, the create table SQL generated for this
		 * entity will create an identity column that natively auto-generates a
		 * primary key whenever an insert occurs. This implementation is only
		 * supported by DB2, MySQL, and MS SQL Server.
		 *
		 * @param columnType
		 * @return
		 */
		public Builder autogeneratePrimaryKeyFromIdentity(String columnType) {
			_idType = "identity";
			_type = columnType;

			return this;
		}

		/**
		 * Generates identifiers that are unique only when no other process is
		 * inserting data into the same table. This implementation should NOT be
		 * used in a clustered environment, but it does work for all supported
		 * databases.
		 *
		 * For example:
		 *
		 * <column
		 *     name="id"
		 *     type="Integer"
		 *     primary="true"
		 *     id-type="increment"
		 * />
		 * @param columnType
		 * @return
		 */
		public Builder autogeneratePrimaryKeyFromIncrement(String columnType) {
			_idType = "increment";
			_type = columnType;

			return this;
		}

		/**
		 * Uses a sequence to generate a primary key. For example:
		 *
		 * <column
		 *     name="id"
		 *     type="Integer"
		 *     primary="true"
		 *     id-type="sequence"
		 *     id-param="id_sequence"
		 * />
		 *
		 * In this implementation, a create sequence SQL statement is created
		 * based on the id-param value (stored in /sql/sequences.sql). This
		 * sequence is then accessed to generate a unique identifier whenever an
		 * insert occurs. This implementation is only supported by DB2, Oracle,
		 * PostgreSQL, and SAP DB.
		 *
		 * @param columnType
		 * @param idSequence
		 * @return
		 */
		public Builder autogeneratePrimaryKeyFromSequence(
			String columnType, String idSequence) {

			_idParam = idSequence;
			_idType = "sequence";
			_type = columnType;

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

		public Builder withoutLazyFetch() {
			if ((_type == null) || (_type.isEmpty()) || !_type.equals("Blob")) {
				return this;
			}

			_lazy = false;

			return this;
		}

		private boolean _accessor;
		private boolean _convertNull;
		private String _dbName;
		private String _entity;
		private boolean _filterPrimary;
		private String _idParam;
		private String _idType;
		private boolean _lazy = true;
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
	 * The convert-null value specifies whether or not the column value is
	 * automatically converted to a non null value if it is null. This only
	 * applies if the type value is String. This is particularly useful if your
	 * entity is referencing a read only table or a database view so that
	 * Hibernate does not try to issue unnecessary updates. The default value is
	 * true.
	 */
	private boolean _convertNull;
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
	 * @see _idType
	 */
	private String _idParam;
	/**
	 * The id-type and id-param values are used in order to create an
	 * auto-generated, auto-incrementing primary key when inserting records into
	 * a table. This can be implemented in 4 different ways, depending on the
	 * type of database being used.
	 * 
	 * In all cases, the primary key of the model object should be assigned a
	 * value of null, and Hibernate will know to replace the null value with an
	 * auto-generated, auto-incremented value. If no id-type value is used, it
	 * is assumed that the primary key will be assigned and not auto-generated.
	 *
	 * The first implementation uses a class to generate a primary key. For
	 * example:
	 * 
	 * <column
	 *     name="id"
	 *     type="Integer"
	 *     primary="true"
	 *     id-type="class"
	 *     id-param="com.liferay.counter.service.persistence.IDGenerator"
	 * />
	 * 
	 * In this implementation, the class specified in the id-param value will be
	 * called to retrieve a unique identifier (in the example above, an Integer)
	 * that will be used as the primary key for the new record. This
	 * implementation works for all supported databases.
	 * 
	 * The second implementation generates identifiers that are unique only when
	 * no other process is inserting data into the same table. This
	 * implementation should NOT be used in a clustered environment, but it does
	 * work for all supported databases.
	 * 
	 * For example:
	 * 
	 * <column
	 *     name="id"
	 *     type="Integer"
	 *     primary="true"
	 *     id-type="increment"
	 * />
	 * 
	 * The third implementation uses an identity column to generate a primary
	 * key.
	 * 
	 * For example:
	 * 
	 * <column
	 *     name="id"
	 *     type="Integer"
	 *     primary="true"
	 *     id-type="identity"
	 * />
	 * 
	 * In this implementation, the create table SQL generated for this entity
	 * will create an identity column that natively auto-generates a primary key
	 * whenever an insert occurs. This implementation is only supported by DB2,
	 * MySQL, and MS SQL Server.
	 * 
	 * The fourth implementation uses a sequence to generate a primary key. For
	 * example:
	 * 
	 * <column
	 *     name="id"
	 *     type="Integer"
	 *     primary="true"
	 *     id-type="sequence"
	 *     id-param="id_sequence"
	 * />
	 *
	 * In this implementation, a create sequence SQL statement is created based
	 * on the id-param value (stored in /sql/sequences.sql). This sequence is
	 * then accessed to generate a unique identifier whenever an insert occurs.
	 * This implementation is only supported by DB2, Oracle, PostgreSQL, and SAP
	 * DB.
	 */
	private String _idType;
	/**
	 * The lazy value is only valid when type is Blob. It specifies whether or
	 * not to do a lazy fetch for Blob. The default value is true.
	 */
	private boolean _lazy = true;
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