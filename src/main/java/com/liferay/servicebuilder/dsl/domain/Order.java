package com.liferay.servicebuilder.dsl.domain;

/**
 * The order element specifies a default ordering and sorting of the entities
 * when they are retrieved from the database.
 * 
 * @author Manuel de la Peña
 */
public class Order {

	public Order(Builder builder) {
		_by = builder._by;
	}

	public OrderBy getBy() {
		return _by;
	}

	public static class Builder {

		public Order build() {
			return new Order(this);
		}

		public Builder by(OrderBy by) {
			_by = by;

			return this;
		}

		private OrderBy _by;

	}

	/**
	 * Set the by attribute to "asc" or "desc" to order by ascending or
	 * descending.
	 */
	private OrderBy _by;

}