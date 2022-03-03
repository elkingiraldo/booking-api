# booking-api
Booking API is a backend repository that allow to the last Cancun Hotel to handle reservations.

## Introduction
The project is build with the help of the following technologies and frameworks:
* Java 11
* Maven
* Docker
* Spring Framework
* Spring-boot
* Lombok
* Mockito
* Log4j
* JUnit
* H2
* PostgresSQL

## Cloning project
You should clone this project in your local machine and the PROJECT_ROOT_PATH will be: **(booking-api)**

## Automatic compiling and deploying
There is a file `start-booking-api.sh` that compiles and deploys, the project and the DB, into two different docker containers.<br/>
For executing this file, you need to go into the PROJECT_ROOT_PATH and execute the following command:
```
$ sh start-booking-api.sh
```

## Environments
There are 3 different environments available for compiling and deploying mutants project:

- **dev** :arrow_right: For developing and working in local environment. **http://localhost:6969**
- **docker** :arrow_right: For using the Docker provided images. **http://localhost:8080**
- **prod** :arrow_right: For using booking application and DB hosted in AWS. **COMING_SOON**

The properties of each project are defined on `application-dev.properties`, `application-docker.properties` and `application-prod.properties`

## Manual compiling
This is a Maven project and for compiling it you should proceed with the Maven wrapper provided. You should go into PROJECT_ROOT_PATH and run the following command:
```
$ ./mvnw clean install -P {DESIRED_ENVIRONMENT}
```

## Deploying
The project is dockerized, and it has an external Postgres Database dockerized too. For deploying both of them you only need to run the following commands into PROJECT_ROOT_PATH.

The first one will destroy the images generated previously and the second one will create them again.

The project will always check that the DB is already initialized for starting to deploy the application. You can check this process executing `docker-compose up` instead of `docker-compose up -d --build`
```
$ docker-compose down -v
$ docker-compose up -d --build
```

## Docker manipulation
You are available to run and stop each container separately with the following commands:
```
$ docker start/stop booking-api
$ docker start/stop booking-postgresql
```
You can remove each image and container as well with the following commands:
```
$ docker rm booking-api
$ docker rm booking-postgresql
$ docker rmi booking-api:latest
```
For reviewing the DB from console you can type following commands:
```
$ docker exec -it booking-postgresql bash
$ psql -U postgres;
$ \c bookingapidb;
```

## Generalities
The project includes:

- Java documentation into PROJECT_ROOT_PATH/doc path.
- Swagger documentation (:round_pushpin:e.g. For docker environment :arrow_right: `http://localhost:8080/swagger-ui/index.html`)
- i18n for responses in spanish:es: or english:us:.
- Actuator for checking health of application.

### API request
#### Headers and parameters
| header  | value | description |
| ------------- | ------------- | ------------- |
| Accept  | application/json  | Default value  |
| Accept  | application/xml  | If you want a xml response  |
| locale  | en  | Default value  |
| locale  | es  | If you want a response in spanish  |

#### Healthcheck
There is a GET endpoint for verifying health check of application: `http://mutantsapplicationapi-env.eba-2pfp3jad.sa-east-1.elasticbeanstalk.com/actuator/health`

#### Booking
There are several endpoints

### API responses
The system checks in DB before trying to traverse the matrix and when it finds 2 sequences the algorithm stops for avoiding to do an extra effort in the calculation.

#### Healthcheck
200 OK with body:
```
{
    status: "UP"
}
```

#### Booking
There are 2 different type of responses...


#### Errors
The errors...