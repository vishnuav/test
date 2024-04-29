drop table if exists streamed_message;

create table if not exists streamed_message
(
    message_date_time bigint not null,
    message varchar not null,
    primary key (message_date_time)
);
create index if not exists streamed_message_date_time_idx on streamed_message (message_date_time);