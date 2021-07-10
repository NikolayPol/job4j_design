--one-to-many
create table notebooks(
    id serial primary key,
    name varchar(255)
);

create table softs(
    id serial primary key,
    name varchar(255) references notebooks(id)
);

--many-to-many
create table notebooks(
    id serial primary key,
    name varchar(255) references softs(id)
);

create table softs(
    id serial primary key,
    name varchar(255) references notebooks(id)
);

--one-to-one
create table notebooks(
    id serial primary key,
    name varchar(255)
);

create table softs(
    id serial primary key,
    name varchar(255),
    notebook_id int references notebooks(id) unique
);
