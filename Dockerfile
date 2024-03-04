# Start with a Maven base image
FROM maven:3.8.4-openjdk-17 as builder

# Set the working directory in the Docker image
WORKDIR /app

# Copy your project into the Docker image
COPY . /app

# Use Maven to build your application
RUN mvn clean package

# Start with a clean OpenJDK image to run your application
FROM openjdk:17

# Copy the jar file from the builder stage
COPY --from=builder /app/target/*.jar /app/application.jar

# Run your application
CMD ["java", "-jar", "/app/application.jar"]
