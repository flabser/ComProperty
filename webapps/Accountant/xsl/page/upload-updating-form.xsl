<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <header class="content-header">
            <h1 class="header-title">
                <xsl:value-of select="//captions/title/@caption"/>
            </h1>
        </header>
        <section class="content-body">
            <fieldset class="fieldset">
                <div class="form-group">
                    <xsl:if test="//action[@id = 'attach_file']">
                        <label class="btn btn-lg btn-update-file-excel" for="upfile">
                            <i class="fa fa-file-excel-o"></i>
                            <span>
                                <xsl:value-of select="//action[@id = 'attach_file']/@caption"/>
                            </span>
                        </label>
                        <div>
                            <progress id="upload-progress-bar" max="100" value="0"></progress>
                        </div>
                    </xsl:if>
                </div>
            </fieldset>
            <div class="upload-result">
                <div class="js-uploaded-files">
                    <xsl:apply-templates select="//query[@entity = 'uploadedfile']/entry"/>
                </div>
            </div>
        </section>

        <form class="hidden" method="POST" enctype="multipart/form-data">
            <xsl:variable name="fsid" select="//fields/formsesid"/>
            <input type="file" id="upfile" name="upfile" onchange="uploadUpdate(this, {$fsid})"
                   accept="application/vnd.ms-excel"/>
        </form>
        <xsl:call-template name="tpl_update_file_panel"/>
    </xsl:template>

    <xsl:template name="tpl_update_file_panel">
        <template id="tpl_update_file_panel">
            <form>
                <div class="panel update-file-panel js-file-panel">
                    <div class="panel__header blink-anim">
                        <div class="panel-title panel-toggle" data-toggle="panel">
                            <i class="fa"></i>
                            <a href="Provider?id=get-attach&amp;fileid=" class="update-file-link js-link"></a>
                            <span class="update-file-panel-actions">
                                <button type="button" class="btn btn js-check">
                                    <span>Проверить</span>
                                </button>
                                <button type="button" class="btn btn js-select-balance-holder" disabled="disabled">
                                    <span>Балансодержатель</span>
                                </button>
                                <button type="button" class="btn btn js-select-readers" disabled="disabled">
                                    <span>Читатели</span>
                                </button>
                                <button type="button" class="btn btn btn-primary js-load" disabled="disabled">
                                    Загрузить
                                </button>
                                <button type="button" class="btn btn js-delete">
                                    <span>Удалить</span>
                                </button>
                                <div>
                                    <label>
                                        <input type="checkbox" name="stopiferror" value="1">
                                            <xsl:attribute name="checked" select="checked"/>
                                            <span>
                                                Прервать проверку при ошибке
                                            </span>
                                        </input>
                                    </label>
                                </div>
                            </span>
                        </div>
                    </div>
                    <div class="panel__body scroll-shadow">
                        <input type="hidden" name="balanceholder" value=""/>
                        <div>
                            <strong class="update-balance-holder" data-input="balanceholder"></strong>
                            <ul class="update-readers" data-input="reader"></ul>
                        </div>
                        <div class="js-check-result"></div>
                    </div>
                </div>
            </form>
        </template>
    </xsl:template>

    <xsl:template match="entry">
        <form name="js-init-update-panel" data-file-name="{viewcontent/name}">
            <div class="panel update-file-panel update-status-{viewcontent/status} js-file-panel">
                <div class="panel__header">
                    <div class="panel-title panel-toggle" data-toggle="panel">
                        <i class="fa">
                            <xsl:if test="viewcontent/sheeterrs = '' and viewcontent/msg = ''">
                                <xsl:attribute name="class" select="'fa no-errs'"/>
                            </xsl:if>
                        </i>
                        <a href="Provider?id=update-file&amp;fileid=" class="update-file-link js-link">
                            <xsl:value-of select="viewcontent/name"/>
                        </a>
                        <span class="update-file-panel-actions">
                            <button type="button" class="btn btn js-check">
                                <span>Проверить</span>
                            </button>
                            <button type="button" class="btn btn js-select-balance-holder">
                                <xsl:if test="viewcontent/sheeterrs != ''">
                                    <xsl:attribute name="disabled" select="'disabled'"/>
                                </xsl:if>
                                <span>Балансодержатель</span>
                            </button>
                            <button type="button" class="btn btn js-select-readers">
                                <xsl:if test="viewcontent/sheeterrs != ''">
                                    <xsl:attribute name="disabled" select="'disabled'"/>
                                </xsl:if>
                                <span>Читатели</span>
                            </button>
                            <button type="button" class="btn btn btn-primary js-load">
                                <xsl:if test="viewcontent/status != 2 or viewcontent/sheeterrs != '' or viewcontent/msg != ''">
                                    <xsl:attribute name="disabled" select="'disabled'"/>
                                </xsl:if>
                                <span>Загрузить</span>
                            </button>
                            <button type="button" class="btn btn js-delete">
                                <span>Удалить</span>
                            </button>
                            <div>
                                <label>
                                    <input type="checkbox" name="stopiferror" value="1">
                                        <xsl:attribute name="checked" select="checked"/>
                                        <span>
                                            Прервать проверку при ошибке
                                        </span>
                                    </input>
                                </label>
                            </div>
                        </span>
                        <xsl:apply-templates select="viewcontent/msg"/>
                    </div>
                </div>
                <div class="panel__body scroll-shadow">
                    <input type="hidden" name="balanceholder" value=""/>
                    <div>
                        <strong class="update-balance-holder" data-input="balanceholder"></strong>
                        <ul class="update-readers" data-input="reader"></ul>
                    </div>
                    <div class="js-check-result">
                        <xsl:apply-templates select="viewcontent/sheeterrs"/>
                    </div>
                </div>
            </div>
        </form>
    </xsl:template>

    <xsl:template match="msg">
        <p class="update-file-panel-msg">
            <xsl:value-of select="text()"/>
        </p>
    </xsl:template>

    <xsl:template match="sheeterrs">
        <xsl:if test="row">
            <div class="upload-checking-result">
                <table class="table table-bordered">
                    <xsl:for-each select="row">
                        <tr>
                            <td>
                                <xsl:value-of select="@number"/>
                            </td>
                            <xsl:for-each select="column">
                                <td>
                                    <xsl:value-of select="text()"/>
                                </td>
                            </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>
            </div>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>
