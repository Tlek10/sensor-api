# Sensor API

## Инструкции по сборке и запуску через Docker

1. Клонируйте репозиторий на локальную машину:
    ```bash
    git clone https://github.com/your-repository/sensor-api.git
    cd sensor-api
    ```

2. Соберите проект с помощью Maven:
    ```bash
    ./gradlew build
    ```

3. Соберите Docker образ:
    ```bash
    docker build -t sensor-api .
    ```

4. Запустите контейнер с использованием Docker Compose:
    ```bash
    docker-compose up
    ```

Теперь приложение будет доступно по адресу: `http://localhost:8080`.

## Примеры использования API

### 1. Регистрация сенсора
**POST** `/sensors/registration`

**Тело запроса**:
```json
{
    "sensorName": "TestSensor"
}
Ответ:
{
  "token": "jwt_token_here"
}

После получения токена, добавьте его в заголовок следующих запросов:

Authorization: Bearer jwt_token_here



Регистрация сенсора

 `/sensors/registration`
```json

Тело запроса:
{
  "name": "test-sensor",
  "type": "temperature"
}

Ответ

{
  "message": "Sensor registered successfully",
  "sensor": {
    "name": "test-sensor",
    "type": "temperature",
    "id": 1
  }
}

{
  "message": "Sensor with this name already exists"
}
Добавление измерений
POST /measurements

Тело запроса:

{
  "value": 23.5,
  "raining": true,
  "sensor": {
    "name": "test-sensor"
  }
}
Ответ:

{
  "message": "Measurement added successfully",
  "measurement": {
    "value": 23.5,
    "raining": true,
    "timestamp": "2024-12-06T22:30:00",
    "sensor": {
      "name": "test-sensor"
    }
  }
}
Получение всех измерений
GET /measurements

Ответ:

[
  {
    "value": 23.5,
    "raining": true,
    "timestamp": "2024-12-06T22:30:00",
    "sensor": {
      "name": "test-sensor"
    }
  },
  {
    "value": -6.0,
    "raining": false,
    "timestamp": "2024-12-06T22:35:00",
    "sensor": {
      "name": "test-sensor"
    }
  }
]
Получение информации о сенсоре
GET /sensors/{id}

Ответ:

{
  "id": 1,
  "name": "test-sensor",
  "type": "temperature"
}