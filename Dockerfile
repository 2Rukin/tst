FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package


FROM openjdk:17-jdk
LABEL maintainer="tvvmobile@gmail.com, Telegram: @tvvJob"
ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY --from=build /app/target/systemeio-app-0.0.1-SNAPSHOT.jar $APP_HOME/tvvmobile-app.jar

CMD ["java", "-jar", "tvvmobile-app.jar"]

EXPOSE 8080
VOLUME /tmp