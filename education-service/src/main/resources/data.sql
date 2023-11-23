#
# -- Insert data into the university table
# INSERT INTO university (is_deleted, name)
# VALUES
#     (0, 'ABC University'),
#     (0, 'XYZ University');
#
# -- Insert data into the course table
# INSERT INTO course (code, is_deleted, university_id, description, name)
# VALUES
#     (101, 0, 1, 'Introduction to Computer Science', 'CS101'),
#     (201, 0, 2, 'Financial Management', 'FIN201');
#
# -- Insert data into the edu_experience table
# INSERT INTO edu_experience (is_deleted, alumni_id, university_id, degree, start_date, end_date)
# VALUES
#     (0, 1, 1, 'Bachelor of Science', '2018-09-01', '2022-05-15');
#
# -- Insert data into the edu_experience_course table
# INSERT INTO edu_experience_course (edu_experience_id, course_id)
# VALUES
#     (1, 1);
#
#
#
# -- Set auto-increment for university table
# ALTER TABLE university AUTO_INCREMENT = 3;
#
# -- Set auto-increment for course table
# ALTER TABLE course AUTO_INCREMENT = 3;
#
# -- Set auto-increment for edu_experience table
# ALTER TABLE edu_experience AUTO_INCREMENT = 2;
#
# -- Set auto-increment for edu_experience_course table
# ALTER TABLE edu_experience_course AUTO_INCREMENT = 2;
#
#
