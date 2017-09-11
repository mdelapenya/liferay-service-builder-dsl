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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.servicebuilder.dsl.domain.column.Column;
import com.liferay.servicebuilder.dsl.domain.column.FilterPrimaryColumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An entity usually represents a business facade and a table in the database.
 * If an entity does not have any columns, then it only represents a business
 * facade. The Service Builder will always generate an empty business facade
 * POJO if it does not exist. Upon subsequent generations, the Service Builder
 * will check to see if the business facade already exists. If it exists and has
 * additional methods, then the Service Builder will also update the SOAP
 * wrappers.
 *
 * If an entity does have columns, then the value object, the POJO class that
 * is mapped to the database, and other persistence utilities are also generated
 * based on the order and finder elements.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "entity")
@JsonPropertyOrder(alphabetic = true)
public class Entity implements ServiceBuilderElement {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Entity)) {
			return false;
		}

		Entity that = (Entity)obj;

		return _name.equals(that._name);
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "column")
	public List<Column> getColumns() {
		return _columns;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "data-source")
	public String getDatasource() {
		return _datasource;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "finder")
	public List<Finder> getFinders() {
		return _finders;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "human-name")
	public String getHumanName() {
		if (_humanName == null) {
			return _name;
		}

		return _humanName;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "name")
	public String getName() {
		return _name;
	}

	@JacksonXmlProperty(localName = "order")
	public Order getOrder() {
		return _order;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "persistence-class")
	public String getPersistenceClass() {
		return _persistenceClass;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "reference")
	public List<Reference> getReferences() {
		return _references;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "session-factory")
	public String getSessionFactory() {
		return _sessionFactory;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "table")
	public String getTable() {
		if (_table == null) {
			return _name;
		}

		return _table;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "tx-manager")
	public String getTxManager() {
		return _txManager;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "tx-required")
	public List<TxRequiredMethod> getTxRequiredMethods() {
		return _txRequiredMethodMethods;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "cache-enabled")
	public boolean hasCacheEnabled() {
		return _cacheEnabled;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@JacksonXmlProperty(
		isAttribute = true, localName = "dynamic-update-enabled"
	)
	public Boolean hasDynamicUpdate() {
		if (_dynamicUpdateEnabled == null) {
			return hasMvccEnabled();
		}

		return _dynamicUpdateEnabled;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "json-enabled")
	public boolean hasJsonSerialization() {
		if (_jsonEnabled == null) {
			return _remoteService;
		}

		return _jsonEnabled;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "local-service")
	public boolean hasLocalService() {
		return _localService;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "mvcc-enabled")
	public boolean hasMvccEnabled() {
		return _mvccEnabled;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "remote-service")
	public boolean hasRemoteService() {
		return _remoteService;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "trash-enabled")
	public boolean hasTrashEnabled() {
		return _trashEnabled;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "uuid")
	public boolean hasUuid() {
		return _uuid;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "uuid-accessor")
	public boolean hasUuidAccessor() {
		return _uuidAccesor;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "deprecated")
	public boolean isDeprecated() {
		return _deprecated;
	}

	public static class BuilderImpl
		implements Builder, BuilderWithFilterPrimary, EntityBuilder {

		/**
		 * @param name Specifies the name of the entity.
		 */
		public BuilderImpl(String name) {
			_entity = new Entity();

			_entity._name = name;
		}

		/**
		 * Builds the Entity object from the previous composition operations.
		 * 
		 * @return the Entity model
		 */
		public Entity build() {
			Entity entity = _entity;

			_entity = new Entity();

			return entity;
		}

		/**
		 * The deprecated value specifies whether the entity's services are
		 * deprecated.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder deprecate() {
			_entity._deprecated = true;

			return this;
		}

		/**
		 * The cache-enabled value specifies whether or not to cache this
		 * queries for this entity. Set this to false if data in the table will
		 * be updated by other programs. The default value is true.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder disableCache() {
			_entity._cacheEnabled = false;

			return this;
		}

		/**
		 * Disables the transaction manager.
		 * 
		 * @see EntityBuilder#withTxManager(String)
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder disableTxManager() {
			_entity._txManager = "none";

			return this;
		}

		/**
		 * Adds a column to the list of columns in the entity.
		 * 
		 * @param column the column object to be added to the entity
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withColumn(Column column) {
			_addColumn(column);

			return this;
		}

		/**
		 * Adds an array of columns to the list of columns in the entity.
		 * 
		 * @param columns the columns array to be added to the entity
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withColumns(Column... columns) {
			Arrays.stream(columns).forEach(column -> withColumn(column));

			return this;
		}

		/**
		 * You can generate classes to use a custom data source and session
		 * factory. Point "spring.configs" in portal.properties to load your
		 * custom Spring XML with the definitions of your custom data source and
		 * session factory.
		 *
		 * Then set the data-source and session-factory values to your custom
		 * values.
		 *
		 * @param datasource Specifies the data source target that is set to the
		 *                   persistence class. The default value is the Liferay
		 *                   data source. This is used in conjunction with
		 *                   session-factory. See data-source-spring.xml.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withDatasource(String datasource) {
			_entity._datasource = datasource;

			return this;
		}

		/**
		 * @param dynamicUpdate Specifies whether or not unmodified properties
		 *                      are excluded in the SQL update statement. The
		 *                      default value is the value of the attribute
		 *                      mvcc-enabled.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withDynamicUpdate(Boolean dynamicUpdate) {
			_entity._dynamicUpdateEnabled = dynamicUpdate;

			return this;
		}

		/**
		 * Adds the only one filter-primary column to the entity.
		 * 
		 * @param column the filter-primary column to be added to the entity
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withFilterPrimaryColumn(
			FilterPrimaryColumn column) {

			_addColumn(column);

			return this;
		}

		/**
		 * Adds a finder method to the list of finder methods in the entity.
		 *
		 * @param finder the finder method to be added to the entity
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withFinder(Finder finder) {
			if (!_entity._finders.contains(finder)) {
				_entity._finders.add(finder);
			}
			else {
				if (_logger.isWarnEnabled()) {
					_logger.warn(
						"Not adding " + finder.getName() +
							" finder because it already exists");
				}
			}

			return this;
		}

		/**
		 * Adds an array of finder methods to the list of finder methods in the
		 * entity.
		 * 
		 * @param finders the array of finder methods to be added to the entity
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withFinders(Finder... finders) {
			Arrays.stream(finders).forEach(finder -> withFinder(finder));

			return this;
		}

		/**
		 * @param humanName Specifies the readable name to use when generating
		 *                  documentation for this entity. If none is specified,
		 *                  one will be generated from the name.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withHumanName(String humanName) {
			_entity._humanName = humanName;

			return this;
		}

		/**
		 * The json-enabled value specifies whether or not the entity should be
		 * annotated for JSON serialization. By default, if the remote-service
		 * value is true, then the json-enabled value is true.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withJsonSerialization() {
			_entity._jsonEnabled = true;

			return this;
		}

		/**
		 * If the local-service value is true, then the service will generate
		 * the local interfaces for the service. The default value is false.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withLocalServices() {
			_entity._localService = true;

			return this;
		}

		/**
		 * @param mvccEnabled Specifies whether or not to enable MVCC for this
		 *                    entity to prevent lost updates. The default value
		 *                    is based on the mvcc-enabled attribute in the
		 *                    service-builder element.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withMvcc(boolean mvccEnabled) {
			_entity._mvccEnabled = mvccEnabled;

			if (_entity._dynamicUpdateEnabled == null) {
				withDynamicUpdate(mvccEnabled);
			}

			return this;
		}

		/**
		 * Specifies the order for the retrieval of the entity.
		 * 
		 * @param order the order to be applied when the entity is retrieved
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withOrder(Order order) {
			_entity._order = order;

			return this;
		}

		/**
		 * @param persistenceClass Specifies the name of your custom persistence
		 *                         class. This class must implement the
		 *                         generated persistence interface or extend the
		 *                         generated persistence class. This allows you
		 *                         to override default behavior without
		 *                         modifying the generated persistence class.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withPersistenceClass(String persistenceClass) {
			_entity._persistenceClass = persistenceClass;

			return this;
		}

		/**
		 * Adds a reference to the list of references in the entity.
		 * 
		 * @param reference the reference to be added to the entity
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withReference(Reference reference) {
			if (!_entity._references.contains(reference)) {
				_entity._references.add(reference);
			}

			return this;
		}

		/**
		 * Adds an array of references to the list of references in the entity.
		 *
		 * @param references the array of references to be added to the entity
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withReferences(Reference... references) {
			Arrays.stream(references).forEach(
				reference -> withReference(reference));

			return this;
		}

		/**
		 * If the remote-service value is true, then the service will generate
		 * remote interfaces for the service. The default value is true.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withRemoteServices() {
			_entity._remoteService = true;

			withJsonSerialization();

			return this;
		}

		/**
		 * @param sessionFactory Specifies the session factory that is set to
		 *                       the persistence class. The default value is the
		 *                       Liferay session factory. This is used in
		 *                       conjunction with data-source. See
		 *                       data-source-spring.xml.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withSessionFactory(String sessionFactory) {
			_entity._sessionFactory = sessionFactory;

			return this;
		}

		/**
		 * @param table Specifies the name of the table that this entity maps to
		 *              in the database. If this value is not set, then the name
		 *              of the table is the same as the name of the entity.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withTable(String table) {
			_entity._table = table;

			return this;
		}

		/**
		 * The trash-enabled value specifies whether trash related methods
		 * should be generated or not.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withTrashEnabled() {
			_entity._trashEnabled = true;

			return this;
		}

		/**
		 * @param txManager Specifies the transaction manager that Spring uses.
		 *                  The default value is the Spring Hibernate
		 *                  transaction manager that wraps the Liferay data
		 *                  source and session factory. See
		 *                  data-source-spring.xml. Set this attribute to "none"
		 *                  to disable transaction management.
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withTxManager(String txManager) {
			_entity._txManager = txManager;

			return this;
		}

		/**
		 * Adds a method that requires transactions to the list of methods that
		 * in require transactions in the entity.
		 * 
		 * @param txRequiredMethod the method that requires transactions 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withTxRequiredMethod(
			TxRequiredMethod txRequiredMethod) {

			_addTxRequiredMethod(txRequiredMethod);

			return this;
		}

		/**
		 * Adds an array of method that requires transactions to the list of
		 * methods that in require transactions in the entity.
		 *
		 * @param txRequiredMethods the array of methods that requires
		 *                          transactions 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withTxRequiredMethods(
			TxRequiredMethod... txRequiredMethods) {

			Arrays.stream(txRequiredMethods)
				.forEach(
					txRequiredMethod -> withTxRequiredMethod(txRequiredMethod));

			return this;
		}

		/**
		 * If the uuid value is true, then the service will generate a UUID
		 * column for the service. This column will automatically be populated
		 * with a UUID. Developers will also be able to find and remove based on
		 * that UUID. The default value is false.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withUuid() {
			_entity._uuid = true;

			return this;
		}

		/**
		 * If the uuid-accessor value is true, then the service will generate a
		 * UUID column accessor for the service. This accessor will provide a
		 * fast and type-safe way to access entity's UUID.
		 * 
		 * @return the instance representing the entity builder
		 */
		public EntityBuilder withUuidAccessor() {
			_entity._uuidAccesor = true;

			return this;
		}

		private void _addColumn(Column column) {
			if (!_entity._columns.contains(column)) {
				_entity._columns.add(column);
			}
			else {
				if (_logger.isWarnEnabled()) {
					_logger.warn(
						"Not adding " + column.getName() +
							" column because it already exists");
				}
			}
		}

		private void _addTxRequiredMethod(TxRequiredMethod txRequiredMethod) {
			if (!_entity._txRequiredMethodMethods.contains(txRequiredMethod)) {
				_entity._txRequiredMethodMethods.add(txRequiredMethod);
			}
			else {
				if (_logger.isWarnEnabled()) {
					_logger.warn(
						"Not adding " + txRequiredMethod.getMethodName() +
							" txRequiredMethod because it already exists");
				}
			}
		}

		private Entity _entity;

	}

	public interface Builder {

		Entity build();

	}

	public interface BuilderWithFilterPrimary extends Builder {

		EntityBuilder deprecate();

		EntityBuilder disableCache();

		EntityBuilder disableTxManager();

		EntityBuilder withColumn(Column column);

		EntityBuilder withColumns(Column... columns);

		EntityBuilder withDatasource(String datasource);

		EntityBuilder withDynamicUpdate(Boolean dynamicUpdate);

		EntityBuilder withFinder(Finder finder);

		EntityBuilder withFinders(Finder... finders);

		EntityBuilder withHumanName(String humanName);

		EntityBuilder withJsonSerialization();

		EntityBuilder withLocalServices();

		EntityBuilder withMvcc(boolean mvccEnabled);

		EntityBuilder withOrder(Order order);

		EntityBuilder withPersistenceClass(String persistenceClass);

		EntityBuilder withReference(Reference reference);

		EntityBuilder withReferences(Reference... reference);

		EntityBuilder withRemoteServices();

		EntityBuilder withSessionFactory(String sessionFactory);

		EntityBuilder withTable(String table);

		EntityBuilder withTrashEnabled();

		EntityBuilder withTxManager(String txManager);

		EntityBuilder withTxRequiredMethod(TxRequiredMethod txRequiredMethod);

		EntityBuilder withTxRequiredMethods(
			TxRequiredMethod... txRequiredMethod);

		EntityBuilder withUuid();

		EntityBuilder withUuidAccessor();

	}

	public interface EntityBuilder extends BuilderWithFilterPrimary {

		BuilderWithFilterPrimary withFilterPrimaryColumn(
			FilterPrimaryColumn column);

	}

	private Entity() {
	}

	private static final Logger _logger = LogManager.getLogger(Entity.class);

	/**
	 * The cache-enabled value specifies whether or not to cache this queries
	 * for this entity. Set this to false if data in the table will be updated
	 * by other programs. The default value is true.
	 */
	private boolean _cacheEnabled = true;

	private List<Column> _columns = new ArrayList<>();

	/**
	 * You can generate classes to use a custom data source and session factory.
	 * Point "spring.configs" in portal.properties to load your custom Spring
	 * XML with the definitions of your custom data source and session factory.
	 * Then set the data-source and session-factory values to your custom
	 * values.
	 *
	 * The data-source value specifies the data source target that is set to the
	 * persistence class. The default value is the Liferay data source. This is
	 * used in conjunction with session-factory. See data-source-spring.xml.
	 */
	private String _datasource;

	/**
	 * The deprecated value specifies whether the entity's services are
	 * deprecated.
	 */
	private boolean _deprecated;

	/**
	 * The dynamic-update-enabled value specifies whether or not unmodified
	 * properties are excluded in the SQL update statement. The default value is
	 * the value of the attribute mvcc-enabled.
	 */
	private Boolean _dynamicUpdateEnabled;

	private List<Finder> _finders = new ArrayList<>();

	/**
	 * The human-name value specifies the readable name to use when generating
	 * documentation for this entity. If none is specified, one will be
	 * generated from the name.
	 */
	private String _humanName;

	/**
	 * The json-enabled value specifies whether or not the entity should be
	 * annotated for JSON serialization. By default, if the remote-service value
	 * is true, then the json-enabled value is true.
	 */
	private Boolean _jsonEnabled;

	/**
	 * If the local-service value is true, then the service will generate the
	 * local interfaces for the service. The default value is false.
	 */
	private boolean _localService;

	/**
	 * The mvcc-enabled value specifies whether or not to enable MVCC for this
	 * entity to prevent lost updates. The default value is based on the
	 * mvcc-enabled attribute in the service-builder element.
	 */
	private boolean _mvccEnabled;

	/**
	 * The name value specifies the name of the entity.
	 */
	private String _name;

	private Order _order;

	/**
	 * The persistence-class value specifies the name of your custom persistence
	 * class. This class must implement the generated persistence interface or
	 * extend the generated persistence class. This allows you to override
	 * default behavior without modifying the generated persistence class.
	 */
	private String _persistenceClass;

	private List<Reference> _references = new ArrayList<>();

	/**
	 * If the remote-service value is true, then the service will generate
	 * remote interfaces for the service. The default value is true.
	 */
	private boolean _remoteService = true;

	/**
	 * The session-factory value specifies the session factory that is set to
	 * the persistence class. The default value is the Liferay session factory.
	 * This is used in conjunction with data-source. See data-source-spring.xml.
	 */
	private String _sessionFactory;

	/**
	 * The table value specifies the name of the table that this entity maps to
	 * in the database. If this value is not set, then the name of the table is
	 * the same as the name of the entity.
	 */
	private String _table;

	/**
	 * The trash-enabled value specifies whether trash related methods should be
	 * generated or not.
	 */
	private boolean _trashEnabled;

	/**
	 * The tx-manager value specifies the transaction manager that Spring uses.
	 * The default value is the Spring Hibernate transaction manager that wraps
	 * the Liferay data source and session factory. See data-source-spring.xml.
	 * Set this attribute to "none" to disable transaction management.
	 */
	private String _txManager;

	private List<TxRequiredMethod> _txRequiredMethodMethods = new ArrayList<>();

	/**
	 * If the uuid value is true, then the service will generate a UUID column
	 * for the service. This column will automatically be populated with a UUID.
	 * Developers will also be able to find and remove based on that UUID. The
	 * default value is false.
	 */
	private boolean _uuid;

	/**
	 * If the uuid-accessor value is true, then the service will generate a
	 * UUID column accessor for the service. This accessor will provide a fast
	 * and type-safe way to access entity's UUID.
	 */
	private boolean _uuidAccesor;

}