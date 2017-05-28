package com.liferay.servicebuilder.dsl.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
public class Entity {

	public Entity(Builder builder) {
		_name = builder._name;

		_cacheEnabled = builder._cacheEnabled;
		_columns = builder._columns;
		_datasource = builder._datasource;
		_deprecated = builder._deprecated;
		_dynamicUpdateEnabled = builder._dynamicUpdateEnabled;
		_finders = builder._finders;
		_humanName = builder._humanName;
		_jsonEnabled = builder._jsonEnabled;
		_localService = builder._localService;
		_mvccEnabled = builder._mvccEnabled;
		_orders = builder._orders;
		_persistenceClass = builder._persistenceClass;
		_remoteService = builder._remoteService;
		_sessionFactory = builder._sessionFactory;
		_table = builder._table;
		_trashEnabled = builder._trashEnabled;
		_txManager = builder._txManager;
		_uuid = builder._uuid;
		_uuidAccesor = builder._uuidAccesor;
	}

	public Set<Column> getColumns() {
		return _columns;
	}

	public String getDatasource() {
		return _datasource;
	}

	public Set<Finder> getFinders() {
		return _finders;
	}

	public String getHumanName() {
		return _humanName;
	}
	
	public String getName() {
		return _name;
	}

	public Set<Order> getOrders() {
		return _orders;
	}

	public String getPersistenceClass() {
		return _persistenceClass;
	}

	public String getTable() {
		return _table;
	}

	public String getTxManager() {
		return _txManager;
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

		public Builder(String name) {
			_name = name;
		}

		public Entity build() {
			return new Entity(this);
		}

		public Builder disableCache() {
			_cacheEnabled = false;

			return this;
		}

		public Builder deprecate() {
			_deprecated = true;

			return this;
		}

		public Builder withColumn(Column column) {
			_columns.add(column);

			return this;
		}

		public Builder withColumns(Column... column) {
			Collections.addAll(_columns, column);

			return this;
		}

		public Builder withDatasource(String datasource) {
			_datasource = datasource;

			return this;
		}

		public Builder withDynamicUpdate(boolean dynamicUpdate) {
			_dynamicUpdateEnabled = dynamicUpdate;

			return this;
		}

		public Builder withFinder(Finder finder) {
			_finders.add(finder);

			return this;
		}

		public Builder withFinders(Finder... finder) {
			Collections.addAll(_finders, finder);

			return this;
		}

		public Builder withHumanName(String humanName) {
			_humanName = humanName;

			return this;
		}

		public Builder withJsonSerialization() {
			_jsonEnabled = true;

			return this;
		}

		public Builder withLocalServices() {
			_localService = true;

			return this;
		}

		public Builder withMvcc(boolean mvccEnabled) {
			_mvccEnabled = mvccEnabled;

			withDynamicUpdate(mvccEnabled);

			return this;
		}

		public Builder withOrder(Order order) {
			_orders.add(order);

			return this;
		}

		public Builder withOrders(Order... order) {
			Collections.addAll(_orders, order);

			return this;
		}

		public Builder withPersistenceClass(String persistenceClass) {
			_persistenceClass = persistenceClass;

			return this;
		}

		public Builder withRemoteServices() {
			_remoteService = true;

			withJsonSerialization();

			return this;
		}

		public Builder withSessionFactory(String sessionFactory) {
			_sessionFactory = sessionFactory;

			return this;
		}

		public Builder withTable(String table) {
			_table = table;

			return this;
		}

		public Builder withTrashEnabled() {
			_trashEnabled = true;

			return this;
		}

		public Builder withTxManager(String txManager) {
			_txManager = txManager;

			return this;
		}

		public Builder withUuid() {
			_uuid = true;

			return this;
		}

		public Builder withUuidAccessor() {
			_uuidAccesor = true;

			return this;
		}

		private boolean _cacheEnabled = true;
		private Set<Column> _columns = new HashSet<>();
		private String _datasource;
		private boolean _deprecated;
		private boolean _dynamicUpdateEnabled;
		private Set<Finder> _finders = new HashSet<>();
		private String _humanName;
		private boolean _jsonEnabled;
		private boolean _localService;
		private boolean _mvccEnabled;
		private final String _name;
		private Set<Order> _orders = new HashSet<>();
		private String _persistenceClass;
		private boolean _remoteService;
		private String _sessionFactory;
		private String _table;
		private boolean _trashEnabled;
		private String _txManager;
		private boolean _uuid;
		private boolean _uuidAccesor;
	}

	/**
	 * The cache-enabled value specifies whether or not to cache this queries
	 * for this entity. Set this to false if data in the table will be updated
	 * by other programs. The default value is true.
	 */
	private boolean _cacheEnabled = true;
	private Set<Column> _columns;
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
	private Set<Finder> _finders;
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
	private Set<Order> _orders;
	/**
	 * The persistence-class value specifies the name of your custom persistence
	 * class. This class must implement the generated persistence interface or
	 * extend the generated persistence class. This allows you to override
	 * default behavior without modifying the generated persistence class.
	 */
	private String _persistenceClass;
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