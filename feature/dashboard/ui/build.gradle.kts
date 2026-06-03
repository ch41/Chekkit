plugins {
    id("aiapplication.ui.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.aiapplication.feature.dashboard.ui"
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":core:design"))
    implementation(project(":core:presentation"))
    implementation(project(":core:common"))

}