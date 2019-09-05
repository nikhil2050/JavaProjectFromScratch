package com.nikhil.nyx.common.appconfig.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.nikhil.nyx.common.appconfig.ApplicationConfig;

public class ApplicationConfigTest {
	ApplicationConfig applicationConfig = new ApplicationConfig();
	
	@Before
	public void setup() throws Exception {
		applicationConfig.setMongoHost("localhost");
		applicationConfig.setMongoPort("27017");
		/*
		 * And so on...
		 */
	}
	
	@Test
	public void testMongoHost() {
		assertNotNull(applicationConfig.getMongoHost());
	}

	@Test
	public void testFailcase() {
		assertNull(applicationConfig.getMongoHost());
	}
}
