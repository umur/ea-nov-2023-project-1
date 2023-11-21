FROM openjdk:17-alpine as build
EXPOSE 8002
ENV PORT=8002
ENV EUREKA_URI=http://localhost:8000/eureka
RUN apk add --no-cache maven
WORKDIR /app
COPY . /app
RUN mvn package
ENTRYPOINT ["java","-jar","/app/target/APIGatewayService-0.0.1-SNAPSHOT.jar"]