FROM openjdk:11-alpine

MAINTAINER elkin.giraldo.pinedo@gmail.com
EXPOSE 8080

RUN apt-get install -y tzdata
ENV TZ America/Bogota
VOLUME [ "/home" ]

ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=develop", "/home/booking-api.jar" ]