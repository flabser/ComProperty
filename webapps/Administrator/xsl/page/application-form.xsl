<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity = 'application']"/>
    </xsl:template>

    <xsl:template match="document[@entity = 'application']">
        <form name="{@entity}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/application/@caption"/>
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
                            <input type="text" name="name" value="{fields/name}" class="span6" required="required"
                                   autofocus="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/code/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="appcode" value="{fields/appcode}" class="span6"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/default_url/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="defaulturl" value="{fields/defaulturl}" class="span7"
                                   required="required"/>
                        </div>
                    </div>
                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{@docid}"/>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
