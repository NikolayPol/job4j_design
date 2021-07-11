create table owners(
    id serial primary key,
    name varchar(255)
);

create table devices(
    id serial primary key,
    name varchar(255),
    owner_id int references owners(id)
);

insert into owners(name) values ('Owner 1');
insert into owners(name) values ('Owner 2');
insert into owners(name) values ('Owner 3');

insert into devices(name, owner_id) values ('Device 1', 1);
insert into devices(name, owner_id) values ('Device 2', 2);
insert into devices(name, owner_id) values ('Device 3', 3);
insert into devices(name, owner_id) values ('Device 4', null);
insert into devices(name, owner_id) values ('Device 5', null);
insert into devices(name, owner_id) values ('Device 6', 1);

--inner join
select * from devices d inner join owners o on d.owner_id=o.id;

--left outer join
select * from devices d left join owners o on d.owner_id=o.id;

select * from devices d left join owners o on d.owner_id=o.id
where o.id is null; --устройства, у которых нет владельца

select * from owners o left join devices d on o.id = d.owner_id;
--По идеи мы должны были получить 3 записи, т.к. в таблице owner у нас 3 записи,
--но т.к. Для каждой записи табл1 подбираются записи из табл2, а для
-- владельца с id = 1, подобралось 2 записи из таблицы табл2, то в результате мы
-- получили на одну запись больше. Т.к. связь между таблицами many-to-one,
-- то предпочтительней первый вариант запроса, т.е. выполнять присоединение таблицы,
-- которая one, к таблице, которая many.

--right outer join

--Таким образом, при правом соединении выбираются записи из правой таблицы и для
--них подбираются записи из левой таблицы, которые удовлетворяют условию.
--Разумеется, при левом соединении наоборот.
--Следовательно, следующие пары запросов будут работать одинаково,
--отличаться будет возможно только порядок столбцов в результирующей выборке.
select * from devices d left join owners o on d.owner_id = o.id;
select * from owners o right join devices d on d.owner_id = o.id;

select * from owners o left join devices d on o.id = d.owner_id;
select * from devices d right join owners o on d.owner_id = o.id;

--full join
--Данный тип внешнего соединения дает результат левого + правого соединений,
--т.е. представляет собой комбинацию этих двух соединений. Работает он так:
--выполняется левое соединение, выполняется правое соединение и оба результата
--этих запросов попадают в результирующую выборку.
select * from devices d full join owners o on d.owner_id = o.id;

select * from devices d left join owners o on d.owner_id = o.id
union
select * from devices d right join owners o on d.owner_id = o.id
;

--cross join
--Этот вид join используется редко. Результатом этого запроса является
--декартово множество, т.е. все пары элементов.
--Выполнение cross join позволяет получить все возможные пары.
--Например, если в таблице 1 N записей,
--а в таблице 2 M записей, то мы получим в результате N * M записей.
--Хорошим примером декартова множества является таблица умножения.
--Заметьте! on в этом случае не пишется
select * from devices d cross join owners o;

create table numbers(
    num int unique
);

insert into numbers(num) values (1);
insert into numbers(num) values (2);
insert into numbers(num) values (3);

select n1.num as a, n2.num as b, (n1.num * n2.num) as "a*b=" from numbers n1 cross join numbers n2;

