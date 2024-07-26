import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)

    // mark
    id("org.jetbrains.kotlinx.kover") version "0.8.3" // To set test coverage rule
}

// Mark - test coverage
kover {
    reports {
        verify {
            rule {
                /**
                 * If 10% code is executed in test it should pass, otherwise failed
                 * You can set it 80 if you can write that much unit test code
                 */
                minBound(10)
            }
        }
        filters {  // To get clean report we should filter unwanted packages/classes
            excludes {
                // Entry points
                classes("MainKt") // Desktop
                classes("*.MainActivity") // Android

                // Generated classes and resources
                packages("*.generated.*")
                packages("*.di*") // Dependency injection package

                // Compose related(If you don't do UI testing)
                classes("*ComposableSingletons*")
                annotatedBy("androidx.compose.runtime.Composable")
                annotatedBy("androidx.compose.ui.tooling.preview.Preview")

                classes("CounterKtTest") // For this project this is for UI testing
            }
        }
    }
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        // Mark (More related code in defaultConfig section)
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant { // For android instrumented test
            sourceSetTree.set(KotlinSourceSetTree.test)
            dependencies {
                implementation(libs.core.ktx)
                implementation(libs.compose.ui.test.junit4.android)
                implementation(libs.compose.ui.test.manifest)
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }

        // Mark - 1
        commonTest.dependencies {
            implementation(libs.kotlin.test) // To add library in test
            implementation(kotlin("test-annotations-common")) // To get more annotaions

            implementation(libs.assertk)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.touhidapps.unittestcompose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    // Mark
    // add this, NOTE: it's "isIncludeAndroidResources" not "includeAndroidResources"
    testOptions.unitTests.isIncludeAndroidResources = true

    defaultConfig {
        applicationId = "com.touhidapps.unittestcompose"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        // Mark
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
dependencies {
    testImplementation(libs.junit.jupiter)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.touhidapps.unittestcompose"
            packageVersion = "1.0.0"
        }
    }
}
