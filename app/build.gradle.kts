plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.proyecto_inventario"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proyecto_inventario"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding{
        enable = true
    }
    viewBinding{
        enable = true
    }
    buildFeatures {
        viewBinding=true
        }
    packagingOptions {
        exclude ("META-INF/DEPENDENCIES")
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(files("libs\\jtds-1.3.1.jar"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.4")
    implementation ("androidx.activity:activity-ktx:1.8.0")
//    implementation ("org.apache.pdfbox:pdfbox:2.0.25")

    implementation ("com.itextpdf:itextpdf:5.5.13")




    //Importando las librería para cifrar contraseñas utilizando el algoritmo bcrypt
    implementation ("org.mindrot:jbcrypt:0.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}