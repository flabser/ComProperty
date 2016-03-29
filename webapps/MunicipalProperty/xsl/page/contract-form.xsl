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
                </div>
            </header>
            <section class="content-body">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active">
                        <a href="#tabs-1" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/properties/@caption"/>
                        </a>
                    </li>
                    <li>
                        <a href="#tabs-3" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/additional/@caption"/>
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tabs-1">
                        <fieldset class="fieldset">
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/reg_number/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="regnumber" value="{fields/regnumber}" class="span4"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/order/@caption"/>
                                </div>
                                <div class="controls">
                                    <a class="input-placeholder" href="{fields/order/url}">
                                        <xsl:value-of select="fields/order/ordername"/>
                                    </a>
                                    <input type="hidden" name="orderid" value="{fields/order/@docid}"/>
                                </div>
                            </div>
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
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/reg_date/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="date" name="regdate" value="{fields/regdate}" class="span2"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/expiry_date/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="date" name="expired" value="{fields/expired}" class="span2"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/status/@caption"/>
                                </div>
                                <div class="controls">
                                    <xsl:apply-templates select="//constants[@entity = 'contractstatus']/entry"
                                                         mode="constants">
                                        <xsl:with-param name="select" select="fields/contractstatus"/>
                                        <xsl:with-param name="type" select="'radio'"/>
                                    </xsl:apply-templates>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="fieldset">
                            <legend class="legend">
                                <xsl:value-of select="//captions/reg_documents/@caption"/>
                            </legend>
                            <xsl:call-template name="upload-files">
                                <xsl:with-param name="input-name" select="'reg-files'"/>
                            </xsl:call-template>
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-3">
                        <xsl:call-template name="docinfo"/>
                    </div>
                </div>
            </section>
            <input type="hidden" name="fsid" value="{//formsesid}"/>
        </form>
    </xsl:template>

</xsl:stylesheet>
