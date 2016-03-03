﻿<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="templates/constants.xsl"/>

    <xsl:output method="html" encoding="utf-8" indent="no"/>

    <xsl:template name="layout">
        <xsl:param name="title" select="//org"/>
        <xsl:call-template name="HTML-DOCTYPE"/>
        <html>
            <xsl:call-template name="html-head">
                <xsl:with-param name="title" select="$title"/>
            </xsl:call-template>
            <body class="no_transition">
                <div class="main-load" id="main-load" style="display:none"></div>
                <div class="layout">
                    <div class="content-overlay" id="content-overlay"></div>
                    <xsl:call-template name="main-header"/>
                    <div class="container">
                        <section class="content">
                            <xsl:call-template name="sign-in-form"/>
                            <xsl:call-template name="_content"/>
                        </section>
                    </div>
                    <xsl:call-template name="main-footer"/>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="_content"/>

    <xsl:template name="html-head">
        <xsl:param name="title" select="''"/>
        <head>
            <title>
                <xsl:value-of select="$title"/>
            </title>
            <link rel="shortcut icon" href="favicon.ico"/>
            <meta name="format-detection" content="telephone=no"/>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

            <link rel="stylesheet" href="/SharedResources/vendor/font-awesome/css/font-awesome.min.css"/>
            <link rel="stylesheet" href="/SharedResources/nb/css/nb.build.css"/>
            <link rel="stylesheet" href="css/ws.css"/>
        </head>
    </xsl:template>

    <xsl:template name="main-header">
        <header class="header navbar">
            <div class="container">
                <div class="navbar-header">
                    <span class="brand-title">
                        <xsl:value-of select="//org"/>
                    </span>
                </div>
                <nav class="navbar-nav navbar-right">
                    <xsl:if test="//@userid != 'anonymous'">
                        <ul class="nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-user"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="user-profile" title="{//captions/user_profile/@caption}">
                                            <xsl:value-of select="@username"/>
                                        </a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a class="logout" href="Logout">
                                            <xsl:value-of select="//captions/logout/@caption"/>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </xsl:if>
                </nav>
            </div>
        </header>
    </xsl:template>

    <xsl:template name="sign-in-form">
        <xsl:if test="//@userid = 'anonymous'">
            <form class="sign-in" action="Login" method="post">
                <table>
                    <tr>
                        <td>
                            <input type="text" name="login" id="login"/>
                        </td>
                        <td>
                            <input type="password" name="pwd" value="" id="pwd"/>
                        </td>
                        <td>
                            <button type="submit">sign in</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" id="cbx" name="noauth" value="1"/>
                            <font>
                                Чужой компьютер
                            </font>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <input type="hidden" name="type" value="login"/>
            </form>
        </xsl:if>
    </xsl:template>

    <xsl:template name="main-footer">
        <div class="footer-spacer"></div>
        <footer class="footer">
            <span>v.</span>
            <span>
                <xsl:value-of select="//serverversion"/>
            </span>
            <span>build:</span>
            <span>
                <xsl:value-of select="//build"/>
            </span>
        </footer>
    </xsl:template>

</xsl:stylesheet>