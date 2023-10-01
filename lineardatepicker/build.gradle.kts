plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}
android {
    compileSdk = 34
    defaultConfig {
        minSdk = 16
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.pouyaheydari.lineardatepicker"
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.pouyaheydari.lineardatepicker"
            artifactId = "lineardatepicker"
            version = "1.5.0"
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
