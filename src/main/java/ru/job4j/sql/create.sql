--drop table attachs, category, comments, item, role, rules, state, users;

--Категории заявки
create table category(
    id serial primary key,
    name varchar(255)
);
--Состояние заявки
create table state(
    id serial primary key,
    name varchar(255)
);
--Заявки
create table item(
    id serial primary key,
    name varchar(255),
    category_id int references category(id),
    state_id int references state(id)
);

--Пользователи
create table users(
    id serial primary key,
    name varchar(255),
    role_id int references role(id)
);

--Роли
create table role(
    id serial primary key,
    name varchar(255),
    rules_id int references rules(id)

);

--таблица для связи role-rules
create table role_rules(
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);

--Права ролей
create table rules(
    id serial primary key,
    name varchar(255)
);

--Комментарии заявок
create table comments(
    id serial primary key,
    comment text,
    item_id int references item(id)
);

--Приложенные файлы
create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int references attachs(id)
);
