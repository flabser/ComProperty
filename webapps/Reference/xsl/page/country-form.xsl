<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <form name="{//document/@entity}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/country/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/name/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="name" value="{//fields/name}" class="span7" required="required"
                                   autofocus="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/code/@caption"/>
                        </div>
                        <div class="controls">
                            <select name="code" class="span2" required="required">
                                <xsl:apply-templates select="//constants[@entity = 'countrycode']/entry"
                                                     mode="select_options">
                                    <xsl:with-param name="selected" select="//fields/code"/>
                                </xsl:apply-templates>
                            </select>
                        </div>
                    </div>
                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
               <input type="hidden" name="docid" value="{/request/page/response/content/document[@entity = 'country']/@docid}"/>
            </section>
        </form>
    </xsl:template>

    <xsl:template match="entry" mode="select_options">
        <xsl:param name="selected"/>

        <option value="{@attrval}">
            <xsl:if test="@attrval = $selected">
                <xsl:attribute name="selected" select="'selected'"/>
            </xsl:if>
            <xsl:value-of select="text()"/>
        </option>
    </xsl:template>

</xsl:stylesheet>