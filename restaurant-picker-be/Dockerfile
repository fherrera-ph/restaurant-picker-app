FROM openjdk:17-jdk-slim
WORKDIR /app

COPY target/restaurant-picker-be-0.0.1-SNAPSHOT.jar /app/restaurant-picker-be-0.0.1-SNAPSHOT.jar
EXPOSE 8080

CMD ["java", "-jar", "restaurant-picker-be-0.0.1-SNAPSHOT.jar"]

