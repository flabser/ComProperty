<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

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
            <div class="pull-left">
                <div class="inline">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </div>
            <div class="pull-right">
                <xsl:apply-templates select="//view_content" mode="page-navigator"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="entry" mode="view-table-body">
        <div class="entry-wrap">
            <div data-id="{@id}" class="entry">
                <label class="entry-select">
                    <input type="checkbox" name="docid" value="{@id}"/>
                </label>
                <a href="{@url}" class="entry-link">
                    <div class="entry-fields">
                        <span class="vw-inv-number">
                            <xsl:value-of select="viewcontent/type"/>
                        </span>
                        <span class="vw-object-name">
                            <xsl:value-of select="viewcontent/sender"/>
                        </span>
                        <span class="vw-original-cost">
                            <xsl:value-of select="viewcontent/recipient"/>
                        </span>
                        <span class="vw-balance-holder">
                            <xsl:value-of select="viewcontent/sendtime"/>
                        </span>
                        <span class="vw-balance-holder">
                            <xsl:value-of select="viewcontent/content"/>
                        </span>
                    </div>
                </a>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="view-table-captions">
        <span class="vw-inv-number">
            <xsl:value-of select="//captions/type/@caption"/>
        </span>
        <span class="vw-object-name">
            <xsl:value-of select="//captions/sender/@caption"/>
        </span>
        <span class="vw-original-cost">
            <xsl:value-of select="//captions/recipient/@caption"/>
        </span>
        <span class="vw-balance-holder">
            <xsl:value-of select="//captions/sendtime/@caption"/>
        </span>
        <span class="vw-balance-holder">
            <xsl:value-of select="//captions/content/@caption"/>
        </span>
    </xsl:template>

</xsl:stylesheet>
