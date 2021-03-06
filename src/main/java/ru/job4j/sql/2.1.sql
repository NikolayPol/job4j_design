create table position(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    position_id int references position(id)
);

insert into position(name) values ('programmer');
insert into position(name) values ('Javaprogrammer');
insert into employees(name, position_id) values ('Ivan', 1);
insert into employees(name, position_id) values ('Ivan', 2);

select * from employees;
select * from position;
select * from position where id in (select id from employees);