apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "implementation"(project(Modules.commonUI))
    "implementation"(project(Modules.core))
}
