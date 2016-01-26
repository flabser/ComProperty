<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:variable name="editmode" select="/request/document/@editmode"/>
    <xsl:variable name="status" select="document/@status"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout">
            <xsl:with-param name="active_aside_id" select="'furniture-view'"/>
        </xsl:call-template>
    </xsl:template>

    <xsl:template name="_content">
        <form action="Provider" method="post" enctype="application/x-www-form-urlencoded">
            <header class="content-header">
                <h1 class="header-title">
                    <xsl:value-of select="//captions/furniture/@caption"/>
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
                            <xsl:value-of select="//captions/documentsoftitle/@caption"/>
                        </a>
                    </li>
                    <li>
                        <a href="#tabs-3" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/note/@caption"/>
                        </a>
                    </li>
                    <li>
                        <a href="#tabs-4" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/files/@caption"/>
                        </a>
                    </li>
                    <li>
                        <a href="#tabs-5" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/additional/@caption"/>
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tabs-1">
                        <br/>
                        <table width="100%" border="0">

                            <xsl:if test="document/fields/balanceholder !=''">
                                <!-- поле "Балансодержатель" -->
                                <tr>
                                    <td class="fc" style="padding-top:5px">
                                        <font style="vertical-align:top">
                                            <xsl:value-of select="document/captions/balanceholder/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
                                        </font>
                                        <xsl:if test="$editmode = 'edit'">
                                            <a>
                                                <xsl:attribute name="href">javascript:dialogBoxStructure('balanceholder','false','balanceholder','frm', 'balanceholdertbl');</xsl:attribute>
                                                <img src="/SharedResources/img/iconset/report_magnify.png"/>
                                            </a>
                                        </xsl:if>
                                    </td>
                                    <td style="padding-top:5px">
                                        <table id="balanceholdertbl" style="border-spacing:0px 3px; margin-top:-3px">
                                            <tr>
                                                <td style="width:600px;" class="td_editable">
                                                    <xsl:if test="$editmode != 'edit'">
                                                        <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:value-of select="document/fields/balanceholdername"/>&#xA0;
                                                    <span style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'><font><xsl:value-of select="document/fields/corr/@attrval"/></font></span>
                                                </td>
                                            </tr>
                                        </table>
                                        <input type="hidden" value="{document/fields/balanceholder}" id="balanceholder" name="balanceholder"/>
                                        <input type="hidden" value="{document/captions/balanceholder/@caption}" id="balanceholdercaption"/>
                                    </td>
                                </tr>
                                <!-- 	Наименование Гос. учреждения (каз.) -->
                                <tr>
                                    <td class="fc">
                                        <xsl:value-of select="document/captions/namekaz/@caption"/>&#xA0;:
                                    </td>
                                    <td>
                                        <input type="text" value="{document/fields/balanceholdernamekaz}" style="width:500px;" class="td_editable" name="balanceholdernamekaz">
                                            <xsl:if test="$editmode != 'edit'">
                                                <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                            </xsl:if>
                                        </input>
                                    </td>
                                </tr>
                                <!-- БИН Коммунаульных Гос. учреждений -->
                                <tr>
                                    <td class="fc">
                                        <xsl:value-of select="document/captions/bin/@caption"/>&#xA0;:
                                    </td>
                                    <td>
                                        <input type="text" value="{document/fields/bin}" style="width:500px;" class="td_editable" name="bin">
                                            <xsl:if test="$editmode != 'edit'">
                                                <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                            </xsl:if>
                                        </input>
                                    </td>
                                </tr>
                            </xsl:if>
                            <!--<xsl:call-template name="kofkuf"/>-->
                            <!-- 	Инвентарный номер -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/invnumber/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/invnumber}" style="width:500px;" class="td_editable" name="invnumber" title="{document/captions/invnumbertitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- Наименование объекта -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/objectname/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/>&#xA0;:
                                </td>
                                <td>
                                    <textarea name="objectname" rows="3" class="textarea_editable" style="width:700px" title="{document/captions/objectnametitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">textarea_noteditable</xsl:attribute>
                                        </xsl:if>
                                        <xsl:if test="document/@editmode = 'edit'">
                                            <xsl:attribute name="onfocus">fieldOnFocus(this)</xsl:attribute>
                                            <xsl:attribute name="onblur">fieldOnBlur(this)</xsl:attribute>
                                        </xsl:if>
                                        <xsl:value-of select="document/fields/objectname"/>
                                    </textarea>
                                </td>
                            </tr>
                            <!-- 	Описание объекта -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/description/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
                                </td>
                                <td>
                                    <textarea name="description" rows="3" class="textarea_editable" style="width:700px" title="{document/captions/descriptiontitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">textarea_noteditable</xsl:attribute>
                                        </xsl:if>
                                        <xsl:if test="document/@editmode = 'edit'">
                                            <xsl:attribute name="onfocus">fieldOnFocus(this)</xsl:attribute>
                                            <xsl:attribute name="onblur">fieldOnBlur(this)</xsl:attribute>
                                        </xsl:if>
                                        <xsl:value-of select="document/fields/description"/>
                                    </textarea>
                                </td>
                            </tr>
                            <!-- 	Код права на имущество -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/propertycode/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <xsl:variable name="propertycode" select="document/fields/propertycode/@attrval"/>
                                    <select size="1"  style="width:611px;" class="select_noteditable" disabled="disabled">
                                        <option value="0">
                                            <xsl:if test="not(document/fields/propertycode) or $propertycode = '0'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                        </option>
                                        <option value="">
                                            <xsl:if test="$propertycode != ''">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            <xsl:value-of select="document/fields/propertycode"/>
                                        </option>
                                    </select>
                                    <input type="hidden" name="propertycode" value="{document/fields/propertycode/@attrval}">
                                        <xsl:if test="not(document/fields/propertycode) or $propertycode = '0'">
                                            <xsl:attribute name="value">0</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Дата принятия на баланс -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/acceptancedate/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" name="acceptancedate" maxlength="10" autocomplete="off" readonly="readonly" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/acceptancedate,1,10)}">
                                        <xsl:if test="$editmode = 'edit'">
                                            <xsl:attribute name="id">acceptancedate</xsl:attribute>
                                        </xsl:if>
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- Поле "Область" -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/region/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <xsl:variable name="region" select="document/fields/region/@attrval" />
                                    <select size="1" name="region" style="width:611px;" class="select_editable">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">select_noteditable</xsl:attribute>
                                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                                            <option value="">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                <xsl:value-of select="document/fields/region"/>
                                            </option>
                                        </xsl:if>
                                        <xsl:for-each select="document/glossaries/region/query/entry">
                                            <option value="{@docid}">
                                                <xsl:if test="$region = @docid or (lower-case(viewcontent/viewtext1) = 'алматы г.а.' and $status = 'new')">
                                                    <xsl:attribute name="selected">selected</xsl:attribute>
                                                </xsl:if>
                                                <xsl:value-of select="viewcontent/viewtext1"/>
                                            </option>
                                        </xsl:for-each>
                                    </select>
                                    <xsl:if test="$editmode !='edit'">
                                        <input type="hidden" name="region" value="{document/fields/region/@attrval}"/>
                                    </xsl:if>
                                </td>
                            </tr>
                            <!-- Поле "Город" -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/city/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <xsl:variable name="city" select="document/fields/city/@attrval" />
                                    <select size="1" name="city" style="width:611px;" class="select_editable">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">select_noteditable</xsl:attribute>
                                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                                            <option value="">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                <xsl:value-of select="document/fields/city"/>
                                            </option>
                                        </xsl:if>
                                        <xsl:for-each select="document/glossaries/city/query/entry">
                                            <option value="{@docid}">
                                                <xsl:if test="$city = @docid">
                                                    <xsl:attribute name="selected">selected</xsl:attribute>
                                                </xsl:if>
                                                <xsl:value-of select="viewcontent/viewtext1"/>
                                            </option>
                                        </xsl:for-each>
                                    </select>
                                    <xsl:if test="$editmode !='edit'">
                                        <input type="hidden" name="city" value="{document/fields/city/@attrval}"/>
                                    </xsl:if>
                                </td>
                            </tr>
                            <!-- Поле "Район" -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/district/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <xsl:variable name="district" select="document/fields/district/@attrval" />
                                    <select size="1" name="district" style="width:611px;" class="select_editable">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">select_noteditable</xsl:attribute>
                                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                                            <option value="">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                                <xsl:value-of select="document/fields/district"/>
                                            </option>
                                        </xsl:if>
                                        <xsl:for-each select="document/glossaries/district/query/entry">
                                            <option value="{@docid}">
                                                <xsl:if test="$district = @docid">
                                                    <xsl:attribute name="selected">selected</xsl:attribute>
                                                </xsl:if>
                                                <xsl:value-of select="viewcontent/viewtext1"/>
                                            </option>
                                        </xsl:for-each>
                                    </select>
                                    <xsl:if test="$editmode !='edit'">
                                        <input type="hidden" name="district" value="{document/fields/district/@attrval}"/>
                                    </xsl:if>
                                </td>
                            </tr>
                            <!-- Поле "Улица" -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/street/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <input type="text" id="input_street" style="width:500px; padding:2px;" name="input_street" value="{document/fields/street}" class="td_editable" autocomplete="off">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                    <input type="hidden" id="street" name="street" value="{document/fields/street/@attrval}"/>
                                </td>
                            </tr>
                            <!-- Поле "Дом" -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/home/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <input type="text" style="width:200px; padding:2px;" name="home" value="{document/fields/home}" class="td_editable" autocomplete="off">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- Поле "Адрес" -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/address/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <input type="text" style="width:500px; padding:2px;" name="address" value="{document/fields/address}" class="td_editable" autocomplete="off">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Количество -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/amount/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/amount}" style="width:90px;" class="td_editable" name="amount" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" title="{document/captions/amounttitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Первоначальная стоимость -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/originalcost/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/originalcost}" style="width:90px;" class="td_editable" name="originalcost" autocomplete="off" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{document/captions/originalcosttitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Балансовая стоимость -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/balancecost/@caption"/> :
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/balancecost}" style="width:90px;" class="td_editable" name="balancecost" autocomplete="off" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{document/captions/balancecosttitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Оценочная стоимость-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/estimatedcost/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/estimatedcost}" style="width:90px;" class="td_editable" name="estimatedcost" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{document/captions/estimatedcosttitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Остаточная стоимость-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/residualcost/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/residualcost}" style="width:90px;" class="td_editable" name="residualcost" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{document/captions/residualcosttitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Предельная норма амортизации -->
                            <tr>
                                <td class="fc" style="padding-top:5px">
                                    <xsl:value-of select="document/captions/limitdepreciation/@caption"/> :
                                </td>
                                <td style="padding-top:5px">
                                    <input type="text" value="{document/fields/limitdepreciation}" style="width:90px;" class="td_editable" name="limitdepreciation" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{document/captions/limitdepreciationtitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Основание поступления в Гос. собственность-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/receiptbasisingproperty/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/receiptbasisingproperty}" style="width:500px;" class="td_editable" name="receiptbasisingproperty" title="{document/captions/receiptbasisingpropertytitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Основание поступления на баланс-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/receiptbasisinbalance/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/receiptbasisinbalance}" style="width:500px;" class="td_editable" name="receiptbasisinbalance" title="{document/captions/receiptbasisinbalancetitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Основание снятия с баланса -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/orderofremovalfrombalance/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/orderofremovalfrombalance}" style="width:500px;" class="td_editable" name="orderofremovalfrombalance" title="{document/captions/orderofremovalfrombalancetitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- Модель -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/model/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/model}" style="width:500px;" class="td_editable" name="model" title="{document/captions/modeltitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Цвет -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/color/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/color}" style="width:500px;" class="td_editable" name="color"  title="{document/captions/colortitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Ширина -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/width/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/width}" style="width:80px;" class="td_editable" name="width" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" title="{document/captions/widthtitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input> мм
                                </td>
                            </tr>
                            <!-- 	Высота -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/height/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/height}" style="width:80px;" class="td_editable" name="height" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" required="required" title="{document/captions/heighttitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input> мм
                                </td>
                            </tr>
                            <!-- 	Глубина -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/depth/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/depth}" style="width:80px;" class="td_editable" name="depth" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" title="{document/captions/depthtitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input> мм
                                </td>
                            </tr>
                            <!-- 	Год выпуска -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/yearrelease/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <xsl:variable name="year" select="format-date(current-date(),'[Y0001]')"/>
                                    <input type="text" value="{document/fields/yearrelease}" style="width:80px;" class="td_editable" name="yearrelease" autocomplete="off" pattern="^18\d\d$|^19\d\d$|^20[{substring($year,3,1)}][0-{substring($year,4,1)}]$|^200[0-9]$" onkeyup="javascript:validationyearfield(this)" title="{document/captions/yearreleasetitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- 	Изменение первоначальной стоимости-->
                            <tr>
                                <td colspan="2">
                                    <b><xsl:value-of select="document/captions/сhangeoriginalcost/@caption"/></b>
                                </td>
                            </tr>
                            <!-- 	Ремонт-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/repair/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <select value="{document/fields/repair}" style="width:200px;" class="select_editable" name="repair">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="class">select_noteditable</xsl:attribute>
                                            <xsl:attribute name="disabled">disabled</xsl:attribute>
                                        </xsl:if>
                                        <option value="1">
                                            <xsl:if test="document/fields/repair != '1' and document/fields/repair != '2'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                        </option>
                                        <option value="1">
                                            <xsl:if test="document/fields/repair = '1'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            <xsl:value-of select="document/captions/reconstruction/@caption"/>
                                        </option>
                                        <option value="2">
                                            <xsl:if test="document/fields/repair = '2'">
                                                <xsl:attribute name="selected">selected</xsl:attribute>
                                            </xsl:if>
                                            <xsl:value-of select="document/captions/capital/@caption"/>
                                        </option>
                                    </select>
                                    <xsl:if test="$editmode !='edit'">
                                        <input type="hidden" name="repair" value="{document/fields/repair}"/>
                                    </xsl:if>
                                </td>
                            </tr>
                            <!-- Обесценение -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/depreciating/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/depreciating}" style="width:40px;" class="td_editable" name="depreciating" pattern="^100$||^[0-9]?[0-9]?[.,]?[0-9]?[0-9]$" onkeyup="javascript:validationpercentfield(this)" title="{document/captions/depreciatingtitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>%
                                </td>
                            </tr>
                            <!-- 	Накопленная амортизация-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/cumulativedepreciation/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/cumulativedepreciation}" style="width:40px;" class="td_editable" name="cumulativedepreciation" pattern="^100$||^[0-9]?[0-9]?[.,]?[0-9]?[0-9]$" onkeyup="javascript:validationpercentfield(this)" title="{document/captions/cumulativedepreciationtitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>%
                                </td>
                            </tr>
                            <!-- 	Износ-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/deterioration/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/deterioration}" style="width:40px;" class="td_editable" name="deterioration" pattern="^100$||^[0-9]?[0-9]?[.,]?[0-9]?[0-9]$" onkeyup="javascript:validationpercentfield(this)" title="{document/captions/deteriorationtitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>%
                                </td>
                            </tr>
                            <!-- Наличие обременения-->
                            <tr>
                                <td colspan="2">
                                    <b><xsl:value-of select="document/captions/overloadingexistence/@caption"/></b>
                                </td>
                            </tr>
                            <!-- Залог -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/pledge/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/pledge}" style="width:500px;" class="td_editable" name="pledge" title="{document/captions/pledgetitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- Арест по решению суда -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/arrestingbyacourtdecision/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/arrestingbyacourtdecision}" style="width:500px;" class="td_editable" name="arrestingbyacourtdecision" title="{document/captions/arrestingbyacourtdecisiontitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <!-- Юридическое притязание-->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/legalclaim/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/legalclaim}" style="width:500px;" class="td_editable" name="legalclaim" title="{document/captions/legalclaimtitle/@caption}">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-2">
                        <table width="100%" border="0">
                            <!-- 	Технический паспорт -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/technicalpassport/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/technicalpassport}" style="width:500px;" class="td_editable" name="technicalpassport">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/propertyarticleincorporation/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/propertyarticlein}" style="width:500px;" class="td_editable" name="propertyarticlein">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/resolutiontransferacceptancereport/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <input type="text" value="{document/fields/restransferacceptance}" style="width:500px;" class="td_editable" name="restransferacceptance">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                    </input>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-3">
                        <table width="100%" border="0">
                            <!-- 	Примечание -->
                            <tr>
                                <td class="fc">
                                    <xsl:value-of select="document/captions/note/@caption"/>&#xA0;:
                                </td>
                                <td>
                                    <textarea type="text" value="{document/fields/note}" style="width:500px;" class="textarea_editable" name="note">
                                        <xsl:if test="$editmode != 'edit'">
                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                            <xsl:attribute name="class">textarea_noteditable</xsl:attribute>
                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                        </xsl:if>
                                        <xsl:value-of select="document/fields/note"/>
                                    </textarea>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-4">
                        <form action="Uploader" name="upload" id="upload" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="type" value="rtfcontent"/>
                            <input type="hidden" name="formsesid" value="{formsesid}"/>
                            <!-- Секция "Вложения" -->
                            <div display="block" id="att">
                                <br/>
                                <!--<xsl:call-template name="attach"/>-->
                            </div>
                        </form>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-5">
                        <!--<xsl:call-template name="docinfo"/>-->
                    </div>
                </div>
            </section>
            <input type="hidden" name="type" value="save"/>
            <input type="hidden" name="id" value="{@id}"/>
            <input type="hidden" name="author" value="{document/fields/author/@attrval}"/>
            <input type="hidden" name="doctype" value="{document/@doctype}"/>
            <input type="hidden" name="key" value="{document/@docid}"/>
            <input type="hidden" name="parentdocid" value="{document/@parentdocid}"/>
            <input type="hidden" name="parentdoctype" value="{document/@parentdoctype}"/>
            <input type="hidden" name="isrented" value="{document/fields/isrented}"/>
        </form>
    </xsl:template>

</xsl:stylesheet>
