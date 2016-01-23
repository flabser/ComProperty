<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <header class="content-header">
            <h1 class="header-title">
                <xsl:value-of select="//captions/role/@caption"/>
            </h1>
            <div class="content-actions">
                <xsl:apply-templates select="//actionbar"/>
            </div>
        </header>
        <section class="content-body">
            <form name="{//document/@entity}">
                <fieldset class="fieldset">

                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{//document/id}"/>
            </form>
        </section>
    </xsl:template>

</xsl:stylesheet>
