

-- Insert data into the feedback table
INSERT INTO feedback_portal.feedback (is_deleted, alumni_id, description)
VALUES
    (0, 1, 'Great event!'),
    (0, 2, 'Could have been better.');


ALTER TABLE feedback_portal.feedback AUTO_INCREMENT = 3;


ALTER TABLE feedback_portal.log AUTO_INCREMENT = 3;

