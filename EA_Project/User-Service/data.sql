INSERT INTO role (name) VALUES
    ('Admin'),
    ('Student');
    
INSERT INTO user (email, password, active, deleted, role_id) VALUES
    ('admin@example.com', 'admin123', true, false, 1), -- Admin
    ('student1@example.com', 'student123', true, false, 2),
    ('student2@example.com', 'student223', true, false, 2),
    ('student3@example.com', 'student323', true, false, 2),
    ('student4@example.com', 'student423', true, false, 2),
    ('student5@example.com', 'student523', true, false, 2);