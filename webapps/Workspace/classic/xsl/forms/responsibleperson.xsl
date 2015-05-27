<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/form.xsl"/>
	<xsl:import href="../templates/sharedactions.xsl"/>
	<xsl:variable name="doctype" select="request/document/captions/name/@caption"/>
	<xsl:variable name="editmode" select="/request/document/@editmode"/>
	<xsl:variable name="threaddocid" select="document/@docid"/>
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="yes"/>
	<xsl:variable name="skin" select="request/@skin"/>
	<xsl:template match="/request">
		<html>
			<head>
				<title>
					<xsl:value-of select="$doctype"/> - АИС 'Коммунальное Имущество'
				</title>
				<xsl:call-template name="cssandjs"/>
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
			</head>
			<body style="background:#fff !important;">
				<xsl:variable name="status" select="@status"/>
				<div id="docwrapper">
					<xsl:call-template name="documentheader"/>	
					<div class="formwrapper">
						<div class="formtitle">
						   	<div class="title">
						   		Регистрация								
							</div>
						</div>
						<div class="button_panel">
							<span style="float:left">
								<xsl:call-template name="showxml"/>
								<button title ="{document/captions/saveclose/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnsavedoc">
									<xsl:attribute name="onclick">javascript:SaveFormJquery(&quot;<xsl:value-of select="history/entry[@type eq 'page'][last()]"/>&amp;page=<xsl:value-of select="document/@openfrompage"/>&quot;)</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/disk.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/captions/saveclose/@caption"/></font>
									</span>
								</button>
							</span>
							<span style="float:right; margin-right:5px">
								<button title= "{document/captions/close/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="canceldoc">
									<xsl:attribute name="onclick">javascript:window.history.back()</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/cross.png" class="button_img"/>
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
									<a href="#tabs-1">
										<xsl:value-of select="document/captions/properties/@caption"/>
									</a>
								</li>
                                <li class="ui-state-default ui-corner-top">
                                    <a href="#tabs-2">
                                        Вложения

                                    </a>
                                </li>
                                <li class="ui-state-default ui-corner-top">
                                    <a href="#tabs-3">
                                       <!-- <xsl:value-of select="document/captions/documentation/@caption"/>-->
                                        <xsl:value-of select="document/captions/documentation/@caption"/>
                                    </a>
                                </li>
                            </ul>
							<div class="ui-tabs-panel" id="tabs-1">
								<form action="Provider" name="frm" method="post" id="frm" enctype="application/x-www-form-urlencoded">
									<div display="block" id="property">
										<br/>
										<table width="100%" border="0">
										<!-- ФИО -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/fio/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/fio}" style="width:500px;" class="td_editable" name="fio">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Логин -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/login/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/login}" style="width:300px;" class="td_editable" name="login">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
                                                    <xsl:if test="$editmode = 'edit'">
                                                        <xsl:attribute name="onblur">javascript:checkLogin(this.value)</xsl:attribute>
                                                    </xsl:if>
												</input>
                                                <xsl:if test="document/@status = 'new'">
                                                    <span style="padding-left:5px"><img  id="loginStatusImg" src="/SharedResources/img/iconset/cross.png" style="width:10px; opacity:0.5" /></span>
                                                </xsl:if>
											</td>
										</tr>
										<!-- Пароль -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/password/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="password" value="{document/fields/password}" style="width:300px;" class="td_editable" name="password">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- Повтор пароля -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/reenterpassword/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="password" value="{document/fields/reenterpassword}" style="width:300px;" class="td_editable" name="reenterpassword">
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
												<input type="text" value="{document/fields/phone}" style="width:400px;" class="td_editable" name="phone">
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
												<input type="text" value="{document/fields/email}" style="width:300px;" class="td_editable" name="email">
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
                                                <font style="vertical-align:top">
												    <xsl:value-of select="document/captions/orgname/@caption"/> :
                                                </font>
                                                <xsl:if test="$editmode = 'edit'">
                                                    <a>
                                                        <xsl:attribute name="href">javascript:dialogBoxStructure('institution','false','institution','frm', 'institutiontbl');</xsl:attribute>
                                                        <img src="/SharedResources/img/iconset/report_magnify.png"/>
                                                    </a>
                                                </xsl:if>
											</td>

                                            <td style="padding-top:5px">
                                                <table id="institutiontbl" style="border-spacing:0px 3px; margin-top:-3px">
                                                    <tr>
                                                        <td style="width:600px;" class="td_editable">
                                                            <xsl:if test="$editmode != 'edit'">
                                                                <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                            </xsl:if>
                                                            <xsl:value-of select="document/fields/institutionname"/>&#xA0;
                                                            <span style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'><font><xsl:value-of select="document/fields/institution/@attrval"/></font></span>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <input type="hidden" value="{document/fields/institution}" id="institution" name="institution"/>
                                                <input type="hidden" value="{document/captions/institution/@caption}" id="institutioncaption"/>
                                                <input type="hidden" value="{document/fields/institution_name}" id="institution_name" name="institution_name"/>

                                            </td>
										</tr>
										<!-- <tr>
											<td class="fc" style="padding-top:5px">
												<font style="vertical-align:top">
													<xsl:value-of select="document/captions/orgname/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/> :
												</font>
												<xsl:if test="$editmode = 'edit'">
													<a>
														<xsl:attribute name="href">javascript:dialogBoxStructure('balanceholder','false','orgname','frm', 'orgnametbl');</xsl:attribute>
														<img src="/SharedResources/img/iconset/report_magnify.png"/>
													</a>
												</xsl:if>
											</td>
											<td style="padding-top:5px">
												<table id="orgnametbl" style="border-spacing:0px 3px; margin-top:-3px">
													<tr>
														<td style="width:600px;" class="td_editable">
															<xsl:if test="$editmode != 'edit'">
																<xsl:attribute name="class">td_noteditable</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="document/fields/orgname"/>&#xA0;
															<span style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'><font><xsl:value-of select="document/fields/corr/@attrval"/></font></span>
														</td>
													</tr>
												</table>
												<input type="hidden" value="{document/fields/orgname}" id="orgname" name="orgname"/>
												<input type="hidden" value="{document/captions/orgname/@caption}" id="orgnamecaption"/>
											</td>
										</tr> -->
									</table>		
									</div>   
									<input type="hidden" name="type" value="save"/>
									<input type="hidden" name="id" value="{@id}"/>		
									<input type="hidden" name="key" value="{document/@docid}"/>
                                    <input type="hidden" name="parentdocid" value="1"/>
                                    <input type="hidden" name="parentdoctype" value="888"/>
                                    <input type="hidden" name="doctype" value="{document/@doctype}"/>
                                    <input type="hidden" name="formsesid" value="{formsesid}"/>
                                </form>
					    	</div>
                            <div class="ui-tabs-panel" id="tabs-3">
                                <div display="block" id="">
                                    <table width="100%" border="0">
                                       <!-- <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href='/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=ais_pattern.xls' >Шаблон для АИС.xls</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href="/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=responsible_person_manual.docx" >Инструкция для Ответственого по загрузке данных-2.docx</a>
                                            </td>
                                        </tr>  -->
                                        <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href="/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=Анкета.docx" >Анкета.docx</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href="/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=Пример_EXCEL_файла.xlsx" >Пример excel файла.xlsx</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href="/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=инструкция по загрузки.docx" >Инструкция по загрузки.docx</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href="/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=Формат_данных_и_КУФ.docx" >Формат данных и КУФ.docx</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href="/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=справочник коф.doc" >Справочник КОФ.doc</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="fc"></td>
                                            <td>
                                                <a style="color:blue;" href="/Workspace/Provider?type=page&amp;id=downloadfile&amp;filename=шаблон_exel_файла.xls" >Шаблон_excel_файла.xls</a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div class="ui-tabs-panel" id="tabs-2">
                                <div display="block"  id="property" width="100%">
                                    <form action="Uploader" name="upload" id="upload" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="type" value="rtfcontent"/>
                                        <input type="hidden" name="formsesid" value="{formsesid}"/>
                                        <!-- Секция "Вложения" -->
                                        <div display="block" id="att" style="width:100%">
                                            <xsl:call-template name="attach"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
					    </div>
		    			<div style="height:10px"/>
		    		</div>
		    	</div>
			</body>	
		</html>
	</xsl:template>
</xsl:stylesheet>