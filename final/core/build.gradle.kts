val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    api("io.github.microutils:kotlin-logging-jvm:3.0.5")

    // model mapper
    implementation("org.modelmapper:modelmapper:3.1.1")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}