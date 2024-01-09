create database if not exists school;
use school;

create table if not exists schools (
                                       id bigint primary key,
                                       school_name varchar(255) not null,
    location varchar(255) not null,
    established_date timestamp
    );

create table if not exists departments (
                                           id bigint primary key,
                                           name varchar(255) not null,
    school_id bigint,
    foreign key (school_id) references schools(id)
    );

create table if not exists teachers (
                                        id bigint primary key,
                                        first_name varchar(255) not null,
    last_name varchar(255) not null,
    hire_date timestamp,
    department_id bigint,
    foreign key (department_id) references departments(id)
    );

create table if not exists students (
                                        id bigint primary key,
                                        first_name varchar(255) not null,
    last_name varchar(255) not null,
    birthdate date,
    department_id bigint,
    foreign key (department_id) references departments(id)
    );

create table if not exists courses (
                                       id bigint primary key auto_increment,
                                       course_name varchar(255) not null,
    credit double
    );

create table if not exists onsite_courses (
                                              id bigint primary key,
                                              room_number tinyint,
                                              date timestamp,
                                              course_id bigint,
                                              foreign key (course_id) references courses(id)
    );

create table if not exists online_courses (
                                              id bigint primary key,
                                              url varchar(255),
    course_id bigint,
    foreign key (course_id) references courses(id)
    );

create table if not exists enrollments (
                                           id bigint primary key,
                                           enrollment_date timestamp,
                                           student_id bigint,
                                           course_id bigint,
                                           unique key unique_enrollment (student_id, course_id),
    foreign key (student_id) references students(id),
    foreign key (course_id) references courses(id)
    );

create table if not exists grades (
                                      id bigint primary key,
                                      enrollment_id bigint,
                                      grade varchar(45)
    );

create table if not exists parents (
                                       id bigint primary key,
                                       first_name varchar(255) not null,
    last_name varchar(255) not null
    );

create table if not exists students_parents (
                                                student_id bigint,
                                                parent_id bigint,
                                                primary key (student_id, parent_id),
    foreign key (student_id) references students(id),
    foreign key (parent_id) references parents(id)
    );

create table if not exists teachers_departments (
                                                    teacher_id bigint,
                                                    department_id bigint,
                                                    primary key (teacher_id, department_id),
    foreign key (teacher_id) references teachers(id),
    foreign key (department_id) references departments(id)
    );

create table if not exists attendance (
                                          id bigint primary key,
                                          onsite_course_id bigint,

                                          status varchar(255),
    foreign key (onsite_course_id) references onsite_courses(id)
    );
