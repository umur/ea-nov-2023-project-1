
INSERT INTO survey (id, title, description, start_date, end_date,deleted)
VALUES (1, 'Survey 1', 'This is the first survey', '2020-01-01', '2020-01-31',0),
       (2, 'Survey 2', 'This is the second survey', '2020-02-01', '2020-02-28',0);

INSERT INTO survey_answer (id, survey_id, user_id, answer,deleted)
VALUES
    (1,1,1,"Hello",0),
    (2,1,2,"Hello",0);