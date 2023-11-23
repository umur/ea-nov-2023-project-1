# Use an official OpenJDK runtime as a base image
FROM openjdk:17.0-jdk-slim

# Set environment variables
ENV APP_NAME=api-gateway
ENV APP_VERSION=1.0.0

RUN apt-get update
RUN apt-get -y install inetutils-ping
RUN apt-get install telnet

# Create and set the working directory
WORKDIR /app

# Copy the JAR file into the image
COPY target/api-gateway-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
