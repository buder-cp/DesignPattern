package com.example.plugindemo

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginDemo implements Plugin<Project>{

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('plugindemo', ExtensionDemo)
        project.afterEvaluate {
            println "watch ${extension.name}"
        }

        def transform = new TransformDemo()
        def baseExtension = project.extensions.getByType(BaseExtension)
//        println "bootClassPath = ${baseExtension.bootClasspath} "
        baseExtension.registerTransform(transform)
    }
}
