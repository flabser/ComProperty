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
        <form name="{//document/@entity}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/title/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">

                <fieldset class="fieldset">
                    <div class="form-group">
                        <label class="btn btn-lg" for="upfile">
                            <i class="fa fa-file-excel-o"></i>
                            <span>
                                <xsl:value-of select="//captions/attach_file/@caption"/>
                            </span>
                        </label>
                        <div>
                            <progress id="upload-progress-bar" max="100" value="0"></progress>
                        </div>
                    </div>
                </fieldset>

                <input type="hidden" name="type" value="save"/>
                <input type="hidden" name="id" value="{@id}"/>
                <input type="hidden" name="key" value="{document/@docid}"/>

                <div class="upload-result">
                    <div class="js-uploaded-files"></div>
                </div>
            </section>
        </form>
        <form class="hidden" method="POST" enctype="multipart/form-data">
            <input type="file" id="upfile" name="upfile" onchange="upload(this)"
                   accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
        </form>
        <xsl:call-template name="tpl_upload_file"/>
    </xsl:template>

    <xsl:template name="tpl_upload_file">
        <template id="tpl_upload_file">
            <div class="panel update-file-panel js-file-panel">
                <div class="panel__header blink-anim">
                    <div class="panel-title panel-toggle" data-toggle="panel">
                        <i class="fa"></i>
                        <a href="Provider?type=getattach&amp;key=" class="update-file-link js-link"></a>
                        <span>
                            <button type="button" class="btn btn-sm js-check">проверить</button>
                            <button type="button" class="btn btn-sm btn-primary js-load" disabled="disabled">загрузить
                            </button>
                            <button type="button" class="btn btn-sm js-delete">удалить</button>
                        </span>
                    </div>
                </div>
                <div class="panel__body js-check-result"></div>
            </div>
        </template>
    </xsl:template>

</xsl:stylesheet>
