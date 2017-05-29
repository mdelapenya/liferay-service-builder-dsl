package com.liferay.servicebuilder.dsl.domain;

/**
 * The finder-column element specifies the columns to find by.
 * 
 * @author Manuel de la Peña
 */
public class FinderColumn implements ServiceBuilderElement {

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

		/**
		 * @param name Specifies the name of the finder method.
		 *
		 * For example:
		 *
		 * <finder name="CompanyId" return-type="Collection">
		 *     <finder-column name="companyId" />
		 * </finder>
		 *
		 * The above settings will create a finder with the name findByCompanyId
		 * that will return a Collection and require a given companyId. It will
		 * also generate several more findByCompanyId methods that take in
		 * pagination fields (int begin, int end) and more sorting options. The
		 * easiest way to understand this is to look at a generated
		 * PersistenceImpl class. The Service Builder will also generate
		 * removeByCompanyId and countByCompanyId.
		 *
		 * See com.liferay.portal.service.persistence.impl.LayoutPersistenceImpl
		 * for a good example.
		 */
		public Builder(String name) {
			_finderColumn = new FinderColumn();

			_finderColumn._name = name;
		}

		public FinderColumn build() {
			FinderColumn finderColumn = _finderColumn;

			_finderColumn = new FinderColumn();

			return finderColumn;
		}

		/**
		 * The attribute case-sensitive is a boolean value and is only used if
		 * the column is a String value.
		 */
		public Builder caseInsensitive() {
			_finderColumn._caseSensitive = false;

			return this;
		}

		/**
		 * @param operator Takes in the values AND or OR and will generate an
		 *                 additional finder where this column's parameter takes
		 *                 an array instead of a single value. Every value in
		 *                 this array will be compared with the column using the
		 *                 comparator, and the conditions will be combined with
		 *                 either an AND or OR operator. For example, a finder
		 *                 column with the = comparator and an
		 *                 arrayable-operator of OR will act like an IN clause.
		 */
		public Builder withArrayableOperator(ArrayableOperator operator) {
			_finderColumn._arrayableOperator = operator;

			return this;
		}

		/**
		 * @param finderComparator Takes in the values =, !=, <, <=, >, >=, or
		 *                         LIKE and is used to compare this column.
		 */
		public Builder withComparator(FinderComparator finderComparator) {
			_finderColumn._comparator = finderComparator;

			return this;
		}

		private FinderColumn _finderColumn;

	}

	private FinderColumn() {}

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