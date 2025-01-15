include(":module1")
include(":module2")
include(":library")

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "gradle-settings-libsversions-bug"

plugins {
    // Deleting any of these two lines fixes the issue
    id("com.gradle.develocity") version "3.19"
    id("com.autonomousapps.build-health") version "2.7.0"

    id("com.android.application") version "8.7.0" apply false
    id("com.android.library") version "8.7.0" apply false
    id("org.jetbrains.kotlin.jvm") version "2.1.0" apply false
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
        termsOfUseAgree = "yes"
        publishing.onlyIf { false }
    }
}
