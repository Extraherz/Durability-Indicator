
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

sourceSets {
    named("main") {
        java.srcDir(rootProject.file("config/src/main/java"))
        resources.srcDir(rootProject.file("config/src/main/resources"))
    }
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


repositories {
    maven("https://maven.midnightdust.eu/releases")
}



dependencies {
    implementation(project(":common"))

    implementation(
        "eu.midnightdust:midnightlib:" +
                "${property("midnightlib_version")}+" +
                "${property("minecraft_version")}-neoforge"
    )
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

