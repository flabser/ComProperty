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
					<xsl:value-of select="$doctype"/>  - АИС 'Коммунальное Имущество'
				</title>
				<xsl:call-template name="cssandjs"/>
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
								<button title ="{document/captions/saveclose/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnsavedoc" disabled="disabled">
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
									<li class="ui-state-default ui-corner-top">
										<a href="#tabs-2"><xsl:value-of select="document/captions/regdoc/@caption"/></a>
									</li>
								</li> 
							</ul>
							<div class="ui-tabs-panel" id="tabs-1">
								<form action="Provider" name="frm" method="post" id="frm" enctype="application/x-www-form-urlencoded">
									<div display="block" id="property">
										<br/>
										<table width="100%" border="0">

                                            <!--  БИН   -->
                                            <tr>
                                                <td class="fc">
                                                    <xsl:value-of select="document/captions/bin/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/>&#xA0;:
                                                </td>
                                                <td>
                                                    <input type="text" value="{document/fields/bin}" onkeydown="numericfield(this)" onchange="enableCheckByIINButton(this)" maxlength="12" style="width:150px;" class="td_editable" name="bin" id="bin" title="{document/captions/bintitle/@caption}">

                                                        <xsl:if test="$editmode != 'edit'">
                                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                                        </xsl:if>
                                                    </input>
                                                </td>
                                            </tr>

                                            <!-- Номер свидетельства о гос.регистрации -->
                                            <tr>
                                                <td class="fc">
                                                    <xsl:value-of select="document/captions/gos_reg_num/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/>&#xA0;:
                                                </td>
                                                <td>
                                                    <input type="text" value="{document/fields/gos_reg_num}"  onchange="enableCheckByIINButton(this)" maxlength="12" style="width:150px;" class="td_editable" name="bin" id="gos_reg_num" title="{document/captions/gos_reg_num/@caption}">

                                                        <xsl:if test="$editmode != 'edit'">
                                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                                        </xsl:if>
                                                    </input>
                                                    <div class="button_box ui-corner-all ui-button ui-state-default" onclick="javascript:checkByBIN()" style="width:24px; height:22px;">
                                                        <img src="/SharedResources/img/iconset/tick_gray.png" id="checkButtonImg" class="unchecked"/>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="fc">
                                                    <xsl:value-of select="document/captions/reg_date/@caption"/>&#xA0;:
                                                </td>
                                                <td>
                                                    <input type="text" value="{document/fields/gos_reg_date}" style="width:150px;" class="td_editable" name="gos_reg_date">
                                                        <!--<xsl:if test="$editmode != 'edit'">-->
                                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                                        <!--</xsl:if>-->
                                                    </input>
                                                </td>
                                            </tr>
                                            <!--  РНН   -->
                                            <!--<tr>
                                                <td class="fc">
                                                    <xsl:value-of select="document/captions/rnn/@caption"/>&#xA0;:
                                                </td>
                                                <td>
                                                    <input type="text" value="{document/fields/rnn}" style="width:115px;" class="td_editable" name="rnn">
                                                        <xsl:if test="$editmode != 'edit'">
                                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                                        </xsl:if>
                                                    </input>
                                                </td>
                                            </tr>  -->
                                            <!--  ИИК   -->
                                           <!-- <tr>
                                                <td class="fc">
                                                    <xsl:value-of select="document/captions/iik/@caption"/>&#xA0;:
                                                </td>
                                                <td>
                                                    <input type="text" value="{document/fields/iik}" style="width:150px;" class="td_editable" name="iik">
                                                        <xsl:if test="$editmode != 'edit'">
                                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                                        </xsl:if>
                                                    </input>
                                                </td>
                                            </tr>    -->
                                            <!--  БИК   -->
                                           <!-- <tr>
                                                <td class="fc">
                                                    <xsl:value-of select="document/captions/bik/@caption"/>&#xA0;:
                                                </td>
                                                <td>
                                                    <input type="text" value="{document/fields/bik}" style="width:150px;" class="td_editable" name="bik">
                                                        <xsl:if test="$editmode != 'edit'">
                                                            <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                            <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                            <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                                        </xsl:if>
                                                    </input>
                                                </td>
                                            </tr> -->
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
                                                    </input>
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
												<input type="text" value="{document/fields/fulllegaladdress}" style="width:400px;" class="td_editable" name="fulllegaladdress">
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
										<!-- 	Факс-->
										<!--<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/fax/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/fax}" style="width:400px;" class="td_editable" name="fax">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr> -->
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

										<!--  Банковские реквизиты   -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/bankdetails/@caption"/>&#xA0;:
											</td>
											<td>
												<textarea type="text" value="{document/fields/bankdetails}" style="width:500px;" class="td_editable" name="bankdetails" cols="4">
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="readonly">readonly</xsl:attribute>
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
														<xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
													</xsl:if>
												</textarea>
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
										<!--  ФИО ответственного исполнителя-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/executive/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/executive}" style="width:500px;" class="td_editable" name="executive">
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
									<input type="hidden" name="type" value="save"/>
									<input type="hidden" name="id" value="{@id}"/>		
									<input type="hidden" name="key" value="{document/@docid}"/> 
					   			</form>
					    	</div>
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
					    </div>
		    			<div style="height:10px"/>
		    		</div>
		    	</div>
			</body>	
		</html>
	</xsl:template>
</xsl:stylesheet>