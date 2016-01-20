<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="pagination.xsl"/>

    <xsl:template name="page-info">
        <xsl:param name="title" select="//captions/title/@caption"/>
        <h1 class="header-title">
            <xsl:value-of select="$title"/>

            <xsl:if test="//view_content//query/@count">
                <sup class="entry-count">
                    <small>
                        <xsl:value-of select="concat('(', //view_content//query/@count, ')')"/>
                    </small>
                </sup>
            </xsl:if>
        </h1>
        <div class="content-actions">
            <xsl:apply-templates select="//view_content" mode="page-navigator"/>
            <xsl:apply-templates select="//actionbar"/>
        </div>
    </xsl:template>

</xsl:stylesheet>
