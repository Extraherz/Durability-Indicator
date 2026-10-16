
plugins {
    id("net.fabricmc.fabric-loom") version "1.18.4"
}

base {
    archivesName.set("durabilityindicator-fabric")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    withSourcesJar()
}

sourceSets {
    named("main") {
        java.srcDir(rootProject.file("config/src/main/java"))
        resources.srcDir(rootProject.file("config/src/main/resources"))
    }
}

repositories {
    maven("https://maven.midnightdust.eu/releases")
    maven("https://maven.terraformersmc.com/releases/")
}

dependencies {
    minecraft(
        "com.mojang:minecraft:${property("minecraft_version")}"
    )

    implementation(
        "net.fabricmc:fabric-loader:${property("fabric_loader_version")}"
    )

    implementation(
        "net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}"
    )

    implementation(project(":common"))


    implementation(
        "eu.midnightdust:midnightlib:" +
                "${property("midnightlib_version")}+" +
                "${property("minecraft_version")}-fabric"
    )

}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version.toString())
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(25)
}


val commonOutput = project(":common")
    .extensions.getByType<SourceSetContainer>()["main"].output

tasks.jar {
    from(commonOutput)

    from(rootProject.file("LICENSE")) {
        rename { "LICENSE_${project.name}" }
    }
}

