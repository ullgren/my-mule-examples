package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class GlobalFunctionIntegtationTest extends  FunctionalTestCase {

	byte[] payload = { 0x7F, 0x0, 0x0, 0x1 };
	
	@Override
	protected String getConfigResources() {
		return "src/main/app/global_functions.xml";
	}
	
	@Test
	public void testFilterBytesAsHexWithoutGlobalFunction() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://FilterBytesAsHexWithoutGlobalFunction", payload, properties);
	    assertEquals(payload, result.getPayloadAsBytes());
	}
	
	@Test
	public void testFilterBytesAsHexWithGlobalFunction() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://FilterBytesAsHexWithGlobalFunction", payload, properties);
	    assertEquals(payload, result.getPayloadAsBytes());
	}
}
