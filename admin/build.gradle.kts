plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
}

dependencies {
    //core 모듈 의존성 추가
    api(project(":core"))

    //Web 계층 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")

    // swagger
    implementation("org.springdoc:springdoc-openapi-ui:1.6.15")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.15")

    // h2
    runtimeOnly("com.h2database:h2:2.1.214")

    // mysql driver
    runtimeOnly("mysql:mysql-connector-java")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}