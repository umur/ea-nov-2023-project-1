FROM openjdk:11-jre-slim
MAINTAINER NIZAR.M
WORKDIR /app
COPY target/student-service-0.0.1-SNAPSHOT.jar /app/student.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/student.jar"]