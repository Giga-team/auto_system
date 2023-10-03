FROM eclipse-temurin:17-jdk
ARG JAR_FILE
COPY ${JAR_FILE} car-dealership-system-api.jar
EXPOSE 8080
CMD ["java", "-jar", "car-dealership-system-api.jar"]