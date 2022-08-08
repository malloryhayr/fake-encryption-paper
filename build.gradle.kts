import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "1.3.6"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "dev.igalaxy"
version = "1.0.1"
description = "Hides the annoying \"Chat messages can't be verified\" popup on Vanilla clients even if chat encryption isn't forced "

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    paperDevBundle("1.19.1-R0.1-SNAPSHOT")
    implementation("net.axay:kspigot:1.19.0")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.0.0-SNAPSHOT")
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

tasks {

    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    shadowJar { }
}

bukkit {
    name = "FakeEncryption"
    description = description
    main = "dev.igalaxy.fakeencryption.FakeEncryption"
    version = version
    apiVersion = "1.19"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "fake-encryption"
            from(components["java"])
        }
    }
}