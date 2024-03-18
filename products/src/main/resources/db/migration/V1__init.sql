create table products (
    id bigserial primary key,
    account_number bigserial,
    balance bigserial,
    type varchar (255)
);

insert into products (account_number, balance, type) values
(1, 100, 'ACCOUNT'),
(2, 250, 'CARD'),
(3, 1080, 'ACCOUNT'),
(4, 0, 'CARD'),
(5, 560, 'ACCOUNT');