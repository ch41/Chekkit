plugins {
    id("aiapplication.ui.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.feature.receipts.ui"
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":core:design"))
    implementation(project(":core:presentation"))
    implementation(project(":core:common"))
    implementation(project(":feature:receipts:domain"))

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.koin.androidx.compose)
}
