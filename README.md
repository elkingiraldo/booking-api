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
* JPA
* PostgreSQL
* FlywayDB
* JUnit **COMING_SOON**
* H2

## Cloning project
You should clone this project in your local machine and the PROJECT_ROOT_PATH will be: **(booking-api)**

## Environments
There are 3 different environments available for compiling and deploying booking project:

- **dev** :arrow_right: For developing and working in local environment. **http://localhost:6969**
- **docker** :arrow_right: For using the Docker provided images. **http://localhost:8080**
- **prod** :arrow_right: For using booking application and DB hosted in AWS. **COMING_SOON**

There is a common `application.properties` and some additional properties of each environment are defined on `application-dev.properties`, `application-docker.properties` and `application-prod.properties`

## Automatic compiling and deploying
:warning: **You need to have installed docker and docker-compose on your machine for executing this step** :warning:

There is a file `start-booking-api.sh` that compiles and deploys, the project and the DB, into two different docker containers.<br/>
For executing this file, you need to go into the PROJECT_ROOT_PATH and execute the following command:
```
$ sh start-booking-api.sh
```

## Manual compiling
This is a Maven project and for compiling it, you should proceed with the Maven wrapper provided. You should go into PROJECT_ROOT_PATH and run the following command:
```
$ ./mvnw clean install -P {DESIRED_ENVIRONMENT}
```

## Deploying
The project is dockerized, and it has an external PostgreSQL Database dockerized too. For deploying both of them you only need to run the following commands into PROJECT_ROOT_PATH.

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
$ docker rmi postgres:alpine
```
For reviewing the DB from console you can type following commands:
```
$ docker exec -it booking-postgresql bash
$ psql -U postgres;
$ \c bookingapidb;
```
The database username and password can be found on the properties files.

## Generalities
The project includes:

- Java documentation into PROJECT_ROOT_PATH/doc path. **COMING_SOON**
- Swagger documentation (:round_pushpin:e.g. For docker environment :arrow_right: `http://localhost:8080/swagger-ui/index.html`)
- i18n for responses in French :fr: Spanish :es: or English :us:.
- Actuator for checking health of application (`/actuator/health`).

### API request
#### Headers and parameters
| header  | value | description |
| ------------- | ------------- | ------------- |
| Accept  | application/json  | Default value  |
| Accept  | application/xml  | If you want a xml response  |
| locale  | en  | If you want a response in English :us:  |
| locale  | es  | If you want a response in Spanish :es:  |
| locale  | fr  | If you want a response in French :fr:  |

#### Booking API
There are several endpoints:
* **GET** `/users` :arrow_right: Obtain all DB Users.
* **POST** `/users` :arrow_right: Create a new user.
```
{
    "nickname": "somenickname",
    "name": "Some Name",
    "lastName": "Some last name"
}
```
* **GET** `/users/{nickname}` :arrow_right: Obtain information from a specific user.
* **POST** `/booking` :arrow_right: Make a new reservation.
:warning: The dates format available is `yyyy-MM-dd` :warning:
```
{
    "startDate": "2022-04-04",
    "endDate": "2022-04-05",
    "nickname": "elkingiraldo91"
}
```
There are 4 possible states of the reservation:
    * SCHEDULED :arrow_right: When the reservation is created.
	* IN_PROGRESS :arrow_right: There is a reservation during the actual date.
	* CANCELLED :arrow_right: When user cancelled the reservation.
	* COMPLETED :arrow_right: When the reservation was finished correctly in the past.

Additionally, there is a column for logical deletion.

* **GET** `/booking/room/availability :arrow_right: Check availability of the room. It will return the dates available from today to 60 days later as default.

### API responses
These are the responses from the API.

#### Booking
* **GET** `/users` :arrow_right: 200 OK
```
[
    {
        "nickname": "elkingiraldo91",
        "name": "Elkin Giovanni",
        "lastName": "Giraldo Pinedo"
    }
]
```
* **POST** `/users` :arrow_right: 201 Created
```
{
    "nickname": "pedro4",
    "name": "Pedro",
    "lastName": "Perez"
}
```
* **GET** `/users/pedro4` :arrow_right: 200 OK
```
{
    "nickname": "pedro4",
    "name": "Pedro",
    "lastName": "Perez"
}
```
* **POST** `/booking` :arrow_right: 201 Created
```
{
    "startDate": "2022-04-04",
    "endDate": "2022-04-05",
    "reservationId": "01687a8b-e273-41d3-ac5a-ca4fe8d8e3b7",
    "totalPrice": 216091.0,
    "status": "SCHEDULED",
    "room": {
        "name": "T1-302",
        "currentPrice": 108045.5
    },
    "user": {
        "nickname": "elkingiraldo91",
        "name": "Elkin Giovanni",
        "lastName": "Giraldo Pinedo"
    }
}
```
* **GET** `/booking/room/availability :arrow_right: 200 OK
```
[
    "2022-03-21",
    "2022-03-22",
    "2022-03-25",
    "2022-03-27",
    "2022-03-28",
    "2022-03-31",
    "2022-04-01",
    "2022-04-02",
    "2022-05-17",
    "2022-05-18"
]
```

#### Errors
This is the list of possible errors. Depend on `locale` header, the answer will change language.

* **general.exception** :arrow_right: General Exception.
* **booking.dates.not.availables.exception** :arrow_right: The range dates is not available.
* **booking.end.date.validation.exception** :arrow_right: The end date should be provided in the DTO sent.
* **booking.max.duration.reservation.exception** :arrow_right: The maximum consecutive reservation period allowed is 3 days.
* **booking.nickname.cant.be.empty.exception** :arrow_right: The nickname should be provided in the DTO sent.
* **booking.start.date.after.end.date.exception** :arrow_right: The start date cannot be later than the end date.
* **booking.start.date.validation.exception** :arrow_right: The start date should be provided in the DTO sent.
* **booking.valid.days.reservation.exception** :arrow_right: You can only make a reservation between tomorrow and up to 30 calendar days.
* **user.already.created.exception** :arrow_right: User was already created in database.
* **user.should.be.registered.exception** :arrow_right: The user should be registered in our platform, please use the endpoint /user to do it.
* **user.last.name.cant.be.empty.exception** :arrow_right: The last name should be filled in the UserDTO provided.
* **user.name.cant.be.empty.exception** :arrow_right: The name should be filled in the UserDTO provided.
* **user.nickname.cant.be.empty.exception** :arrow_right: The nickname should be filled in the UserDTO provided.
* **user.not.found.exception** :arrow_right: User not found in database.