// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    application
    id("com.android.library") version "7.1.3" apply false
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.serialization") version "1.7.0"
    `java-library`
}
