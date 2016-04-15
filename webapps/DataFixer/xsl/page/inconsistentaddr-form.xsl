<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>
    <xsl:import href="../templates/sharedfields.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity != '']"/>
    </xsl:template>

    <xsl:template match="document[@entity]">
        <form class="form" name="{@entity}" action="" enctype="application/x-www-form-urlencoded"
              data-edit="{@editable}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:variable name="kufName" select="lower-case(fields/kuf/@name)"/>
                    <xsl:value-of select="//captions/*[lower-case(name()) = $kufName]/@caption"/>
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
                        <a href="#tabs-6" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/additional/@caption"/>
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tabs-1">
                        <fieldset class="fieldset">
                            <div class="fieldset1">
                                <xsl:call-template name="balanceholder-bin"/>
                                <xsl:call-template name="kof-kuf"/>
                                <xsl:call-template name="invnumber"/>
                                <xsl:call-template name="objectname"/>
                                <xsl:call-template name="description"/>                              
                            </div>
                           
                        </fieldset>
                        <xsl:call-template name="address"/>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-2">
                        <fieldset class="fieldset">
                            <xsl:call-template name="documents-of-title"/>
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-3">
                        <fieldset class="fieldset">
                            <xsl:call-template name="notes"/>
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-4">
                        <fieldset class="fieldset">
                            <xsl:call-template name="upload-files">
                                <xsl:with-param name="input-name" select="'reg-files'"/>
                            </xsl:call-template>
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-5">
                        <div class="view" data-load="orders"></div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-6">
                        <xsl:call-template name="docinfo"/>
                    </div>
                </div>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
