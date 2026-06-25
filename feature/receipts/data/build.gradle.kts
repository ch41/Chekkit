plugins {
    id("aiapplication.android.library")
}

android {
    namespace = "com.aiapplication.feature.receipts.data"
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.ktx)

    implementation(project(":core:common"))
    implementation(project(":feature:receipts:domain"))
}