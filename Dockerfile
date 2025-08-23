# Base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml separately for caching
COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the source code
COPY src src

# Build the project (skip tests)
RUN ./mvnw clean package -DskipTests

# Expose the port your Spring Boot app uses
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/pelaksefid-0.0.1-SNAPSHOT.jar"]
