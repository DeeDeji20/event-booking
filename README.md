# Project Description
The project entails development of RESTful services that facilitates easy booking/reservation of tickets.
This application is secured using spring security.

The services include:
- Creating a user and enabling user sign in.
- Registration of an event.
- Booking/Reservation of an event.
- Searching for events.
- Checking booked reservations.
- Cancelling reservations.
- Notifying users of their upcoming events.
- Audit trail of notifications.

# User Stories

✅As a user, I want to be able to create an account
so that I can access the system and perform
various actions.

✅As a user, I want to be able to authenticate
by providing my credentials (username and
password) so that I can securely log into the system.

✅As a user, I want to be able to create events by providing
event details such as name, date, available attendees count,
description, and category. This will allow me to organise and
manage events within the system.

✅As a user, I want to be able to search for events based on criteria so that I can
find events of interest.

✅As a user, I want to be able to reserve tickets for events by
selecting the desired event and specifying the number of
tickets I want to reserve. This will enable me to secure my
attendance at the event.

✅As a user, I want to receive notifications before an event
starts so that I can be reminded and prepared for the upcoming
event

✅As a user, I want to be able to view my booked events

✅As a user, I want to be able to cancel my reservation for an event so
that I can free up my spot and allow someone else to attend in my place.

✅When event time has passed, I want event status to be ended


