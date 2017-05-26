package com.liferay.servicebuilder.dsl.domain;

/**
 * The finder-column element specifies the columns to find by.
 * 
 * @author Manuel de la Peña
 */
public class FinderColumn {

	public FinderColumn(Builder builder) {
		_arrayableOperator = builder._arrayableOperator;
		_caseSensitive = builder._caseSensitive;
		_comparator = builder._comparator;
		_name = builder._name;
	}

	public ArrayableOperator getArrayableOperator() {
		return _arrayableOperator;
	}

	public FinderComparator getComparator() {
		return _comparator;
	}

	public String getName() {
		return _name;
	}

	public boolean isCaseSensitive() {
		return _caseSensitive;
	}

	public static class Builder {

		public Builder(String name) {
			_name = name;
		}

		public FinderColumn build() {
			return new FinderColumn(this);
		}

		public Builder caseInsensitive() {
			_caseSensitive = false;

			return this;
		}

		public Builder withArrayableOperator(ArrayableOperator operator) {
			_arrayableOperator = operator;

			return this;
		}

		public Builder withComparator(FinderComparator finderComparator) {
			_comparator = finderComparator;

			return this;
		}

		private ArrayableOperator _arrayableOperator;
		private boolean _caseSensitive = true;
		private FinderComparator _comparator;
		private String _name;

	}

	/**
	 * The attribute arrayable-operator takes in the values AND or OR and will
	 * generate an additional finder where this column's parameter takes an
	 * array instead of a single value. Every value in this array will be
	 * compared with the column using the comparator, and the conditions will be
	 * combined with either an AND or OR operator. For example, a finder column
	 * with the = comparator and an arrayable-operator of OR will act like an IN
	 * clause.
	 */
	private ArrayableOperator _arrayableOperator;
	/**
	 * The attribute case-sensitive is a boolean value and is only used if the
	 * column is a String value.
	 */
	private boolean _caseSensitive = true;
	/**
	 * The attribute comparator takes in the values =, !=, <, <=, >, >=, or LIKE
	 * and is used to compare this column.
	 */
	private FinderComparator _comparator;
	/**
	 * The name value specifies the name of the finder method.
	 *
	 * For example:
	 *
	 * <finder name="CompanyId" return-type="Collection">
	 *     <finder-column name="companyId" />
	 * </finder>
	 *
	 * The above settings will create a finder with the name findByCompanyId
	 * that will return a Collection and require a given companyId. It will also
	 * generate several more findByCompanyId methods that take in pagination
	 * fields (int begin, int end) and more sorting options. The easiest way to
	 * understand this is to look at a generated PersistenceImpl class. The
	 * Service Builder will also generate removeByCompanyId and
	 * countByCompanyId.
	 *
	 * See com.liferay.portal.service.persistence.impl.LayoutPersistenceImpl for
	 * a good example.
	 */
	private String _name;

}