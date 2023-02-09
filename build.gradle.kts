// Gradle plugins to use
plugins {
    java
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
}

// Group name for the project
group = "com.criff"

// Version of the project
version = "0.0.1-SNAPSHOT"

// Compatability of the source code with Java version
java.sourceCompatibility = JavaVersion.VERSION_17

// Configuration for compile-only dependencies
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

// Repository to search for dependencies
repositories {
    mavenCentral()
}

// Dependencies required for the project
dependencies {
    // Spring Boot starter for JPA data access
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // Spring Boot starter for security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // Spring Boot starter for web application
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Lombok for code generation
    compileOnly("org.projectlombok:lombok")
    // Spring Boot DevTools for development-only use
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // MySQL connector for database connectivity
    runtimeOnly("com.mysql:mysql-connector-j")
    // PostgreSQL JDBC driver
    runtimeOnly("org.postgresql:postgresql")
    // Annotation processor for Spring Boot Configuration
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    // Annotation processor for Lombok
    annotationProcessor("org.projectlombok:lombok")
    // Spring Boot starter for testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Spring Security test library
    testImplementation("org.springframework.security:spring-security-test")
    // Hibernate core for JPA implementation
    implementation("org.hibernate.orm:hibernate-core:6.1.7.Final")
    // bcrypt implementation for password hashing
    implementation("org.mindrot:jbcrypt:0.4")
    // Apache Commons Lang3 for string manipulation and utilities
    implementation("org.apache.commons:commons-lang3")
    // Jackson for data binding and serialization/deserialization
    implementation("com.fasterxml.jackson.core:jackson-databind")
}

// Task configuration for JUnit tests
tasks.withType<Test> {
    // Use JUnit Platform for testing
    useJUnitPlatform()
}

