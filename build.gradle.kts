plugins {
    base
}

group = "de.extraherz.durabilityindicator"
version = providers.gradleProperty("mod_version").get()

subprojects {
    group = rootProject.group
    version = rootProject.version
}

tasks.wrapper {
    gradleVersion = "9.7.1"
    distributionType = Wrapper.DistributionType.BIN
}
