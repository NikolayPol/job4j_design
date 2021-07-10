create table passport(
    id serial primary key,
    seria inet,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    passport_id int references passport(id) unique
);