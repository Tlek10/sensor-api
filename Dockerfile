FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/sensor-api-0.0.1-SNAPSHOT.jar sensor-api.jar

EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "sensor-api.jar"]
