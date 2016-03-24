<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity = 'region']"/>
    </xsl:template>

    <xsl:template match="document[@entity]">
        <form name="{@entity}" action="">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/region/@caption"/>
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
                            <input type="text" name="name" value="{fields/name}" class="span7" autofocus="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/type/@caption"/>
                        </div>
                        <div class="controls">
                            <select name="regiontype" class="span7" autocomplete="off">
                                <xsl:apply-templates select="fields/type" mode="selected_options"/>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/country/@caption"/>
                        </div>
                        <div class="controls">
                            <select name="country" class="span7">
                                <xsl:apply-templates select="fields/country" mode="selected_options"/>
                            </select>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="fieldset">
                    <legend class="legend">
                        <xsl:value-of select="//captions/localized_names/@caption"/>
                    </legend>
                    <xsl:for-each select="fields/localizednames/entry">
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="./@id"/>
                            </div>
                            <div class="controls">
                                <input type="text" value="{.}" name="{lower-case(./@id)}localizedname" class="span7"/>
                            </div>
                        </div>
                    </xsl:for-each>
                </fieldset>
            </section>
        </form>
    </xsl:template>

    <xsl:template match="*" mode="selected_options">
        <option value="{@id}" selected="selected">
            <xsl:value-of select="."/>
        </option>
    </xsl:template>

</xsl:stylesheet>
