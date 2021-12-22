apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "api"(Networking.retrofit)
    "api"(Networking.moshi)
    "api"(Networking.loggingInterceptor)
    "api"(Networking.moshiConverter)
    "kapt"(Networking.moshiCodeGen)
}
