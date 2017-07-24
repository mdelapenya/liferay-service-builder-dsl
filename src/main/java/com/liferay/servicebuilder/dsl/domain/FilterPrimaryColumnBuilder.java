/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public class FilterPrimaryColumnBuilder {

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
	public FilterPrimaryColumnBuilder(String name, ServiceBuilderType type) {
		_column = new FilteredPrimaryColumn();

		_column.setName(name);
		_column.setType(type);
		_column.setFilterPrimary(true);
	}

	/**
	 * If the primary value is set to true, then this column is part of the
	 * primary key of the entity. If multiple columns have the primary value
	 * set to true, then a compound key will be created.
	 */
	public FilterPrimaryColumnBuilder asPrimaryKey() {
		_column.setPrimary(true);

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
	public FilterPrimaryColumnBuilder autogeneratePrimaryKeyFromClass(
		ServiceBuilderType columnType, String className) {

		_column.setIdParam(className);
		_column.setIdType("class");
		_column.setType(columnType);

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
	public FilterPrimaryColumnBuilder autogeneratePrimaryKeyFromIdentity(
		ServiceBuilderType columnType) {

		_column.setIdType("identity");
		_column.setType(columnType);

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
	public FilterPrimaryColumnBuilder autogeneratePrimaryKeyFromIncrement(
		ServiceBuilderType columnType) {

		_column.setIdType("increment");
		_column.setType(columnType);

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
	public FilterPrimaryColumnBuilder autogeneratePrimaryKeyFromSequence(
		ServiceBuilderType columnType, String idSequence) {

		_column.setIdParam(idSequence);
		_column.setIdType("sequence");
		_column.setType(columnType);

		return this;
	}

	public FilteredPrimaryColumn build() {
		FilteredPrimaryColumn column = _column;

		_column = new FilteredPrimaryColumn();

		return column;
	}

	/**
	 * The container-model value specifies whether the column represents the
	 * primary key of a container model.
	 */
	public FilterPrimaryColumnBuilder containerModel() {
		_column.setContainerModel(true);

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
	public FilterPrimaryColumnBuilder convertsNull() {
		_column.setConvertNull(true);

		return this;
	}

	/**
	 * The localized value specifies whether or not the value of the column
	 * can have different values for different locales. The default value is
	 * false.
	 */
	public FilterPrimaryColumnBuilder localized() {
		_column.setLocalized(true);

		return this;
	}

	/**
	 * The parent-container-model value specifies whether the column
	 * represents the primary key of a parent container model.
	 */
	public FilterPrimaryColumnBuilder parentContainerModel() {
		_column.setParentContainerModel(true);

		return this;
	}

	/**
	 * This accessor value specifies whether or not to generate an accessor
	 * for this column. This accessor will provide a fast and type-safe way
	 * to access column value.
	 */
	public FilterPrimaryColumnBuilder withAccessor() {
		_column.setAccessor(true);

		return this;
	}

	/**
	 * Set db-name to map the field to a physical database column that is
	 * different from the column name.
	 */
	public FilterPrimaryColumnBuilder withDbName(String dbName) {
		_column.setDbName(dbName);

		return this;
	}

	/**
	 * The json-enabled value specifies whether or not the column should be
	 * annotated for JSON serialization. By default, if the json-enabled
	 * value in the entity element is true, then the json-enabled value in
	 * the column element is true.
	 */
	public FilterPrimaryColumnBuilder withJsonSerialization() {
		_column.setJsonSerialization(true);

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
	public FilterPrimaryColumnBuilder withManyToManyRelationship(
		String entity, String mappingTable) {

		_column.setEntity(entity);
		_column.setMappingTable(mappingTable);

		return this;
	}

	/**
	 * The lazy value is only valid when type is Blob. It specifies whether
	 * or not to do a lazy fetch for Blob. The default value is true.
	 */
	public FilterPrimaryColumnBuilder withoutLazyFetch() {
		if ((_column.getType() == null) ||
			!_column.getType().equals(ServiceBuilderType.BLOB)) {

			return this;
		}

		_column.setLazy(false);

		return this;
	}

	private FilteredPrimaryColumn _column;

}
