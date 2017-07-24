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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;

/**
 * The column element represents a column in the database.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "column")
@JsonPropertyOrder(alphabetic = true)
public class NonFilterPrimaryColumn implements Column {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NonFilterPrimaryColumn)) {
			return false;
		}

		NonFilterPrimaryColumn that = (NonFilterPrimaryColumn)obj;

		return name.equals(that.name);
	}

	@JacksonXmlProperty(isAttribute = true, localName = "db-name")
	public String getDbName() {
		return _dbName;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "entity")
	public String getEntity() {
		return _entity;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "id-param")
	public String getIdParam() {
		return _idParam;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "id-type")
	public String getIdType() {
		return _idType;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "mapping-table")
	public String getMappingTable() {
		return _mappingTable;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "name")
	public String getName() {
		return name;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "type")
	public ServiceBuilderType getType() {
		return _type;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "accessor")
	public boolean hasAccessor() {
		return _accessor;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "json-enabled")
	public boolean hasJsonSerialization() {
		return _jsonEnabled;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "container-model")
	public boolean isContainerModel() {
		return _containerModel;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "convert-null")
	public boolean isConvertNull() {
		return _convertNull;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "filter-primary")
	public boolean isFilterPrimary() {
		return false;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "lazy")
	public boolean isLazy() {
		return _lazy;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "localized")
	public boolean isLocalized() {
		return _localized;
	}

	@JacksonXmlProperty(
		isAttribute = true, localName = "parent-container-model"
	)
	public boolean isParentContainerModel() {
		return _parentContainerModel;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "primary")
	public boolean isPrimary() {
		return _primary;
	}

	@Override
	public void setAccessor(boolean accessor) {
		this._accessor = accessor;
	}

	@Override
	public void setContainerModel(boolean containerModel) {
		this._containerModel = containerModel;
	}

	@Override
	public void setConvertNull(boolean convertNull) {
		this._convertNull = convertNull;
	}

	@Override
	public void setDbName(String dbName) {
		this._dbName = dbName;
	}

	@Override
	public void setEntity(String entity) {
		this._entity = entity;
	}

	@Override
	public void setIdParam(String idParam) {
		this._idParam = idParam;
	}

	@Override
	public void setIdType(String idType) {
		this._idType = idType;
	}

	@Override
	public void setJsonSerialization(boolean jsonSerialization) {
		this._jsonEnabled = jsonSerialization;
	}

	@Override
	public void setLazy(boolean lazy) {
		this._lazy = lazy;
	}

	@Override
	public void setLocalized(boolean localized) {
		this._localized = localized;
	}

	@Override
	public void setMappingTable(String mappingTable) {
		this._mappingTable = mappingTable;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setParentContainerModel(boolean parentContainerModel) {
		this._parentContainerModel = parentContainerModel;
	}

	@Override
	public void setPrimary(boolean primary) {
		this._primary = primary;
	}

	@Override
	public void setType(ServiceBuilderType type) {
		this._type = type;
	}

	protected NonFilterPrimaryColumn() {
	}

	/**
	 * The name value specifies the getter and setter name in the entity.
	 */
	protected String name;

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
	private ServiceBuilderType _type;

}