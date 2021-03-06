<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>
    <xsl:import href="../templates/sharedfields.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity = 'contract']"/>
    </xsl:template>

    <xsl:template match="document[@entity]">
        <form class="form" name="{@entity}" action="" data-edit="{@editable}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/contract/@caption"/>
                </h1>
                <!--<div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>-->
            </header>
            <section class="content-body">
                <fieldset class="fieldset fieldset1">
                    <div class="form-group">
                        <div class="control-label">
                            <div>Text for sign</div>
                            <button type="button" class="btn btn-primary" data-action="sign">Sign</button>
                        </div>
                        <div class="controls">
                            <textarea id="text_for_sign" class="span12">
                                <xsl:value-of select="fields/description"/>
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <div>Sign</div>
                            <button type="button" class="btn btn-primary" data-action="verify">Verify</button>
                            <p id="verify-result" style="font-size:1.5em;color:blue;"></p>
                        </div>
                        <div class="controls">
                            <textarea id="text_sign" class="span12">
                                <xsl:value-of select="fields/description"/>
                            </textarea>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="fieldset fieldset2">
                    <div class="form-group">
                        <div class="control-label">
                            <div>XML for sign</div>
                            <button type="button" class="btn btn-primary" data-action="sign-xml">Sign</button>
                        </div>
                        <div class="controls">
                            <textarea id="xml_for_sign" readonly="readonly" class="disabled span12">
                                <xsl:value-of select="fields/description"/>
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <div>XML sign</div>
                            <button type="button" class="btn btn-primary" data-action="verify-xml">Verify</button>
                            <p id="verify-xml-result" style="font-size:1.5em;color:blue;"></p>
                        </div>
                        <div class="controls">
                            <textarea id="xml_sign" class="span12" style="height:300px">
                                <xsl:value-of select="fields/description"/>
                            </textarea>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="fieldset">
                    <legend class="legend">
                        <xsl:value-of select="//captions/reg_documents/@caption"/>
                    </legend>
                    <div class="form-group">
                        <button type="button" class="btn btn-upload" data-upload="att-sign">
                            <i class="fa fa-paperclip"></i>
                            <span>
                                <xsl:value-of select="//captions/attach_file/@caption"/>
                            </span>
                        </button>
                        <button type="button" class="btn btn-primary" data-action="sign-files">
                            Sign files (Пока только один)
                        </button>
                        <div class="attachments" data-upload-files="att-sign">
                            <xsl:for-each select="fields/attachments/attachment">
                                <div class="attachments-file">
                                    <a class="file-name" data-file="{filename}" href="{url}">
                                        <xsl:value-of select="filename"/>
                                    </a>
                                    <span class="btn btn-sm btn-link btn-remove-file on-edit">
                                        <i class="fa fa-times"></i>
                                    </span>
                                </div>
                            </xsl:for-each>
                        </div>
                    </div>
                </fieldset>
            </section>
            <input type="hidden" name="fsid" value="{//fsid}"/>
        </form>
    </xsl:template>

</xsl:stylesheet>
