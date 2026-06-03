plugins {
    id("aiapplication.android.app")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.aiapplication.app"

    defaultConfig {
        applicationId = "com.aiapplication.app"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.androidx.navigation.compose)

    implementation(project(":core:design"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":core:di"))
    implementation(project(":feature:scanner:data"))
}