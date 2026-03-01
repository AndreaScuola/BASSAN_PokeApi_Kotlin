plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("kapt") version "2.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // OkHttp (richiesto da Retrofit)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines (molto consigliato con Retrofit)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // Test
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}