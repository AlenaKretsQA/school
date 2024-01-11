-- 1 big statement to join all tables in the database (use at least 5 tables related to each other, exclude many-to-many connection table, use aliases for this one, and choose only necessary fields from tables).
select
    s.id as student_id,
    s.first_name as student_first_name,
    s.last_name as student_last_name,
    d.name as department_name,
    t.id as teacher_id,
    t.first_name as teacher_first_name,
    t.last_name as teacher_last_name,
    c.course_name,
    e.enrollment_date
from students s
         join departments d on s.department_id = d.id
         join enrollments e on s.id = e.student_id
         join courses c on e.course_id = c.id
         join teachers_departments td on d.id = td.department_id
         join teachers t on td.teacher_id = t.id;

-- 5 statements with left, right, inner  joins (use aliases for columns and tables).
-- left join
select students.first_name, enrollments.enrollment_date
from students
         left join enrollments on students.id = enrollments.student_id;

-- right join
select enrollments.enrollment_date, students.first_name
from enrollments
         right join students on enrollments.student_id = students.id;

-- inner join
select students.first_name, enrollments.enrollment_date
from students
         inner join enrollments on students.id = enrollments.student_id;

-- left join with multiple tables
select students.first_name, enrollments.enrollment_date, courses.course_name
from students
         left join enrollments on students.id = enrollments.student_id
         left join courses on enrollments.course_id = courses.id;

-- inner join with multiple tables
select students.first_name, enrollments.enrollment_date, courses.course_name
from students
         inner join enrollments on students.id = enrollments.student_id
         inner join courses on enrollments.course_id = courses.id;

-- 7 statements with aggregate functions + group by + having.
-- count of students in each department
select department_id, count(*) as student_count
from students
group by department_id
having student_count > 1;

-- average credits of courses in each department
select departments.name as department_name, avg(courses.credits) as avg_credits
from departments
         inner join teachers_departments on departments.id = teachers_departments.department_id
         inner join teachers on teachers_departments.teacher_id = teachers.id
         inner join courses on teachers.id = courses.id
group by department_name
having avg_credits > 3.0;

-- maximum enrollment date for each student
select student_id, max(enrollment_date) as max_enrollment_date
from enrollments
group by student_id
having max_enrollment_date is not null;

-- sum of credits for each student
select student_id, sum(credits) as total_credits
from enrollments
         join courses on enrollments.course_id = courses.id
group by student_id
having total_credits > 10.0;

-- count of teachers in each department
select department_id, count(*) as teacher_count
from teachers_departments
group by department_id
having teacher_count > 2;