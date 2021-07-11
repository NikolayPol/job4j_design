--1. Создать таблицы и заполнить их начальными данными
create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)

);

insert into departments(name) values ('IT');
insert into departments(name) values ('Sale');
insert into departments(name) values ('Law');

insert into emploees(name, departments_id) values ('Ivan', 1);
insert into emploees(name, departments_id) values ('Petr', 1);
insert into emploees(name, departments_id) values ('Dmitry', 2);
insert into emploees(name, departments_id) values ('Parfiry', 2);

--2. Выполнить запросы с left, rigth, full, cross соединениями
select * from emploees e inner join departments d on e.departments_id = d.id;
select * from emploees e left join departments d on e.departments_id = d.id;
select * from emploees e right join departments d on e.departments_id = d.id;
select * from emploees e full join departments d on e.departments_id = d.id;

--3. Используя left join найти департаменты, у которых нет работников
select * from departments d left join emploees e on d.id = e.departments_id
where e.departments_id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from departments d left join emploees e on d.id = e.departments_id;
select * from emploees e right join departments d on e.departments_id=d.id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- Используя cross join составить все возможные разнополые пары
create table teens(
    id serial primary key,
    name varchar(255),
    gender text
);

insert into teens(name, gender) values ('Ivan', 'm');
insert into teens(name, gender) values ('Pavel', 'm');
insert into teens(name, gender) values ('Petr', 'm');
insert into teens(name, gender) values ('Daria', 'w');
insert into teens(name, gender) values ('Natalia', 'w');
insert into teens(name, gender) values ('Maria', 'w');

select * from teens t1 cross join teens t2 where t1.gender!=t2.gender;