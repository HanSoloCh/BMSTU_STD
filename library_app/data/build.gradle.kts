plugins {
    kotlin("jvm")
    alias(libs.plugins.allure)
    idea
}

sourceSets {
    create("integrationTest") {
        kotlin.srcDir("src/integrationTest/kotlin")
    }
}

idea {
    module {
        sourceSets.named("integrationTest") {
            testSources.from(this.kotlin.srcDirs)
        }
    }
}

configurations.named("integrationTestImplementation") {
    extendsFrom(configurations["testImplementation"])
}
configurations.named("integrationTestRuntimeOnly") {
    extendsFrom(configurations["testRuntimeOnly"])
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath

    shouldRunAfter(tasks.named("test"))
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
    testImplementation(project(":data"))
}