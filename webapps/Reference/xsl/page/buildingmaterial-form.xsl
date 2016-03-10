<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <form name="{//document/@entity}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/building_material/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">
                <div class="tab-content">
                    <fieldset class="fieldset">
                        <div class="form-group">
                            <div class="control-label">
                                    <xsl:value-of select="//captions/name/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="name" value="{//fields/name}" class="span7" required="required"
                                           autofocus="true"/>
                                </div>
                            </div>
                    </fieldset>
                    <fieldset class="fieldset">
                            <legend class="legend legend-address">
                                <xsl:value-of select="//captions/localized_names/@caption"/>
                            </legend>
                            <xsl:for-each select="//fields/localizednames/entry">
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="./@id"/>
                                    </div>
                                    <div class="controls">
                                        <input type="text" value="{.}" name="{lower-case(./@id)}localizedname" class="span7" required="required" autofocus="true"/>
                                    </div>
                                </div>
                            </xsl:for-each>
                        </fieldset>
                </div>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{//document/@docid}"/>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
