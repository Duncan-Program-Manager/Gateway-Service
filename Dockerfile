FROM openjdk:11 as rabbitmq
EXPOSE 8083 3306
ADD target/Gateway-0.0.1-SNAPSHOT.jar gateway-docker.jar
ENTRYPOINT ["java","-jar","gateway-docker.jar"]
