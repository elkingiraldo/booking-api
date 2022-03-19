FROM openjdk:11.0.10-jdk-slim

MAINTAINER elkin.giraldo.pinedo@gmail.com

RUN apt-get install -y tzdata
ENV TZ America/Bogota
VOLUME [ "/home" ]

ENV WAIT_VERSION 2.9.0
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

ADD target/booking-api-0.0.1-SNAPSHOT.jar booking-api.jar

ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=docker", "booking-api.jar" ]