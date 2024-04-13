# Use a base image with Maven, JDK 17, and other necessary tools
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app


# Copy the Maven project file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight base image for the application
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the build stage to the container
COPY --from=build /app/target/*.jar /app/FlightBookingApplication.jar

# Specify the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "FlightBookingApplication.jar"]
