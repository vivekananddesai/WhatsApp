// Manifest version information!

plugins {
  id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
  id(BuildPlugins.KOTLIN_KAPT)
  id("org.jlleitschuh.gradle.ktlint")
}

subprojects {
  apply {
    from("variants.gradle.kts")
  }
}

// def preDexEnabled = "true" == System.getProperty("pre-dex", "true")

android {
  compileSdkVersion(ProjectProperties.COMPILE_SDK)

  defaultConfig {
    applicationId = (ProjectProperties.APPLICATION_ID)
    minSdkVersion(ProjectProperties.MIN_SDK)
    targetSdkVersion(ProjectProperties.TARGET_SDK)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
  }

  packagingOptions {
    exclude("META-INF/LICENSE.txt")
    exclude("META-INF/NOTICE.txt")
    exclude("LICENSE.txt")
  }

  dexOptions {
    preDexLibraries = true
  }

  buildFeatures {
    dataBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

// Required for annotation processing plugins like Dagger
kapt {
  generateStubs = true
  correctErrorTypes = true
}

dependencies {

  implementation(project(":data"))
  implementation(project(":domain"))

  /*Kotlin*/
  api(Lib.Kotlin.KT_STD)
  api(Lib.Kotlin.KTX_CORE)

  /* Android Designing and layout */
  implementation(Lib.Android.CONSTRAINT_LAYOUT)
  implementation(Lib.Android.FRAGMENT)
  implementation(Lib.Android.MATERIAL_DESIGN)
  implementation(Lib.Android.LIFECYCLE_VIEWMODEL_KTX)

  /*DI*/
  implementation(Lib.Di.DAGGER)
  implementation(Lib.Di.DAGGER_ANDROID)
  kapt(Lib.Di.DAGGER_PROCESSOR)
  kapt(Lib.Di.DAGGER_COMPILER)
  kaptTest(Lib.Di.DAGGER_COMPILER)

  /* Logger */
  api(Lib.Logger.TIMBER)

  /* Async */
  api(Lib.Async.COROUTINES)
  api(Lib.Async.COROUTINES_ANDROID)

  /*Testing*/
  testImplementation(TestLib.JUNIT)
  testImplementation(TestLib.CORE_TEST)
  testImplementation(TestLib.ANDROID_JUNIT)
  testImplementation(TestLib.ARCH_CORE)
  testImplementation(TestLib.MOCK_WEB_SERVER)
  testImplementation(TestLib.ROBO_ELECTRIC)
  testImplementation(TestLib.COROUTINES)
  testImplementation(TestLib.MOCKITO_CORE)
}
