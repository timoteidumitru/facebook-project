# Start with a clean OpenJDK image to run your application
FROM openjdk:17

# Set the working directory in the Docker image
WORKDIR /app

# Copy the jar file from your local build output to the Docker image
# You need to ensure the path to your .jar file is correct relative to the Docker build context
COPY target/facebook-project-0.0.1-SNAPSHOT.jar /app/application.jar

# Run your application
CMD ["java", "-jar", "/app/application.jar"]
