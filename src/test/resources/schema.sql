drop view if exists message_view;
create table if not exists message
(
    message_date_time bigint not null,
    message varchar not null,
    message_date bigint not null,
    primary key (message_date_time)
);