-- Users-- 
INSERT INTO users (
        id,
        first_name,
        last_name,
        email,
        password,
        graduation_year,
        educational_details,
        industry,
        professional_achievements,
        profile_pic
    )
VALUES (
        1,
        'John',
        'Doe',
        'A@yahoo.com',
        '123',
        null,
        null,
        null,
        null,
        null
    ),
    (
        2,
        'Alice',
        'Smith',
        'B@gmail.com',
        '456',
        null,
        null,
        null,
        null,
        null
    ),
    (
        3,
        'Bob',
        'Johnson',
        'C@hotmail.com',
        '789',
        null,
        null,
        null,
        null,
        null
    ),
    (
        4,
        'Emily',
        'Wilson',
        'D@outlook.com',
        '101',
        null,
        null,
        null,
        null,
        null
    ),
    (
        5,
        'Michael',
        'Brown',
        'E@gmail.com',
        '202',
        null,
        null,
        null,
        null,
        null
    ),
    (
        6,
        'Sarah',
        'Davis',
        'F@gmail.com',
        '303',
        null,
        null,
        null,
        null,
        null
    ),
    (
        7,
        'David',
        'Lee',
        'G@yahoo.com',
        '404',
        null,
        null,
        null,
        null,
        null
    ),
    (
        8,
        'Jennifer',
        'Clark',
        'H@hotmail.com',
        '505',
        null,
        null,
        null,
        null,
        null
    ),
    (
        9,
        'Kevin',
        'Miller',
        'I@gmail.com',
        '606',
        null,
        null,
        null,
        null,
        null
    ),
    (
        10,
        'Laura',
        'Turner',
        'J@yahoo.com',
        '707',
        null,
        null,
        null,
        null,
        null
    );
-- Users-- 
-- Jobs-- 
-- Jobs with Users
INSERT INTO jobs (
        id,
        user_id,
        description,
        name,
        requirements
    )
VALUES (
        1,
        1,
        'Software Developer position for a tech startup.',
        'Software Developer',
        'Bachelors degree in Computer Science, 3 years of experience.'
    ),
    (
        2,
        2,
        'Marketing Manager position for a growing company.',
        'Marketing Manager',
        'Bachelors degree in Marketing, 5 years of experience.'
    ),
    (
        3,
        3,
        'Accountant role at a reputable accounting firm.',
        'Accountant',
        'Bachelors degree in Accounting, CPA certification.'
    ),
    (
        4,
        4,
        'Sales Representative job with a competitive commission structure.',
        'Sales Representative',
        'High school diploma, excellent communication skills.'
    ),
    (
        5,
        5,
        'Graphic Designer role for a creative agency.',
        'Graphic Designer',
        'Bachelors degree in Graphic Design, proficiency in Adobe Creative Suite.'
    );
-- Jobs without Users
INSERT INTO jobs (
        id,
        user_id,
        description,
        name,
        requirements
    )
VALUES (
        6,
        null,
        'Customer Service Representative position for a call center.',
        'Customer Service Representative',
        'High school diploma, good communication skills.'
    ),
    (
        7,
        null,
        'Data Analyst role at a data analytics company.',
        'Data Analyst',
        'Bachelors degree in Statistics, experience with data analysis tools.'
    ),
    (
        8,
        null,
        'Warehouse Associate job at a logistics company.',
        'Warehouse Associate',
        'High school diploma, ability to lift heavy objects.'
    ),
    (
        9,
        null,
        'Nurse position at a local hospital.',
        'Nurse',
        'Bachelors degree in Nursing, RN license.'
    ),
    (
        10,
        null,
        'Chef position at a fine dining restaurant.',
        'Chef',
        'Culinary degree, experience in fine dining kitchens.'
    );
-- Jobs-- 
-- Job Applications-- 
INSERT INTO applications (status, id, job_id, user_id)
VALUES (0, 1, 1, 1),
    (1, 2, 2, 2),
    (0, 3, 3, 3),
    (2, 4, 4, 4),
    (0, 5, 5, 5),
    (1, 6, 6, 6),
    (2, 7, 7, 7),
    (0, 8, 8, 8),
    (1, 9, 9, 9),
    (0, 10, 10, 10);
