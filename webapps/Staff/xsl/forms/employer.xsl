<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/form.xsl"/>
	<xsl:import href="../templates/sharedactions.xsl"/>
    <xsl:import href="e.xsl"/>
    <xsl:import href="responsibleperson.xsl"/>

	<xsl:variable name="doctype"><xsl:value-of select="/request/document/captions/employer/@caption"/></xsl:variable>
	<xsl:variable name="threaddocid" select="document/@docid"/>
	<xsl:variable name="editmode" select="/request/document/@editmode"/>
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="yes"/>
	<xsl:variable name="skin" select="request/@skin"/>
	<xsl:template match="/request">
		<html>
			<head>
				<title>
					<xsl:value-of select="document/captions/employer/@caption"/> - 4ms Structure
				</title>
				<xsl:call-template name="cssandjs"/>
				<xsl:call-template name="keypressactions"/>
				<script>
					function checkAll(el,classname) {
						if(!(el.checked)) {
							$("input[class="+classname+"]").each(function(){$(this).attr("disabled","disabled");})
							$("tr[class="+classname+"]").each(function(){$(this).attr("style","background:#ededed");})
						}else{
							$("input[class="+classname+"]").each(function(){$(this).removeAttr("disabled");})
							$("tr[class="+classname+"]").each(function(){$(this).removeAttr("style");})
						}
					}
				</script>
				<xsl:if test="/request/@lang = 'RUS'">
					<script>
					$(function() {
						$('#birthdate').datepicker({
							showOn: 'button',
							buttonImage: '/SharedResources/img/iconset/calendar.png',
							buttonImageOnly: true,
							regional:['ru'],
							showAnim: '',
							changeYear : true,
							changeMonth : true,
							yearRange: '-100y:c+nn',
						}).keyup(function(e) {
						    if(e.keyCode == 8 || e.keyCode == 46) {
						        $.datepicker._clearDate(this);
						    }
						});
					});
				</script>
				</xsl:if>
					<xsl:if test="/request/@lang = 'KAZ'">
						<script>
							$(function() {
								$('#birthdate').datepicker({
									showOn: 'button',
									buttonImage: '/SharedResources/img/iconset/calendar.png',
									buttonImageOnly: true,
									regional:['ru'],
									showAnim: '',
									changeYear:  true,
									yearRange: '-100:+0',
									changeMonth: true,
									monthNames: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
									'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
									monthNamesShort: ['Қаңтар','Ақпан','Наурыз','Сәуір','Мамыр','Маусым',
									'Шілде','Тамыз','Қыркүйек','Қазан','Қараша','Желтоқсан'],
									dayNames: ['жексебі','дүйсенбі','сейсенбі','сәрсенбі','бейсенбі','жұма','сенбі'],
									dayNamesShort: ['жек','дүй','сей','сәр','бей','жұм','сен'],
									dayNamesMin: ['Жс','Дс','Сс','Ср','Бс','Жм','Сн'],
								});
							}).keyup(function(e) {
									if(e.keyCode == 8 || e.keyCode == 46) {
      									$.datepicker._clearDate(this);
    								}
								});
						});
						</script>
					</xsl:if>
					<xsl:if test="/request/@lang = 'ENG'">
						<script>
							$(function() {
								$('#birthdate').datepicker({
									showOn: 'button',
									buttonImage: '/SharedResources/img/iconset/calendar.png',
									buttonImageOnly: true,
									regional:['ru'],
									showAnim: '',
									changeYear : true,
									changeMonth : true,
									yearRange: '-100y:c+nn',
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
								}).keyup(function(e) {
									if(e.keyCode == 8 || e.keyCode == 46) {
      									$.datepicker._clearDate(this);
    								}
								});
							});
						</script>
					</xsl:if>
					<xsl:if test="/request/@lang = 'CHN'">
						<script>
							$(function() {
								$('#birthdate').datepicker({
									showOn: 'button',
									buttonImage: '/SharedResources/img/iconset/calendar.png',
									buttonImageOnly: true,
									regional:['en-GB'],
									showAnim: '',
									changeYear : true,
									changeMonth : true,
									yearRange: '-100y:c+nn',
									closeText: '关闭',
									prevText: '&#x3c;上月',
									nextText: '下月&#x3e;',
									currentText: '今天',
									monthNames: ['一月','二月','三月','四月','五月','六月',
									'七月','八月','九月','十月','十一月','十二月'],
									monthNamesShort: ['一','二','三','四','五','六',
									'七','八','九','十','十一','十二'],
									dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
									dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
									dayNamesMin: ['日','一','二','三','四','五','六'],
									weekHeader: '周',
									firstDay: 1,
									isRTL: false,
									showMonthAfterYear: true,
									yearSuffix: '年',
								}).keyup(function(e) {
									if(e.keyCode == 8 || e.keyCode == 46) {
      									$.datepicker._clearDate(this);
    								}
								});
							});
						</script>
					</xsl:if>
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
										   case 68:
										   		<!-- клавиша d -->
										     	e.preventDefault();
										     	$("#btnnewdep").click();
										      	break;
										   case 69:
										   		<!-- клавиша e -->
										     	e.preventDefault();
										     	$("#btnnewemp").click();
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
							$("#btnnewdep").hotnav({keysource:function(e){ return "d"; }});
							$("#btnnewemp").hotnav({keysource:function(e){ return "e"; }});
							$("#currentuser").hotnav({ keysource:function(e){ return "u"; }});
							$("#logout").hotnav({keysource:function(e){ return "q"; }});
							$("#helpbtn").hotnav({keysource:function(e){ return "h"; }});
						}
					]]>
				</script>
			</head>
			<body>
				<xsl:variable name="status" select="@status"/>
				<div id="docwrapper">
					<xsl:call-template name="documentheader"/>	
					<div class="formwrapper">
						<div class="formtitle">
							<div class="title">
								<xsl:call-template name="doctitleBoss"/>
							</div>
						</div>
						<div class="button_panel">
							<span style="float:left">
								<xsl:call-template name="showxml"/>
								<xsl:if test="document/actionbar/action[@id = 'save_and_close']/@mode='ON'">
                                    <button title= "{document/captions/saveclose/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnsavedoc">
                                        <xsl:attribute name="onclick">javascript:SaveFormJquery(&quot;<xsl:value-of select="history/entry[@type eq 'page'][last()]"/>&amp;page=<xsl:value-of select="document/@openfrompage"/>&quot;)</xsl:attribute>
                                        <span>
                                            <img src="/SharedResources/img/classic/icons/disk.png" class="button_img"/>
                                            <font class="button_text"><xsl:value-of select="document/captions/saveclose/@caption"/></font>
                                        </span>
                                    </button>
								</xsl:if>
								<xsl:if test="document/@status !='new'">
								<xsl:if test="document/actionbar/action[@id = 'NEW_DEPARTMENT']/@mode='ON'">
									<button title= "{document/captions/newdept/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnnewdep" style="margin-left:5px">
										<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=structure&amp;id=department&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
										<span>
											<img src="/SharedResources/img/classic/icons/package_add.png" class="button_img"/>
											<font class="button_text"><xsl:value-of select="document/captions/newdept/@caption"/></font>
										</span>
									</button>
									</xsl:if>
									<xsl:if test="document/actionbar/action[@id = 'NEW_EMPLOYER']/@mode='ON'">
									<button title= "{document/captions/newemp/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnnewemp" style="margin-left:5px">
										<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=structure&amp;id=employer&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
										<span>
											<img src="/SharedResources/img/classic/icons/user_add.png" class="button_img"/>
											<font class="button_text"><xsl:value-of select="document/captions/newemp/@caption"/></font>
										</span>
									</button>
									</xsl:if>
								</xsl:if>

                                <xsl:if test="document/actionbar/action[@id = 'activate']/@mode='ON'">
                                    <button title= "{document/actionbar/action[@id = 'activate']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnActivate" style="margin-left:5px">
                                        <xsl:attribute name="onclick">activateContractor('responsibleperson', '<xsl:value-of select="document/fields/userid" />')</xsl:attribute>
                                        <span>
                                            <img src="/SharedResources/img/classic/icons/tick.png" class="button_img"/>
                                            <font class="button_text"><xsl:value-of select="document/actionbar/action[@id = 'activate']/@caption"/></font>
                                        </span>
                                    </button>
                                </xsl:if>
                                <xsl:if test="document/actionbar/action[@id = 'send_notification']/@mode='ON'">
                                    <button title= "{document/actionbar/action[@id = 'send_notification']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnSendNotification" style="margin-left:5px">
                                        <xsl:attribute name="onclick">sendNotification('<xsl:value-of select="document/fields/userid" />')</xsl:attribute>
                                        <span>
                                            <img src="/SharedResources/img/classic/icons/email_edit.png" class="button_img"/>
                                            <font class="button_text"><xsl:value-of select="document/actionbar/action[@id = 'send_notification']/@caption"/></font>
                                        </span>
                                    </button>
                                </xsl:if>
							</span>
							<span style="float:right; margin-right:5px">
								<xsl:call-template name="cancelwithjson"/>
							</span>
						</div>
						<div style="clear:both"/>
						<div style="-moz-border-radius:0px;height:1px; width:100%; margin-top:10px;"/>
						<div style="clear:both"/>
                           <xsl:choose>
                               <xsl:when test="document/fields/form = 'E'">
                                   <xsl:call-template name="employer" />
                               </xsl:when>
                               <xsl:when test="document/fields/form = 'responsibleperson'">
                                   <xsl:call-template name="responsibleperson" />
                               </xsl:when>
                               <xsl:otherwise>
                                   <xsl:call-template name="employer" />
                               </xsl:otherwise>
                           </xsl:choose>

						<div style="height:10px"/>
					</div>
				</div>
				<xsl:call-template name="formoutline"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>