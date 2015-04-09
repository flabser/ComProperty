<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- Тип документа -->
	<xsl:template name="doctitle">
		<font style="font-size:18px">
			<xsl:value-of select="document/fields/title"/>
		</font>
	</xsl:template>
	
	<xsl:template name="fields_tooltip">
		<script type="text/javascript">
			$(document).ready(function(){
				$(".td_editable[title],.textarea_editable[title]").tipTip({maxWidth: "300px", defaultPosition: "right", activation: "hover", delay:"200"});
			})
		</script>
	</xsl:template>
	
	<xsl:template name="htmlareaeditor">
		<script type="text/javascript">  
			$(function() {
        		//$("textarea").htmlarea(); 
	        	$("#txtDefaultHtmlArea").htmlarea(); // Initialize jHtmlArea's with all default values
           		$("#btnRemoveCustomHtmlArea").click(function() {
            		$("#txtCustomHtmlArea").htmlarea("dispose");
           		});
        	});
		</script>
	</xsl:template>

	<!-- Набор javascript библиотек -->
	<xsl:template name="cssandjs">
		<link type="text/css" rel="stylesheet" href="classic/css/main.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="classic/css/form.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="classic/css/actionbar.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="classic/css/dialogs.css?ver=3"/>
		<link type="text/css" rel="stylesheet" href="/SharedResources/jquery/css/smoothness/jquery-ui-1.8.20.custom.css"/>
		<link type="text/css" rel="Stylesheet" media="screen" href="/SharedResources/jquery/js/tiptip/tipTip.css"/>
		<link rel="Stylesheet" type="text/css" href="/SharedResources/jquery/js/jhtmlarea/style/jHtmlArea.css"/>
		<link type="text/css" rel="stylesheet" href="/SharedResources/jquery/js/hotnav/jquery.hotnav.css"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/jquery-1.4.2.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.core.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.effects.core.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.widget.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/jquery.ui.datepicker.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.mouse.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.slider.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.progressbar.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.autocomplete.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.draggable.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.position.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.dialog.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.tabs.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.button.min.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/tiptip/jquery.tipTip.js"/>
		<script type="text/javascript" src="classic/scripts/form.js?ver=3"/>
		<script type="text/javascript" src="classic/scripts/coord.js?ver=3"/>
		<script type="text/javascript" src="classic/scripts/dialogs.js?ver=3"/>
		<script type="text/javascript" src="classic/scripts/outline.js?ver=3"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/cookie/jquery.cookie.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/jhtmlarea/scripts/jHtmlArea-0.7.0.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/hotnav/jquery.hotkeys.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/hotnav/jquery.hotnav.js"/>
		<script type="text/javascript" src="/SharedResources/jquery/js/moment.js"/>
		<script>
			cancelcaption='<xsl:value-of select="document/captions/cancel/@caption"/>'
			docfilter='<xsl:value-of select="document/captions/docfilter/@caption"/>'
			changeviewcaption='<xsl:value-of select="document/captions/changeview/@caption"/>'
			receiverslistcaption='<xsl:value-of select="document/captions/receiverslist/@caption"/>'
			commentcaption='<xsl:value-of select="document/captions/commentcaption/@caption"/>'
			correspforacquaintance='<xsl:value-of select="document/captions/correspforacquaintance/@caption"/>'
			searchcaption='<xsl:value-of select="document/captions/searchcaption/@caption"/>'
			contributorscoord='<xsl:value-of select="document/captions/contributorscoord/@caption"/>'
			type='<xsl:value-of select="document/captions/type/@caption"/>'
			hours='<xsl:value-of select="document/captions/hours/@caption"/>'
			yescaption='<xsl:value-of select="document/captions/yescaption/@caption"/>'
			nocaption='<xsl:value-of select="document/captions/nocaption/@caption"/>'
			warning='<xsl:value-of select="document/captions/warning/@caption"/>'
			documentsavedcaption = '<xsl:value-of select="document/captions/documentsavedcaption/@caption"/>';
			documentmarkread = '<xsl:value-of select="document/captions/documentmarkread/@caption"/>';
			pleasewaitdocsave = '<xsl:value-of select="document/captions/pleasewaitdocsave/@caption"/>';
			lang='<xsl:value-of select="@lang"/>';
			redirectAfterSave = '<xsl:value-of select="history/entry[@type eq 'page'][last()]"/>&amp;page=<xsl:value-of select="document/@openfrompage"/>'
			showauthor = '<xsl:value-of select="document/captions/showauthor/@caption"/>';
			showrecipient = '<xsl:value-of select="document/captions/showrecipient/@caption"/>';
			successfullydeleted = '<xsl:value-of select="document/captions/successfullydeleted/@caption"/>';
			choosemember = '<xsl:value-of select="document/captions/choosemember/@caption"/>';
			choosevalue = '<xsl:value-of select="document/captions/choosevalue/@caption"/>';
			alreadychosen = '<xsl:value-of select="document/captions/alreadychosen/@caption"/>';
			isrecieverofsz = '<xsl:value-of select="document/captions/isrecieverofsz/@caption"/>';
			issignerofsz = '<xsl:value-of select="document/captions/issignerofsz/@caption"/>';
			hourss = '<xsl:value-of select="document/captions/hourss/@caption"/>';
			hours = '<xsl:value-of select="document/captions/hours/@caption"/>';
			day = '<xsl:value-of select="document/captions/day/@caption"/>';
			days = '<xsl:value-of select="document/captions/days/@caption"/>';
			saving = '<xsl:value-of select="document/captions/saving/@caption"/>';
			showfilename = '<xsl:value-of select="document/captions/showfilename/@caption"/>'; 
			addcomment = '<xsl:value-of select="document/captions/addcomment/@caption"/>'; 
			removedfromcontrol = '<xsl:value-of select="document/captions/removedfromcontrol/@caption"/>';
			attention = '<xsl:value-of select="document/captions/attention/@caption"/>';
			add_comment='<xsl:value-of select="document/captions/add_comment/@caption"/>';
			unlimited ='<xsl:value-of select="document/captions/unlimited/@caption"/>';
			newcoord= '<xsl:value-of select="document/captions/newcoord/@caption"/>';
			delete_file= '<xsl:value-of select="document/captions/delete_file/@caption"/>';
			onlynumber= '<xsl:value-of select="document/captions/onlynumber/@caption"/>';
		</script>
		<script type="text/javascript">    
			$(function(){
				$("#tabs").tabs();
				$("button").button();
			});
    	</script>
	</xsl:template>

	<xsl:template name="formoutline">
		<div id="outline">
			<div id="outline-container" style="width:297px; padding-top:10px">
				<xsl:for-each select="//response/content/outline">
					<div>
						<div>
							<div class="treeentry" style="padding:3px 0px 3px 0px; border:1px solid #F9F9F9; cursor:pointer" onclick="javascript:ToggleCategory(this)">								
								<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:6px; cursor:pointer"/>
								<img src="/SharedResources/img/classic/1/folder_open_view.png" style="margin-left:5px;"/>
								<font style="font-family:arial; font-size:0.9em; margin-left:4px; vertical-align:3px">											
									<xsl:value-of select="@caption"/>
								</font>
							</div>
						</div>
						<div style="clear:both;"/>
						<div id="{@id}">
							<xsl:call-template name="outline-section-state"/>
							<xsl:for-each select="entry">
								<div class="entry treeentry" style="width:298px; padding:3px 0px 3px 0px; border:1px solid #F9F9F9;">
									<xsl:if test="/request/@id = @id">
										<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
									</xsl:if>
									<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
										<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
									</xsl:if>
									<xsl:if test="./entry">
										<div onclick ="javascript:ToggleCategory(this)">
											<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:35px; cursor:pointer; float:left"/>
										</div>
									</xsl:if>
									<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important;" title="{@hint}">
										<div class="viewlink">
											<xsl:if test="/request/@id = @id">
												<xsl:attribute name="class">viewlink_current</xsl:attribute>										
											</xsl:if>
											<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
												<xsl:attribute name="class">viewlink_current</xsl:attribute>										
											</xsl:if>
											<div style="padding-left:35px;">
												<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
													<img src="/SharedResources/img/classic/1/doc_view.png"/>
												</xsl:if>
												<xsl:if test="@id ='favdocs'">
													<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
												</xsl:if>
												<xsl:if test="@id ='recyclebin'">
													<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
												</xsl:if>													 
												<font class="viewlinktitle">												
													 <xsl:value-of select="@caption"/>										
												</font>
											</div>
										</div>
									</a>
								</div>
								<div style="clear:both"/>
								<div class="outlineEntry" id="{@id}">
									<xsl:call-template name="outline-section-state"/>
									<xsl:for-each select="entry">
										<div class="entry treeentry" style="width:298px; padding:3px 0px 3px 0px; border:1px solid #F9F9F9;">
											<xsl:if test="/request/@id = @id">
												<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
											</xsl:if>
											<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
												<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
											</xsl:if>
											<xsl:if test="./entry">
												<div onclick ="javascript:ToggleCategory(this)">
													<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:49px; cursor:pointer; float:left"/>
												</div>
											</xsl:if>
											<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important;" title="{@hint}">
												<div class="viewlink">
													<xsl:if test="/request/@id = @id">
														<xsl:attribute name="class">viewlink_current</xsl:attribute>										
													</xsl:if>
													<xsl:if test="contains(@url, //current_outline_entry/response/content/entry/@entryid) and //current_outline_entry/response/content/entry/@entryid != '' ">
														<xsl:attribute name="class">viewlink_current</xsl:attribute>										
													</xsl:if>
													<div style="padding-left:65px;">
														<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
															<img src="/SharedResources/img/classic/1/doc_view.png"/>
														</xsl:if>
														<xsl:if test="@id ='favdocs'">
															<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
														</xsl:if>
														<xsl:if test="@id ='recyclebin'">
															<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
														</xsl:if>													 
														<font class="viewlinktitle">												
															 <xsl:value-of select="@caption"/>										
														</font>
													</div>
												</div>
											</a>
										</div>
										<div style="clear:both"/>
										<div class="outlineEntry" id="{@id}">
											<xsl:call-template name="outline-section-state"/>
											<xsl:for-each select="entry">
												<div class="entry treeentry" style="width:283px; padding:3px 0px 3px 15px; border:1px solid #F9F9F9;">
													<xsl:if test="/request/@id = @id">
														<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
													</xsl:if>
													<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
														<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
													</xsl:if>
													<xsl:if test="./entry">
														<div onclick ="javascript:ToggleCategory(this)">
															<img src="/SharedResources/img/classic/1/minus.png" style="margin-left:49px; cursor:pointer; float:left"/>
														</div>
													</xsl:if>
													<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important" title="{@hint}">
														<div class="viewlink">
															<xsl:if test="/request/@id = @id">
																<xsl:attribute name="class">viewlink_current</xsl:attribute>										
															</xsl:if>	
															<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
																<xsl:attribute name="class">viewlink_current</xsl:attribute>										
															</xsl:if>	
															<div style="padding-left:65px;">
																<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
																	<img src="/SharedResources/img/classic/1/doc_view.png"/>
																</xsl:if>
																<xsl:if test="@id ='favdocs'">
																	<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
																</xsl:if>
																<xsl:if test="@id ='recyclebin'">
																	<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
																</xsl:if>													 
																<font class="viewlinktitle">												
																	 <xsl:value-of select="@caption"/>										
																</font>
															</div>
														</div>
													</a>
												</div>
												<div style="clear:both"/>
												<div class="outlineEntry" id="{@id}">
													<xsl:call-template name="outline-section-state"/>
													<xsl:for-each select="entry">
														<div class="entry treeentry" style="width:283px; padding:3px 0px 3px 15px; border:1px solid #F9F9F9;">
															<xsl:if test="/request/@id = @id">
																<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
															</xsl:if>
															<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
																<xsl:attribute name="class">entry treeentrycurrent</xsl:attribute>										
															</xsl:if>
															<a href="{@url}" style="width:90%; vertical-align:top; text-decoration:none !important" title="{@hint}">
																<div class="viewlink">
																	<xsl:if test="/request/@id = @id">
																		<xsl:attribute name="class">viewlink_current</xsl:attribute>										
																	</xsl:if>	
																	<xsl:if test="contains(@url, /request/page/outline/page/current_outline_entry/response/content/entry/@entryid) and /request/page/outline/page/current_outline_entry/response/content/entry/@entryid != '' ">
																		<xsl:attribute name="class">viewlink_current</xsl:attribute>										
																	</xsl:if>	
																	<div style="padding-left:85px;">
																		<xsl:if test="@id !='favdocs' and (@id != 'recyclebin')">
																			<img src="/SharedResources/img/classic/1/doc_view.png"/>
																		</xsl:if>
																		<xsl:if test="@id ='favdocs'">
																			<img src="/SharedResources/img/iconset/star_full.png" height="17px"/>
																		</xsl:if>
																		<xsl:if test="@id ='recyclebin'">
																			<img src="/SharedResources/img/iconset/bin.png" height="17px"/>
																		</xsl:if>													 
																		<font class="viewlinktitle">												
																			 <xsl:value-of select="@caption"/>										
																		</font>
																	</div>
																</div>
															</a>
														</div>
													</xsl:for-each>
												</div>
											</xsl:for-each>
										</div>
									</xsl:for-each>
								</div>
							</xsl:for-each>
						</div>
					</div>
				</xsl:for-each>
			</div>
		</div>
		<div id="resizer" style="position:absolute; top: 80px; left:1px; background:#E6E6E6; width:12px; bottom:0px; border-radius: 0 6px 6px 0; border: 1px solid #adadad; border-left: ; cursor:pointer" onclick="javascript:openformpanel()">
			<span  id="iconresizer" class="ui-icon ui-icon-triangle-1-e" style="margin-left:-2px; position:relative; top:49%"></span>
		</div>
	</xsl:template>
	
	<xsl:template name="outline-section-state">
		<script>
			if($.cookie("COMMUNAL_<xsl:value-of select='@id'/>") != 'null'){
				$("#<xsl:value-of select='@id'/>").css("display",$.cookie("COMMUNAL_<xsl:value-of select='@id'/>"))
				if($.cookie("COMMUNAL_<xsl:value-of select='@id'/>") == "none"){
					$("#<xsl:value-of select='@id'/>").prev().prev().children().children("img:first").attr("src","/SharedResources/img/classic/1/plus.png")							
				}else{
					$("#<xsl:value-of select='@id'/>").prev().prev().children().children("img:first").attr("src","/SharedResources/img/classic/1/minus.png")							
				}
			}
		</script>	
	</xsl:template>
	
	<xsl:template name="documentheader">
		<div style="position:absolute; top:0px; left:0px; width:100%; background:url(classic/img/yellow_background.jpg); height:70px; border-bottom:1px solid #fcdd76; z-index:2">
			<span style="float:left">
				<font style="font-size:1.5em; vertical-align:50px; color:#555555; margin-left:20px; line-height:60px">АИС 'Коммунальное Имущество'</font>
			</span>
			<span style="float:right; padding:5px 5px 5px 0px">
				<a id="currentuser" title="{document/captions/view_userprofile/@caption}" href=" Provider?type=edit&amp;element=userprofile&amp;id=userprofile" style="text-decoration:none;color: #555555 ; font: 11px Tahoma; font-weight:bold">
					<font>
						<xsl:value-of select="@username"/>
					</font>
				</a>
				<a id="logout" href="Logout" title="{document/captions/logout/@caption}" style="text-decoration:none;color: #555555 ; font: 11px Tahoma; font-weight:bold">
					<font style="margin-left:20px;"><xsl:value-of select="document/captions/logout/@caption"/></font> 
				</a>
				<a id="helpbtn" href="Provider?type=static&amp;id=help_category_list" title="{document/captions/help/@caption}" style="text-decoration:none;color: #555555 ; font: 11px Tahoma; font-weight:bold">
					<font style="margin-left:20px;"><xsl:value-of select="document/captions/help/@caption"/></font> 
				</a>
			</span>
		</div>
	</xsl:template>

	<xsl:template name="ECPsignFields">
		<input type="hidden" name="sign" id="sign" value="{sign}" style="width:100%;" />
		<input type="hidden" name="signedfields" id="signedfields" value="{signedfields}" style="width:100%;" />
		<!-- <APPLET CODE="kz.flabs.eds.applet.EDSApplet" NAME="edsApplet" ARCHIVE="eds.jar, commons-codec-1.3.jar" HEIGHT="1" WIDTH="1">
			<param name="ARCHIVE" value="/eds.jar, /commons-codec-1.3.jar" />
		</APPLET> -->

		<xsl:if test="document/@canbesign='1111'">
			<script type="text/javascript" src="/edsApplet/js/jquery.blockUI.js" charset="utf-8"></script>
        	<script type="text/javascript" src="/edsApplet/js/crypto_object.js" charset="utf-8"></script>
        	<script type="text/javascript">
				edsApp.init();
			</script>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="markisread">
		<xsl:if test="document[@isread = 0][@status != 'new']">
			<script>
				markRead(<xsl:value-of select="document/@doctype"/>, <xsl:value-of select="document/@docid"/>);
			</script>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="docleasingcontract">
	<p style="margin-bottom: 0in; line-height: 100%" align="CENTER">
		<font face="Times New Roman, serif">
			<font size="3">ТИПОВАЯ
				ФОРМА</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="CENTER">
		<font face="Times New Roman, serif">
			<font size="3">ДОГОВОР
				ИМУЩЕСТВЕННОГО
				НАЙМА (АРЕНДЫ)</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="CENTER">
		<font face="Times New Roman, serif">
			<font size="3">________________________________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="CENTER">
		<font face="Times New Roman, serif">
			<font size="3">(наименование
				объекта)</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="CENTER">
		<font face="Times New Roman, serif">
			<font size="3">г.
				___________ N ___________ " ___ " _________ 20 __
				г.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">_____________________________________________
				(наименование
				теркомитета)
				территориальный
				комитет государственного
				имущества и
				приватизации
				именуемый в
				дальнейшем
				"Наймодатель",
				в лице Председателя
				______________________________ (Ф.И.О.),
				действующего
				на основании
				Положения "О
				________________________ (наименование
				текомитета)
				территориальном
				комитете
				государственного
				имущества и
				приватизации",
				утвержденного
				приказом Комитета
				государственного
				имущества и
				приватизации
				МФ РК N _____ от " ____ "
				_______________ 200 ____ года с одной
				стороны, и
				_________________________________ (наименование
				нанимателя)
				в лице ________________________
				(Ф.И.О.) именуемый
				в дальнейшем
				"Наниматель",
				с другой стороны
				совместно
				именуемые как
				"Стороны",
				заключили
				настоящий
				Договор о
				нижеследующем.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>1.
					Предмет договора</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">1.
				Предмет договора
				является
				предоставление
				в имущественный
				наем (аренду)
				_______________________________ расположение
				по адресу:
				_____________ (наименование
				Объекта)
				_____________________ (местонахождение
				и краткая
				характеристика
				Объекта)
				__________________________________________ (местонахождение
				и краткая
				характеристика
				Объекта) именуемого
				в дальнейшем
				"Объект", находящегося
				на балансе
				__________________________________________ (наименование
				балансодержателя):</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">(1)
				на основании
				приказа
				____________________________________ наименование
				теркомитета
				от " ____ " _________________ 200 __ года
				N _______;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">(2)
				по результатам
				тендера в
				соответствии
				с протокольным
				решением от
				" ___ " _____________ 200 __ года N
				_____.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">2.
				Наймодатель
				передает, а
				Наниматель
				принимает в
				имущественный
				наем (аренду)
				Объект с " ____ "
				____________ 200 __ года по "
				____" __________________ 200 ___ года
				для использования
				в целях
				____________________________ (назначение
				Объект, либо
				условия тендера).</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">По
				истечении
				указанного
				срока данный
				договор прекращает
				свое действие.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>2.
					Общие условия</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">3.
				Прием - передача
				Объект в аренду
				осуществляется
				по акту приема
				- передачи (с
				отражением
				фактического
				состояния
				Объекта на
				момент передачи),
				которой подписывается
				представителями
				Нанимателя,
				балансодержателя
				и утверждается
				Наймодателем
				и является
				неотъемлемой
				частью настоящего
				договора.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">4.
				Подписанием
				Договора Наймодатель
				и балансодержатель,
				кроме всего
				прочего, удостоверяют,
				что сдаваемый
				Объект на момент
				передачи не
				должен, не находится
				под арестом
				и не может быть
				истребован
				в течение действия
				договора какой
				- либо из сторон,
				не имеющей
				отношения к
				договору.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">5.
				Изменение
				юридического
				статуса либо
				организационно
				- правовой формы
				сторон не меняют
				сути договора
				и все права и
				обязанности
				переходят к
				соответствующим
				правопреемникам,
				за исключением
				случаев, когда
				стороны изъявят
				желание расторгнуть
				договор, изменить
				его, либо нормы
				права требует
				его переформирования.
				При этом стороны
				обязаны информировать
				друг друга об
				изменении
				юридического
				статуса после
				факта перерегистрации.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">6.
				Договор, заключенный
				на срок свыше
				одного года,
				подлежит
				государственный
				регистрации.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">Государственная
				регистрация
				Договора
				осуществляется
				за счет средств
				Нанимателя.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>3.
					Права и обязанности
					Сторон</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">7.
				Наймодатель
				имеет право:</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">1)
				по согласованию
				с балансодержателем
				дать письменное
				разрешение
				Нанимателю
				на перепланировку
				или переоборудование
				Объекта, расположенных
				в нем сетей и
				коммуникаций;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">2)
				осуществлять
				контроль за
				своевременностью
				перечисления
				арендной платы,
				установленной
				подпунктом
				1) пункта 10 Договора;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">3)
				начислить пеню
				за несвоевременность
				внесения арендной
				платы;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">4)
				пролонгировать,
				вносить изменения
				и дополнения
				в Договор по
				согласованию
				Сторон;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">5)
				осуществлять
				проверки целевого
				использования
				Объекта.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">8.
				Наниматель
				имеет право:</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">1)
				вносить арендную
				плату авансом;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">2)
				с письменного
				согласия
				балансодержателя
				обратиться
				к Наймодателю
				за разрешением
				на перепланировку
				или переоборудование
				Объекта, расположенных
				в нем сетей и
				коммуникаций
				и передачу в
				субаренду;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">3)
				вносить предложения
				Наймодателю
				о пролонгации,
				внесении изменений
				и дополнений
				или расположении
				Договора.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">9.
				Наймодатель
				обязан:</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">1)
				обеспечить
				передачу Объекта
				балансодержателем
				Нанимателю
				по акту приема
				- передачи и
				его утверждение
				в течение 30
				календарных
				дней с даты
				принятия решения
				о передаче
				Объекта в
				имущественный
				наем (аренду);</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">2)
				не препятствовать
				Нанимателю
				владеть и
				пользоваться
				Объектом в
				установленном
				Договором
				порядке;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">3)
				в случае изменения
				условия условий
				Договора или
				размера арендной
				платы письменно
				уведомить об
				этом нанимателя
				за месяц до
				очередного
				срока внесения
				арендной платы;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">4)
				направить
				Нанимателю
				извещение о
				начислении
				пени и штрафов
				за просроченные
				платежи не
				позднее 10 дней
				до очередного
				срока внесения
				арендной платы.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">10.
				Наниматель
				обязан:</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">1)
				вносить арендную
				плату, а также
				другие платежи
				(штрафов, пени)
				не позднее
				_________________________________ (сроки
				внесения арендной
				платы);</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">2)
				ежеквартально
				производить
				сверку расчетов
				с Наймодателем
				с предоставлением
				копий платежных
				поручений
				(квитанций) в
				течение 3 трех
				дней после
				внесения арендной
				платы (штрафов,
				пени);</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">3)
				использовать
				принятый Объект
				исключительно
				в целях, предусмотренных
				Договором;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">4)
				содержатель
				Объект в надлежащем
				порядке, не
				совершать
				действий, способных
				вызвать повреждение
				Объекта или
				расположенных
				в нем инженерных
				коммуникаций;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">5)
				в случае выхода
				из строя отдельных
				элементов
				Объекта, инженерного
				оборудования,
				как по вине
				Нанимателя,
				так и в силу
				естественного
				износа, производить
				ремонтные
				работы за свой
				счет;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">6)
				не осуществлять
				без предварительного
				письменного
				разрешения
				Наймодателя
				перепланировку
				или переоборудование
				Объекта, расположенных
				в нем сетей и
				коммуникаций;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">7)
				беспрепятственно
				допускать на
				Объект и прилегающий
				к нему земельный
				участок представителей
				Наймодатель,
				служб санитарного
				надзора и других
				государственных
				органов, контролирующих
				соблюдение
				законодательства
				и иных норм,
				касающихся
				порядка использования
				и эксплуатации
				Объекта, в
				установленные
				ими сроки устранять
				зафиксированные
				нарушения;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">8)
				не передавать
				свои права по
				Договору в
				залоге, не вносить
				их в качестве
				вклада в установленный
				капитал хозяйственных
				товариществ,
				акционерных
				обществ взноса
				в производственных
				кооператив;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">9)
				за месяц до
				истечения срока
				действия договора
				аренды подать
				письменное
				заявление о
				желании продлить
				аренду Объекта.
				Отсутствие
				такового заявления
				дает основание
				Наймодателю
				передать Объект
				в имущественный
				наем (аренду)
				другим юридическим
				или физическим
				лицам;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">10)
				при расторжении
				Договора обеспечить
				возврат Объекта
				в течение 10
				календарных
				дней балансодержателю
				по акту приема
				- передачи,
				подписанному
				Нанимателем
				и балансодеражателем
				и утвержденному
				Наймодателем;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">11)
				возместить
				ущерб в случае
				возврата Объекта
				в нерабочем
				или неудовлетворенном
				техническом
				состоянии (с
				износом, превышающим
				нормативные
				показатели).</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>4.
					Арендная плата
					и порядок расчетов</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">11.
				Размер арендной
				платы за имущественный
				наем Объекта
				составляет
				_____________ тенге в месяц
				(расчет арендной
				платы) приведен
				в приложении
				к Договору,
				являющемуся
				неотъемлемой
				частью Договора).</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">12.
				В плату за
				имущественный
				наем не включаются
				платежи за
				коммунальные
				услуги, отчисления
				на текущий и
				капитальный
				ремонт, платежи
				за обслуживание
				Объекта. Эти
				платежи оплачиваются
				Нанимателем
				непосредственно
				ведомственной
				охране, эксплуатационным,
				коммунальным,
				санитарным
				и другим службам,
				предоставляющим
				услуги, либо
				по Договору
				с балансодержателем.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">13.
				Арендная плата,
				а также другие
				платежи (штрафы,
				пеня) перечисляется
				Нанимателем
				не позднее
				_____________________________ (сроки
				внесения вредной
				арендной платы)
				на расчетный
				счет НК по
				______________________________________, (наименование
				налогового
				органа) РНН
				____________________ , БИК
				___________________________ (наименование
				управление
				Казначейства)
				управление
				Казначейства
				КБЕ ________ код бюджетной
				классификации
				201228 (в обязательном
				порядке в платежном
				поручении
				указывать код
				бюджетной
				классификации).</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">14.
				Размер арендной
				платы корректируется
				в соответствии
				с индексом
				инфляции по
				данным органов
				статистики,
				при этом Наймодатель
				письменно
				уведомляет
				Нанимателя
				об изменении
				арендной платы
				не позднее 30
				дней до очередного
				срока внесения
				арендной платы.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">15.
				При нарушении
				Нанимателем
				срока внесения
				арендной платы,
				предусмотренный
				подпунктом
				1) пункта 10 Договора,
				Наниматель
				оплачивает
				пеню в размере
				0,5% от неуплаченной
				суммы задолженности
				за каждый день
				просрочки.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">16.
				За исполнение
				Нанимателем
				Объект не по
				назначению,
				а также за сдачу
				Объекта в субаренду
				без согласия
				Наймодателя,
				Наниматель
				уплачивает
				штраф в размере
				_________________ процентов
				суммы годовой
				платы за имущественный
				наем (аренду).</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">16.
				За использование
				Нанимателем
				Объекта не по
				назначению,
				а также за сдачу
				Объекта в субаренду
				без согласия
				Наймодателя,
				Наниматель
				уплачивает
				штраф в размере
				___________________ процентов
				суммы годовой
				арендной платы
				за имущественный
				наем (аренду).</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">17.
				В случае досрочного
				расположения
				Договора по
				инициативе
				Нанимателя
				оплаченная
				авансом арендная
				плата не возвращается.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>5.
					Условия расторжения
					договора</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">18.
				Наниматель
				вправе досрочно
				расторгнуть
				Договор, предупредив
				об этом Наймодателя
				письменно не
				позднее чем
				за месяц до
				расторжения
				договора.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">19.
				Договор подлежит
				досрочному
				расторжению
				в одностороннем
				порядке по
				инициативе
				Наймодателя
				в случаях:</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">1)
				ликвидации
				Нанимателя
				как юридического
				лица;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">2)
				нарушения
				Нанимателем
				условий договора
				имущественного
				найма;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">3)
				по требованию
				Наймодателя
				или Нанимателя
				в случаях,
				предусмотренных
				законодательными
				актами или
				Договором;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">4)
				если Наниматель
				более двух раз
				по истечении
				установленного
				договором срока
				платежа не
				вносит плату
				за пользование
				имуществом;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">5)
				представления
				Нанимателю
				другого объекта
				с его письменного
				согласия;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">6)
				в случае, если
				сдаваемый в
				имущественный
				наем Объект
				передается
				на приватизацию;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">7)
				по письменному
				заявителю
				балансодержателя
				на имя Наймодателя;</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">8)
				в иных случаях,
				предусмотренных
				законодательством
				РК или Договором.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">21.
				Досрочное
				расположение
				Договора в
				одностороннем
				порядке может
				быть обжаловано
				в судебном
				порядке. До
				вступления
				решения суда
				в силу Объект
				сохраняется
				за Нанимателем,
				которой продолжает
				вносить плату
				за аренду согласно
				Договору.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>6.
					Особые условия</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">22.
				Договор составлен
				в двух экземплярах
				на государственном
				и русском языках,
				имеющих одинаковую
				юридическую
				силу, один из
				которых остается
				у Наймодателя
				и один - у Нанимателя.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">23.
				Изменения и
				дополнения
				к Договору
				имеют силу,
				если они совершены
				уполномоченными
				на то лицами.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">24.
				В случае, если
				Наниматель
				произвел за
				счет собственных
				средств с согласия
				балансодержателя
				и письменного
				разрешения
				Наймодателя
				улучшения,
				неотделимые
				без вреда для
				Объекта, Наниматель
				имеет право
				после прекращения
				Договора не
				возмещение
				балансодержателем
				стоимости этих
				улучшений.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">Отделимые
				улучшения
				Объекта, произведенные
				Нанимателем,
				является его
				собственностью.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">Стоимость
				неотделимых
				улучшений,
				произведенных
				Нанимателем
				без согласия
				Наймодателя
				и балансодержателя,
				возмещению
				не подлежит.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>7.
					Порядок рассмотрения
					споров</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">25.
				Споры между
				Сторонами,
				которые могут
				возникнуть
				из настоящего
				Договора или
				в связи с ним,
				разрешаются
				путем переговоров,
				а при не достижении
				согласия - в
				судебном порядке.</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">
				<b>8.
					Юридические
					адреса и банковские
					реквизиты
					Сторон</b>
			</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">"НАЙМОДАТЕЛЬ":
				"НАНИМАТЕЛЬ":</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">Председатель
				__________________________ ________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">(наименование
				теркомитета)
				________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">Государственного
				имущества и
				________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">приватизации
				__________________________ ________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">_______________________________________
				________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">(местонахождение)
				(наименование
				и</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">местонахождение</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">Нанимателя)</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">_______________________________________
				________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">(Ф.И.О.)
				(Ф.И.О.)</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<br/>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">_______________________________________
				________________________</font>
		</font>
	</p>
	<p style="margin-bottom: 0in; line-height: 100%" align="JUSTIFY">
		<font face="Times New Roman, serif">
			<font size="3">(Подпись)
				(Подпись)</font>
		</font>
	</p>
