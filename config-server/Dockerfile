FROM openjdk:17-alpine as build

EXPOSE 8001

ENV PORT = 8001
ENV EUREKA_URI=http://localhost:8000/eureka

ENV EVENT_PORT=8801
ENV EVENT_DB_URL = localhost:3306
ENV EVENT_DB_NAME = eventsDb
ENV EVENT_DB_USER = root
ENV EVENT_DB_PASSWORD = 1

ENV CHAT_PORT=8809
ENV CHAT_DB_URL = localhost:3306
ENV CHAT_DB_NAME = chatDb
ENV CHAT_DB_USER = root
ENV CHAT_DB_PASSWORD = 1

RUN apk add --no-cache maven
WORKDIR /app
COPY . /app
RUN mvn package
ENTRYPOINT ["java","-jar","/app/target/ConfigurationService-0.0.1-SNAPSHOT.jar"]
