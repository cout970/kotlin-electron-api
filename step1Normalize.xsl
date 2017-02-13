<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:custom="http://www.org" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    exclude-result-prefixes="custom" version="2.0">

    <!-- settings -->
    <xsl:output indent="yes" method="xml" />
    <xsl:strip-space elements="*" />

    <!-- copy everything -->
    <xsl:template match="@*|node()" mode="pass1">
        <xsl:copy>
            <xsl:apply-templates mode="pass1" select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="@*|node()" mode="pass2">
        <xsl:copy>
            <xsl:apply-templates mode="pass2" select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="@*|node()" mode="pass3">
        <xsl:copy>
            <xsl:apply-templates mode="pass3" select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="@*|node()" mode="pass4">
        <xsl:copy>
            <xsl:apply-templates mode="pass4" select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="@*|node()" mode="pass5">
        <xsl:copy>
            <xsl:apply-templates mode="pass5" select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="@*|node()" mode="pass6">
        <xsl:copy>
            <xsl:apply-templates mode="pass6" select="@*|node()" />
        </xsl:copy>
    </xsl:template>

    <xsl:template match="/">
        <xsl:variable name="pass1output">
            <xsl:apply-templates mode="pass1" />
        </xsl:variable>
        <xsl:variable name="pass2output">
            <xsl:apply-templates mode="pass2" select="$pass1output" />
        </xsl:variable>
        <xsl:variable name="pass3output">
            <xsl:apply-templates mode="pass3" select="$pass2output" />
        </xsl:variable>
        <xsl:variable name="pass4output">
            <xsl:apply-templates mode="pass4" select="$pass3output" />
        </xsl:variable>
        <xsl:variable name="pass5output">
            <xsl:apply-templates mode="pass5" select="$pass4output" />
        </xsl:variable>
        <xsl:variable name="pass6output">
            <xsl:apply-templates mode="pass6" select="$pass5output" />
        </xsl:variable>
        <file>
            <xsl:copy-of select="$pass6output//*[self::class|self::object|self::struct]" />
        </file>
    </xsl:template>

    <!-- Pass 1: rename sect1..4 -> section -->

    <xsl:template match="sect1 | sect2 | sect3 | sect4" mode="pass1">
        <section>
            <xsl:apply-templates mode="pass1" select="@*|node()" />
        </section>
    </xsl:template>

    <!-- pull title up -->
    <xsl:template match="*[self::sect1|self::sect2|self::sect3|self::sect4]/*[1][self::title]" mode="pass1">
        <xsl:attribute name="title">
            <xsl:value-of select="." />
        </xsl:attribute>
    </xsl:template>

    <!-- Pass 2: renames, reorders -->

    <xsl:template match="section[starts-with(@title, 'Class:')]" mode="pass2">
        <class title="{substring(@title, 8)}">
            <xsl:apply-templates mode="pass2" />
        </class>
    </xsl:template>

    <xsl:template match="section[ends-with(@title, ' Object')]" mode="pass2">
        <struct title="{substring-before(@title, ' Object')}">
            <xsl:apply-templates mode="pass2" />
        </struct>
    </xsl:template>

    <xsl:template match="section[count(*[@id='events' or @id='methods']) > 0]" mode="pass2">
        <object title="{@title}">
            <xsl:apply-templates mode="pass2" />
        </object>
    </xsl:template>

    <xsl:template match="section[@id='instance-events']" mode="pass2">
        <events>
            <xsl:apply-templates mode="pass2" />
        </events>
    </xsl:template>

    <xsl:template match="section[@id='events']" mode="pass2">
        <events>
            <xsl:apply-templates mode="pass2" />
        </events>
    </xsl:template>

    <xsl:template match="section[@id='instance-methods']" mode="pass2">
        <methods type="instance">
            <xsl:apply-templates mode="pass2" />
        </methods>
    </xsl:template>

    <xsl:template match="section[@id='static-methods']" mode="pass2">
        <methods type="static">
            <xsl:apply-templates mode="pass2" />
        </methods>
    </xsl:template>

    <xsl:template match="section[@id='methods']" mode="pass2">
        <methods>
            <xsl:apply-templates mode="pass2" />
        </methods>
    </xsl:template>

    <xsl:template match="section[ends-with(@id, 'methods')]/section" mode="pass2">
        <xsl:variable name="nameWithoutParen" select="tokenize(@title, '\(')[1]" />
        <xsl:variable name="nameTokens" select="tokenize($nameWithoutParen, '\.')[position() != 1]" />
        <method name="{string-join($nameTokens, '.')}">
            <xsl:apply-templates mode="pass2" />
            <description>
                <xsl:copy-of select="node()" />
            </description>
        </method>
    </xsl:template>

    <xsl:template match="section[@id='instance-properties']" mode="pass2">
        <properties type="instance">
            <xsl:apply-templates mode="pass2" />
        </properties>
    </xsl:template>

    <xsl:template match="section[ends-with(@id, 'properties')]/section" mode="pass2">
        <xsl:variable name="nameWithoutParen" select="tokenize(@title, '\(')[1]" />
        <xsl:variable name="nameTokens" select="tokenize($nameWithoutParen, '\.')[position() != 1]" />
        <property name="{string-join($nameTokens, '.')}">
            <info>
                <xsl:value-of select="para[1]/literal[1]"/>
            </info>
            <description>
                <xsl:copy-of select="node()" />
            </description>
        </property>
    </xsl:template>

    <xsl:template match="section[starts-with(@id, 'new-')]" mode="pass2">
        <constructor>
            <xsl:apply-templates mode="pass2" />
        </constructor>
    </xsl:template>

    <xsl:template match="itemizedlist[1]" mode="pass2">
        <xsl:apply-templates mode="pass2" />
    </xsl:template>

    <xsl:template match="listitem" mode="pass2">
        <param>
            <xsl:apply-templates mode="pass2" />
        </param>
    </xsl:template>

    <xsl:template match="listitem/para" mode="pass2">
        <info>
            <xsl:apply-templates mode="pass2" />
        </info>
    </xsl:template>

    <xsl:template match="section/para[node()[1][self::text()][matches(., '^\s*Returns')]][1]" mode="pass2">
        <returns>
            <info>
                <xsl:value-of select="substring(normalize-space(.), 8)" />
            </info>
            <xsl:apply-templates mode="pass2" select="following-sibling::*[1][self::itemizedlist]"/>
            <description>
                <para>
                    <xsl:copy-of select="literal[1]/following-sibling::node()"/>
                </para>
            </description>
        </returns>
    </xsl:template>

    <!--Pass 3: parameters -->
    <xsl:template match="struct/*[not(self::param)]" mode="pass3" />
    <xsl:template match="class/*[not(self::constructor|self::methods|self::properties)]" mode="pass3" />
    <xsl:template match="object/*[not(self::class|self::constructor|self::methods|self::properties)]" mode="pass3" />
    <xsl:template match="constructor/*[not(self::param)]" mode="pass3" />
    <xsl:template match="methods/*[not(self::method)]" mode="pass3" />
    <xsl:template match="method/*[not(self::param or self::returns or self::description)]" mode="pass3" />
    <xsl:template match="properties/*[not(self::property)]" mode="pass3" />
    <xsl:template match="events" mode="pass3" />

    <!-- delete return info and list out of description -->
    <xsl:template match="method/description/para[matches(text()[1], '^\s*Returns')]" mode="pass3" priority="1"/>
    <xsl:template match="method/description/itemizedlist[preceding-sibling::*[1][self::para][matches(text()[1], '^\s*Returns')]]" mode="pass3" priority="1"/>

    <xsl:template match="//description//para" mode="pass3">
        <para>
            <xsl:value-of select="normalize-space(.)"/>
        </para>
    </xsl:template>

    <xsl:template match="//description//programlisting" mode="pass3">
        <programlisting>...omitted...</programlisting>
    </xsl:template>

    <xsl:template match="//description//itemizedlist/@spacing" mode="pass3"/>

    <!-- delete parameters out of description -->
    <xsl:template match="method/description/node()[1][self::itemizedlist]" mode="pass3" priority="3"/>

    <xsl:template match="//description//itemizedlist" mode="pass3" priority="2">
        <list>
            <xsl:apply-templates mode="pass3"/>
        </list>
    </xsl:template>

    <xsl:template match="//description//listitem" mode="pass3">
        <item>
            <xsl:apply-templates mode="pass3"/>
        </item>
    </xsl:template>

    <!-- get name -->
    <xsl:template match="param[info[node()[1][self::literal]]]" mode="pass3">
        <xsl:copy>
            <xsl:choose>
                <xsl:when test="starts-with(info/literal[1], '...')">
                    <xsl:attribute name="name">
                        <xsl:value-of select="substring-after(info/literal[1], '...')" />
                    </xsl:attribute>
                    <xsl:attribute name="vararg">true</xsl:attribute>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:attribute name="name">
                        <xsl:value-of select="info/literal[1]" />
                    </xsl:attribute>
                </xsl:otherwise>
            </xsl:choose>

            <xsl:apply-templates mode="pass3" />
        </xsl:copy>
    </xsl:template>

    <xsl:template match="info/node()[position() = 1][self::literal]" mode="pass3" />

    <xsl:template match="info/node()[position() > 1][not(self::text())]" mode="pass3">
        <xsl:value-of select="." />
    </xsl:template>

    <!-- Pass 4: NORMALIZE SPACES -->
    <xsl:template match="info/text()" mode="pass4">
        <xsl:value-of select="normalize-space(.)" />
    </xsl:template>

    <!-- Pass 5: parameters -->

    <!-- Union (..|..) -->
    <xsl:template match="info[matches(text(), '^\([a-zA-Z\s\|\[\]]+\)')]" mode="pass5">
        <xsl:attribute name="type">union</xsl:attribute>

        <xsl:if test="substring(substring-after(text(), ')'), 0, 3) = '[]'">
            <xsl:attribute name="isArray">true</xsl:attribute>
        </xsl:if>
        <xsl:variable name="types" select="substring(substring-before(text(), ')'), 2)" />
        <xsl:variable name="tokens" select="tokenize($types, '\|')" />
        <xsl:for-each select="$tokens">

            <option type="{tokenize(normalize-space(.), '[^a-zA-Z]')[1]}">
                <xsl:if test="ends-with(normalize-space(.), '[]')">
                    <xsl:attribute name="isArray">true</xsl:attribute>
                </xsl:if>
            </option>

        </xsl:for-each>
    </xsl:template>

    <!-- Type -->
    <xsl:template match="info[matches(text(), '^[a-zA-Z]+[^\[]')]" mode="pass5">
        <xsl:variable name="type" select="tokenize(., '[^a-zA-Z]')[1]" />
        <xsl:attribute name="type">
            <xsl:value-of select="$type" />
        </xsl:attribute>
        <xsl:if test="substring(., string-length($type)+1, 2) = '[]'">
            <xsl:attribute name="isArray">true</xsl:attribute>
        </xsl:if>
    </xsl:template>

    <xsl:template match="param[info[1][contains(text(), 'ptional')]]" mode="pass5">
        <xsl:copy>
            <xsl:attribute name="optional">true</xsl:attribute>
            <xsl:apply-templates mode="pass5" select="node()|@*" />
        </xsl:copy>
    </xsl:template>

    <!-- throw out unparsed Info -->
    <xsl:template match="info" mode="pass5" />

    <!-- generate empty constructor -->
    <xsl:template match="class[not(count(constructor))]" mode="pass5">
        <xsl:copy>
            <xsl:apply-templates mode="pass5" select="@*" />
            <constructor />
            <xsl:apply-templates mode="pass5" />
        </xsl:copy>
    </xsl:template>

    <!-- rename object param to properties -->
    <xsl:template match="param[@type = 'Object']/param" mode="pass6">
        <property>
            <xsl:apply-templates mode="pass6" select="@*" />
            <xsl:apply-templates mode="pass6" />
        </property>
    </xsl:template>

    <!-- rename struct param to properties -->
    <xsl:template match="struct/param" mode="pass6">
        <property>
            <xsl:apply-templates mode="pass6" select="@*" />
            <xsl:apply-templates mode="pass6" />
        </property>
    </xsl:template>
</xsl:stylesheet>