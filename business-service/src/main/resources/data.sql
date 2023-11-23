-- Insert data into the company table
INSERT INTO company (is_deleted, industry, name)
VALUES
    (0, 'Technology', 'TechCorp Inc'),
    (0, 'Finance', 'FinanceX Ltd');

-- Insert data into the open_job table
INSERT INTO open_job (is_deleted, company_id, creator_id, open_date, description)
VALUES
    (0, 1, 1, '2023-05-01 08:00:00', 'Software Engineer Position'),
    (0, 2, 1, '2023-06-10 09:30:00', 'Financial Analyst Job');

-- Insert data into the open_job_alumni_applications table
INSERT INTO application (alumni_id, open_job_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1);

-- Insert data into the prof_experience table
INSERT INTO prof_experience (is_deleted, alumni_id, company_id, start_date, end_date)
VALUES
    (0, 1, 1, '2022-01-15', '2023-03-30'),
    (0, 1, 2, '2024-01-15', null),
    (0, 2, 2, '2021-11-20', null),
    (0, 3, 2, '2021-11-20', null);

INSERT INTO address (id, street, city, state, zip, is_deleted)
VALUES
    (1, '123 Main St', 'New York', 'NY', '10001', false),
    (2, '456 Elm St', 'Los Angeles', 'CA', '90001', false),
    (3, '789 Oak St', 'Chicago', 'IL', '60601', false),
    (4, '4 Oak St', 'Fairfield', 'IA', '60601', false);

-- Update Company, University, and Alumni tables with the corresponding address
UPDATE company
SET address_id = 1
WHERE id = 1;

UPDATE company
SET address_id = 4
WHERE id = 2;


-- Set auto-increment for company table
ALTER TABLE company AUTO_INCREMENT = 3;

-- Set auto-increment for open_job table
ALTER TABLE open_job AUTO_INCREMENT = 3;

-- Set auto-increment for open_job_alumni_applications table
ALTER TABLE application AUTO_INCREMENT = 3;

-- Set auto-increment for prof_experience table
ALTER TABLE prof_experience AUTO_INCREMENT = 5;

-- Set auto-increment for Address table
ALTER TABLE address AUTO_INCREMENT = 5;

