<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//request[@id = 'info-form']/page/response"/>
    </xsl:template>

    <xsl:template match="response">
        <form name="{@entity}" action="" data-edit="{@editable}">
            <header class="content-header">
                <h1 class="header-title">
                    REST Services
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            getbycoord
                        </div>
                        <div class="controls">
                            <input type="text" name="appcode" value="{content/getbycoord}" class="span6"/>
                            <!--<label>
                                <input type="checkbox" name="data-type" value="json"/>
                                json
                            </label>-->
                            <button type="button" class="btn" data-action="do-get">
                                Проверить
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <h3>Ответ</h3>
                        <pre id="request-result"></pre>
                    </div>
                </fieldset>
                 <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            getbystreet
                        </div>
                        <div class="controls">
                            <input type="text" name="appcode" value="{content/getbystreet}" class="span6"/>
                            <!--<label>
                                <input type="checkbox" name="data-type" value="json"/>
                                json
                            </label>-->
                            <button type="button" class="btn" data-action="do-get">
                                Проверить
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <h3>Ответ</h3>
                        <pre id="request-result"></pre>
                    </div>
                </fieldset>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
