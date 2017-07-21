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