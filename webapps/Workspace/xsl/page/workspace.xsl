<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <section class="ws">
            <xsl:apply-templates select="//availableapps"/>
        </section>
    </xsl:template>

    <xsl:template match="availableapps">
        <section class="ws-apps">
            <div class="container">
                <xsl:choose>
                    <xsl:when test="//@userid != 'anonymous'">
                        <xsl:apply-templates select="query/entry" mode="app"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:call-template name="off-app"/>
                    </xsl:otherwise>
                </xsl:choose>
            </div>
        </section>
    </xsl:template>

    <xsl:template match="entry" mode="app">
        <div class="ws-app">
            <a class="ws-app-link" href="/{viewcontent/viewtext}/{viewcontent/viewtext1}">
                <span class="ws-app-logo">
                    <img class="ws-app-logo" src="/{viewcontent/viewtext}/img/logo.png" alt="logo"/>
                </span>
                <span class="ws-app-type">
                    <xsl:value-of select="viewcontent/viewtext4"/>
                </span>
                <span class="ws-app-name">
                    <xsl:value-of select="viewcontent/viewtext3"/>
                </span>
            </a>
        </div>
    </xsl:template>

    <xsl:template name="off-app">
        <div class="ws-app off"></div>
        <div class="ws-app off"></div>
        <div class="ws-app off"></div>
    </xsl:template>

</xsl:stylesheet>
