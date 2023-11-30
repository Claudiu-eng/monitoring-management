FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:21
COPY --from=build /app/target/ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement-0.0.1-SNAPSHOT.jar monitoring-management.jar
ENTRYPOINT ["java", "-jar", "/monitoring-management.jar"]
