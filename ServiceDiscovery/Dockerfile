FROM eclipse-temurin:latest
EXPOSE 8000

ENV PORT = 8000
ENV EUREKA_URI=http://localhost:8000/eureka

RUN mkdir /app
WORKDIR /app
COPY . ./src
RUN ./src/mvnw -f ./src/pom.xml clean install \
    && cp src/target/ServiceDiscovery-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/ServiceDiscovery-0.0.1-SNAPSHOT.jar"]