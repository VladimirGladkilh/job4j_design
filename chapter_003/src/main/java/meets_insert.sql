
insert into Meet (name) values ('Birthday');
insert into Meet (name) values ('Drinking');
insert into Meet (name) values ('Fishing');
insert into Meet (name) values ('Coworking');

insert into Users (name) values ('Piter');
insert into Users (name) values ('Mary');
insert into Users (name) values ('Joe');
insert into Users (name) values ('Andrea');

insert into Meeting (meet_id, user_id) values (1, 1);
insert into Meeting (meet_id, user_id) values (1, 2);
insert into Meeting (meet_id, user_id) values (1, 3);
insert into Meeting (meet_id, user_id) values (2, 1);
insert into Meeting (meet_id, user_id) values (2, 3);
insert into Meeting (meet_id, user_id) values (3, 1);
insert into Meeting (meet_id, user_id) values (3, 3);
insert into Meeting (meet_id, user_id, state) values (1, 4, false);
insert into Meeting (meet_id, user_id, state) values (3, 4, false);
