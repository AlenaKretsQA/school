-- 5 alter table
alter table teachers add column email varchar(255);
update teachers set email = 'petr.petrova@email.com' where id = 2;
alter table teachers drop column email;
alter table students add column gender char(1);
update students set gender = 'f' where id = 1;
