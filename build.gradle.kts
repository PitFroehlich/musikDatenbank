import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.1.0"
    id("org.openapi.generator") version "6.6.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
    id("org.springframework.boot") version "3.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
    kotlin("kapt") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.0"
}

group = "com.htwk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.1.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.liquibase:liquibase-core:4.22.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.2")



}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set((File("${projectDir}/src/main/resources/api.yml")).toString())
    outputDir.set("$buildDir/generated/openapi")
    configOptions.set(mapOf(
        "dateLibrary" to "java8",
        "serializableModel" to "true",
        "useSpringBoot3" to "true",
        "modelMutable" to "true",
        "interfaceOnly" to "true"
    ))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}



tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDirs("$rootDir/build/generated/openapi/src/main/kotlin", "${rootDir
            }build/generated/sources/annotationProcessor/java/main", "${rootDir
            }build/generated/source/kapt/main")
        }
    }
}

tasks.named("bootRun") {
    dependsOn("openApiGenerate")
}

