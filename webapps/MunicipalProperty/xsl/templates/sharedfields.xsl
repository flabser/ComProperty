<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="balanceholder-bin">
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/balance_holder/@caption"/>
            </div>
            <div class="controls">
                <div class="span8">
                    <div class="input selectize-dialog" data-input="balanceholder"
                         onclick="nbApp.dialogChoiceBalanceHolder(this)">
                        <xsl:value-of select="//fields/balanceholder"/>
                    </div>
                </div>
                <input type="hidden" name="balanceholderid" value="{//fields/balanceholderid}" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/bin/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="balanceholderbin" value="{//fields/balanceholderbin}" class="span4"
                       readonly="readonly"/>
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
                <div class="inline span3">
                    <input type="text" name="kof" value="{//fields/kof}"/>
                </div>
                <span style="display:inline-block; padding-top:4px; text-align:center; width:19px;">
                    <xsl:value-of select="'/'"/>
                </span>
                <div class="inline span1">
                    <input type="text" name="kuf" value="{//fields/kuf}" readonly="readonly"/>
                </div>
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
                <input type="text" name="invnumber" value="{//fields/invnumber}" class="span3"/>
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
                <textarea name="objectname" class="span8" required="required">
                    <xsl:value-of select="//fields/objectname"/>
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
                <textarea name="description" class="span8" required="required">
                    <xsl:value-of select="//fields/description"/>
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
                    <xsl:apply-templates select="//query[@entity = 'propertycode']/entry" mode="select_options">
                        <xsl:with-param name="select" select="//fields/propertycode"/>
                    </xsl:apply-templates>
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
                <input type="date" name="acceptancedate" value="{//fields/acceptancedate}" class="span2"/>
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
                <input type="number" name="originalcost" value="{//fields/originalcost}" class="span4"/>
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
                <input type="number" name="cumulativedepreciation" value="{//fields/cumulativedepreciation}"
                       class="span4"/>
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
                <input type="number" name="impairmentloss" value="{//fields/impairmentloss}" class="span4"/>
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
                <input type="number" name="balancecost" value="{//fields/balancecost}" class="span4"/>
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
                <input type="number" name="revaluationamount" value="{//fields/revaluationamount}" class="span4"/>
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
                <input type="number" name="afterrevaluationamount" value="{//fields/afterevaluationamount}"
                       class="span4"/>
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
                    <xsl:apply-templates select="//query[@entity = 'receivingreason']/entry" mode="select_options">
                        <xsl:with-param name="select" select="//fields/receivingreason"/>
                    </xsl:apply-templates>
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
                    <xsl:value-of select="//fields/model"/>
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
                <input type="text" name="commissioningyear" value="{//fields/commissioningyear}" class="span1"/>
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
                <input type="text" name="acquisitionyear" value="{//fields/acquisitionyear}" class="span1"
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
                <label class="btn btn-sm">
                    <input type="checkbox" name="isreadytouse" value="1">
                        <xsl:if test="//fields/isreadytouse = 'true'">
                            <xsl:attribute name="checked" select="'checked'"/>
                        </xsl:if>
                    </input>
                    <span>
                        Годен
                    </span>
                </label>
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
                    <xsl:value-of select="//fields/notes"/>
                </textarea>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="docinfo">
        <div class="panel panel-default">
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/editable/@caption"/>
                </div>
                <div class="controls">
                    <span class="input-placeholder">
                        <xsl:value-of select="//document/@editable"/>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/reg_date/@caption"/>
                </div>
                <div class="controls">
                    <span class="input-placeholder">
                        <xsl:value-of select="//fields/regdate"/>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/author/@caption"/>
                </div>
                <div class="controls">
                    <span class="input-placeholder">
                        <xsl:value-of select="//fields/author"/>
                    </span>
                </div>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="documents-of-title">
        <!-- Технический паспорт -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/technical_passport/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="technicalpassport" value="{//fields/technicalpassport}" class="span6"/>
            </div>
        </div>
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/propertyarticlein/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="propertyarticlein" value="{//fields/propertyarticlein}" class="span6"/>
            </div>
        </div>
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="//captions/restransferacceptance/@caption"/>
            </div>
            <div class="controls">
                <input type="text" name="restransferacceptance" value="{//fields/restransferacceptance}" class="span6"/>
            </div>
        </div>
    </xsl:template>

    <xsl:template name="address">
        <fieldset class="fieldset">
            <legend class="legend legend-address">
                <xsl:value-of select="//captions/address/@caption"/>
            </legend>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/region/@caption"/>
                </div>
                <div class="controls">
                    <div class="span8">
                        <div class="input selectize-dialog" data-input="region" onclick="nbApp.choiceRegion(this)">
                            <xsl:value-of select="//fields/region"/>
                        </div>
                    </div>
                    <input type="hidden" name="region" value="{//fields/region}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/city/@caption"/>
                </div>
                <div class="controls">
                    <div class="span8">
                        <div class="input selectize-dialog" data-input="city" onclick="nbApp.choiceCity(this)">
                            <xsl:value-of select="//fields/city"/>
                        </div>
                    </div>
                    <input type="hidden" name="region" value="{//fields/city}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/district/@caption"/>
                </div>
                <div class="controls">
                    <div class="span8">
                        <div class="input selectize-dialog" data-input="district" onclick="nbApp.choiceDistrict(this)">
                            <xsl:value-of select="//fields/district"/>
                        </div>
                    </div>
                    <input type="hidden" name="region" value="{//fields/district}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/street/@caption"/>
                </div>
                <div class="controls">
                    <div class="span8">
                        <div class="input selectize-dialog" data-input="street" onclick="nbApp.choiceStreet(this)">
                            <xsl:value-of select="//fields/street"/>
                        </div>
                    </div>
                    <input type="hidden" name="region" value="{//fields/street}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/home/@caption"/>
                </div>
                <div class="controls">
                    <input type="text" name="home" value="{//fields/home}" class="span1"/>
                </div>
            </div>
            <div class="form-group">
                <div class="control-label">
                    <xsl:value-of select="//captions/additional/@caption"/>
                </div>
                <div class="controls">
                    <input type="text" name="address" value="{//fields/additionalinfo}" class="span8"/>
                </div>
            </div>
        </fieldset>
    </xsl:template>

    <!-- Основание поступления в Гос. собственность <div class="form-group">
        <div class="control-label"> <xsl:value-of select="//captions/receiptbasisingproperty/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-6"> <input
        type="text" name="receiptbasisingproperty" value="{//fields/receiptbasisingproperty}"
        class="form-control"/> </div> </div> </div> -->
    <!-- Основание снятия с баланса -->
    <!-- <div class="form-group"> <div class="control-label"> <xsl:value-of
        select="//captions/orderofremovalfrombalance/@caption"/> </div> <div class="controls">
        <div class="field-wrapper col-md-6"> <input type="text" name="orderofremovalfrombalance"
        value="{//fields/orderofremovalfrombalance}" class="form-control"/> </div>
        </div> </div> -->


    <!-- Цвет -->
    <!-- <div class="form-group"> <div class="control-label"> <xsl:value-of
        select="//captions/color/@caption"/> </div> <div class="controls"> <div class="field-wrapper
        col-md-6"> <input type="text" name="color" value="{//fields/color}" class="form-control"/>
        </div> </div> </div> Ширина <div class="form-group"> <div class="control-label">
        <xsl:value-of select="//captions/width/@caption"/> </div> <div class="controls">
        <div class="field-wrapper col-md-1"> <input type="number" name="width" value="{//fields/width}"
        class="form-control"/> </div> </div> </div> Высота <div class="form-group">
        <div class="control-label"> <xsl:value-of select="//captions/height/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-1"> <input
        type="number" name="height" value="{//fields/height}" class="form-control"/>
        </div> </div> </div> Глубина <div class="form-group"> <div class="control-label">
        <xsl:value-of select="//captions/depth/@caption"/> </div> <div class="controls">
        <div class="field-wrapper col-md-1"> <input type="number" name="depth" value="{//fields/depth}"
        class="form-control"/> </div> </div> </div> Год выпуска <div class="form-group">
        <div class="control-label"> <xsl:value-of select="//captions/yearrelease/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-1"> <input
        type="number" name="yearrelease" value="{//fields/yearrelease}" class="date-year
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
                        <input type="text" name="repair" value="{//fields/repair}"
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
                        <input type="number" name="depreciating" value="{//fields/depreciating}"
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
                        <input type="number" name="cumulativedepreciation" value="{//fields/cumulativedepreciation}"
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
                        <input type="number" name="deterioration" value="{//fields/deterioration}"
                            class="percent form-control" />
                    </div>
                </div>
            </div>
        </fieldset> -->


    <!-- Наличие обременения -->
    <!-- <fieldset class="fieldset"> <legend class="legend"> <xsl:value-of select="//captions/overloadingexistence/@caption"/>
        </legend> <div class="form-group"> <div class="control-label"> <xsl:value-of
        select="//captions/pledge/@caption"/> </div> <div class="controls"> <div
        class="field-wrapper col-md-6"> <input type="text" name="pledge" value="{//fields/pledge}"
        class="form-control"/> </div> </div> </div> <div class="form-group"> <div
        class="control-label"> <xsl:value-of select="//captions/arrestingbyacourtdecision/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-6"> <input
        type="text" name="arrestingbyacourtdecision" value="{//fields/arrestingbyacourtdecision}"
        class="form-control"/> </div> </div> </div> <div class="form-group"> <div
        class="control-label"> <xsl:value-of select="//captions/legalclaim/@caption"/>
        </div> <div class="controls"> <div class="field-wrapper col-md-6"> <input
        type="text" name="legalclaim" value="{//fields/legalclaim}" class="form-control"/>
        </div> </div> </div> </fieldset> -->

    <!-- <form action="Uploader" name="upload" id="upload" method="post" enctype="multipart/form-data">
        <input type="hidden" name="type" value="rtfcontent" /> <input type="hidden"
        name="formsesid" value="{formsesid}" /> Секция "Вложения" <div display="block"
        id="att"> <br /> <xsl:call-template name="attach" /> </div> </form> -->


    <xsl:template match="entry" mode="select_options">
        <xsl:param name="select"/>
        <option value="viewcontent/name">
            <xsl:if test="@id = $select">
                <xsl:attribute name="selected" select="'selected'"/>
            </xsl:if>
            <xsl:attribute name="value" select="@id"/>
            <xsl:value-of select="."/>
        </option>
    </xsl:template>

</xsl:stylesheet>
