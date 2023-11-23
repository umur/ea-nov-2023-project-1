# Use an official OpenJDK runtime as a base image
FROM openjdk:17.0-jdk-slim

# Set environment variables
ENV APP_NAME=job-service
ENV APP_VERSION=1.0.0

# Create and set the working directory
WORKDIR /app

# Copy the JAR file into the image
COPY target/job-service-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port the application runs on
EXPOSE 8081

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
