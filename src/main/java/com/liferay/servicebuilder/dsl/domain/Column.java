package com.liferay.servicebuilder.dsl.domain;

/**
 * The column element represents a column in the database.
 *
 * @author Manuel de la Peña
 */
public class Column {

	public boolean hasAccessor() {
		return _accessor;
	}

	public boolean hasJsonSerialization() {
		return _jsonEnabled;
	}

	public boolean isContainerModel() {
		return _containerModel;
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

	public boolean isLocalized() {
		return _localized;
	}

	public boolean isParentContainerModel() {
		return _parentContainerModel;
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

		/**
		 * @param name Specifies the getter and setter name in the entity.
		 * @param type Specifies whether the column is a String, Boolean, or
		 *             int, etc.
		 *             For example:
		 *             <column name="companyId" db-name="companyId"
		 *                     type="String" />
		 *             The above column specifies that there will be a getter
		 *             called pojo.getCompanyId() that will return a String.
		 */
		Builder(String name, String type) {
			_column = new Column();

			_column._name = name;
			_column._type = type;
		}

		/**
		 * If the primary value is set to true, then this column is part of the
		 * primary key of the entity. If multiple columns have the primary value
		 * set to true, then a compound key will be created.
		 */
		public Builder asPrimaryKey() {
			_column._primary = true;

			return this;
		}

		public Column build() {
			Column column = _column; 

			_column = new Column();

			return column;
		}

		/**
		 * The container-model value specifies whether the column represents the
		 * primary key of a container model.
		 */
		public Builder containerModel() {
			_column._containerModel = true;

			return this;
		}

		/**
		 * The convert-null value specifies whether or not the column value is
		 * automatically converted to a non null value if it is null. This only
		 * applies if the type value is String. This is particularly useful if
		 * your entity is referencing a read only table or a database view so
		 * that Hibernate does not try to issue unnecessary updates. The default
		 * value is true.
		 */
		public Builder convertsNull() {
			_column._convertNull = true;

			return this;
		}

		/**
		 * The filter-primary value specifies the column to use as the primary
		 * key column when using filter finders. Only one column should ever
		 * have this value set to true. If no column has this set to true, then
		 * the default primary column be used.
		 */
		public Builder filterPrimary() {
			_column._filterPrimary = true;

			return this;
		}

		/**
		 * This accessor value specifies whether or not to generate an accessor
		 * for this column. This accessor will provide a fast and type-safe way
		 * to access column value.
		 */
		public Builder withAccessor() {
			_column._accessor = true;

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

			_column._idParam = className;
			_column._idType = "class";
			_column._type = columnType;

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
			_column._idType = "identity";
			_column._type = columnType;

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
			_column._idType = "increment";
			_column._type = columnType;

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

			_column._idParam = idSequence;
			_column._idType = "sequence";
			_column._type = columnType;

			return this;
		}

		/**
		 * The localized value specifies whether or not the value of the column
		 * can have different values for different locales. The default value is
		 * false.
		 */
		public Builder localized() {
			_column._localized = true;

			return this;
		}

		/**
		 * The parent-container-model value specifies whether the column
		 * represents the primary key of a parent container model.
		 */
		public Builder parentContainerModel() {
			_column._parentContainerModel = true;

			return this;
		}

		/**
		 * Set db-name to map the field to a physical database column that is
		 * different from the column name.
		 */
		public Builder withDbName(String dbName) {
			_column._dbName = dbName;

			return this;
		}

		/**
		 * The json-enabled value specifies whether or not the column should be
		 * annotated for JSON serialization. By default, if the json-enabled
		 * value in the entity element is true, then the json-enabled value in
		 * the column element is true.
		 */
		public Builder withJsonSerialization() {
			_column._jsonEnabled = true;

			return this;
		}

		/**
		 * The Service Builder will assume you are specifying a many to many
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
		 *
		 * @param entity
		 * @param mappingTable
		 */
		public Builder withManyToManyRelationship(
			String entity, String mappingTable) {

			_column._entity = entity;
			_column._mappingTable = mappingTable;

			return this;
		}

		/**
		 * The lazy value is only valid when type is Blob. It specifies whether
		 * or not to do a lazy fetch for Blob. The default value is true.
		 */
		public Builder withoutLazyFetch() {
			if ((_column._type == null) || (_column._type.isEmpty()) ||
				!_column._type.equals("Blob")) {

				return this;
			}

			_column._lazy = false;

			return this;
		}

		private Column _column;

	}

	private Column() {}

	/**
	 * This accessor value specifies whether or not to generate an accessor for
	 * this column. This accessor will provide a fast and type-safe way to
	 * access column value.
	 */
	private boolean _accessor;
	/**
	 * The container-model value specifies whether the column represents the
	 * primary key of a container model.
	 */
	private boolean _containerModel;
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
	 * The json-enabled value specifies whether or not the column should be
	 * annotated for JSON serialization. By default, if the json-enabled value
	 * in the entity element is true, then the json-enabled value in the column
	 * element is true.
	 */
	private boolean _jsonEnabled;
	/**
	 * The lazy value is only valid when type is Blob. It specifies whether or
	 * not to do a lazy fetch for Blob. The default value is true.
	 */
	private boolean _lazy = true;
	/**
	 * The localized value specifies whether or not the value of the column can
	 * have different values for different locales. The default value is false.
	 */
	private boolean _localized;
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
	 * The parent-container-model value specifies whether the column represents
	 * the primary key of a parent container model.
	 */
	private boolean _parentContainerModel;
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