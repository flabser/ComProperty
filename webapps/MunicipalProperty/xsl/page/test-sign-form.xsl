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
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                    <a class="btn" onClick="knca.init()">knca init</a>
                    <a class="btn" onClick="knca.signPlainData('hello world')">knca signPlainData('hello world')</a>
                </div>
            </header>
            <section class="content-body">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/contract_name/@caption"/>
                        </div>
                        <div class="controls">
                            <textarea name="description" class="span8">
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
            <input type="hidden" name="fsid" value="{//formsesid}"/>
        </form>
    </xsl:template>

</xsl:stylesheet>