</xsl:template>
	
	<xsl:template name="docinfo">
		<br/>
		<table width="100%" border="0">
			<tr>
				<td class="fc">
					<xsl:value-of select="document/captions/statusdoc/@caption"/> :
				</td>
				<td>
					<font>
						<xsl:choose>
							<xsl:when test="document/@status='new'">
								<xsl:value-of select="document/captions/newdoc/@caption"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="document/captions/saved/@caption"/>
							</xsl:otherwise>
						</xsl:choose>
					</font>		
				</td>
			</tr>
			<tr>
				<td class="fc">
					<xsl:value-of select="document/captions/permissions/@caption"/> :
				</td>
				<td>
					<font>
					<xsl:choose>
					 <xsl:when test ="/request/document/fields/control/allcontrol = '0'"> 
						<xsl:value-of select="document/captions/readonly/@caption" />
					</xsl:when>
						<xsl:otherwise>
							<xsl:choose>
							<xsl:when test="document/@editmode='view'">
								<xsl:value-of select="document/captions/readonly/@caption"/>
							</xsl:when>
							<xsl:when test="document/@editmode='readonly'">
								<xsl:value-of select="document/captions/readonly/@caption" />
							</xsl:when>
							<xsl:when test="document/@editmode='edit'">
								<xsl:value-of select="document/captions/editing/@caption" />
							</xsl:when>
							<xsl:when test="document/@editmode='noaccess'">
								<xsl:value-of select="document/captions/readonly/@caption" />
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="document/captions/modenotdefined/@caption" />
							</xsl:otherwise>
							</xsl:choose>
						</xsl:otherwise>
					</xsl:choose>
					</font>
				</td>
			</tr>
			<xsl:if test="document/@status !='new'">
				<tr>
					<td class="fc">
						<xsl:value-of select="document/captions/infofread/@caption"/> :
					</td>
					<td>
						<script type="text/javascript">
							usersWhichReadInTable(this,<xsl:value-of select="document/@doctype"/>,<xsl:value-of select="document/@docid"/>)
						</script>
						<table class="table-border-gray" id="userswhichreadtbl" style="width:600px">
							<tr>
								<td style="width:350px; text-align:center">
									<font><xsl:value-of select="document/captions/whomread/@caption"/></font>
								</td>
								<td style="width:250px; text-align:center">
									<font><xsl:value-of select="document/captions/timeofreading/@caption"/></font>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="fc">
						<xsl:value-of select="document/captions/DS/@caption"/> :
					</td>
					<td>
						<font>
							<xsl:choose>
								<xsl:when test="document/@sign ='0'">
									<xsl:value-of select="document/captions/documentnotsigned/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='1'">
									<xsl:value-of select="document/captions/signistrue/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='2'">
									<xsl:value-of select="document/captions/signisfalse/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='3'">
									<xsl:value-of select="document/captions/invalidkey/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='4'">
									<xsl:value-of select="document/captions/algorithmnotfound/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='5'">
									<xsl:value-of select="document/captions/fillmechanismnotfound/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='6'">
									<xsl:value-of select="document/captions/invalidcharkey/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='7'">
									<xsl:value-of select="document/captions/invalidparalgo/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='8'">
									<xsl:value-of select="document/captions/totalexceptionkey/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='10'">
									<xsl:value-of select="document/captions/filecertnotfound/@caption"/>
								</xsl:when>
								<xsl:when test="document/@sign ='11'">
									<xsl:value-of select="document/captions/onefilenotfound4sign/@caption"/>
								</xsl:when>
								<xsl:otherwise>
								</xsl:otherwise>
							</xsl:choose>
						</font>		
					</td>
				</tr>
			</xsl:if>
		</table>
	</xsl:template>
	
	<xsl:template name="attach">
		<div id="attach" style="display:block;">
			<table style="border:0; border-collapse:collapse" id="upltable" width="99%">
				<xsl:if test="$editmode = 'edit'">
					<tr>
						<td class="fc">
							<xsl:value-of select="document/captions/attachments/@caption"/>:
						</td>
						<td>
							<input type="file" size="60" border="#CCC" name="fname" id="fileInput">
								<xsl:attribute name="onchange">javascript:submitFile('upload', 'upltable', 'fname'); ajaxFunction()</xsl:attribute>
							</input>&#xA0;
							<br/>
							<style>.ui-progressbar .ui-progressbar-value { background-image: url(/SharedResources/jquery/css/base/images/pbar-ani.gif); }</style>
							<div id="progressbar" style="width:370px; margin-top:5px; height:12px"></div>
							<div id="progressstate" style="width:370px; display:none">
								<font style="visibility:hidden; color:#999; font-size:11px; width:70%" id="readybytes"></font>
								<font style="visibility:hidden; color:#999; font-size:11px; float:right;" id="percentready"></font>
								<font style="visibility:hidden; text-align:center; color:#999; font-size:11px; width:30%; text-align:center" id="initializing">Подготовка к загрузке</font>
							</div>
						</td>
						<td></td>
					</tr>
				</xsl:if>
				<xsl:variable name='docid' select="document/@docid"/>
				<xsl:variable name='doctype' select="document/@doctype"/>
				<xsl:variable name='formsesid' select="formsesid"/>
				
				<xsl:for-each select="document/fields/rtfcontent/entry">
					<tr>
						<xsl:variable name='id' select='@hash'/>
						<xsl:variable name='filename' select='@filename'/>
						<xsl:variable name="extension" select="tokenize(lower-case($filename), '\.')[last()]"/>
						<xsl:variable name="resolution"/>
						<xsl:attribute name='id' select="$id"/>
						<td class="fc"></td>
						<td colspan="2">
							<div class="test" style="width:90%; overflow:hidden; display:inline-block">
								<xsl:choose>
									<xsl:when test="$extension = 'jpg' or $extension = 'jpeg' or $extension = 'gif' or $extension = 'bmp' or $extension = 'png'">
										<img class="imgAtt" title="{$filename}" style="border:1px solid lightgray; max-width:800px; max-height:600px; margin-bottom:5px">
											<xsl:attribute name="onload">checkImage(this)</xsl:attribute>
											<xsl:attribute name='src'>Provider?type=getattach&amp;formsesid=<xsl:value-of select="$formsesid"/>&amp;doctype=<xsl:value-of select="$doctype"/>&amp;key=<xsl:value-of select="@id"/>&amp;field=rtfcontent&amp;id=rtfcontent&amp;id=rtfcontent&amp;file=<xsl:value-of select='$filename'/></xsl:attribute>
										</img>
										<xsl:if test="$editmode = 'edit'">
											<xsl:if test="comment =''">
												<a href='' style="vertical-align:top;" title='tect'>
													<xsl:attribute name='href'>javascript:addCommentToAttach('<xsl:value-of select="$id"/>')</xsl:attribute>
													<img id="commentaddimg{$id}" src="/SharedResources/img/classic/icons/comment_add.png" style="width:16px; height:16px" >
														<xsl:attribute name="title" select="//document/captions/add_comment/@caption"/>
													</img>
												</a>
											</xsl:if>
											<a href='' style="vertical-align:top; margin-left:8px">
												<xsl:attribute name='href'>javascript:deleterow('<xsl:value-of select="$formsesid"/>','<xsl:value-of select='$filename'/>','<xsl:value-of select="$id" />')</xsl:attribute>
												<img src="/SharedResources/img/iconset/cross.png" style="width:13px; height:13px">
												<xsl:attribute name="title" select="//document/captions/delete_file/@caption"/>
												</img>
											</a>
										</xsl:if>
									</xsl:when>
									<xsl:otherwise>
										<img src="/SharedResources/img/iconset/file_extension_{$extension}.png" style="margin-right:5px">
											<xsl:attribute name="onerror">javascript:changeAttIcon(this)</xsl:attribute>
										</img>
										<a style="vertical-align:5px">
											<xsl:attribute name='href'>Provider?type=getattach&amp;formsesid=<xsl:value-of select="$formsesid"/>&amp;doctype=<xsl:value-of select="$doctype"/>&amp;key=<xsl:value-of select="@id"/>&amp;field=rtfcontent&amp;id=rtfcontent&amp;file=<xsl:value-of select='$filename'/>	</xsl:attribute>
											<xsl:value-of select='replace($filename,"%2b","+")'/>
										</a>&#xA0;&#xA0;
										<xsl:if test="$editmode = 'edit'">
											<xsl:if test="comment =''">
												<a href='' style="vertical-align:5px;">
													<xsl:attribute name='href'>javascript:addCommentToAttach('<xsl:value-of select="$id"/>')</xsl:attribute>
													<img id="commentaddimg{$id}" src="/SharedResources/img/classic/icons/comment_add.png" style="width:16px; height:16px">
														<xsl:attribute name="title" select="//document/captions/add_comment/@caption"/>
													</img>
												</a>
											</xsl:if>
											<a href='' style="vertical-align:5px; margin-left:5px">
												<xsl:attribute name='href'>javascript:deleterow('<xsl:value-of select="$formsesid"/>','<xsl:value-of select='$filename' />','<xsl:value-of select="$id"/>')</xsl:attribute>
												<img src="/SharedResources/img/iconset/cross.png" style="width:13px; height:13px">
													<xsl:attribute name="title" select="//document/captions/delete_file/@caption"/>
												</img>
											</a>
										</xsl:if>
									</xsl:otherwise>
								</xsl:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td/>
						<td colspan="2" style="color:#777; font-size:12px">
							<xsl:if test="comment !=''">
								<xsl:value-of select="concat(//document/captions/comments/@caption,':',comment)"/>
								<br/><br/>
							</xsl:if>
						</td>
					</tr>
				</xsl:for-each>
			</table>
			<br/>
			<br/>
		</div>
	</xsl:template>
	
	<xsl:template name="documentitle-reports">
		<xsl:param name="title"/>
		<font style="font-size:18px">
			<xsl:value-of select="$title"/>
		</font>
	</xsl:template>
</xsl:stylesheet>