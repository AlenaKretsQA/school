-- 10 statements for updating.
update teachers set last_name = 'petrova' where id = 1;
update students set birthdate = '1999-06-01' where id = 1;
update courses set credits = 4.5 where id = 1;
update enrollments set enrollment_date = '2022-01-15' where id = 1;
update parents set last_name = 'johnson' where id = 1;

update teachers set hire_date = '2022-02-15' where id in (2, 3);
update students set birthdate = '2000-01-10' where id in (2, 3);
update courses set credits = 3.8 where id in (2, 3);
update enrollments set enrollment_date = '2022-02-01' where id in (2, 3);
update parents set last_name = 'smith' where id in (1, 2);
