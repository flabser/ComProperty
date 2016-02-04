<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="kof_kuf">
        <!-- KOF & KUF -->
        <div class="form-group">
            <div class="control-label">
                <xsl:value-of select="'КОФ/КУФ'"/>
            </div>
            <div class="controls">
                <div class="field-wrapper col-md-2">
                    <input type="text" name="kof" value="{//fields/kof}" class="form-control"/>
                </div>
                <div style="float:left;padding:4px">
                    <xsl:value-of select="'/'"/>
                </div>
                <div class="field-wrapper col-md-1">
                    <input type="text" name="kuf" value="{//fields/kuf}" class="form-control"/>
                </div>
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>
