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
					<xsl:if test="document/@status = 'new'">
						<xsl:value-of select="document/captions/new/@caption"/>&#xA0;<xsl:value-of select="lower-case($doctype)"/>&#xA0;-  АИС 'Коммунальное Имущество'
					</xsl:if>
					<xsl:if test="document/@status != 'new'">
						<xsl:value-of select="$doctype"/>&#xA0;  - АИС 'Коммунальное Имущество'
					</xsl:if>
				</title>
				<xsl:call-template name="cssandjs"/>
				<xsl:call-template name="markisread"/>
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
							$('#orgregdate, #regdateorder').datepicker({
								showOn: 'button',
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								buttonText: "Изменить дату",
								regional:['ru'],
								showAnim: '',
							});
						});
					</script>
				</xsl:if>
				<xsl:if test="/request/@lang = 'KAZ'">
					<script>
						$(function() {
							$('#orgregdate, #regdateorder').datepicker({
								showOn: 'button',
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								regional:['ru'],
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
					<xsl:if test="/request/@lang = 'ENG'">
						<script>
							$(function() {
								$('#orgregdate, #regdateorder').datepicker({
									showOn: 'button',
									buttonImage: '/SharedResources/img/iconset/calendar.png',
									buttonImageOnly: true,
									regional:['ru'],
									showAnim: '',
									monthNames: ['January','February','March','April','May','June',
									'July','August','September','October','November','December'],
									monthNamesShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
									'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
									dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
									dayNamesShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
									dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa'],
									weekHeader: 'Wk',
									firstDay: 1,
									isRTL: false,
									showMonthAfterYear: false,
								});
									
							});
						</script>
					</xsl:if>
				</xsl:if>
		</head>
		<body>
			<xsl:variable name="status" select="@status"/>
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
										
										<!-- Полное наименование учреждения -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/orgfullname/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/orgfullname}" style="width:500px;" class="td_editable" name="orgfullname">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Полное наименование учреждения -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/orgfullnamekaz/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/orgfullnamekaz}" style="width:500px;" class="td_editable" name="orgfullnamekaz">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Полный юридический адрес -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/fulllegaladdress/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/fulllegaladdress}" style="width:500px;" class="td_editable" name="fulllegaladdress">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										
										<!-- 	Полный фактический адрес -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/fullactualaddress/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/fullactualaddress}" style="width:500px;" class="td_editable" name="fullactualaddress">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										
										<!-- 	Контактный телефон -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/phone/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/phone}" style="width:500px;" class="td_editable" name="phone">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Факс-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/fax/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/fax}" style="width:500px;" class="td_editable" name="fax">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Электронный адрес-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/email/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/email}" style="width:500px;" class="td_editable" name="email">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!--  БИН   -->
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
										<!--  ФИО Руководителя   -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/rukfullname/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/rukfullname}" style="width:500px;" class="td_editable" name="rukfullname">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!--  ФИО Гл. Бухгалтера-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/glavbuhfullname/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/glavbuhfullname}" style="width:500px;" class="td_editable" name="glavbuhfullname">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!--  Идентификационный код по ОКПО-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/okpo/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/okpo}" style="width:500px;" class="td_editable" name="okpo">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!--  Вид деятельности по ОКЭД -->
										<!--<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/oked/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="oked" select="document/fields/oked/@attrval" />
												<select size="1" name="oked" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
														<option value="">
															<xsl:attribute name="selected">selected</xsl:attribute>
															<xsl:value-of select="document/fields/oked"/>
														</option>
													</xsl:if>
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
										</tr>-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/oked/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/oked}" style="width:500px;" class="td_editable" name="oked">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Вид деятельности в соответствии с учредительными документами -->
										<!--<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/typeactivity/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="typeactivity" select="document/fields/typeactivity/@attrval"/>
												<select size="1" name="typeactivity" style="width:611px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
														<option value="">
															<xsl:attribute name="selected">selected</xsl:attribute>
															<xsl:value-of select="document/fields/typeactivity"/>
														</option>
													</xsl:if>
													<xsl:for-each select="document/glossaries/kvd/query/entry">
														<option value="{@docid}">
															<xsl:if test="$typeactivity = @docid">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="viewcontent/viewtext1"/>
														</option>
													</xsl:for-each>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="typeactivity" value="{document/fields/typeactivity/@attrval}"/>
												</xsl:if>
											</td>
										</tr>-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/typeactivity/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/typeactivity}" style="width:500px;" class="td_editable" name="typeactivity">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Дата Гос. регистрации -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/orgregdate/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="orgregdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/orgregdate,1,10)}">
													<xsl:if test="$editmode = 'edit'">
														<xsl:attribute name="id">orgregdate</xsl:attribute>
													</xsl:if>
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Номер Гос. регистрации-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/orgregnumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/orgregnumber}" style="width:500px;" class="td_editable" name="orgregnumber">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										
										<!-- 	Дата постановления РК о создании (реорганизации)-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/regdateorder/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="regdateorder" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/regdateorder,1,10)}">
													<xsl:if test="$editmode = 'edit'">
														<xsl:attribute name="id">regdateorder</xsl:attribute>
													</xsl:if>
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Номер постановления РК о создании (реорганизации)-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/numberorder/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/numberorder}" style="width:500px;" class="td_editable" name="numberorder">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Вид деятельности в соответствии с учредительными документами 
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/typeactivity/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/typeactivity}" style="width:500px;" class="td_editable" name="typeactivity">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>-->
										<!-- 	Численность штата  -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/stuffcount/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/stuffcount}" style="width:500px;" class="td_editable" name="stuffcount">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Показатели инвестиций  -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/indexinvestments/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/indexinvestments}" style="width:500px;" class="td_editable" name="indexinvestments">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Рентабельность  -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/profitability/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/profitability}" style="width:500px;" class="td_editable" name="profitability">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Производительность труда  -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/laborproductivity/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/laborproductivity}" style="width:500px;" class="td_editable" name="laborproductivity">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Льготы -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/privilege/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="checkbox" value="1" name="privilege">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
													<xsl:if test="document/fields/privilege = '1'">
														<xsl:attribute name="checked">checked</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
                                        <!-- 	Льготы -->
                                        <tr>
                                            <td class="fc">
                                                <xsl:value-of select="document/captions/for_reg/@caption"/>&#xA0;:
                                            </td>
                                            <td>
                                                <input type="checkbox" value="1" name="registration">
                                                    <xsl:if test="$editmode != 'edit'">
                                                        <xsl:attribute name="disabled">disabled</xsl:attribute>
                                                    </xsl:if>
                                                    <xsl:if test="document/fields/registration = '1'">
                                                        <xsl:attribute name="checked">checked</xsl:attribute>
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
								<input type="hidden" name="allcontrol" value="{document/fields/allcontrol}"/>
								<input type="hidden" name="doctype" value="{document/@doctype}"/>
								<input type="hidden" name="key" value="{document/@docid}"/>
								<input type="hidden" name="parentdocid" value="{document/@parentdocid}"/>
								<input type="hidden" name="parentdoctype" value="{document/@parentdoctype}"/>
								<input type="hidden" name="page" value="{document/@openfrompage}"/>
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