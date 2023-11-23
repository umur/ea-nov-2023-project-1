-- Insert data into the log table
INSERT INTO publication_portal.log (date, duration, operation)
VALUES ('2023-04-15 14:05:00', 120, 'Event Sign-In'),
       ('2023-05-20 10:15:00', 90, 'Job Fair Registration');

INSERT INTO publication_type (id, name)
VALUES
    (1, 'news'),
    (2, 'updates'),
    (3, 'announcements');

-- Insert data into the publication table
INSERT INTO publication_portal.publication (is_deleted, publication_type_id, description)
VALUES (0, 1, 'news from maharishi'),
       (0, 3, 'announcement for tm');

-- Set auto-increment for publication_type table
ALTER TABLE publication_type AUTO_INCREMENT = 4;

-- Set auto-increment for log table
ALTER TABLE  publication_portal.log AUTO_INCREMENT = 3;

-- Set auto-increment for publication table
ALTER TABLE  publication_portal.publication AUTO_INCREMENT = 3;



