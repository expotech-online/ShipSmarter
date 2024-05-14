FROM openjdk:17-alpine

LABEL authors="ahmedukamel"

RUN apk add bash

WORKDIR /app

RUN mkdir -p "/app/images/company/logo" \
    "/app/images/company/licence" \
    "/app/images/company/pictures"

COPY  target/*.jar application.jar

EXPOSE 8080

CMD ["java", "-jar", "application.jar"]