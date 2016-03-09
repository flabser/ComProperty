<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template match="entry" mode="roles">
        <xsl:param name="select"/>
        <div>
            <label class="btn btn-sm">
                <input type="checkbox" name="roles" value="{@id}">
                    <xsl:if test="contains($select, viewcontent/name)">
                        <xsl:attribute name="checked" select="checked"/>
                    </xsl:if>
                    <span>
                        <xsl:value-of select="viewcontent/name"/>
                    </span>
                </input>
            </label>
        </div>
    </xsl:template>

    <xsl:template match="entry" mode="select_options">
        <xsl:param name="select"/>
        <option value="{@id}">
            <xsl:if test="@id = $select">
                <xsl:attribute name="selected" select="'selected'"/>
            </xsl:if>
            <xsl:value-of select="."/>
        </option>
    </xsl:template>

    <xsl:template name="_content">
        <form name="{//document/@entity}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/employee/@caption"/>
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
                            ИИН <xsl:value-of select="//captions/iin/@caption"/>
                        </div>
                        <div class="controls">
                            <input type="text" name="name" value="{//fields/iin}" class="span7" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/organization/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="controls">
                                <select name="organization" class="span4" autocomplete="off">
                                    <option value="null"></option>
                                    <xsl:apply-templates select="//query[@entity = 'organization']/entry"
                                                         mode="select_options">
                                        <xsl:with-param name="select" select="//fields/organizationid"/>
                                    </xsl:apply-templates>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/department/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="controls">
                                <div class="controls">
                                    <select name="department" class="span4" autocomplete="off">
                                        <option value="null"></option>
                                        <xsl:apply-templates select="//query[@entity = 'department']/entry"
                                                             mode="select_options">
                                            <xsl:with-param name="select" select="//fields/departmentid"/>
                                        </xsl:apply-templates>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/position/@caption"/>
                        </div>
                        <div class="controls">
                            <select name="position" class="span4" autocomplete="off">
                                <option value="null"></option>
                                <xsl:apply-templates select="//query[@entity = 'position']/entry"
                                                     mode="select_options">
                                    <xsl:with-param name="select" select="//fields/positionid"/>
                                </xsl:apply-templates>
                            </select>
                        </div>
                    </div>
                    <fieldset class="fieldset">
                        <legend class="legend legend-address">
                            Системные поля
                        </legend>
                        <div class="form-group">
                            <div class="control-label">
                            </div>
                            <div class="controls">
                                <label class="btn btn-sm">
                                    <input type="checkbox" name="reguser" id="reguser" autocomplete="off">
                                        <span>
                                           Зарегистрировать учетную запись
                                        </span>
                                    </input>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/login_name/@caption"/>
                            </div>
                            <div class="controls">
                                <input type="text" name="login_name" value="{//fields/login_name}" class="span7 regfields" autocomplete="off" disabled="disabled"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/email/@caption"/>
                            </div>
                            <div class="controls">
                                <input type="email" name="email" value="{//fields/email}" class="span7 regfields" autocomplete="off" disabled="disabled"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/password/@caption"/>
                            </div>
                            <div class="controls">
                                <input type="password" name="password" value="{//fields/password}" class="span7 regfields" autocomplete="off" disabled="disabled"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/reenterpassword/@caption"/>
                            </div>
                            <div class="controls">
                                <input type="password" name="reenterpassword" value="{//fields/reenterpassword}" class="span7 regfields" autocomplete="off" disabled="disabled"/>
                            </div>
                        </div>
                        <!--<div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/roles/@caption"/>
                            </div>
                            <div class="controls">
                                <input type="text" name="roles" value="{//fields/roles}" class="span7"/>
                            </div>
                        </div>-->
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/roles/@caption"/>
                            </div>
                            <div class="controls">
                                <xsl:apply-templates select="//query[@entity = 'role']/entry" mode="roles">
                                    <xsl:with-param name="select" select="//fields/roles"/>
                                </xsl:apply-templates>
                            </div>
                        </div>
                    </fieldset>
                </fieldset>

                <input type="hidden" name="id" value="{/request/@id}"/>
                <input type="hidden" name="docid" value="{/request/page/response/content/document[@entity = 'employee']/@docid}"/>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
