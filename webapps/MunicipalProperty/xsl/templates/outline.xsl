<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="app_menu" mode="outline">
        <xsl:param name="active-id" select="//app_menu//current/@id"/>

        <aside class="aside side-nav" id="side-nav">
            <xsl:apply-templates select="response/content/outline" mode="outline">
                <xsl:with-param name="active-id" select="$active-id"/>
            </xsl:apply-templates>
        </aside>
    </xsl:template>

    <xsl:template match="outline" mode="outline">
        <xsl:param name="active-id" select="''"/>

        <section>
            <xsl:if test="@caption != ''">
                <xsl:attribute name="class" select="'collapsible'"/>
                <xsl:attribute name="id" select="concat('side-nav-', @id)"/>
                <header data-role="toggle">
                    <xsl:value-of select="@caption"/>
                </header>
            </xsl:if>
            <ul>
                <xsl:apply-templates mode="outline">
                    <xsl:with-param name="active-id" select="$active-id"/>
                </xsl:apply-templates>
            </ul>
        </section>
    </xsl:template>

    <xsl:template match="entry" mode="outline">
        <xsl:param name="active-id" select="''"/>

        <li>
            <a href="{@url}" title="{@hint}" class="nav-link">
                <xsl:if test="$active-id != '' and @id = $active-id">
                    <xsl:attribute name="class" select="'nav-link active'"/>
                </xsl:if>
                <xsl:choose>
                    <xsl:when test="@id = 'users'">
                        <i class="fa fa-users"></i>
                    </xsl:when>
                    <xsl:otherwise>
                        <i class="fa fa-file-o"></i>
                    </xsl:otherwise>
                </xsl:choose>
                <span>
                    <xsl:value-of select="@caption"/>
                </span>
            </a>
            <xsl:if test="./entry">
                <ul id="side-nav-{@id}{position()}">
                    <xsl:apply-templates mode="outline">
                        <xsl:with-param name="active-id" select="$active-id"/>
                    </xsl:apply-templates>
                </ul>
            </xsl:if>
        </li>
    </xsl:template>

</xsl:stylesheet>
