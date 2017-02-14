<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <!-- settings -->
    <xsl:output method="text" />
    <xsl:strip-space elements="*" />
    <xsl:include href="xslLineWrapping.xsl" />
    <xsl:template match="text()" mode="#all" />

    <!-- file -->
    <xsl:template match="/">
        <xsl:text>package jsapi.electron&#10;&#10;</xsl:text>
        <xsl:apply-templates />
    </xsl:template>

    <!-- STRUCT -->
    <xsl:template match="struct">
        <xsl:value-of select="concat('class ', @title, '(&#10;')" />
        <xsl:apply-templates mode="constructor" />
        <xsl:text>) {&#10;&#10;</xsl:text>
        <xsl:text>    val instance: dynamic = this&#10;&#10;</xsl:text>
        <xsl:text>    // ~ Builders ------------------------------------------------------------------------------&#10;</xsl:text>
        <xsl:apply-templates mode="adhoc_object_builder" select="property" />
        <xsl:text>}&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- OBJECT -->
    <xsl:template match="object">
        <xsl:value-of select="concat('object ', @title, ' {&#10;&#10;')" />

        <!-- import -->
        <xsl:text>    </xsl:text>
        <xsl:apply-templates mode="module" select="@title" />

        <!-- event -->
        <xsl:apply-templates mode="events" select="." />

        <!-- methods -->
        <xsl:apply-templates select="methods" />

        <!-- builders -->
        <xsl:text>&#10;</xsl:text>
        <xsl:text>    // ~ Builders ------------------------------------------------------------------------------&#10;</xsl:text>
        <xsl:apply-templates mode="adhoc_object_builder" select="(constructor|methods/method)/param" />

        <!-- end -->
        <xsl:text>}&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- CLASS -->
    <xsl:template match="class">
        <xsl:value-of select="concat('class ', @title)" />

        <!-- constructor -->
        <xsl:apply-templates mode="signature" select="constructor" />
        <xsl:text> {&#10;&#10;</xsl:text>
        <xsl:apply-templates mode="delegate_call" select="constructor" />

        <!-- event -->
        <xsl:apply-templates mode="events" select="." />

        <!-- properties -->
        <xsl:apply-templates select="properties[@type='instance']" />

        <!-- methods -->
        <xsl:apply-templates select="methods[@type='instance']" />

        <!-- class companion -->
        <xsl:text>&#10;</xsl:text>
        <xsl:text>    // ~ Companion -----------------------------------------------------------------------------&#10;&#10;</xsl:text>
        <xsl:text>    companion object {</xsl:text>
        <xsl:text> &#10;&#10;</xsl:text>

        <!-- import -->
        <xsl:text>        </xsl:text>
        <xsl:apply-templates mode="module" select="@title" />

        <!-- methods -->
        <xsl:apply-templates select="methods[@type='static']" />

        <!-- /end of class companion -->
        <xsl:text>    }&#10;</xsl:text>

        <!-- builders -->
        <xsl:text>&#10;</xsl:text>
        <xsl:text>    // ~ Builders ------------------------------------------------------------------------------&#10;</xsl:text>
        <xsl:apply-templates mode="adhoc_object_builder" select="(constructor|methods/method)/param" />

        <!-- end -->
        <xsl:text>}&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- module -->
    <xsl:template match="class/@title|object/@title" mode="module">
        <xsl:text>private val module: dynamic = js("require('electron').</xsl:text>
        <xsl:value-of select="." />
        <xsl:text>")&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- constructor -->
    <xsl:template match="constructor" mode="signature">
        <!-- signature -->
        <xsl:text>(</xsl:text>
        <xsl:apply-templates mode="signature" />
        <xsl:text>)</xsl:text>
    </xsl:template>

    <!-- constructor -->
    <xsl:template match="constructor" mode="delegate_call">

        <!-- delegate / init -->
        <xsl:text>    val instance: dynamic&#10;&#10;</xsl:text>
        <xsl:text>    init {&#10;</xsl:text>
        <xsl:text>        val _constructor = js("require('electron').</xsl:text>
        <xsl:value-of select="../@title" />
        <xsl:text>")&#10;</xsl:text>
        <xsl:apply-templates mode="delegate_call_vals" />

        <xsl:text>        instance = js("new _constructor(</xsl:text>
        <xsl:apply-templates mode="delegate_call" />
        <xsl:text>)")&#10;</xsl:text>
        <xsl:text>    }&#10;&#10;</xsl:text>
    </xsl:template>


    <!-- param: constructor delegate call -->
    <xsl:template match="constructor/param" mode="delegate_call">
        <xsl:text>_</xsl:text>
        <xsl:value-of select="@name" />
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- delegate call -->
    <xsl:template match="constructor/param" mode="delegate_call_vals">
        <xsl:value-of select="concat('        val _', @name, ' = ')" />
        <xsl:apply-templates mode="delegate_call" select="." />
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- events -->
    <xsl:template match="class|object" mode="events">
        <xsl:text>    // ~ Events --------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        <xsl:text>    fun onEvent(event: String, callback: () -> Unit) = &#10;</xsl:text>
        <xsl:text>        module.on(event, callback)&#10;&#10;</xsl:text>
    </xsl:template>

    <!-- methods -->
    <xsl:template match="methods">
        <xsl:apply-templates select="*[not(contains(@name, '.') or @name = 'require')]" />
    </xsl:template>


    <!-- method -->
    <xsl:template match="method">
        <xsl:variable name="indent">
            <xsl:if test="parent::methods[@type='static']">
                <xsl:text>    </xsl:text>
            </xsl:if>
        </xsl:variable>

        <xsl:if test="position() = 1">
            <xsl:value-of select="$indent" />
            <xsl:text>    // ~ Methods -------------------------------------------------------------------------------&#10;&#10;</xsl:text>
        </xsl:if>

        <!-- description -->
        <xsl:apply-templates select="description" />

        <!-- fun <name>(params)-->
        <xsl:value-of select="concat($indent, '    fun ', @name, '(')" />
        <xsl:apply-templates mode="signature" select="param" />
        <xsl:text>): </xsl:text>

        <!-- : returns -->
        <xsl:apply-templates mode="type" select="returns" />
        <xsl:if test="not(returns)">
            <xsl:text>Unit</xsl:text>
        </xsl:if>
        <xsl:text> = &#10;</xsl:text>

        <!-- delegate call -->
        <xsl:value-of select="$indent" />
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
        <xsl:text>)</xsl:text>

        <!-- end -->
        <xsl:text>&#10;</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:text>&#10;</xsl:text>
        </xsl:if>
    </xsl:template>


    <!-- param: method/constructor signature -->
    <xsl:template match="param" mode="signature">
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates mode="type" select="." />
        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- methods/constructor delegate call: object -->
    <xsl:template match="param" mode="delegate_call">
        <xsl:variable name="allPropsOptional">
            <xsl:value-of select="count(property[not(@optional = true())]) = 0" />
        </xsl:variable>

        <!-- var name -->
        <xsl:value-of select="@name" />

        <!-- reference to wrapped instance -->
        <xsl:if
            test="not(@type = 'Object') and not(@isArray = true()) and not(@type = ('union', 'Boolean', 'Number', 'String', 'Integer', 'Float', 'Object', 'Function', 'Double'))">
            <xsl:if test="@optional=true()">
                <xsl:text>?</xsl:text>
            </xsl:if>
            <xsl:text>.instance</xsl:text>
        </xsl:if>

        <!-- object building -->
        <xsl:if test="@type = 'Object' and $allPropsOptional = true()">
            <xsl:if test="@optional=true()">
                <xsl:text>?</xsl:text>
            </xsl:if>
            <xsl:text>.let { </xsl:text>
            <xsl:apply-templates mode="adhoc_object_name" select="." />
            <xsl:text>().apply(it) }</xsl:text>
        </xsl:if>

        <xsl:if test="position() &lt; last()">
            <xsl:text>, </xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- params -->
    <xsl:template match="returns|param|property" mode="type">

        <!-- variables -->
        <xsl:variable name="allPropsOptional">
            <xsl:value-of select="count(property[not(@optional = true())]) = 0" />
        </xsl:variable>
        <xsl:variable name="isFunction">
            <xsl:value-of select="@type = 'Function' or (@type = 'Object' and $allPropsOptional = true())" />
        </xsl:variable>

        <xsl:choose>
            <xsl:when test="@isArray = true()">
                <xsl:text>Array&lt;</xsl:text>
            </xsl:when>
            <xsl:when test="@optional = true() and $isFunction = true()">
                <xsl:text>(</xsl:text>
            </xsl:when>
        </xsl:choose>

        <xsl:choose>
            <xsl:when test="@type = 'Integer'">
                <xsl:text>Int</xsl:text>
            </xsl:when>
            <xsl:when test="@type = 'Function'">
                <xsl:text>(</xsl:text>
                <xsl:apply-templates mode="signature" select="param" />
                <xsl:text>) -> Unit</xsl:text>
            </xsl:when>
            <xsl:when test="@type = 'Object'">
                <xsl:apply-templates mode="adhoc_object_name" select="." />
                <xsl:if test="$allPropsOptional = true()">
                    <xsl:text>.() -> Unit</xsl:text>
                </xsl:if>
            </xsl:when>
            <xsl:when
                test="@type = ('chunkedEncoding', 'headers', 'httpVersionMajor', 'Buffer', 'union', 'MenuItemConstructorOptions', 'URL', 'Blob', 'any', 'Promise', 'Event', 'Accelerator')">
                <xsl:text>dynamic</xsl:text>
            </xsl:when>
            <xsl:when test="not(@type)">
                <xsl:text>dynamic</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="@type" />
            </xsl:otherwise>
        </xsl:choose>

        <xsl:choose>
            <xsl:when test="@isArray = true()">
                <xsl:text>&gt;</xsl:text>
            </xsl:when>
            <xsl:when test="@optional = true() and $isFunction = true()">
                <xsl:text>)</xsl:text>
            </xsl:when>
        </xsl:choose>
        <xsl:if test="@optional = true()">
            <xsl:text>?</xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- return type object is dynamic for now -->
    <xsl:template match="returns[@type = 'Object']" mode="type">
        <xsl:text>dynamic</xsl:text>
    </xsl:template>

    <!-- object param name -->
    <xsl:template match="*[self::param|self::property and @type = 'Object']" mode="adhoc_object_name">
        <xsl:value-of select="upper-case(substring(ancestor::method/@name, 1, 1))" />
        <xsl:value-of select="substring(ancestor::method/@name, 2)" />
        <xsl:value-of select="upper-case(substring(@name, 1, 1))" />
        <xsl:value-of select="substring(@name, 2)" />
    </xsl:template>


    <!-- builder for param object  -->
    <xsl:template match="*[self::param|self::property and @type = 'Object']" mode="adhoc_object_builder">

        <!-- name -->
        <xsl:text>&#10;    class </xsl:text>
        <xsl:apply-templates mode="adhoc_object_name" select="." />

        <!-- properties -->
        <xsl:text>(&#10;</xsl:text>
        <xsl:apply-templates mode="constructor" select="property" />
        <xsl:text>    )</xsl:text>

        <!-- inner builders -->
        <xsl:apply-templates mode="adhoc_object_builder" />

        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- properties -->
    <xsl:template match="property" mode="constructor">
        <xsl:text>        var </xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates mode="type" select="." />
        <xsl:if test="@optional = true()">
            <xsl:text> = null</xsl:text>
        </xsl:if>
        <xsl:if test="position() &lt; last()">
            <xsl:text>,</xsl:text>
        </xsl:if>
        <xsl:text>&#10;</xsl:text>
    </xsl:template>

    <!-- properties -->
    <xsl:template match="properties/property">
        <xsl:if test="position() = 1">
            <xsl:text>    // ~ Properties ----------------------------------------------------------------------------&#10;&#10;</xsl:text>
        </xsl:if>

        <!-- description -->
        <xsl:apply-templates select="description" />

        <!-- val name: type -->
        <xsl:text>    val </xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>: </xsl:text>
        <xsl:apply-templates mode="type" select="." />
        <xsl:text> get() = instance.</xsl:text>
        <xsl:value-of select="@name" />
        <xsl:text>&#10;&#10;</xsl:text>
        <xsl:if test="position() = last()">
            <xsl:text>&#10;</xsl:text>
        </xsl:if>
    </xsl:template>


    <!-- indent -->
    <xsl:template match="property/description" mode="indent" priority="-1">
        <xsl:text>    </xsl:text>
    </xsl:template>
    <xsl:template match="property/description/*" mode="indent" priority="-1">
        <xsl:text>    </xsl:text>
    </xsl:template>
    <xsl:template match="method/description" mode="indent" priority="-1">
        <xsl:text>    </xsl:text>
    </xsl:template>
    <xsl:template match="method/description//*" mode="indent" priority="-1">
        <xsl:text>    </xsl:text>
    </xsl:template>

    <xsl:template match="class/methods[@type='static']/method/description" mode="indent">
        <xsl:text>        </xsl:text>
    </xsl:template>
    <xsl:template match="class/methods[@type='static']/method/description//*" mode="indent">
        <xsl:text>        </xsl:text>
    </xsl:template>

    <!-- description -->
    <xsl:template match="description">
        <xsl:apply-templates mode="indent" select="." />
        <xsl:text>/**&#10;</xsl:text>
        <xsl:apply-templates />
        <xsl:apply-templates mode="indent" select="." />
        <xsl:text> */&#10;</xsl:text>
    </xsl:template>

    <!-- description -->
    <xsl:template match="description/para">
        <xsl:variable name="indent">
            <xsl:apply-templates mode="indent" select="." />
        </xsl:variable>

        <xsl:apply-templates mode="indent" select="." />
        <xsl:text> * </xsl:text>

        <xsl:call-template name="wrap-string">
            <xsl:with-param name="str">
                <xsl:value-of select="." />
            </xsl:with-param>
            <xsl:with-param name="break-mark">
                <xsl:value-of select="concat('&#10;', $indent, ' * ')" />
            </xsl:with-param>
            <xsl:with-param name="wrap-col">80</xsl:with-param>
        </xsl:call-template>

        <xsl:text>&#10;</xsl:text>
        <xsl:if test="position() &lt; last()">
            <xsl:apply-templates mode="indent" select="." />
            <xsl:text> *&#10;</xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- description -->
    <xsl:template match="description//item">
        <xsl:variable name="indent">
            <xsl:apply-templates mode="indent" select="." />
        </xsl:variable>

        <xsl:variable name="listIndent">
            <xsl:for-each select="1 to count(ancestor::*[self::list]) - 1">
                <xsl:text>   </xsl:text>
            </xsl:for-each>
        </xsl:variable>
        <xsl:value-of select="concat($indent, ' * ', $listIndent, ' . ')" />

        <xsl:call-template name="wrap-string">
            <xsl:with-param name="str">
                <xsl:value-of select="para" />
            </xsl:with-param>
            <xsl:with-param name="break-mark">
                <xsl:value-of select="concat('&#10;', $indent, ' * ', $listIndent, '   ')" />
            </xsl:with-param>
            <xsl:with-param name="wrap-col">80</xsl:with-param>
        </xsl:call-template>

        <xsl:text>&#10;</xsl:text>

        <!-- line after item -->
        <!--<xsl:value-of select="concat($indent, ' *&#10;')"/>-->

        <xsl:if test="position() = last()">
            <xsl:value-of select="concat($indent, ' *&#10;')" />
        </xsl:if>

        <xsl:apply-templates select="list" />
    </xsl:template>

    <!-- description -->
    <xsl:template match="description/programlisting">
        <xsl:variable name="indent">
            <xsl:apply-templates mode="indent" select="." />
        </xsl:variable>
        <xsl:for-each select="tokenize(., '\n')">
            <xsl:value-of select="concat($indent, ' *  | ', ., '&#10;')" />
        </xsl:for-each>
        <xsl:if test="position() &lt; last()">
            <xsl:apply-templates mode="indent" select="." />
            <xsl:text> *&#10;</xsl:text>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>