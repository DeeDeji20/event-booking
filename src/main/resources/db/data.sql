insert into user (id, name, email, password, created_at) values
(1, 'Adeola Oladeji', 'deolaoladeji@gmail.com', '$2a$10$.joVdHNdhNj0gkQL.AWRQe1qFbiN/3W7Z7JTlZQC/fzir.H.6u4u6', '2024-01-15 10:00:00.000000');

insert into user_authorities (authorities, user_id) values (0, 1);

insert into event (id, name, category, event_status, current_number_of_attendees, declined_count, event_date, available_attendees_count) values
(1, 'dev games', 'GAME', 'ACTIVE', 100, 0, '2024-01-15 10:00:00.000000', 50),
(6, 'dev lottery', 'GAME', 'ACTIVE', 100, 0, '2024-01-15 10:00:00.000000', 50),
(5, 'dev hangout', 'GAME', 'ACTIVE', 50, 0, '2024-01-15 10:00:00.000000',100),
(8, 'dev zone', 'CONFERENCE', 'ACTIVE', 120, 0, '2024-01-15 10:00:00.000000', 0),
(9, 'dev zone', 'CONFERENCE', 'ACTIVE', 120, 0, '2024-01-18 10:00:00.000000', 0),
(10, 'dev zone', 'CONFERENCE', 'ACTIVE', 120, 0, '2024-01-19 10:00:00.000000', 0),
(7, 'dev buzz', 'GAME', 'ACTIVE', 150, 0, '2024-01-15 10:00:00.000000', 0);


insert into reservation (id, event_id, user_id, reservation_status, ticket_count) values
(100, 1, 1, 'BOOKED', 4),
(101, 6, 1, 'BOOKED', 1),
(103, 5, 1, 'BOOKED', 3),
(104, 8, 1, 'BOOKED', 5),
(105, 7, 1, 'CANCELED', 2);
