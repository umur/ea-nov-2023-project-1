INSERT INTO event_type (id, name)
VALUES (1, 'reunions'),
       (2, 'professional networking events'),
       (3, 'workshops'),
       (4, 'seminars');


-- Insert data into the event_type table
INSERT INTO event_portal.event_type (name)
VALUES ('Networking Event'),
       ('Job Fair'),
       ('Workshop');

-- Insert data into the event table
INSERT INTO event_portal.event (is_deleted, event_type_id, description, start_date, end_date)
VALUES (0, 1, 'e1', '2023-04-15 14:00:00', '2023-04-15 18:00:00'),
       (0, 2, 'e2', '2023-05-20 10:00:00', '2023-05-20 16:00:00');

-- Insert data into the alumni_event table
INSERT INTO event_portal.alumni_event (is_accepted, is_attended, is_deleted, alumni_id, event_id)
VALUES (1, 1, 0, 1, 1),
       (1, 0, 0, 2, 1);


-- Insert data into the log table
INSERT INTO event_portal.log (date, duration, operation)
VALUES ('2023-04-15 14:05:00', 120, 'Event Sign-In'),
       ('2023-05-20 10:15:00', 90, 'Job Fair Registration');


-- Set auto-increment for event_type table
ALTER TABLE event_type
    AUTO_INCREMENT = 5;


-- Set auto-increment for event_type table
ALTER TABLE event_portal.event_type
    AUTO_INCREMENT = 4;

-- Set auto-increment for event table
ALTER TABLE event_portal.event
    AUTO_INCREMENT = 3;

-- Set auto-increment for alumni_event table
ALTER TABLE event_portal.alumni_event
    AUTO_INCREMENT = 3;


-- Set auto-increment for log table
ALTER TABLE event_portal.log
    AUTO_INCREMENT = 3;




