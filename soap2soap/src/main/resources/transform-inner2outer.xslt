<?xml version='1.0' ?>
<xsl:stylesheet version="1.0" xmlns:ns0="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                              xmlns:urn="urn:greeter:GreeterResponder:1" xmlns:echo="http://echo.example.com/">

   <xsl:output method="xml" version="1.0" encoding="UTF-8" omit-xml-declaration="yes" indent="yes" />
   
   <xsl:template match="echo:echoResponse">
   	  <urn:GreetingResponse>
         <urn:Greeting>
         	<xsl:value-of select="return"/>
         </urn:Greeting>
      </urn:GreetingResponse>
   </xsl:template>
</xsl:stylesheet>