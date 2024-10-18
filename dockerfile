FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/navigator-data-exporter-0.0.1-SNAPSHOT.jar /app/navigator-data-exporter.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/navigator-data-exporter.jar"]
