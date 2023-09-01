@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.safeargs.kotlin)
}

android {
    namespace = "com.waliahimanshu.android.feature.photos"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        consumerProguardFiles("consumer-rules.pro")
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":model"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation)
    implementation(libs.material)

    implementation(libs.hilt.android)
    implementation(libs.glide)
    implementation(libs.paging)
    kapt(libs.hilt.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kfixture)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
}
