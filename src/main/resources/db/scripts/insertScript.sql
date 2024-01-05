-- 10 statements for insertion (single and batch insertions)
insert into schools (id, school_name, location, established_date) values (1, 'school #1', 'woodinville', '2022-01-01');
insert into schools (id, school_name, location, established_date) values (2, 'school #2', 'bellevue', '2014-10-01');
insert into departments (id, name, school_id) values (1, 'mathematics', 1);
insert into departments (id, name, school_id) values (2, 'literature', 1);
insert into teachers (id, first_name, last_name, hire_date, department_id) values (1, 'petr', 'petrov', '2022-01-01', 1);
insert into teachers (id, first_name, last_name, hire_date, department_id) values (2, 'ivan', 'ivanov', '2022-10-01', 1);
insert into students (id, first_name, last_name, birthdate, department_id) values (1, 'alice', 'smith', '2000-01-10', 1);
insert into students (id, first_name, last_name, birthdate, department_id) values (2, 'alena', 'krets', '2000-01-01', 1);
insert into courses (id, course_name, credits) values (1, 'algebra', 3.0);
insert into courses (id, course_name, credits) values (2, 'athithmetic basics', 4.0);

insert into teachers (id, first_name, last_name, hire_date, department_id) values
                                                                               (3, 'jane', 'doe', '2022-02-01', 1),
                                                                               (4, 'bob', 'johnson', '2022-03-01', 1);
insert into students (id, first_name, last_name, birthdate, department_id) values
                                                                               (3, 'bob', 'smith', '1999-05-15', 1),
                                                                               (4, 'eve', 'johnson', '2001-08-20', 1);
insert into courses (id, course_name, credits) values
                                                   (3, 'geometry', 4.0),
                                                   (4, 'history', 3.5);
insert into enrollments (id, enrollment_date, student_id, course_id) values
                                                                         (1, '2022-01-10', 1, 1),
                                                                         (2, '2022-02-15', 2, 1),
                                                                         (3, '2022-02-20', 3, 2);
insert into parents (id, first_name, last_name) values
                                                    (1, 'mary', 'doe'),
                                                    (2, 'robert', 'smith');
