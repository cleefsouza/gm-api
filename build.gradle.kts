import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.Property

val springBootVersion: String by project
val jooqVersion: String by project
val jwtVersion: String by project

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("nu.studer.jooq") version "6.0.1"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "br.com.mir4"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-jooq:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.jsonwebtoken:jjwt:$jwtVersion")

    runtimeOnly("org.postgresql:postgresql")

    jooqGenerator("org.jooq:jooq-meta-extensions:$jooqVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
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

jooq {
    version.set(jooqVersion)

    configurations {

        // db classes from local migrations
        create("main") {

            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc = null

                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                        properties.add(
                            Property().withKey("scripts")
                                .withValue("src/main/resources/dblocal/schema_jooq.sql")
                        )
                        properties.add(
                            Property().withKey("defaultNameCase").withValue("lower")
                        )
                    }

                    generate.apply {
                        isDaos = false
                        isPojosEqualsAndHashCode = true
                    }

                    target.apply {
                        packageName = "br.com.mir4.guild.manager.model.jooq"
                    }
                }
            }
        }
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateJooq")
}

sourceSets {
    main {
        java {
            setSrcDirs(
                listOf(
                    "src/main/kotlin",
                    "build/generated-src/jooq/main"
                )
            )
        }
    }
}
