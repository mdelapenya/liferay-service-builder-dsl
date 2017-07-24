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

package com.liferay.servicebuilder.dsl.domain.column;

import com.liferay.servicebuilder.dsl.domain.ServiceBuilderElement;
import com.liferay.servicebuilder.dsl.domain.ServiceBuilderType;

/**
 * @author Manuel de la Peña
 */
public interface Column extends ServiceBuilderElement {

	String getDbName();

	String getEntity();

	String getIdParam();

	String getIdType();

	String getMappingTable();

	String getName();

	ServiceBuilderType getType();

	boolean hasAccessor();

	boolean hasJsonSerialization();

	boolean isContainerModel();

	boolean isConvertNull();

	boolean isFilterPrimary();

	boolean isLazy();

	boolean isLocalized();

	boolean isParentContainerModel();

	boolean isPrimary();

	void setAccessor(boolean accessor);

	void setContainerModel(boolean containerModel);

	void setConvertNull(boolean convertNull);

	void setDbName(String dbName);

	void setEntity(String entity);

	void setIdParam(String idParam);

	void setIdType(String idType);

	void setJsonSerialization(boolean jsonSerialization);

	void setLazy(boolean lazy);

	void setLocalized(boolean localized);

	void setMappingTable(String mappingTable);

	void setName(String name);

	void setParentContainerModel(boolean parentContainerModel);

	void setPrimary(boolean primary);

	void setType(ServiceBuilderType type);

}