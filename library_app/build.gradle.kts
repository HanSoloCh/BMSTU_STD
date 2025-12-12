import buildsrc.HalsteadTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.allure)
    id("io.gitlab.arturbosch.detekt") version "1.23.6" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1" apply false
}

subprojects {
    apply(plugin = "io.qameta.allure")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    tasks.register<Delete>("cleanAllureReport") {
        delete(layout.buildDirectory.dir("reports/allure-report/allureReport"))
    }

    tasks.register<Copy>("copyAllureHistory") {
        from(layout.buildDirectory.dir("reports/allure-report/allureReport/history"))
        into(layout.buildDirectory.dir("allure-results/history"))
        doFirst {
            println("[${project.name}] Copying Allure history from last report...")
        }
    }

    tasks.withType<Test>().configureEach {
        dependsOn("copyAllureHistory")
    }

    tasks.named("allureReport") {
        dependsOn("cleanAllureReport")
        dependsOn(tasks.withType<Test>())
    }

    extensions.configure<DetektExtension> {
        buildUponDefaultConfig = true
        autoCorrect = true
        config.setFrom(files("${rootProject.projectDir}/config/detekt/detekt.yml"))
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = "17"
        reports {
            html.required.set(true)
            xml.required.set(false)
            txt.required.set(false)
        }
    }

    extensions.configure<KtlintExtension> {
        version.set("12.1.1")
        android.set(
            plugins.hasPlugin("com.android.application") ||
                plugins.hasPlugin("com.android.library") ||
                plugins.hasPlugin("org.jetbrains.kotlin.android")
        )
    }

    val halsteadTask = tasks.register<HalsteadTask>("halstead") {
        sourceDirs.set(listOf(project.file("src")))
        outputFile.set(layout.buildDirectory.file("reports/halstead/halstead-report.txt"))
        maxVolume = 1200
        maxDifficulty = 50
    }

    tasks.matching { it.name == "check" }.configureEach {
        dependsOn("detekt")
        dependsOn("ktlintCheck")
        dependsOn(halsteadTask)
    }
}

tasks.register("installGitHooks") {
    group = "verification"
    description = "Install git hooks that run detekt, ktlint and halstead before commit."
    doLast {
        val hooksDir = rootDir.resolve(".git/hooks")
        hooksDir.mkdirs()
        rootDir.resolve("config/git-hooks/pre-commit").copyTo(
            hooksDir.resolve("pre-commit"),
            overwrite = true
        )
        hooksDir.resolve("pre-commit").setExecutable(true)
        println("Pre-commit hook installed.")
    }
}
