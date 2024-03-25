CREATE TABLE tb_user
    (
        id bigserial
    primary key,
    name varchar(255),
        surname varchar(255),
        username varchar(255),
        password varchar(255),
        email varchar(255),
        birthday_date date,
        joined_date date,
        last_visit_date date
);

ALTER TABLE tb_user
owner to postgres;