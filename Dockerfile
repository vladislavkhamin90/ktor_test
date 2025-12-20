# Этап сборки
FROM gradle:8-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle shadowJar --no-daemon  # Изменено с build на shadowJar

# Этап запуска
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Ищем shadow JAR (он будет иметь суффикс -all или -all.jar в зависимости от версии плагина)
COPY --from=builder /app/build/libs/*-all.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
