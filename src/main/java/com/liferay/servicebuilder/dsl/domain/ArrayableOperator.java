package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public enum ArrayableOperator {

	AND("AND"), OR("OR");

	ArrayableOperator(String operator) {
		_operator = operator;
	}

	public String getOperator() {
		return _operator;
	}

	private String _operator;

}