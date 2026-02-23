plugins {
    id("java")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

group = "ru.mika050916"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        name = "LuminiaDev"
        url = uri("https://repo.luminiadev.com/snapshots")
    }
}

dependencies {
    compileOnly("com.koshakmine:Lumi:1.5.0-SNAPSHOT")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.processResources {
    val props = mapOf("version" to project.version)
    filesMatching("plugin.yml") {
        expand(props)
    }
    inputs.properties(props)
}