FROM openjdk:17-alpine
MAINTAINER maha_AR
ARG JAR_FILE=target/*.jar
COPY ./target/News-Service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]