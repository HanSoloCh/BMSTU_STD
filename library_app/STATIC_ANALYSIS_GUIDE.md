# Руководство по статическому анализу кода

## Обзор

В проект интегрированы средства статического анализа для проверки качества кода:

1. **Цикломатическая сложность (Cyclomatic Complexity)** - проверяется через detekt
2. **Сложность по Холстеду (Halstead Complexity)** - проверяется через кастомную задачу HalsteadTask
3. **Code Style** - проверяется через detekt и ktlint
4. **Проверка типов** - выполняется компилятором Kotlin

**Примечание**: Модуль `:ui` (Android UI) исключен из проверок статического анализа, так как Android проекты часто имеют специфичные требования к структуре кода.

## Установка и настройка

### 1. Установка Git Hooks

Для автоматической проверки перед каждым коммитом установите pre-commit hook:

```bash
./gradlew installGitHooks
```

После установки при каждом коммите автоматически будут запускаться проверки:

- `detekt` - статический анализ кода
- `ktlintCheck` - проверка стиля кода
- `halstead` - проверка метрик Холстеда

Если проверки не пройдут, коммит будет заблокирован (кроме случая использования флага
`--no-verify`).

### 2. Конфигурация

#### Detekt (Цикломатическая сложность и стиль)

Конфигурация находится в `config/detekt/detekt.yml`:

- **Цикломатическая сложность**: максимум 10 (правило `CyclomaticComplexMethod`)
- **Длина метода**: максимум 60 строк (правило `LongMethod`)
- **Длина строки**: максимум 140 символов (правило `MaxLineLength`)

#### Halstead (Сложность по Холстеду)

Конфигурация в `build.gradle.kts`:

- **Volume**: максимум 1200
- **Difficulty**: максимум 50

#### Ktlint (Code Style)

Использует стандартные правила Kotlin style guide с поддержкой Android.

## Использование

### Ручной запуск проверок

#### Запуск всех проверок:

```bash
./gradlew check
```

Эта команда запускает:

- `detekt` - статический анализ
- `ktlintCheck` - проверка стиля
- `halstead` - метрики Холстеда

#### Запуск отдельных проверок:

```bash
# Только detekt
./gradlew detekt

# Только ktlint
./gradlew ktlintCheck

# Только Halstead
./gradlew halstead
```

#### Автоисправление форматирования:

```bash
# Автоматическое добавление переносов строк в конце всех файлов
./gradlew addTrailingNewlines

# Автоисправление через detekt (detekt исправляет код автоматически при запуске)
./gradlew detekt

# Автоисправление через ktlint
./gradlew ktlintFormat

# Комплексное форматирование (все сразу)
./gradlew formatAll
```

**Важно**: 
- Задача `addTrailingNewlines` автоматически добавляет перенос строки в конце всех Kotlin файлов, если его нет. Это решает проблему с правилом `NewLineAtEndOfFile` из detekt.
- Detekt автоматически исправляет код при выполнении задачи `detekt` (благодаря `autoCorrect = true` в конфигурации). Отдельной задачи `detektFormat` не существует.

### Просмотр отчетов

После запуска проверок отчеты доступны в:

- **Detekt**: `build/reports/detekt/detekt.html` (для каждого модуля)
- **Halstead**: `build/reports/halstead/halstead-report.txt` (для каждого модуля)
- **Ktlint**: вывод в консоль

### CI/CD Pipeline

Проверки автоматически запускаются в CI/CD перед тестами (см. `.github/workflows/ci.yml`):

1. Статический анализ (`detekt ktlintCheck halstead`)
2. Запуск тестов (`test`)

Если проверки не пройдут, пайплайн завершится с ошибкой.

## Требования и пороги

### Цикломатическая сложность

**Максимум: 10**

Цикломатическая сложность метода не должна превышать 10. Если метод превышает этот порог, его нужно
разбить на более мелкие методы.

**Пример проблемы:**

```kotlin
fun complexMethod() {
    if (condition1) {
        if (condition2) {
            if (condition3) {
                // ... много вложенных условий
            }
        }
    }
}
```

**Решение:**

```kotlin
fun complexMethod() {
    if (condition1) {
        handleCondition1()
    }
}

private fun handleCondition1() {
    if (condition2) {
        handleCondition2()
    }
}
```

### Метрики Холстеда

**Volume: максимум 1200**  
**Difficulty: максимум 50**

Если файл превышает эти пороги, его нужно упростить или разбить на несколько файлов.

### Code Style

Проект следует стандартному Kotlin style guide:

- Использование camelCase для имен переменных и функций
- PascalCase для имен классов
- Максимальная длина строки: 140 символов
- Запрет wildcard импортов
- Обязательный перенос строки в конце файла

## Решение проблем

### Ошибка: "CyclomaticComplexMethod"

Если метод имеет цикломатическую сложность > 10:

1. Найдите метод в отчете detekt
2. Разбейте метод на более мелкие функции
3. Вынесите сложную логику в отдельные методы

### Ошибка: "Halstead thresholds exceeded"

Если файл превышает пороги Холстеда:

1. Проверьте отчет в `build/reports/halstead/halstead-report.txt`
2. Упростите код, уменьшив количество операторов и операндов
3. Разбейте большой файл на несколько меньших

### Ошибка: "ktlintCheck failed"

Если ktlint находит проблемы со стилем:

1. Запустите `./gradlew ktlintFormat` для автоисправления
2. Проверьте вывод в консоли для ручных исправлений

### Обход проверок (только в исключительных случаях)

Если нужно закоммитить без проверок (не рекомендуется):

```bash
git commit --no-verify -m "message"
```

**Внимание**: Используйте `--no-verify` только в исключительных случаях и обязательно исправьте
проблемы в следующем коммите.

## Структура файлов

```
library_app/
├── build.gradle.kts              # Основная конфигурация Gradle
├── buildSrc/
│   └── src/main/kotlin/
│       └── HalsteadTask.kt        # Кастомная задача для метрик Холстеда
├── config/
│   ├── detekt/
│   │   └── detekt.yml            # Конфигурация detekt
│   └── git-hooks/
│       └── pre-commit             # Pre-commit hook скрипт
└── .github/
    └── workflows/
        └── ci.yml                 # CI/CD пайплайн
```

## Полезные команды

```bash
# Установка hooks
./gradlew installGitHooks

# Полная проверка
./gradlew check

# Только статический анализ
./gradlew detekt ktlintCheck halstead

# Автоисправление форматирования
./gradlew addTrailingNewlines  # Добавить переносы строк в конце файлов
./gradlew detektFormat          # Автоисправление через detekt
./gradlew ktlintFormat          # Автоисправление через ktlint
./gradlew formatAll             # Все исправления сразу

# Проверка конкретного модуля
./gradlew :api:detekt
./gradlew :domain:ktlintCheck
./gradlew :data:halstead
```

### Автоматическое исправление перед коммитом

Pre-commit hook автоматически запускает:
1. `addTrailingNewlines` - добавляет переносы строк
2. `detektFormat` - автоисправление detekt
3. `ktlintFormat` - автоисправление ktlint

Затем выполняет проверки. Это означает, что вам не нужно вручную добавлять переносы строк - они добавятся автоматически при коммите!

## Дополнительная информация

- [Detekt Documentation](https://detekt.github.io/detekt/)
- [Ktlint Documentation](https://ktlint.github.io/)
- [Kotlin Style Guide](https://kotlinlang.org/docs/coding-conventions.html)
- [Halstead Complexity Metrics](https://en.wikipedia.org/wiki/Halstead_complexity_measures)

