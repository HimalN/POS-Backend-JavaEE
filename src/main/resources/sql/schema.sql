create database POSBackend;

use POSBackend;

create table customers
(
    id          Varchar(11) primary key ,
    name        varchar(30),
    address     varchar(30),
    phone       varchar(30)
);
create table item
(
    id          Varchar(11) primary key ,
    itemName        varchar(30),
    category    varchar(30),
    weight      varchar(30),
    price       varchar(30),
    Qty         varchar(30)
);