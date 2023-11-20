FROM openjdk:11-jre-slim
MAINTAINER NIZAR.M
WORKDIR /app
COPY target/surevy-surveyresponse-service-0.0.1-SNAPSHOT.jar /app/surevy-surveyresponse.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/surevy-surveyresponse.jar"]
