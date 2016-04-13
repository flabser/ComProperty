<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>
    <xsl:import href="../templates/sharedfields.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity = 'notification']"/>
    </xsl:template>

    <xsl:template match="document[@entity]">
        <form class="form" name="{@entity}" action="" data-edit="{@editable}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/notification/@caption"/>
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
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tabs-1">
                        <fieldset class="fieldset">
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/reg_date/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="type" value="{fields/regdate}" class="span2"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/type/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="type" value="{fields/type}" class="span3"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/sender/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="type" value="{fields/sender}" class="span4"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/sendtime/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="type" value="{fields/sendingtime}" class="span2"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/recipient/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="type" value="{fields/recipient}" class="span4"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/content/@caption"/>
                                </div>
                                <div class="controls">
                                    <textarea type="text" name="body" value="{fields/body}" class="span8">
                                       <xsl:value-of select="fields/body"/>
                                    </textarea>
                                </div>
                            </div>
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
