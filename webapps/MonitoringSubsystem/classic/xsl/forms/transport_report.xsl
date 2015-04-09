<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/form.xsl"/>
	<xsl:import href="../templates/sharedactions.xsl"/>
	<xsl:variable name="doctype">Отчет</xsl:variable>
	<xsl:variable name="path" select="/request/@skin"/>
	<xsl:variable name="threaddocid" select="document/@granddocid"/>
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="yes"/>
	<xsl:variable name="skin" select="request/@skin"/>
	<xsl:variable name="editmode" select="/request/document/@editmode"/>
	<xsl:template match="/request">
		<html>
			<head>
				<title>
					Отчет транспорт -  АИС 'Коммунальное Имущество'
				</title>
				<xsl:call-template name="cssandjs"/>
				<xsl:call-template name="htmlareaeditor"/>
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
									     	$("#generatereport").click();
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
							$("#generatereport").hotnav({keysource:function(e){ return "s"; }});
							$("#currentuser").hotnav({ keysource:function(e){ return "u"; }});
							$("#logout").hotnav({keysource:function(e){ return "q"; }});
							$("#helpbtn").hotnav({keysource:function(e){ return "h"; }});
						}
					]]>
				</script>
			<xsl:if test="document/@editmode='edit'">
				<xsl:if test="/request/@lang = 'RUS'">
					<script>
						$(function(){
							var dates = $( "#acceptancedatefrom, #acceptancedateto" ).datepicker({
							defaultDate: "+1w",
							showOn: "button",
							buttonImage: '/SharedResources/img/iconset/calendar.png',
							buttonImageOnly: true,
							changeMonth: true,
							changeYear: true,
							numberOfMonths: 1,
							onSelect: function( selectedDate ) {
								var option = this.id == "acceptancedatefrom" ? "minDate" : "maxDate",
								instance = $( this ).data( "datepicker" ),
								date = $.datepicker.parseDate(
								instance.settings.dateFormat ||
								$.datepicker._defaults.dateFormat,
								selectedDate, instance.settings );
								dates.not(this).datepicker("option", option, date);
								}
							});
						});
					</script>
				</xsl:if>
				<xsl:if test="/request/@lang = 'KAZ'">
					<script>
						$(function(){
							var dates = $( "#acceptancedatefrom, #acceptancedateto" ).datepicker({
								defaultDate: "+1w", 
								showOn: "button",
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								changeMonth: true,
								changeYear: true,
								numberOfMonths: 1,
								monthNames: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
								'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
								monthNamesShort: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
								'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
								dayNames: ['жексебі','дүйсенбі','сейсенбі','сәрсенбі','бейсенбі','жұма','сенбі'],
								dayNamesShort: ['жек','дүй','сей','сәр','бей','жұм','сен'],
								dayNamesMin: ['Жс','Дс','Сс','Ср','Бс','Жм','Сн'],
								onSelect: function( selectedDate ) {
									var option = this.id == "acceptancedatefrom" ? "minDate" : "maxDate",
									instance = $( this ).data( "datepicker" ),
									date = $.datepicker.parseDate(
									instance.settings.dateFormat ||
									$.datepicker._defaults.dateFormat,
									selectedDate, instance.settings );
									dates.not( this ).datepicker("option", option, date);
								}
							});
						});
					</script>
				</xsl:if>			
				</xsl:if>
			</head>
			<body>
				<xsl:if test="@status!='new'">
					<xsl:attribute name="onLoad">javascript:onLoadActions()</xsl:attribute>
				</xsl:if>
				<div id="docwrapper">
					<xsl:call-template name="documentheader"/>	
					<div class="formwrapper">
						<div class="formtitle">
							<div style="float:left" class="title">
								<xsl:call-template name="documentitle-reports">
									<xsl:with-param name="title">Отчет транспорт</xsl:with-param>
								</xsl:call-template>
							</div>
							<div style="float:right; padding-right:5px">
							</div>
						</div>
						<div class="button_panel">
							<span style=" vertical-align:12px; float:left">
								<xsl:call-template name="showxml"/>
								<!-- <xsl:if test="document/fields/allcontrol != '0' and document/@editmode ='edit'"> -->
									<xsl:call-template name="filling_report"/>
							<!-- 	</xsl:if> -->
							</span>
							<span style="float:right" class="bar_right_panel">
								<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" title="{document/captions/close/@caption}" id="canceldoc">
									<xsl:attribute name="onclick">javascript:CancelForm(&quot;<xsl:value-of select="history/entry[@type eq 'view'][last()]"/>&quot;,&quot;<xsl:value-of select="document/fields/grandparform"/>&quot;)</xsl:attribute>
									<span>
										<img src="/SharedResources/img/iconset/cross.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/captions/close/@caption"/></font>
									</span>
								</button>
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
							</ul>
							<form action="Provider" name="frm" method="post" id="frm" enctype="application/x-www-form-urlencoded">
								<div class="ui-tabs-panel" id="tabs-1">
									<br/>
								 	<table width="100%" border="0">
										<!-- поле "Балансодержатель" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<font style="vertical-align:top">
													<xsl:value-of select="document/captions/balanceholder/@caption"/> :
												</font>
												<xsl:if test="$editmode = 'edit'">
													<a>
														<xsl:attribute name="href">javascript:dialogBoxStructure('balanceholder','true','balanceholder','frm', 'balanceholdertbl');</xsl:attribute>
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
										<!-- Дата принятия на баланс -->
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"><xsl:value-of select="document/captions/acceptancedate/@caption"/> :</td>
											<td>
												&#xA0;<label for="from" style="vertical-align:5px;"><xsl:value-of select="document/captions/from/@caption"/></label>&#xA0;
												<input type="text" id="acceptancedatefrom" size="7" name="acceptancedatefrom" value="" style="background:#fff; padding:3px 3px 3px 5px; width:80px; border:1px solid #ccc; vertical-align:top;">
												</input>
												&#xA0;<label for="to" style="vertical-align:5px;"><xsl:value-of select="document/captions/to/@caption"/></label>&#xA0;
												<input type="text" id="acceptancedateto" value="{document/fields/acceptancedateto}" size="7" name="acceptancedateto" style="background:#fff; padding:3px 3px 3px 5px; width:80px; border:1px solid #ccc; vertical-align:top">
												</input>
											</td>
										</tr>
										<!-- Тип имущества-->
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"><xsl:value-of select="document/captions/propertytype/@caption"/> :</td>
											<td>
												<input type="checkbox" name="propertytype" value="automobile"/>&#xA0;<xsl:value-of select="document/captions/automobile/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="cargo"/>&#xA0;<xsl:value-of select="document/captions/cargo/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="dejtransport"/>&#xA0;<xsl:value-of select="document/captions/dejtransport/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="hospitaltransport"/>&#xA0;<xsl:value-of select="document/captions/hospitaltransport/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="officialtransport"/>&#xA0;<xsl:value-of select="document/captions/officialtransport/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="bus"/>&#xA0;<xsl:value-of select="document/captions/bus/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="trolleybus"/>&#xA0;<xsl:value-of select="document/captions/trolleybus/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="tram"/>&#xA0;<xsl:value-of select="document/captions/tram/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="watertransport"/>&#xA0;<xsl:value-of select="document/captions/watertransport/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="taxi"/>&#xA0;<xsl:value-of select="document/captions/taxi/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="specialequipment"/>&#xA0;<xsl:value-of select="document/captions/specialequipment/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="motorcycle"/>&#xA0;<xsl:value-of select="document/captions/motorcycle/@caption"/>
											</td>
										</tr>
										<!-- 	Код права на имущество -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/propertycode/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="propertycode" select="document/fields/propertycode/@attrval" />
												<select size="10" multiple="multiple" name="propertycode" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
														<option value="">
															<xsl:attribute name="selected">selected</xsl:attribute>
															<xsl:value-of select="document/fields/propertycode"/>
														</option>
													</xsl:if>
													<xsl:for-each select="document/glossaries/propertycode/query/entry">
														<option value="{@docid}">
															<xsl:if test="$propertycode = @docid">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:if test="viewcontent/viewtext2 = '005' or viewcontent/viewtext2 = '006' or viewcontent/viewtext2 = '007'">
																<xsl:value-of select="../entry[viewcontent/viewtext2 = '004']/viewcontent/viewtext1"/> - 
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
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
													<option value="">
														<xsl:attribute name="selected">selected</xsl:attribute>
													</option>
													<xsl:for-each select="document/glossaries/region/query/entry">
														<option value="{@docid}">
															<xsl:if test="$region = @docid">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
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
													<option value="">
														<xsl:attribute name="selected">selected</xsl:attribute>
													</option>
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
													<option value="">
														<xsl:attribute name="selected">selected</xsl:attribute>
													</option>
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
												<xsl:variable name="street" select="document/fields/street/@attrval" />
												<select size="1" name="street" style="width:611px;" class="select_editable">
													<option value="">
														<xsl:attribute name="selected">selected</xsl:attribute>
													</option>
													<xsl:for-each select="document/glossaries/street/query/entry">
														<option value="{@docid}">
															<xsl:if test="$street = @docid">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="street" value="{document/fields/street/@attrval}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- Поле "дом" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/home/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<input type="text" style="width:500px; padding:2px;" name="home" value="{document/fields/home}" class="td_editable" autocomplete="off">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:7px;"><xsl:value-of select="document/captions/reportfiletype/@caption"/> :</td>
											<td>
												<table>
													<tr>
														<td>
															<input type="radio" name="typefilereport" value="2">
																<xsl:attribute name="onclick">javascript: reportsTypeCheck(this)</xsl:attribute>
																<xsl:if test="document/@editmode !='edit'">
																	<xsl:attribute name="disabled">disabled</xsl:attribute>
																</xsl:if>
																<xsl:if test="document/fields/typefilereport  = '2'">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
																<xsl:if test="document/@status  = 'new'">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
																XLS
															</input>
														</td>
														 <td>
															<input type="radio" name="typefilereport" value="1">
																<xsl:attribute name="onclick">javascript: reportsTypeCheck(this)</xsl:attribute>
																<xsl:if test="document/@editmode !='edit'">
																	<xsl:attribute name="disabled">disabled</xsl:attribute>
																</xsl:if>
																<xsl:if test="document/fields/typefilereport  = '1'">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
																
																PDF
															</input>
														</td>
														<!--<td>
															<input type="radio" name="typefilereport" value="3">
																<xsl:attribute name="onclick">javascript: reportsTypeCheck(this)</xsl:attribute>
																<xsl:if test="document/@editmode !='edit'">
																	<xsl:attribute name="disabled">disabled</xsl:attribute>
																</xsl:if>
																<xsl:if test="document/fields/typefilereport  = '3'">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
																HTML
															</input>
														</td> -->
													</tr>
												</table>
											</td>
										</tr>
										  <!-- <tr>
											<td class="fc" style = "padding:7px;"><xsl:value-of select="document/captions/openreport/@caption"/> :</td>
											<td>
												<table>
													<tr>
														<td> 
															<input type="radio" name="disposition" value="attachment">
																<xsl:if test="document/@editmode='noaccess'">
																	<xsl:attribute name="disabled">disabled</xsl:attribute>
																</xsl:if>
																<xsl:if test="document/fields/disposition  = 'attachment'">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
																<xsl:if test="document/@status  = 'new'">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
																<xsl:value-of select="document/captions/bydefaultinprogram/@caption"/>
															</input>
														</td>
														<td>
															<input type="radio" name="disposition" value="inline">
																<xsl:if test="document/@editmode='noaccess'">
																	<xsl:attribute name="disabled">disabled</xsl:attribute>
																</xsl:if>
																<xsl:if test="document/fields/disposition  = 'inline'">
																	<xsl:attribute name="checked">checked</xsl:attribute>
																</xsl:if>
																<xsl:value-of select="document/captions/inbrowserwindow/@caption"/>
															</input>
														</td>
													</tr>
												</table>
											</td>
										</tr> --> 
									</table>
								</div>
								<!-- Скрытые поля -->
								<input type="hidden" name="type" value="save"/>
								<input type="hidden" name="id" value="{@id}"/>
								<input type="hidden" name="author" value="{document/fields/author/@attrval}"/>
								<input type="hidden" name="doctype" value="{document/@doctype}"/>
								<input type="hidden" name="key" value="{document/@docid}"/>
							</form>
							<div id="executers" style="display:none">
								<table style="width:100%">
									<xsl:for-each select="document/fields/executors/entry">
											<tr>
												<td>
													<input type="checkbox" name="chbox" value="{user}" id="{user/@attrval}">
														<xsl:if test="user/@attrval =''">
															<xsl:attribute name="disabled">disabled</xsl:attribute>
														</xsl:if>
													</input>	
												</td>
												<td>
													<font class="font"  style="font-family:verdana; font-size:13px; margin-left:2px">
														<xsl:if test="user/@attrval =''">
															<xsl:attribute name="color">gray</xsl:attribute>
														</xsl:if>
														<xsl:value-of select="user"/> 
													</font>
												</td>
											</tr>
									</xsl:for-each>
								</table>
							</div>
							<input type="hidden" id="currentuser" value="{@userid}"/>
							<input type="hidden" id="localusername" value="{@username}"/>
						</div>
						<div style="height:10px"/>
					</div>
				</div>
				<xsl:call-template name="formoutline"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>