FROM openjdk:17-alpine as build
EXPOSE 8807
ENV PORT=8807
ENV CONFIG_URI=http://configuration:8001
RUN apk add --no-cache maven
WORKDIR /app
COPY . /app
RUN mvn package
ENTRYPOINT ["java","-jar","/app/target/insight-0.0.1-SNAPSHOT.jar"]
