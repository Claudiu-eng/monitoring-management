FROM openjdk:21
ADD target/ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement-0.0.1-SNAPSHOT.jar monitoring-management.jar
ENTRYPOINT ["java", "-jar", "/monitoring-management.jar"]