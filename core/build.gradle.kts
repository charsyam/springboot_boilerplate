plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
}


val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    api("io.github.microutils:kotlin-logging-jvm:2.0.11")

    // swagger
    implementation("org.springdoc:springdoc-openapi-ui:1.6.12")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.12")

    // model mapper
    implementation("org.modelmapper:modelmapper:2.4.4")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}