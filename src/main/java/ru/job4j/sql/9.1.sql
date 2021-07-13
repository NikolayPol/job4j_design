--Создать структур данных в базе.
-- Таблицы:
-- Кузов. Двигатель, Коробка передач.
-- Создать структуру Машина. Машина не может существовать без данных из п.1.
-- Заполнить таблицы через insert.
create table body(
    id   serial primary key,
    name text
);

create table engine(
    id   serial primary key,
    name text
);

create table transmission(
    id   serial primary key,
    name text
);

create table car(
    id              serial primary key,
    name            text,
    body_id         int references body (id),
    engine_id       int references engine (id),
    transmission_id int references transmission (id)
);

insert into body(name) values ('body1');
insert into body(name) values ('body2');
insert into engine(name) values ('engine1');
insert into engine(name) values ('engine2');
insert into transmission(name) values ('transmission1');
insert into transmission(name) values ('transmission2');
insert into car(name, body_id, engine_id, transmission_id)
values ('car', 1, 1, 1);

--Создать SQL запросы:
--1) Вывести список всех машин и все привязанные к ним детали.
select car.name from car
union
select body.name from body inner join car c on body.id = c.body_id
union
select e.name from engine e inner join car c on e.id = c.body_id
union
select t.name from transmission t inner join car c on t.id = c.body_id;

--2) Вывести отдельно детали (1 деталь - 1 запрос), которые
--не используются НИ в одной машине, кузова, двигатели, коробки передач.
select b.name as Неиспользуемые_детали from body b full join car c on b.id = c.body_id
where body_id is null
union
select e.name as Неиспользуемые_детали from engine e full join car c on e.id = c.body_id
where c.engine_id is null
union
select t.name as Неиспользуемые_детали from transmission t full join car c on t.id = c.body_id
where c.transmission_id is null;