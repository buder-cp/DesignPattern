package com.example.plugindemo

import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginDemo implements Plugin<Project>{

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('plugindemo', ExtensionDemo)
        project.afterEvaluate {
            println "watch ${extension.name}"
        }
    }
}
