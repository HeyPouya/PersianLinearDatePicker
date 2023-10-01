@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
}
android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.pouyaheydari.lineardatepicker"
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
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    //Support
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintLayout)

    //Test
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.pouyaheydari.lineardatepicker"
            artifactId = "lineardatepicker"
            version = libs.versions.appVersion.get()
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
