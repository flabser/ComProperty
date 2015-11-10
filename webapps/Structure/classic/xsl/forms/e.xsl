<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="yes"/>

    <xsl:template name="employer">
        <div id="tabs">
            <ul
                    class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
                <li class="ui-state-default ui-corner-top">
                    <a href="#tabs-1">
                        <xsl:value-of select="document/captions/properties/@caption"/>
                    </a>
                </li>
                <xsl:if test="$editmode = 'edit'">
                    <li class="ui-state-default ui-corner-top">
                        <a href="#tabs-2"><xsl:value-of select="document/captions/applications/@caption"/></a>
                    </li>
                    <li class="ui-state-default ui-corner-top">
                        <a href="#tabs-3"><xsl:value-of select="document/captions/electronicsignature/@caption"/></a>
                    </li>
                </xsl:if>
            </ul>
            <form action="Provider" name="frm" method="post" id="frm" enctype="application/x-www-form-urlencoded">
                <div class="ui-tabs-panel" id="tabs-1">

                    <div display="block" id="property">
                        <br/>
                        <table width="100%" border="0">
                            <tr>
                                <td class="fc"><xsl:value-of select="document/captions/fullname/@caption"/> :</td>
                                <td>
                                    <input type="text" name="fullname" value="{document/fields/fullname}" size="45" class="td_editable" style="width:600px">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <tr>
                                <td class="fc"><xsl:value-of select="document/captions/shortname/@caption"/> :</td>
                                <td>
                                    <input type="text" name="shortname" value="{document/fields/shortname}" style="width:400px" class="td_editable">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                           </tr>
                            <tr>
                                <td class="fc">UserID :</td>
                                <td>
                                    <input type="text" name="userid" value="{document/fields/userid}" class="td_editable" style="width:400px">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <xsl:if test="$editmode = 'edit'">
                                <tr>
                                    <td class="fc"><xsl:value-of select="document/captions/password/@caption"/> :</td>
                                    <td>
                                        <input type="password" value="" name="password" style="width:300px" class="td_editable">
                                            <xsl:if test="$editmode != 'edit'">
                                                <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            </xsl:if>
                                        </input>
                                    </td>
                                </tr>
                            </xsl:if>
                            <tr>
                                <td class="fc"><xsl:value-of select="document/captions/phone/@caption"/> :</td>
                                <td>
                                    <input type="text" name="phone" value="{document/fields/phone}" size="20" class="td_editable" style="width:301px;">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <tr>
                                <td class="fc">e-mail :</td>
                                <td>
                                    <input type="text" name="email" value="{document/fields/email}" class="td_editable" style="width:400px">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <xsl:if test="document/fields/form = 'responsibleperson' or document/fields/form = 'naturalperson' or document/fields/form = 'legalentity' ">
                                <tr>
                                    <td class="fc" style="padding-top:5px">
                                        <xsl:value-of select="document/captions/orgname/@caption"/> :
                                    </td>
                                    <td style="padding-top:5px">
                                        <xsl:variable name="oked" select="document/fields/oked/@attrval" />
                                        <select size="1" name="orgname" style="width:611px;" class="select_editable">
                                            <xsl:if test="$editmode != 'edit'">
                                                <xsl:attribute name="class">select_noteditable</xsl:attribute>
                                                <xsl:attribute name="disabled">disabled</xsl:attribute>
                                                <option value="">
                                                    <xsl:attribute name="selected">selected</xsl:attribute>
                                                    <xsl:value-of select="document/fields/oked"/>
                                                </option>
                                            </xsl:if>
                                            <option value="48">СШ №1</option>
                                            <option value="48">СШ №2</option>
                                            <option value="48">СШ №3</option>
                                            <option value="48">СШ №4</option>
                                            <option value="48">СШ №5</option>
                                            <option value="48">СШ №6</option>
                                            <option value="48">СШ №7</option>
                                            <option value="48">СШ №8</option>
                                            <option value="48">СШ №9</option>
                                            <option value="48">СШ №10</option>
                                            <option value="48">СШ №11</option>
                                            <option value="48">ГКПБ №1</option>
                                            <option value="48">ГКПБ №4</option>
                                            <option value="48">ГКПБ №7</option>
                                            <option value="48">ЯСЛИ-САД №35</option>
                                            <option value="48">ЯСЛИ-САД №38</option>
                                            <xsl:for-each select="document/glossaries/oked/query/entry">
                                                <option value="{@docid}">
                                                    <xsl:if test="$oked = @docid">
                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="viewcontent/viewtext1"/> - <xsl:value-of select="viewcontent/viewtext3"/>
                                                </option>
                                            </xsl:for-each>
                                        </select>
                                        <xsl:if test="$editmode !='edit'">
                                            <input type="hidden" name="oked" value="{document/fields/oked/@attrval}"/>
                                        </xsl:if>
                                    </td>
                                </tr>
                            </xsl:if>
                                <tr>
                                    <td class="fc">Instant Messenger address :</td>
                                    <td>
                                        <input style="width:300px" name="instmsgaddress" type="text" class="td_editable" value="{document/fields/instmsgaddress}">
                                            <xsl:if test="$editmode != 'edit'">
                                                <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            </xsl:if>
                                        </input>
                                        <span style="vertical-align:middle;">
                                            <xsl:choose>
                                                <xsl:when test="document/fields/instmsgstatus = 'false'">
                                                    <img src="/SharedResources/img/iconset/bullet_red.png" title="Instant Messenger off"/>
                                                </xsl:when>
                                                <xsl:when test="document/fields/instmsgstatus = 'true'">
                                                    <img src="/SharedResources/img/iconset/bullet_gren.png" title="Instant Messenger on"/>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <img src="/SharedResources/img/iconset/bullet_red.png" title="Instant Messenger off"/>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fc"><xsl:value-of select="document/captions/organization/@caption"/> :
                                    </td>
                                    <td>
                                        <table id="orgtable">
                                            <tr>
                                                <td class="td_editable" style="width:300px;">
                                                    <xsl:if test="$editmode != 'edit'">
                                                        <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                        <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="document/fields/organization"/>&#xA0;
                                                </td>
                                            </tr>
                                        </table>
                                        <input type="hidden" name="organization" value="{document/fields/organization/@attrval}" size="30"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fc"><xsl:value-of select="document/captions/department/@caption"/> :
                                        <xsl:if test="$editmode = 'edit'">
                                            <a href="">
                                                <xsl:attribute name="href">javascript:dialogBoxStructure('deptpicklist','false','depid','frm', 'depttable');</xsl:attribute>
                                                <img src="/SharedResources/img/iconset/report_magnify.png"/>
                                            </a>
                                        </xsl:if>
                                    </td>

                                    <td>
                                        <table id="depttable">
                                            <tr>
                                                <td class="td_editable" style="width:300px;">
                                                    <xsl:if test="$editmode != 'edit'">
                                                        <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                        <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="document/fields/depid"/>&#xA0;
                                                </td>
                                            </tr>
                                        </table>
                                        <input type="hidden" name="depid" size="30" value="{document/fields/depid/@attrval}"/>
                                        <input type="hidden" id="depidcaption" size="30" value="Департамент"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fc"><xsl:value-of select="document/captions/post/@caption"/> :</td>
                                    <td>
                                        <select name="post" class="select_editable" style="width:312px;">
                                            <xsl:if test="$editmode != 'edit'">
                                                <xsl:attribute name="disabled">disabled</xsl:attribute>
                                                <xsl:attribute name="class">select_noteditable</xsl:attribute>
                                                <option value=" ">
                                                    <xsl:value-of select="document/fields/post"/>
                                                </option>
                                            </xsl:if>
                                            <xsl:variable name="post" select="document/fields/post/@attrval"/>
                                            <xsl:if test="$editmode = 'edit'">
                                                <option value="">
                                                    <xsl:attribute name="selected">selected</xsl:attribute>
                                                </option>
                                            </xsl:if>
                                            <xsl:for-each select="document/glossaries/post/query/entry">
                                                <option value="{@docid}">
                                                    <xsl:if test="/request/document/fields/post/@attrval = @docid">
                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="viewcontent/viewtext1"/>
                                                </option>
                                            </xsl:for-each>
                                        </select>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="fc"><xsl:value-of select="document/captions/rank/@caption"/> :</td>
                                    <td>
                                        <input type="text" name="rank" value="{document/fields/rank}" size="20" class="td_editable" style="width:301px;">
                                            <xsl:if test="$editmode != 'edit'">
                                                <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            </xsl:if>
                                        </input>
                                    </td>
                                </tr>
                                <xsl:if test="$editmode = 'edit'">
                                    <tr>
                                        <td class="fc" style="padding-top:5px;position:relative;top:0px;">
                                            <xsl:value-of select="document/captions/birthdate/@caption"/> :
                                        </td>
                                        <td style="padding-top:5px;">
                                            <input type="text" name="birthdate" maxlength="10" value="{substring(document/fields/birthdate,1,10)}" readonly="readonly" class="td_editable"  style="width:80px;">
                                                <xsl:if test="$editmode = 'edit'">
                                                    <xsl:attribute name="id">birthdate</xsl:attribute>
                                                </xsl:if>
                                                <xsl:if test="$editmode != 'edit'">
                                                    <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                </xsl:if>
                                            </input>
                                        </td>
                                    </tr>
                                    <tr>

                                        <td class="fc"><xsl:value-of select="document/captions/mailing/@caption"/> :</td>
                                        <td>
                                            <select name="sendto" class="select_editable" style="width:311px;">
                                                <xsl:if test="$editmode != 'edit'">
                                                    <xsl:attribute name="disabled">disabled</xsl:attribute>
                                                    <xsl:attribute name="class">select_noteditable</xsl:attribute>
                                                </xsl:if>
                                                <option value="1">
                                                    <xsl:if test="document/fields/sendto =1">
                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="document/captions/touserandreplacer/@caption"/>
                                                </option>
                                                <option value="2">
                                                    <xsl:if test="document/fields/sendto =2">
                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="document/captions/onlytouser/@caption"/>
                                                </option>
                                                <option value="3">
                                                    <xsl:if test="document/fields/sendto =3">
                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="document/captions/onlytoreplacer/@caption"/>
                                                </option>
                                                <option value="4">
                                                    <xsl:if test="document/fields/sendto =4">
                                                        <xsl:attribute name="selected">selected</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="document/captions/shutoff/@caption"/>
                                                </option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fc">Уволен :</td>
                                        <td>
                                            <input type="checkbox" name="fired" value="1">
                                                <xsl:if test="$editmode != 'edit'">
                                                    <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                </xsl:if>
                                                <xsl:if test="document/fields/fired = '1'">
                                                    <xsl:attribute name="checked">true</xsl:attribute>
                                                </xsl:if>
                                            </input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="fc"><xsl:value-of select="document/captions/comment/@caption"/> :</td>
                                        <td>
                                            <textarea class="textarea_editable" rows="4" value="{document/fields/comment}" name="comment" cols="45" style="width:301px;">
                                                <xsl:if test="$editmode != 'edit'">
                                                    <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                    <xsl:attribute name="class">textarea_noteditable</xsl:attribute>
                                                </xsl:if>
                                                <xsl:value-of select="document/fields/comment"/>
                                            </textarea>
                                        </td>
                                    </tr>
                                </xsl:if>
                                <tr>
                                    <td class="fc"><xsl:value-of select="document/captions/groups/@caption"/> :</td>
                                    <td>
                                        <xsl:for-each select="document/fields/group/entry">
                                            <font><xsl:value-of select="."/></font><br/>
                                        </xsl:for-each>
                                        <!--<xsl:for-each select="document/fields/group/entry">
                                            <input type="hidden" name="group" value="{.}"/>
                                        </xsl:for-each>
                                         <xsl:if test = "not(document/fields/group/@islist)">
                                            <input type="hidden" name="group" value="{document/fields/group/@attrval}"/>
                                        </xsl:if> -->
                                    </td>
                                </tr>
                        </table>
                    </div>
                    <input type="hidden" name="type" value="save"/>
                    <input type="hidden" name="id" value="employer"/>
                    <input type="hidden" name="key" value="{document/@docid}"/>
                    <input type="hidden" name="doctype" value="{document/@doctype}"/>
                    <input type="hidden" name="parentdoctype" id="parentdoctype" value="{document/@parentdoctype}"/>
                    <input type="hidden" name="parentdocid" id="parentdocid" value="{document/@parentdocid}"/>
                </div>
                <xsl:if test="$editmode = 'edit'">
                    <div class="ui-tabs-panel" id ="tabs-2">
                        <div display="block" id="applications">
                            <br/>

                            <table border="0" style="margin-left:50px; margin-top:30px;width:645px; border-collapse:collapse" class="table_editable">
                                <xsl:if test="not(/request/document/fields/issupervisor='1') and document/@status != 'new'">
                                    <xsl:attribute name="class">table_noteditable</xsl:attribute>
                                </xsl:if>
                                <xsl:for-each select="document/glossaries/application/entry">
                                    <xsl:variable name="app" select="apptype"/>
                                    <tr style="background:#EDEDED">
                                        <th colspan="3" style="text-align:left">
                                            <div style="width:290px; display:inline-block; text-align:right">
                                                <input type="checkbox" id="allchbox" value="{$app}" name="enabledapps" autocomplete="off">
                                                    <xsl:attribute name="onClick">javascript:checkAll(this, '<xsl:value-of select="$app"/>')</xsl:attribute>
                                                    <xsl:if test="not(/request/document/fields/issupervisor='1') and document/@status != 'new'">
                                                        <xsl:attribute name="disabled">disabled</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:if test="/request/document/fields/apps/entry = $app">
                                                        <xsl:attribute name="checked">checked</xsl:attribute>
                                                    </xsl:if>
                                                </input>
                                            </div>
                                            <font style="vertical-align:2px; margin-left:5px">
                                                <xsl:value-of select="$app"/>
                                            </font>
                                            <input type="hidden" name="loginmode" value="2"/>
                                        </th>
                                    </tr>
                                    <xsl:for-each select="/request/document/glossaries/roles/entry[app = $app]">

                                        <tr>
                                            <xsl:attribute name='class'><xsl:value-of select="$app"/></xsl:attribute>
                                            <td>
                                                <input type="checkbox" value="{name}#{$app}" name='role'>
                                                    <xsl:attribute name='class' select="$app"/>
                                                    <xsl:if test="/request/document/fields/role/entry[appid=$app]/name = name">
                                                        <xsl:attribute name="checked">checked</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:if test="not(/request/document/fields/issupervisor='1') and document/@status != 'new'">
                                                        <xsl:attribute name="disabled">disabled</xsl:attribute>
                                                    </xsl:if>
                                                    <br/>
                                                </input>
                                            </td>
                                            <td style="text-align:left">
                                                <xsl:if test="name = 'supervisor'">
                                                    <xsl:attribute name="style">color:red</xsl:attribute>
                                                </xsl:if>
                                                <xsl:value-of select="name"/>
                                                <font style="margin-left:5px; color:#aaaaaa">
                                                    <xsl:value-of select="description"/>
                                                </font>
                                            </td>
                                        </tr>
                                    </xsl:for-each>
                                    <tr>
                                        <td height="10px"></td>
                                    </tr>
                                </xsl:for-each>
                                <tr style= "text-align:center;background-color:#ededed">
                                    <td style="text-align:left; width:25px">
                                    </td>
                                    <th colspan="2">System</th>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="checkbox" value="supervisor" name='role' disabled="disabled">
                                            <xsl:if test="/request/document/fields/role/entry = 'supervisor'">
                                                <xsl:attribute name="checked">checked</xsl:attribute>
                                            </xsl:if>
                                            <br/>
                                        </input>
                                    </td>
                                    <td style="text-align:left">
                                        <xsl:attribute name="style">color:red</xsl:attribute>
                                        supervisor
                                        <font style="margin-left:5px; color:#aaaaaa">
                                        </font>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="ui-tabs-panel" id ="tabs-3" >
                        <div display="block" id="publickey">
                            <br/>
                            <table border="0" style="width:100%;">
                                <tr>
                                    <xsl:choose>
                                        <xsl:when test="/request/document/fields/publickey!= ''">
                                            <td class="fc"><xsl:value-of select="document/captions/publickey/@caption"/>:&#xA0;</td>
                                            <td>
                                                <input type="text" name="publickey" disabled="disabled">
                                                    <xsl:attribute name="value" select="/request/document/fields/publickey"/>
                                                </input>
                                            </td>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td style ="padding-left:70px">
                                                <xsl:value-of select="document/captions/elsignstatus/@caption"/>
                                            </td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </tr>
                            </table>
                        </div>
                    </div>
                </xsl:if>
            </form>
        </div>
    </xsl:template>
</xsl:stylesheet>