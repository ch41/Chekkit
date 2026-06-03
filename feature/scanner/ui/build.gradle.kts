plugins {
    id("aiapplication.ui.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.aiapplication.feature.scanner.ui"
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.camerax.core)
    implementation(libs.camerax.camera2)
    implementation(libs.camerax.lifecycle)
    implementation(libs.camerax.view)
    implementation(libs.camerax.mlkit.vision)

    implementation("com.google.mlkit:text-recognition:16.0.1")

    implementation(project(":core:common"))
    implementation(project(":core:presentation"))
    implementation(project(":core:design"))

}