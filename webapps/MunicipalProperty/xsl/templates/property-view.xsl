<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="entry" mode="view-table-body">
        <div class="entry-wrap">
            <div data-id="{@id}" class="entry">
                <label class="entry-select">
                    <input type="checkbox" name="docid" value="{@id}"/>
                </label>
                <a href="{@url}" class="entry-link">
                    <div class="entry-fields">
                        <span class="vw-inv-number">
                            <xsl:value-of select="viewcontent/invnumber"/>
                        </span>
                        <span class="vw-object-name">
                            <xsl:value-of select="viewcontent/objectname"/>
                        </span>
                        <span class="vw-original-cost">
                            <xsl:value-of select="viewcontent/originalcost"/>
                        </span>
                        <span class="vw-balance-holder">
                            <xsl:value-of select="viewcontent/balanceholder"/>
                        </span>
                    </div>
                </a>
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>
