<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:import href="../layout.xsl"/>

	<xsl:template match="/request">
		<xsl:call-template name="layout"/>
	</xsl:template>

	<xsl:template name="_content">
		<xsl:variable name="editmode" select="//document/@editmode"/>
		
		 <script type="text/javascript">
      		function changeEventHandler(event) {
          			var xhrArgs = {
        				cache: false,
        				type: 'POST',
        				datatype: 'json',
        				url: 'Provider',
       					data: {
            				'id': 'change-lang-action',
           					'lang': event.target.value,
        				},
        
        				success: function(res) {
            				window.location.reload(false);          
        				},
        				error: function(err) {
        	
       					 },
        				complete: function() {
           
        				}
    			};
        	return nb.ajax(xhrArgs);        	

      }
    </script>
    
		<form name="{//page/@entity}">
			<header class="content-header">
				<h1 class="header-title">
					<xsl:value-of select="concat(//captions/employee/@caption, ' - ', //fields/name)"/>
				</h1>
				<div class="content-actions">
					<xsl:apply-templates select="//actionbar"/>
				</div>
			</header>
			<section class="content-body">
				<ul class="nav nav-tabs" role="tablist">
					<li class="active">
						<a href="#tabs-1" role="tab" data-toggle="tab">
							<xsl:value-of select="//captions/properties/@caption"/>
						</a>
					</li>
					<li>
						<a href="#tabs-3" role="tab" data-toggle="tab">
							<xsl:value-of select="//captions/interface/@caption"/>
						</a>
					</li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tabs-1">
						<fieldset class="fieldset">
			
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/user_name/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="fio" value="{//fields/name}"
												   class="form-control"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/login_name/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="login" value="{//fields/login}"
												   class="form-control"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/password/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="password" value="{//fields/password}"
												   class="form-control"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/reenterpassword/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="reenterpassword" value="{//fields/reenterpassword}"
												   class="form-control"/>
										</div>
									</div>
								</div>							
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/email/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="email" value="{//fields/email}"
												   class="form-control"/>
										</div>
									</div>
								</div>								
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/org_name/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="orgname" value="{//fields/organization}"
												   class="form-control"/>
										</div>
									</div>
								</div>			
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/department/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="department" value="{//fields/department}"
												   class="form-control"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/position/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<input type="text" name="post" value="{//fields/position}"
												   class="form-control"/>
										</div>
									</div>
								</div>								
								
								<div class="form-group">
									<div class="control-label">
										<xsl:value-of select="//captions/role/@caption"/>
									</div>
									<div class="controls">
										<div class="field-wrapper col-md-6">
											<table>
												<xsl:for-each select="//fields/role[not(entry)]">
													<xsl:variable name="role" select="."/>
													<xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
														<tr>
															<td style="width:500px;" class="td_noteditable">
																<xsl:if test="//document/@editmode != 'edit'">
																	<xsl:attribute name="class">
																		td_noteditable
																	</xsl:attribute>
																</xsl:if>
																<xsl:value-of select="."/>

																<xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
																	<input type="hidden" name="role" value="{.}"/>
																</xsl:if>
															</td>
														</tr>
													</xsl:if>
												</xsl:for-each>
												<xsl:for-each select="//fields/role/entry">
													<xsl:variable name="role" select="."/>
													<xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
														<tr>
															<td style="width:500px;" class="td_noteditable">
																<xsl:if test="//document/@editmode != 'edit'">
																	<xsl:attribute name="class">
																		td_noteditable
																	</xsl:attribute>
																</xsl:if>
																<xsl:value-of select="."/>

																<xsl:if test="//glossaries/roles/entry[ison='ON'][name = $role]">
																	<input type="hidden" name="role" value="{.}"/>
																</xsl:if>
															</td>
														</tr>
													</xsl:if>
												</xsl:for-each>
											</table>
										</div>
									</div>
								</div>								
						
						</fieldset>
					</div>
					<div role="tabpanel" class="tab-pane" id="tabs-3">
						<div class="form-group">
							<div class="control-label">
								<xsl:value-of select="//captions/countdocinview/@caption"/>
							</div>
							<div class="controls">
								<div class="field-wrapper col-md-1">
									<select name="pagesize" class="form-control">
										<option id="countdocinview10">
											<xsl:if test="//fields/countdocinview = '10'">
												<xsl:attribute name="selected">selected</xsl:attribute>
											</xsl:if>
											10
										</option>
										<option id="countdocinview20">
											<xsl:if test="//fields/countdocinview = '20'">
												<xsl:attribute name="selected">selected</xsl:attribute>
											</xsl:if>
											20
										</option>
										<option id="countdocinview30">
											<xsl:if test="//fields/countdocinview = '30'">
												<xsl:attribute name="selected">selected</xsl:attribute>
											</xsl:if>
											30
										</option>
										<option id="countdocinview50">
											<xsl:if test="//fields/countdocinview = '50'">
												<xsl:attribute name="selected">selected</xsl:attribute>
											</xsl:if>
											50
										</option>
									</select>
								</div>
							</div>
						</div>					
						<div class="form-group">
							<div class="control-label">
								<xsl:value-of select="//captions/interface_lang/@caption"/>
							</div>
							<div class="controls">
								<div class="field-wrapper col-md-1">
									<select name="lang" class="form-control"  onchange="changeEventHandler(event);">
										<xsl:variable name='currentlang' select="../@lang"/>
										<xsl:for-each select="//availablelangs/entry">
											<option id="{id}" value="{id}">
												 <xsl:if test=". = $currentlang">
													<xsl:attribute name="selected">selected
													</xsl:attribute>
													
												</xsl:if>	
													<xsl:attribute name="value"><xsl:value-of select="."/></xsl:attribute> 								
												<xsl:value-of select="."/>										
											</option>
										</xsl:for-each>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

			<input type="hidden" name="id" value="userprofile"/>
		</form>
	
	</xsl:template>

</xsl:stylesheet>