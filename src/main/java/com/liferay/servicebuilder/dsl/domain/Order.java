package com.liferay.servicebuilder.dsl.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * The order element specifies a default ordering and sorting of the entities
 * when they are retrieved from the database.
 * 
 * @author Manuel de la Peña
 */
public class Order {

	public Order(Builder builder) {
		_by = builder._by;
		_orderColumns = builder._orderColumns;
	}

	public OrderBy getBy() {
		return _by;
	}

	public Set<OrderColumn> getOrderColumns() {
		return _orderColumns;
	}

	public static class Builder {

		public Order build() {
			return new Order(this);
		}

		public Builder by(OrderBy by) {
			_by = by;

			return this;
		}

		public Builder withOrderColumn(OrderColumn orderColumn) {
			_orderColumns.add(orderColumn);

			return this;
		}

		private OrderBy _by;
		private Set<OrderColumn> _orderColumns = new HashSet<>();

	}

	/**
	 * Set the by attribute to "asc" or "desc" to order by ascending or
	 * descending.
	 */
	private OrderBy _by;
	private Set<OrderColumn> _orderColumns;

}