-- Job Applications-- 
-- Job Postings -- 
INSERT INTO postings (status, creation_date, id, job_id, poster_id)
VALUES (0, '2023-11-01', 1, 1, 1),
    (0, '2023-11-02', 2, 2, 2),
    (1, '2023-11-03', 3, 3, 3),
    (0, '2023-11-04', 4, 4, 4),
    (1, '2023-11-05', 5, 5, 5),
    (0, '2023-11-06', 6, 6, 6),
    (0, '2023-11-07', 7, 7, 7),
    (1, '2023-11-08', 8, 8, 8),
    (1, '2023-11-09', 9, 9, 9),
    (0, '2023-11-10', 10, 10, 10);
-- Job Postings --
-- Event Types -- 
INSERT INTO event_types (id, name)
VALUES (1, 'Reunions'),
    (2, 'Professional Networking Events'),
    (3, 'Workshops'),
    (4, 'Seminars');
-- Event Types --
-- Events -- 
INSERT INTO events (event_date, id, type_id, name, location)
VALUES -- Events of type 'Reunions'
    (
        '2023-11-10',
        1,
        1,
        'Reunion Event 1',
        'Location 1'
    ),
    (
        '2023-11-15',
        2,
        1,
        'Reunion Event 2',
        'Location 2'
    ),
    (
        '2023-11-15',
        3,
        1,
        'Reunion Event 3',
        'Location 3'
    ),
    (
        '2023-11-15',
        4,
        1,
        'Reunion Event 4',
        'Location 4'
    ),
    -- Events of type 'Professional Networking Events'
    (
        '2023-12-05',
        5,
        2,
        'Networking Event 1',
        'Location 5'
    ),
    (
        '2023-12-10',
        6,
        2,
        'Networking Event 2',
        'Location 6'
    ),
    (
        '2023-12-10',
        7,
        2,
        'Networking Event 3',
        'Location 7'
    ),
    (
        '2023-12-10',
        8,
        2,
        'Networking Event 4',
        'Location 8'
    ),
    -- Events of type 'Workshops'
    (
        '2023-11-30',
        9,
        3,
        'Workshop Event 1',
        'Location 9'
    ),
    (
        '2023-11-25',
        10,
        3,
        'Workshop Event 2',
        'Location 10'
    ),
    (
        '2023-11-25',
        11,
        3,
        'Workshop Event 3',
        'Location 11'
    ),
    (
        '2023-11-25',
        12,
        3,
        'Workshop Event 4',
        'Location 12'
    ),
    -- Events of type 'Seminars'
    (
        '2023-12-15',
        13,
        4,
        'Seminar Event 1',
        'Location 13'
    ),
    (
        '2023-11-20',
        14,
        4,
        'Seminar Event 2',
        'Location 14'
    ),
    (
        '2023-11-20',
        15,
        4,
        'Seminar Event 3',
        'Location 15'
    ),
    (
        '2023-11-20',
        16,
        4,
        'Seminar Event 4',
        'Location 16'
    );
-- Events --
-- User Organizing Events --
INSERT INTO user_organizing_event (event_id, organizer_id)
VALUES (3, 1),
    (4, 1),
    (7, 2),
    (11, 3),
    (15, 4),
    (5, 5),
    (6, 6),
    (12, 7),
    (16, 8),
    (1, 9),
    (10, 10);
-- User Organizing Events --
-- User Attending Events -- 
INSERT INTO user_attending_event (attendee_id, event_id)
VALUES (1, 1),
    (1, 5),
    (2, 2),
    (2, 6),
    (3, 3),
    (3, 7),
    (4, 4),
    (4, 8),
    (5, 9),
    (5, 13),
    (6, 10),
    (6, 14),
    (7, 11),
    (7, 15),
    (8, 12),
    (8, 16),
    (9, 5),
    (9, 14),
    (10, 6),
    (10, 15);
-- User Attending Events --
-- User Rsvp Events --
INSERT INTO user_rsvped_event (rsvper_Id, event_id)
VALUES (1, 1),
    (1, 5),
    (2, 2),
    (2, 6),
    (3, 3),
    (3, 7),
    (4, 4),
    (4, 8),
    (5, 9),
    (5, 13),
    (6, 10),
    (6, 14),
    (7, 11),
    (7, 15),
    (8, 12),
    (8, 16),
    (9, 5),
    (9, 14),
    (10, 6),
    (10, 15);
-- User Rsvp Events --