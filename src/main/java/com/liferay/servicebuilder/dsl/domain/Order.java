package com.liferay.servicebuilder.dsl.domain;

import java.util.ArrayList;
import java.util.List;

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

	public List<OrderColumn> getOrderColumns() {
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
		private List<OrderColumn> _orderColumns = new ArrayList<>();

	}

	/**
	 * Set the by attribute to "asc" or "desc" to order by ascending or
	 * descending.
	 */
	private OrderBy _by;
	private List<OrderColumn> _orderColumns;

}