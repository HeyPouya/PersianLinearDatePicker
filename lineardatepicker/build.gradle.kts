plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}
android {
    compileSdk = 33
    defaultConfig {
        minSdk = 16
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.pouyaheydari.lineardatepicker"
    buildFeatures {
        viewBinding = true
    }
    publishing {
        multipleVariants("release") {
            allVariants()
        }
    }
}

dependencies {
    //Support
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")

}
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.pouyaheydari.lineardatepicker"
            artifactId = "lineardatepicker"
            version = "1.4.0"
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
