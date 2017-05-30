package com.liferay.servicebuilder.dsl.domain;

/**
 * @author Manuel de la Peña
 */
public enum ServiceBuilderType {

	BLOB("Blob"), BOOLEAN("Boolean"), COLLECTION("Collection"), INTEGER("int"),
	LONG("long"), STRING("String");

	ServiceBuilderType(String type) {
		_type = type;
	}

	public String getType() {
		return _type;
	}

	@Override
	public String toString() {
		return _type;
	}

	private String _type;

}