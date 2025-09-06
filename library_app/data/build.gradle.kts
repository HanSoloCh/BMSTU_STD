plugins {
    kotlin("jvm")
    alias(libs.plugins.allure)
}

dependencies {
    implementation(project(":domain"))

    // Exposed
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)
    implementation(libs.sqlite.jdbc)
    // Connection pool
    implementation(libs.hikariCP)


    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Тестирование
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(kotlin("test"))
    testImplementation(libs.h2)

    // Тестовые контейнеры
    testImplementation(libs.postgresql)
    testImplementation(libs.testcontainers)
    testImplementation(libs.testcontainers.postgresql)
}