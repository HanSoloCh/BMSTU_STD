#!/bin/bash
# Скрипт для тестирования запросов Prometheus

echo "=== Тест 1: Базовый запрос (без rate) ==="
curl -s "http://localhost:9090/api/v1/query" \
  --data-urlencode 'query=container_cpu_usage_seconds_total{id=~"/system.slice/docker-.*"}' \
  | python3 -c "import sys, json; d=json.load(sys.stdin); r=d.get('data',{}).get('result',[]); print(f'Найдено: {len(r)} метрик')"

echo ""
echo "=== Тест 2: Запрос с rate ==="
curl -s "http://localhost:9090/api/v1/query" \
  --data-urlencode 'query=rate(container_cpu_usage_seconds_total{id=~"/system.slice/docker-.*"}[1m])*100' \
  | python3 -c "import sys, json; d=json.load(sys.stdin); print('Статус:', json.load(sys.stdin).get('status')); d=json.load(sys.stdin); err=d.get('error'); print('Ошибка:', err if err else 'нет'); r=d.get('data',{}).get('result',[]); print(f'Результатов: {len(r)}')" 2>&1

echo ""
echo "=== Тест 3: Упрощенный запрос ==="
curl -s "http://localhost:9090/api/v1/query" \
  --data-urlencode 'query=rate(container_cpu_usage_seconds_total[1m])*100' \
  | python3 -c "import sys, json; d=json.load(sys.stdin); r=d.get('data',{}).get('result',[]); print(f'Найдено: {len(r)} метрик')"




