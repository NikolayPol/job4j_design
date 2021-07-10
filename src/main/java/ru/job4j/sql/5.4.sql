create table noteboook(
    id serial primary key,
    name varchar(255)
);

create table softs(
    id serial primary key,
    name varchar(255),
    notebook_id int references noteboook(id)
);

insert into noteboook(name) values ('HP');
insert into noteboook(name) values ('MacBook');
insert into noteboook(name) values ('MSI');

insert into softs(name, notebook_id) values ('IntellijIdea', 1);
insert into softs(name, notebook_id) values ('DataGRip', 1);
insert into softs(name, notebook_id) values ('Postgres', 2);
insert into softs(name, notebook_id) values ('Warcraft', 3);

select * from noteboook n inner join softs s on n.id = s.notebook_id;

select n.name as Ноутбук, s.name as ПО
from noteboook n inner join softs s on n.id = s.notebook_id;

select n.name as Ноутбук, s.name as ПО
from noteboook n inner join softs s on n.id = s.notebook_id
where n.name='HP';
