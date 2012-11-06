SOAP2SOAP
=========

In this test integration we have a internal web-service that exposes a not so user friendly interface.
See src/test/resources/innerservice.wsdl

We now  want to expose a new public interface.

The basic steps for the integration is
 1. Inbound enpoint that exposes the public interface and gives us the payload (soap body)
 2. XSLT transformation of body payload
 3. Proxy client to send the transformed body to the inner service
 4. XSLT transformation of the response payload

In this project I recreate the same excersise but using Mule ESB CE 3.3.0 
(http://www.mulesoft.org/download-mule-esb-community-edition)

Things I found in this project that I wish Mule had out-of-the-box.

 * "Set payload" component that can handle the value as a multi-line CDATA instead of a attribute.

Thanks to ryan carter for helping me with this one.
http://forum.mulesoft.org/mulesoft/topics/is_there_a_soap_client_that_does_not_require_a_service_class

