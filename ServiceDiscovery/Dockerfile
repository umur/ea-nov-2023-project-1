FROM eclipse-temurin:latest
EXPOSE 8000
RUN mkdir /app
WORKDIR /app
COPY . ./src
RUN ./src/mvnw -f ./src/pom.xml clean install \
    && cp src/target/ServiceDiscovery-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/ServiceDiscovery-0.0.1-SNAPSHOT.jar"]