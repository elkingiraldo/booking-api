FROM openjdk:11-alpine

MAINTAINER elkin.giraldo.pinedo@gmail.com
EXPOSE 8080

RUN apt-get install -y tzdata
ENV TZ America/Bogota
VOLUME [ "/home" ]

ADD target/booking-api-0.0.1-SNAPSHOT booking-api.jar

ENTRYPOINT [ "java", "-jar", "/home/booking-api.jar" ]