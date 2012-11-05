<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:urn="urn:greeter:GreeterResponder:1" xmlns:echo="http://echo.example.com/">
  <xsl:output method="xml" encoding="UTF-8"  indent="yes" omit-xml-declaration="yes"/>
    
  
  <!-- Transform from outer to inner -->
  <xsl:template match="urn:GreetingRequest">
  	<echo:echo>
  		<arg0>
  			<xsl:value-of select="urn:Name"/>
  		</arg0>
  	</echo:echo>
  </xsl:template> 

</xsl:stylesheet>