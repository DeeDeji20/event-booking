-- set foreign_key_checks=0;
SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE "user" RESTART IDENTITY ;
TRUNCATE TABLE "event" RESTART IDENTITY;
TRUNCATE TABLE "user_authorities" RESTART IDENTITY;
TRUNCATE TABLE "reservation" RESTART IDENTITY;

-- truncate table "event";
-- truncate table "reservation";
-- truncate table "user";
-- truncate table "user_authorities";


insert into "user" ("id", "name", "email", "password", "created_at") values
(500, 'Adeola Oladeji', 'deolaoladeji@gmail.com', '$2a$10$.joVdHNdhNj0gkQL.AWRQe1qFbiN/3W7Z7JTlZQC/fzir.H.6u4u6', '2024-01-15 10:00:00.000000');

insert into "user_authorities" ("authorities", "user_id") values (0, 500);

insert into "event" ("id", "name", "category", "event_status", "current_number_of_attendees", "declined_count", "event_date", "available_attendees_count") values
(100, 'dev games', 'GAME', 'ACTIVE', 50, 0, '2024-03-15 10:00:00.000000', 100),
(106, 'dev lottery', 'GAME', 'ACTIVE', 50, 0, '2024-03-15 10:00:00.000000', 100),
(105, 'dev hangout', 'GAME', 'ACTIVE', 50, 0, '2024-03-15 10:00:00.000000',100),
(108, 'dev zone', 'CONFERENCE', 'ACTIVE', 0, 0, '2024-03-15 10:00:00.000000', 120),
(109, 'dev zone', 'CONFERENCE', 'ACTIVE', 0, 0, '2024-03-18 10:00:00.000000', 120),
(110, 'dev zone', 'CONFERENCE', 'ACTIVE', 0, 0, '2024-03-19 10:00:00.000000', 120),
(107, 'dev buzz', 'GAME', 'ACTIVE', 0, 0, '2024-03-15 10:00:00.000000', 150);


insert into "reservation" ("id", "event_id", "user_id", "reservation_status", "ticket_count") values
(100, 100, 500, 'BOOKED', 4),
(101, 100, 500, 'BOOKED', 1),
(103, 105, 500, 'BOOKED', 3),
(104, 108, 500, 'BOOKED', 5),
(105, 107, 500, 'CANCELED', 2);
