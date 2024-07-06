plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // RestAssured
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("io.rest-assured:json-path:5.4.0")
    testImplementation("io.rest-assured:json-schema-validator:5.4.0")
    testImplementation("io.rest-assured:spring-mock-mvc:5.4.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.4.0")

    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    //Gson
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")

    //.env
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

}

tasks.test {
    useJUnitPlatform()
}