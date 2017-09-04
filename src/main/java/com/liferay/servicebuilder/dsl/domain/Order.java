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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The order element specifies a default ordering and sorting of the entities
 * when they are retrieved from the database.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "order")
@JsonPropertyOrder(alphabetic = true)
public class Order implements ServiceBuilderElement {

	@JacksonXmlProperty(isAttribute = true, localName = "by")
	public OrderBy getBy() {
		return _by;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "order-column")
	public List<OrderColumn> getOrderColumns() {
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
			if (!_order._orderColumns.contains(orderColumn)) {
				_order._orderColumns.add(orderColumn);
			}
			else {
				if (_logger.isWarnEnabled()) {
					_logger.warn(
						"Not adding " + orderColumn.getName() +
							" order-column because it already exists");
				}
			}

			return this;
		}

		public Builder withOrderColumns(OrderColumn... orderColumns) {
			Arrays.stream(orderColumns)
				.forEach(orderColumn -> withOrderColumn(orderColumn));

			return this;
		}

		private Order _order;

	}

	private Order() {
	}

	private static final Logger _logger = LogManager.getLogger(Order.class);

	/**
	 * Set the by attribute to "asc" or "desc" to order by ascending or
	 * descending.
	 */
	private OrderBy _by;

	private List<OrderColumn> _orderColumns = new ArrayList<>();

}