# Running Application
To  start the application, do the following :
- [JDK - least version should be 17](https://www.oracle.com/ng/java/technologies/downloads/)
- [Maven 3](https://maven.apache.org/)
- Run the following maven commands:
    - mvn clean:clean
    - mvn install
    - mvn compile
- Either execute the ` main ` method located in the /src/main/java/com/musala/Main.java class from your IDE or run mvn spring-boot:run


## User Entity
- name (limited to 100 characters);
- email (valid email format);
- password (minimum 8 characters).

## Event Entity
- name (limited to 100 characters);
- date (in a valid date format);
- available attendees count (positive integer limited to 1000);
- event description (limited to 500 characters).
- category (Concert, Conference, Game)

## API Documentation

### User

#### POST http://localhost:8080/users

- Create users
  **Request Sample**
```shell
curl --location 'localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John Doe",
    "email": "test@gmail.com",
    "password": "password"
}'

```
**Response Examples:**
```json
{
  "data": "User Created Successfully"
}
```


#### POST localhost:8080/auth/login

- Login
  **Request Sample**
```shell
curl --location 'localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"email":"test@gmail.com",
"password":"password"
}'

```
**Response Examples:**
```json
{
  "access_token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRlb2xhb2xhZGVqaUBnbWFpbC5jb20iLCJleHAiOjE3MDgwMjkxMDksImlhdCI6MTcwNzk0MjcwOX0.j1A2dTtPHbzVXo2pqmrDQLnWQgpcZk1gqdFDtmQvDAF0cQ_TyICBc2XkNZR0lvaFs-5wUwts1ktkV9rSt2Czxg"
}
```


### Event
#### GET http://localhost:8080/events?name=dev%20game&category=GAME&pageNumber=1&pageSize=25
- Get events by search criteria
```shell
curl -X 'GET' \
  'http://localhost:8080/events?name=dev%20game&category=GAME&pageNumber=1&pageSize=25' \
  -H 'accept: application/json' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRlb2xhb2xhZGVqaUBnbWFpbC5jb20iLCJleHAiOjE3MDgwMjkxMDksImlhdCI6MTcwNzk0MjcwOX0.j1A2dTtPHbzVXo2pqmrDQLnWQgpcZk1gqdFDtmQvDAF0cQ_TyICBc2XkNZR0lvaFs-5wUwts1ktkV9rSt2Czxg'
```
**Response Examples:**
```json
{
  "data": {
    "id": 1,
    "name": "First event",
    "category": "GAME",
    "createdBy": {
      "id": 1,
      "name": "John Doe",
      "email": "test@gmail.com"
    },
    "eventDate": "2024-02-16T16:09:10",
    "availableAttendeesCount": 1000
  }
}
```


#### POST http://localhost:8080/events
- Create events

```shell
curl --location 'localhost:8080/events' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRlb2xhb2xhZGVqaUBnbWFpbC5jb20iLCJleHAiOjE3MDc5MTYwNzMsImlhdCI6MTcwNzgyOTY3M30.qiWBCLMn_C3Sk3106zFZOMsOVb15LNzWwib4kWXMST5WLbVj96Z869PgVJ4OxgH501Z-6iRzd-W2rI0EOHU7EA' \
--data '{
  "name": "First event",
  "date": "2024-02-16T16:09:10",
  "availableAttendeesCount": 1000,
  "description": "test description",
  "category": "GAME"
}'
```

**Response Examples:**
```json
{
  "data": {
    "id": 1,
    "name": "First event",
    "category": "GAME",
    "createdBy": {
      "id": 1,
      "name": "John Doe",
      "email": "test@gmail.com"
    },
    "eventDate": "2024-02-16T16:09:10",
    "availableAttendeesCount": 1000
  }
}
```



#### POST http://localhost:8080/events/1/tickets
- Reserve ticket for event

```shell

curl --location 'localhost:8080/events/1/tickets' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRlb2xhb2xhZGVqaUBnbWFpbC5jb20iLCJleHAiOjE3MDc5MDA4MTgsImlhdCI6MTcwNzgxNDQxOH0.4Bg7dJF9PTQwSijWt5kUqc0L6DhXMaXZVwF-FJM-Ed5TjNjT6WTT7tNBPm8U7H8PrR2Cp8lkxwJ04qGkIzo6Yg' \
--data '{
"attendeesCount": 5
}'
```

**Response Examples:**
```json
{
  "data": {
    "message": "Tickets reserved successfully"
  }
}
```

### Reservation

#### GET http://localhost:8080/reservation
- Get all reservations

```shell

curl --location 'localhost:8080/api/v1/reservation?page=1&size=10' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRlb2xhb2xhZGVqaUBnbWFpbC5jb20iLCJleHAiOjE3MDgwMjkxMDksImlhdCI6MTcwNzk0MjcwOX0.j1A2dTtPHbzVXo2pqmrDQLnWQgpcZk1gqdFDtmQvDAF0cQ_TyICBc2XkNZR0lvaFs-5wUwts1ktkV9rSt2Czxg'
```

**Response Examples:**
```json
{
  "data": [
    {
      "id": 104,
      "ticketCount": 5,
      "reservationStatus": "BOOKED",
      "event": {
        "id": 8,
        "name": "dev zone",
        "category": "CONFERENCE",
        "eventDate": "2024-01-15T10:00:00",
        "availableAttendeesCount": 0
      }
    }
  ]
}
```


#### POST http://localhost:8080/reservation/cancel/1
- Cancel Reservation

```shell
curl --location --request POST 'localhost:8080/api/v1/reservation/cancel/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRlb2xhb2xhZGVqaUBnbWFpbC5jb20iLCJleHAiOjE3MDc5MDI0MjYsImlhdCI6MTcwNzgxNjAyNn0.USTPXfJKYWpj5fDrSFdLYZO_QS5QSNOR3Jg8cGKE0Kqlxuo8xyub-LCceUIUjyfIWxeujbJOARMV-0ilkKxzBw'
```

**Response Examples:**
```json
{
  "data": {
    "id": 1,
    "ticketCount": 5,
    "reservationStatus": "CANCELED",
    "createdAt": "2024-02-13T10:20:44.258161",
    "event": {
      "id": 1,
      "name": "First event",
      "category": "GAME",
      "createdBy": {
        "id": 1,
        "name": "Adeola Oladeji",
        "email": "deolaoladeji@gmail.com"
      },
      "eventDate": "2024-02-16T16:09:10",
      "availableAttendeesCount": 1000
    }
  }
}
```


#### GET localhost:8080/api/v1/reservation/test@gmail.com
- Get all users reservation

```shell
curl --location 'localhost:8080/api/v1/reservation/test@gmail.com' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImRlb2xhb2xhZGVqaUBnbWFpbC5jb20iLCJleHAiOjE3MDc5MDA4MTgsImlhdCI6MTcwNzgxNDQxOH0.4Bg7dJF9PTQwSijWt5kUqc0L6DhXMaXZVwF-FJM-Ed5TjNjT6WTT7tNBPm8U7H8PrR2Cp8lkxwJ04qGkIzo6Yg'

```

**Response Examples:**
```json
{
    "data": [
        {
            "id": 104,
            "ticketCount": 5,
            "reservationStatus": "BOOKED",
            "event": {
                "id": 8,
                "name": "dev zone",
                "category": "CONFERENCE",
                "eventDate": "2024-01-15T10:00:00",
                "availableAttendeesCount": 0,
                "eventStatus": "ACTIVE"
            }
        },
        {
            "id": 103,
            "ticketCount": 3,
            "reservationStatus": "BOOKED",
            "event": {
                "id": 5,
                "name": "dev hangout",
                "category": "GAME",
                "eventDate": "2024-01-15T10:00:00",
                "availableAttendeesCount": 100,
                "eventStatus": "ACTIVE"
            }
        },
        {
            "id": 101,
            "ticketCount": 1,
            "reservationStatus": "BOOKED",
            "event": {
                "id": 6,
                "name": "dev lottery",
                "category": "GAME",
                "eventDate": "2024-01-15T10:00:00",
                "availableAttendeesCount": 50,
                "eventStatus": "ACTIVE"
            }
        },
        {
            "id": 100,
            "ticketCount": 4,
            "reservationStatus": "BOOKED",
            "event": {
                "id": 1,
                "name": "dev games",
                "category": "GAME",
                "eventDate": "2024-01-15T10:00:00",
                "availableAttendeesCount": 50,
                "eventStatus": "ACTIVE"
            }
        }
    ]
}
```

# Swagger Documentation
The swagger documentation can be found here:

http://localhost:8080/swagger-ui/index.html

# Considerations
The project follows a MVC (Model-View-Controller) architectural 
design model and this is to effectively adhere to some of the design principles (SOLID principle)
that fosters separation of concerns.
The controller delegate tasks to the appropriate service where the business logic is performed.

The application uses in-memory H2 Database storage for persisting data.
H2 database’s lightweight nature and in-memory capabilities make it a convenient
choice for temporary data storage during application development.



