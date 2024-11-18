// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21" // Match your Kotlin version
    id("androidx.navigation.safeargs.kotlin") version "2.8.4" apply  false// Use the latest version



}