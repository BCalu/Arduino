create database if not exists rxtx;
use rxtx;

create table if not exists test(
	dato integer
);

drop schema rxtx;
describe test;
SELECT * FROM test;