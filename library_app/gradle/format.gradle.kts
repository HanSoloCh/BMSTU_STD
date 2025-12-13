// Автоматическое исправление форматирования для всех модулей

tasks.register("format") {
    group = "formatting"
    description = "Auto-format all Kotlin files using detekt and ktlint"
    dependsOn(subprojects.map { "${it.path}:detektFormat" })
    dependsOn(subprojects.map { "${it.path}:ktlintFormat" })
}

tasks.register("formatCheck") {
    group = "formatting"
    description = "Check formatting without auto-fixing"
    dependsOn(subprojects.map { "${it.path}:detekt" })
    dependsOn(subprojects.map { "${it.path}:ktlintCheck" })
}

