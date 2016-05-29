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
                    <xsl:value-of select="//captions/no_access/@caption"/>
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
        <form class="wizard update-file-check-status-{//fields/status}" name="wizard" action="p?id=update-wizard"
              method="get">
            <ul class="wizard_steps">
                <xsl:choose>
                    <xsl:when test="//fields/step = 4">
                        <li class="wizard_step active">
                            <a href="#result" data-wizard-step="4">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat(//captions/step/@caption, ' ', 4)"/>
                                </span>
                                <span class="wizard_step-description">
                                    <xsl:value-of select="//captions/result/@caption"/>
                                </span>
                            </a>
                        </li>
                    </xsl:when>
                    <xsl:otherwise>
                        <li class="wizard_step">
                            <xsl:if test="//fields/step = 0 or //fields/step = 1">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <a href="#step1" data-wizard-step="1">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat(//captions/step/@caption, ' ', 1)"/>
                                </span>
                                <span class="wizard_step-description">
                                    <xsl:value-of select="//captions/wizard_step_select_file_and_action/@caption"/>
                                </span>
                            </a>
                        </li>
                        <li class="wizard_step">
                            <xsl:if test="//fields/step = 2">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <xsl:if test="//fields/loadtype = ''">
                                <xsl:attribute name="class" select="'wizard_step disabled'"/>
                            </xsl:if>
                            <a href="#step2" data-wizard-step="2">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat(//captions/step/@caption, ' ', 2)"/>
                                </span>
                                <span class="wizard_step-description">
                                    <xsl:value-of select="//captions/wizard_step_check_file/@caption"/>
                                    <xsl:choose>
                                        <xsl:when test="//fields/status = 2">
                                            <i class="fa fa-check-circle-o"></i>
                                        </xsl:when>
                                        <xsl:when test="//fields/status = 4">
                                            <i class="fa fa-exclamation-circle"></i>
                                        </xsl:when>
                                    </xsl:choose>
                                </span>
                            </a>
                        </li>
                        <li class="wizard_step">
                            <xsl:if test="//fields/status = 0 or //fields/status = 1 or //fields/status = 4">
                                <xsl:attribute name="class" select="'wizard_step disabled'"/>
                            </xsl:if>
                            <xsl:if test="//fields/step = 3">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <a href="#step3" data-wizard-step="3">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat(//captions/step/@caption, ' ', 3)"/>
                                </span>
                                <span class="wizard_step-description">
                                    <xsl:choose>
                                        <xsl:when test="//fields/loadtype = 'writeoff'">
                                            <xsl:value-of select="//captions/wizard_action_writeoff/@caption"/>
                                        </xsl:when>
                                        <xsl:when test="//fields/loadtype = 'transfer'">
                                            <xsl:value-of select="//captions/wizard_action_transfer/@caption"/>
                                        </xsl:when>
                                        <xsl:when test="//fields/loadtype = 'upload'">
                                            <xsl:value-of select="//captions/wizard_action_upload/@caption"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:attribute name="class"
                                                           select="'wizard_step-description wizard_step-no-action'"/>
                                            <span class="text-muted">
                                                <xsl:value-of select="//captions/wizard_no_action/@caption"/>
                                            </span>
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
            <xsl:if test="//fields/filename != ''">
                <nav class="wizard_nav">
                    <a class="wizard_nav-reset" href="?id=update-wizard">
                        <xsl:choose>
                            <xsl:when test="//fields/step = 4">
                                <xsl:value-of select="//captions/new_update/@caption"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="//captions/wizard_reset/@caption"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </a>
                    <button type="button" class="wizard_nav-btn" data-wizard-step="prev">
                        <i class="fa fa-angle-left"></i>
                        <span>
                            <xsl:value-of select="//captions/wizard_prev_step/@caption"/>
                        </span>
                    </button>
                    <button type="button" class="wizard_nav-btn" data-wizard-step="next">
                        <span>
                            <xsl:value-of select="//captions/wizard_next_step/@caption"/>
                        </span>
                        <i class="fa fa-angle-right"></i>
                    </button>
                    <label class="wizard_nav-auto-btn">
                        <input type="checkbox" name="wizard-auto" value="1"/>
                        <span>
                            <xsl:value-of select="//captions/wizard_auto_step/@caption"/>
                        </span>
                    </label>
                </nav>
            </xsl:if>
            <input type="hidden" name="fsid" value="{//formsesid}"/>
            <input type="hidden" name="step" value="{//fields/step}"/>
            <input type="hidden" name="uploadtype" value="{//fields/loadtype}"/>
            <input type="hidden" name="filename" value="{//fields/filename}"/>
            <input type="hidden" name="status" value="{//fields/status}"/>
        </form>

        <xsl:if test="//fields/step = 2">
            <div class="scroll-shadow update-status-{//fields/status}">
                <div class="js-check-result">
                    <xsl:apply-templates select="//fields/sheeterrs"/>
                </div>
            </div>
        </xsl:if>
    </xsl:template>

    <xsl:template name="tpl_step_1">
        <xsl:call-template name="wizard-file-name"/>
        <!--<xsl:apply-templates select="//fields/msg"/>-->
        <xsl:if test="//fields/filename != ''">
            <div class="wizard_content-gr">
                <header>
                    <xsl:value-of select="//captions/actions/@caption"/>
                </header>
                <section>
                    <label class="wizard_action-btn">
                        <input type="radio" name="_uploadtype" value="upload"/>
                        <span>
                            <xsl:value-of select="//captions/wizard_action_upload/@caption"/>
                        </span>
                        <i class="fa fa-angle-right"></i>
                    </label>
                    <div></div>
                    <label class="wizard_action-btn">
                        <input type="radio" name="_uploadtype" value="writeoff"/>
                        <span>
                            <xsl:value-of select="//captions/wizard_action_writeoff/@caption"/>
                        </span>
                        <i class="fa fa-angle-right"></i>
                    </label>
                    <div></div>
                    <label class="wizard_action-btn">
                        <input type="radio" name="_uploadtype" value="transfer"/>
                        <span>
                            <xsl:value-of select="//captions/wizard_action_transfer/@caption"/>
                        </span>
                        <i class="fa fa-angle-right"></i>
                    </label>
                </section>
            </div>
        </xsl:if>
    </xsl:template>

    <xsl:template name="tpl_step_2">
        <xsl:call-template name="wizard-file-name"/>
        <xsl:apply-templates select="//fields/msg"/>
        <div class="wizard_content-gr">
            <header>
                <xsl:value-of select="//captions/actions/@caption"/>
            </header>
            <section>
                <p>
                    <label>
                        <input type="checkbox" name="stopiferror" value="1" checked="checked"/>
                        <span>
                            <xsl:value-of select="//captions/stop_check_if_error/@caption"/>
                        </span>
                    </label>
                </p>
                <a class="wizard_action-btn js-check" href="#check">
                    <span>
                        <xsl:value-of select="//captions/check_file/@caption"/>
                    </span>
                    <i class="fa fa-angle-right"></i>
                </a>
            </section>
        </div>
    </xsl:template>

    <xsl:template name="tpl_step_3">
        <xsl:call-template name="wizard-file-name"/>
        <xsl:apply-templates select="//fields/msg"/>
        <xsl:if test="//fields/loadtype = 'upload' or //fields/loadtype = 'transfer'">
            <div class="wizard_content-gr">
                <header>
                    <xsl:value-of select="//captions/select_balanceholder_readers/@caption"/>
                </header>
                <section>
                    <xsl:if test="//fields/loadtype = 'upload'">
                        <button type="button" class="btn btn js-select-balance-holder">
                            <span>
                                <xsl:value-of select="//captions/balanceholder/@caption"/>
                            </span>
                        </button>
                        <button type="button" class="btn btn js-select-readers">
                            <span>
                                <xsl:value-of select="//captions/readers/@caption"/>
                            </span>
                        </button>
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
                    </xsl:if>
                    <xsl:if test="//fields/loadtype = 'transfer'">
                        <div name="js-init-update-panel" data-file-name="{//fields/filename}">
                            <div style="min-height:90px">
                                <ul class="nb-dialog-list wizard-fields-list">
                                    <li>
                                        <button type="button" class="btn btn js-select-recipients"
                                                style="margin-top:3px; padding-bottom:11px">
                                            <span>
                                                <xsl:value-of select="//captions/recipient/@caption"/>
                                            </span>
                                        </button>
                                        <label class="btn btn-md btn-update-file-excel js-attach-order" for="uporder">
                                            <i class="fa fa-file-text-o"></i>
                                            <span>
                                                <xsl:value-of select="//captions/attach_order/@caption"/>
                                            </span>
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
                </section>
            </div>
        </xsl:if>
        <div class="wizard_content-gr">
            <header>
                <xsl:value-of select="//captions/actions/@caption"/>
            </header>
            <section>
                <a class="wizard_action-btn js-step-3" href="#">
                    <xsl:choose>
                        <xsl:when test="//fields/loadtype = 'upload'">
                            <xsl:attribute name="data-action" select="'upload'"/>
                            <span>
                                <xsl:value-of select="//captions/wizard_action_upload/@caption"/>
                            </span>
                        </xsl:when>
                        <xsl:when test="//fields/loadtype = 'writeoff'">
                            <xsl:attribute name="data-action" select="'writeoff'"/>
                            <span>
                                <xsl:value-of select="//captions/wizard_action_writeoff/@caption"/>
                            </span>
                        </xsl:when>
                        <xsl:when test="//fields/loadtype = 'transfer'">
                            <xsl:attribute name="data-action" select="'transfer'"/>
                            <span>
                                <xsl:value-of select="//captions/wizard_action_transfer/@caption"/>
                            </span>
                        </xsl:when>
                    </xsl:choose>
                </a>
            </section>
        </div>
    </xsl:template>

    <xsl:template name="tpl_step_4">
        <div class="wizard_content-gr">
            <header>
                <xsl:value-of select="//captions/file/@caption"/>
            </header>
            <section>
                <xsl:value-of select="//fields/filename"/>
            </section>
        </div>
        <div class="wizard_content-gr">
            <header>
                <xsl:value-of select="//captions/result/@caption"/>
            </header>
            <section>
                <xsl:apply-templates select="//fields/msg"/>
            </section>
        </div>
    </xsl:template>

    <xsl:template name="wizard-file-name">
        <div class="wizard_content-gr">
            <header>
                <xsl:value-of select="//captions/file/@caption"/>
            </header>
            <section>
                <xsl:if test="//fields/step = 1 or //fields/step = 0">
                    <label class="btn btn-update-file-excel" for="upfile">
                        <i class="fa fa-file-excel-o"></i>
                        <span>
                            <xsl:value-of select="//action[@id = 'attach_file']/@caption"/>
                        </span>
                    </label>
                </xsl:if>
                <span class="update-file-name">
                    <xsl:value-of select="//fields/filename"/>
                </span>
            </section>
        </div>
    </xsl:template>

    <xsl:template match="msg">
        <xsl:if test="text() != ''">
            <p class="update-file-msg">
                <xsl:value-of select="text()"/>
            </p>
        </xsl:if>
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
