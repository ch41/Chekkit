plugins {
    id("aiapplication.android.library")
}

android {
    namespace = "com.aiapplication.feature.scanner.data"
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.ktx)
    implementation("cz.adaptech:tesseract4android:4.9.0") {
        exclude(group = "cz.adaptech.tesseract4android", module = "tesseract4android-openmp")
    }

    implementation(project(":core:common"))
    implementation(project(":feature:scanner:domain"))
}