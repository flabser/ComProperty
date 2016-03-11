<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>
    <xsl:import href="../templates/strategic-object.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity != '']"/>
    </xsl:template>

    <xsl:template match="document[@entity]">
        <form class="form form-edit-{@editable}" name="{@entity}" action="" enctype="application/x-www-form-urlencoded">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/object_reserved_fund/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <xsl:call-template name="strategic-object-field-set"/>
        </form>
    </xsl:template>

</xsl:stylesheet>
