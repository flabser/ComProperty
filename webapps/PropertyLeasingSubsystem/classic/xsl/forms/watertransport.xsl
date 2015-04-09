<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/form.xsl"/>
	<xsl:import href="../templates/sharedactions.xsl"/>
	<xsl:variable name="doctype" select="request/document/fields/title"/>
	<xsl:variable name="path" select="/request/@skin" />
	<xsl:variable name="threaddocid" select="document/@docid"/>
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="yes"/>
	<xsl:variable name="skin" select="request/@skin"/>
	<xsl:variable name="editmode" select="/request/document/@editmode"/>
	<xsl:template match="/request">
		<html>
			<head>
				<title>
					<xsl:value-of select="$doctype"/>&#xA0;  - АИС 'Коммунальное Имущество'
				</title>
				<xsl:call-template name="cssandjs"/>
				<xsl:call-template name="markisread"/>
				<xsl:call-template name="htmlareaeditor"/>
				<xsl:call-template name="fields_tooltip"/>
				<script type="text/javascript">
					$(document).ready(function(){hotkeysnav()})
   					<![CDATA[
   						function hotkeysnav() {
							$(document).bind('keydown', function(e){
			 					if (e.ctrlKey) {
			 						switch (e.keyCode) {
									   case 66:
									   		<!-- клавиша b -->
									     	e.preventDefault();
									     	$("#canceldoc").click();
									      	break;
									   case 83:
									   		<!-- клавиша s -->
									     	e.preventDefault();
									     	$("#btnsavedoc").click();
									      	break;
									   case 85:
									   		<!-- клавиша u -->
									     	e.preventDefault();
									     	window.location.href=$("#currentuser").attr("href")
									      	break;
									   case 81:
									   		<!-- клавиша q -->
									     	e.preventDefault();
									     	window.location.href=$("#logout").attr("href")
									      	break;
									   case 72:
									   		<!-- клавиша h -->
									     	e.preventDefault();
									     	window.location.href=$("#helpbtn").attr("href")
									      	break;
									   default:
									      	break;
									}
			   					}
							});
							$("#canceldoc").hotnav({keysource:function(e){ return "b"; }});
							$("#btnsavedoc").hotnav({keysource:function(e){ return "s"; }});
							$("#currentuser").hotnav({ keysource:function(e){ return "u"; }});
							$("#logout").hotnav({keysource:function(e){ return "q"; }});
							$("#helpbtn").hotnav({keysource:function(e){ return "h"; }});
						}
					]]>
				</script>
				<xsl:if test="document/@editmode = 'edit'">
					<xsl:if test="/request/@lang = 'RUS'">
						<script>
						$(function() {
							$('#acceptancedate').datepicker({
								showOn: 'button',
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								changeMonth: true,
								changeYear: true,
								buttonText: "Изменить дату принятия на баланс",
								regional:['ru'],
								showAnim: '',
							});
						});
					</script>
				</xsl:if>
				<xsl:if test="/request/@lang = 'KAZ'">
					<script>
						$(function() {
							$('#acceptancedate').datepicker({
								showOn: 'button',
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								regional:['ru'],
								changeMonth: true,
								changeYear: true,
								showAnim: '',
								monthNames: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
								'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
								monthNamesShort: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
								'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
								dayNames: ['жексебі','дүйсенбі','сейсенбі','сәрсенбі','бейсенбі','жұма','сенбі'],
								dayNamesShort: ['жек','дүй','сей','сәр','бей','жұм','сен'],
								dayNamesMin: ['Жс','Дс','Сс','Ср','Бс','Жм','Сн'],
								});
								
							});
					</script>
				</xsl:if>
			</xsl:if>
		</head>
		<body>
			<xsl:variable name="status" select="document/@status"/>
			<div id="docwrapper">
				<xsl:call-template name="documentheader"/>	
				<div class="formwrapper">
					<div class="formtitle">
						<div style="float:left" class="title">
							<xsl:call-template name="doctitle"/><span id="whichreadblock">Прочтен</span>
						</div>
						<div style="float:right; padding-left:5px">
						</div>
					</div>
					<div class="button_panel">
						<span style="width:80%; vertical-align:12px; float:left">
							<xsl:call-template name="showxml"/>
							<xsl:call-template name="save"/>
							<xsl:if test="$status != 'new'">
								<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
									<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=orderleasing&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Добавить договор аренды</font>
									</span>
								</button>
							</xsl:if>
						</span>
						<span style="float:right">
							<xsl:call-template name="cancel"/>
						</span>
					</div>
					<div style="clear:both"/>
					<div style="-moz-border-radius:0px;height:1px; width:100%; margin-top:10px;"/>
					<div style="clear:both"/>
					<div id="tabs">
						<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-1"><xsl:value-of select="document/captions/properties/@caption"/></a>
							</li>
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-5"><xsl:value-of select="document/captions/note/@caption"/></a>
							</li>
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-2"><xsl:value-of select="document/captions/files/@caption"/></a>
							</li>
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-3"><xsl:value-of select="document/captions/additional/@caption"/></a>
							</li>
						</ul>
						<form action="Provider" name="frm" method="post" id="frm" enctype="application/x-www-form-urlencoded">
							<div class="ui-tabs-panel" id="tabs-1">
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
										
										<!-- 	Инвентарный номер -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/invnumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/invnumber}" style="width:500px;" class="td_editable" name="invnumber">
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
												<textarea name="objectname" rows="3" class="textarea_editable" style="width:700px">
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
												<textarea name="description" rows="3" class="textarea_editable" style="width:700px">
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
												<input type="text" name="acceptancedate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/acceptancedate,1,10)}">
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
										<!-- 	Первоначальная стоимость -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/originalcost/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
											</td>
											<td>
												<input type="text" value="{document/fields/originalcost}" style="width:500px;" class="td_editable" name="originalcost">
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
												<input type="text" value="{document/fields/estimatedcost}" style="width:500px;" class="td_editable" name="estimatedcost">
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
												<input type="text" value="{document/fields/residualcost}" style="width:500px;" class="td_editable" name="residualcost">
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
												<input type="text" value="{document/fields/receiptbasisingproperty}" style="width:500px;" class="td_editable" name="receiptbasisingproperty">
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
												<input type="text" value="{document/fields/receiptbasisinbalance}" style="width:500px;" class="td_editable" name="receiptbasisinbalance">
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
												<input type="text" value="{document/fields/orderofremovalfrombalance}" style="width:500px;" class="td_editable" name="orderofremovalfrombalance">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Номер двигателя -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/enginenumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/enginenumber}" style="width:500px;" class="td_editable" name="enginenumber">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Номер кузова -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/bodynumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/bodynumber}" name="bodynumber" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Гос. номер  -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/grnz/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/grnz}" name="grnz" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	№ Тех. паспорта   -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/srts/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/srts}" name="srts" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Марка (Модель) -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/model/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/model}" name="model" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Год выпуска -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/yearrelease/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/yearrelease}" name="yearrelease" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/category/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<select size="1" name="category" style="width:50px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
													<option value="A">
														<xsl:if test="document/fields/category = 'A'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														A
													</option>
													<option value="B">
														<xsl:if test="document/fields/category = 'B'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														B
													</option>
													<option value="C">
														<xsl:if test="document/fields/category = 'C'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														C
													</option>
													<option value="D">
														<xsl:if test="document/fields/category = 'D'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														D
													</option>
													<option value="E">
														<xsl:if test="document/fields/category = 'E'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														E
													</option>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="category" value="{document/fields/category}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Цвет  -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/color/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/color}" name="color" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Мощность двигателя  -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/powerengine/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/powerengine}" name="powerengine" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
												<!-- Объем двигателя -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/volumeengine/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/volumeengine}" name="volumeengine" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Вид топлива -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/fueltype/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/fueltype}" name="fueltype" style="width:200px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Расход ГСМ-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/expenditurefuel/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/expenditurefuel}" name="expenditurefuel" style="width:200px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Пробег -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/carrun/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/carrun}" name="carrun" style="width:150px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Нормативный срок эксплуатации -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/normperiodmaintenance/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/normperiodmaintenance}" name="normperiodmaintenance" style="width:200px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Состояние -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/condition/@caption"/>&#xA0;:
											</td>
											<td>
												<select value="{document/fields/condition}" style="width:200px;" class="select_editable" name="condition">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
													<option value="1">
														<xsl:if test="document/fields/condition = '1'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Отличное
													</option>
													<option value="2">
														<xsl:if test="document/fields/condition = '2'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Хорошее
													</option>
													<option value="3">
														<xsl:if test="document/fields/condition = '3'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Удовлетворительное
													</option>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="condition" value="{document/fields/condition}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Количество пассажирcких мест (не час пик) -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/quantitypassengerseats/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/quantitypassengerseats}" name="quantitypassengerseats" style="width:100px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Количество пассажирcких мест (час пик) -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/quantitypassengerseats/@caption"/> (<xsl:value-of select="document/captions/rushhour/@caption"/>)&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/quantitypassengerseats_rh}" name="quantitypassengerseats_rh" style="width:100px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Масса без нагрузки-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/weightwithoutload/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/weightwithoutload}" name="weightwithoutload" style="width:500px;" class="td_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Разрешенная максимальная нагрузка-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/maxload/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/maxload}" name="maxload" style="width:500px;" class="td_editable">
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
												<input type="text" value="{document/fields/pledge}" style="width:500px;" class="td_editable" name="pledge">
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
												<input type="text" value="{document/fields/arrestingbyacourtdecision}" style="width:500px;" class="td_editable" name="arrestingbyacourtdecision">
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
												<input type="text" value="{document/fields/legalclaim}" style="width:500px;" class="td_editable" name="legalclaim">
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
								<div id="tabs-5">
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
								<!-- Скрытые поля документа -->
								<input type="hidden" name="type" value="save"/>
								<input type="hidden" name="id" value="{@id}"/>
								<input type="hidden" name="author" value="{document/fields/author/@attrval}"/>
								<input type="hidden" name="doctype" value="{document/@doctype}"/>
								<input type="hidden" name="key" value="{document/@docid}"/>
								<input type="hidden" name="parentdocid" value="{document/@parentdocid}"/>
								<input type="hidden" name="parentdoctype" value="{document/@parentdoctype}"/>
							</form>
							<div id="tabs-2" style="height:500px">
								<form action="Uploader" name="upload" id="upload" method="post" enctype="multipart/form-data">
									<input type="hidden" name="type" value="rtfcontent"/>
									<input type="hidden" name="formsesid" value="{formsesid}"/>
									<!-- Секция "Вложения" -->
									<div display="block" id="att">
										<br/>
										<xsl:call-template name="attach"/>
									</div>
								</form>
							</div>
							<div id="tabs-3">
								<xsl:call-template name="docinfo"/>
							</div>
						</div>
						<div style="height:10px"/>
					</div>
				</div>
				<xsl:call-template name="formoutline"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>