FROM openjdk:17-alpine as build
EXPOSE 8809
ENV PORT=8809
ENV CONFIG_URI=http://configuration:8001
RUN apk add --no-cache maven
WORKDIR /app
COPY . /app
RUN mvn package
ENTRYPOINT ["java","-jar","/app/target/chat-service-0.0.1-SNAPSHOT.jar"]
