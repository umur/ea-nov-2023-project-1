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

-- chats
INSERT INTO chats (id, is_group, name, created_at)
values (1, false, 'one to one 1', '2023-11-01'),
       (2, false, 'one to one 2', '2023-11-02'),
       (3, false, 'one to one 3', '2023-11-03'),
       (4, false, 'one to one 4', '2023-11-04'),
       (5, false, 'one to one 5', '2023-11-05'),
       (6, true, 'group1', '2023-11-06'),
       (7, true, 'group2', '2023-11-07'),
       (8, true, 'group3', '2023-11-08'),
       (9, true, 'group4', '2023-11-09'),
       (10, true, 'group5', '2023-11-10');

--  chat_participants
INSERT INTO chats_participants (chat_id, participants_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 1),
       (3, 4),
       (4, 1),
       (4, 5),
       (5, 1),
       (5, 6),
       (6, 1),
       (6, 2),
       (6, 3),
       (7, 1),
       (7, 2),
       (7, 3),
       (8, 1),
       (8, 2),
       (8, 3),
       (9, 1),
       (9, 2),
       (9, 3),
       (10, 1),
       (10, 2),
       (10, 3);

-- messages
INSERT INTO chat_messages (id, chat_id, sender_id, type, content, created_at)
values (1, 1, 1, 'text', 'hello', '2023-11-01'),
       (2, 1, 2, 'text', 'hi', '2023-11-02'),
       (3, 1, 1, 'text', 'how are you?', '2023-11-03'),
       (4, 1, 2, 'text', 'I am fine', '2023-11-04'),
       (5, 1, 1, 'text', 'what are you doing?', '2023-11-05'),
       (6, 1, 2, 'text', 'nothing much', '2023-11-06'),
       (7, 1, 1, 'text', 'ok', '2023-11-07'),
       (8, 1, 2, 'text', 'bye', '2023-11-08'),
       (9, 2, 1, 'text', 'hello', '2023-11-09'),
       (10, 2, 3, 'text', 'hi', '2023-11-10'),
       (11, 2, 1, 'text', 'how are you?', '2023-11-11'),
       (12, 2, 3, 'text', 'I am fine', '2023-11-12'),
       (13, 2, 1, 'text', 'what are you doing?', '2023-11-13'),
       (14, 2, 3, 'text', 'nothing much', '2023-11-14'),
       (15, 2, 1, 'text', 'ok', '2023-11-15'),
       (16, 2, 3, 'text', 'bye', '2023-11-16'),
       (17, 3, 1, 'text', 'hello', '2023-11-17'),
       (18, 3, 4, 'text', 'hi', '2023-11-18'),
       (19, 3, 1, 'text', 'how are you?', '2023-11-19'),
       (20, 3, 4, 'text', 'I am fine', '2023-11-20');