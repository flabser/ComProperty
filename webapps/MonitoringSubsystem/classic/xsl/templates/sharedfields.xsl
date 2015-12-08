<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	
	<xsl:template name="kofkuf">
			<!-- 	KOF & KUF -->
										<tr>
											<td class="fc">
												КОФ/КУФ&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/kof}" style="width:150px;" class="td_editable" name="invnumber" title="{document/captions/invnumbertitle/@caption}">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>&#xA0;/&#xA0;
												<input type="text" value="{document/fields/kuf}" style="width:100px;" class="td_editable" name="invnumber" title="{document/captions/invnumbertitle/@caption}">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>											
										</tr>
	</xsl:template>

	</xsl:stylesheet>