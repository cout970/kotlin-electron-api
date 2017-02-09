<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <!-- settings -->
    <xsl:output method="text" />
    <xsl:strip-space elements="*" />
    <xsl:template match="text()" mode="#all" />

    <xsl:template match="/">
        <xsl:text>package jsapi.electron&#10;&#10;</xsl:text>
        <xsl:apply-templates select="file/(object|class)" />
    </xsl:template>

    <!-- OBJECT -->
    <xsl:template match="object|class">
        <xsl:value-of select="local-name()" />
        <xsl:text> </xsl:text>
        <xsl:value-of select="@title" />
        <xsl:apply-templates select="constructor" />
        <xsl:text> {&#10;&#10;</xsl:text>

        <!-- Delegate -->
        <xsl:text>    private val module: dynamic = js("require('electron').</xsl:text>
        <xsl:value-of select="@title" />
        <xsl:text>")&#10;</xsl:text>
        <xsl:if test="local-name() = 'class'">
            <xsl:text>    private val instance: dynamic = js("new (require('electron').</xsl:text>
            <xsl:value-of select="@title" />
            <xsl:text>)()")&#10;</xsl:text>
        </xsl:if>

        <xsl:text>&#10;</xsl:text>
        <xsl:text>    fun onEvent(event: String, callback: () -> Unit) = &#10;</xsl:text>
        <xsl:text>        module.on(event, callback)&#10;&#10;</xsl:text>

        <xsl:apply-templates select="//method" />
        <xsl:text>    // ~ Builders -------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        <xsl:apply-templates mode="builder" select="//(constructor|method)/parameters" />
        <xsl:text>}</xsl:text>
    </xsl:template>

    <!-- CONSTRUCTOR -->
    <xsl:template match="constructor">
        <xsl:text>(</xsl:text>
        <xsl:apply-templates mode="name_and_type" />
        <xsl:text>)</xsl:text>
    </xsl:template>

    <!-- METHOD without . -->
    <xsl:template match="method[not(contains(@name, '.')) and not(@name = 'require')]">

        <xsl:if test="position() = 1">
            <xsl:text>    // ~ Methods -------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        </xsl:if>

        <!-- name -->
        <xsl:text>    fun </xsl:text>
        <xsl:value-of select="@name" />

        <!-- parameters -->
        <xsl:text>(</xsl:text>
        <xsl:apply-templates mode="name_and_type" select="parameters" />
        <xsl:text>): </xsl:text>
        <xsl:apply-templates mode="type" select="returns/param" />
        <xsl:if test="not(returns)">
            <xsl:text>Unit</xsl:text>
        </xsl:if>

        <!-- delegate call -->
        <xsl:text> = &#10;</xsl:text>
        <xsl:choose>
            <xsl:when test="@type = 'instance'">
                <xsl:text>        instance.</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>        module.</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:value-of select="@name" />
        <xsl:text>(</xsl:text>
        <xsl:value-of select="parameters/param/@name" separator=", " />
        <xsl:text>)&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- PARAMETER Name: Type -->
    <xsl:template match="param" mode="name_and_type">

        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates mode="type" select="." />
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- PARAMETER Type -->
    <xsl:template match="param[@optional=true() and (@type='Function' or @type='Object') and not(@isArray=true())]"
        mode="type">
        <xsl:text>(</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>)?</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- PARAMETER Type -->
    <xsl:template match="param" mode="type">
        <xsl:if test="@isArray = true()">
            <xsl:text>Array&lt;</xsl:text>
        </xsl:if>
        <xsl:apply-templates select="@type" />
        <xsl:if test="@isArray = 'true'">
            <xsl:text>&gt;</xsl:text>
        </xsl:if>
        <xsl:if test="@optional = true()">
            <xsl:text>?</xsl:text>
        </xsl:if>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- Type: Function -->
    <xsl:template match="@type[. = 'Function']">
        <xsl:text>(</xsl:text>
        <xsl:apply-templates mode="name_and_type" select="parent::node()/*" />
        <xsl:text>) -> Unit</xsl:text>
    </xsl:template>

    <!-- Type: Object -->
    <xsl:template match="@type[. = 'Object' and not(ancestor::returns) and not(../../../@type = 'Function')]">
        <xsl:apply-templates mode="builderName" select="." />
        <xsl:text>.() -> Unit</xsl:text>
    </xsl:template>

    <!-- Type: Int -->
    <xsl:template match="@type[. = 'Integer']">
        <xsl:text>Int</xsl:text>
    </xsl:template>

    <!-- Type: String  -->
    <xsl:template match="@type[. = 'String']">
        <xsl:text>String</xsl:text>
    </xsl:template>

    <!-- Type: Double -->
    <xsl:template match="@type[. = 'Double']">
        <xsl:text>Double</xsl:text>
    </xsl:template>

    <!-- Type: Float -->
    <xsl:template match="@type[. = 'Float']">
        <xsl:text>Float</xsl:text>
    </xsl:template>

    <!-- Type: Boolean -->
    <xsl:template match="@type[. = 'Boolean']">
        <xsl:text>Boolean</xsl:text>
    </xsl:template>

    <!-- Type: Boolean -->
    <xsl:template match="@type[. = 'Number']">
        <xsl:text>Float</xsl:text>
    </xsl:template>

    <!-- Type: * -->
    <xsl:template match="@type">
        <xsl:text>dynamic</xsl:text>
    </xsl:template>

    <!-- Type: Object BuilderName -->
    <xsl:template match="@type[. = 'Object']" mode="builderName">
        <xsl:variable name="methodName" select="ancestor::method/@name" />
        <xsl:variable name="paramName" select="parent::param/@name" />
        <xsl:value-of select="upper-case(substring($methodName, 1, 1))" />
        <xsl:value-of select="substring($methodName, 2)" />
        <xsl:value-of select="upper-case(substring($paramName, 1, 1))" />
        <xsl:value-of select="substring($paramName, 2)" />
    </xsl:template>

    <!-- Object Parameter Builder -->
    <xsl:template match="param[@type = 'Object' and not(../../@type = 'Function')]" mode="builder">

        <!-- name -->
        <xsl:text>    class </xsl:text>
        <xsl:apply-templates mode="builderName" select="@type" />
        <xsl:text>(&#10;</xsl:text>
        <xsl:apply-templates mode="builder_val" />
        <xsl:text>    )&#10;&#10;</xsl:text>

        <!-- inner builders -->
        <xsl:apply-templates mode="builder" select="parameters" />

    </xsl:template>

    <!-- Parameter (Builder) -->
    <xsl:template match="param" mode="builder_val">
        <xsl:value-of select="concat('        var ', replace(@name, '-', ''), ': ')" />
        <xsl:apply-templates mode="type" select="." />
        <xsl:if test="position() &lt; last()">
            <xsl:text>,</xsl:text>
        </xsl:if>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>


</xsl:stylesheet>