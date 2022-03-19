#!/usr/bin/env bash

docker stop booking-api
docker stop booking-postgresql

./mvnw clean install -P docker

docker-compose down -v
docker rmi booking-api:latest
docker rmi postgres:alpine

docker-compose up -d --build
