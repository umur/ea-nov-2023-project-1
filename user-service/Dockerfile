FROM eclipse-temurin:17

LABEL maintainer="dunkygeoffrey39@gmail.com"

WORKDIR /home/app

COPY target/user-service-0.0.1-SNAPSHOT.jar /home/app/restful-user-service1.jar


ENV PORT=8802
ENV CONFIG_URI=http://configuration:8001
ENV EUREKA_URI=http://discovery:8000/eureka

ENV USER_DB_URL = localhost:3306
ENV USER_DB_NAME = user_db
ENV USER_DB_USER = student
ENV USER_DB_PASSWORD = cluster@1


RUN apk add --no-cache maven
RUN mvn package
ENTRYPOINT ["java", "-jar", "restful-user-service1.jar"]