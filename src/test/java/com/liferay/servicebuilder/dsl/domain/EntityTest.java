package com.liferay.servicebuilder.dsl.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class EntityTest {

	@Test
	public void testBuild() {
		Entity entity = builder.build();

		Assert.assertEquals("JournalArticle", entity.getName());
		Assert.assertFalse(entity.isLocalService());
		Assert.assertFalse(entity.isRemoteService());
		Assert.assertFalse(entity.isTrashEnabled());
	}

	@Test
	public void testBuildWithLocalServices() {
		Entity entity = builder.withLocalServices().build();

		Assert.assertTrue(entity.isLocalService());
	}

	@Test
	public void testBuildWithRemoteServices() {
		Entity entity = builder.withRemoteServices().build();

		Assert.assertTrue(entity.isRemoteService());
	}

	@Test
	public void testBuildWithTrashEnabled() {
		Entity entity = builder.withTrashEnabled().build();

		Assert.assertTrue(entity.isTrashEnabled());
	}

	private Entity.Builder builder = new Entity.Builder("JournalArticle");

}