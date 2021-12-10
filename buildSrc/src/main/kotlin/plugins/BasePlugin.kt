package plugins

import Versions
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

internal interface BasePlugin {
    fun DependencyHandler.implementation(depName: Any) {
        add("implementation", depName)
    }

    fun DependencyHandler.kapt(depName: String) {
        add("kapt", depName)
    }

    /**
     * Configures the [Java][org.gradle.api.plugins.JavaPluginExtension] extension.
     */
    fun Project.applyJavaExtension() {
        val extension = project.extensions.findByType(JavaPluginExtension::class.java) ?: return
        extension.apply {
            sourceCompatibility = Versions.JAVA
            targetCompatibility = Versions.JAVA
        }
    }

    fun Project.importExternalLibs(block: (DependencyHandlerScope) -> Unit) {
        dependencies {
            block.invoke(this)
        }
    }

    fun Project.applyPlugins(block: (PluginContainer) -> Unit) {
        block(project.plugins)
    }
}
