<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="utf-8" indent="no"/>

    <xsl:template match="//view_content//result">
        <xsl:if test="entry">
            <ul>
                <xsl:apply-templates select="entry"/>
            </ul>
        </xsl:if>
    </xsl:template>

    <xsl:template match="entry">
        <li>
            <label ondblclick="nb.dialog.execute(this)">
                <input data-type="select" type="radio" name="employee" value="{@id}" data-text="{viewcontent/name}"/>
                <input data-id="{@id}" name="login" value="{viewcontent/login}" type="hidden"/>
                <span>
                    <xsl:value-of select="viewcontent/name"/>
                </span>
            </label>
        </li>
    </xsl:template>

</xsl:stylesheet>
