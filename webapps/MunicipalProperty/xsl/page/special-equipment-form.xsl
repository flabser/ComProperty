<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>
    <xsl:import href="../templates/transport.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <form class="form form-{$editmode}" action="Provider" method="post"
              enctype="application/x-www-form-urlencoded" name="property">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/special_equipment/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <xsl:call-template name="transport-field-set"/>
        </form>
    </xsl:template>

</xsl:stylesheet>
