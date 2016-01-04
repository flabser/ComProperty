<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/form.xsl"/>
	<xsl:import href="../templates/sharedfields.xsl"/>
	<xsl:import href="../templates/sharedactions.xsl"/>
	<xsl:variable name="doctype" select="request/page/fields/title"/>
	<xsl:variable name="path" select="/request/@skin" />
	<xsl:variable name="threaddocid" select="page/@docid"/>
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="yes"/>
	<xsl:variable name="skin" select="request/@skin"/>
	<xsl:variable name="editmode" select="/request/page/@editmode"/>
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
					$(page).ready(function(){
						suggestionStreet()
						hotkeysnav()
					})
   					<![CDATA[
   						function hotkeysnav() {
							$(page).bind('keydown', function(e){
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
				<xsl:if test="page/@editmode = 'edit'">
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
			<xsl:variable name="status" select="page/@status"/>
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
									<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=order&amp;key=&amp;parentdocid=<xsl:value-of select="page/@docid"/>&amp;parentdoctype=<xsl:value-of select="page/@doctype"/>"</xsl:attribute>
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
								<a href="#tabs-1"><xsl:value-of select="page/captions/properties/@caption"/></a>
							</li>
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-4"><xsl:value-of select="page/captions/pagesoftitle/@caption"/></a>
							</li>
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-5"><xsl:value-of select="page/captions/note/@caption"/></a>
							</li>
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-2"><xsl:value-of select="page/captions/files/@caption"/></a>
							</li>
							<li class="ui-state-default ui-corner-top">
								<a href="#tabs-3"><xsl:value-of select="page/captions/additional/@caption"/></a>
							</li>
						</ul>
						
							<form action="Provider" name="frm" method="post" id="frm" enctype="application/x-www-form-urlencoded">
								<div class="ui-tabs-panel" id="tabs-1">
									<br/>
									<table width="100%" border="0">
										
									<xsl:if test="page/fields/balanceholder !=''">	
											<!-- поле "Балансодержатель" -->
											<tr>
												<td class="fc" style="padding-top:5px">
													<font style="vertical-align:top">
														<xsl:value-of select="page/captions/balanceholder/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
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
																<xsl:value-of select="page/fields/balanceholdername"/>&#xA0;
																<span style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'><font><xsl:value-of select="page/fields/corr/@attrval"/></font></span>
															</td>
														</tr>
													</table>
													<input type="hidden" value="{page/fields/balanceholder}" id="balanceholder" name="balanceholder"/>
													<input type="hidden" value="{page/captions/balanceholder/@caption}" id="balanceholdercaption"/>
												</td>
											</tr>
											<!-- 	Наименование Гос. учреждения (каз.) -->
											<tr>
												<td class="fc">
													<xsl:value-of select="page/captions/namekaz/@caption"/>&#xA0;:
												</td>
												<td>
													<input type="text" value="{page/fields/balanceholdernamekaz}" style="width:500px;" class="td_editable" name="balanceholdernamekaz">
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
													<xsl:value-of select="page/captions/bin/@caption"/>&#xA0;:
												</td>
												<td>
													<input type="text" value="{page/fields/bin}" style="width:500px;" class="td_editable" name="bin">
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
												<xsl:value-of select="page/captions/invnumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/invnumber}" style="width:500px;" class="td_editable" name="invnumber" title="{page/captions/invnumbertitle/@caption}">
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
												<xsl:value-of select="page/captions/objectname/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/>&#xA0;:
											</td>
											<td>
												<textarea name="objectname" rows="3" class="textarea_editable" style="width:700px" title="{page/captions/objectnametitle/@caption}">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">textarea_noteditable</xsl:attribute>
													</xsl:if>
													<xsl:if test="page/@editmode = 'edit'">
														<xsl:attribute name="onfocus">fieldOnFocus(this)</xsl:attribute>
														<xsl:attribute name="onblur">fieldOnBlur(this)</xsl:attribute>
													</xsl:if>
													<xsl:value-of select="page/fields/objectname"/>
												</textarea>
											</td>
										</tr>
										<!-- 	Описание объекта -->
										<tr>
											<td class="fc">
												<xsl:value-of select="page/captions/description/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
											</td>
											<td>
												<textarea name="description" rows="3" class="textarea_editable" style="width:700px" title="{page/captions/descriptiontitle/@caption}">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">textarea_noteditable</xsl:attribute>
													</xsl:if>
													<xsl:if test="page/@editmode = 'edit'">
														<xsl:attribute name="onfocus">fieldOnFocus(this)</xsl:attribute>
														<xsl:attribute name="onblur">fieldOnBlur(this)</xsl:attribute>
													</xsl:if>
													<xsl:value-of select="page/fields/description"/>
												</textarea>
											</td>
										</tr>
										<!-- 	Код права на имущество -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="page/captions/propertycode/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="propertycode" select="page/fields/propertycode/@attrval"/>
												<select size="1"  style="width:611px;" class="select_noteditable" disabled="disabled">
													<option value="0">
														<xsl:if test="not(page/fields/propertycode) or $propertycode = '0'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
													</option>
													<option value="">
														<xsl:if test="$propertycode != ''">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														<xsl:value-of select="page/fields/propertycode"/>
													</option>
												</select>
												<input type="hidden" name="propertycode" value="{page/fields/propertycode/@attrval}">
													<xsl:if test="not(page/fields/propertycode) or $propertycode = '0'">
														<xsl:attribute name="value">0</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Дата принятия на баланс -->
										<tr>
											<td class="fc">
												<xsl:value-of select="page/captions/acceptancedate/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="acceptancedate" maxlength="10" autocomplete="off" readonly="readonly" class="td_editable" style="width:80px; vertical-align:top" value="{substring(page/fields/acceptancedate,1,10)}">
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
												<xsl:value-of select="page/captions/region/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="region" select="page/fields/region/@attrval" />
												<select size="1" name="region" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
														<option value="">
															<xsl:attribute name="selected">selected</xsl:attribute>
															<xsl:value-of select="page/fields/region"/>
														</option>
													</xsl:if>
													<xsl:for-each select="page/glossaries/region/query/entry">
														<option value="{@docid}">
															<xsl:if test="$region = @docid or (lower-case(viewcontent/viewtext1) = 'алматы г.а.' and $status = 'new')">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="region" value="{page/fields/region/@attrval}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Поле "Город" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="page/captions/city/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="city" select="page/fields/city/@attrval" />
												<select size="1" name="city" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
														<option value="">
															<xsl:attribute name="selected">selected</xsl:attribute>
															<xsl:value-of select="page/fields/city"/>
														</option>
													</xsl:if>
													<xsl:for-each select="page/glossaries/city/query/entry">
														<option value="{@docid}">
															<xsl:if test="$city = @docid">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="city" value="{page/fields/city/@attrval}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Поле "Район" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="page/captions/district/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="district" select="page/fields/district/@attrval" />
												<select size="1" name="district" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
														<option value="">
															<xsl:attribute name="selected">selected</xsl:attribute>
															<xsl:value-of select="page/fields/district"/>
														</option>
													</xsl:if>
													<xsl:for-each select="page/glossaries/district/query/entry">
														<option value="{@docid}">
															<xsl:if test="$district = @docid">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="district" value="{page/fields/district/@attrval}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Поле "Улица" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="page/captions/street/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<input type="text" id="input_street" style="width:500px; padding:2px;" name="input_street" value="{page/fields/street}" class="td_editable" autocomplete="off">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
												</input>
												<input type="hidden" id="street" name="street" value="{page/fields/street/@attrval}"/>
											</td>
										</tr>
										<!-- Поле "Дом" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="page/captions/home/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<input type="text" style="width:200px; padding:2px;" name="home" value="{page/fields/home}" class="td_editable" autocomplete="off">
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
												<xsl:value-of select="page/captions/address/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<input type="text" style="width:500px; padding:2px;" name="address" value="{page/fields/address}" class="td_editable" autocomplete="off">
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
												<xsl:value-of select="page/captions/amount/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/amount}" style="width:90px;" class="td_editable" name="amount" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" title="{page/captions/amounttitle/@caption}">
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
												<xsl:value-of select="page/captions/originalcost/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
											</td>
											<td>
												<input type="text" value="{page/fields/originalcost}" style="width:90px;" class="td_editable" name="originalcost" autocomplete="off" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{page/captions/originalcosttitle/@caption}">
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
												<xsl:value-of select="page/captions/balancecost/@caption"/> :
											</td>
											<td>
												<input type="text" value="{page/fields/balancecost}" style="width:90px;" class="td_editable" name="balancecost" autocomplete="off" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{page/captions/balancecosttitle/@caption}">
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
												<xsl:value-of select="page/captions/estimatedcost/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/estimatedcost}" style="width:90px;" class="td_editable" name="estimatedcost" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{page/captions/estimatedcosttitle/@caption}">
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
												<xsl:value-of select="page/captions/residualcost/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/residualcost}" style="width:90px;" class="td_editable" name="residualcost" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{page/captions/residualcosttitle/@caption}">
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
												<xsl:value-of select="page/captions/limitdepreciation/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<input type="text" value="{page/fields/limitdepreciation}" style="width:90px;" class="td_editable" name="limitdepreciation" pattern="\d+((.|,)\d+)?$" onkeyup="javascript:validationfloatfield(this)" title="{page/captions/limitdepreciationtitle/@caption}">
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
												<xsl:value-of select="page/captions/receiptbasisingproperty/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/receiptbasisingproperty}" style="width:500px;" class="td_editable" name="receiptbasisingproperty" title="{page/captions/receiptbasisingpropertytitle/@caption}">
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
												<xsl:value-of select="page/captions/receiptbasisinbalance/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/receiptbasisinbalance}" style="width:500px;" class="td_editable" name="receiptbasisinbalance" title="{page/captions/receiptbasisinbalancetitle/@caption}">
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
												<xsl:value-of select="page/captions/orderofremovalfrombalance/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/orderofremovalfrombalance}" style="width:500px;" class="td_editable" name="orderofremovalfrombalance" title="{page/captions/orderofremovalfrombalancetitle/@caption}">
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
												<xsl:value-of select="page/captions/model/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/model}" style="width:500px;" class="td_editable" name="model" title="{page/captions/modeltitle/@caption}">
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
												<xsl:value-of select="page/captions/color/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/color}" style="width:500px;" class="td_editable" name="color"  title="{page/captions/colortitle/@caption}">
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
												<xsl:value-of select="page/captions/width/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/width}" style="width:80px;" class="td_editable" name="width" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" title="{page/captions/widthtitle/@caption}">
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
												<xsl:value-of select="page/captions/height/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/height}" style="width:80px;" class="td_editable" name="height" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" required="required" title="{page/captions/heighttitle/@caption}">
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
												<xsl:value-of select="page/captions/depth/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/depth}" style="width:80px;" class="td_editable" name="depth" autocomplete="off" pattern="^[ 0-9]+$" onkeyup="javascript:validationnumfield(this)" title="{page/captions/depthtitle/@caption}">
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
												<xsl:value-of select="page/captions/yearrelease/@caption"/>&#xA0;:
											</td>
											<td>
												<xsl:variable name="year" select="format-date(current-date(),'[Y0001]')"/>
												<input type="text" value="{page/fields/yearrelease}" style="width:80px;" class="td_editable" name="yearrelease" autocomplete="off" pattern="^18\d\d$|^19\d\d$|^20[{substring($year,3,1)}][0-{substring($year,4,1)}]$|^200[0-9]$" onkeyup="javascript:validationyearfield(this)" title="{page/captions/yearreleasetitle/@caption}">
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
												<b><xsl:value-of select="page/captions/сhangeoriginalcost/@caption"/></b>
											</td>
										</tr>
										<!-- 	Ремонт-->
										<tr>
											<td class="fc">
												<xsl:value-of select="page/captions/repair/@caption"/>&#xA0;:
											</td>
											<td>
												<select value="{page/fields/repair}" style="width:200px;" class="select_editable" name="repair">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
													<option value="1">
														<xsl:if test="page/fields/repair != '1' and page/fields/repair != '2'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
													</option>
													<option value="1">
														<xsl:if test="page/fields/repair = '1'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														<xsl:value-of select="page/captions/reconstruction/@caption"/>
													</option>
													<option value="2">
														<xsl:if test="page/fields/repair = '2'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														<xsl:value-of select="page/captions/capital/@caption"/>
													</option>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="repair" value="{page/fields/repair}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Обесценение -->
										<tr>
											<td class="fc">
												<xsl:value-of select="page/captions/depreciating/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/depreciating}" style="width:40px;" class="td_editable" name="depreciating" pattern="^100$||^[0-9]?[0-9]?[.,]?[0-9]?[0-9]$" onkeyup="javascript:validationpercentfield(this)" title="{page/captions/depreciatingtitle/@caption}">
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
												<xsl:value-of select="page/captions/cumulativedepreciation/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/cumulativedepreciation}" style="width:40px;" class="td_editable" name="cumulativedepreciation" pattern="^100$||^[0-9]?[0-9]?[.,]?[0-9]?[0-9]$" onkeyup="javascript:validationpercentfield(this)" title="{page/captions/cumulativedepreciationtitle/@caption}">
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
												<xsl:value-of select="page/captions/deterioration/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/deterioration}" style="width:40px;" class="td_editable" name="deterioration" pattern="^100$||^[0-9]?[0-9]?[.,]?[0-9]?[0-9]$" onkeyup="javascript:validationpercentfield(this)" title="{page/captions/deteriorationtitle/@caption}">
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
												<b><xsl:value-of select="page/captions/overloadingexistence/@caption"/></b>
											</td>
										</tr>
										<!-- Залог -->
										<tr>
											<td class="fc">
												<xsl:value-of select="page/captions/pledge/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/pledge}" style="width:500px;" class="td_editable" name="pledge" title="{page/captions/pledgetitle/@caption}">
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
												<xsl:value-of select="page/captions/arrestingbyacourtdecision/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/arrestingbyacourtdecision}" style="width:500px;" class="td_editable" name="arrestingbyacourtdecision" title="{page/captions/arrestingbyacourtdecisiontitle/@caption}">
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
												<xsl:value-of select="page/captions/legalclaim/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/legalclaim}" style="width:500px;" class="td_editable" name="legalclaim" title="{page/captions/legalclaimtitle/@caption}">
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
								<input type="hidden" name="author" value="{page/fields/author/@attrval}"/>
								<input type="hidden" name="doctype" value="{page/@doctype}"/>
								<input type="hidden" name="key" value="{page/@docid}"/>
								<input type="hidden" name="parentdocid" value="{page/@parentdocid}"/>
								<input type="hidden" name="parentdoctype" value="{page/@parentdoctype}"/>
								<input type="hidden" name="isrented" value="{page/fields/isrented}"/>
								<div id="tabs-4">
									<table width="100%" border="0">
										<!-- 	Технический паспорт -->
										<tr>
											<td class="fc">
												<xsl:value-of select="page/captions/technicalpassport/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/technicalpassport}" style="width:500px;" class="td_editable" name="technicalpassport">
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
												<xsl:value-of select="page/captions/propertyarticleincorporation/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/propertyarticlein}" style="width:500px;" class="td_editable" name="propertyarticlein">
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
												<xsl:value-of select="page/captions/resolutiontransferacceptancereport/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{page/fields/restransferacceptance}" style="width:500px;" class="td_editable" name="restransferacceptance">
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
												<xsl:value-of select="page/captions/note/@caption"/>&#xA0;:
											</td>
											<td>
												<textarea type="text" value="{page/fields/note}" style="width:500px;" class="textarea_editable" name="note">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">textarea_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
													<xsl:value-of select="page/fields/note"/>
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