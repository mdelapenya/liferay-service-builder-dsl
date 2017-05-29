package com.liferay.servicebuilder.dsl.domain;

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
public class OrderColumn implements ServiceBuilderElement {

	public String getName() {
		return _name;
	}

	public boolean isCaseSensitive() {
		return _caseSensitive;
	}

	public OrderBy getOrderBy() {
		return _orderBy;
	}

	public static class Builder {

		public Builder (String name) {
			_orderColumn = new OrderColumn();

			_orderColumn._name = name;
		}

		public OrderColumn build() {
			OrderColumn orderColumn = _orderColumn;

			_orderColumn = new OrderColumn();

			return orderColumn;
		}

		public Builder caseInsensitive() {
			_orderColumn._caseSensitive = false;

			return this;
		}

		/**
		 * Default order-by value is ascending
		 */
		public Builder descending() {
			_orderColumn._orderBy = OrderBy.DESC;

			return this;
		}

		private OrderColumn _orderColumn;

	}

	private OrderColumn() {}

	private boolean _caseSensitive = true;
	private String _name;
	private OrderBy _orderBy = OrderBy.ASC;
}