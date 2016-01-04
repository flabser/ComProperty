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
					Уведомление  - АИС 'Коммунальное Имущество'
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
						<xsl:if test="document/fields/notificationtype ='regobj'">
							<div style="margin:20px auto; width:900px; border:1px solid #999; min-height:300px; padding:5px; background:#FFFFFE">
								<img style="width:75px; height:75px; float:right; padding:0px 0px" src="classic/img/almaty.png" />
								<table style="width:80%; border-collapse:collapse">
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Дата отправки: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/sentdate"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Отправитель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Сервер отправки уведомлений</td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Содержание: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Регистрация объекта <xsl:value-of select="document/fields/object"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Получатель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/balanceholdername"/> &lt;<xsl:value-of select="document/fields/balanceholderemail"/>&gt;</td>
									</tr>
								</table>
								
								<div style="background:#DEDEDE; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; height:25px; text-align:center; font-weight:bold; padding-top:10px; font-size:17px">
									Уведомление о регистрации объекта коммунальной собственности
								</div>
								<div style="background:#9A9A9A; width:100%; height:2px; margin-top:10px"/>
								<table style="width:95%; margin:10px auto">
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Балансодержатель:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><xsl:value-of select="document/fields/balanceholdername"/></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">БИН Балансодержателя:</td>
										<td style="background:#F2F2F2; text-align:left; padding:10px"><xsl:value-of select="document/fields/balanceholderbin"/></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Инвентарный номер:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><a class="doclink" href="{document/fields/objectlink}"><xsl:value-of select="document/fields/invnumber"/></a></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Описание объекта:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><xsl:value-of select="document/fields/description"/></td>
									</tr>
									<tr>
										<td style="width:200px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Тип права на имущество:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><xsl:value-of select="document/fields/propertycode"/></td>
									</tr>
									<tr>
										<td style="width:200px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Дата принятия на баланс:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><xsl:value-of select="document/fields/acceptancedate"/></td>
									</tr>
								</table>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; padding:10px 0 10px 0; text-align:center; font-size:10px">
									Данное уведомление было сформировано и отправлено сервером уведомлений автоматически
								</div>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-bottom:10px"/>
							</div>
						</xsl:if>
							
						<xsl:if test="document/fields/notificationtype ='regcontract'">	
							<div style="margin:20px auto; width:900px; border:1px solid #999; min-height:300px; padding:5px; background:#FFFFFE">
								<img style="width:75px; height:75px; float:right; padding:0px 0px" src="classic/img/almaty.png" />
								<table style="width:80%; border-collapse:collapse">
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Дата отправки: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/sentdate"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Отправитель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Сервер отправки уведомлений</td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Содержание: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Регистрация договора № <xsl:value-of select="document/fields/object"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Получатель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/balanceholdername"/> &lt;<xsl:value-of select="document/fields/balanceholderemail"/>&gt;</td>
									</tr>
								</table>
								
								<div style="background:#DEDEDE; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; height:25px; text-align:center; font-weight:bold; padding-top:10px; font-size:17px">
									Уведомление о регистрации нового договора
								</div>
								<div style="background:#9A9A9A; width:100%; height:2px; margin-top:10px"/>
								<table style="width:95%; margin:10px auto">
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Постановление:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><a class="doclink" href="{document/fields/orderurl}"><xsl:value-of select="document/fields/ordername"/></a></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Номер договора:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><a class="doclink" href="{document/fields/objecturl}"><xsl:value-of select="document/fields/regnumber"/></a></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Дата регистрации:</td>
										<td style="background:#F2F2F2; text-align:left; padding:10px"><xsl:value-of select="document/fields/regdate"/></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Объект договора:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><a class="doclink" href="{document/fields/comobjecturl}"><xsl:value-of select="document/fields/comobjectname"/></a></td>
									</tr>
									<tr>
										<td style="width:200px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Дата окончания договора:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><xsl:value-of select="document/fields/endcontractdate"/></td>
									</tr>
								</table>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; padding:10px 0 10px 0; text-align:center; font-size:10px">
									Данное уведомление было сформировано и отправлено сервером уведомлений автоматически
								</div>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-bottom:10px"/>
							</div>
						</xsl:if>
						<xsl:if test="document/fields/notificationtype ='regorder'">	
							<div style="margin:20px auto; width:900px; border:1px solid #999; min-height:300px; padding:5px; background:#FFFFFE">
								<img style="width:75px; height:75px; float:right; padding:0px 0px" src="classic/img/almaty.png" />
								<table style="width:80%; border-collapse:collapse">
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Дата отправки: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/sentdate"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Отправитель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Сервер отправки уведомлений</td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Содержание: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Регистрация постановления № <xsl:value-of select="document/fields/object"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Получатель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/balanceholdername"/> &lt;<xsl:value-of select="document/fields/balanceholderemail"/>&gt;</td>
									</tr>
								</table>
								
								<div style="background:#DEDEDE; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; height:25px; text-align:center; font-weight:bold; padding-top:10px; font-size:17px">
									Уведомление о регистрации нового постановления
								</div>
								<div style="background:#9A9A9A; width:100%; height:2px; margin-top:10px"/>
								<table style="width:95%; margin:10px auto">
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Регистрационный номер:</td>
										<td style="background:#F2F2F2; text-align:left; padding:10px"><a class="doclink" href="{document/fields/objectlink}"><xsl:value-of select="document/fields/regnumber"/></a></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Дата регистрации:</td>
										<td style="background:#F2F2F2; text-align:left; padding:10px"><xsl:value-of select="document/fields/regdate"/></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Объект постановления:</td>
										<td style="background:#F2F2F2; text-align:left; padding:10px"><a class="doclink" href="{document/fields/comobjecturl}"><xsl:value-of select="document/fields/comobjectname"/></a></td>
									</tr>
									<tr>
										<td style="width:230px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Тип права на имущество:</td>
										<td style="background:#F2F2F2; text-align:left; padding:10px"><xsl:value-of select="document/fields/propertycode"/></td>
									</tr>
									<tr>
										<td style="width:200px; background:#D9D9D9; text-align:right; font-weight:bold; font-size:12px">Дата начала действия:</td>
										<td style="background:#F2F2F2; text-align:left;  padding:10px"><xsl:value-of select="document/fields/regdate"/></td>
									</tr>
								</table>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; padding:10px 0 10px 0; text-align:center; font-size:10px">
									Данное уведомление было сформировано и отправлено сервером уведомлений автоматически
								</div>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-bottom:10px"/>
							</div>
						</xsl:if>
						<xsl:if test="document/fields/notificationtype ='notif'">	
							<div style="margin:20px auto; width:900px; border:1px solid #999; min-height:300px; padding:5px; background:#FFFFFE">
								<img style="width:75px; height:75px; float:right; padding:0px 0px" src="classic/img/almaty.png" />
								<table style="width:80%; border-collapse:collapse">
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Дата отправки: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/sentdate"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Отправитель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Сервер отправки уведомлений</td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Содержание: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Уведомление о истечении срока действия договора</td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Получатель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/balanceholdername"/> &lt;<xsl:value-of select="document/fields/balanceholderemail"/>&gt;</td>
									</tr>
								</table>
								
								<div style="background:#DEDEDE; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; height:25px; text-align:center; font-weight:bold; padding-top:10px; font-size:17px">
									Уведомление
								</div>
								<div style="background:#9A9A9A; width:100%; height:2px; margin-top:10px"/>
								<div style="width:95%; min-height:250px; margin:50px auto">
									<div style="width:20px; display:inline-block"/>Предупреждаем Вас о том, что <xsl:value-of select="document/fields/endcontractdate"/> истекает срок действия договора:  <a class="doclink" href="{document/fields/objectlink}">№ <xsl:value-of select="document/fields/regnumber"/> от <xsl:value-of select="document/fields/regdate"/></a> на объект <a class="doclink" href="{document/fields/comobjecturl}"><xsl:value-of select="document/fields/comobjectname"/></a>
								</div>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; padding:10px 0 10px 0; text-align:center; font-size:10px">
									Данное уведомление было сформировано и отправлено сервером уведомлений автоматически
								</div>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-bottom:10px"/>
							</div>
						</xsl:if>
						<xsl:if test="document/fields/notificationtype ='uploadobj'">
							<div style="margin:20px auto; width:900px; border:1px solid #999; min-height:300px; padding:5px; background:#FFFFFE">
								<img style="width:75px; height:75px; float:right; padding:0px 0px" src="classic/img/almaty.png" />
								<table style="width:80%; border-collapse:collapse">
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Дата отправки: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/sentdate"/></td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Отправитель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Сервер отправки уведомлений</td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Содержание: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;">Уведомление о загрузке объектов</td>
									</tr>
									<tr>
										<td style="width:130px; text-align:right; color:#808080; font-weight:bold; font-size:11px">Получатель: </td>
										<td style="text-align:left; font-size:13px; font-family:arial; padding-left:10px;"><xsl:value-of select="document/fields/balanceholdername"/></td>
									</tr>
								</table>

								<div style="background:#DEDEDE; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; height:25px; text-align:center; font-weight:bold; padding-top:10px; font-size:17px">
									Уведомление
								</div>
								<div style="background:#9A9A9A; width:100%; height:2px; margin-top:10px"/>
								<div style="width:95%; min-height:250px; margin:50px auto">
									<div style="width:20px; display:inline-block"/><xsl:value-of select="document/fields/viewtext"/>
								</div>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-top:10px"/>
								<div style="width:100%; padding:10px 0 10px 0; text-align:center; font-size:10px">
									Данное уведомление было сформировано и отправлено сервером уведомлений автоматически
								</div>
								<div style="background:#9A9A9A; width:100%; height:1px; margin-bottom:10px"/>
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