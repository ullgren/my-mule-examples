SOAP2SOAP
=========

A common integration scenario is that a company/organisation has a old internal web-service that they want to expose as a public interface.
Often these internal services have a less than ideal interface (See src/test/resources/innerservice.wsdl) which is often auto-generated from some business logic so you do not want to simply expose the existing contract. Instead we want to have a interface more suited for use by external entities. However it is not always practical or possible to rewrite the internal service.

The solution is to build a SOAP proxy with transformation.

The basic steps for the integration is
 1. Inbound enpoint that exposes the public API and gives us the payload (soap body)
 2. XSLT transformation of body payload to comply with the internal API
 3. Proxy client to send the transformed body to the inner service
 4. XSLT transformation of the response payload to comply with the public API.

In this project I recreate the same excersise but using Mule ESB CE 3.3.0 
(http://www.mulesoft.org/download-mule-esb-community-edition)

Thanks to ryan carter for helping me with this one.
http://forum.mulesoft.org/mulesoft/topics/is_there_a_soap_client_that_does_not_require_a_service_class

