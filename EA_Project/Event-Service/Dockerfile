FROM openjdk:17-alpine

COPY ./target/Event-Service-0.0.1-SNAPSHOT.jar app.jar
LABEL authors="Omarfln"

ENTRYPOINT ["java", "-jar", "./app.jar"]