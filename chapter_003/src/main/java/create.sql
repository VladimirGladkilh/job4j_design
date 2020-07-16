create database job4j;
\c job4j;
create table roles(id serial primary key, name varchar(255) not null);
insert into roles(name) values ('admin');
insert into roles(name) values ('manager');
insert into roles(name) values ('default');

create table users(id serial primary key, login varchar(255) not null, role_id int references roles(id));
insert into users(login, role_id) values ('user1', 1);
insert into users(login, role_id) values ('user2', 2);
insert into users(login, role_id) values ('user3', 3);


create table rules(id serial primary key, name varchar(255) not null);
insert into rules(name) values ('read');
insert into rules(name) values ('write');
insert into rules(name) values ('deny');
insert into rules(name) values ('full');

create table rules_list(id serial primary key, role_id int references roles(id), rule_id int references rules(id));
insert into rules_list(role_id, rule_id) values ((select id from roles where name='admin'), (select id from rules where name='full'));
insert into rules_list(role_id, rule_id) values ((select id from roles where name='default'), (select id from rules where name='deny'));
insert into rules_list(role_id, rule_id) values ((select id from roles where name='manager'), (select id from rules where name='read'));
insert into rules_list(role_id, rule_id) values ((select id from roles where name='manager'), (select id from rules where name='write'));

create table states(id serial primary key, name varchar(255) not null);
insert into states(name) values ('new');
insert into states(name) values ('inwork');
insert into states(name) values ('closed');

create table category (id serial primary key, name varchar(255) not null);
insert into category(name) values ('error');
insert into category(name) values ('request');
insert into category(name) values ('modification');


create table items (id serial primary key, soder varchar(1024) not null, user_id int references users(id), state_id int references states(id), category_id int references category(id));
insert into items(soder, user_id, state_id, category_id) values ('first error', (select id from users where login='user1'), (select id from states where name='new'), (select id from category where name='error'));
insert into items(soder, user_id, state_id, category_id) values ('second error', (select id from users where login='user3'), (select id from states where name='inwork'), (select id from category where name='request'));
insert into items(soder, user_id, state_id, category_id) values ('third error', (select id from users where login='user2'), (select id from states where name='closed'), (select id from category where name='error'));


create table attaches (id serial primary key, name varchar(255) not null, ext varchar(5) not null, item_id int references items(id));
insert into attaches(name, ext, item_id) values('some file', 'txt', (select id from items where soder='first error'));
insert into attaches(name, ext, item_id) values('second file', 'docx', (select id from items where soder='first error'));

create table comments (id serial primary key, description varchar(1024) not null, item_id int references items(id));