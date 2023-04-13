plugins {
    java
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")

    maven("https://oss.sonatype.org/content/groups/public/")

    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")

    maven("https://ci.ender.zone/plugin/repository/everything/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.2-R0.1-SNAPSHOT")
    compileOnly("net.milkbowl.vault:VaultAPI:1.7")
    compileOnly("me.clip:placeholderapi:2.11.3")
    compileOnly("org.jetbrains:annotations:24.0.1")
}

group = "nl.chimpgamer"
version = "1.0.5"
description = "UltimateShout"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    processResources {
        expand("version" to project.version)
    }

    compileJava {
        options.encoding = "UTF-8"
    }
}