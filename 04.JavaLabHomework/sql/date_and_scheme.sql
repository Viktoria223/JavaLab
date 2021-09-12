create table password_blacklist
(
    id serial primary key,
    password varchar(30) not null
);

insert into password_blacklist(password)
values ('password');
insert into password_blacklist(password)
values ('qwerty007');
insert into password_blacklist(password)
values ('123456');
insert into password_blacklist(password)
values ('111111');
insert into password_blacklist(password)
values ('qwerty111');
insert into password_blacklist(password)
values ('00000000');
insert into password_blacklist(password)
values ('9876543');
insert into password_blacklist(password)
values ('qwerty');