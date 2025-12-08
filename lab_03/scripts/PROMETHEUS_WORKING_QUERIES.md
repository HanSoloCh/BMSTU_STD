# Рабочие запросы Prometheus

## Важно: Как правильно вводить запросы в Prometheus UI

1. Открой **http://localhost:9090**
2. Перейди на вкладку **"Graph"**
3. В поле запроса введи запрос **БЕЗ кавычек** (Prometheus UI сам обрабатывает запрос)
4. Нажми **"Execute"**, затем **"Graph"**

## Рабочие запросы (копируй и вставляй в Prometheus UI)

### CPU использование (в процентах)

**Все Docker контейнеры:**
```
rate(container_cpu_usage_seconds_total{id=~"/system.slice/docker-.*"}[1m]) * 100
```

**Только контейнеры benchmark (более точный фильтр):**
```
rate(container_cpu_usage_seconds_total{id=~"/system.slice/docker-.*", id!~"/system.slice/docker.service"}[1m]) * 100
```

### Память (в мегабайтах)

**Все Docker контейнеры:**
```
container_memory_usage_bytes{id=~"/system.slice/docker-.*"} / 1024 / 1024
```

### Сетевая активность

**Входящий трафик (байт/сек):**
```
rate(container_network_receive_bytes_total{id=~"/system.slice/docker-.*"}[1m])
```

**Исходящий трафик (байт/сек):**
```
rate(container_network_transmit_bytes_total{id=~"/system.slice/docker-.*"}[1m])
```

## Проверка работы

Если запросы не работают, проверь:

1. **Доступность Prometheus:**
   ```bash
   curl http://localhost:9090/api/v1/query?query=up
   ```

2. **Статус targets:**
   - Открой http://localhost:9090/targets
   - Должен быть `cadvisor` со статусом **UP**

3. **Тест запроса через скрипт:**
   ```bash
   ./scripts/test_prometheus_queries.sh
   ```

## Частые ошибки

### Ошибка: "parse error"
- Убедись, что не используешь кавычки в UI
- Проверь синтаксис регулярного выражения: `id=~"/system.slice/docker-.*"`

### Ошибка: "no data"
- Убедись, что контейнеры запущены: `docker compose ps`
- Проверь, что cAdvisor работает: `docker compose logs cadvisor`

### Запрос работает, но нет данных
- Подожди 1-2 минуты после запуска контейнеров (нужны данные для rate)
- Используй запрос без `rate()` для проверки наличия метрик:
  ```
  container_cpu_usage_seconds_total{id=~"/system.slice/docker-.*"}
  ```

## Альтернатива: использование скрипта

Если запросы в UI не работают, используй скрипт для автоматического сбора метрик:
```bash
python3 scripts/collect_metrics_with_phases.py --scenario 1 --duration 600 &
```




