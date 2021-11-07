apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "implementation"(project(Modules.commonUI))
    "implementation"(Accompanist.Pager)
    "implementation"(Accompanist.PagerIndicator)
}
