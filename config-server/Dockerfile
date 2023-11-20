FROM eclipse-temurin:latest
EXPOSE 8001
ENV PORT = 8001
ENV EUREKA_URI=http://localhost:8000/eureka

ENV EVENT_PORT=8801
ENV EVENT_DB_URL = localhost:3306
ENV EVENT_DB_NAME = eventsDb
ENV EVENT_DB_USER = root
ENV EVENT_DB_PASSWORD = 1


RUN mkdir /app
WORKDIR /app
COPY . ./src
RUN ./src/mvnw -f ./src/pom.xml clean install \
    && cp src/target/ConfigurationService-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/ConfigurationService-0.0.1-SNAPSHOT.jar"]