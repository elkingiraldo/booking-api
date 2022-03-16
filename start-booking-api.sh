#!/usr/bin/env bash

docker stop booking-api

./mvnw clean install

docker-compose down -v
docker rmi booking-api:latest
#docker-compose up -d --build
docker-compose up --build
