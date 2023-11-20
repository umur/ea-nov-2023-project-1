FROM eclipse-temurin:17

LABEL maintainer="dunkygeoffrey39@gmail.com"

WORKDIR /home/app

COPY target/springboot-user-service-0.0.1-SNAPSHOT.jar /home/app/restful-user-service1.jar

ENTRYPOINT ["java", "-jar", "restful-user-service1"]