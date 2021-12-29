apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    // modules
    "implementation"(project(Modules.commonUI))
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.networking))
    "implementation"(project(Modules.otpView))

    // tests
    "testImplementation"(Tests.mockk)
    "testImplementation"(Tests.coroutines)
    "testImplementation"(Tests.truth)
}
