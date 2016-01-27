<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:import href="../layout.xsl"/>

	<xsl:variable name="editmode" select="/request/document/@editmode"/>
	<xsl:variable name="doctype" select="request/document/captions/doctypemultilang/@caption"/>
	<xsl:variable name="skin" select="request/@skin"/>
	<xsl:variable name="path" select="/request/@skin"/>

	<xsl:template match="/request">
		<xsl:call-template name="layout"/>
	</xsl:template>

	<xsl:template name="_content">
		<div class="formwrapper">
			<div class="formtitle">
				<div class="title">
					<font>
						<xsl:value-of select="document/captions/employer/@caption"/> -
						<xsl:value-of select="document/fields/fullname"/>
					</font>
				</div>
			</div>
			<div id="tabs">
				<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
					<li class="ui-state-default ui-corner-top">
						<a href="#tabs-1">
							<xsl:value-of select="document/captions/properties/@caption"/>
						</a>
					</li>
					<li class="ui-state-default ui-corner-top">
						<a href="#tabs-2">Вложения</a>
					</li>
					<li class="ui-state-default ui-corner-top">
						<a href="#tabs-3">
							<xsl:value-of select="document/captions/interface/@caption"/>
						</a>
					</li>
				</ul>
				<form action="Provider" name="frm" method="post" id="frm"
					  enctype="application/x-www-form-urlencoded">
					<div class="ui-tabs-panel" id="tabs-1">
						<div display="block" id="property" width="100%">
							<br/>
							<table width="80%" border="0" style="margin-top:8px">
								<xsl:if test="document/fields/form = 'responsibleperson'">
									<!-- ФИО -->
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/fio/@caption"/>&#xA0;:
										</td>
										<td>
											<input type="text" value="{document/fields/fullname}"
												   style="width:500px;" class="td_editable" name="fio">
												<!--<xsl:if test="$editmode != 'edit'">-->
												<xsl:attribute name="readonly">readonly</xsl:attribute>
												<xsl:attribute name="class">td_noteditable</xsl:attribute>
												<xsl:attribute name="onfocus">javascript:$(this).blur()
												</xsl:attribute>
												<!--</xsl:if>-->
											</input>
										</td>
									</tr>
									<!-- Логин -->
									<tr>
										<td class="fc">
											Логин&#xA0;:
										</td>
										<td>
											<input type="text" value="{document/fields/userid}"
												   style="width:300px;" class="td_editable" name="login">
												<!--<xsl:if test="$editmode != 'edit'">-->
												<xsl:attribute name="readonly">readonly</xsl:attribute>
												<xsl:attribute name="class">td_noteditable</xsl:attribute>
												<xsl:attribute name="onfocus">javascript:$(this).blur()
												</xsl:attribute>
												<!--</xsl:if>-->
											</input>
										</td>
									</tr>

									<!-- Пароль -->
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/password/@caption"/>&#xA0;:
										</td>
										<td>
											<input type="password" value="" style="width:300px;"
												   class="td_editable" name="password">
												<xsl:if test="$editmode != 'edit'">
													<xsl:attribute name="readonly">readonly</xsl:attribute>
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
													<xsl:attribute name="onfocus">
														javascript:$(this).blur()
													</xsl:attribute>
												</xsl:if>
											</input>
										</td>
									</tr>
									<!-- Повтор пароля -->
									<tr>
										<td class="fc">
											<xsl:value-of
													select="document/captions/reenterpassword/@caption"/>&#xA0;:
										</td>
										<td>
											<input type="password" value="{document/fields/reenterpassword}"
												   style="width:300px;" class="td_editable"
												   name="reenterpassword">
												<xsl:if test="$editmode != 'edit'">
													<xsl:attribute name="readonly">readonly</xsl:attribute>
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
													<xsl:attribute name="onfocus">
														javascript:$(this).blur()
													</xsl:attribute>
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
											<input type="text" value="{document/fields/phone}"
												   style="width:400px;" class="td_editable" name="phone">
												<xsl:if test="$editmode != 'edit'">
													<xsl:attribute name="readonly">readonly</xsl:attribute>
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
													<xsl:attribute name="onfocus">
														javascript:$(this).blur()
													</xsl:attribute>
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
											<input type="text" value="{document/fields/email}"
												   style="width:300px;" class="td_editable" name="email">
												<xsl:if test="$editmode != 'edit'">
													<xsl:attribute name="readonly">readonly</xsl:attribute>
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
													<xsl:attribute name="onfocus">
														javascript:$(this).blur()
													</xsl:attribute>
												</xsl:if>
											</input>
										</td>
									</tr>
									<tr>
										<td class="fc" style="padding-top:5px">
											<font style="vertical-align:top">
												<xsl:value-of select="document/captions/orgname/@caption"/>
												:
											</font>
										</td>

										<td style="padding-top:5px">
											<table id="institutiontbl"
												   style="border-spacing:0px 3px; margin-top:-3px">
												<tr>
													<td style="width:600px;" class="td_editable">
														<xsl:if test="$editmode != 'edit'">
															<xsl:attribute name="class">td_noteditable
															</xsl:attribute>
														</xsl:if>
														<xsl:value-of
																select="document/fields/institutionname"/>&#xA0;
														<xsl:if test="(document/fields/org_link)">
															<span style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'>
																<a href="/RegistrySubsystem/{document/fields/org_link}&amp;redirect=account"
																   alt="Изменение данных об учреждении">
																	<img src="/SharedResources/img/iconset/pencil_go.png"
																		 style="width:16px;padding-left:4px"/>
																</a>
															</span>
														</xsl:if>
													</td>
												</tr>
											</table>
											<input type="hidden" value="{document/fields/institution}"
												   id="institution" name="institution"/>
											<input type="hidden"
												   value="{document/captions/institution/@caption}"
												   id="institutioncaption"/>
										</td>
									</tr>
								</xsl:if>
								<xsl:if test="document/fields/form != 'responsibleperson'">
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/department/@caption"/> :
										</td>
										<td>
											<table>
												<tr>
													<td class="td_noteditable" style="width:500px">
														<xsl:if test="document/@editmode != 'edit'">
															<xsl:attribute name="class">td_noteditable
															</xsl:attribute>
														</xsl:if>
														<xsl:if test="document/fields/department != '0'">
															<xsl:value-of
																	select="document/fields/department"/>
														</xsl:if>
														&#xA0;
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/postid/@caption"/> :
										</td>
										<td>
											<table>
												<tr>
													<td class="td_noteditable" style="width:500px">
														<xsl:if test="document/@editmode != 'edit'">
															<xsl:attribute name="class">td_noteditable
															</xsl:attribute>
														</xsl:if>
														<xsl:value-of select="document/fields/post"/>&#xA0;
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/shortname/@caption"/> :
										</td>
										<td>
											<table>
												<tr>
													<td class="td_noteditable" style="width:500px">
														<xsl:if test="document/@editmode != 'edit'">
															<xsl:attribute name="class">td_noteditable
															</xsl:attribute>
														</xsl:if>
														<xsl:value-of select="document/fields/shortname"/>&#xA0;
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/fullname/@caption"/> :
										</td>
										<td>
											<table>
												<tr>
													<td class="td_noteditable" style="width:500px">
														<xsl:if test="document/@editmode != 'edit'">
															<xsl:attribute name="class">td_noteditable
															</xsl:attribute>
														</xsl:if>
														<xsl:value-of select="document/fields/fullname"/>&#xA0;
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="fc">ID :</td>
										<td>
											<input style="width:500px;" class="td_editable" name="userid"
												   value="{document/fields/userid}">
												<xsl:if test="document/@editmode != 'edit'">
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
												</xsl:if>
											</input>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/oldpassword/@caption"/>
											:
										</td>
										<td>
											<input style="width:300px;" class="td_editable" id="oldpwd"
												   name="oldpwd" type="password" autocomplete="off">
												<xsl:if test="document/@editmode != 'edit'">
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
												</xsl:if>
											</input>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/newpassword/@caption"/>
											:
										</td>
										<td>
											<input style="width:300px;" id="newpwd" name="pwd"
												   type="password" class="td_editable" autocomplete="off">
												<xsl:if test="document/@editmode != 'edit'">
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
												</xsl:if>
											</input>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of
													select="document/captions/repeatnewpassword/@caption"/>
											:
										</td>
										<td>
											<input style="width:300px;" id="newpwd2" name="pwd2"
												   type="password" class="td_editable" autocomplete="off">
												<xsl:if test="document/@editmode != 'edit'">
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
												</xsl:if>
											</input>
										</td>
									</tr>
									<tr>
										<td class="fc">Instant Messenger address :</td>
										<td>
											<input style="width:300px" name="instmsgaddress" type="text"
												   class="td_editable"
												   value="{document/fields/instmsgaddress}">
												<xsl:if test="document/@editmode != 'edit'">
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
												</xsl:if>
											</input>
											<span style="vertical-align:middle;">
												<xsl:choose>
													<xsl:when
															test="document/fields/instmsgstatus = 'false'">
														<img src="/SharedResources/img/iconset/bullet_red.png"
															 title="Instant Messenger off"/>
													</xsl:when>
													<xsl:when test="document/fields/instmsgstatus = 'true'">
														<img src="/SharedResources/img/iconset/bullet_gren.png"
															 title="Instant Messenger on"/>
													</xsl:when>
													<xsl:otherwise>
														<img src="/SharedResources/img/iconset/bullet_red.png"
															 title="Instant Messenger off"/>
													</xsl:otherwise>
												</xsl:choose>
											</span>
										</td>
									</tr>
									<tr>
										<td class="fc">E-mail :</td>
										<td width="69%">
											<input style="width:300px" id="email" name="email" type="text"
												   class="td_editable" value="{document/fields/email}">
												<xsl:if test="document/@editmode != 'edit'">
													<xsl:attribute name="class">td_noteditable
													</xsl:attribute>
												</xsl:if>
											</input>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/role/@caption"/> :
										</td>
										<td>
											<table>
												<xsl:for-each select="document/fields/role[not(entry)]">
													<xsl:variable name="role" select="."/>
													<xsl:if test="/request/document/glossaries/roles/entry[ison='ON'][name = $role]">
														<tr>
															<td style="width:500px;" class="td_noteditable">
																<xsl:if test="../../../../document/@editmode != 'edit'">
																	<xsl:attribute name="class">
																		td_noteditable
																	</xsl:attribute>
																</xsl:if>
																<xsl:value-of select="."/>

																<xsl:if test="/request/document/glossaries/roles/entry[ison='ON'][name = $role]">
																	<input type="hidden" name="role"
																		   value="{.}"/>
																</xsl:if>
															</td>
														</tr>
													</xsl:if>
												</xsl:for-each>
												<xsl:for-each select="document/fields/role/entry">
													<xsl:variable name="role" select="."/>
													<xsl:if test="/request/document/glossaries/roles/entry[ison='ON'][name = $role]">
														<tr>
															<td style="width:500px;" class="td_noteditable">
																<xsl:if test="../../../../document/@editmode != 'edit'">
																	<xsl:attribute name="class">
																		td_noteditable
																	</xsl:attribute>
																</xsl:if>
																<xsl:value-of select="."/>

																<xsl:if test="/request/document/glossaries/roles/entry[ison='ON'][name = $role]">
																	<input type="hidden" name="role"
																		   value="{.}"/>
																</xsl:if>
															</td>
														</tr>
													</xsl:if>
												</xsl:for-each>
											</table>
										</td>
									</tr>
									<tr>
										<td class="fc">
											<xsl:value-of select="document/captions/group/@caption"/> :
										</td>
										<td width="69%">
											<table>
												<xsl:for-each select="document/fields/group/entry">
													<tr>
														<td style="width:500px" class="td_noteditable">
															<xsl:if test="../../../../document/@editmode != 'edit'">
																<xsl:attribute name="class">td_noteditable
																</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="."/>
														</td>
													</tr>
												</xsl:for-each>
											</table>
										</td>
									</tr>
								</xsl:if>
							</table>
							<br/>
						</div>
					</div>
					<div id="tabs-3">
						<div display="block" id="interface" style="width:100%">
							<br/>
							<table width="80%" border="0" style="margin-top:8px">
								<tr>
									<td class="fc">
										<xsl:value-of select="document/captions/countdocinview/@caption"/>&#xA0;:
									</td>
									<td width="69%">
										<select name="pagesize" id="countdocinview" class="select_editable"
												style="width:85px">
											<xsl:if test="document/@editmode != 'edit'">
												<xsl:attribute name="class">select_noteditable
												</xsl:attribute>
											</xsl:if>
											<option id="countdocinview10">
												<xsl:if test="document/fields/countdocinview = '10'">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												10
											</option>
											<option id="countdocinview20">
												<xsl:if test="document/fields/countdocinview = '20'">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												20
											</option>
											<option id="countdocinview30">
												<xsl:if test="document/fields/countdocinview = '30'">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												30
											</option>
											<option id="countdocinview50">
												<xsl:if test="document/fields/countdocinview = '50'">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												50
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="fc">
										<xsl:value-of select="document/captions/refreshperiod/@caption"/> :
									</td>
									<td width="69%">
										<select name="refresh" id="refresh" class="select_editable"
												style="width:85px">
											<xsl:if test="document/@editmode != 'edit'">
												<xsl:attribute name="class">select_noteditable
												</xsl:attribute>
											</xsl:if>
											<option id="3" value="3">
												<xsl:if test="document/fields/refresh = '3'">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												3 <xsl:value-of select="document/captions/min/@caption"/>.
											</option>
											<option id="5" value="5">
												<xsl:if test="document/fields/refresh = '5' ">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												5 <xsl:value-of select="document/captions/min/@caption"/>.
											</option>
											<option id="10" value="10">
												<xsl:if test="document/fields/refresh = '10' ">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												10 <xsl:value-of select="document/captions/min/@caption"/>.
											</option>
											<option id="15" value="15">
												<xsl:if test="document/fields/refresh = '15' ">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												15 <xsl:value-of select="document/captions/min/@caption"/>.
											</option>
											<option id="20" value="20">
												<xsl:if test="document/fields/refresh = '20' ">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												20 <xsl:value-of select="document/captions/min/@caption"/>.
											</option>
											<option id="30" value="30">
												<xsl:if test="document/fields/refresh = '30' ">
													<xsl:attribute name="selected">selected</xsl:attribute>
												</xsl:if>
												30 <xsl:value-of select="document/captions/min/@caption"/>.
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="fc">
										<xsl:value-of select="document/captions/lang/@caption"/> :
									</td>
									<td width="69%">
										<select name="lang" id="lang" class="select_editable"
												style="width:120px">
											<xsl:variable name='chinese'
														  select="document/captions/chinese/@caption"/>
											<xsl:variable name='currentlang' select="../@lang"/>
											<!-- 												<xsl:attribute name="onchange">javascript:changeSystemSettings(this)</xsl:attribute> -->
											<xsl:for-each select="document/glossaries/langs/entry">
												<option id="{id}" value="{id}">
													<xsl:if test="$currentlang = id">
														<xsl:attribute name="selected">selected
														</xsl:attribute>
													</xsl:if>
													<xsl:if test="id = 'CHN'">
														<xsl:value-of select="$chinese"/>
													</xsl:if>
													<xsl:if test="id = 'KAZ'">
														Қазақша
													</xsl:if>
													<xsl:if test="id != 'CHN' and id != 'KAZ'">
														<xsl:value-of select="name"/>
													</xsl:if>
												</option>
											</xsl:for-each>
										</select>
									</td>
								</tr>
								<!-- <tr>
                                   <td class="fc">Отображение истории посещений :</td>
                                      <td  width="69%">
                                       <input type="checkbox" name="historyvis"  id="history_vis" value="history_on">
                                           <xsl:if test="document/fields/historyvis = 'history_on' ">
                                               <xsl:attribute name="checked">checked</xsl:attribute>
                                           </xsl:if>
                                       </input>
                                   </td>
                               </tr> -->
							</table>
							<br/>
						</div>
					</div>
					<input type="hidden" name="type" value="save_userprofile"/>
					<input type="hidden" name="id" value="userprofile"/>
				</form>
				<div class="ui-tabs-panel" id="tabs-2">
					<div display="block" id="property" width="100%">
						<form action="Uploader" name="upload" id="upload" method="post"
							  enctype="multipart/form-data">
							<input type="hidden" name="type" value="employer_uploadfield"/>
							<input type="hidden" name="formsesid" value="{formsesid}"/>
							<!-- Секция "Вложения" -->
							<div display="block" id="att" style="width:100%">
								<!--<xsl:call-template name="attach"/>-->
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</xsl:template>

</xsl:stylesheet>