<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/form.xsl"/>
	<xsl:import href="../templates/sharedactions.xsl"/>
		<xsl:import href="../templates/reportform.xsl" />
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
					Отчет недвижимое имущество -  АИС 'Коммунальное Имущество'
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
									<xsl:with-param name="title">Отчет недвижимое имущество</xsl:with-param>
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
										<xsl:call-template name="balanceholderprops" />
										<!-- Тип имущества-->
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"><xsl:value-of select="document/captions/propertytype/@caption"/> :</td>
											<td>
												<input type="checkbox" name="propertytype" value="buildings"/>&#xA0;<xsl:value-of select="document/captions/buildings/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="rooms"/>&#xA0;<xsl:value-of select="document/captions/rooms/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="structures"/>&#xA0;<xsl:value-of select="document/captions/structures/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="residentialobjects"/>&#xA0;<xsl:value-of select="document/captions/residentialobjects/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="land"/>&#xA0;<xsl:value-of select="document/captions/land/@caption"/>
											</td>
										</tr>
										<tr>
											<td class="fc" style="padding:5px;position:relative;top:0px"></td>
											<td>
												<input type="checkbox" name="propertytype" value="monument"/>&#xA0;<xsl:value-of select="document/captions/monument/@caption"/>
											</td>
										</tr>
										<xsl:call-template name="propertycodeprops" />
										<xsl:call-template name="addressprops" />
										<xsl:call-template name="reporttypeprops" />
									</table>
								</div>
								<!-- Скрытые поля -->
								<input type="hidden" name="type" value="page"/>
								<input type="hidden" name="id" value="{@id}"/>
								<input type="hidden" name="author" value="{document/fields/author/@attrval}"/>
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