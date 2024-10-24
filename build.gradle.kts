// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.jetbrainsKotlinSerialization)
    alias(libs.plugins.googleDevtoolsKsp) apply false
    alias(libs.plugins.googleServices)
    alias(libs.plugins.jetbrainsKotlinParcelize) apply false

}
