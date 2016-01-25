<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="utf-8" indent="no"/>

    <xsl:template match="//view_content//result">
        <xsl:if test="entry">
            <div class="upload-checking-result">
                <table class="table table-bordered">
                    <xsl:for-each select="entry">
                        <tr>
                            <td>
                                <xsl:value-of select="@row"/>
                            </td>
                            <xsl:for-each select="column">
                                <td>
                                    <xsl:value-of select="text()"/>
                                </td>
                            </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>
            </div>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>
