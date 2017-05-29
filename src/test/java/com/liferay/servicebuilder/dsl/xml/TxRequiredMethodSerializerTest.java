package com.liferay.servicebuilder.dsl.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.liferay.servicebuilder.dsl.domain.TxRequiredMethod;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class TxRequiredMethodSerializerTest {

	@Test
	public void testSerialize() throws JsonProcessingException {
		TxRequiredMethod txRequiredMethod = new TxRequiredMethod.Builder(
			"getFoo").build();

		XMLSerializer serializer = new TxRequiredMethodSerializer(
			txRequiredMethod);

		String xml = serializer.serialize();

		Assert.assertEquals("<tx-required>getFoo</tx-required>", xml);
	}

}