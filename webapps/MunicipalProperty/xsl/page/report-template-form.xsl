<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity != '']"/>
    </xsl:template>

    <xsl:template match="document[@entity]">
        <form class="form form-edit-{@editable}" name="{@entity}" action="" method="post"
              enctype="application/x-www-form-urlencoded">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="concat(//captions/report/@caption, ' / ', fields/name)"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/balance_holder/@caption"/>
                        </div>
                        <div class="controls">
                            <select name="balanceholder" class="span8">
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/property_type/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="span8">
                                <xsl:for-each select="fields/propertytype/entry">
                                    <label class="btn btn-sm">
                                        <input type="checkbox" name="propertycode" value="{@id}"/>
                                        <span>
                                            <xsl:value-of select="text()"/>
                                        </span>
                                    </label>
                                </xsl:for-each>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/report_file_type/@caption"/>
                        </div>
                        <div class="controls">
                            <label class="btn btn-sm">
                                <input type="radio" name="typefilereport" value="1">
                                    <xsl:if test="not(fields/typefilereport) or fields/typefilereport  = '1'">
                                        <xsl:attribute name="checked">checked</xsl:attribute>
                                    </xsl:if>
                                </input>
                                <span>PDF</span>
                            </label>
                            <label class="btn btn-sm">
                                <input type="radio" name="typefilereport" value="2">
                                    <xsl:if test="fields/typefilereport  = '2'">
                                        <xsl:attribute name="checked">checked</xsl:attribute>
                                    </xsl:if>
                                </input>
                                <span>XLSX</span>
                            </label>
                        </div>
                    </div>
                </fieldset>
            </section>
            <footer class="content-actions">
                <button class="btn" type="submit">
                    <xsl:value-of select="//captions/create_report/@caption"/>
                </button>
            </footer>
        </form>
    </xsl:template>

</xsl:stylesheet>
