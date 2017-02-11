package org.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

class IncrementalTransformTask extends DefaultTask {

    def Closure getCommandLine

    def Closure getTransformedName

    @InputFiles
    def inputFiles

    @OutputDirectory
    def File outputDir

    @TaskAction
    void execute(IncrementalTaskInputs inputs) {
//        if (!inputs.incremental) {
//            println "inputs not incremental -> deleting outdir"
//            project.delete(outputDir.listFiles())
//        }

        inputs.outOfDate { change ->
            def transformedName = getTransformedName(change.file)

            println ": * ${change.file.name} -> $transformedName"
            def targetFile = new File(outputDir, transformedName)
            targetFile.getParentFile().mkdirs()
            project.exec {
                commandLine getCommandLine(change.file)
                standardOutput = new FileOutputStream(targetFile)
            }
        }

        inputs.removed { change ->
            def transformedName = getTransformedName(change.file)
            println ": D ${change.file.name} / $transformedName"
            def targetFile = new File(outputDir, transformedName)
            targetFile.delete()
        }
    }
}