# BUILD
FROM openjdk:21
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
# RUN
CMD ["java", "-jar", "app.jar"]