package com.liferay.servicebuilder.dsl.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * The order element specifies a default ordering and sorting of the entities
 * when they are retrieved from the database.
 * 
 * @author Manuel de la Peña
 */
public class Order implements ServiceBuilderElement {

	public OrderBy getBy() {
		return _by;
	}

	public Set<OrderColumn> getOrderColumns() {
		return _orderColumns;
	}

	public static class Builder {

		Builder() {
			_order = new Order();
		}

		public Order build() {
			Order order = _order;

			_order = new Order();

			return order;
		}

		/**
		 * @param by Set the by attribute to "asc" or "desc" to order by
		 *           ascending or descending.
		 */
		public Builder by(OrderBy by) {
			_order._by = by;

			return this;
		}

		public Builder withOrderColumn(OrderColumn orderColumn) {
			_order._orderColumns.add(orderColumn);

			return this;
		}

		private Order _order;

	}

	private Order() {}

	/**
	 * Set the by attribute to "asc" or "desc" to order by ascending or
	 * descending.
	 */
	private OrderBy _by;
	private Set<OrderColumn> _orderColumns = new HashSet<>();

}