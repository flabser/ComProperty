<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../templates/sharedfields.xsl"/>

    <xsl:template name="strategic-object-field-set">
        <section class="content-body">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active">
                    <a href="#tabs-1" role="tab" data-toggle="tab">
                        <xsl:value-of select="//captions/properties/@caption"/>
                    </a>
                </li>
                <!-- <li> <a href="#tabs-2" role="tab" data-toggle="tab"> <xsl:value-of
                    select="//captions/documents_of_title/@caption" /> </a> </li> -->
                <li>
                    <a href="#tabs-3" role="tab" data-toggle="tab">
                        <xsl:value-of select="//captions/notes/@caption"/>
                    </a>
                </li>
                <!-- <li> <a href="#tabs-4" role="tab" data-toggle="tab"> <xsl:value-of
                    select="//captions/files/@caption" /> </a> </li> -->
                <li>
                    <a href="#tabs-5" role="tab" data-toggle="tab">
                        <xsl:value-of select="//captions/additional/@caption"/>
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="tabs-1">
                    <fieldset class="fieldset">
                        <!--<xsl:if test="//document/@editable = 'false'">
                            <xsl:attribute name="disabled" select="'disabled'"/>
                        </xsl:if>-->
                        <xsl:call-template name="balanceholder-bin"/>
                        <xsl:call-template name="kof-kuf"/>
                        <xsl:call-template name="invnumber"/>
                        <xsl:call-template name="objectname"/>
                        <xsl:call-template name="description"/>
                        <xsl:call-template name="propertycode"/>
                        <xsl:call-template name="acceptancedate"/>
                        <xsl:call-template name="originalcost"/>
                        <xsl:call-template name="cumulativedepreciation"/>
                        <xsl:call-template name="impairmentloss"/>
                        <xsl:call-template name="balancecost"/>
                        <xsl:call-template name="revaluationamount"/>
                        <xsl:call-template name="afterrevaluationamount"/>
                        <xsl:call-template name="receivingreason"/>
                        <xsl:call-template name="model"/>
                        <xsl:call-template name="commissioningyear"/>
                        <xsl:call-template name="acquisitionyear"/>
                        <xsl:call-template name="isreadytouse"/>
                        <xsl:call-template name="tags"/>
                    </fieldset>
                </div>
                <div role="tabpanel" class="tab-pane" id="tabs-2">
                    <fieldset class="fieldset">
                        <!--<xsl:if test="//document/@editable = 'false'">
                            <xsl:attribute name="disabled" select="'disabled'"/>
                        </xsl:if>-->
                        <xsl:call-template name="documents-of-title"/>
                    </fieldset>
                </div>
                <div role="tabpanel" class="tab-pane" id="tabs-3">
                    <fieldset class="fieldset">
                        <!--<xsl:if test="//document/@editable = 'false'">
                            <xsl:attribute name="disabled" select="'disabled'"/>
                        </xsl:if>-->
                        <xsl:call-template name="notes"/>
                    </fieldset>
                </div>
                <div role="tabpanel" class="tab-pane" id="tabs-4">

                </div>
                <div role="tabpanel" class="tab-pane" id="tabs-5">
                    <xsl:call-template name="docinfo"/>
                </div>
            </div>
        </section>
    </xsl:template>

</xsl:stylesheet>
