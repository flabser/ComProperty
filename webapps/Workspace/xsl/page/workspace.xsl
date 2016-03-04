<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <section class="ws">
            <xsl:choose>
                <xsl:when test="//query[@entity='application']">
                    <xsl:apply-templates select="//query[@entity='application']"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:call-template name="off-app"/>
                </xsl:otherwise>
            </xsl:choose>
        </section>
    </xsl:template>

    <xsl:template match="query[@entity='application']">
        <section class="ws-apps">
            <div class="container">
                <xsl:apply-templates select="entry" mode="app"/>
            </div>
        </section>
    </xsl:template>

    <xsl:template match="entry" mode="app">
        <div class="ws-app">
            <a class="ws-app-link" href="/{viewcontent/app/@id}/{viewcontent/url}">
                <span class="ws-app-logo">
                    <img class="ws-app-logo" src="/{viewcontent/app/@id}/img/logo.png" alt="logo"/>
                </span>
                <span class="ws-app-type">
                    <xsl:value-of select="viewcontent/app/@id"/>
                </span>
                <span class="ws-app-name">
                    <xsl:value-of select="viewcontent/app"/>
                </span>
            </a>
        </div>
    </xsl:template>

    <xsl:template name="off-app">
        <section class="ws-apps off">
            <div class="container">
                <div class="ws-app off"></div>
                <div class="ws-app off"></div>
                <div class="ws-app off"></div>
            </div>
        </section>
    </xsl:template>

</xsl:stylesheet>
