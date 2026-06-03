plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:9.2.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.21")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "aiapplication.android.app"
            implementationClass = "com.aiapplication.conventions.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "aiapplication.android.library"
            implementationClass = "com.aiapplication.conventions.AndroidLibraryConventionPlugin"
        }
        register("uiLibrary") {
            id = "aiapplication.ui.library"
            implementationClass = "com.aiapplication.conventions.UILibraryConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "aiapplication.kotlin.library"
            implementationClass = "com.aiapplication.conventions.KotlinLibraryConventionPlugin"
        }
    }
}