plugins {
    id("library_android")
}

dependencies {
    api(TestDependencies.ASSERTJ)
    api(TestDependencies.ROBOELECTRIC)
    api(TestDependencies.ROOM)
    api(TestDependencies.CORE)
    api(TestDependencies.ARCH_CORE)
    api(TestDependencies.RULES)
    api(TestDependencies.RUNNER)
    api(TestDependencies.COROUTINES_TEST)
    api(TestDependencies.FRAGMENT_TEST)
    api(TestDependencies.EXT)
    api(TestDependencies.MOCK_WEB_SERVER)
    api(TestDependencies.MOCKK)
}