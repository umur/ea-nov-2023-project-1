# Use the official OpenJDK base image with JDK 17
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project (including the pom.xml file) to the container
COPY target/news-service-0.0.1-SNAPSHOT.jar /app/news-service.jar

# Expose the port your microservice will listen on
EXPOSE 9191

# Command to run the application
CMD ["java", "-jar", "/app/news-service.jar"]