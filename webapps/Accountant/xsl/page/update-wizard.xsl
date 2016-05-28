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
            <xsl:choose>
                <xsl:when test="//action[@id = 'attach_file']">
                    <xsl:call-template name="wizard"/>
                </xsl:when>
                <xsl:otherwise>
                    Нет доступа
                </xsl:otherwise>
            </xsl:choose>
        </section>
        <form class="hidden" method="POST" enctype="multipart/form-data">
            <xsl:variable name="fsid" select="page/response/content/formsesid"/>
            <input type="file" id="upfile" name="upfile" onchange="uploadUpdate(this, {$fsid})"
                   accept="application/vnd.ms-excel"/>
        </form>
        <form class="hidden" method="POST" enctype="multipart/form-data">
            <xsl:variable name="fsid" select="//content/formsesid"/>
            <input type="file" id="uporder" name="uporder" onchange="uploadUpdate(this, {$fsid})"/>
        </form>
    </xsl:template>

    <xsl:template name="wizard">
        <form class="wizard" name="wizard" action="" method="get">
            <ul class="wizard_steps">
                <xsl:choose>
                    <xsl:when test="//fields/step = 4">
                        <li class="wizard_step active">
                            <a href="#">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat('Step', 4)"/>
                                </span>
                                <span class="wizard_step-description">Результат</span>
                            </a>
                        </li>
                    </xsl:when>
                    <xsl:otherwise>
                        <li class="wizard_step">
                            <xsl:if test="//fields/step = 0 or //fields/step = 1">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <a href="p?id=update-wizard&amp;fsid={//formsesid}&amp;step=1&amp;uploadtype={//fields/loadtype}">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat('Step', 1)"/>
                                </span>
                                <span class="wizard_step-description">Выбoр файла и действия</span>
                            </a>
                        </li>
                        <li class="wizard_step">
                            <xsl:if test="//fields/step = 0">
                                <xsl:attribute name="class" select="'wizard_step disabled'"/>
                            </xsl:if>
                            <xsl:if test="//fields/step = 2">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <a href="p?id=update-wizard&amp;fsid={//formsesid}&amp;step=2&amp;uploadtype={//fields/loadtype}">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat('Step', 2)"/>
                                </span>
                                <span class="wizard_step-description">Проверка файла</span>
                            </a>
                        </li>
                        <li class="wizard_step disabled">
                            <xsl:if test="//fields/step = 0">
                                <xsl:attribute name="class" select="'wizard_step disabled'"/>
                            </xsl:if>
                            <xsl:if test="//fields/step = 3">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <a href="p?id=update-wizard&amp;fsid={//formsesid}&amp;step=3&amp;uploadtype={//fields/loadtype}">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat('Step', 3)"/>
                                </span>
                                <span class="wizard_step-description">
                                    <xsl:choose>
                                        <xsl:when test="//fields/loadtype = 'writeoff'">Списание</xsl:when>
                                        <xsl:when test="//fields/loadtype = 'transfer'">Передать имущество</xsl:when>
                                        <xsl:when test="//fields/loadtype = 'upload'">Загрузка</xsl:when>
                                        <xsl:otherwise>
                                            <span class="text-muted">Выберите действие!</span>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </span>
                            </a>
                        </li>
                    </xsl:otherwise>
                </xsl:choose>
            </ul>
            <div class="wizard_content">
                <xsl:choose>
                    <xsl:when test="//fields/step = 0 or //fields/step = 1">
                        <xsl:call-template name="tpl_step_1"/>
                    </xsl:when>
                    <xsl:when test="//fields/step = 2">
                        <xsl:call-template name="tpl_step_2"/>
                    </xsl:when>
                    <xsl:when test="//fields/step = 3">
                        <xsl:call-template name="tpl_step_3"/>
                    </xsl:when>
                    <xsl:when test="//fields/step = 4">
                        <xsl:call-template name="tpl_step_4"/>
                    </xsl:when>
                </xsl:choose>
            </div>
            <xsl:if test="//fields/step > 0">
                <nav class="wizard_nav">
                    <a class="wizard_nav-reset" href="?id=update-wizard">
                        <xsl:choose>
                            <xsl:when test="//fields/step = 4">New upload</xsl:when>
                            <xsl:otherwise>Reset</xsl:otherwise>
                        </xsl:choose>
                    </a>
                    <xsl:choose>
                        <xsl:when test="//fields/step = 2">
                            <a class="wizard_nav-btn active" href="#check">Проверить</a>
                        </xsl:when>
                        <xsl:when test="//fields/step = 3">
                            <a class="wizard_nav-btn active" href="#">
                                <xsl:choose>
                                    <xsl:when test="//fields/loadtype = 'upload'">
                                        <xsl:attribute name="data-action" select="'upload'"/>
                                        Загрузить
                                    </xsl:when>
                                    <xsl:when test="//fields/loadtype = 'writeoff'">
                                        <xsl:attribute name="data-action" select="'writeoff'"/>
                                        Списать
                                    </xsl:when>
                                    <xsl:when test="//fields/loadtype = 'transfer'">
                                        <xsl:attribute name="data-action" select="'transfer'"/>
                                        Передать
                                    </xsl:when>
                                </xsl:choose>
                            </a>
                        </xsl:when>
                    </xsl:choose>
                </nav>
            </xsl:if>
            <input type="text" name="filename" value="{//fields/filename}"/>
            <input type="text" name="fsid" value="{page/response/content/formsesid}"/>
            <input type="text" name="uploadtype" value="{//fields/loadtype}"/>
        </form>

        <xsl:if test="//fields/step = 2">
            <div class="panel__body scroll-shadow update-status-{//fields/status}">
                <xsl:apply-templates select="//fields/msg"/>
                <div class="js-check-result">
                    <xsl:apply-templates select="//fields/sheeterrs"/>
                </div>
            </div>
        </xsl:if>
    </xsl:template>

    <xsl:template name="tpl_step_1">
        <div class="wizard_content-gr">
            <header>Выбoр файла</header>
            <section>
                <label class="btn btn-lg btn-update-file-excel" for="upfile">
                    <i class="fa fa-file-excel-o"></i>
                    <span>
                        <xsl:value-of select="//action[@id = 'attach_file']/@caption"/>
                    </span>
                </label>
                <span class="update-file-name">
                    <xsl:value-of select="//fields/filename"/>
                </span>
            </section>
        </div>
        <xsl:if test="//fields/filename != ''">
            <div class="wizard_content-gr">
                <header>Действия</header>
                <section>
                    <a class="link-next"
                       href="p?id=update-wizard&amp;fsid={//formsesid}&amp;step=2&amp;uploadtype=upload">
                        <xsl:if test="//fields/loadtype = 'upload'">
                            <xsl:attribute name="class" select="'link-next active'"/>
                        </xsl:if>
                        <span>Загрузка</span>
                        <i class="fa fa-angle-right"></i>
                    </a>
                    <a class="link-next"
                       href="p?id=update-wizard&amp;fsid={//formsesid}&amp;step=2&amp;uploadtype=writeoff">
                        <xsl:if test="//fields/loadtype = 'writeoff'">
                            <xsl:attribute name="class" select="'link-next active'"/>
                        </xsl:if>
                        <span>Отметить имущество как списанное</span>
                        <i class="fa fa-angle-right"></i>
                    </a>
                    <a class="link-next"
                       href="p?id=update-wizard&amp;fsid={//formsesid}&amp;step=2&amp;uploadtype=transfer">
                        <xsl:if test="//fields/loadtype = 'transfer'">
                            <xsl:attribute name="class" select="'link-next active'"/>
                        </xsl:if>
                        <span>Передать имущество</span>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </section>
            </div>
        </xsl:if>
    </xsl:template>

    <xsl:template name="tpl_step_2">
        <div class="wizard_content-gr">
            <header>Файл</header>
            <section>
                <p>
                    <span class="update-file-name">
                        <xsl:value-of select="//fields/filename"/>
                    </span>
                </p>
                <hr/>
                <label>
                    <input type="checkbox" name="stopiferror" value="1">
                        <xsl:attribute name="checked" select="checked"/>
                        <span>Прервать проверку при ошибке</span>
                    </input>
                </label>
            </section>
        </div>
    </xsl:template>

    <xsl:template name="tpl_step_3">
        <div class="wizard_content-gr">
            <header>Файл</header>
            <section>
                <span class="update-file-name">
                    <xsl:value-of select="//fields/filename"/>
                </span>
            </section>
        </div>

        <xsl:if test="//fields/loadtype = 'upload'">
            <div name="js-init-update-panel" data-file-name="{//fields/filename}">
                <div style="min-height:90px">
                    <ul class="nb-dialog-list wizard-fields-list">
                        <li>
                            <button type="button" class="btn btn js-select-balance-holder">
                                <span>Балансодержатель</span>
                            </button>
                            <button type="button" class="btn btn js-select-readers">
                                <span>Читатели</span>
                            </button>
                        </li>
                    </ul>
                    <input type="hidden" name="balanceholder" value=""/>
                    <input type="hidden" name="status" value="{//fields/status}"/>
                    <div style="margin-top:15px;">
                        <strong class="update-balance-holder" data-input="balanceholder"></strong>
                        <ul class="update-readers" data-input="readers"></ul>
                        <ul class="update-recipients" data-input="recipient"></ul>
                        <ul class="update-order" data-input="order">
                            <xsl:value-of select="//fields/orderfilename"/>
                        </ul>
                    </div>
                </div>
            </div>
        </xsl:if>

        <xsl:if test="//fields/loadtype = 'writeoff'">
            <div name="js-init-update-panel" data-file-name="{//fields/filename}">
                <div>
                    <p style="font-size:16px; font-weight:bold">Результат</p>
                </div>
                <div style="min-height:90px">


                </div>
                <div style="text-align:right;">
                    <button type="button" class="btn btn js-back">
                        <span>Назад</span>
                    </button>
                    <button type="button" class="btn btn js-step-3">
                        <xsl:if test="//fields/status != 2">
                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                        </xsl:if>
                        <span>Списать</span>
                    </button>
                </div>
                <input type="hidden" name="uploadtype" value="{//fields/loadtype}"/>
            </div>
        </xsl:if>

        <xsl:if test="//fields/loadtype = 'transfer'">
            <div name="js-init-update-panel" data-file-name="{//fields/filename}">
                <div>
                    <p style="font-size:16px; font-weight:bold">Мастер загрузки обновлений - Шаг 3 - Завершение
                        загрузки
                    </p>
                </div>
                <div style="min-height:90px">
                    <ul class="nb-dialog-list wizard-fields-list">
                        <li>
                            <button type="button" class="btn btn js-select-recipients"
                                    style="margin-top:3px; padding-bottom:11px">
                                <span>Получатель</span>
                            </button>
                            <label class="btn btn-md btn-update-file-excel js-attach-order" for="uporder">
                                <i class="fa fa-file-text-o"></i>
                                <span>Прикрепить постановление</span>
                            </label>
                        </li>
                    </ul>
                    <input type="hidden" name="balanceholder" value=""/>
                    <input type="hidden" name="status" value="{//fields/status}"/>
                    <div style="margin-top:15px;">
                        <strong class="update-balance-holder" data-input="balanceholder"></strong>
                        <ul class="update-recipients" data-input="recipient"></ul>
                        <ul class="update-order" data-input="order">
                            <xsl:value-of select="//fields/orderfilename"/>
                        </ul>
                    </div>
                </div>
            </div>
        </xsl:if>
    </xsl:template>

    <xsl:template name="tpl_step_4">
        <div class="wizard_content-gr">
            <header>Результат</header>
            <section>
                <xsl:value-of select="//fields/filename"/>
            </section>
        </div>
    </xsl:template>

    <xsl:template match="msg">
        <p class="update-file-msg">
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
