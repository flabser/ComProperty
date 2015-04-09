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
									   case 87:
									  		<!-- клавиша w -->
									     	e.preventDefault();
									     	$("#btnremind").click();
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
							var dates = $( "#startcontractdate, #endrentdate" ).datepicker({
							defaultDate: "+1w",
							showOn: "button",
							buttonImage: '/SharedResources/img/iconset/calendar.png',
							buttonImageOnly: true,
							changeMonth: true,
							changeYear: true,
							numberOfMonths: 1,
							onSelect: function( selectedDate ) {
								var option = this.id == "startcontractdate" ? "minDate" : "maxDate",
								instance = $( this ).data( "datepicker" ),
								date = $.datepicker.parseDate(
								instance.settings.dateFormat ||
								$.datepicker._defaults.dateFormat,
								selectedDate, instance.settings );
								dates.not( this ).datepicker( "option", option, date );
									calcrent()
								}
							});
						});
						
						 function calcrent(){
						 	if($("#startcontractdate").val() !='' &amp;&amp; $("#endrentdate").val()){
										var startcontractdate=new Date($.datepicker.parseDate( "dd.mm.yy", $("#startcontractdate").val() ));
	        							var endrentdate=new Date($.datepicker.parseDate( "dd.mm.yy", $("#endrentdate").val() ));
								       	var month =1000*60*60*24*30;
								        var day=86400000;
										var hour=3600000;
								        var minute=60000;
	        							var time=endrentdate.getTime()-startcontractdate.getTime();
	        							//var months = Math.floor(time/month);
	        							var a = moment(startcontractdate);
										var b = moment(endrentdate);
										/*y = b.diff(a,'years')
										if(b.diff(a,'years') > 0){
											b = moment(b).subtract('years', b.diff(a,'years'))
										}*/
										months = b.diff(a,'months')
										if(b.diff(a,'months') > 0){
											b = moment(b).subtract('months', b.diff(a,'months'))
										}
										days = b.diff(a,'days')
										//alert("лет " + y +" месяцев " + m +" дней " + d)
								        //var days=Math.floor((time-(months*month))/day);
								       	if(days == 0 &amp;&amp; months == 0){
								       		$("#satr").css("display","")
								       	}else{
								       		$("#satr").css("display","none");
								       	}
								       	ctrif = $("#currenttarif").val()
								       	sum = 0;
								       	endrentmonth =endrentdate.getMonth()
								       	endrentyear =endrentdate.getYear()
								       	var days_in_month = 32 - new Date(endrentyear, endrentmonth, 32).getDate();
								       	if(months > 0){
								       		sum += $("#"+ctrif).val() * months;
								       	}
								        if(days > 0){
								        	sum += $("#"+ctrif).val()/days_in_month * days;
								        }
								        if(days == 0 &amp;&amp; months == 0){
								        	if($("#rentobjtype").val() == 'n'){
								        		$("input[name=tarif]").val(Math.floor((1731*0.1)*100)/100)	
											}else{
												$("input[name=tarif]").val(Math.floor((($("#"+ctrif).val() / days_in_month ) / 24)*100)/100)
											}
								        }else{
									       $("input[name=tarif]").val(Math.floor(sum*100)/100)
								        }
								        
								        if(months &lt; 1){
								        	$("#monthpayment").removeProp("checked").prop("disabled","true")
								        	$("#oncepayment").prop("checked","checked")
								        }else{
								        	$("#monthpayment").removeProp("disabled")
								        }
								        
								        if(months &lt; 3){
								        	$("#quarterpayment").removeProp("checked").prop("disabled","true")
								        }else{
								        	$("#quarterpayment").removeProp("disabled")
								        }
								        
								        if(months &lt; 12){
								        	$("#yearpayment").removeProp("checked").prop("disabled","true")
								        }else{
								        	$("#yearpayment").removeProp("disabled")
								        }
								        
								        $("#rent_days").val(days)
								        $("#rent_months").val(months)
								       
								     }
						 }
					</script>
				</xsl:if>
				<xsl:if test="/request/@lang = 'KAZ'">
					<script>
						$(function() {
							$('#regdate , #endcontractdate').datepicker({
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
							<xsl:call-template name="add_transfer_protocol"/>
							<xsl:if test="$status != 'new'">
								<!-- <button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
									<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=orderleasing&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Добавить доп. договор аренды</font>
									</span>
								</button> -->
								<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
									<xsl:attribute name="onclick">javascript:sentNotifyRentPayment(<xsl:value-of select="document/@docid"/>)</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/email_error.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Уведомить о необходимости внесения арендной платы</font>
									</span>
								</button>
								<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
									<xsl:attribute name="onclick">javascript:sentNotify(<xsl:value-of select="document/@docid"/>)</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/email_error.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Уведомить об окончании действия договора</font>
									</span>
								</button>
								<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
									<xsl:attribute name="onclick">javascript:endContract(<xsl:value-of select="document/@docid"/>)</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/email_error.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Расторгнуть договор</font>
									</span>
								</button>
							</xsl:if>
							<!--  <xsl:if test="$status != 'new'">
								<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
									<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=contract&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
									<span>
										<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
										<font style="font-size:12px; vertical-align:top">Добавить договор</font>
									</span>
								</button>
							</xsl:if>-->
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
										
										<!-- Регистрационный номер-->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/regnumber/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" value="{document/fields/regnumber}" style="width:90px;" class="td_noteditable" name="regnumber" readonly="readonly">
												</input>
											</td>
										</tr>
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
										<!-- Арендатор -->
										<tr>
											<td class="fc" style="padding-top:5px">
												<font style="vertical-align:top">
													<xsl:value-of select="document/captions/leasingholder/@caption"/> :
												</font>
												<xsl:if test="$editmode = 'edit'">
													<a>
														<xsl:attribute name="href">javascript:dialogBoxStructure('organizations','false','leasingholder','frm', 'leasingholdertbl');</xsl:attribute>
														<img src="/SharedResources/img/iconset/report_magnify.png"/>
													</a>
												</xsl:if>
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
										<!-- стоимость аренды -->
										<tr>
											<td class="fc">
												Стоимость аренды &#xA0;:
											</td>
											<td>
												<input type="text" autocomplete="off" value="{document/fields/tarif}" style="width:90px;" class="td_noteditable" name="tarif" readonly="true"/>
												<input type="hidden" autocomplete="off" value="{document/fields/tarif}" id="starttarif" name="starttarif"/>
												<input type="hidden" autocomplete="off" value="{document/fields/lgottarif}" id="lgottarif" name="lgottarif"/>
												<input type="hidden" autocomplete="off" value="starttarif" id="currenttarif" name="currenttarif"/>
												<input type="hidden" autocomplete="off" value="{document/fields/rentobjtype}" id="rentobjtype" name="rentobjtype"/>
											</td>
										</tr>
										<!-- Период оплаты -->
										<tr>
											<td class="fc">
												Период оплаты &#xA0;:
											</td>
											<td>
												<p style="padding:0px; margin: 0px">
													<input type="radio" name="payperiod" value="once" id="oncepayment" autocomplete="off">
														<xsl:if test="document/fields/payperiod = 'once' or document/fields/payperiod = '' or not(document/fields/payperiod)">
															<xsl:attribute name="checked"/>
														</xsl:if>
													</input>
													Однократно
												</p>
												<p style="padding:0px; margin: 0px">
													<input type="radio" name="payperiod" value="month" id="monthpayment" autocomplete="off">
														<xsl:if test="document/fields/payperiod = 'month'">
															<xsl:attribute name="checked"/>
														</xsl:if>
													</input>
													Месяц
												</p>
												<p style="padding:0px; margin: 0px">
													<input type="radio" name="payperiod" value="quarter" id="quarterpayment" autocomplete="off">
														<xsl:if test="document/fields/payperiod = 'quater'">
															<xsl:attribute name="checked"/>
														</xsl:if>
													</input>
													 Квартал
												</p>
												<p style="padding:0px; margin: 0px">
													<input type="radio" name="payperiod" value="year" id="yearpayment" autocomplete="off">
														<xsl:if test="document/fields/payperiod = 'year'">
															<xsl:attribute name="checked"/>
														</xsl:if>
													</input>
													 Год
												</p>
											</td>
										</tr>
										<!-- 	Дата регистрации -->
										<tr>
											<td class="fc">
												<xsl:value-of select="document/captions/regdate/@caption"/>&#xA0;:
											</td>
											<td>
												<input type="text" name="regdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/regdate,1,10)}" readonly="readonly">
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
												Дата начала действия договора&#xA0;:
											</td>
											<td>
												<input type="text" name="startcontractdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/startcontractdate,1,10)}" readonly="readonly">
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
												Дата окончания аренды&#xA0;:
											</td>
											<td>
												<input type="text" name="endrentdate" maxlength="10" autocomplete="off" class="td_editable" style="width:80px; vertical-align:top" value="{substring(document/fields/endrentdate,1,10)}" readonly="readonly">
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
								<input type="hidden" id="rent_days" name="rent_days" value="{document/fields/rent_days}"/>
								<input type="hidden" id="rent_months" name="rent_months" value="{document/fields/rent_months}"/>
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