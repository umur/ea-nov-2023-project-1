FROM eclipse-temurin:17

WORKDIR /app

COPY target/APIGatewayService-0.0.1-SNAPSHOT.jar /app/APIGatewayService-0.0.1-SNAPSHOT.jar

EXPOSE 8002
ENV PORT=8002
ENV EUREKA_URI=http://localhost:8000/eureka
#RUN apk add --no-cache maven
#WORKDIR /app
#COPY . /app
#RUN mvn package
ENTRYPOINT ["java","-jar","APIGatewayService-0.0.1-SNAPSHOT.jar"]
