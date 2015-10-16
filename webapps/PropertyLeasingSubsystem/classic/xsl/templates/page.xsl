<?xml version="1.0" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../templates/view.xsl"/>
	<xsl:variable name="viewtype">Вид</xsl:variable>
	<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
				doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="no"/>
	<xsl:variable name="skin" select="request/@skin"/>
	<xsl:variable name="useragent" select="request/@useragent"/>

	<xsl:template match="//query/entry">
		<xsl:variable name="num" select="position()"/>
		<tr title="{@viewtext}" class="{@docid}" id="{@docid}{@doctype}">
			<xsl:attribute name="bgcolor">#FFFFFF</xsl:attribute>
			<xsl:call-template name="viewtable_dblclick_open"/>
			<td style="text-align:center;border:1px solid #ccc;width:23px;">
				<input type="checkbox" name="chbox" id="{@id}" autocomplete="off" value="{@doctype}"/>
			</td>
			<td style="width:25px; text-align:center; border:1px solid #ccc;">
				<xsl:if test="@hasattach != '0'">
					<img id="atach" src="/SharedResources/img/classic/icons/attach.png" title="Вложений в документе: {@hasattach}"/>
				</xsl:if>
			</td>
			<td  style="border:1px solid #ccc;width:210px;">
				<div style="overflow:hidden; width:99%;">
					<xsl:if test="@hasresponse='1'">
						<xsl:choose>
							<xsl:when test=".[responses]">
								<a href="" style="vertical-align:top; margin-left:3px" id="a{@docid}">
									<xsl:attribute name='href'>javascript:closeResponses(<xsl:value-of select="@docid"/>, <xsl:value-of select="@doctype"/>,<xsl:value-of select="position()"/>,1)</xsl:attribute>
									<img border='0' src="/SharedResources/img/classic/1/minus.png" id="img{@docid}"/>
								</a>
							</xsl:when>
							<xsl:otherwise>
								<a href="" style="vertical-align:top; margin-left:3px" id="a{@docid}">
									<xsl:attribute name='href'>javascript:openParentDocView(<xsl:value-of select="@docid"/>, <xsl:value-of select="@doctype"/>,<xsl:value-of select="position()"/>,1)</xsl:attribute>
									<img border='0' src="/SharedResources/img/classic/1/plus.png" id="img{@docid}"/>
								</a>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:if>
					<xsl:if test="@hasresponse='0'">
						<span style="width:11px; display:inline-block"></span>
					</xsl:if>
					<a class="doclink" style="padding-left:5px;" href="{@url}" title="{@viewtext}">
						<xsl:attribute name="onclick">javascript:beforeOpenDocument()</xsl:attribute>
						<xsl:if test="@isread = '0'">
							<xsl:attribute name="style">font-weight:bold;padding-left:5px</xsl:attribute>
						</xsl:if>
						<xsl:value-of select="viewcontent/viewtext1"/>
					</a>
				</div>
			</td>
			<td  style="border:1px solid #ccc;width:210px;">
				<div style="overflow:hidden; width:99%;">
					<a class="doclink" style="padding-left:5px;" href="{@url}" title="{@viewtext}">
						<xsl:attribute name="onclick">javascript:beforeOpenDocument()</xsl:attribute>
						<xsl:if test="@isread = '0'">
							<xsl:attribute name="style">font-weight:bold;padding-left:5px</xsl:attribute>
						</xsl:if>
						<xsl:value-of select="viewcontent/viewtext3"/>
					</a>
				</div>
			</td>

			<!-- Описание документа -->
			<td  style="border:1px solid #ccc;min-width:250px; word-wrap:break-word; padding-left: 5px">
				<div style="display:block; width:99%; " title="{viewcontent/viewtext}">
					<a href="{@url}" class="doclink" style="width:90%">
						<xsl:attribute name="onclick">javascript:beforeOpenDocument()</xsl:attribute>
						<xsl:if test="@isread = '0'">
							<xsl:attribute name="style">font-weight:bold</xsl:attribute>
						</xsl:if>
						<xsl:value-of select="viewcontent/viewtext"/>
					</a>
				</div>
			</td>
			<td style="border:1px solid #ccc; border-left:none; width:30px; text-align:center">
				<img class="favicon" style="cursor:pointer; width:18px; height:18px" src="/SharedResources/img/iconset/star_empty.png">
					<xsl:attribute name="onclick">javascript:addDocToFav(this,<xsl:value-of select="@docid"/>,<xsl:value-of select="@doctype"/>)</xsl:attribute>
					<xsl:attribute name="title" select="//page/captions/addtofav/@caption"/>
					<xsl:if test="@favourites = 1">
						<xsl:attribute name="onclick">javascript:removeDocFromFav(this,<xsl:value-of select="@docid"/>,<xsl:value-of select="@doctype"/>)</xsl:attribute>
						<xsl:attribute name="src">/SharedResources/img/iconset/star_full.png</xsl:attribute>
						<xsl:attribute name="title" select="//page/captions/removefromfav/@caption"/>
					</xsl:if>
				</img>
			</td>
		</tr>
		<xsl:apply-templates select="responses"/>
	</xsl:template>

	<xsl:template match="responses">
		<tr class="response{../@docid}">
			<xsl:attribute name="bgcolor">#FFFFFF</xsl:attribute>
			<td style="width:5%"/>
			<td style="width:5%"/>
			<td colspan="4" nowrap="true">
				<xsl:apply-templates mode="line"/>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="viewtext" mode="line"/>
	<xsl:template match="viewcontent" mode="line"/>

	<xsl:template match="entry" mode="line">
		<div class="Node" style="overflow:hidden;" id="{@docid}{@doctype}">
			<xsl:call-template name="graft"/>
			<xsl:apply-templates select="." mode="item"/>
		</div>
		<xsl:apply-templates mode="line"/>
	</xsl:template>

	<xsl:template match="entry" mode="item">
		<a  href="{@url}" title="{@viewtext}" class="doclink" style="font-style:arial; width:100%; font-size:99%">
			<xsl:attribute name="onclick">javascript:beforeOpenDocument()</xsl:attribute>
			<xsl:if test="@isread = 0">
				<xsl:attribute name="style">font-weight:bold</xsl:attribute>
			</xsl:if>
			<xsl:if test="@hasattach != 0">
				<img id="atach" src="/SharedResources/img/classic/icons/attach.png" border="0" style="vertical-align:top">
					<xsl:attribute name="title">Вложений в документе: <xsl:value-of select="@hasattach"/></xsl:attribute>
				</img>
			</xsl:if>
			<xsl:variable name='simbol'>'</xsl:variable>
			<xsl:variable name='ecr1' select="replace(viewcontent/viewtext,$simbol ,'&quot;')"/>
			<xsl:variable name='ecr2' select="replace($ecr1, '&#34;' ,'&quot;')"/>
			<font id="font{@docid}{@doctype}">
				<script>
					text='<xsl:value-of select="$ecr2"/>';
					symcount= <xsl:value-of select="string-length(viewcontent/viewtext)"/>;
					ids="font<xsl:value-of select="@docid"/><xsl:value-of select="@doctype"/>";
					replaceVal="<img/>";
					text=text.replace("-->",replaceVal);
					$("#"+ids).html(text);
					$("#"+ids+" > img").attr("src","/SharedResources/img/classic/arrow_blue.gif");
					$("#"+ids+" > img").attr("style","vertical-align:middle");
				</script>
			</font>
		</a>
	</xsl:template>

	<xsl:template name="graft">
		<xsl:apply-templates select="ancestor::entry" mode="tree"/>
		<xsl:choose>
			<xsl:when test="following-sibling::entry">
				<img style="vertical-align:top;" src="/SharedResources/img/classic/tree_tee.gif"/>
			</xsl:when>
			<xsl:otherwise>
				<img style="vertical-align:top;" src="/SharedResources/img/classic/tree_corner.gif"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="responses" mode="tree"/>

	<xsl:template match="*" mode="tree">
		<xsl:choose>
			<xsl:when test="following-sibling::entry and entry[@url]">
				<img style="vertical-align:top;" src="/SharedResources/img/classic/tree_bar.gif"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:if test="parent::responses or parent::entry">
					<img style="vertical-align:top;" src="/SharedResources/img/classic/tree_spacer.gif"/>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="/request">
		<html>
			<head>
				<title>АИС 'Коммунальное Имущество' <xsl:call-template name="pagetitle"/></title>
				<link type="text/css" rel="stylesheet" href="classic/css/outline.css"/>
				<link type="text/css" rel="stylesheet" href="classic/css/main.css"/>
				<link type="text/css" rel="stylesheet" href="/SharedResources/jquery/css/smoothness/jquery-ui-1.8.20.custom.css"/>
				<link type="text/css" rel="stylesheet" href="/SharedResources/jquery/js/hotnav/jquery.hotnav.css"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/jquery-1.4.2.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.widget.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.core.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.effects.core.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/jquery.ui.datepicker.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/jquery.ui.datepicker-ru.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.mouse.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.draggable.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.position.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.button.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/ui/minified/jquery.ui.dialog.min.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/cookie/jquery.cookie.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/hotnav/jquery.hotkeys.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/hotnav/jquery.hotnav.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/scrollTo/scrollTo.js"/>
				<script type="text/javascript" src="/SharedResources/jquery/js/tiptip/jquery.tipTip.js"/>
				<script type="text/javascript" src="classic/scripts/outline.js"/>
				<script type="text/javascript" src="classic/scripts/view.js"/>
				<script type="text/javascript" src="classic/scripts/page.js"/>
				<script type="text/javascript">
					$(document).ready(function(){
					$(".button_panel button").button();
					hotkeysnav()
					outline.type = '<xsl:value-of select="@type"/>';
					outline.viewid = '<xsl:value-of select="@id"/>';
					outline.element = 'project';
					outline.command='<xsl:value-of select="current/@command"/>';
					outline.curPage = '<xsl:value-of select="current/@page"/>';
					outline.category = '';
					outline.filterid = '<xsl:value-of select="@id"/>';
					refresher();
					});
					function hotkeysnav(){
					$(document).bind('keydown', function(e){
					if (e.ctrlKey) {
					switch (e.keyCode) {
					case 78:
					<!-- клавиша n -->
					e.preventDefault();
					$("#btnNewdoc").click();
					break;
					case 68:
					<!-- клавиша d -->
					e.preventDefault();
					$("#btnDeldoc").click();
					break;
					case 70:
					<!-- клавиша f -->
					e.preventDefault();
					$("#btnQFilter").click();
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
					$("#btnNewdoc .ui-button-text").hotnav({keysource:function(e){ return "n"; }});
					$("#btnDeldoc .ui-button-text").hotnav({keysource:function(e){ return "d"; }});
					$("#currentuser").hotnav({ keysource:function(e){ return "u"; }});
					$("#btnQFilter .ui-button-text").hotnav({keysource:function(e){ return "f"; }});
					$("#logout").hotnav({keysource:function(e){ return "q"; }});
					$("#helpbtn").hotnav({keysource:function(e){ return "h"; }});
					}
				</script>
			</head>
			<body>
				<xsl:call-template name="flashentry"/>
				<div id="blockWindow" style="display:none"/>
				<div id="wrapper">
					<xsl:call-template name="loadingpage"/>
					<xsl:call-template name="header-page"/>
					<xsl:call-template name="outline-menu-page"/>
					<span id="view" class="viewframe">
						<div id="viewcontent">
							<div id="viewcontent-header" style="height:130px;">
								<xsl:call-template name="pageinfo"/>
								<div class="button_panel" style="margin-top:8px">
									<div style="float:left; padding-left:15px; margin-top:2px; margin-bottom:3px">
										<xsl:if test="//action[@id='new_document']/@mode = 'ON'">
											<button style="margin-right:5px" title="{//action[@id='new_document']/@hint}" id="btnNewdoc">
												<xsl:attribute name="href">javascript:window.location.href="<xsl:value-of select="//action[@id='new_document']/@url"/>"; beforeOpenDocument()</xsl:attribute>
												<xsl:attribute name="onclick">javascript:window.location.href="<xsl:value-of select="//action[@id='new_document']/@url"/>"; beforeOpenDocument()</xsl:attribute>
												<img src="/SharedResources/img/classic/icons/page_white.png" class="button_img"/>
												<font style="font-size:12px; vertical-align:top"><xsl:value-of select="//action[@id='new_document']/@caption"/></font>
											</button>
										</xsl:if>
										<xsl:if test="//action[@id='delete_document']/@mode = 'ON'">
											<button style="margin-right:5px" title="{//action[@id='delete_document']/@hint}" id="btnDeldoc">
												<xsl:attribute name="onclick">javascript:delDocument();</xsl:attribute>
												<img src="/SharedResources/img/classic/icons/page_white_delete.png" class="button_img"/>
												<font style="font-size:12px; vertical-align:top"><xsl:value-of select="//action[@id='delete_document']/@caption"/></font>
											</button>
										</xsl:if>
									</div>
									<span style="float:right; padding-right:10px;">
									</span>
								</div>
								<div style="clear:both"/>
								<div style="clear:both"/>
								<div id="tableheader">
									<table class="viewtable" id="viewtable" width="100%" style="">
										<tr class="th">
											<td style="text-align:center;height:30px;width:23px;" class="thcell">
												<input type="checkbox" id="allchbox" autocomplete="off" onClick="checkAll(this)"/>
											</td>
											<td style="text-align:center; height:30px; width:25px;" class="thcell"/>
											<td style="width:210px;" class="thcell">
												<xsl:call-template name="sortingcellpage">
													<xsl:with-param name="namefield">VIEWTEXT1</xsl:with-param>
													<xsl:with-param name="sortorder" select="//query/columns/viewtext1/sorting/@order"/>
													<xsl:with-param name="sortmode" select="//query/columns/viewtext1/sorting/@mode"/>
												</xsl:call-template>
											</td>
											<td style="width:210px;" class="thcell">
												<xsl:call-template name="sortingcellpage">
													<xsl:with-param name="namefield">VIEWTEXT3</xsl:with-param>
													<xsl:with-param name="sortorder" select="//query/columns/viewtext3/sorting/@order"/>
													<xsl:with-param name="sortmode" select="//query/columns/viewtext3/sorting/@mode"/>
												</xsl:call-template>
											</td>
											<td style="min-width:250px;" class="thcell">
												<xsl:call-template name="sortingcellpage">
													<xsl:with-param name="namefield">VIEWTEXT2</xsl:with-param>
													<xsl:with-param name="sortorder" select="//query/columns/viewtext2/sorting/@order"/>
													<xsl:with-param name="sortmode" select="//query/columns/viewtext2/sorting/@mode"/>
												</xsl:call-template>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div id="viewtablediv">
								<div id="tablecontent">
									<xsl:if test="query/filtered/condition[fieldname != ''][value != '0']">
										<xsl:attribute name="style">top:132px;</xsl:attribute>
									</xsl:if>
									<table class="viewtable" id="viewtable" width="100%">
										<xsl:choose>
											<xsl:when test="@id = 'report_tasks'">
												<tr onmouseover="javascript:elemBackground(this,'EEEEEE')" onmouseout="elemBackground(this,'FFFFFF')">
													<xsl:attribute name="bgcolor">#FFFFFF</xsl:attribute>
													<td style="text-align:center;border:1px solid #ccc;width:20px;">
														<input type="checkbox" autocomplete="off" name="chbox"/>
													</td>
													<td style="border:1px solid #ccc; padding-left:5px">
														<a href="Provider?type=edit&amp;id=task_report&amp;key=" class="doclink">Задания</a>
													</td>
												</tr>
											</xsl:when>
											<xsl:otherwise>
												<xsl:apply-templates select="//query/entry"/>
											</xsl:otherwise>
										</xsl:choose>
									</table>
									<div style="clear:both; width:100%">&#xA0;</div>
								</div>
							</div>
						</div>
					</span>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>