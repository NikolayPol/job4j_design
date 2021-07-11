create table product(
    id serial primary key ,
    name varchar(255),
    type_id int,
    expired_date date,
    price float
);

create table type(
    id serial primary key ,
    name text
);

insert into type(id, name) values (1, 'Сыр');
insert into type(id, name) values (2, 'Мороженое');
insert into type(id, name) values (3, 'Молоко');

insert into product(name, type_id, expired_date, price)
values ('Сыр плавленный', 1, date '2020-09-01', 100.0);

insert into product(name, type_id, expired_date, price)
values ('Сыр моцарелла', 1, date '2020-09-02', 200.0);

insert into product(name, type_id, expired_date, price)
values ('Сыр камамбер', 1, date '2020-09-03', 400.0);

insert into product(name, type_id, expired_date, price)
values ('Мороженое Баскин Роббинс', 2, date '2020-10-02', 500.0);

insert into product(name, type_id, expired_date, price)
values ('Мороженое Айскро', 2, date '2020-10-06', 300.0);

insert into product(name, type_id, expired_date, price)
values ('Молоко Valio', 3, date '2020-10-07', 150.0);

insert into product(name, type_id, expired_date, price)
values ('Молоко Parmalat', 3, date '2020-10-08', 40.0);

insert into product(name, type_id, expired_date, price)
values ('Молоко Домик в деревне', 3, date '2020-10-09', 30.0);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product inner join type on type_id=type.id
where type.name='Сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from product where name like 'Мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product where expired_date<'2021-07-11';

--4. Написать запрос, который выводит самый дорогой продукт.
select name, price from product
where price = (select max(price) from product);

--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select type.name as Имя_типа, count(type_id) as Количество
from product inner join type on product.type_id = type.id
group by type.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select product.name from product inner join type on product.type_id = type.id
where type.name='Сыр' OR type.name='Мороженое'OR type.name='Фрукты';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. Под количеством подразумевается количество продуктов определенного типа. Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
select t.name from type t inner join product p on p.type_id = t.id
group by t.name
having count(p.type_id) < 3;

--8. Вывести все продукты и их тип.
select p.name, t.name from product p
inner join type t on p.type_id = t.id

