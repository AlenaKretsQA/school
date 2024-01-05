-- 10 statements for deletions.
delete from teachers where id = 1;
delete from students where id = 1;
delete from courses where id = 1;
delete from enrollments where id = 1;
delete from parents where id = 1;
delete from teachers where id in (2, 3);
delete from students where id in (2, 3);
delete from courses where id in (2, 3);
delete from enrollments where id in (2, 3);
delete from parents where id in (1, 2);