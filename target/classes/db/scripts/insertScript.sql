use school;

INSERT INTO schools (id, school_name, location, established_date) VALUES
    (3, 'Elementary School', 'City X', '2005-09-15'),
    (4, 'Middle School', 'City X', '2005-09-15'),
    (5, 'High School', 'City X', '2005-09-15');


INSERT INTO departments (id, name, school_id) VALUES
                                                  (101, 'Mathematics', 1),
                                                  (102, 'Science', 1),
                                                  (103, 'History', 1),
                                                  (104, 'Computer Science', 1);


INSERT INTO teachers (id, first_name, last_name, hire_date, department_id) VALUES
    (1001, 'John', 'Smith', '2010-03-12', 101),
    (1002, 'Bob', 'Pavlov', '2010-03-12', 101),
    (1003, 'Daniel', 'Kotov', '2010-03-12', 102),
    (1004, 'Zee', 'Ivanov', '2010-03-12', 102);


INSERT INTO students (id, first_name, last_name, birthdate, department_id) VALUES
                                                                               (201, 'Emma', 'Johnson', '2003-05-20', 102),
                                                                               (202, 'Daniel', 'Williams', '2002-09-10', 102),
                                                                               (203, 'Olivia', 'Brown', '2004-01-15', 103);


INSERT INTO courses (course_name,credit) VALUES
    ( 'Algebra 101',2),
    ( 'Algebra 102',4),
    ( 'Algebra 103',8);


INSERT INTO onsite_courses (id, room_number, date, course_id) VALUES
                                                                  (301, 101, '2024-02-01 10:00:00', 1),
                                                                  (302, 102, '2024-02-03 14:30:00', 1);


INSERT INTO online_courses (id, url, course_id) VALUES
    (401, 'https://example.com/cs-course', 1);


INSERT INTO enrollments (id, enrollment_date, student_id, course_id) VALUES
                                                                         (10001, '2024-01-15 12:00:00', 201, 1),
                                                                         (10002, '2024-01-16 14:30:00', 202, 1);


INSERT INTO grades (id, enrollment_id, grade) VALUES  (50001, 10001, 'A');


INSERT INTO parents (id, first_name, last_name) VALUES
                                                    (1, 'David', 'Johnson'),
                                                    (2, 'Maria', 'Williams');


INSERT INTO students_parents (student_id, parent_id) VALUES
                                                         (201, 1),
                                                         (202, 2);


INSERT INTO teachers_departments (teacher_id, department_id) VALUES
    (1001, 101);


INSERT INTO attendance (id, onsite_course_id, status) VALUES
    (2001, 301, '2024-02-01', 'Present');