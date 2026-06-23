plugins {
    id("aiapplication.android.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.aiapplication.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(project(":core:design"))
    implementation(project(":feature:dashboard:ui"))
    implementation(project(":feature:scanner:ui"))
}