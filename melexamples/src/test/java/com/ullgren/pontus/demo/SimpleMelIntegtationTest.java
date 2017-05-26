package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transport.NullPayload;

public class SimpleMelIntegtationTest extends  FunctionalTestCase {
	
	@Override
	protected String getConfigResources() {
		return "src/main/app/simple_mel.xml";
	}
	
	@Test
	public void testLogPayload() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "Hello World!";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    String expectedToday = sdf.format(new Date());
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://LogPayload", payload, properties);
	    assertEquals(payload, result.getPayloadAsString());
	    assertEquals(expectedToday, result.getProperty("today", PropertyScope.SESSION));
	}
	
	@Test
	public void testLogPayload2() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "Hello Mars!";
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://LogPayload", payload, properties);
	    // We expect NullPayload since the message was filtered
	    assertEquals(NullPayload.class, result.getPayload().getClass());
	    assertNull(result.getProperty("today", PropertyScope.SESSION));
	}
	
	@Test
	public void testExtractDataXpathFunction() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "<order id='123'><customer/><lines><line /></lines></order>";
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://ExtractDataXpathFunction", payload, properties);
	    assertEquals("id=\"123\"", result.getPayloadAsString());
	}
	
	@Test
	public void testExtractDataXpathEvaluator() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "<order id='123'><customer/><lines><line /></lines></order>";
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://ExtractDataXpathEvaluator", payload, properties);
	    assertEquals("123", result.getPayloadAsString());
	}
	
	@Test
	public void testXpathVsXPath() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "<order id='123'><customer/><lines><line /></lines></order>";
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://XpathVsXPath", payload, properties);
	    assertEquals(payload, result.getPayloadAsString());
	    
	    payload = "<order id='456'><customer/><lines><line /></lines></order>";
	    result = client.send("vm://LogPayload", payload, properties);
	    // We expect NullPayload since the message was filtered
	    assertEquals(NullPayload.class, result.getPayload().getClass());
	    assertNull(result.getProperty("today", PropertyScope.SESSION));
	    
	}
	
	@Test
	public void testSetInboundAsPayloadWithOutHeader() throws Exception 
	{
		MuleClient client = new MuleClient(muleContext);
	    String payload = null;
	    Map<String, Object> properties = null;
	    MuleMessage result = client.send("vm://SetInboundAsPayload", payload, properties);
	    assertEquals("Please set the in header", result.getPayloadAsString());
	}
	
	@Test
	public void testSetInboundAsPayloadWithHeader() throws Exception 
	{
		MuleClient client = new MuleClient(muleContext);
	    String payload = null;
	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("in", "The header value");
	    MuleMessage result = client.send("vm://SetInboundAsPayload", payload, properties);
	    assertEquals("The header value", result.getPayloadAsString());
	}
	
	@Test
	public void testSetInboundAsPayloadWithEmptyHeader() throws Exception 
	{
		MuleClient client = new MuleClient(muleContext);
	    String payload = null;
	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("in", "");
	    MuleMessage result = client.send("vm://SetInboundAsPayload", payload, properties);
	    assertEquals("Please set the in header", result.getPayloadAsString());
	}
	
	@Test
	public void testMergeTwoProperties() throws Exception 
	{
		MuleClient client = new MuleClient(muleContext);
	    String payload = null;
	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("source1Var", new ArrayList<String>() {{
	        add("1");
	        add("2");
	        add("3");
	    }});
	    properties.put("source2Var", new ArrayList<String>() {{
	        add("2");
	        add("3");
	        add("4");
	    }});
	    MuleMessage result = client.send("vm://MergeTwoProperties", payload, properties);
	    assertEquals("[3, 2, 1, 4]", result.getInboundProperty("resultVar").toString());
	}
}
