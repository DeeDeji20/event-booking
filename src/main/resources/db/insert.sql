insert into event (id, name, category, event_status, max_attendees_count, declined_count, event_date, available_attendees_count) values
(5, 'dev games', 'GAME', 'ACTIVE', 100, 0, '2024-01-15 10:00:00.000000', 50),
(6, 'dev lottery', 'GAME', 'ACTIVE', 100, 0, '2024-01-15 10:00:00.000000', 50),
(7, 'dev hangout', 'GAME', 'ACTIVE', 50, 0, '2024-01-15 10:00:00.000000',100),
(8, 'dev zone', 'CONFERENCE', 'ACTIVE', 120, 0, '2024-01-15 10:00:00.000000', 0),
(9, 'dev buzz', 'GAME', 'ACTIVE', 150, 0, '2024-01-15 10:00:00.000000', 0);


insert into reservation (id, event_id, user_id, reservation_status, ticket_count) values
(100, 5, 1, 'BOOKED', 4),
(101, 6, 1, 'BOOKED', 1),
(103, 5, 1, 'BOOKED', 3),
(104, 8, 1, 'BOOKED', 5),
(105, 7, 1, 'CANCELED', 2);
