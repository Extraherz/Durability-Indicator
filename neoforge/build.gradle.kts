
plugins {
    `java-library`
    id("net.neoforged.moddev") version "2.0.148"
}

base {
    archivesName.set("durabilityindicator-neoforge")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    withSourcesJar()
}

neoForge {
    version = providers.gradleProperty("neoforge_version").get()

    runs {
        create("client") {
            client()
        }
    }

    mods {
        create("durabilityindicator") {
            sourceSet(sourceSets.main.get())
        }
    }
}

dependencies {
    implementation(project(":common"))
}

tasks.processResources {
    val replaceProperties = mapOf(
        "mod_version" to project.version.toString(),
        "neoforge_version" to providers.gradleProperty("neoforge_version").get()
    )

    inputs.properties(replaceProperties)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(replaceProperties)
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

