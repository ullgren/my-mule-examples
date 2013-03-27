package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class GlobalFunctionIntegtationTest extends  FunctionalTestCase {

	byte[] payload = { 0x7F, 0x0, 0x0, 0x1 };
	String expectedResult = "Success";
	
	@Override
	protected String getConfigResources() {
		// TODO Auto-generated method stub
		return "src/main/app/global_functions.xml";
	}
	
	@Test
	public void testSend1() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://start1", payload, properties);
	    assertEquals(expectedResult, result.getPayloadAsString());
	}
	
	@Test
	public void testSend2() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://start2", payload, properties);
	    assertEquals(expectedResult, result.getPayloadAsString());
	}
}
