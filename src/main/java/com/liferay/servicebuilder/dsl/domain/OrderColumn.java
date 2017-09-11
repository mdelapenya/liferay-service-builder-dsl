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
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The order-column element allows you to order the entities by specific
 * columns.
 *
 * The attributes of the order-column element allows you to fine tune the
 * ordering of the entity.
 *
 * For example:
 *
 * <order by="asc">
 *     <order-column name="parentLayoutId" />
 *     <order-column name="priority" />
 * </order>
 *
 * The above settings will order by parentLayoutId and then by priority in an
 * ascending manner.
 *
 * For example:
 *
 * <order by="asc">
 *     <order-column name="name" case-sensitive="false" />
 * </order>
 *
 * The above settings will order by name and will not be case sensitive.
 *
 * For example:
 *
 * <order>
 *     <order-column name="articleId" order-by="asc" />
 *     <order-column name="version" order-by="desc" />
 * </order>
 *
 * The above settings will order by articleId in an ascending manner and then by
 * version in a descending manner.
 *
 * @author Manuel de la Peña
 */
@JacksonXmlRootElement(localName = "order-column")
@JsonPropertyOrder(alphabetic = true)
public class OrderColumn implements ServiceBuilderElement {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OrderColumn)) {
			return false;
		}

		OrderColumn that = (OrderColumn)obj;

		return _name.equals(that._name);
	}

	@JacksonXmlProperty(isAttribute = true, localName = "name")
	public String getName() {
		return _name;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "order-by")
	public OrderBy getOrderBy() {
		return _orderBy;
	}

	@JacksonXmlProperty(isAttribute = true, localName = "case-sensitive")
	public boolean isCaseSensitive() {
		return _caseSensitive;
	}

	public static class Builder {

		public Builder(String name) {
			_orderColumn = new OrderColumn();

			_orderColumn._name = name;
		}

		public OrderColumn build() {
			OrderColumn orderColumn = _orderColumn;

			_orderColumn = new OrderColumn();

			return orderColumn;
		}

		/**
		 * Sets as case insensitive the order in this order column. If not set,
		 * the order will be performed as case sensitive.
		 * 
		 * @return the instance representing the order column builder
		 */
		public Builder caseInsensitive() {
			_orderColumn._caseSensitive = false;

			return this;
		}

		/**
		 * Default order-by value is ascending
		 * 
		 * @return the instance representing the order column builder
		 */
		public Builder descending() {
			_orderColumn._orderBy = OrderBy.DESC;

			return this;
		}

		private OrderColumn _orderColumn;

	}

	private OrderColumn() {
	}

	private boolean _caseSensitive = true;
	private String _name;
	private OrderBy _orderBy = OrderBy.ASC;

}