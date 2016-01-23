<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout">
            <xsl:with-param name="include_head">
                <link rel="stylesheet" href="css/layout.css"/>
                <script src="js/upload.js"/>
            </xsl:with-param>
        </xsl:call-template>
    </xsl:template>

    <xsl:template name="_content">
        <header class="content-header">
            <h1 class="header-title">
                <xsl:value-of select="//captions/name/@caption"/>
            </h1>
            <div class="content-actions">
                <xsl:apply-templates select="//actionbar"/>
            </div>
        </header>
        <section class="content-body">
            <form name="{//document/@entity}">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <label class="btn btn-lg" for="upfile">
                            <i class="fa fa-file-excel-o"></i>
                            <span>
                                <xsl:value-of select="//captions/download/@caption"/>
                            </span>
                        </label>
                    </div>
                </fieldset>

                <input type="hidden" name="type" value="save"/>
                <input type="hidden" name="id" value="{@id}"/>
                <input type="hidden" name="key" value="{document/@docid}"/>
                <input type="hidden" name="savedate" value="{document/@docid}"/>
                <input type="hidden" name="author" value="{@username}"/>
            </form>
            <form class="hidden" method="POST" enctype="multipart/form-data">
                <input type="file" id="upfile" name="upfile" onchange="upload(this)"
                       accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
            </form>
            <div class="upload-result">
                <ul class="js-uploaded-files"></ul>
                <div id="checker_result"></div>
            </div>
        </section>
    </xsl:template>

</xsl:stylesheet>