<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:custom="http://www.org" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    exclude-result-prefixes="custom" version="2.0">

    <!-- settings -->
    <xsl:output indent="yes" method="xml" />
    <xsl:strip-space elements="*" />

    <!-- copy everything -->
    <xsl:template match="@*|node()" mode="pass1_prepare">
        <xsl:copy>
            <xsl:apply-templates mode="pass1_prepare" select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="@*|node()" mode="pass2_find_sections">
        <xsl:copy>
            <xsl:apply-templates mode="pass2_find_sections" select="@*|node()" />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="@*|node()" mode="pass3_properties_methods_events">
        <xsl:copy>
            <xsl:apply-templates mode="pass3_properties_methods_events" select="@*|node()" />
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
    <xsl:template match="@*|node()" mode="pass7_cleanup">
        <xsl:copy>
            <xsl:apply-templates mode="pass7_cleanup" select="@*|node()" />
        </xsl:copy>
    </xsl:template>

    <xsl:template match="/">
        <xsl:variable name="out1">
            <xsl:apply-templates mode="pass1_prepare" />
        </xsl:variable>
        <xsl:variable name="out2">
            <xsl:apply-templates mode="pass2_find_sections" select="$out1" />
        </xsl:variable>
        <xsl:variable name="out3">
            <xsl:apply-templates mode="pass3_properties_methods_events" select="$out2" />
        </xsl:variable>
        <xsl:variable name="out4">
            <xsl:apply-templates mode="pass4" select="$out3" />
        </xsl:variable>
        <xsl:variable name="out5">
            <xsl:apply-templates mode="pass5" select="$out4" />
        </xsl:variable>
        <xsl:variable name="out6">
            <xsl:apply-templates mode="pass6" select="$out5" />
        </xsl:variable>
        <xsl:variable name="out7">
            <xsl:apply-templates mode="pass7_cleanup" select="$out6" />
        </xsl:variable>
        <file>
            <xsl:copy-of select="$out7//*[self::class|self::object|self::struct]" />
        </file>
    </xsl:template>

    <!-- Pass 1: prepare -->

    <xsl:template match="sect1 | sect2 | sect3 | sect4" mode="pass1_prepare">
        <section>
            <xsl:apply-templates mode="pass1_prepare" select="@*|node()" />
        </section>
    </xsl:template>

    <!-- pull title up -->
    <xsl:template match="*[self::sect1|self::sect2|self::sect3|self::sect4]/*[1][self::title]" mode="pass1_prepare">
        <xsl:attribute name="title">
            <xsl:value-of select="." />
        </xsl:attribute>
    </xsl:template>

    <!-- Pass 2: find sections -->

    <!-- class -->
    <xsl:template match="section[starts-with(@title, 'Class:')]" mode="pass2_find_sections">
        <class title="{substring(@title, 8)}">
            <xsl:apply-templates mode="pass2_find_sections" />
        </class>
    </xsl:template>

    <!-- struct -->
    <xsl:template match="section[ends-with(@title, ' Object')]" mode="pass2_find_sections">
        <struct title="{substring-before(@title, ' Object')}">
            <xsl:apply-templates mode="pass2_find_sections" />
        </struct>
    </xsl:template>

    <!-- object -->
    <xsl:template match="section[count(*[@id='events' or @id='methods']) > 0]" mode="pass2_find_sections">
        <object title="{@title}">
            <xsl:apply-templates mode="pass2_find_sections" />
        </object>
    </xsl:template>

    <!-- instance events -->
    <xsl:template match="section[@id='instance-events']" mode="pass2_find_sections">
        <events>
            <xsl:apply-templates mode="pass2_find_sections" />
        </events>
    </xsl:template>

    <!-- events -->
    <xsl:template match="section[@id='events']" mode="pass2_find_sections">
        <events>
            <xsl:apply-templates mode="pass2_find_sections" />
        </events>
    </xsl:template>

    <!-- instance methods -->
    <xsl:template match="section[@id='instance-methods']" mode="pass2_find_sections">
        <methods type="instance">
            <xsl:apply-templates mode="pass2_find_sections" />
        </methods>
    </xsl:template>

    <!-- static methods -->
    <xsl:template match="section[@id='static-methods']" mode="pass2_find_sections">
        <methods type="static">
            <xsl:apply-templates mode="pass2_find_sections" />
        </methods>
    </xsl:template>

    <!-- methods -->
    <xsl:template match="section[@id='methods']" mode="pass2_find_sections">
        <methods>
            <xsl:apply-templates mode="pass2_find_sections" />
        </methods>
    </xsl:template>

    <!-- single method-->
    <xsl:template match="section[ends-with(@id, 'methods')]/section" mode="pass2_find_sections">
        <method>
            <xsl:apply-templates mode="pass2_find_sections" select="@*|node()" />
        </method>
    </xsl:template>

    <!-- instance properties section -->
    <xsl:template match="section[@id='instance-properties']" mode="pass2_find_sections">
        <properties type="instance">
            <xsl:apply-templates mode="pass2_find_sections" />
        </properties>
    </xsl:template>

    <!-- single property -->
    <xsl:template match="section[ends-with(@id, 'properties')]/section" mode="pass2_find_sections">
        <property>
            <xsl:apply-templates mode="pass2_find_sections" select="@*|node()" />
        </property>
    </xsl:template>

    <!-- constructor section -->
    <xsl:template match="section[starts-with(@id, 'new-')]" mode="pass2_find_sections">
        <constructor>
            <xsl:apply-templates mode="pass2_find_sections" />
        </constructor>
    </xsl:template>

    <!-- Pass 3: find structure of properties, methods, events -->

    <!-- method name and description -->
    <xsl:template match="method" mode="pass3_properties_methods_events">
        <xsl:variable name="nameWithoutParen" select="tokenize(@title, '\(')[1]" />
        <xsl:variable name="nameTokens" select="tokenize($nameWithoutParen, '\.')[position() != 1]" />
        <method name="{string-join($nameTokens, '.')}">
            <xsl:apply-templates mode="pass3_properties_methods_events" />

            <!-- description -->
            <description>
                <xsl:copy-of select="node()" />
            </description>
        </method>
    </xsl:template>

    <!-- property name and description -->
    <xsl:template match="property" mode="pass3_properties_methods_events">
        <xsl:variable name="nameWithoutParen" select="tokenize(@title, '\(')[1]" />
        <xsl:variable name="nameTokens" select="tokenize($nameWithoutParen, '\.')[position() != 1]" />
        <property name="{string-join($nameTokens, '.')}">
            <!-- type -->
            <info>
                <xsl:value-of select="para[1]/literal[1]" />
            </info>

            <!-- description -->
            <description>
                <xsl:copy-of select="node()" />
            </description>
        </property>
    </xsl:template>

    <!-- collapse itemizedlist -->
    <xsl:template match="itemizedlist[1]" mode="pass3_properties_methods_events">
        <xsl:apply-templates mode="pass3_properties_methods_events" />
    </xsl:template>

    <!-- find param -->
    <xsl:template match="listitem" mode="pass3_properties_methods_events">

        <param name="{para/literal[1]}">
            <!-- type -->
            <info>
                <xsl:copy-of select="para/literal[1]/following-sibling::node()" />
            </info>

            <!-- copy content of of following list if present-->
            <xsl:apply-templates mode="pass3_properties_methods_events" select="*[2][self::itemizedlist]" />

            <!--description -->
            <description>
                <para>
                    <xsl:copy-of select="substring-after(para, '-')" />
                </para>
            </description>
        </param>
    </xsl:template>

    <!-- find return info (first node paragraph that begins with Return -->
    <xsl:template match="method/para[node()[1][self::text()][matches(., '^\s*Returns')]][1]"
        mode="pass3_properties_methods_events">
        <returns>
            <!-- type info (content of paragraph) -->
            <info>
                <xsl:value-of select="substring(normalize-space(.), 8)" />
            </info>

            <!-- copy content of of following list if present-->
            <xsl:apply-templates mode="pass3_properties_methods_events"
                select="following-sibling::*[1][self::itemizedlist]" />

            <!-- copy description = everything after the first literal, which contains the type -->
            <description>
                <para>
                    <xsl:copy-of select="substring-after(., '-')" />
                </para>
            </description>
        </returns>
    </xsl:template>

    <!--Pass 4: parameters -->
    <xsl:template match="struct/*[not(self::param)]" mode="pass4" />
    <xsl:template match="class/*[not(self::constructor|self::methods|self::properties)]" mode="pass4" />
    <xsl:template match="object/*[not(self::class|self::constructor|self::methods|self::properties)]" mode="pass4" />
    <xsl:template match="constructor/*[not(self::param)]" mode="pass4" />
    <xsl:template match="methods/*[not(self::method)]" mode="pass4" />
    <xsl:template match="method/*[not(self::param|self::returns|self::description)]" mode="pass4" />
    <xsl:template match="properties/*[not(self::property)]" mode="pass4" />
    <xsl:template match="events" mode="pass4" />

    <xsl:template match="info/node()[not(self::text())]" mode="pass4">
        <xsl:value-of select="." />
    </xsl:template>

    <!-- fix vararg name -->
    <xsl:template match="param[starts-with(@name, '...')]" mode="pass4">
        <param name="{substring-after(@name, '...')}" vararg="true">
            <xsl:apply-templates mode="pass4" />
        </param>
    </xsl:template>

    <!-- description: delete return paragraph and list after return paragraph of description -->
    <xsl:template match="method/description/para[matches(text()[1], '^\s*Returns')]" mode="pass4" priority="1" />
    <xsl:template
        match="method/description/itemizedlist[preceding-sibling::*[1][self::para][matches(text()[1], '^\s*Returns')]]"
        mode="pass4" priority="1" />

    <!-- description: normalize spaces: -->
    <xsl:template match="//description//para" mode="pass4">
        <para>
            <xsl:value-of select="normalize-space(.)" />
        </para>
    </xsl:template>

    <!-- description: delete parameters -->
    <xsl:template match="method/description/node()[1][self::itemizedlist]" mode="pass4" priority="3" />

    <!-- description: rename itemizedlist to list -->
    <xsl:template match="//description//itemizedlist" mode="pass4" priority="2">
        <list>
            <xsl:apply-templates mode="pass4" />
        </list>
    </xsl:template>

    <!-- description: rename listitem to item -->
    <xsl:template match="//description//listitem" mode="pass4">
        <item>
            <xsl:apply-templates mode="pass4" />
        </item>
    </xsl:template>

    <!-- Pass 5: NORMALIZE SPACES -->
    <xsl:template match="info/text()" mode="pass5">
        <xsl:value-of select="normalize-space(.)" />
    </xsl:template>

    <!-- Pass 6: parse parameter types -->

    <!-- Union (..|..) -->
    <xsl:template match="info[matches(text(), '^\([a-zA-Z\s\|\[\]]+\)')]" mode="pass6">
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
    <xsl:template match="info[matches(text(), '^[a-zA-Z]+[^\[]')]" mode="pass6">
        <xsl:variable name="type" select="tokenize(., '[^a-zA-Z]')[1]" />
        <xsl:attribute name="type">
            <xsl:value-of select="$type" />
        </xsl:attribute>
        <xsl:if test="substring(., string-length($type)+1, 2) = '[]'">
            <xsl:attribute name="isArray">true</xsl:attribute>
        </xsl:if>
    </xsl:template>

    <!-- is optional? -->
    <xsl:template match="param[info[1][contains(text(), 'ptional')]]" mode="pass6">
        <xsl:copy>
            <xsl:attribute name="optional">true</xsl:attribute>
            <xsl:apply-templates mode="pass6" select="node()|@*" />
        </xsl:copy>
    </xsl:template>

    <!-- Pass 7: cleanup -->

    <!-- throw out unparsed Info -->
    <xsl:template match="info" mode="pass7_cleanup" />

    <!-- generate empty constructor -->
    <xsl:template match="class[not(count(constructor))]" mode="pass7_cleanup">
        <xsl:copy>
            <xsl:apply-templates mode="pass7_cleanup" select="@*" />
            <constructor />
            <xsl:apply-templates mode="pass7_cleanup" />
        </xsl:copy>
    </xsl:template>

    <!-- rename object children to property -->
    <xsl:template match="param[@type = 'Object']/param" mode="pass7_cleanup">
        <property>
            <xsl:apply-templates mode="pass7_cleanup" select="@*" />
            <xsl:apply-templates mode="pass7_cleanup" />
        </property>
    </xsl:template>

    <!-- rename struct param to property -->
    <xsl:template match="struct/param" mode="pass7_cleanup">
        <property>
            <xsl:apply-templates mode="pass7_cleanup" select="@*" />
            <xsl:apply-templates mode="pass7_cleanup" />
        </property>
    </xsl:template>
</xsl:stylesheet>