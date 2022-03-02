FROM openjdk:11.0.10-jdk-slim

MAINTAINER elkin.giraldo.pinedo@gmail.com

RUN apt-get install -y tzdata
ENV TZ America/Bogota
VOLUME [ "/home" ]

ADD target/booking-api-0.0.1-SNAPSHOT.jar booking-api.jar

ENTRYPOINT [ "java", "-jar", "booking-api.jar" ]