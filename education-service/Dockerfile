FROM openjdk:17-alpine

WORKDIR /home/app

COPY target/education-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "education-0.0.1-SNAPSHOT.jar"]