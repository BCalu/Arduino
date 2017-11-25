create database if not exists Arduino;
use Arduino;

create table if not exists test(
	Fecha datetime(3),
    AccX float,
    AccY float,
    AccZ float,
    Accx2 float,
    Accy2 float,
    Accz2 float,
    Accx3 float,
    Accy3 float,
    Accz3 float,
    Accx4 float,
    Accy4 float,
    Accz4 float
);

#drop schema Arduino;
#describe test;
SELECT * FROM test;