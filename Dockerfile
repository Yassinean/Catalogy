FROM openjdk:17-jdk-alpine
MAINTAINER baeldung.com
COPY target/catalogue-0.0.1-SNAPSHOT.jar catalogue-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/catalogue-0.0.1-SNAPSHOT.jar"]