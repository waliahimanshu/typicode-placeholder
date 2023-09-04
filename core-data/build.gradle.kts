@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)

}

android {
    namespace = "com.waliahimanshu.android.core.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":model"))
    implementation(libs.square.retrofit)
    implementation(libs.square.okhttp.interceptor)
    implementation(libs.square.retrofit.converter)
    implementation(libs.square.moshi)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kfixture)
    testImplementation(libs.kotlinx.coroutines.test)
}
