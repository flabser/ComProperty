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
							$('#regdate, #contractdate').datepicker({
								showOn: 'button',
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								changeMonth: true,
								changeYear: true,
								buttonText: "Изменить дату регистрации",
								regional:['ru'],
								showAnim: '',
							});
						});
					</script>
				</xsl:if>
				<xsl:if test="/request/@lang = 'KAZ'">
					<script>
						$(function() {
							$('#regdate, #contractdate').datepicker({
								showOn: 'button',
								buttonImage: '/SharedResources/img/iconset/calendar.png',
								buttonImageOnly: true,
								changeMonth: true,
								changeYear: true,
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
									<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=contract&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Добавить договор</font>
									</span>
								</button>
								<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
									<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=act&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Добавить акт</font>
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
										
										<!-- Регистрационный номер-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/regnumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/regnumber}" style="width:90px;" class="td_editable" name="regnumber">
													<xsl:attribute name="readonly">readonly</xsl:attribute>
													<xsl:attribute name="class">td_noteditable</xsl:attribute>
													<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
												</input>
											</td>
										</tr>
										<!-- Объект -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/object/@caption"/>&#xA0;:
											</td>
											<td>
												<a class="doclink" href="{document/fields/objecturl}">
													<xsl:value-of select="document/fields/objectname"/>
												</a>
											</td>
										</tr>
										<!-- поле "Организация" -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<font style="vertical-align:top">
													<xsl:value-of select="document/captions/organization/@caption"/> :
												</font>
												<xsl:if test="$editmode = 'edit'">
													<a>
														<xsl:attribute name="href">javascript:dialogBoxStructure('balanceholder','false','organization','frm', 'organizationtbl');</xsl:attribute>
														<img src="/SharedResources/img/iconset/report_magnify.png"/>
													</a>
												</xsl:if>
											</td>
											<td style="padding-top:5px">
												<table id="organizationtbl" style="border-spacing:0px 3px; margin-top:-3px">
													<tr>
														<td style="width:600px;" class="td_editable">
															<xsl:if test="$editmode != 'edit'">
																<xsl:attribute name="class">td_noteditable</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="document/fields/organizationname"/>&#xA0;
															<span style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'><font><xsl:value-of select="document/fields/corr/@attrval"/></font></span>
														</td>
													</tr>
												</table>
												<input type="hidden" value="{document/fields/organization}" id="organization" name="organization"/>
												<input type="hidden" value="{document/captions/organization/@caption}" id="organizationcaption"/>
											</td>
										</tr>
										<!-- 	Код права на имущество -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/propertycode/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="propertycode" select="document/fields/propertycode/@attrval" />
												<select size="1" name="propertycode" style="width:611px;" class="select_editable">
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
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="propertycode" value="{document/fields/propertycode/@attrval}"/>
												</xsl:if>
											</td>
										</tr>
										<!-- 	Наименование постановления -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/content/@caption"/>&#xA0;:
											</td>
											<td>
												<textarea name="content" rows="3" class="textarea_editable" style="width:700px">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">textarea_noteditable</xsl:attribute>
													</xsl:if>
													<xsl:if test="document/@editmode = 'edit'">
														<xsl:attribute name="onfocus">fieldOnFocus(this)</xsl:attribute>
														<xsl:attribute name="onblur">fieldOnBlur(this)</xsl:attribute>
													</xsl:if>
													<xsl:value-of select="document/fields/content"/>
												</textarea>
											</td>
										</tr>
										<!-- 	Дата регистрации -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/regdate/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="regdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/regdate,1,10)}">
													<xsl:if test="$editmode = 'edit'">
														<xsl:attribute name="id">regdate</xsl:attribute>
													</xsl:if>
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Ввод в действие -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/contractdate/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="contractdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/contractdate,1,10)}">
													<xsl:attribute name="class">td_noteditable</xsl:attribute>
												</input>
											</td>
										</tr>
										<!-- 	Статус -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<xsl:value-of select="document/captions/status/@caption"/> :
											</td>
											<td style="padding-top:5px">
												<select size="1" name="status" style="width:150px;" class="select_editable">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
													<option value="1">
														<xsl:if test="document/fields/status = '1'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Активно
													</option>
													<option value="0">
														<xsl:if test="document/fields/status = '0'">
															<xsl:attribute name="selected">selected</xsl:attribute>
														</xsl:if>
														Неактивно
													</option>
												</select>
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="status" value="{document/fields/status}"/>
												</xsl:if>
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