package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	    Map<String, Object> properties = new HashMap<String, Object>();
	    List<String> endpoints = new ArrayList<String>();
	    endpoints.add("vm://Test2");
	    endpoints.add( "vm://Test3");
	    
	    properties.put("dynamicendpoints", endpoints);
	    MuleMessage result = client.send("vm://Test1", payload, properties);
	    assertEquals(payload, result.getPayloadAsString());
	    MuleMessage response2 = client.request("vm://result2", 1000);
	    assertEquals("Flow 2", response2.getPayloadAsString());
	    MuleMessage response3 = client.request("vm://result3", 1000);
	    assertEquals("Flow 3", response3.getPayloadAsString());
	}
	
}
