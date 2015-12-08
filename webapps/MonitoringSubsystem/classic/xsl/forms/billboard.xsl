<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/form.xsl"/>
		<xsl:import href="../templates/sharedfields.xsl"/>
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
					$(document).ready(function(){
						suggestionStreet()
						hotkeysnav()
					})
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
								showAnim: '',
								changeMonth: true,
								changeYear: true,
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
									<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=order&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Добавить постановление</font>
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
								<a href="#tabs-4"><xsl:value-of select="document/captions/documentsoftitle/@caption"/></a>
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
												<xsl:call-template name="kofkuf"/>	
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
												<xsl:value-of select="document/captions/description/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/>:
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
										<!-- Поле "Территориальное расположение объекта" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/ktr/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<select size="1" name="ktr" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
													<option value="1,20">
														<xsl:if test="document/fields/ktr = '1,20'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Иное месторасположение помещений
													</option>
													<option value="3,0">
														<xsl:if test="document/fields/ktr = '3,0'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Аль-Фараби, Хаджи Мукана (южная сторона) - улица Кожамкулова (западная сторона) - проспект Райымбека (северная сторона) - улица Луганского (восточная сторона)
													</option>
													<option value="1,6">
														<xsl:if test="document/fields/ktr = '1,6'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Тимирязева (южная сторона) - улица Ауэзова (западная сторона) - проспекта Райымбека (северная сторона) - улицы Кожамкулова (восточная сторона)
													</option>
													<option value="1,4">
														<xsl:if test="document/fields/ktr = '1,4'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Тимирязева (южная сторона) - улицы Розыбакиева (западная сторона)- проспекта Райымбека (северная сторона) - улицы Ауэзова (восточная сторона)
													</option>
													<option value="1,2">
														<xsl:if test="document/fields/ktr = '1,2'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														границы Турксибского района и Алатауского района
													</option>
													
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="ktr" value="{document/fields/ktr}"/>
												</xsl:if>
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
										<!-- 	Общая площадь -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/totalarea/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/totalarea}" style="width:200px;" class="td_editable" name="totalarea" autocomplete="off" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{document/captions/totalareatitle/@caption}">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
												&#xA0;
												<input type="text" value="{document/fields/extratotalarea}" style="width:375px;" class="td_editable" name="extratotalarea" autocomplete="off">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Площадь земельного участка -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/landarea/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/landarea}" style="width:90px;" class="td_editable" name="landarea" autocomplete="off" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{document/captions/landareatitle/@caption}">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>&#xA0;
												<input type="text" value="{document/fields/extralandarea}" style="width:375px;" class="td_editable" name="extralandarea" autocomplete="off">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Поле "Материал постройки" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/material/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="material" select="document/fields/material/@attrval" />
												<select size="1" name="material" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
														<option value="">
															<xsl:attribute name="selected">selected</xsl:attribute>
															<xsl:value-of select="document/fields/material"/>
														</option>
													</xsl:if>
													<option value="0">
															
													</option>
													<xsl:for-each select="document/glossaries/material/query/entry">
														<option value="{@docid}">
															<xsl:if test="$material = @docid">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="material" value="{document/fields/material/@attrval}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Годы строительства -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/buildingsyears/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/buildingsyears}" style="width:90px;" class="td_editable" name="buildingsyears" autocomplete="off" title="{document/captions/buildingsyearstitle/@caption}">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Год постройки -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/yearconstruction/@caption"/>&#xA0;:
											</td>
											<td>
												<xsl:variable name="year" select="format-date(current-date(),'[Y0001]')"/>
												<input type="text" value="{document/fields/yearconstruction}" style="width:40px;" class="td_editable" name="yearconstruction" autocomplete="off" pattern="^18\d\d$|^19\d\d$|^20[{substring($year,3,1)}][0-{substring($year,4,1)}]$|^200[0-9]$" onkeyup="javascript:validationyearfield(this)" title="{document/captions/yearconstructiontitle/@caption}">
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
								<!-- Скрытые поля документа -->
								<input type="hidden" name="type" value="save"/>
								<input type="hidden" name="id" value="{@id}"/>
								<input type="hidden" name="author" value="{document/fields/author/@attrval}"/>
								<input type="hidden" name="doctype" value="{document/@doctype}"/>
								<input type="hidden" name="key" value="{document/@docid}"/>
								<input type="hidden" name="parentdocid" value="{document/@parentdocid}"/>
								<input type="hidden" name="parentdoctype" value="{document/@parentdoctype}"/>
								<input type="hidden" name="isrented" value="{document/fields/isrented}"/>
								<div id="tabs-4">
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
												<xsl:value-of select="document/captions/stateactearth/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/stateactearth}" style="width:500px;" class="td_editable" name="stateactearth">
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