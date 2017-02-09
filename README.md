# Kotlin bindings for electron (generator)

## Example for app.md
1. generate docbook.xml: 

 ```
 cat app.md | sed 's/^    \\*/        */g' | sed 's/^  \\*/     */g' | pandoc -t docbook > app.xml
 ```
  
2. normalize: `saxon app.xml step1normalize.xsl > app.normalized.xsl`
3. generate kotlin:  `saxon app.normalized.xml step2KotlinOutput.xsl > app.kt`
