FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/local24-0.0.1-SNAPSHOT.jar /app

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Specify the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "local24-0.0.1-SNAPSHOT.jar"]