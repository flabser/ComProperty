<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>
    <xsl:import href="../templates/sharedfields.xsl"/>

    <xsl:variable name="editmode" select="/request/document/@editmode"/>
    <xsl:variable name="status" select="document/@status"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <form class="form form-{$editmode}" action="Provider" method="post" enctype="application/x-www-form-urlencoded">
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
                            <xsl:value-of select="//captions/documents_of_title/@caption"/>
                        </a>
                    </li>
                    <li>
                        <a href="#tabs-3" role="tab" data-toggle="tab">
                            <xsl:value-of select="//captions/notes/@caption"/>
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
                        <fieldset class="fieldset">
                            <xsl:if test="$editmode != 'edit'">
                                <xsl:attribute name="disabled" select="'disabled'"/>
                            </xsl:if>
       
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/balance_holder/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <div class="form-control selection" data-input="balanceholder"
                                                 onclick="nbApp.choiceBalanceHolder(this)">
                                                <xsl:value-of select="//fields/balanceholder"/>
                                            </div>
                                            <input type="hidden" name="balanceholder" value="{//fields/balanceholder}"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>
                             <!--    <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/namekaz/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="balanceholdernamekaz"
                                                   value="{//fields/balanceholdernamekaz}" class="form-control"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div> -->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/bin/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-2">
                                            <input type="text" name="balanceholderbin" value="{//fields/balanceholderbin}" class="form-control"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>
                        

                            <xsl:call-template name="kof_kuf"/>

                            <!-- Инвентарный номер -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/inv_number/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="text" name="invnumber" value="{//fields/invnumber}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <!-- Наименование объекта -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/object_name/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <textarea name="objectname" class="form-control" required="required">
                                            <xsl:value-of select="//fields/objectname"/>
                                        </textarea>
                                    </div>
                                </div>
                            </div>
                            <!-- Описание объекта -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/description/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <textarea name="description" class="form-control" required="required">
                                            <xsl:value-of select="//fields/description"/>
                                        </textarea>
                                    </div>
                                </div>
                            </div>
                            <!-- Код права на имущество -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/property_code/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-3">
                                        <input type="text" name="propertycode" value="{//fields/propertycode}"
                                               class="form-control" readonly="readonly"/>
                                    </div>
                                </div>
                            </div>                            
                            <!-- Дата принятия на баланс -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/acceptance_date/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="date" name="acceptancedate" value="{//fields/acceptancedate}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>                           
                            <!-- Поле "Область" -->
                          <!--   <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/region/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <div class="form-control selection" data-input="region"
                                             onclick="nbApp.choiceRegion(this)">
                                            <xsl:value-of select="//fields/region"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="region" value="{//fields/region}"/>
                                </div>
                            </div>
                            Поле "Город"
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/city/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <div class="form-control selection" data-input="city"
                                             onclick="nbApp.choiceCity(this)">
                                            <xsl:value-of select="//fields/city"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="region" value="{//fields/city}"/>
                                </div>
                            </div>
                            Поле "Район"
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/district/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <div class="form-control selection" data-input="district"
                                             onclick="nbApp.choiceDistrict(this)">
                                            <xsl:value-of select="//fields/district"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="region" value="{//fields/district}"/>
                                </div>
                            </div>
                            Поле "Улица"
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/street/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <div class="form-control selection" data-input="street"
                                             onclick="nbApp.choiceStreet(this)">
                                            <xsl:value-of select="//fields/street"/>
                                        </div>
                                    </div>
                                    <input type="hidden" name="region" value="{//fields/street}"/>
                                </div>
                            </div>
                            Поле "Дом"
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/home/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="text" name="home" value="{//fields/home}" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            Поле "Адрес"
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/address/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="address" value="{//fields/address}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            Количество
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/amount/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="number" name="amount" value="{//fields/amount}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div> -->
                            <!-- Первоначальная стоимость -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/original_cost/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="number" name="originalcost" value="{//fields/originalcost}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                                 <!-- Накопленная амортизация -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/cumulative_depreciation/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="number" name="cumulativedepreciation"
                                               value="{//fields/cumulativedepreciation}" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                              <!-- Убыток от обесценения -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/impairment_loss/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="number" name="impairmentloss"
                                               value="{//fields/impairmentloss}" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <!-- Балансовая стоимость -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/balance_cost/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="number" name="balancecost" value="{//fields/balancecost}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                         
                            <!-- Сумма переоценки-->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/revaluation_amount/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="number" name="revaluationamount" value="{//fields/revaluationamount}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <!-- Балансовая стоимость после переоценки-->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/after_revaluation_amount/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-2">
                                        <input type="number" name="afterrevaluationamount" value="{//fields/afterevaluationamount}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                              <!-- Основание поступления на баланс -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/receiving_reason/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-3">
                                        <input type="text" name="receivingreason" value="{//fields/receivingreason}"
                                               class="form-control" readonly="readonly"/>
                                    </div>
                                </div>
                            </div>
