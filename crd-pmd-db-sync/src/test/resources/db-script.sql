drop table if exists streamed_message;
drop table if exists exception_message;

create table if not exists streamed_message
(
    message_date_time bigint not null,
    message varchar not null,
    primary key (message_date_time)
);
create index if not exists streamed_message_date_time_idx on streamed_message (message_date_time);
create table if not exists exception_message
(
    id bigint not null primary key,
    source_name varchar not null,
    destination_type varchar not null,
    destination_url varchar not null,
    status varchar not null,
    payload text not null,
    exception_date_time bigint not null
);
create index if not exists streamed_message_date_time_idx on exception_message (exception_date_time);
create index if not exists streamed_message_source_name_idx on exception_message (source_name);
create index if not exists streamed_message_destination_type_idx on exception_message (destination_type);