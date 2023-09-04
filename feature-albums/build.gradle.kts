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
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        consumerProguardFiles("consumer-rules.pro")
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
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
    implementation(libs.coil.compose)
    implementation(libs.paging)
    kapt(libs.hilt.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.android.compiler)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.paging)
    implementation(libs.androidx.compose.foundation)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kfixture)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
}
