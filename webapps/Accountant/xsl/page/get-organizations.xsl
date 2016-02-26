<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../templates/pagination.xsl"/>

    <xsl:output method="html" encoding="utf-8" indent="no"/>

    <xsl:template match="//view_content//query">
        <xsl:if test="entry">
            <xsl:apply-templates select="//view_content" mode="page-navigator"/>
            <ul class="nb-dialog-list">
                <xsl:apply-templates select="entry" mode="dep"/>
            </ul>
        </xsl:if>
    </xsl:template>

    <xsl:template match="entry"/>

    <xsl:template match="entry" mode="dep">
        <li class="nb-dialog-list-it">
            <label ondblclick="nb.dialog.execute(this)">
                <input data-type="select" type="radio" name="org" value="{@id}" data-text="{viewcontent/name}"/>
                <span>
                    <xsl:value-of select="viewcontent/name"/>
                </span>
                <input data-id="{@id}" name="login" value="{viewcontent/login}" type="hidden"/>
            </label>
        </li>
    </xsl:template>

</xsl:stylesheet>
