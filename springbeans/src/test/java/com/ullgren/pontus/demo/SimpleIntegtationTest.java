package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class SimpleIntegtationTest extends  FunctionalTestCase {
	
	@Override
	protected String getConfigResources() {
		return "src/main/app/mule-config.xml";
	}
	
	@Test
	public void testLogPayload() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "Hello World!";
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://Test1", payload, properties);
	    assertEquals(payload, result.getPayloadAsString());
	}
	
}
