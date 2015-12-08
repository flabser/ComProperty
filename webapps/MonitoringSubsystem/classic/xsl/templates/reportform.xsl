<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="balanceholderprops">
		<!-- поле "Балансодержатель" -->
		<tr>
			<td class="fc" style="padding-top:5px">
				<font style="vertical-align:top">
					<xsl:value-of select="document/captions/balanceholder/@caption" />
					:
				</font>
				<xsl:if test="$editmode = 'edit'">
					<a>
						<xsl:attribute name="href">javascript:dialogBoxStructure('balanceholder','true','balanceholder','frm', 'balanceholdertbl');</xsl:attribute>
						<img src="/SharedResources/img/iconset/report_magnify.png" />
					</a>
				</xsl:if>
			</td>
			<td style="padding-top:5px">
				<table id="balanceholdertbl" style="border-spacing:0px 3px; margin-top:-3px">
					<tr>
						<td style="width:600px;" class="td_editable">
							<xsl:if test="$editmode != 'edit'">
								<xsl:attribute name="class">td_noteditable</xsl:attribute>
							</xsl:if>
							<xsl:value-of select="document/fields/balanceholdername" />
							&#xA0;
							<span
								style='float:right; border-left:1px solid #ccc; width:17px; padding-right:10px; padding-left:2px; padding-top:1px; color:#ccc; font-size:10.5px'>
								<font>
									<xsl:value-of select="document/fields/corr/@attrval" />
								</font>
							</span>
						</td>
					</tr>
				</table>
				<input type="hidden" value="{document/fields/balanceholder}"
					id="balanceholder" name="balanceholder" />
				<input type="hidden" value="{document/captions/balanceholder/@caption}"
					id="balanceholdercaption" />
			</td>
		</tr>
		<!-- Дата принятия на баланс -->
		<!--  <tr>
			<td class="fc" style="padding:5px;position:relative;top:0px">
				<xsl:value-of select="document/captions/acceptancedate/@caption" />
				:
			</td>
			<td>
				&#xA0;
				<label for="from" style="vertical-align:5px;">
					<xsl:value-of select="document/captions/from/@caption" />
				</label>
				&#xA0;
				<input type="text" id="acceptancedatefrom" size="7"
					name="acceptancedatefrom" value=""
					style="background:#fff; padding:3px 3px 3px 5px; width:80px; border:1px solid #ccc; vertical-align:top;">
				</input>
				&#xA0;
				<label for="to" style="vertical-align:5px;">
					<xsl:value-of select="document/captions/to/@caption" />
				</label>
				&#xA0;
				<input type="text" id="acceptancedateto" value="{document/fields/acceptancedateto}"
					size="7" name="acceptancedateto"
					style="background:#fff; padding:3px 3px 3px 5px; width:80px; border:1px solid #ccc; vertical-align:top">
				</input>
			</td>
		</tr>-->
	</xsl:template>

	<xsl:template name="propertycodeprops">
		<!-- Код права на имущество -->
		<!-- <tr>
			<td class="fc" style="padding-top:5px">
				<xsl:value-of select="document/captions/propertycode/@caption" />
				:
			</td>
			<td style="padding-top:5px">
				<xsl:variable name="propertycode"
					select="document/fields/propertycode/@attrval" />
				<select size="10" multiple="multiple" name="propertycode"
					style="width:611px;" class="select_editable">
					<xsl:if test="$editmode != 'edit'">
						<xsl:attribute name="class">select_noteditable</xsl:attribute>
						<xsl:attribute name="disabled">disabled</xsl:attribute>
						<option value="">
							<xsl:attribute name="selected">selected</xsl:attribute>
							<xsl:value-of select="document/fields/propertycode" />
						</option>
					</xsl:if>
					<xsl:for-each select="document/glossaries/propertycode/query/entry">
						<option value="{@docid}">
							<xsl:if test="$propertycode = @docid">
								<xsl:attribute name="selected">selected</xsl:attribute>
							</xsl:if>
							<xsl:if
								test="viewcontent/viewtext2 = '005' or viewcontent/viewtext2 = '006' or viewcontent/viewtext2 = '007'">
								<xsl:value-of
									select="../entry[viewcontent/viewtext2 = '004']/viewcontent/viewtext1" />
								-
							</xsl:if>
							<xsl:value-of select="viewcontent/viewtext1" />
						</option>
					</xsl:for-each>
				</select>
			</td>
		</tr> -->
	</xsl:template>
	<xsl:template name="addressprops">
		<!-- 
		<tr>
			<td class="fc" style="padding-top:5px">
				<xsl:value-of select="document/captions/region/@caption" />
				:
			</td>
			<td style="padding-top:5px">
				<xsl:variable name="region" select="document/fields/region/@attrval" />
				<select size="1" name="region" style="width:611px;" class="select_editable">
					<option value="">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</option>
					<xsl:for-each select="document/glossaries/region/query/entry">
						<option value="{@docid}">
							<xsl:if test="$region = @docid">
								<xsl:attribute name="selected">selected</xsl:attribute>
							</xsl:if>
							<xsl:value-of select="viewcontent/viewtext1" />
						</option>
					</xsl:for-each>
				</select>
			</td>
		</tr>

		<tr>
			<td class="fc" style="padding-top:5px">
				<xsl:value-of select="document/captions/city/@caption" />
				:
			</td>
			<td style="padding-top:5px">
				<xsl:variable name="city" select="document/fields/city/@attrval" />
				<select size="1" name="city" style="width:611px;" class="select_editable">
					<option value="">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</option>
					<xsl:for-each select="document/glossaries/city/query/entry">
						<option value="{@docid}">
							<xsl:if test="$city = @docid">
								<xsl:attribute name="selected">selected</xsl:attribute>
							</xsl:if>
							<xsl:value-of select="viewcontent/viewtext1" />
						</option>
					</xsl:for-each>
				</select>
				<xsl:if test="$editmode !='edit'">
					<input type="hidden" name="city" value="{document/fields/city/@attrval}" />
				</xsl:if>
			</td>
		</tr>
		
		<tr>
			<td class="fc" style="padding-top:5px">
				<xsl:value-of select="document/captions/district/@caption" />
				:
			</td>
			<td style="padding-top:5px">
				<xsl:variable name="district" select="document/fields/district/@attrval" />
				<select size="1" name="district" style="width:611px;" class="select_editable">
					<option value="">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</option>
					<xsl:for-each select="document/glossaries/district/query/entry">
						<option value="{@docid}">
							<xsl:if test="$district = @docid">
								<xsl:attribute name="selected">selected</xsl:attribute>
							</xsl:if>
							<xsl:value-of select="viewcontent/viewtext1" />
						</option>
					</xsl:for-each>
				</select>
				<xsl:if test="$editmode !='edit'">
					<input type="hidden" name="district"
						value="{document/fields/district/@attrval}" />
				</xsl:if>
			</td>
		</tr>
	
		<tr>
			<td class="fc" style="padding-top:5px">
				<xsl:value-of select="document/captions/street/@caption" />
				:
			</td>
			<td style="padding-top:5px">
				<xsl:variable name="street" select="document/fields/street/@attrval" />
				<select size="1" name="street" style="width:611px;" class="select_editable">
					<option value="">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</option>
					<xsl:for-each select="document/glossaries/street/query/entry">
						<option value="{@docid}">
							<xsl:if test="$street = @docid">
								<xsl:attribute name="selected">selected</xsl:attribute>
							</xsl:if>
							<xsl:value-of select="viewcontent/viewtext1" />
						</option>
					</xsl:for-each>
				</select>
				<xsl:if test="$editmode !='edit'">
					<input type="hidden" name="street" value="{document/fields/street/@attrval}" />
				</xsl:if>
			</td>
		</tr>
		
		<tr>
			<td class="fc" style="padding-top:5px">
				<xsl:value-of select="document/captions/home/@caption" />
				:
			</td>
			<td style="padding-top:5px">
				<input type="text" style="width:500px; padding:2px;" name="home"
					value="{document/fields/home}" class="td_editable" autocomplete="off">
					<xsl:if test="$editmode != 'edit'">
						<xsl:attribute name="class">td_noteditable</xsl:attribute>
						<xsl:attribute name="disabled">disabled</xsl:attribute>
					</xsl:if>
				</input>
			</td>
		</tr> -->
	</xsl:template>
	<xsl:template name="reporttypeprops">
		<tr>
			<td class="fc" style="padding:7px;">
				<xsl:value-of select="document/captions/reportfiletype/@caption" />
				:
			</td>
			<td>
				<table>
					<tr>
					<td>
							<input type="radio" name="typefilereport" value="1">
								<xsl:attribute name="onclick">javascript: reportsTypeCheck(this)</xsl:attribute>
								<xsl:if test="document/@editmode !='edit'">
									<xsl:attribute name="disabled">disabled</xsl:attribute>
								</xsl:if>
								<xsl:if test="document/fields/typefilereport  = '1'">
									<xsl:attribute name="checked">checked</xsl:attribute>
								</xsl:if>
								<xsl:if test="document/@status  = 'new'">
									<xsl:attribute name="checked">checked</xsl:attribute>
								</xsl:if>
								PDF
							</input>
						</td>
						<td>
							<input type="radio" name="typefilereport" value="2">
								<xsl:attribute name="onclick">javascript: reportsTypeCheck(this)</xsl:attribute>
								<xsl:if test="document/@editmode !='edit'">
									<xsl:attribute name="disabled">disabled</xsl:attribute>
								</xsl:if>
								<xsl:if test="document/fields/typefilereport  = '2'">
									<xsl:attribute name="checked">checked</xsl:attribute>
								</xsl:if>								
								XLSX
							</input>
						</td>
						
						
					</tr>
				</table>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>