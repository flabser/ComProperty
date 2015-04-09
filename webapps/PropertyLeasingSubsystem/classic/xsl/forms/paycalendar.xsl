<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:import href="../templates/form.xsl"/>
	<xsl:import href="../templates/sharedactions.xsl"/>
	<xsl:decimal-format name = "df" grouping-separator = " "/>
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
								<a href="#tabs-2"><xsl:value-of select="document/captions/additional/@caption"/></a>
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
												</input>
											</td>
										</tr>
										<!-- Объект -->
										<tr>
											<td class="fc">
												Объект :
											</td>
											<td>
												<a class="doclink" href="{document/fields/objecturl}">
													<xsl:value-of select="document/fields/objectname"/>
												</a>
											</td>
										</tr>
										<!-- Арендатор -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<font style="vertical-align:top">
													<xsl:value-of select="document/captions/leasingholder/@caption"/> :
												</font>
											</td>
											<td style="padding-top:5px">
												<table id="leasingholdertbl" style="border-spacing:0px 3px; margin-top:-3px">
													<tr>
														<td style="width:600px;" class="td_editable">
															<xsl:if test="$editmode != 'edit'">
																<xsl:attribute name="class">td_noteditable</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="document/fields/leasingholdername"/>&#xA0;
															<span style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'><font><xsl:value-of select="document/fields/corr/@attrval"/></font></span>
														</td>
													</tr>
												</table>
												<input type="hidden" value="{document/fields/leasingholder}" id="balanceholder" name="leasingholder"/>
												<input type="hidden" value="{document/captions/leasingholder/@caption}" id="leasingholdercaption"/>
											</td>
										</tr>
										<tr>
											<td class="fc">
												Стоимость аренды :
											</td>
											<td>
												<input type="text" autocomplete="off" value="{document/fields/tarif}" style="width:100px;" class="td_noteditable" name="tarif" readonly="true">
												</input>
												<input type="hidden" autocomplete="off" value="{document/fields/tarif}" id="starttarif"/>
												<input type="hidden" autocomplete="off" value="{document/fields/lgottarif}" id="lgottarif"/>
												<input type="hidden" autocomplete="off" value="starttarif" id="currenttarif"/>
												<input type="hidden" autocomplete="off" value="{document/fields/rentobjtype}" id="rentobjtype"/>
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
										<!-- 	Дата начала действия -->
										<tr>
											<td class="fc">
												Дата начала действия договора :
											</td>
											<td>
												<input type="text" name="startcontractdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/startcontractdate,1,10)}">
													<xsl:if test="$editmode = 'edit'">
														<xsl:attribute name="id">startcontractdate</xsl:attribute>
													</xsl:if>
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Дата окончания аренды -->
										<tr>
											<td class="fc">
												Дата окончания аренды :
											</td>
											<td>
												<input type="text" name="endrentdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/endrentdate,1,10)}">
													<xsl:if test="$editmode = 'edit'">
														<xsl:attribute name="id">endrentdate</xsl:attribute>
													</xsl:if>
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">td_noteditable</xsl:attribute>
													</xsl:if>
												</input>
											</td>
										</tr>
										<!-- 	Срок аренды -->
										<tr id="satr" style="display:none">
											<td class="fc" style="padding-top:5px">
												Срок аренды в часах:
											</td>
											<td style="padding-top:5px">
												<select size="1" name="sa" id="sa" autocomplete="off" style="width:100px;" class="select_editable" onchange="javascript:changeRentHours()">
													<xsl:if test="document/fields/rentobjtype ='n'">
														<xsl:attribute name="onchange">javascript:changeRentHoursN()</xsl:attribute>
													</xsl:if>
													<xsl:if test="$editmode != 'edit'">
														<xsl:attribute name="class">select_noteditable</xsl:attribute>
														<xsl:attribute name="disabled">disabled</xsl:attribute>
													</xsl:if>
													<xsl:variable name="sa" select="/request/document/fields/sa"/>
													<xsl:for-each select="1 to 23">
														<option value="{.}">
															<xsl:if test="$sa = .">
																<xsl:attribute name="selected">selected</xsl:attribute>
															</xsl:if>
															<xsl:value-of select="."/>
														</option>
													</xsl:for-each>
												</select>
												  Часов
												<xsl:if test="$editmode !='edit'">
													<input type="hidden" name="sa" value="{document/fields/sa}"/>
												</xsl:if>
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
										<!-- 	Таблица с платежами -->
										<tr>
											<td class="fc" style="padding-top:5px">
												Платежи :
											</td>
											<td style="padding-top:5px">
												<xsl:variable name="year" select="substring(document/fields/startcontractdate,7,4)"/>
												<xsl:variable name="month"  select="substring(document/fields/startcontractdate,4,2)"/>
												<xsl:variable name="day"  select="substring(document/fields/startcontractdate,1,2)"/>
												<xsl:variable name="startdate" as="xs:date" select="xs:date(concat($year,'-',$month,'-',$day))"></xsl:variable>
												
												<xsl:variable name="countitems" select="position()"/>
												<xsl:variable name="currenttarif" select="document/fields/currenttarif"/>
												<xsl:variable name="tarif">
													<xsl:if test="$currenttarif ='starttarif'">
														<xsl:value-of select="document/fields/starttarif"/> 
													</xsl:if>
													<xsl:if test="$currenttarif ='lgottarif'">
														<xsl:value-of select="document/fields/lgottarif"/> 
													</xsl:if>
												</xsl:variable>
												<xsl:variable name="months" as="xs:integer" select="document/fields/rent_months"/>
												<xsl:variable name="quarters" select="number(document/fields/rent_months) idiv number('3')"/>
												<xsl:variable name="years" select="number(document/fields/rent_months) idiv number('12')"/>
												<xsl:variable name="days_" as="xs:integer" select="document/fields/rent_days"/>
												<xsl:variable name="endrentdate"  select="document/fields/endrentdate"/>
													<xsl:variable name="tarif_"  select="document/fields/tarif"/>
												<input type="radio" name="payperiod" onclick="javascript:$('#quarters_table').css('display','none'); $('#years_table').css('display','none'); $('#months_table').css('display','block')" autocomplete="off" checked="cheked">Месяц</input>
												<xsl:if test="$months > 4">
													<input type="radio" name="payperiod" onclick="javascript:$('#quarters_table').css('display','block'); $('#years_table').css('display','none'); $('#months_table').css('display','none')" autocomplete="off">Квартал</input>
												</xsl:if>
												<xsl:if test="$months > 12">
													<input type="radio" name="payperiod" onclick="javascript:$('#quarters_table').css('display','none'); $('#years_table').css('display','block'); $('#months_table').css('display','none')" autocomplete="off">Год</input>
												</xsl:if>
												<br/>
												<br/>
												<table style="width:900px; border-collapse:collapse; display:none" id="quarters_table">
													<tr>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:200px">
															Дата
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:300px">
															Планируемая сумма
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:300px">
															Оплаченная сумма
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:220px">
															Отклонение
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:200px">
															Пеня
														</td>
													</tr>
													<xsl:for-each select="1 to $quarters">
														<xsl:variable name="counter" as="xs:integer" select=". * 3"/>
														<xsl:variable name="months_"  select="concat('P0Y',$counter - 3,'M')"/>
														<xsl:variable name="duration-q" as="xs:yearMonthDuration" select="xs:yearMonthDuration($months_)"/>
														<tr>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/> 
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:value-of select="format-number($tarif * 3, '### ###.##', 'df')"/>
																 (<xsl:value-of select="format-number($tarif * $counter, '### ###.##', 'df')" />)
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
															</td>
														</tr>
													</xsl:for-each>
													<xsl:if test="number(document/fields/rent_months) mod number('3') != 0">
														<tr>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:variable name="months_"  select="concat('P0Y',$quarters * 3,'M')"/>
																<xsl:variable name="months"  select="concat('P0Y',$quarters,'M')"/>
																<xsl:variable name="duration-q" as="xs:yearMonthDuration" select="xs:yearMonthDuration($months_)"/>
																<xsl:variable name="duration-quarter" as="xs:yearMonthDuration" select="xs:yearMonthDuration($months_)"/>
																<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/>
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:variable name="fullsumm" as="xs:double" select="document/fields/tarif"/>
																<xsl:variable name="ost"  select="$fullsumm - ($quarters * 3 * $tarif)"/>
																<xsl:value-of select="format-number($ost, '### ###.##', 'df')"/>
																 (<xsl:value-of select="format-number($fullsumm, '### ###.##', 'df')"/>)
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
															</td>
														</tr>
													</xsl:if>
													<tr>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:right; padding-right:5px" colspan="5">
																<xsl:value-of select="concat('итог : ', format-number(document/fields/tarif ,'### ###.##', 'df'))"/>
															</td>
															
														</tr>
												</table>
												
												<table style="width:1000px; border-collapse:collapse; display:block" id="months_table">
													<tr>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:200px">
															Дата
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:300px">
															Планируемая сумма
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:300px">
															Оплаченная сумма
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:220px">
															Отклонение
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:200px">
															Пеня
														</td>
													</tr>
													<xsl:for-each select="1 to $months">
														<xsl:variable name="counter" as="xs:integer" select="."/>
														<xsl:variable name="months_"  select="concat('P0Y',$counter - 1,'M')"/>
														<xsl:variable name="duration-q" as="xs:yearMonthDuration" select="xs:yearMonthDuration($months_)"/>
															<tr>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																	<xsl:if test="position() != $months">
																		<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/> 
																	</xsl:if>
																	<xsl:if test="position() = $months and $days_ &lt; 14">
																		<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/> 
																	</xsl:if>
																	<xsl:if test="position() = $months and $days_ &gt; 13">
																		<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/> 
																	</xsl:if>
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																	<xsl:if test=". != $months">
																		<xsl:value-of select="format-number($tarif, '### ###.##', 'df')"/> (<xsl:value-of select="format-number($tarif * $counter, '### ###.##', 'df')"/>)
																	</xsl:if>
																	<!-- <xsl:if test="position() = $months and $days_ &lt; 14">
																		<xsl:value-of select="format-number($tarif, '### ###.##', 'df')"/> (<xsl:value-of select="format-number($tarif * $counter, '### ###.##', 'df')"/>)
																	</xsl:if>
																	<xsl:if test="position() = $months and $days_ &gt;= 13">
																		<xsl:variable name="fullsumm" as="xs:double" select="$tarif_"/>
																		<xsl:variable name="ost"  select="$fullsumm - (($months - 1)  * $tarif)"/>
																		<xsl:value-of select="format-number($ost, '### ###.##', 'df')"/> (<xsl:value-of select="format-number($tarif_, '### ###.##', 'df')"/>)
																	</xsl:if> -->
																	<xsl:if test="position() = $months and $days_ &gt;= 14">
																		<xsl:value-of select="format-number($tarif, '### ###.##', 'df')"/> (<xsl:value-of select="format-number($tarif * $counter, '### ###.##', 'df')"/>)
																	</xsl:if>
																	<xsl:if test="position() = $months and $days_ &lt;= 14">
																		<xsl:variable name="fullsumm" as="xs:double" select="$tarif_"/>
																		<xsl:variable name="ost"  select="$fullsumm - (($months - 1)  * $tarif)"/>
																		<xsl:value-of select="format-number($ost, '### ###.##', 'df')"/> (<xsl:value-of select="format-number($tarif_, '### ###.##', 'df')"/>)
																	</xsl:if>
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
															</tr>
														
													</xsl:for-each>
													 <xsl:if test="$days_  >= 14">
													 	 <xsl:variable name="months_"  select="concat('P0Y',$months,'M')"/>
														<xsl:variable name="duration-q" as="xs:yearMonthDuration" select="xs:yearMonthDuration($months_)"/>
														<tr>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/>  
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:variable name="fullsumm" as="xs:double" select="document/fields/tarif"/>
																<xsl:variable name="ost"  select="$fullsumm - ($months * $tarif)"/>
																<xsl:value-of select="format-number($ost, '### ###.##', 'df')"/> (<xsl:value-of select="format-number(document/fields/tarif, '### ###.##', 'df')"/>)
															</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
															</tr>
														</xsl:if> 
													<tr>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:right; padding-right:5px" colspan="5">
																<xsl:value-of select="concat('итог : ', format-number(document/fields/tarif, '### ###.##', 'df'))"/>
															</td>
															
														</tr>
												</table>	
																							
												<table style="width:900px; border-collapse:collapse; display:none" id="years_table">
													<tr>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:200px">
															Дата
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:300px">
															Планируемая сумма
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:300px">
															Оплаченная сумма
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:220px">
															Отклонение
														</td>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center; width:200px">
															Пеня
														</td>
													</tr>
													<xsl:for-each select="1 to $years">
														<xsl:variable name="counter" as="xs:integer" select=". * 12"/>
														<xsl:variable name="months_"  select="concat('P0Y',$counter - 12 ,'M')"/>
														<xsl:variable name="duration-q" as="xs:yearMonthDuration" select="xs:yearMonthDuration($months_)"/>
															<tr>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																		<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/> 
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																	<xsl:value-of select="format-number($tarif * 12, '### ###.##', 'df')"/> (<xsl:value-of select="format-number($tarif * $counter, '### ###.##', 'df')"/>)
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
															</tr>
														
													</xsl:for-each>
													<xsl:if test="number(document/fields/rent_months) mod number('12') != 0">
														<xsl:variable name="months_"  select="concat('P0Y',$years * 12 ,'M')"/>
														<xsl:variable name="duration-q" as="xs:yearMonthDuration" select="xs:yearMonthDuration($months_)"/>
														<tr>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:value-of select="format-date($startdate + $duration-q, '[D11].[M11].[Y1111]')"/> 
															</td>
															<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																<xsl:variable name="fullsumm" as="xs:double" select="document/fields/tarif"/>
																<xsl:variable name="ost"  select="$fullsumm - ($years * $tarif * 12)"/>
																<xsl:value-of select="format-number($ost, '### ###.##', 'df')"/>(<xsl:value-of select="format-number(document/fields/tarif, '### ###.##', 'df')"/>)
															</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
																<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:center">
																</td>
															</tr>
														</xsl:if>
													<tr>
														<td style="border:1px solid #ccc; height:30px; background:#fcfcfc; text-align:right; padding-right:5px" colspan="5">
															<xsl:value-of select="concat('итог : ', format-number(document/fields/tarif, '### ###.##', 'df'))"/>
														</td>
													</tr>
												</table>												
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
							<div id="tabs-2">
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