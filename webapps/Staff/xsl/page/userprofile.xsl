<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout">
            <xsl:with-param name="include_head">
                <script type="text/javascript">
                    <![CDATA[
                    function createCookie(name, value, days) {
                        var expires;

                        if (days) {
                            var date = new Date();
                            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                            expires = '; expires=' + date.toGMTString();
                        } else {
                            expires = '';
                        }
                        document.cookie = encodeURIComponent(name) + '=' + encodeURIComponent(value) + expires + '; path=/';
                    }

                    function changeEventHandler(el) {
                        var data = {};
                        data[el.name] = el.value;

                        var xhrArgs = {
                            cache: false,
                            type: 'POST',
                            dataType: 'json',
                            url: 'Provider?id=change-session-val-action',
                            data: data,
                            success: function(res) {
                                createCookie("activetab", "#tabs-3", 1)
                                window.location.reload(false);
                            }
                        };
                        return nb.ajax(xhrArgs);
                    }

                    function readCookie(name) {
                        var nameEQ = encodeURIComponent(name) + "=";
                        var ca = document.cookie.split(';');
                        for (var i = 0; i < ca.length; i++) {
                            var c = ca[i];
                            while (c.charAt(0) === ' ') c = c.substring(1, c.length);
                            if (c.indexOf(nameEQ) === 0) return decodeURIComponent(c.substring(nameEQ.length, c.length));
                        }
                        return null;
                    }

                    function eraseCookie(name) {
                        createCookie(name, "", -1);
                    }

                    $(document).ready(function(){
                        var tab = readCookie("activetab");
                        if(tab){
                            $(".nav").find("a[href="+tab+"]").click();
                            eraseCookie("activetab")
                        }
                    }); ]]>
                </script>
            </xsl:with-param>
        </xsl:call-template>
    </xsl:template>

    <xsl:template name="_content">
        <xsl:apply-templates select="//document[@entity = 'employee']"/>
    </xsl:template>

    <xsl:template match="document[@entity = 'employee']">
        <form name="{@entity}" action="" autocomplete="off">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="concat(//captions/employee/@caption, ' - ', fields/name)"/>
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
                            <xsl:value-of select="//captions/interface/@caption"/>
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tabs-1">
                        <fieldset class="fieldset">
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/user_name/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="fio" value="{fields/name}" class="span6"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/login_name/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="login" value="{fields/login}" class="span3"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/password/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="pwd" class="span3"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/password_confirm/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="password" name="pwd_confirm" class="span3"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/email/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="email" value="{fields/email}" class="span3"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/org_name/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="orgname" value="{fields/organization}" class="span6"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/department/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="department" value="{fields/department}" class="span6"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/position/@caption"/>
                                </div>
                                <div class="controls">
                                    <input type="text" name="post" value="{fields/position}" class="span6"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/role/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="span6">
                                        <table>
                                            <xsl:for-each select="fields/role[not(entry)]">
                                                <xsl:variable name="role" select="."/>
                                                <xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
                                                    <tr>
                                                        <td>
                                                            <xsl:value-of select="."/>
                                                            <xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
                                                                <input type="hidden" name="role" value="{.}"/>
                                                            </xsl:if>
                                                        </td>
                                                    </tr>
                                                </xsl:if>
                                            </xsl:for-each>
                                            <xsl:for-each select="fields/role/entry">
                                                <xsl:variable name="role" select="."/>
                                                <xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
                                                    <tr>
                                                        <td>
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
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-3">
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/countdocinview/@caption"/>
                            </div>
                            <div class="controls">
                                <div class="span2">
                                    <select name="pagesize" onchange="changeEventHandler(this);">
                                        <option value="10">
                                            <xsl:if test="//pagesize = '10'">
                                                <xsl:attribute name="selected" select="'selected'"/>
                                            </xsl:if>
                                            10
                                        </option>
                                        <option value="20">
                                            <xsl:if test="//pagesize = '20'">
                                                <xsl:attribute name="selected" select="'selected'"/>
                                            </xsl:if>
                                            20
                                        </option>
                                        <option value="30">
                                            <xsl:if test="//pagesize = '30'">
                                                <xsl:attribute name="selected" select="'selected'"/>
                                            </xsl:if>
                                            30
                                        </option>
                                        <option value="50">
                                            <xsl:if test="//pagesize = '50'">
                                                <xsl:attribute name="selected" select="'selected'"/>
                                            </xsl:if>
                                            50
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label">
                                <xsl:value-of select="//captions/interface_lang/@caption"/>
                            </div>
                            <div class="controls">
                                <div class="span2">
                                    <xsl:variable name="currentlang" select="/request/@lang"/>
                                    <select name="lang" onchange="changeEventHandler(this);">
                                        <xsl:for-each select="//query[@entity = 'language']/entry">
                                            <option value="{viewcontent/lang/@id}">
                                                <xsl:if test="viewcontent/lang/@id = $currentlang">
                                                    <xsl:attribute name="selected" select="'selected'"/>
                                                </xsl:if>
                                                <xsl:value-of select="viewcontent/lang"/>
                                            </option>
                                        </xsl:for-each>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label"></div>
                            <div class="controls">
                                <a href="javascript:void(0)" data-toggle-theme="theme1" class="input-placeholder">
                                    <xsl:value-of select="//captions/change_skin/@caption"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
    </xsl:template>

</xsl:stylesheet>
