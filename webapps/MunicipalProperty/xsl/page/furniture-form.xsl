<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:import href="../layout.xsl" />
	<xsl:import href="../templates/sharedfields.xsl" />

	<xsl:variable name="editmode" select="/request/document/@editmode" />
	<xsl:variable name="status" select="document/@status" />

	<xsl:template match="/request">
		<xsl:call-template name="layout" />
	</xsl:template>

	<xsl:template name="_content">
		<form class="form form-{$editmode}" action="Provider" method="post"
			enctype="application/x-www-form-urlencoded">
			<header class="content-header">
				<h1 class="header-title">
					<xsl:value-of select="//captions/furniture/@caption" />
				</h1>
				<div class="content-actions">
					<xsl:apply-templates select="//actionbar" />
				</div>
			</header>
			<section class="content-body">
				<ul class="nav nav-tabs" role="tablist">
					<li class="active">
						<a href="#tabs-1" role="tab" data-toggle="tab">
							<xsl:value-of select="//captions/properties/@caption" />
						</a>
					</li>
					<!-- <li> <a href="#tabs-2" role="tab" data-toggle="tab"> <xsl:value-of 
						select="//captions/documents_of_title/@caption" /> </a> </li> -->
					<li>
						<a href="#tabs-3" role="tab" data-toggle="tab">
							<xsl:value-of select="//captions/notes/@caption" />
						</a>
					</li>
					<!-- <li> <a href="#tabs-4" role="tab" data-toggle="tab"> <xsl:value-of 
						select="//captions/files/@caption" /> </a> </li> -->
					<li>
						<a href="#tabs-5" role="tab" data-toggle="tab">
							<xsl:value-of select="//captions/additional/@caption" />
						</a>
					</li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tabs-1">
						<fieldset class="fieldset">
							<xsl:if test="//document/@editable = 'false'">
								<xsl:attribute name="disabled" select="'disabled'" />
							</xsl:if>
						</fieldset>
						referrer=<xsl:value-of select="@referrer" />
						<xsl:call-template name="personal-estate-field-set" />
					</div>
					<div role="tabpanel" class="tab-pane" id="tabs-2">
						<fieldset class="fieldset">
							<xsl:if test="//document/@editable = 'false'">
								<xsl:attribute name="disabled" select="'disabled'" />
							</xsl:if>
							<xsl:call-template name="documents-of-title" />
						</fieldset>
					</div>
					<div role="tabpanel" class="tab-pane" id="tabs-3">
						<fieldset class="fieldset">
							<xsl:if test="//document/@editable = 'false'">
								<xsl:attribute name="disabled" select="'disabled'" />
							</xsl:if>
							<xsl:call-template name="notes" />
						</fieldset>
					</div>
					<div role="tabpanel" class="tab-pane" id="tabs-4">
						
					</div>
					<div role="tabpanel" class="tab-pane" id="tabs-5">
						<xsl:call-template name="docinfo" />
					</div>
				</div>
			</section>			
			<input type="hidden" name="id" value="{@id}" />
			<input type="hidden" name="docid" value="{//document/@docid}" />		
		</form>
	</xsl:template>


</xsl:stylesheet>
