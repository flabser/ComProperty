<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="entry" mode="view-table-body">
        <div class="entry-wrap">
            <div data-id="{@id}" class="entry">
                <label class="entry-select">
                    <input type="checkbox" name="docid" value="{@id}"/>
                </label>
                <a href="{@url}" class="entry-link">
                    <div class="entry-fields">
                        <span class="vw-inv-number">
                            <xsl:value-of select="viewcontent/invnumber"/>
                        </span>
                        <span class="vw-object-name">
                            <xsl:value-of select="viewcontent/objectname"/>
                        </span>
                        <span class="vw-original-cost">
                            <xsl:value-of select="viewcontent/originalcost"/>
                        </span>
                        <span class="vw-balance-holder">
                            <xsl:value-of select="viewcontent/balanceholder"/>
                        </span>
                        <span class="vw-tags">
                            <xsl:if test="viewcontent/tags">
                                <xsl:for-each select="viewcontent/tags/tag">
                                    <div class="tag tag-xs">
                                        <xsl:if test="name != ''">
                                            <xsl:attribute name="style" select="('color:', color)"/>
                                        </xsl:if>
                                        <xsl:value-of select="name"/>
                                    </div>
                                </xsl:for-each>
                            </xsl:if>
                        </span>
                    </div>
                </a>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="view-table-captions">
        <span class="vw-inv-number">
            <xsl:value-of select="//captions/inv_number/@caption"/>
        </span>
        <span class="vw-object-name">
            <xsl:value-of select="//captions/object_name/@caption"/>
        </span>
        <span class="vw-original-cost">
            <xsl:value-of select="//captions/original_cost/@caption"/>
        </span>
        <span class="vw-balance-holder">
            <xsl:value-of select="//captions/balance_holder/@caption"/>
        </span>
        <span class="vw-tags">
            <xsl:value-of select="//captions/tags/@caption"/>
        </span>
    </xsl:template>

</xsl:stylesheet>
