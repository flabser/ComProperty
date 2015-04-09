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
				<xsl:if test="document/@editmode = 'edit'">
					<xsl:if test="/request/@lang = 'RUS'">
						<script>
							$(function() {
								$('#regdate').datepicker({
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
								$('#regdate').datepicker({
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
								$('#regdate').datepicker({
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
                                        <!-- 	ИИН -->
                                        <tr>
                                            <td class="fc">
                                                <xsl:value-of select="document/captions/iin/@caption"/><img style="height:11px; vertical-align:top" src="/SharedResources/img/iconset/bullet_red.png" title="обязательное поле"/>&#xA0;:
                                            </td>
                                            <td>
                                                <input type="text" value="{document/fields/iin}" onkeydown="numericfield(this)" onchange="enableCheckByIINButton(this)" maxlength="12" style="width:150px;" class="td_editable" name="iin" id="iin" title="{document/captions/iintitle/@caption}">

                                                    <xsl:if test="$editmode != 'edit'">
                                                        <xsl:attribute name="readonly">readonly</xsl:attribute>
                                                        <xsl:attribute name="class">td_noteditable</xsl:attribute>
                                                        <xsl:attribute name="onfocus">javascript:$(this).blur()</xsl:attribute>
                                                    </xsl:if>
                                                </input>
                                                <div class="button_box ui-corner-all ui-button ui-state-default" onclick="checkByIIN()" style="width:24px; height:22px;">
                                                    <img src="/SharedResources/img/iconset/tick_gray.png" id="checkButtonImg" class="unchecked"/>
                                                </div>
                                                <!--<img src="/SharedResources/img/iconset/ok.png"  />-->
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

										<!-- ФИО  -->
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
										<!-- Полное наименование учреждения -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/orgfullname/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/orgfullnamekaz}" style="width:500px;" class="td_editable" name="orgfullname">
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
												<input type="text" value="{document/fields/phone}" style="width:300px;" class="td_editable" name="phone">
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
										</tr>  -->
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
										<!--  Вид деятельности по ОКЭД -->
										<tr>
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
													<option value="48">СЕЛЬСКОЕ, ЛЕСНОЕ И РЫБНОЕ ХОЗЯЙСТВО</option>
													<option value="48">ТОРГОВЛЯ</option>
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
										</tr>
										<!-- 	Номер Гос. регистрации-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/regnumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/regnumber}" style="width:150px;" class="td_editable" name="regnumber">
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
									</table>		
									</div>   
									<input type="hidden" name="type" value="save"/>
									<input type="hidden" name="parentdocid" value="1"/>
                                    <input type="hidden" name="parentdoctype" value="888"/>
                                    <input type="hidden" name="id" value="{@id}"/>
                                    <input type="hidden" name="key" value="{document/@docid}"/>
                                    <input type="hidden" name="doctype" value="{document/@doctype}"/>
                                    <input type="hidden" name="tender_group_id" value="{document/glossaries/group/query/entry[viewcontent/viewtext1='[rent_viewer]']/@docid}"/>
                                    <input type="hidden" name="formsesid" value="{formsesid}"/>
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