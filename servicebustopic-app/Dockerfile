FROM maven:3.5.2-jdk-8-alpine

MAINTAINER JM

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/

RUN mvn clean install