<!--                             Основание поступления в Гос. собственность
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/receiptbasisingproperty/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="receiptbasisingproperty"
                                               value="{//fields/receiptbasisingproperty}" class="form-control"/>
                                    </div>
                                </div>
                            </div>   -->                         
                            <!-- Основание снятия с баланса -->
                           <!--  <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/orderofremovalfrombalance/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="orderofremovalfrombalance"
                                               value="{//fields/orderofremovalfrombalance}" class="form-control"/>
                                    </div>
                                </div>
                            </div> -->
                            <!-- Модель -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/model/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="model" value="{//fields/model}" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                              <!-- Год ввода в эксплуатацию -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/commissioning_year/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="text" name="commissioningyear" value="{//fields/commissioningyear}" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                              <!-- Год приобретения -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/acquisition_year/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="text" name="acquisitionyear" value="{//fields/acquisitionyear}"
                                               class="form-control" readonly="readonly"/>
                                    </div>
                                </div>
                            </div>
                               <!-- Сведения о годности к эксплуатации -->
                              <div class="form-group">
                        <div class="control-label">
                            <xsl:value-of select="//captions/is_ready_to_use/@caption"/>
                        </div>
                        <div class="controls">
                            <div class="field-wrapper col-lg-6">
 
                                    <label class="btn btn-sm">
                                        <input type="checkbox" name="isreadytouse" value="{//fields/isreadytouse}"/>
                                        <span>
                                              Годен
                                        </span>
                                    </label>
                              
                            </div>
                        </div>
                    </div>
                       
                          
                            <!-- Цвет -->
                            <!-- <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/color/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="color" value="{//fields/color}" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            Ширина
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/width/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="number" name="width" value="{//fields/width}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            Высота
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/height/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="number" name="height" value="{//fields/height}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            Глубина
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/depth/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="number" name="depth" value="{//fields/depth}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            Год выпуска
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/yearrelease/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-1">
                                        <input type="number" name="yearrelease" value="{//fields/yearrelease}"
                                               class="date-year form-control"/>
                                    </div>
                                </div>
                            </div> -->
                            
                            
                            
                          
                            <!-- Изменение первоначальной стоимости -->
                              <fieldset class="fieldset">
                                <legend class="legend">
                                    <xsl:value-of select="//captions/сhange_original_cost/@caption"/>
                                </legend>
                                <!-- Ремонт-->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/repair/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="repair" value="{//fields/repair}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- Обесценение -->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/depreciating/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-1">
                                            <input type="number" name="depreciating" value="{//fields/depreciating}"
                                                   class="percent form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- Накопленная амортизация -->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/cumulativedepreciation/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-1">
                                            <input type="number" name="cumulativedepreciation"
                                                   value="{//fields/cumulativedepreciation}"
                                                   class="percent form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- Износ -->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/deterioration/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-1">
                                            <input type="number" name="deterioration" value="{//fields/deterioration}"
                                                   class="percent form-control"/>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <!-- Наличие обременения -->
                            <fieldset class="fieldset">
                                <legend class="legend">
                                    <xsl:value-of select="//captions/overloadingexistence/@caption"/>
                                </legend>
                                <!-- Залог -->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/pledge/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="pledge" value="{//fields/pledge}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- Арест по решению суда -->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/arrestingbyacourtdecision/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="arrestingbyacourtdecision"
                                                   value="{//fields/arrestingbyacourtdecision}" class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- Юридическое притязание -->
                                <div class="form-group">
                                    <div class="control-label">
                                        <xsl:value-of select="//captions/legalclaim/@caption"/>
                                    </div>
                                    <div class="controls">
                                        <div class="field-wrapper col-md-6">
                                            <input type="text" name="legalclaim" value="{//fields/legalclaim}"
                                                   class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </fieldset>
                    </div> -->
                    <div role="tabpanel" class="tab-pane" id="tabs-2">
                        <fieldset class="fieldset">
                            <xsl:if test="$editmode != 'edit'">
                                <xsl:attribute name="disabled" select="'disabled'"/>
                            </xsl:if>
                            <!-- Технический паспорт -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/technicalpassport/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="technicalpassport" value="{//fields/technicalpassport}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/propertyarticlein/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="propertyarticlein" value="{//fields/propertyarticlein}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/restransferacceptance/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <input type="text" name="restransferacceptance"
                                               value="{//fields/restransferacceptance}"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-3">
                        <fieldset class="fieldset">
                            <xsl:if test="$editmode != 'edit'">
                                <xsl:attribute name="disabled" select="'disabled'"/>
                            </xsl:if>
                            <!-- Примечание -->
                            <div class="form-group">
                                <div class="control-label">
                                    <xsl:value-of select="//captions/notes/@caption"/>
                                </div>
                                <div class="controls">
                                    <div class="field-wrapper col-md-6">
                                        <textarea name="technicalpassport">
                                            <xsl:value-of select="//fields/notes"/>
                                        </textarea>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-4">
                       <!--  <form action="Uploader" name="upload" id="upload" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="type" value="rtfcontent"/>
                            <input type="hidden" name="formsesid" value="{formsesid}"/>
                            Секция "Вложения"
                            <div display="block" id="att">
                                <br/>
                                <xsl:call-template name="attach"/>
                            </div>
                        </form> -->
                    </div>
                    <div role="tabpanel" class="tab-pane" id="tabs-5">
                        <!--<xsl:call-template name="docinfo"/>-->
                    </div>
                </div>
            </section>
            <input type="hidden" name="type" value="save"/>
            <input type="hidden" name="id" value="{@id}"/>
            <input type="hidden" name="docid" value="{//document/@docid}"/>
        </form>
    </xsl:template>

</xsl:stylesheet>
