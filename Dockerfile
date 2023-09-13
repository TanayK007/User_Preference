FROM adoptopenjdk:11-jdk-hotspot

COPY ./target/userPreference-0.0.1-SNAPSHOT.jar userPreference-0.0.1-SNAPSHOT.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "userPreference-0.0.1-SNAPSHOT.jar"]