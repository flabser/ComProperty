<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- кнопка показать xml документ  -->
	<xsl:template name="showxml">
		<xsl:if test="@debug=1">
			<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
				<xsl:attribute name="onclick">javascript:window.location = window.location + '&amp;onlyxml=1'</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_code.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top">XML</font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>
	
	<!-- кнопка сохранения  -->
	<xsl:template name="save">
		<xsl:if test="document/actionbar/action[@id='save_and_close']/@mode = 'ON'">
			<button title="{document/actionbar/action [@id='save_and_close']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnsavedoc">
				<xsl:attribute name="onclick">javascript:SaveFormJquery('<xsl:value-of select="/request/history/entry[@type = 'page'][last()]"/>')</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/disk.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actionbar/action [@id='save_and_close']/@caption"/></font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>
	
	<!-- добавить акт приема 
	<xsl:template name="add_acceptance_protocol">
		<xsl:if test="document/actionbar/action[@id='add_acceptance_protocol']/@mode = 'ON'">
			<button title="{document/actionbar/action [@id='add_acceptance_protocol']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=acceptance_protocol&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype" />"</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_white_get.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actionbar/action[@id='add_acceptance_protocol']/@caption"/></font>
				</span>
			</button>
		</xsl:if>
	</xsl:template> -->
	
	<!-- добавить акт приема-передачи  -->
	<xsl:template name="add_transfer_protocol">
		<xsl:if test="document/actionbar/action[@id='add_transfer_protocol']/@mode = 'ON'">
			<button title="{document/actionbar/action [@id='add_transfer_protocol']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=transfer_protocol&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype" />"</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_white_put.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actionbar/action[@id='add_transfer_protocol']/@caption"/></font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>
	
	
	<xsl:template name="add_leasing_contract">
		<xsl:if test="document/actionbar/action[@id='ADD_LEASING_CONTRACT']/@mode = 'ON'">
			<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=orderleasing&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid" />&amp;parentdoctype=<xsl:value-of select="document/@doctype" />"</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top">
						<xsl:value-of select="document/actionbar/action [@id='ADD_LEASING_CONTRACT']/@caption"/>
					</font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="add_rooms">
		<xsl:if test="document/actionbar/action[@id='ADD_ROOMS']/@mode = 'ON'">
			<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=rooms&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actionbar/action [@id='ADD_ROOMS']/@caption"/></font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="submit_application_for_rent">
		<xsl:if test="document/actionbar/action[@id='SUBMIT_APPLICATION_FOR_RENT']/@mode = 'ON'">
			<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=edit&amp;id=rentapplication&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>"</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actionbar/action [@id='SUBMIT_APPLICATION_FOR_RENT']/@caption"/></font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="add_leasing">
		<xsl:if test="document/actionbar/action[@id='ADD_LEASING']/@mode = 'ON'">
			<button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:addRent("<xsl:value-of select='document/@docid'/>")</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_white_add.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actionbar/action [@id='ADD_LEASING']/@caption"/></font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>
	
	<!-- 	кнопка сформировать отчет -->
	<xsl:template name="filling_report">
		<button title ="{document/actionbar/action[@id='save_and_close']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px"  id="generatereport">
			<xsl:attribute name="onclick">javascript:fillingReport()</xsl:attribute>
			<span>
				<img src="/SharedResources/img/classic/icons/disk.png" class="button_img"/>
				<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/captions/fillingreport/@caption"/></font>
			</span>
		</button>
	</xsl:template>
	
	<xsl:template name="newdiscussion">
		<xsl:if test="document/actions/action [.='COMPOSE_DISCUSSION']/@enable = 'true'">
			<button  title ="{document/actionbar/action[@id = 'COMPOSE_DISCUSSION']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:window.location.href="Provider?type=document&amp;id=discussion&amp;key=&amp;parentdocid=<xsl:value-of select="document/@docid"/>&amp;parentdoctype=<xsl:value-of select="document/@doctype"/>&amp;page=null"</xsl:attribute>
				<span>
					<img src="/SharedResources/img/comment/icons/comments_add.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top">Создать обсуждение</font>
				</span>
			</button>
		</xsl:if>
	</xsl:template>

	<!--кнопка закрыть-->
	<xsl:template name="cancel">
		<button title= "{document/captions/close/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="canceldoc">
			<xsl:attribute name="onclick">javascript:<xsl:value-of select="document/actionbar/action[@id = 'close']/js"/></xsl:attribute>
			<span>
				<img src="/SharedResources/img/classic/icons/cross.png" class="button_img"/>
				<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/captions/close/@caption"/></font>
			</span>
		</button>
	</xsl:template>

	<!--кнопка ознакомить-->
	<xsl:template name="acquaint">
		<xsl:if test="document/actions/action [.='GRANT_ACCESS']/@enable = 'true'">
			<button title ="{document/actions/action [.='GRANT_ACCESS']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btngrantaccess" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:acquaint(<xsl:value-of select="document/@docid"/>,<xsl:value-of select="document/@doctype"/>)</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/page_white_get.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actions/action [.='GRANT_ACCESS']/@caption"/></font>
				</span>
			</button>
			<script>
				acquaintcaption = '<xsl:value-of select="document/actions/action [.='GRANT_ACCESS']/@caption"/>';
			</script>
		</xsl:if>
	</xsl:template>

	<!--кнопка напомнить-->
	<xsl:template name="remind">
		<xsl:if test="document/actions/action [.='NOTIFY_EXECUTERS']/@enable = 'true'">
			<button title= "{document/actions/action [.='NOTIFY_EXECUTERS']/@hint}" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" id="btnremind" style="margin-left:5px">
				<xsl:attribute name="onclick">javascript:remind(<xsl:value-of select="document/@docid"/>,<xsl:value-of select="document/@doctype"/>)</xsl:attribute>
				<span>
					<img src="/SharedResources/img/classic/icons/clock_red.png" class="button_img"/>
					<font style="font-size:12px; vertical-align:top"><xsl:value-of select="document/actions/action [.='NOTIFY_EXECUTERS']/@caption"/></font>
				</span>
			</button>
			<script>
				remindcaption = '<xsl:value-of select="document/actions/action [.='NOTIFY_EXECUTERS']/@caption"/>';
			</script>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="ECPsign">
	<!--  	<xsl:if test="document/@sign != '1'">
			<button>
				<xsl:attribute name="onclick">edsApp.sign('<xsl:value-of select="@id"/>', this); return false;</xsl:attribute>
				<img src="/SharedResources/img/iconset/page_edit.png" class="button_img"/>
				<font style="font-size:12px; vertical-align:top">Добавить ЭЦП</font>
			</button>
		</xsl:if>-->
	</xsl:template>
</xsl:stylesheet>