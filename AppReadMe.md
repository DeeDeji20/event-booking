# Project Description
The project entails development of RESTful services that facilitates easy booking/reservation of tickets.
This application is secured using spring security
The services includes:
- Creating a user and enabling user sign in.
- Registration of an event.
- Booking/Reservation of an event.
- Searching for events.
- Checking booked reservations.
- Cancelling reservations.
- Notifying users of their upcoming events.
- Audit trail of notifications.

# Build Commands
To  start the application, run the following commands:
- Build
    - Run: `./mvnw spring-boot:run `
    - Test: `./mvnw test `

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
