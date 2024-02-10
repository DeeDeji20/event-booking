insert into event (id, name, category, event_status, max_attendees_count, declined_count, event_date) values
(5, 'dev games', 'GAME', 'ACTIVE', 100, 0, '2024-01-15 10:00:00.000000'),
(6, 'dev lottery', 'GAME', 'ACTIVE', 100, 0, '2024-01-15 10:00:00.000000'),
(7, 'dev hangout', 'GAME', 'ACTIVE', 50, 0, '2024-01-15 10:00:00.000000'),
(8, 'dev zone', 'CONFERENCE', 'ACTIVE', 120, 0, '2024-01-15 10:00:00.000000'),
(9, 'dev buzz', 'GAME', 'ACTIVE', 150, 0, '2024-01-15 10:00:00.000000');


insert into reservation (id, event_id, user_id, reservation_status) values
(100, 5, 1, 'BOOKED'),
(101, 6, 1, 'BOOKED'),
(103, 5, 1, 'BOOKED'),
(104, 8, 1, 'BOOKED'),
(105, 7, 1, 'CANCELED');
