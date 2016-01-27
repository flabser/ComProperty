<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <header class="content-header">
            <h1 class="header-title">
                <xsl:value-of select="//captions/report/@caption"/>
            </h1>
        </header>
        <section class="content-body">
            <form name="{//document/@entity}">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/balanceholder/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="field-wrapper col-md-6">
                                <div class="form-control selection" data-input="balanceholder"
                                     onclick="nbApp.choiceBalanceHolder(this)">
                                    <xsl:value-of select="//fields/balanceholdername"/>
                                </div>
                                <input type="hidden" name="balanceholder" value="{//fields/balanceholder}"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/propertycode/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="field-wrapper col-md-6">
                                <label class="btn btn-sm">
                                    <input type="checkbox" name="propertycode">
                                        1
                                    </input>
                                </label>
                                <label class="btn btn-sm">
                                    <input type="checkbox" name="propertycode">
                                        2
                                    </input>
                                </label>
                                <label class="btn btn-sm">
                                    <input type="checkbox" name="propertycode">
                                        3
                                    </input>
                                </label>
                                <!--<input type="text" name="propertycode" value="{//fields/propertycode}"
                                       class="form-control" readonly="readonly"/>-->
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/report_file_type/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="field-wrapper col-md-6">
                                <label class="btn btn-sm">
                                    <input type="radio" name="typefilereport" value="1">
                                        <xsl:if test="//fields/typefilereport  = '1'">
                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                        </xsl:if>
                                        <xsl:if test="//document/@status  = 'new'">
                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                        </xsl:if>
                                        PDF
                                    </input>
                                </label>
                                <label class="btn btn-sm">
                                    <input type="radio" name="typefilereport" value="2">
                                        <xsl:if test="//fields/typefilereport  = '2'">
                                            <xsl:attribute name="checked">checked</xsl:attribute>
                                        </xsl:if>
                                        XLSX
                                    </input>
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{//document/@docid}"/>
            </form>
        </section>
        <footer class="content-actions">
            <button class="btn btn-primary" type="button">
                Сформировать отчет
            </button>
        </footer>
    </xsl:template>

</xsl:stylesheet>
