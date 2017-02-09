# Kotlin bindings for electron (generator)

## Example for app.md

1. fix list indentation:

```
cat app.md | sed 's/^    \\*/        */g' | sed 's/^  \\*/     */g' > app.fixed.md
```

2. generate docbook.xml: 

```
cat app.fixed.md | pandoc -t docbook > app.xml
```

3. normalize: 

 ```
 saxon app.xml step1normalize.xsl > app.normalized.xsl
 ```

4. generate kotlin: 

 ```
 saxon app.normalized.xml step2KotlinOutput.xsl > app.kt`
 ```
