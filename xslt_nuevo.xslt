<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/ystfeed/vespaadd/document">
		<html>
			<head>
				<title>Archivo Nuevo</title>
			</head>
			<body>
				<xsl:value-of select="uri"></xsl:value-of>
				<xsl:value-of select="subject"></xsl:value-of>
				<xsl:value-of select="content"></xsl:value-of>
				<xsl:value-of select="bestanswer"></xsl:value-of>
				<xsl:value-of select="nbestanswers"></xsl:value-of>
				<xsl:value-of select="cat"></xsl:value-of>
				<xsl:value-of select="maincat"></xsl:value-of>				
				<xsl:value-of select="date"></xsl:value-of>
				<xsl:value-of select="res_date"></xsl:value-of>				
				<xsl:value-of select="language"></xsl:value-of>
				<xsl:value-of select="id"></xsl:value-of>
				<xsl:value-of select="best_id"></xsl:value-of>
				<xsl:value-of select="score"></xsl:value-of>
				<xsl:value-of select="followers"></xsl:value-of>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>