<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="page/view_content/response/content/result">
		Результат проверки :
			<table>
			<xsl:for-each select="entry">
				<tr>
				<td><xsl:value-of select="@row"/></td>
				<xsl:for-each select="column">
				   	<td><xsl:value-of select="text()"/></td>
				   </xsl:for-each>
				</tr>
			</xsl:for-each>
			</table>
	</xsl:template>
</xsl:stylesheet>