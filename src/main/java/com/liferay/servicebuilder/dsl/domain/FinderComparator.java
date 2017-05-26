package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public enum FinderComparator {

	DISTINCT("!="), EQUALS("="), GREATER("A"), GREATER_EQUALS(">="), LESS("<"),
	LESS_EQUALS("<="), LIKE("LIKE");

	FinderComparator(String comparator) {
		_comparator = comparator;
	}

	public String getComparator() {
		return _comparator;
	}

	private String _comparator;

}