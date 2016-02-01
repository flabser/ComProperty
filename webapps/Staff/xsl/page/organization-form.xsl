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
                    <xsl:value-of select="//captions/organization/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/name/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="field-wrapper col-lg-6">
                                <input type="text" name="name" value="{//fields/name}" class="form-control"
                                       required="required"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label"></div>
                        <div class="controls">
                            <div class="field-wrapper col-lg-6">
                                <label class="btn btn-sm">
                                    <input type="checkbox" name="is_primary" value="1">
                                        <xsl:if test="//fields/is_primary  = '1'">
                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                        </xsl:if>
                                        <xsl:value-of select="//captions/is_primary_organization/@caption"/>
                                    </input>
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{//document/@docid}"/>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
