package com.liferay.servicebuilder.dsl.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.HashSet;
import java.util.Set;

/**
 * The order element specifies a default ordering and sorting of the entities
 * when they are retrieved from the database.
 * 
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "order")
@JsonPropertyOrder(alphabetic=true)
public class Order implements ServiceBuilderElement {

	@JacksonXmlProperty(isAttribute = true, localName = "by")
	public OrderBy getBy() {
		return _by;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "order-column")
	public Set<OrderColumn> getOrderColumns() {
		return _orderColumns;
	}

	public static class Builder {

		public Builder() {
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