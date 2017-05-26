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
public class OrderColumn {

	public OrderColumn(Builder builder) {
		_caseSensitive = builder._caseSensitive;
		_name = builder._name;
		_orderBy = builder._orderBy;
	}

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
			_name = name;
		}

		public OrderColumn build() {
			return new OrderColumn(this);
		}

		public Builder caseInsensitive() {
			_caseSensitive = false;

			return this;
		}

		public Builder descending() {
			_orderBy = OrderBy.DESC;

			return this;
		}

		private boolean _caseSensitive = true;
		private String _name;
		private OrderBy _orderBy = OrderBy.ASC;

	}
	private boolean _caseSensitive = true;
	private String _name;
	private OrderBy _orderBy = OrderBy.ASC;
}