package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transport.NullPayload;

public class PlayWithNullPayloadTest extends  FunctionalTestCase {
	
	@Override
	protected String getConfigResources() {
		return "src/main/app/simple_mel.xml";
	}
	
	@Test
	public void testNullPayload() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	   
	    MuleMessage result = client.send("vm://PlayWithNullPayload", "Kale Kula", null);
	    assertEquals("",result.getPayload());
	}
}
