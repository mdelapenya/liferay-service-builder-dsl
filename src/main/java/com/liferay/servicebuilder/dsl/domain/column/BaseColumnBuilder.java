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

package com.liferay.servicebuilder.dsl.domain.column;

import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;

/**
 * @author Manuel de la Peña
 */
public abstract class BaseColumnBuilder implements ColumnBuilder {

	/**
	 * If the primary value is set to true, then this column is part of the
	 * primary key of the entity. If multiple columns have the primary value
	 * set to true, then a compound key will be created.
	 */
	public BaseColumnBuilder asPrimaryKey() {
		column.setPrimary(true);

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
	 * @return the instance representing the column builder
	 */
	public BaseColumnBuilder autogeneratePrimaryKeyFromClass(
		ServiceBuilderType columnType, String className) {

		column.setIdParam(className);
		column.setIdType("class");
		column.setType(columnType);

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
	 * @return the instance representing the column builder
	 */
	public BaseColumnBuilder autogeneratePrimaryKeyFromIdentity(
		ServiceBuilderType columnType) {

		column.setIdParam(null);
		column.setIdType("identity");
		column.setType(columnType);

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
	 * @return the instance representing the column builder
	 */
	public BaseColumnBuilder autogeneratePrimaryKeyFromIncrement(
		ServiceBuilderType columnType) {

		column.setIdParam(null);
		column.setIdType("increment");
		column.setType(columnType);

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
	 * @return the instance representing the column builder
	 */
	public BaseColumnBuilder autogeneratePrimaryKeyFromSequence(
		ServiceBuilderType columnType, String idSequence) {

		column.setIdParam(idSequence);
		column.setIdType("sequence");
		column.setType(columnType);

		return this;
	}

	/**
	 * The container-model value specifies whether the column represents the
	 * primary key of a container model.
	 */
	public BaseColumnBuilder containerModel() {
		column.setContainerModel(true);

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
	public BaseColumnBuilder convertsNull() {
		column.setConvertNull(true);

		return this;
	}

	/**
	 * The localized value specifies whether or not the value of the column
	 * can have different values for different locales. The default value is
	 * false.
	 */
	public BaseColumnBuilder localized() {
		column.setLocalized(true);

		return this;
	}

	/**
	 * The parent-container-model value specifies whether the column
	 * represents the primary key of a parent container model.
	 */
	public BaseColumnBuilder parentContainerModel() {
		column.setParentContainerModel(true);

		return this;
	}

	/**
	 * This accessor value specifies whether or not to generate an accessor
	 * for this column. This accessor will provide a fast and type-safe way
	 * to access column value.
	 */
	public BaseColumnBuilder withAccessor() {
		column.setAccessor(true);

		return this;
	}

	/**
	 * Set db-name to map the field to a physical database column that is
	 * different from the column name.
	 */
	public BaseColumnBuilder withDbName(String dbName) {
		column.setDbName(dbName);

		return this;
	}

	/**
	 * The json-enabled value specifies whether or not the column should be
	 * annotated for JSON serialization. By default, if the json-enabled
	 * value in the entity element is true, then the json-enabled value in
	 * the column element is true.
	 */
	public BaseColumnBuilder withJsonSerialization() {
		column.setJsonSerialization(true);

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
	public BaseColumnBuilder withManyToManyRelationship(
		String entity, String mappingTable) {

		column.setEntity(entity);
		column.setMappingTable(mappingTable);

		return this;
	}

	/**
	 * The lazy value is only valid when type is Blob. It specifies whether
	 * or not to do a lazy fetch for Blob. The default value is true.
	 */
	public BaseColumnBuilder withoutLazyFetch() {
		if ((column.getType() == null) ||
			!column.getType().equals(ServiceBuilderType.BLOB)) {

			return this;
		}

		column.setLazy(false);

		return this;
	}

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
	protected BaseColumnBuilder(String name, ServiceBuilderType type) {
		column = newColumn();

		column.setName(name);
		column.setType(type);
	}

	protected abstract Column newColumn();

	protected Column column;

}