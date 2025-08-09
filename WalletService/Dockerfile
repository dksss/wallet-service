FROM eclipse-temurin:17-jre-alpine
LABEL authors="jodyvole"

WORKDIR /app

COPY build/libs/*.jar wallet_service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "wallet_service.jar"]