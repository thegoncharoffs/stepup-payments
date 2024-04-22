create table limits (
    user_id bigserial primary key,
    payment_limit integer
);

insert into limits (payment_limit) values
    (10000),
    (200),
    (10),
    (10000),
    (10000);