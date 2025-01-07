pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "Deal"
enableFeaturePreview("VERSION_CATALOGS")
include (":app")
include (":core")
include (":core:data")
include (":core:domain")
include (":core:network")
include(":core:designsystem")
include (":features")
include(":features:splash")
include(":features:login")
include(":core:navigation")
include(":features:boarding")
include(":features:register")
include(":core:model")
include(":features:home")
include(":features:unit_test")
