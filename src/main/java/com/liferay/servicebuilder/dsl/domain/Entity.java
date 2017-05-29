package com.liferay.servicebuilder.dsl.domain;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

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
public class Entity implements ServiceBuilderElement {

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Entity)) {
			return false;
		}

		Entity that = (Entity)obj;

		return _name.equals(that._name);
	}

	public List<Column> getColumns() {
		return _columns;
	}

	public String getDatasource() {
		return _datasource;
	}

	public List<Finder> getFinders() {
		return _finders;
	}

	public String getHumanName() {
		return _humanName;
	}
	
	public String getName() {
		return _name;
	}

	public Order getOrder() {
		return _order;
	}

	public String getPersistenceClass() {
		return _persistenceClass;
	}

	public List<Reference> getReferences() {
		return _references;
	}

	public String getTable() {
		return _table;
	}

	public String getTxManager() {
		return _txManager;
	}

	public List<TxRequiredMethod> getTxRequiredMethods() {
		return _txRequiredMethodMethods;
	}

	public String getSessionFactory() {
		return _sessionFactory;
	}

	public boolean hasCacheEnabled() {
		return _cacheEnabled;
	}

	public boolean hasDynamicUpdate() {
		return _dynamicUpdateEnabled;
	}

	public boolean hasJsonSerialization() {
		return _jsonEnabled;
	}

	public boolean hasLocalService() {
		return _localService;
	}

	public boolean hasMvccEnabled() {
		return _mvccEnabled;
	}

	public boolean hasRemoteService() {
		return _remoteService;
	}

	public boolean hasTrashEnabled() {
		return _trashEnabled;
	}

	public boolean hasUuid() {
		return _uuid;
	}

	public boolean hasUuidAccessor() {
		return _uuidAccesor;
	}

	public boolean isDeprecated() {
		return _deprecated;
	}

	public static class Builder {

		/**
		 * @param name Specifies the name of the entity.
		 */
		public Builder(String name) {
			_entity = new Entity();

			_entity._name = name;
		}

		public Entity build() {
			Entity entity = _entity;

			_entity = new Entity();

			return entity;
		}

		/**
		 * The cache-enabled value specifies whether or not to cache this
		 * queries for this entity. Set this to false if data in the table will
		 * be updated by other programs. The default value is true.
		 */
		public Builder disableCache() {
			_entity._cacheEnabled = false;

			return this;
		}

		/**
		 * The deprecated value specifies whether the entity's services are
		 * deprecated.
		 */
		public Builder deprecate() {
			_entity._deprecated = true;

			return this;
		}

		public Builder withColumn(Column column) {
			if (!_entity._columns.contains(column)) {
				_entity._columns.add(column);
			}

			return this;
		}

		public Builder withColumns(Column... column) {
			Collections.addAll(_entity._columns, column);

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
		 */
		public Builder withDatasource(String datasource) {
			_entity._datasource = datasource;

			return this;
		}

		/**
		 * @param dynamicUpdate Specifies whether or not unmodified properties
		 *                      are excluded in the SQL update statement. The
		 *                      default value is the value of the attribute
		 *                      mvcc-enabled.
		 */
		public Builder withDynamicUpdate(boolean dynamicUpdate) {
			_entity._dynamicUpdateEnabled = dynamicUpdate;

			return this;
		}

		public Builder withFinder(Finder finder) {
			if (!_entity._finders.contains(finder)) {
				_entity._finders.add(finder);
			}

			return this;
		}

		public Builder withFinders(Finder... finder) {
			Collections.addAll(_entity._finders, finder);

			return this;
		}

		/**
		 * @param humanName Specifies the readable name to use when generating
		 *                  documentation for this entity. If none is specified,
		 *                  one will be generated from the name.
		 */
		public Builder withHumanName(String humanName) {
			_entity._humanName = humanName;

			return this;
		}

		/**
		 * The json-enabled value specifies whether or not the entity should be
		 * annotated for JSON serialization. By default, if the remote-service
		 * value is true, then the json-enabled value is true.
		 */
		public Builder withJsonSerialization() {
			_entity._jsonEnabled = true;

			return this;
		}

		/**
		 * If the local-service value is true, then the service will generate
		 * the local interfaces for the service. The default value is false.
		 */
		public Builder withLocalServices() {
			_entity._localService = true;

			return this;
		}

		/**
		 * @param mvccEnabled Specifies whether or not to enable MVCC for this
		 *                    entity to prevent lost updates. The default value
		 *                    is based on the mvcc-enabled attribute in the
		 *                    service-builder element.
		 */
		public Builder withMvcc(boolean mvccEnabled) {
			_entity._mvccEnabled = mvccEnabled;

			withDynamicUpdate(mvccEnabled);

			return this;
		}

		public Builder withOrder(Order order) {
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
		 */
		public Builder withPersistenceClass(String persistenceClass) {
			_entity._persistenceClass = persistenceClass;

			return this;
		}

		public Builder withReference(Reference reference) {
			if (!_entity._references.contains(reference)) {
				_entity._references.add(reference);
			}

			return this;
		}

		public Builder withReferences(Reference... reference) {
			Collections.addAll(_entity._references, reference);

			return this;
		}

		/**
		 * If the remote-service value is true, then the service will generate
		 * remote interfaces for the service. The default value is true.
		 */
		public Builder withRemoteServices() {
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
		 */
		public Builder withSessionFactory(String sessionFactory) {
			_entity._sessionFactory = sessionFactory;

			return this;
		}

		/**
		 * @param table Specifies the name of the table that this entity maps to
		 *              in the database. If this value is not set, then the name
		 *              of the table is the same as the name of the entity.
		 */
		public Builder withTable(String table) {
			_entity._table = table;

			return this;
		}

		/**
		 * The trash-enabled value specifies whether trash related methods
		 * should be generated or not.
		 */
		public Builder withTrashEnabled() {
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
		 */
		public Builder withTxManager(String txManager) {
			_entity._txManager = txManager;

			return this;
		}

		public Builder withTxRequiredMethod(TxRequiredMethod txRequiredMethod) {
			if (!_entity._txRequiredMethodMethods.contains(txRequiredMethod)) {
				_entity._txRequiredMethodMethods.add(txRequiredMethod);
			}

			return this;
		}

		public Builder withTxRequiredMethods(TxRequiredMethod... txRequiredMethod) {
			Collections.addAll(
				_entity._txRequiredMethodMethods, txRequiredMethod);

			return this;
		}

		/**
		 * If the uuid value is true, then the service will generate a UUID
		 * column for the service. This column will automatically be populated
		 * with a UUID. Developers will also be able to find and remove based on
		 * that UUID. The default value is false.
		 */
		public Builder withUuid() {
			_entity._uuid = true;

			return this;
		}

		/**
		 * If the uuid-accessor value is true, then the service will generate a
		 * UUID column accessor for the service. This accessor will provide a
		 * fast and type-safe way to access entity's UUID.
		 */
		public Builder withUuidAccessor() {
			_entity._uuidAccesor = true;

			return this;
		}

		private Entity _entity;

	}

	private Entity() {}

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
	private boolean _dynamicUpdateEnabled;
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
	private boolean _jsonEnabled;
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
	private boolean _remoteService;
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