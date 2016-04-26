<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout">
            <xsl:with-param name="include_head">
                <link rel="stylesheet" href="/SharedResources/vendor/google-code-prettify/prettify.css"/>
                <script src="/SharedResources/vendor/google-code-prettify/prettify.js"></script>
                <style>
                    .controls .btn { vertical-align: middle; }
                </style>
            </xsl:with-param>
        </xsl:call-template>
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
                    <div class="form-group js-rest-scope">
                        <div class="control-label">
                            getbycoord
                        </div>
                        <div class="controls">
                            <input type="text" value="{content/getbycoord}" class="span6"/>
                            <button type="button" class="btn" data-action="do-get">Проверить</button>
                            <button type="button" class="btn" data-action="do-clear">Очистить</button>
                        </div>
                        <h3>Ответ</h3>
                        <pre class="prettyprint lang-javascript"></pre>
                    </div>
                    <div class="form-group js-rest-scope">
                        <div class="control-label">
                            getbystreet
                        </div>
                        <div class="controls">
                            <input type="text" value="{content/getbystreet}" class="span6"/>
                            <button type="button" class="btn" data-action="do-get">Проверить</button>
                            <button type="button" class="btn" data-action="do-clear">Очистить</button>
                        </div>
                        <h3>Ответ</h3>
                        <pre class="prettyprint lang-javascript"></pre>
                    </div>
                </fieldset>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
