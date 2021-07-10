create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Device1', 100.0);
insert into devices(name, price) values ('Device2', 200.0);
insert into devices(name, price) values ('Device3', 300.0);

insert into people(name) values ('Ivan');
insert into people(name) values ('Boris');
insert into people(name) values ('Petr');

insert into devices_people(people_id, device_id) values (1,1), (1,3), (2,1), (2,2), (3,3);
--alter sequence devices_people_id_seq restart with 1;

--Используя агрегатные функции вывести среднюю цену устройств.
select avg(price) from devices;

--Используя группировку вывести для каждого человека среднюю цену его устройств.
select dp.people_id, avg(d.price)
from devices_people dp inner join devices d on dp.device_id = d.id
group by dp.people_id;

--Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 5000.
select dp.people_id, avg(d.price)
from devices_people dp inner join devices d on dp.device_id = d.id
group by dp.people_id
having avg(price) > 200;