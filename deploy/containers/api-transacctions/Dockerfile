# Usar la imagen base de Java
FROM openjdk:17-jdk-slim
ARG APP_JAR
WORKDIR /app
COPY ${APP_JAR} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]