use java_ee_db;

-- Create table
create table USER_INFO
(
USER_NAME varchar(30) not null,
GENDER VARCHAR(1) not null,
PASSWORD  VARCHAR(30) not null,
primary key (USER_NAME)
);

-- Create table
create table ACCOUNT
(
ID SERIAL NOT NULL,
USER_NAME varchar(30) not null,
CARD_NAME varchar(30) not null,
CARD_NUMBER varchar(30) not null,
CARD_BALANCE DOUBLE not null DEFAULT 0,
primary key (ID)

);

-- Create table
create table PAYMENTS
(
PAYMENT_NUMBER INT NOT NULL,
PAYMENT_AUTHOR VARCHAR(30) NOT NULL,
PAYMENT_PURPOSE VARCHAR(100) NOT NULL,
PAYMENT_AMOUNT DOUBLE NOT NULL,
PAYMENT_DATE_AND_TIME DATETIME NOT NULL,
PAYMENT_STATUS VARCHAR(20) NOT NULL,
primary key (PAYMENT_NUMBER)
);

-- Create table
create table PRODUCT
(
CODE  VARCHAR(20) not null,
NAME  VARCHAR(128) not null,
PRICE FLOAT not null,
primary key (CODE)
);

-- Insert data: ---------------------------------------------------------------
 
insert into user_info (USER_NAME, GENDER, PASSWORD)
values ('admin', 'M', 'admin12345');

insert into ACCOUNT (USER_NAME, CARD_NAME, CARD_NUMBER, CARD_BALANCE)
values ('admin', 'M', '3535353535353535', 0 );

insert into PAYMENTS (PAYMENT_NUMBER, PAYMENT_AUTHOR, PAYMENT_PURPOSE, PAYMENT_AMOUNT,
                      PAYMENT_DATE_AND_TIME , PAYMENT_STATUS)
values (1, 'admin', 'CHECK', 0, '2021-09-04 20:20:20', 'CHECK');
 
insert into product (CODE, NAME, PRICE)
values ('P001', 'Java Core', 100);
 
insert into product (CODE, NAME, PRICE)
values ('P002', 'C# Core', 90);

