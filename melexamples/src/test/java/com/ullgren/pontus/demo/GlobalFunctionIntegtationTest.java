package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class GlobalFunctionIntegtationTest extends  FunctionalTestCase {

	byte[] payload = { 'D', 'e', 't', 't', 'a', ' ', (byte)0xE4, 'r', ' ', 'e', 'n', ' ', 'm', (byte)0xE4, 
            'n', 'g', 'd', ' ', 't', 'e', 'c', 'k', 'e', 'n', ' ', 'i', ' ', 'I', 
            'S', 'O', ' ', 'e', 'n', 'c', 'o', 'd', 'i', 'n', 'g', '.', ' ', (byte)0xF6, 
            (byte)0xE4, (byte)0xE5, (byte)0xC5, (byte)0xC4, (byte)0xD6, '$', (byte)0xA3
    };
	// Swedish sentence followed by a bunch of non-lating chars.
	// Translated to english: "This is a bunch of chars in ISO encoding. öäåÅÄÖ$£"
	String expectedResult = "Detta är en mängd tecken i ISO encoding. öäåÅÄÖ$£";
	
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
