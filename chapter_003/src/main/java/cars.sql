create table body(id serial primary key, name varchar(255) not null);
insert into body(name) values ('hatch');
insert into body(name) values ('pickup');
insert into body(name) values ('sedan');
insert into body(name) values ('multivan');

create table engine(id serial primary key, name varchar(255) not null);
insert into engine(name) values ('4A-FE');
insert into engine(name) values ('MR497QN');
insert into engine(name) values ('21083');
insert into engine(name) values ('1JZ');

create table gear(id serial primary key, name varchar(255) not null);
insert into gear(name) values ('DSG');
insert into gear(name) values ('Jatco');
insert into gear(name) values ('Tiptronik');
insert into gear(name) values ('Manual');
insert into gear(name) values ('Robot');

create table car(id serial primary key, name varchar(255) not null,
body_id int references body(id) not null,
engine_id int references engine(id) not null,
gear_id int references gear(id) not null
);

insert into car(name, body_id, engine_id, gear_id) values ('Vaz 2108', 1, 3, 4);
insert into car(name, body_id, engine_id, gear_id) values ('Vaz 2110', 3, 3, 4);
insert into car(name, body_id, engine_id, gear_id) values ('Mark 2', 3, 4, 5);
insert into car(name, body_id, engine_id, gear_id) values ('Audi Q3', 1, 1, 1);

--Select all cars  with material
select c.name, b.name, e.name, g.name
from car c
inner join body b on b.id=c.body_id
inner join engine e on e.id=c.engine_id
inner join gear g on g.id=c.gear_id;

--select unused parts
--use exists
select * from body b
where not exists( select 1 from car where body_id=b.id);
--use left join
select e.* from engine e
left join car c on c.engine_id=e.id
where c.engine_id is null;
--use not in select
select g.* from gear g
where g.id not in (select gear_id from car);