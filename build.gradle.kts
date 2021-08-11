import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.hiltAndroid)
        classpath(StaticAnalysis.ktlint)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint").version(StaticAnalysis.ktlintVersion)
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    outputColorName.set("RED")

    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
