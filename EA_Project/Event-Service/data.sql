INSERT INTO student (name) VALUES
    ('John Doe'),
    ('Jane Smith'),
    ('Robert Johnson'),
    ('Emily Davis'),
    ('Michael Brown');
    
INSERT INTO event (name, date, description, category) VALUES
    ('Alumni Reunion', '2023-12-01', 'Join us for a day of reconnecting with old friends.', 'Reunion'),
    ('Career Fair', '2023-11-15', 'Explore career opportunities with top employers.', 'Career'),
    ('Guest Speaker Series', '2023-11-20', 'Renowned speakers discussing industry trends.', 'Speaker'),
    ('Sports Tournament', '2023-12-10', 'Compete in various sports and showcase your skills.', 'Sports'),
    ('Community Outreach', '2023-11-25', 'Contribute to community service initiatives.', 'Community');
    
INSERT INTO event_students (event_id, student_id) VALUES
    (1, 1), -- Alumni Reunion: John Doe
    (1, 2), -- Alumni Reunion: Jane Smith
    (2, 3), -- Career Fair: Robert Johnson
    (2, 4), -- Career Fair: Emily Davis
    (3, 1), -- Guest Speaker Series: John Doe
    (3, 2), -- Guest Speaker Series: Jane Smith
    (4, 3), -- Sports Tournament: Robert Johnson
    (4, 4), -- Sports Tournament: Emily Davis
    (5, 1), -- Community Outreach: John Doe
    (5, 5); -- Community Outreach: Michael Brown
