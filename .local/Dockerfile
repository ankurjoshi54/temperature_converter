FROM openjdk:8

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle/

RUN ./gradlew build

EXPOSE 8080 8081
