package com.ullgren.pontus.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.axiom.attachments.utils.IOUtils;
import org.junit.Assert;
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
	public void testEncryptPayload() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "Hello World!";
//	    String expectedPayload = IOUtils.toString(ClassLoader.getSystemResourceAsStream("encryptedmessage.asc"));

	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("MULE_USER", "Test Testsson (This is just a test key please do not trust it.) <test.testsson@mailinator.com>");
	    MuleMessage result = client.send("vm://Test1", payload, properties);
	    
	    Assert.assertTrue("Payload does not start with expected PGP header",  result.getPayloadAsString().startsWith("-----BEGIN PGP MESSAGE-----"));
	    // Uncomment to write the encrypted message to file so that we can validate it using "gpg -d < output.txt"
//	    IOUtils.write(result.getPayloadAsString(), new FileOutputStream("output.txt"));
	}
	
//	@Test
	public void testDecryptPayload() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String expectedPayload = "Hello World!";
	    String payload = new String(IOUtils.getStreamAsByteArray(ClassLoader.getSystemResourceAsStream("encryptedmessage.asc")), "UTF-8");

	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("MULE_USER", "Test Testsson (This is just a test key please do not trust it.) <test.testsson@mailinator.com>");
	    MuleMessage result = client.send("vm://Test2", payload, properties);
	    Assert.assertEquals(expectedPayload, result.getPayloadAsString());
	}

//	@Test
	public void testThereAndBackAgain() throws Exception
	{
	    MuleClient client = new MuleClient(muleContext);
	    String payload = "Hello World!";

	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("MULE_USER", "Test Testsson (This is just a test key please do not trust it.) <test.testsson@mailinator.com>");
	    MuleMessage result = client.send("vm://Test3", payload, properties);
	    
	    Assert.assertEquals(payload, result.getPayloadAsString());
	}
}
