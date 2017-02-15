# Kotlin bindings for electron

Some `xsl` transformatiosn to generate Kotlin API from the electron docs. Output is found here: [generated-api/jsapi/electron/](https://github.com/fab1an/kotlin-electron-api/tree/master/generated-api/jsapi/electron)

The markdown is first converted to an [intermediate format](https://github.com/fab1an/kotlin-electron-api/tree/master/build/api_docbook) and then to Kotlin. This intermediate format could be used to generate API for other docs.

#### Building:
* pandoc must be installed and on the path
* put electron api docs somewhere and adjust path in `gradle.properties`
* ```./gradlew electronAPI```

##### Steps taken internally:

1. fix list indentation in markdown
2. use pandoc to create docbook from markdown, output: `build/api_docbook/*.source.xml`
3. use `saxon`/xsl to normalize xml, output: `build/api_docbook/*.normalized.xml`
4. use `saxon`/xsl to generate kotlin, output: `generated-api/jsapi/electron/*.kt`
