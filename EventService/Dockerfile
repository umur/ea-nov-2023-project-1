FROM eclipse-temurin:latest
EXPOSE 8801
ENV PORT=8801
ENV CONFIG_URI=http://configuration:8001
RUN mkdir /app
WORKDIR /app
COPY . ./src
RUN ./src/mvnw -f ./src/pom.xml clean install && cp src/target/EventService-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/EventService-0.0.1-SNAPSHOT.jar"]