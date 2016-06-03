<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:variable name="wizardStep" select="//fields/step"/>
    <xsl:variable name="loadType" select="//fields/loadtype"/>
    <xsl:variable name="updateFileStatus" select="//fields/status"/>
    <xsl:variable name="fsId" select="//formsesid"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout">
            <xsl:with-param name="include_head">
                <link rel="stylesheet" href="/SharedResources/knca/eds.css"/>
                <script src="/SharedResources/knca/sjcl.js"></script>
                <script src="/SharedResources/knca/knca_ws.js"></script>
                <!-- <script src="/SharedResources/knca/knca.js"></script> -->
            </xsl:with-param>
        </xsl:call-template>
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
            <input type="file" id="upfile" name="upfile" onchange="uploadUpdate(this, {$fsId})"
                   accept="application/vnd.ms-excel"/>
        </form>
        <form class="hidden" method="POST" enctype="multipart/form-data">
            <input type="file" id="uporder" name="uporder" onchange="uploadUpdate(this, {$fsId})"/>
        </form>
    </xsl:template>

    <xsl:template name="wizard">
        <form class="wizard update-file-check-status-{$updateFileStatus}" name="wizard" action="p?id=update-wizard"
              method="get">
            <ul class="wizard_steps">
                <xsl:choose>
                    <xsl:when test="$wizardStep = 4">
                        <li class="wizard_step active">
                            <a href="#result" data-wizard-step="4">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="//captions/result/@caption"/>
                                </span>
                                <span class="wizard_step-description result">
                                    <xsl:call-template name="wizard-action-name"/>
                                </span>
                            </a>
                        </li>
                    </xsl:when>
                    <xsl:otherwise>
                        <li class="wizard_step">
                            <xsl:if test="$wizardStep = 0 or $wizardStep = 1">
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
                            <xsl:if test="$wizardStep = 2">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <xsl:if test="$loadType = ''">
                                <xsl:attribute name="class" select="'wizard_step disabled'"/>
                            </xsl:if>
                            <a href="#step2" data-wizard-step="2">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat(//captions/step/@caption, ' ', 2)"/>
                                </span>
                                <span class="wizard_step-description">
                                    <xsl:value-of select="//captions/wizard_step_check_file/@caption"/>
                                    <xsl:choose>
                                        <xsl:when test="$updateFileStatus = 2">
                                            <i class="fa fa-check-circle-o"></i>
                                        </xsl:when>
                                        <xsl:when test="$updateFileStatus = 4">
                                            <i class="fa fa-exclamation-circle"></i>
                                        </xsl:when>
                                    </xsl:choose>
                                </span>
                            </a>
                        </li>
                        <li class="wizard_step">
                            <xsl:if test="$updateFileStatus = 0 or $updateFileStatus = 1 or $updateFileStatus = 4">
                                <xsl:attribute name="class" select="'wizard_step disabled'"/>
                            </xsl:if>
                            <xsl:if test="$wizardStep = 3">
                                <xsl:attribute name="class" select="'wizard_step active'"/>
                            </xsl:if>
                            <a href="#step3" data-wizard-step="3">
                                <span class="wizard_step-title">
                                    <xsl:value-of select="concat(//captions/step/@caption, ' ', 3)"/>
                                </span>
                                <span class="wizard_step-description">
                                    <xsl:choose>
                                        <xsl:when
                                                test="$loadType = 'upload' or $loadType = 'transfer' or $loadType = 'writeoff'">
                                            <xsl:call-template name="wizard-action-name"/>
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
                    <xsl:when test="$wizardStep = 0 or $wizardStep = 1">
                        <xsl:call-template name="tpl_step_1"/>
                    </xsl:when>
                    <xsl:when test="$wizardStep = 2">
                        <xsl:call-template name="tpl_step_2"/>
                    </xsl:when>
                    <xsl:when test="$wizardStep = 3">
                        <xsl:call-template name="tpl_step_3"/>
                    </xsl:when>
                    <xsl:when test="$wizardStep = 4">
                        <xsl:call-template name="tpl_step_4"/>
                    </xsl:when>
                </xsl:choose>
            </div>
            <xsl:if test="//fields/filename != ''">
                <nav class="wizard_nav">
                    <a class="wizard_nav-reset" href="?id=update-wizard">
                        <xsl:choose>
                            <xsl:when test="$wizardStep = 4">
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
            <input type="hidden" name="fsid" value="{$fsId}"/>
            <input type="hidden" name="step" value="{$wizardStep}"/>
            <input type="hidden" name="uploadtype" value="{$loadType}"/>
            <input type="hidden" name="filename" value="{//fields/filename}"/>
            <input type="hidden" name="status" value="{$updateFileStatus}"/>
        </form>

        <xsl:if test="$wizardStep = 2">
            <div class="scroll-shadow update-status-{$updateFileStatus} js-check-result">
                <xsl:apply-templates select="//fields/sheeterrs"/>
            </div>
        </xsl:if>
    </xsl:template>

    <xsl:template name="tpl_step_1">
        <xsl:call-template name="wizard-file-name"/>
        <xsl:apply-templates select="//fields/msg"/>
        <xsl:if test="//fields/filename != ''">
            <div class="wizard_content-gr">
                <header>
                    <xsl:value-of select="//captions/actions/@caption"/>
                </header>
                <section>
                    <label class="wizard_action-btn">
                        <input type="radio" name="_uploadtype" value="upload"/>
                        <span>
                            <xsl:call-template name="wizard-action-name">
                                <xsl:with-param name="load-type" select="'upload'"/>
                            </xsl:call-template>
                        </span>
                        <i class="fa fa-angle-right"></i>
                    </label>
                    <div></div>
                    <label class="wizard_action-btn">
                        <input type="radio" name="_uploadtype" value="writeoff"/>
                        <span>
                            <xsl:call-template name="wizard-action-name">
                                <xsl:with-param name="load-type" select="'writeoff'"/>
                            </xsl:call-template>
                        </span>
                        <i class="fa fa-angle-right"></i>
                    </label>
                    <div></div>
                    <label class="wizard_action-btn">
                        <input type="radio" name="_uploadtype" value="transfer"/>
                        <span>
                            <xsl:call-template name="wizard-action-name">
                                <xsl:with-param name="load-type" select="'transfer'"/>
                            </xsl:call-template>
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
                <button type="button" class="wizard_action-btn js-check">
                    <span>
                        <xsl:value-of select="//captions/check_file/@caption"/>
                    </span>
                    <i class="fa fa-angle-right"></i>
                </button>
            </section>
        </div>
        <!--<div class="wizard_content-gr">
            <p class="sign-help">
                <xsl:value-of select="//captions/sign_help/@caption"/>
            </p>
            <div class="sign-msg"></div>
            <section>
                <button type="button" class="wizard_action-btn js-sign">
                    <span>
                        <xsl:value-of select="//captions/sign_file/@caption"/>
                    </span>
                    <i class="fa fa-pencil"></i>
                </button>
                <textarea id="sign-text" readonly="readonly"></textarea>
            </section>
        </div>-->
    </xsl:template>

    <xsl:template name="tpl_step_3">
        <xsl:call-template name="wizard-file-name"/>
        <xsl:apply-templates select="//fields/msg"/>
        <xsl:if test="$loadType = 'upload' or $loadType = 'transfer'">
            <div class="wizard_content-gr">
                <xsl:if test="$loadType = 'upload'">
                    <header>
                        <xsl:value-of select="//captions/select_balanceholder_readers/@caption"/>
                    </header>
                    <section>
                        <button type="button" class="btn js-select-balance-holder">
                            <xsl:value-of select="//captions/balanceholder/@caption"/>
                        </button>
                        <button type="button" class="btn js-select-readers">
                            <xsl:value-of select="//captions/readers/@caption"/>
                        </button>
                        <div style="margin-top:15px;">
                            <strong class="update-balance-holder" data-input="balanceholder"></strong>
                            <ul class="update-readers" data-input="readers"></ul>
                            <ul class="update-order" data-input="order">
                                <xsl:value-of select="//fields/orderfilename"/>
                            </ul>
                        </div>
                    </section>
                </xsl:if>
                <xsl:if test="$loadType = 'transfer'">
                    <header>
                        <xsl:value-of select="//captions/select_recipient_attach_order/@caption"/>
                    </header>
                    <section>
                        <!--<p>
                            <label>
                                <input type="checkbox" name="sign-file" value="1" checked="checked"/>
                                <span>
                                    <xsl:value-of select="//captions/sign_file/@caption"/>
                                </span>
                            </label>
                        </p>-->
                        <button type="button" class="btn js-select-recipients">
                            <xsl:value-of select="//captions/recipient/@caption"/>
                        </button>
                        <label class="btn js-attach-order" for="uporder">
                            <i class="fa fa-paperclip"></i>
                            <span>
                                <xsl:value-of select="//captions/attach_order/@caption"/>
                            </span>
                        </label>
                        <button type="button" class="btn js-select-readers">
                            <xsl:value-of select="//captions/readers/@caption"/>
                        </button>
                        <div style="margin-top:15px;">
                            <ul class="update-recipients" data-input="recipient">
                                <xsl:value-of select="//fields/recipient"/>
                            </ul>
                            <ul class="update-order" data-input="order">
                                <xsl:value-of select="//fields/orderfilename"/>
                            </ul>
                            <ul class="update-readers" data-input="readers"></ul>
                        </div>
                    </section>
                </xsl:if>
            </div>
        </xsl:if>
        <div class="wizard_content-gr">
            <header>
                <xsl:value-of select="//captions/actions/@caption"/>
            </header>
            <section>
                <button type="button" class="wizard_action-btn js-step-3" data-action="$loadType">
                    <xsl:call-template name="wizard-action-name"/>
                </button>
            </section>
        </div>
    </xsl:template>

    <xsl:template name="tpl_step_4">
        <xsl:call-template name="wizard-file-name"/>
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
                <xsl:if test="$wizardStep = 0">
                    <p>
                        <label>
                            <input type="checkbox" name="sign-file" value="1" checked="checked">
                                <xsl:if test="//fields/filename != ''">
                                    <xsl:attribute name="disabled" select="'disabled'"/>
                                </xsl:if>
                            </input>
                            <span>
                                <xsl:value-of select="//captions/sign_file/@caption"/>
                            </span>
                        </label>
                    </p>
                </xsl:if>
                <xsl:if test="$wizardStep = 0">
                    <label class="btn btn-update-file-excel" for="upfile">
                        <i class="fa fa-file-excel-o"></i>
                        <span>
                            <xsl:value-of select="//action[@id = 'attach_file']/@caption"/>
                        </span>
                    </label>
                </xsl:if>
                <span class="update-file-name">
                    <xsl:if test="//fields/sign = 'valid'">
                        <i class="fa fa-check-circle-ok" style="color:green"></i>
                    </xsl:if>
                    <xsl:if test="//fields/sign = 'invalid'">
                        <i class="fa fa-exclamation-triangle" style="color:red"></i>
                    </xsl:if>
                    <xsl:value-of select="//fields/filename"/>
                </span>
            </section>
        </div>
    </xsl:template>

    <xsl:template name="wizard-action-name">
        <xsl:param name="load-type" select="$loadType"/>
        <xsl:choose>
            <xsl:when test="$load-type = 'upload'">
                <xsl:value-of select="//captions/wizard_action_upload/@caption"/>
            </xsl:when>
            <xsl:when test="$load-type = 'writeoff'">
                <xsl:value-of select="//captions/wizard_action_writeoff/@caption"/>
            </xsl:when>
            <xsl:when test="$load-type = 'transfer'">
                <xsl:value-of select="//captions/wizard_action_transfer/@caption"/>
            </xsl:when>
        </xsl:choose>
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
