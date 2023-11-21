FROM openjdk:17-alpine as build

LABEL maintainer="dunkygeoffrey39@gmail.com"

<<<<<<< HEAD
WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar /app/restful-user-service1.jar

EXPOSE 8181

ENV PORT=8181
=======
EXPOSE 8802
ENV PORT=8802
>>>>>>> dbfed9bed7e502e5b9c03827564e9f07bcd7d90a
ENV CONFIG_URI=http://configuration:8001
ENV EUREKA_URI=http://discovery:8000/eureka

ENV USER_DB_URL = localhost:3306
ENV USER_DB_NAME = usersdb
ENV USER_DB_USER = root
ENV USER_DB_PASSWORD = 1


<<<<<<< HEAD
#RUN apk add --no-cache maven
#RUN mvn package
ENTRYPOINT ["java", "-jar", "restful-user-service1.jar"]
=======
RUN apk add --no-cache maven
WORKDIR /app
COPY . /app
RUN mvn package
ENTRYPOINT ["java","-jar","/app/target/user-service-0.0.1-SNAPSHOT.jar"]
>>>>>>> dbfed9bed7e502e5b9c03827564e9f07bcd7d90a
