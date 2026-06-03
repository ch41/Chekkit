pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
      /*  {
            maven {
                url = uri("https://jitpack.io")
            }
        }*/
    }
}

rootProject.name = "Chekkit"

includeBuild("build-logic")
include(":app")
include(":core:design")
include(":core:di")
include(":core:navigation")
include(":core:domain")
include(":feature:dashboard:ui")
include(":feature:dashboard:domain")
include(":feature:dashboard:data")
include(":core:common")
include(":feature:scanner:ui")
include(":feature:scanner:data")
include(":feature:scanner:domain")
include(":core:presentation")
