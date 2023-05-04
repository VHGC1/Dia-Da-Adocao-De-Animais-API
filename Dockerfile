# Stage 1: Build
FROM maven AS build-env
WORKDIR /app

# Copy project and build as distinct layers
COPY src ./src
COPY pom.xml ./
RUN mvn clean package

# Stage 2: Run
# runtime image
FROM openjdk:11
WORKDIR /app
COPY --from=build-env /app/target/*.jar ./app.jar
ENTRYPOINT ["java","-jar","./app.jar"]