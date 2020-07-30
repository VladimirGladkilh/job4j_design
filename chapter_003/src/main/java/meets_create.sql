create table Meet(
id serial primary key,
name varchar(255));

create table Users(
id serial primary key,
name varchar(255));

create table Meeting(
	id serial primary key,
	meet_id int references Meet(id) not null,
	user_id int references Users(id) not null,
	state boolean default true
);