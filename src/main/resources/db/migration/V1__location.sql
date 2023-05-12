create extension if not exists "uuid-ossp";

create schema if not exists wbot;

create table if not exists wbot.w_t_location
(
    l_id    uuid primary key default public.uuid_generate_v4(),
    l_name  varchar(50) not null,
    l_local_name varchar(50),
    l_lon   numeric not null,
    l_lat numeric not null ,
    l_country varchar(50),
    l_chat_id  bigint not null
);