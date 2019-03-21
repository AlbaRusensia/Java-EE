create database MyStoreSecond;
use MyStoreSecond;

create table  if not exists USER_ACCOUNT 
(
ID INT auto_increment primary key,
USER_NAME VARCHAR(255) not null,
GENDER    VARCHAR(1) not null,
PASSWORD  VARCHAR(255) not null
);
 

create table   if not exists PRODUCT
(
ID INT auto_increment primary key,
CODE  VARCHAR(20) not null,
NAME  VARCHAR(255) not null,
PRICE FLOAT not null
) ;
 
 
insert into USER_ACCOUNT (USER_NAME, GENDER, PASSWORD)
values ('Anton', 'M', '1111');
 
insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('Vova', 'M', '2222');
 
insert into PRODUCT (CODE, NAME, PRICE)
values ('P001', 'Java Core', 100);
 
insert into product (CODE, NAME, PRICE)
values ('P002', 'C# Core', 90);

ALTER  TABLE PRODUCT ADD UNIQUE (CODE);