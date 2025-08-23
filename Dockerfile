FROM eclipse-temurin:8-jdk

WORKDIR /app

COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/PelakSefid-2.0.0.jar"]
