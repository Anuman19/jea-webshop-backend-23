-- database
create database JEA_PiBS1;

-- table users
create table users
(
    city        varchar(60),
    email       varchar(50)
        constraint users_pk2
            unique,
    home_number integer,
    password    varchar(120),
    street      varchar(60),
    username    varchar(20)
        constraint users_pk3
            unique,
    zip         varchar(10),
    id          integer not null
        constraint users_pk
            primary key
);

alter table users
    owner to postgres;

-- table roles

create table roles
(
    name varchar(20),
    id   integer not null
        constraint roles_pk
            primary key
);

alter table roles
    owner to postgres;

-- table user_roles
create table user_roles
(
    user_id bigint  not null
        constraint user_id_fk
            references users,
    role_id integer not null
        constraint role_id_fk
            references roles,
    constraint user_roles_pk
        primary key (user_id, role_id)
);

alter table user_roles
    owner to postgres;

create unique index user_roles_user_id_role_id_uindex
    on user_roles (user_id, role_id);

