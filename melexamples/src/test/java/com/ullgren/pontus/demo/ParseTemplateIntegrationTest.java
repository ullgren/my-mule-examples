package com.ullgren.pontus.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class ParseTemplateIntegrationTest extends  FunctionalTestCase {

	ArrayList<Map<String, Object>> payload = new ArrayList<Map<String, Object>>();
	
	@Override
	protected String getConfigResources() {
		return "src/main/app/parsetemplate.xml";
	}
	
	@Test
	public void testParseTemplate() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    
	    Map<String, Object> properties = null;
	    Map<String, Object> m1 = new HashMap<String, Object>();
	    m1.put("key", "key1");
	    m1.put("value", "value1");
	    Map<String, Object> m2 = new HashMap<String, Object>();
	    m2.put("key", "key2");
	    m2.put("value", "value2");
	    payload.add(m1);
	    payload.add(m2);
	    
	    MuleMessage result = client.send("vm://ParseTemplate", payload, properties);
	    String expected = "<list>\n   <item key=\"key1\">value1</item>\n   <item key=\"key2\">value2</item>\n</list>"; 
	    assertEquals(expected, result.getPayload());
	    
	}
}
