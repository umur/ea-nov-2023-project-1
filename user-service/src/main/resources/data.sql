-- Insert data into the alumni table
INSERT INTO role_type (name)
VALUES
    ("ADMIN"),
    ("ALUMNI"),
    ("FACULTY");

INSERT INTO alumni (is_deleted, mail, password, phone, profile_picture, role_id, location)
VALUES
    (0, 'john.doe@example.com', 'password123', '123-456-7890', 'john_doe.jpg', 1, "fairfield"),
    (0, 'jane.smith@example.com', 'secret456', '987-654-3210', 'jane_smith.jpg', 2, "san francisco"),
    (0, 'job_creator@example.com', 'secret123', '987-654-3210', 'job_creator.jpg', 1, "denver");

ALTER TABLE alumni AUTO_INCREMENT = 4;

