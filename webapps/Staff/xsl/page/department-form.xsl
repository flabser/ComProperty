<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity = 'department']"/>
    </xsl:template>

    <xsl:template match="document[@entity]">
        <form name="{@entity}" action="">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/department/@caption"/>
                </h1>
                <div class="content-actions">
                    <xsl:apply-templates select="//actionbar"/>
                </div>
            </header>
            <section class="content-body">
                <fieldset class="fieldset">
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/organization/@caption"/>
                        </div>
                        <div class="controls">
                            <select name="organization" class="span7" required="required" autocomplete="off">
                                <option value=""></option>
                                <xsl:apply-templates select="fields/organization" mode="selected_options"/>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/name/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="name" value="{fields/name}" class="span7" required="required"
                                   autofocus="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/type/@caption"/>
                        </div>
                        <div class="controls">
                            <select name="departmenttype" class="span7">
                                <option value=""></option>
                                <xsl:apply-templates select="//constants[@entity = 'departmenttype']/entry"
                                                     mode="departmenttype_options">
                                    <xsl:with-param name="select" select="fields/departmenttype"/>
                                </xsl:apply-templates>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/description/@caption"/>
                        </div>
                        <div class="controls">
                            <textarea name="description" class="span7">
                                <xsl:value-of select="fields/description"/>
                            </textarea>
                        </div>
                    </div>
                </fieldset>
            </section>
        </form>
    </xsl:template>

    <xsl:template match="*" mode="selected_options">
        <option value="{@id}" selected="selected">
            <xsl:value-of select="."/>
        </option>
    </xsl:template>

    <xsl:template match="entry" mode="departmenttype_options">
        <xsl:param name="select"/>
        <option value="{@attrval}">
            <xsl:if test="@attrval = $select">
                <xsl:attribute name="selected" select="'selected'"/>
            </xsl:if>
            <xsl:value-of select="."/>
        </option>
    </xsl:template>

</xsl:stylesheet>
