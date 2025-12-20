plugins {
    kotlin("jvm") version "2.0.21" // Проверьте актуальную версию Kotlin
    id("io.ktor.plugin") version "3.0.0" // Проверьте актуальную версию Ktor
    id("com.github.johnrengelman.shadow") version "8.1.1" // Добавьте этот плагин для fat JAR
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "com.example.ApplicationKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.11") // Убедитесь, что версия актуальная
    implementation("io.ktor:ktor-server-netty:2.3.11")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("io.ktor:ktor-server-config-yaml:2.3.11")
    
    testImplementation("io.ktor:ktor-server-test-host:2.3.11")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.24")
}

// Конфигурация для создания fat JAR с помощью shadowJar
tasks.shadowJar {
    manifest {
        attributes(
            "Main-Class" to "com.example.ApplicationKt"
        )
    }
    // Убедитесь, что задача assemble зависит от shadowJar
    tasks.assemble.get().dependsOn(tasks.shadowJar)
}
