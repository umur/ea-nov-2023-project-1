INSERT INTO survey (name, description) VALUES
    ('Feedback Survey', 'Gather feedback on the alumni program'),
    ('Interest Survey', 'Understand students'' interests in various activities'),
    ('Event Satisfaction Survey', 'Collect feedback on recent events');
    
INSERT INTO survey_response (survey_id, student_id, response) VALUES
    (1, 1, 'Positive'),
    (1, 2, 'Neutral'),
    (1, 3, 'Negative'),
    (2, 4, 'Positive'),
    (2, 5, 'Positive'),
    (3, 1, 'Negative'),
    (3, 2, 'Positive');
