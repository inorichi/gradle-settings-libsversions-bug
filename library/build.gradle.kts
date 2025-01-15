plugins {
    id("com.android.library")
}

android {
    namespace = "com.example.library"
    compileSdk = 35
}

dependencies {
    implementation(project(":module1"))
    implementation(project(":module2"))
}