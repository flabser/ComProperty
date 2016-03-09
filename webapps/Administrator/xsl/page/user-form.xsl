<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity = 'user']"/>
    </xsl:template>

    <xsl:template match="document[@entity = 'user']">
        <form name="{@entity}">
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
                            <input type="text" name="login" value="{fields/login}" class="span4" required="required"
                                   autofocus="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="'E-mail'"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="email" value="{fields/email}" class="span4"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/password/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="password" name="pwd" value="" class="span3" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/password_confirm/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="password" name="pwd_confirm" value="" class="span3" autocomplete="off"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/applications/@caption"/>
                        </div>
                        <div class="controls">
                            <ul class="list-style-none">
                                <xsl:for-each select="//query[@entity = 'application']/entry">
                                    <li>
                                        <label>
                                            <input type="checkbox" name="app" value="{viewcontent/app/@id}">
                                                <xsl:if test="//fields/apps/entry/@id = viewcontent/app/@id">
                                                    <xsl:attribute name="checked" select="'checked'"/>
                                                </xsl:if>
                                            </input>
                                            <span>
                                                <xsl:value-of select="viewcontent/app"/>
                                            </span>
                                        </label>
                                    </li>
                                </xsl:for-each>
                            </ul>
                        </div>
                    </div>
                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{@docid}"/>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
