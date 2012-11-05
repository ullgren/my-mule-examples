SOAP2SOAP
=========

In this test integration we have a internal web-service that exposes a not so user friendly interface.
See src/test/resources/innerservice.wsdl

We now  want to expose a new public interface.

In the past I've done similar integrations using JBoss ESB where the flow is like this:
 1. Inbound enpoint that exposes the webservice and gives me the payload (soap body)
 2. XSLT transformation (and other enrichment) of body payload
 3. A home made action/endpoint that takes the (new) payload and calls the internal SOAP endoint.
https://community.jboss.org/wiki/JAX-WSBasedWebServiceClientAction

I normaly find Mule very inuative and simple when it comes to WS-SOAP but unfortuanly there is no           
simple WS-SOAP client component that lets us send the XML payload directly to a remote endpoint without 
the need of a service classes and transforming payload into Object[] or domain objects.

In this project I recreate the same excersise but using Mule ESB CE 3.3.0 
(http://www.mulesoft.org/download-mule-esb-community-edition)

Things I found in this project that I wish Mule had out-of-the-box.

 * "Set payload" component that can handle the value as a multi-line CDATA instead of a attribute.
 * A simple "wrap payload in envelope" opteration on the SOAP component.

