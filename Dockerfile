FROM gradle:8-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Этап запуска
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*-all.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

