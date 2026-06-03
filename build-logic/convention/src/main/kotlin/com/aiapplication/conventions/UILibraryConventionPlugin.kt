package com.aiapplication.conventions

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class UILibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("aiapplication.android.library")

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    compose = true
                }
            }

            dependencies {
                add("implementation", platform("androidx.compose:compose-bom:2025.02.00"))
                add("implementation", "androidx.compose.runtime:runtime")
                add("implementation", "androidx.compose.ui:ui")
                add("implementation", "androidx.compose.ui:ui-graphics")
                add("implementation", "androidx.compose.material3:material3")
                add("implementation", "androidx.compose.ui:ui-tooling-preview")
                add("debugImplementation", "androidx.compose.ui:ui-tooling")

                add("implementation", "io.insert-koin:koin-android:4.2.1")
                add("implementation", "io.insert-koin:koin-compose:4.2.1")
                add("implementation", "io.insert-koin:koin-androidx-compose:4.2.1")
            }
        }
    }
}
