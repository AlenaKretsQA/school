-- Update teachers table
UPDATE teachers SET last_name = 'Johnson' WHERE id = 1001;

UPDATE students SET birthdate = '2002-09-11' WHERE id = 202;

UPDATE courses SET credits = 4.0 WHERE id = 1;

UPDATE onsite_courses SET room_number = 103 WHERE id = 301;

UPDATE enrollments SET enrollment_date = '2024-01-17 09:00:00' WHERE id = 10001;

UPDATE grades SET grade = 'A+' WHERE id = 50001;

UPDATE parents SET last_name = 'Smith' WHERE id = 1;

UPDATE students_parents SET parent_id = 1 WHERE student_id = 202;

UPDATE teachers_departments SET department_id = 102 WHERE teacher_id = 1001;

UPDATE attendance SET status = 'Absent' WHERE id = 2001;

