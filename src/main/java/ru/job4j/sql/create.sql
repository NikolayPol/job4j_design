--drop table attachs, category, comments, item, role, rules, state, users;
--Пользователи
create table category(
    id serial primary key,
    name varchar(255)
);
--Состояние заявки
create table state(
    id serial primary key,
    name varchar(255)
);
--Роли
create table item(
    id serial primary key,
    name varchar(255),
    categoty_id int references category(id),
    state_id int references state(id)
);
--Права ролей
create table users(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);
--Заявки
create table role(
    id serial primary key,
    name varchar(255),
    user_id int references users(id)
);
--Комментарии заявок
create table rules(
    id serial primary key,
    name varchar(255),
    role_id int references role(id)
);
--Приложенные файлы
create table comments(
    id serial primary key,
    comment text,
    item_id int references item(id)
);
--Категории заявки
create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);
