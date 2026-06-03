plugins {
    id("aiapplication.android.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.aiapplication.di"
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(project(":core:presentation"))

    implementation(project(":feature:dashboard:ui"))
    implementation(project(":feature:dashboard:data"))
    implementation(project(":feature:dashboard:domain"))

    implementation(project(":feature:scanner:ui"))
    implementation(project(":feature:scanner:data"))
    implementation(project(":feature:scanner:domain"))
}