--select
select * from universities;
select name from universities where name='U5';

select * from students;
select name from students;
select speciality from students;
select name, course, speciality from students;

--Фильтрация, Операторы сравнения =, <, >, <=, >=, !=, is null, is not null
select name, course, speciality from students where course!=2;
select name, course, speciality from students where course is not null;
select * from students where enroll_date>'01.08.2020';
select * from students where course > 2;

--LIKE, OR, AND
select * from students where speciality like'M%';
select * from students where speciality like'%1';
select * from students where speciality like'%1' and course>2;
select * from students where speciality like'%1' or course>2;

--Работа с датами
select current_date;
select current_date>'10.09.2020';
select current_date + interval '1 month';

--Упорядочение ORDER BY
select * from students;
select * from students order by speciality asc;
select * from students order by speciality desc;

--Выборка уникальных элементов SELECT DISTINCT
select distinct course from students;

--Выборка определенного числа элементов LIMIT
select * from students;
select * from students limit 5;