
INSERT INTO student (graduation_year, description, category, industry) VALUES
    ('2020', 'Computer Science graduate', 'Technology', 'IT'),
    ('2019', 'Business Administration graduate', 'Business', 'Finance'),
    ('2021', 'Engineering graduate', 'Engineering', 'Mechanical'),
    ('2018', 'Marketing graduate', 'Marketing', 'Advertising'),
    ('2017', 'Health Sciences graduate', 'Health', 'Medical Research');
    
INSERT INTO job (company_name, industry, description, student_id, address) VALUES
    ('TechCo', 'Technology', 'Developing cutting-edge software applications', 1, '123 Main St, City1, State1, Country1, 12345'),
    ('AdAgency', 'Marketing', 'Creating and executing marketing campaigns', 2, '456 Oak St, City2, State2, Country2, 56789'),
    ('EngCorp', 'Engineering', 'Designing and testing mechanical systems', 3, '789 Pine St, City3, State3, Country3, 98765'),
    ('FinTech', 'Finance', 'Analyzing financial data and providing insights', 4, '101 Elm St, City4, State4, Country4, 54321'),
    ('HealthLab', 'Health', 'Conducting research to advance medical knowledge', 5, '202 Cedar St, City5, State5, Country5, 67890');

INSERT INTO job_applied_students (job_id, student_id) VALUES
    (1, 2), 
    (2, 3), 
    (3, 4),
    (4, 5),
    (5, 1); 