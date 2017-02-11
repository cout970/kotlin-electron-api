# Kotlin bindings for electron

### Usage
* pandoc must be installed
* put electron api docs somewhere and change path in `gradle.properties`
* ```./gradlew electronAPI```


### Steps taken internally:

1. fix list indentation in markdown
2. use pandoc to create docbook from markdown, output: `build/api_docbook/*.source.xml`
3. use `saxon`/xsl to normalize xml, output: `build/api_docbook/*.normalized.xml`
4. use `saxon`/xsl to generate kotlin, output: `generated-api/jsapi/electron/*.kt`
