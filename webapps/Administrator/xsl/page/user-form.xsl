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
                    <xsl:value-of select="//captions/user/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/login/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="login" value="{//fields/login}" class="span5" required="required"
                                   autofocus="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="'E-mail'"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="email" value="{//fields/email}" class="span5"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/password/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="pwd" value="{//fields/pwd}" class="span5"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/application/@caption"/>
                        </div>
                        <div class="controls">
                            <xsl:for-each select="//fields/app">
                                <div>
                                    <xsl:value-of select="."/>
                                </div>
                            </xsl:for-each>
                        </div>
                    </div>
                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{//document/@docid}"/>
            </section>
            <footer class="content-footer">
                <span>
                    <xsl:value-of select="concat(//captions/reg_date/@caption, //fields/regdate)"/>
                </span>
            </footer>
        </form>
    </xsl:template>

</xsl:stylesheet>
