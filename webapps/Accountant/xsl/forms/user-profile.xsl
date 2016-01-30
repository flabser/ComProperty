<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:variable name="editmode" select="//document/@editmode"/>

        <form name="{//document/@entity}">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="concat(//captions/employer/@caption, ' - ', //fields/fullname)"/>
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
                        <a href="#tabs-2" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/attachments/@caption"/>
                        </a>
                    </li>
                    <li>
                        <a href="#tabs-3" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/interface/@caption"/>
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tabs-1">
                        <fieldset class="fieldset">
                            <xsl:if test="//fields/form = 'responsibleperson'">
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/fio/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="fio" value="{//fields/fullname}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/login/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="login" value="{//fields/userid}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/password/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="password" value="{//fields/password}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/reenterpassword/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="reenterpassword" value="{//fields/reenterpassword}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/phone/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="phone" value="{//fields/phone}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/email/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="email" value="{//fields/email}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/email/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="email" value="{//fields/email}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/orgname/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="orgname" value="{//fields/orgname}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/orgname/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="orgname" value="{//fields/orgname}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                            </xsl:if>
                            <xsl:if test="//fields/form != 'responsibleperson'">
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/department/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="department" value="{//fields/department}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/postid/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="post" value="{//fields/post}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/shortname/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="shortname" value="{//fields/shortname}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/fullname/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="fullname" value="{//fields/fullname}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="'ID'"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="userid" value="{//fields/userid}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/oldpassword/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="oldpwd" value="{//fields/oldpwd}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/newpassword/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="newpassword" value="{//fields/newpassword}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/repeatnewpassword/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="repeatnewpassword"
                                                   value="{//fields/repeatnewpassword}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="'Instant Messenger address'"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="instmsgaddress" value="{//fields/instmsgaddress}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/email/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="email" value="{//fields/email}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/role/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <table>
                                                <xsl:for-each select="//fields/role[not(entry)]">
                                                    <xsl:variable name="role" select="."/>
                                                    <xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
                                                        <tr>
                                                            <td style="width:500px;" class="td_noteditable">
                                                                <xsl:if test="//document/@editmode != 'edit'">
                                                                    <xsl:attribute name="class">
                                                                        td_noteditable
                                                                    </xsl:attribute>
                                                                </xsl:if>
                                                                <xsl:value-of select="."/>

                                                                <xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
                                                                    <input type="hidden" name="role" value="{.}"/>
                                                                </xsl:if>
                                                            </td>
                                                        </tr>
                                                    </xsl:if>
                                                </xsl:for-each>
                                                <xsl:for-each select="//fields/role/entry">
                                                    <xsl:variable name="role" select="."/>
                                                    <xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
                                                        <tr>
                                                            <td style="width:500px;" class="td_noteditable">
                                                                <xsl:if test="//document/@editmode != 'edit'">
                                                                    <xsl:attribute name="class">
                                                                        td_noteditable
                                                                    </xsl:attribute>
                                                                </xsl:if>
                                                                <xsl:value-of select="."/>

                                                                <xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
                                                                    <input type="hidden" name="role" value="{.}"/>
                                                                </xsl:if>
                                                            </td>
                                                        </tr>
                                                    </xsl:if>
                                                </xsl:for-each>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/group/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <table>
                                            <xsl:for-each select="//fields/group/entry">
                                                <tr>
                                                    <td style="width:500px" class="td_noteditable">
                                                        <xsl:if test="//document/@editmode != 'edit'">
                                                            <xsl:attribute name="class">td_noteditable
                                                            </xsl:attribute>
                                                        </xsl:if>
                                                        <xsl:value-of select="."/>
                                                    </td>
                                                </tr>
                                            </xsl:for-each>
                                        </table>
                                    </div>
                                </div>
                            </xsl:if>
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-2">
                        <!--<xsl:call-template name="attach"/>-->
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-3">
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/countdocinview/@caption"/>
                            </div>
                            <div class="controls">
                                <div class="field-wrapper col-md-6">
                                    <select name="pagesize" class="form-control">
                                        <option id="countdocinview10">
                                            <xsl:if test="//fields/countdocinview = '10'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            10
                                        </option>
                                        <option id="countdocinview20">
                                            <xsl:if test="//fields/countdocinview = '20'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            20
                                        </option>
                                        <option id="countdocinview30">
                                            <xsl:if test="//fields/countdocinview = '30'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            30
                                        </option>
                                        <option id="countdocinview50">
                                            <xsl:if test="//fields/countdocinview = '50'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            50
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/refreshperiod/@caption"/>
                            </div>
                            <div class="controls">
                                <div class="field-wrapper col-md-6">
                                    <select name="refresh" class="form-control">
                                        <option id="3" value="3">
                                            <xsl:if test="//fields/refresh = '3'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            3 <xsl:value-of select="//captions/min/@caption"/>.
                                        </option>
                                        <option id="5" value="5">
                                            <xsl:if test="//fields/refresh = '5' ">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            5 <xsl:value-of select="//captions/min/@caption"/>.
                                        </option>
                                        <option id="10" value="10">
                                            <xsl:if test="//fields/refresh = '10' ">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            10 <xsl:value-of select="//captions/min/@caption"/>.
                                        </option>
                                        <option id="15" value="15">
                                            <xsl:if test="//fields/refresh = '15' ">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            15 <xsl:value-of select="//captions/min/@caption"/>.
                                        </option>
                                        <option id="20" value="20">
                                            <xsl:if test="//fields/refresh = '20' ">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            20 <xsl:value-of select="//captions/min/@caption"/>.
                                        </option>
                                        <option id="30" value="30">
                                            <xsl:if test="//fields/refresh = '30' ">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            30 <xsl:value-of select="//captions/min/@caption"/>.
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/lang/@caption"/>
                            </div>
                            <div class="controls">
                                <div class="field-wrapper col-md-6">
                                    <select name="lang" class="form-control">
                                        <xsl:variable name='chinese' select="//captions/chinese/@caption"/>
                                        <xsl:variable name='currentlang' select="../@lang"/>
                                        <xsl:for-each select="//glossaries/langs/entry">
                                            <option id="{id}" value="{id}">
                                                <xsl:if test="$currentlang = id">
                                                    <xsl:attribute name="selected">selected
                                                    </xsl:attribute>
                                                </xsl:if>
                                                <xsl:if test="id = 'CHN'">
                                                    <xsl:value-of select="$chinese"/>
                                                </xsl:if>
                                                <xsl:if test="id = 'KAZ'">
                                                    Қазақша
                                                </xsl:if>
                                                <xsl:if test="id != 'CHN' and id != 'KAZ'">
                                                    <xsl:value-of select="name"/>
                                                </xsl:if>
                                            </option>
                                        </xsl:for-each>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <input type="hidden" name="id" value="userprofile"/>
        </form>
        <form action="Uploader" name="upload" method="post" enctype="multipart/form-data">
            <input type="hidden" name="type" value="employer_uploadfield"/>
            <input type="hidden" name="formsesid" value="{formsesid}"/>
            <div display="block" id="att" style="width:100%">
                <!--<xsl:call-template name="attach"/>-->
            </div>
        </form>
    </xsl:template>

</xsl:stylesheet>
