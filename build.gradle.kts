// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.gms:google-services:4.4.0")
    }
    repositories {
        mavenCentral()
    }

}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.android.test") version "8.1.0" apply false
}