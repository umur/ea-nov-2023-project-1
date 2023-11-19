FROM eclipse-temurin:latest
EXPOSE 8001
RUN mkdir /app
WORKDIR /app
COPY . ./src
RUN ./src/mvnw -f ./src/pom.xml clean install \
    && cp src/target/ConfigurationService-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/ConfigurationService-0.0.1-SNAPSHOT.jar"]