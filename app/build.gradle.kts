plugins {
    id (Dependencies.Plugins.application)
    id (Dependencies.Plugins.kotlinAndroid)
    id (Dependencies.Plugins.kotlinKapt)
    id (Dependencies.Plugins.relay) version Dependencies.Plugins.relayVersion
}

android {
    namespace = Config.appName
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appName
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Core.Version.composeCompiler
    }
    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }

    configurations {
        create("cleanedAnnotations")
        implementation {
            exclude(group = "com.intellij", module = "annotations")
        }
    }
}

dependencies {
    implementation(Dependencies.Core.coreKts)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.Core.androidMaterial)
    implementation(Dependencies.Core.activityCompose)

    implementation(Dependencies.Compose.uiTooling)
    implementation(Dependencies.Compose.uiToolingPreview)
//    implementation(Dependencies.Compose.material)
//    implementation(Dependencies.Compose.materialIconsExtended)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.composeMaterialWindow)

    implementation(Dependencies.Lifecycle.viewModelKtx)
    implementation(Dependencies.Lifecycle.runtimeKtx)
    implementation(Dependencies.Lifecycle.extensions)

    implementation(Dependencies.Navigation.navigationCompose)
    implementation(Dependencies.Navigation.lifecycleViewModelCompose)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitConverterGson)

    implementation(Dependencies.Coil.coilCompose)

    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Room.compiler)

    implementation(Dependencies.Koin.koin)
    implementation(Dependencies.Koin.compose)

    testImplementation(Dependencies.Test.junit)

    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.roboeletric)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.okHttp3MockWebServer)
    androidTestImplementation(Dependencies.Test.androidJunit)
    androidTestImplementation(Dependencies.Test.espressoCore)
    androidTestImplementation(Dependencies.Test.composeJunit)
}
