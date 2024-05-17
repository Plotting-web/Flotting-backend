# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files to the container
COPY build.gradle settings.gradle /app/

# Copy the gradle wrapper to the container
COPY gradlew /app/
COPY gradle /app/gradle

# Download the gradle dependencies
RUN ./gradlew build --exclude-task test || return 0
CMD ["ls"]

# Copy the rest of the application source code to the container
COPY src /app/src

# Build the application
RUN ./gradlew build --exclude-task test

# Use a smaller base image for the runtime
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/Flotting-backend-0.0.1-SNAPSHOT.jar /app/Flotting-backend-0.0.1-SNAPSHOT.jar
CMD ["ls /app/build/libs"]

# Expose the port the application runs on
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "Flotting-backend-0.0.1-SNAPSHOT.jar"]

