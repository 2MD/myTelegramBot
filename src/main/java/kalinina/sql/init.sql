create schema if not exists bot;

drop table if exists bot.chat_inf;

create table bot.chat_inf(
    chat_id       bigint primary key,
    user_name     varchar(200),
    first_name	  varchar(200) not null,
    last_name     varchar(200)
);

CREATE UNIQUE INDEX table_code ON bot.chatInf (chat_id);

drop table if exists bot.suggest_advice;

create table bot.suggest_advice(
    id            serial primary key
    advice        varchar(2000)
);
