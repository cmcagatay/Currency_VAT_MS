FROM library/openjdk:8-jdk-slim

WORKDIR /

COPY target/challenge-0.0.1.jar challenge.jar

CMD ["java", "-jar", "challenge.jar"]

EXPOSE 8081