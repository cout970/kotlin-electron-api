<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <!-- settings -->
    <xsl:output method="text" />
    <xsl:strip-space elements="*" />

    <xsl:template match="/">
        <xsl:text>package jsapi.electron&#10;&#10;</xsl:text>
        <xsl:apply-templates />
    </xsl:template>


    <!-- OBJECT -->
    <xsl:template match="object">
        <xsl:value-of select="concat('object ', @title, ' {&#10;&#10;')" />
        <xsl:text>    private val module: dynamic = js("require('electron').</xsl:text>
        <xsl:value-of select="@title" />
        <xsl:text>")&#10;&#10;</xsl:text>
        <xsl:text>    fun onEvent(event: String, callback: () -> Unit) = &#10;</xsl:text>
        <xsl:text>        module.on(event, callback)&#10;&#10;</xsl:text>
        <xsl:apply-templates select="methods/method" />
        <xsl:text>    // ~ Builders -------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        <xsl:apply-templates mode="builder" select="(constructor|methods/method)/param" />
        <xsl:text>}&#10;&#10;</xsl:text>
    </xsl:template>


    <!-- CLASS -->
    <xsl:template match="class">
        <xsl:value-of select="concat('class ', @title)" />
        <xsl:apply-templates select="constructor" />
        <xsl:text> {&#10;&#10;</xsl:text>
        <xsl:text>    private val module: dynamic = js("require('electron').</xsl:text>
        <xsl:value-of select="@title" />
        <xsl:text>")&#10;&#10;</xsl:text>
        <xsl:apply-templates mode="delegate_call" select="constructor" />
        <xsl:text>    fun onEvent(event: String, callback: () -> Unit) = &#10;</xsl:text>
        <xsl:text>        module.on(event, callback)&#10;&#10;</xsl:text>
        <xsl:apply-templates select="methods/method" />
        <xsl:text>    // ~ Builders -------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        <xsl:apply-templates mode="builder" select="(constructor|methods/method)/param" />
        <xsl:text>}&#10;&#10;</xsl:text>
    </xsl:template>


    <!-- STRUCT -->
    <xsl:template match="struct">
        <xsl:value-of select="concat('class ', @title, '(&#10;')" />
        <xsl:apply-templates select="property" />
        <xsl:text>) {&#10;&#10;</xsl:text>
        <xsl:text>    val instance: dynamic = this&#10;&#10;</xsl:text>
        <xsl:text>    // ~ Builders -------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        <xsl:apply-templates mode="builder" select="property" />
        <xsl:text>}&#10;&#10;</xsl:text>
    </xsl:template>


    <!-- CONSTRUCTOR -->
    <xsl:template match="constructor">
        <xsl:text>(</xsl:text>
        <xsl:apply-templates />
        <xsl:text>)</xsl:text>
    </xsl:template>


    <!-- METHOD -->
    <xsl:template match="method[not(contains(@name, '.')) and not(@name = 'require')]">
        <xsl:if test="position() = 1">
            <xsl:text>    // ~ Methods -------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        </xsl:if>

        <!-- name + parameters -->
        <xsl:value-of select="concat('    fun ', @name, '(')" />
        <xsl:apply-templates select="param" />
        <xsl:text>): </xsl:text>

        <!-- returns type -->
        <xsl:apply-templates select="returns" />
        <xsl:if test="not(returns)">
            <xsl:text>Unit</xsl:text>
        </xsl:if>

        <!-- delegate call -->
        <xsl:text> = &#10;</xsl:text>
        <xsl:apply-templates mode="delegate_call" select="." />
    </xsl:template>

    <xsl:template match="method" />


    <!-- PARAMETER -->
    <xsl:template match="param">
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- optional PARAMETER -->
    <xsl:template match="param[@optional = true()]">
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>?</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- array PARAMETER -->
    <xsl:template match="param[@isArray = true()]">
        <xsl:value-of select="@name" />
        <xsl:text>: Array&lt;</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>&gt;</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- optional array PARAMETER -->
    <xsl:template match="param[@isArray = true() and @optional = true()]" priority="2">
        <xsl:value-of select="@name" />
        <xsl:text>: Array&lt;</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>&gt;?</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- optional function need to be in parentheses -->
    <xsl:template match="param[@optional=true() and @type='Function']" priority="1">
        <xsl:value-of select="@name" />
        <xsl:text>: (</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>)?</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- object builder -->
    <xsl:template match="param[@type = 'Object' and count(property[@optional = true()]) = count(property)]"
        priority="1">
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>.() -> Unit</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- object builder -->
    <xsl:template
        match="param[@type = 'Object' and @optional=true() and count(property[@optional = true()]) = count(property)]"
        priority="2"
    >
        <xsl:value-of select="@name" />
        <xsl:text>: (</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>.() -> Unit)?</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- RETURN parameter -->
    <xsl:template match="returns">
        <xsl:apply-templates select="@type" />
    </xsl:template>

    <!-- RETURN parameter: array -->
    <xsl:template match="returns[@isArray = true()]">
        <xsl:text>Array&lt;</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>&gt;</xsl:text>
    </xsl:template>

    <!-- RETURN parameter: Object -->
    <xsl:template match="returns/@type[. = 'Object']">
        <xsl:text>dynamic</xsl:text>
    </xsl:template>

    <!-- Type: Function -->
    <xsl:template match="@type[. = 'Function']">
        <xsl:text>(</xsl:text>
        <xsl:apply-templates select="parent::node()/param" />
        <xsl:text>) -> Unit</xsl:text>
    </xsl:template>

    <!-- Type: Object -->
    <xsl:template match="*[self::param or self::property]/@type[. = 'Object']">
        <xsl:apply-templates mode="builder_name" select="parent::node()" />
    </xsl:template>

    <!-- Type: Int -->
    <xsl:template match="@type[. = 'Integer']">
        <xsl:text>Int</xsl:text>
    </xsl:template>

    <!-- Type: Number -->
    <xsl:template match="@type[. = 'Number']">
        <xsl:text>Float</xsl:text>
    </xsl:template>

    <!-- Type: Boolean -->
    <xsl:template match="@type[. = 'true']">
        <xsl:text>Boolean</xsl:text>
    </xsl:template>

    <!-- Type: * -->
    <xsl:template
        match="@type[. = 'Buffer' or . = 'union' or . = 'the' or . = 'MenuItemConstructorOptions' or . = 'URL' or . = 'Blob' or . = 'any' or . = 'Promise' or . = 'Event' or . = 'Accelerator']">
        <xsl:text>dynamic</xsl:text>
    </xsl:template>

    <!-- Type: String  -->
    <xsl:template match="@type">
        <xsl:value-of select="."/>
    </xsl:template>


    <!-- BUILDER NAME for object -->
    <xsl:template match="*[(self::param or self::property) and @type = 'Object']" mode="builder_name">
        <xsl:value-of select="upper-case(substring(ancestor::method/@name, 1, 1))" />
        <xsl:value-of select="substring(ancestor::method/@name, 2)" />
        <xsl:value-of select="upper-case(substring(@name, 1, 1))" />
        <xsl:value-of select="substring(@name, 2)" />
    </xsl:template>

    <!-- builder for param object  -->
    <xsl:template match="*[(self::param or self::property) and @type = 'Object']" mode="builder">

        <!-- name -->
        <xsl:text>    class </xsl:text>
        <xsl:apply-templates mode="builder_name" select="." />
        <xsl:text>(&#10;</xsl:text>
        <xsl:apply-templates />
        <xsl:text>    )&#10;&#10;</xsl:text>

        <!-- inner builders -->
        <xsl:apply-templates mode="builder" />

    </xsl:template>

    <!-- PROPERTY -->
    <xsl:template match="property">
        <xsl:text>        var </xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- optional PROPERTY -->
    <xsl:template match="property[@optional = true()]">
        <xsl:text>        var </xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>? = null</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- optional function need to be in parentheses -->
    <xsl:template match="property[@optional=true() and @type='Function']" priority="1">
        <xsl:text>        var </xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>: (</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>)? = null</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- array PROPERTY -->
    <xsl:template match="property[@isArray = true()]">
        <xsl:text>        var </xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>: Array&lt;</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>&gt;</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- array PROPERTY -->
    <xsl:template match="property[@isArray = true() and @optional = true()]" priority="1">
        <xsl:text>        var </xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>: Array&lt;</xsl:text>
        <xsl:apply-templates select="@type" />
        <xsl:text>&gt;? = null</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>


    <!-- METHOD delegate call -->
    <xsl:template match="method" mode="delegate_call">
        <xsl:choose>
            <xsl:when test="../@type = 'instance'">
                <xsl:text>        instance.</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>        module.</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:value-of select="@name" />
        <xsl:text>(</xsl:text>
        <xsl:apply-templates mode="delegate_call" select="param" />
        <xsl:text>)&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- METHOD delegate call: add .instance for types -->
    <xsl:template match="param" mode="delegate_call">
        <xsl:value-of select="@name" />
        <xsl:if test="not(@isArray = true())">
            <xsl:if
                test="not(@type = ('Boolean','Number','String', 'Integer', 'Float','Object','Number','Function','Double','true'))">
                <xsl:if test="@optional=true()">
                    <xsl:text>?</xsl:text>
                </xsl:if>
                <xsl:text>.instance</xsl:text>
            </xsl:if>
        </xsl:if>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- METHOD delegate call: object building -->
    <xsl:template
        match="param[@type = 'Object' and count(property[@optional = true()]) = count(property)]"
        mode="delegate_call">
        <xsl:value-of select="@name" />
        <xsl:if test="@optional=true()">
            <xsl:text>?</xsl:text>
        </xsl:if>
        <xsl:text>.let { </xsl:text>
        <xsl:apply-templates mode="builder_name" select="." />
        <xsl:text>().apply(it) }</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>


    <!-- CONSTRUCTOR delegate call -->
    <xsl:template match="constructor" mode="delegate_call">
        <xsl:text>    val instance: dynamic&#10;&#10;</xsl:text>
        <xsl:text>    init {&#10;</xsl:text>
        <xsl:text>        val _constructor = js("require('electron').</xsl:text>
        <xsl:value-of select="../@title" />
        <xsl:text>")&#10;</xsl:text>
        <xsl:apply-templates mode="constructor_delegate_call_vals" select="param" />

        <xsl:text>        instance = js("new _constructor(</xsl:text>
        <xsl:value-of select="concat('_', param/@name)" separator=", " />
        <xsl:text>)")&#10;</xsl:text>
        <xsl:text>    }&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- CONSTRUCTOR delegate call values -->
    <xsl:template match="param" mode="constructor_delegate_call_vals">
        <xsl:value-of select="concat('        val _', @name, ' = ', @name)" />
        <xsl:text>&#10;</xsl:text>
    </xsl:template>


    <!-- CONSTRUCTOR delegate call values, object with at least one non optional parameter -->
    <xsl:template match="param[@type = 'Object' and count(param[@optional = true()]) = count(param)]"
        mode="constructor_delegate_call_vals">

        <xsl:value-of select="concat('        val _', @name, ' = ')" />
        <xsl:value-of select="@name" />
        <xsl:if test="@optional=true()">
            <xsl:text>?</xsl:text>
        </xsl:if>
        <xsl:text>.let { </xsl:text>
        <xsl:apply-templates mode="builder_name" select="." />
        <xsl:text>().apply(it) }</xsl:text>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

</xsl:stylesheet>