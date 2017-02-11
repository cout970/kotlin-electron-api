package org.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

class IncrementalXSLTTask extends DefaultTask {

    def Closure getTransformedName

    def File xsltFile

    @InputFiles
    def inputFiles

    @OutputDirectory
    def File outputDir

    @TaskAction
    void execute(IncrementalTaskInputs inputs) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl", null)
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new FileInputStream(xsltFile)))

        inputs.outOfDate { change ->
            def transformedName = getTransformedName(change.file)

            println ": * ${change.file.name} -> $transformedName"
            def targetFile = new File(outputDir, transformedName)
            targetFile.getParentFile().mkdirs()

            transformer.transform(new StreamSource(new FileInputStream(change.file)), new StreamResult(targetFile))
        }

        inputs.removed { change ->
            def transformedName = getTransformedName(change.file)
            println ": D ${change.file.name} / $transformedName"
            def targetFile = new File(outputDir, transformedName)
            targetFile.delete()
        }
    }
}