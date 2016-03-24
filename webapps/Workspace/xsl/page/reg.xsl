<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:import href="../layout.xsl"/>

    <xsl:template match="/request">
        <xsl:call-template name="layout"/>
    </xsl:template>

    <xsl:template name="_content">
        <form class="sign-up" action="" method="post">
            <h1>
                <xsl:value-of select="//captions/sign_up/@caption"/>
            </h1>
            <label class="fio">
                <input type="text" name="fio" value="" required="required">
                    <xsl:attribute name="placeholder" select="//captions/fio/@caption"/>
                </input>
            </label>
            <label class="org">
                <input type="text" name="org" value="" required="required">
                    <xsl:attribute name="placeholder" select="//captions/org/@caption"/>
                </input>
            </label>
            <label class="orgbin">
                <input type="text" name="orgbin" value="" required="required">
                    <xsl:attribute name="placeholder" select="//captions/bin/@caption"/>
                </input>
            </label>
            <label class="login">
                <input type="text" name="login" value="" required="required">
                    <xsl:attribute name="placeholder" select="//captions/user/@caption"/>
                </input>
            </label>
            <label class="comment">
                <textarea name="comment">
                    <xsl:attribute name="placeholder" select="//captions/comment/@caption"/>
                </textarea>
            </label>
            <button class="btn" type="submit">
                <xsl:value-of select="//captions/sign_up/@caption"/>
            </button>
            <a href="?id=workspace" class="btn-sign-in">
                <xsl:value-of select="//captions/login/@caption"/>
            </a>
        </form>
    </xsl:template>

</xsl:stylesheet>
