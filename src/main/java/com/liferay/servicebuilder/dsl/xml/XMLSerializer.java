package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Manuel de la Peña
 */
public interface XMLSerializer {

	String serialize() throws JsonProcessingException;

}