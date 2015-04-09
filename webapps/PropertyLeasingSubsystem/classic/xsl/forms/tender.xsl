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
				<script type="text/javascript">
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
							$('#regdate ').datepicker({
								showOn: 'button',
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								changeMonth: true,
								changeYear: true,
								buttonText: "Изменить дату",
								regional:['ru'],
								showAnim: '',
							});
						});
						
						$(function() {
							var dates = $( "#startdate, #enddate" ).datepicker({
							defaultDate: "+1w",
							showOn: "button",
							buttonImage: '/SharedResources/img/iconset/calendar.png',
							buttonImageOnly: true,
							changeMonth: true,
							changeYear: true,
							numberOfMonths: 1,
							onSelect: function( selectedDate ) {
								var option = this.id == "startdate" ? "minDate" : "maxDate",
								instance = $( this ).data( "datepicker" ),
								date = $.datepicker.parseDate(
								instance.settings.dateFormat ||
								$.datepicker._defaults.dateFormat,
								selectedDate, instance.settings );
								dates.not( this ).datepicker( "option", option, date );
								}
							});
						});
						
						 
					</script>
				</xsl:if>
				<xsl:if test="/request/@lang = 'KAZ'">
					<script>
						$(function() {
							$('#regdate').datepicker({
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
							
						$(function() {
							var dates = $( "#startdate, #enddate" ).datepicker({
							defaultDate: "+1w",
							showOn: "button",
							buttonImage: '/SharedResources/img/iconset/calendar.png',
							buttonImageOnly: true,
							changeMonth: true,
							changeYear: true,
							monthNames: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
								'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
								monthNamesShort: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
								'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
								dayNames: ['жексебі','дүйсенбі','сейсенбі','сәрсенбі','бейсенбі','жұма','сенбі'],
								dayNamesShort: ['жек','дүй','сей','сәр','бей','жұм','сен'],
								dayNamesMin: ['Жс','Дс','Сс','Ср','Бс','Жм','Сн'],
							numberOfMonths: 1,
							onSelect: function( selectedDate ) {
								var option = this.id == "startdate" ? "minDate" : "maxDate",
								instance = $( this ).data( "datepicker" ),
								date = $.datepicker.parseDate(
								instance.settings.dateFormat ||
								$.datepicker._defaults.dateFormat,
								selectedDate, instance.settings );
								dates.not( this ).datepicker( "option", option, date );
								}
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
							<xsl:call-template name="doctitle"/>
						</div>
						<div style="float:right; padding-left:5px">
						</div>
					</div>
					<div class="button_panel">
						<span style="width:80%; vertical-align:12px; float:left">
							<xsl:call-template name="showxml"/>
							<xsl:call-template name="save"/>
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
							<xsl:if test="document/@status !='new'">
								<li class="ui-state-default ui-corner-top">
									<a href="#tabs-4">Договор</a>
								</li>
							</xsl:if>
						</ul>
						
							<form action="Provider" name="frm" method="post" id="frm" enctype="application/x-www-form-urlencoded">
								<div class="ui-tabs-panel" id="tabs-1">
									<br/>
									<table width="100%" border="0">
										
										
										<!-- Объект -->
										<tr>
											<td class="fc">
												Объект&#xA0;:
											</td>
											<td>
												<a class="doclink" href="{document/fields/objecturl}">
													<xsl:value-of select="document/fields/objectname"/>
												</a>
											</td>
										</tr>
										<!-- Адрес -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/address/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/address}" style="width:500px;" class="td_noteditable" readonly="readonly"/>
												
											</td>
										</tr>
										<!-- Общая площадь -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/totalarea/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/totalarea}" style="width:120px;" class="td_noteditable" readonly="readonly" autocomplete="off"/>
											</td>
										</tr>
										<!-- наименование наймодателя -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<font style="vertical-align:top">
													<xsl:value-of select="document/captions/owner/@caption"/> :
												</font>
											</td>
											<td style="padding-top:5px">
                                                <input type="text" value="{document/fields/owner}" name="leasingholder" style="width:500px;" class="td_noteditable" readonly="readonly" autocomplete="off"/>
											</td>
										</tr>
                                        <!-- rent_cost -->
                                        <tr>
                                            <td class="fc">
                                                <xsl:value-of select="document/captions/rent_cost/@caption"/>&#xA0;:
                                            </td>
                                            <td>
                                                <input type="text" value="{document/fields/rent_cost}" style="width:120px;" class="td_noteditable" readonly="readonly" autocomplete="off"/>
                                            </td>
                                        </tr>
										<!-- 	Дата регистрации -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/regdate/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="regDate" maxlength="10" autocomplete="off" class="td_noteditable" style="width:80px; vertical-align:top" value="{substring(document/fields/regDate,1,10)}" readonly="readonly">
													<!--<xsl:if test="$editmode = 'edit'">-->
														<!--<xsl:attribute name="id">regdate</xsl:attribute>-->
													<!--</xsl:if>-->
													<!--<xsl:if test="$editmode != 'edit'">-->
														<!--<xsl:attribute name="class">td_noteditable</xsl:attribute>-->
													<!--</xsl:if>-->
												</input>
											</td>
										</tr>

										<!-- 	Начало аренды -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/closedate/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="closeDate" maxlength="10" autocomplete="off" class="td_noteditable" style="width:80px; vertical-align:top" value="{substring(document/fields/closeDate,1,10)}" readonly="readonly">

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
							</form>
							<div id="tabs-2" style="height:500px">
								<form action="Uploader" name="upload" id="upload" method="post" enctype="multipart/form-data">
									<!-- Секция "Вложения" -->
									<div display="block" id="att">

										<div style="max-width:900px; width:900px">
											<ul>
												<style>
													ul{
														padding-left:10px
													}
													ol{
													  padding-left:25px;
													  list-style: none;
													  counter-reset: num;
													}
													ol li:before{
													  content: counters(num,'.') '. ';
													  counter-increment: num;
													}  
												</style>
												<div style="text-align:center; font-size:16px; font-weight:bold">
													Список документов необходимых для подачи заявки на аренду объекта
												</div>
												<br/>
												<ol>
													<li>
														Для юридических лиц: 
														<ol>
															<li>
															   копии справки или свидетельства о государственной регистрации (перерегистрации) юридического лица
															</li> 
															<li>
															   учредительные документы (учредительный договор и устав) с обязательным предъявлением оригинала для 
															   сверки либо нотариально засвидетельствованные копии указанных документов;
															</li>
														</ol>
													</li>
													<li>
														Для физических лиц:
														<ol>
															<li>
															    копия свидетельства о государственной регистрации индивидуального предпринимателя
															</li>
															<li>
															   копия документа удостоверяющего личность физического лица
															</li>
															<li>
															    копия книги регистрации граждан (адресной справки) с обязательным предъявлением оригинала 
															    для сверки либо нотариально засвидетельствованные копии указанных документов;
															</li>
														</ol>
													</li>
													<li>
														Для акционерных обществ:
														<ol>
															<li>
																выписку из реестра держателей ценных бумаг
															</li>
														</ol> 
													</li>
													<li>
														Для акционерных обществ:
														<ol>
															<li>
																выписку из реестра держателей ценных бумаг
															</li>
														</ol> 
													</li>
													<li>
														Для товариществ с ограниченной ответственностью: 
														<ol>
															<li>
																выписку из реестра участников товарищества (в случае ведения реестра участников товарищества);
															</li>
														</ol>
													</li>
													<li>
														Для иностранных юридических лиц:
														<ol>
															<li>
																учредительные документы с нотариально заверенным переводом на государственный и русский языки;
															</li>
														</ol>
													</li>
													<li>
														Для всех:
														<ol>
															<li>
																справку налогового органа об отсутствии налоговой задолженности нанимателя, выданной не позднее, чем за месяц 
																до момента подачи заявки
															</li>
														</ol>
													</li>
												</ol>
											</ul>
										</div>
                                        <br/>
                                        <xsl:call-template name="attach"/>
                                        <br/>
                                        <input type="hidden" name="type" value="rtfcontent"/>
                                        <input type="hidden" name="formsesid" value="{formsesid}"/>
									</div>
								</form>
							</div>
							<div id="tabs-3">
								<xsl:call-template name="docinfo"/>
							</div>
							<xsl:if test="document/@status !='new'">
								<div id="tabs-4">
									<xsl:call-template name="docleasingcontract"/>
								</div>
							</xsl:if>
						</div>
						<div style="height:10px"/>
					</div>
				</div>
				<xsl:call-template name="formoutline"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>