<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="balanceholder-bin">
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/balance_holder/@caption"/>
            </div>
            <div class="controls">
                <xsl:choose>
                    <xsl:when test="@docid eq '' or @docid eq 'null'">
                        <select name="balanceholder" class="span8">
                            <xsl:apply-templates select="fields/balanceholder" mode="selected_options"/>
                        </select>
                    </xsl:when>
                    <xsl:otherwise>
                        <div class="input-placeholder input span8 disabled">
                            <xsl:value-of select="fields/balanceholder"/>
                            <input type="hidden" name="balanceholder" value="{fields/balanceholder/@id}"
                                   disabled="disabled"/>
                        </div>
                    </xsl:otherwise>
                </xsl:choose>
            </div>
        </div>
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/bin/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="balanceholderbin" value="{fields/balanceholderbin}" class="span4"
                       disabled="disabled"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="kof-kuf">
        <!-- KOF & KUF -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="'КОФ/КУФ'"/>
            </div>
            <div class="controls">
                <div class="kof-kuf">
                    <input type="text" name="kof" value="{fields/kof}"/>
                    <input type="text" name="kuf" value="{fields/kuf}" readonly="readonly" class="disabled"/>
                </div>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="property_status_type">
        <!-- property_status -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/property_status/@caption"/>
            </div>
            <div class="controls">
                <xsl:apply-templates select="//constants[@entity = 'propertystatustype']/entry[@attrval != 'UNKNOWN']"
                                     mode="constants">
                    <xsl:with-param name="select" select="fields/propertystatus"/>
                    <xsl:with-param name="type" select="'radio'"/>
                </xsl:apply-templates>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="invnumber">
        <!-- Инвентарный номер -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/inv_number/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="invnumber" value="{fields/invnumber}" class="span3"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="objectname">
        <!-- Наименование объекта -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/object_name/@caption"/>
            </div>
            <div class="controls">
                <textarea name="objectname" class="span8">
                    <xsl:value-of select="fields/objectname"/>
                </textarea>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="description">
        <!-- Описание объекта -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/description/@caption"/>
            </div>
            <div class="controls">
                <textarea name="description" class="span8">
                    <xsl:value-of select="fields/description"/>
                </textarea>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="propertycode">
        <!-- Код права на имущество -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/property_code/@caption"/>
            </div>
            <div class="controls">
                <select name="propertycode" class="span8">
                    <xsl:apply-templates select="fields/propertycode" mode="selected_options"/>
                </select>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="acceptancedate">
        <!-- Дата принятия на баланс -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/acceptance_date/@caption"/>
            </div>
            <div class="controls">
                <input type="date" name="acceptancedate" value="{fields/acceptancedate}" class="span2"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="originalcost">
        <!-- Первоначальная стоимость -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/original_cost/@caption"/>
            </div>
            <div class="controls">
                <input type="number" name="originalcost" value="{fields/originalcost}" class="span3"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="cumulativedepreciation">
        <!-- Накопленная амортизация -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/cumulative_depreciation/@caption"/>
            </div>
            <div class="controls">
                <input type="number" name="cumulativedepreciation" value="{fields/cumulativedepreciation}"
                       class="span3"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="impairmentloss">
        <!-- Убыток от обесценения -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/impairment_loss/@caption"/>
            </div>
            <div class="controls">
                <input type="number" name="impairmentloss" value="{fields/impairmentloss}" class="span3"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="balancecost">
        <!-- Балансовая стоимость -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/balance_cost/@caption"/>
            </div>
            <div class="controls">
                <input type="number" name="balancecost" value="{fields/balancecost}" class="span3"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="revaluationamount">
        <!-- Сумма переоценки -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/revaluation_amount/@caption"/>
            </div>
            <div class="controls">
                <input type="number" name="revaluationamount" value="{fields/revaluationamount}" class="span3"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="afterrevaluationamount">
        <!-- Балансовая стоимость после переоценки -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/after_revaluation_amount/@caption"/>
            </div>
            <div class="controls">
                <input type="number" name="afterrevaluationamount" value="{fields/afterrevaluationamount}"
                       class="span3"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="receivingreason">
        <!-- Основание поступления на баланс -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/receiving_reason/@caption"/>
            </div>
            <div class="controls">
                <select name="receivingreason" class="span8">
                    <xsl:apply-templates select="fields/receivingreason" mode="selected_options"/>
                </select>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="model">
        <!-- Модель -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/model/@caption"/>
            </div>
            <div class="controls">
                <textarea name="model" class="span8">
                    <xsl:value-of select="fields/model"/>
                </textarea>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="commissioningyear">
        <!-- Год ввода в эксплуатацию -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/commissioning_year/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="commissioningyear" value="{fields/commissioningyear}" class="span1"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="acquisitionyear">
        <!-- Год приобретения -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/acquisition_year/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="acquisitionyear" value="{fields/acquisitionyear}" class="span1 disabled"
                       readonly="readonly"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="isreadytouse">
        <!-- Сведения о годности к эксплуатации -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/is_ready_to_use/@caption"/>
            </div>
            <div class="controls">
                <label class="input">
                    <input type="checkbox" name="isreadytouse" value="1">
                        <xsl:if test="fields/isreadytouse = 'true'">
                            <xsl:attribute name="checked" select="'checked'"/>
                        </xsl:if>
                    </input>
                    <span>
                        <xsl:value-of select="'Годен'"/>
                    </span>
                </label>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="tags">
        <!-- Метки -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/tags/@caption"/>
            </div>
            <div class="controls">
                <select name="tags" class="span8" multiple="true">
                    <xsl:apply-templates select="fields/tags" mode="selected_options"/>
                </select>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="notes">
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/notes/@caption"/>
            </div>
            <div class="controls">
                <textarea name="notes" class="span8">
                    <xsl:value-of select="fields/notes"/>
                </textarea>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="upload-files">
        <xsl:param name="input-name"/>

        <div class="form-group">
            <button type="button" class="btn btn-upload" data-upload="{$input-name}">
                <i class="fa fa-paperclip"></i>
                <span>
                    <xsl:value-of select="//captions/attach_file/@caption"/>
                </span>
            </button>
            <div class="attachments" data-upload-files="{$input-name}">
                <xsl:for-each select="fields/attachments/attachment">
                    <div class="attachments-file">
                        <a class="file-name" data-file="{filename}" href="{url}">
                            <xsl:value-of select="filename"/>
                        </a>
                        <span class="btn btn-sm btn-link btn-remove-file on-edit">
                            <i class="fa fa-times"></i>
                        </span>
                    </div>
                </xsl:for-each>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="docinfo">
        <fieldset class="fieldset">
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/editable/@caption"/>
                </div>
                <div class="controls">
                    <span class="input-placeholder">
                        <i>
                            <xsl:attribute name="class">
                                <xsl:choose>
                                    <xsl:when test="@editable = 'true'">
                                        fa fa-pencil
                                    </xsl:when>
                                    <xsl:when test="@editable = 'false'">
                                        fa fa-eye
                                    </xsl:when>
                                    <xsl:otherwise>
                                        fa fa-warning
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:attribute>
                        </i>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/reg_date/@caption"/>
                </div>
                <div class="controls">
                    <span class="input-placeholder">
                        <xsl:value-of select="fields/regdate"/>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/author/@caption"/>
                </div>
                <div class="controls">
                    <span class="input-placeholder">
                        <xsl:value-of select="fields/author"/>
                    </span>
                </div>
            </div>
        </fieldset>
    </xsl:template>

    <xsl:template name="documents-of-title">
        <!-- Правоустанавливающие документы -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/tech_cert/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="techcert" value="{fields/techcert}" class="span8"/>
            </div>
        </div>
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/reg_cert/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="regcert" value="{fields/regcert}" class="span8"/>
            </div>
        </div>
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/decrees_acts/@caption"/>
            </div>
            <div class="controls">
                <textarea name="decreesacts" class="span8">
                    <xsl:value-of select="fields/decreesacts"/>
                </textarea>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="address">
        <fieldset class="fieldset">
            <legend class="legend">
                <xsl:value-of select="//captions/address/@caption"/>
            </legend>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/region/@caption"/>
                </div>
                <div class="controls">
                    <select name="region" class="span8 disabled" readonly="readonly">
                        <xsl:apply-templates select="fields/address/region" mode="selected_options"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/locality/@caption"/>
                </div>
                <div class="controls">
                    <select name="locality" class="span8 disabled" readonly="readonly">
                        <xsl:apply-templates select="fields/address/locality" mode="selected_options"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/district/@caption"/>
                </div>
                <div class="controls">
                    <select name="district" class="span8">
                        <xsl:apply-templates select="fields/address/district" mode="selected_options"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/street/@caption"/>
                </div>
                <div class="controls">
                    <select name="street" class="span8">
                        <xsl:apply-templates select="fields/address/street" mode="selected_options"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/home/@caption"/>
                </div>
                <div class="controls">
                    <input type="text" name="housenumber" value="{fields/address/housenumber}" class="span1"/>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/coordinates/@caption"/>
                </div>
                <div class="controls">
                    <input type="text" name="coordinates" value="{fields/address/coordinates}" class="span4"/>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/additional/@caption"/>
                </div>
                <div class="controls">
                    <input type="text" name="additionalinfo" value="{fields/address/additionalinfo}" class="span8"/>
                </div>
            </div>
        </fieldset>
    </xsl:template>

    <!-- Основание поступления в Гос. собственность <div class="form-group">
        <div class="control-label"> <xsl:value-of select="//captions/receiptbasisingproperty/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-6"> <input
        type="text" name="receiptbasisingproperty" value="{fields/receiptbasisingproperty}"
        class="form-control"/> </div> </div> </div> -->
    <!-- Основание снятия с баланса -->
    <!-- <div class="form-group"> <div class="control-label"> <xsl:value-of
        select="//captions/orderofremovalfrombalance/@caption"/> </div> <div class="controls">
        <div class="field-wrapper col-md-6"> <input type="text" name="orderofremovalfrombalance"
        value="{fields/orderofremovalfrombalance}" class="form-control"/> </div>
        </div> </div> -->


    <!-- Цвет -->
    <!-- <div class="form-group"> <div class="control-label"> <xsl:value-of
        select="//captions/color/@caption"/> </div> <div class="controls"> <div class="field-wrapper
        col-md-6"> <input type="text" name="color" value="{fields/color}" class="form-control"/>
        </div> </div> </div> Ширина <div class="form-group"> <div class="control-label">
        <xsl:value-of select="//captions/width/@caption"/> </div> <div class="controls">
        <div class="field-wrapper col-md-1"> <input type="number" name="width" value="{fields/width}"
        class="form-control"/> </div> </div> </div> Высота <div class="form-group">
        <div class="control-label"> <xsl:value-of select="//captions/height/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-1"> <input
        type="number" name="height" value="{fields/height}" class="form-control"/>
        </div> </div> </div> Глубина <div class="form-group"> <div class="control-label">
        <xsl:value-of select="//captions/depth/@caption"/> </div> <div class="controls">
        <div class="field-wrapper col-md-1"> <input type="number" name="depth" value="{fields/depth}"
        class="form-control"/> </div> </div> </div> Год выпуска <div class="form-group">
        <div class="control-label"> <xsl:value-of select="//captions/yearrelease/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-1"> <input
        type="number" name="yearrelease" value="{fields/yearrelease}" class="date-year
        form-control"/> </div> </div> </div> -->


    <!-- Изменение первоначальной стоимости -->
    <!-- 	<fieldset class="fieldset">
            <legend class="legend">
                <xsl:value-of select="//captions/сhange_original_cost/@caption" />
            </legend>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/repair/@caption" />
                </div>
                <div class="controls">
                    <div class="field-wrapper col-md-6">
                        <input type="text" name="repair" value="{fields/repair}"
                            class="form-control" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/depreciating/@caption" />
                </div>
                <div class="controls">
                    <div class="field-wrapper col-md-1">
                        <input type="number" name="depreciating" value="{fields/depreciating}"
                            class="percent
            form-control" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/cumulativedepreciation/@caption" />
                </div>
                <div class="controls">
                    <div class="field-wrapper col-md-1">
                        <input type="number" name="cumulativedepreciation" value="{fields/cumulativedepreciation}"
                            class="percent
            form-control" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/deterioration/@caption" />
                </div>
                <div class="controls">
                    <div class="field-wrapper col-md-1">
                        <input type="number" name="deterioration" value="{fields/deterioration}"
                            class="percent form-control" />
                    </div>
                </div>
            </div>
        </fieldset> -->


    <!-- Наличие обременения -->
    <!-- <fieldset class="fieldset"> <legend class="legend"> <xsl:value-of select="//captions/overloadingexistence/@caption"/>
        </legend> <div class="form-group"> <div class="control-label"> <xsl:value-of
        select="//captions/pledge/@caption"/> </div> <div class="controls"> <div
        class="field-wrapper col-md-6"> <input type="text" name="pledge" value="{fields/pledge}"
        class="form-control"/> </div> </div> </div> <div class="form-group"> <div
        class="control-label"> <xsl:value-of select="//captions/arrestingbyacourtdecision/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-6"> <input
        type="text" name="arrestingbyacourtdecision" value="{fields/arrestingbyacourtdecision}"
        class="form-control"/> </div> </div> </div> <div class="form-group"> <div
        class="control-label"> <xsl:value-of select="//captions/legalclaim/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-6"> <input
        type="text" name="legalclaim" value="{fields/legalclaim}" class="form-control"/>
        </div> </div> </div> </fieldset> -->

    <!-- <form action="Uploader" name="upload" id="upload" method="post" enctype="multipart/form-data">
        <input type="hidden" name="type" value="rtfcontent" /> <input type="hidden"
        name="formsesid" value="{formsesid}" /> Секция "Вложения" <div display="block"
        id="att"> <br /> <xsl:call-template name="attach" /> </div> </form> -->

    <xsl:template match="*" mode="selected_options">
        <xsl:choose>
            <xsl:when test="entry">
                <xsl:for-each select="entry">
                    <option value="{@id}" selected="selected">
                        <xsl:value-of select="."/>
                    </option>
                </xsl:for-each>
            </xsl:when>
            <xsl:otherwise>
                <xsl:if test="@id">
                    <option value="{@id}" selected="selected">
                        <xsl:value-of select="."/>
                    </option>
                </xsl:if>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="entry" mode="constants">
        <xsl:param name="select"/>
        <xsl:param name="type" select="'checkbox'"/>
        <xsl:param name="name" select="$select/name()"/>
        <xsl:variable name="attrval" select="@attrval"/>
        <xsl:variable name="lowerAttrval" select="lower-case(@attrval)"/>
        <xsl:variable name="caption" select="//captions/*[lower-case(name()) = $lowerAttrval]/@caption"/>

        <label class="input">
            <input type="{$type}" name="{$name}" value="{@attrval}">
                <xsl:if test="contains($attrval, $select)">
                    <xsl:attribute name="checked" select="checked"/>
                </xsl:if>
                <span>
                    <xsl:choose>
                        <xsl:when test="$caption != ''">
                            <xsl:value-of select="$caption"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="."/>
                        </xsl:otherwise>
                    </xsl:choose>
                </span>
            </input>
        </label>
    </xsl:template>

</xsl:stylesheet>
