plugins {
    java
    war
    id("io.freefair.lombok") version "6.4.1"
}

group = "com.max"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

object Versions {
    const val springVersion = "5.3.17"
    const val log4jVersion = "2.17.2"
    const val slf4jVersion = "1.7.36"
    const val thymeleafVersion = "3.0.15.RELEASE"
}

dependencies {
    implementation("org.springframework:spring-webmvc:${Versions.springVersion}")
    implementation("org.springframework:spring-orm:${Versions.springVersion}")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.springframework:spring-aop:${Versions.springVersion}")
    implementation("org.aspectj:aspectjweaver:1.9.9")

    implementation("org.springframework.security:spring-security-core:5.6.2")
    implementation("org.springframework.security:spring-security-web:5.6.2")
    implementation("org.springframework.security:spring-security-config:5.6.2")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")

    implementation("org.apache.tomcat:tomcat-jasper:9.0.60")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("javax.servlet:jstl:1.2")

    implementation("org.hibernate.validator:hibernate-validator:6.0.13.Final")

    implementation("org.hibernate:hibernate-core:5.6.7.Final")
    implementation("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2.Final")
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("org.apache.tomcat:tomcat-dbcp:9.0.60")

    implementation("org.thymeleaf:thymeleaf:${Versions.thymeleafVersion}")
    implementation("org.thymeleaf:thymeleaf-spring5:${Versions.thymeleafVersion}")

    implementation("org.apache.logging.log4j:log4j-core:${Versions.log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-api:${Versions.log4jVersion}")

    implementation("org.slf4j:slf4j-api:${Versions.slf4jVersion}")
    implementation("org.slf4j:slf4j-log4j12:${Versions.slf4jVersion}")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")
}