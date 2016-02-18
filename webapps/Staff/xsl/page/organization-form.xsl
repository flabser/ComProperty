<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../layout.xsl" />

	<xsl:template match="/request">
		<xsl:call-template name="layout" />
	</xsl:template>

	<xsl:template name="_content">
		<form name="{//document/@entity}">
			<header class="content-header">
				<h1 class="header-title">
					<xsl:value-of select="//captions/organization/@caption" />
				</h1>
				<div class="content-actions">
					<xsl:apply-templates select="//actionbar" />
				</div>
			</header>
			<section class="content-body">
				<fieldset class="fieldset">
					<div class="form-group">
						<div class="control-label">
							<xsl:value-of select="//captions/name/@caption" />
						</div>
						<div class="controls">
							<input type="text" name="name" value="{//fields/name}"
								class="span7" autofocus="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="control-label">
							<xsl:value-of select="//captions/org_category/@caption" />
						</div>
						<div class="controls">
							<select name="orgcategory" class="span4">
								<option value="null"></option>
								<xsl:apply-templates select="//query[@entity = 'orgcategory']/entry"
									mode="select_options">
									<xsl:with-param name="select" select="//fields/orgcategory" />
								</xsl:apply-templates>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="control-label"></div>
						<div class="controls">
							<label class="btn btn-sm">
								<input type="checkbox" name="is_primary" value="true">
									<xsl:if test="//fields/isprimary  = 'true'">
										<xsl:attribute name="checked">checked</xsl:attribute>
									</xsl:if>
									<span>
										<xsl:value-of select="//captions/is_primary_organization/@caption" />
									</span>
								</input>
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="control-label">
							<xsl:value-of select="//captions/bin/@caption" />
						</div>
						<div class="controls">
							<input type="text" name="bin" value="{//fields/bin}" class="span4" />
						</div>
					</div>
					<div class="form-group">
											<div class="control-label">
							<xsl:value-of select="//captions/organization_labels/@caption" />
						</div>
						 <div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label>
								<xsl:apply-templates
									select="//query[@entity = 'organizationlabel']/entry" mode="labels">
									<xsl:with-param name="select" select="//fields/labels" />
								</xsl:apply-templates>
							</label>
						</div>
					</div>
					</div>
				</fieldset>

				<input type="hidden" name="id" value="{/request/@id}" />
				<input type="hidden" name="docid" value="{//document/@docid}" />
			</section>
		</form>
	</xsl:template>


	<xsl:template match="entry" mode="labels">
		<xsl:param name="select" />
		
		<input type="checkbox" name="labels">
			<xsl:if test="contains($select,viewcontent/name)">
				<xsl:attribute name="checked" select="checked" />
			</xsl:if>
			<xsl:attribute name="value" select="@id" />
			<span>
				<xsl:value-of select="viewcontent/name" />
			</span>
		</input>
		<br />
	</xsl:template>

	<xsl:template match="entry" mode="select_options">
		<xsl:param name="select" />
		<option value="viewcontent/name">
			<xsl:if test="@id = $select">
				<xsl:attribute name="selected" select="'selected'" />
			</xsl:if>
			<xsl:attribute name="value" select="@id" />
			<xsl:value-of select="." />
		</option>
	</xsl:template>

</xsl:stylesheet>
