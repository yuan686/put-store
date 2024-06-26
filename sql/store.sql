-- 创建数据库
drop database put_store;
create database put_store;
use put_store;

-- 创建调查船表
create table ship_type(
	ship_id int primary key auto_increment,
	ship_name varchar(64) not null
)ENGINE=INNODB default charset=utf8mb4 comment='调查船表';

-- 创建航次表
create table voyage(
	voyage_id int primary key auto_increment,
	ship_id int,
	voyage_name varchar(64)not null,
	foreign key (ship_id) REFERENCES ship_type(ship_id)
)ENGINE=INNODB default charset=utf8mb4 comment='航次表';


-- 设置海域表
create table sea_area(
	sea_area_id int primary key auto_increment,
	sea_area_name varchar(64) not null
)ENGINE=INNODB default charset=utf8mb4 comment='海域表';

-- 创建详细位置表
create table location(
	location_id int primary key auto_increment,
	voyage_id int,
	sea_area_id int,
	position varchar(64),
	x_coordinate DECIMAL(10,6),
	y_coordinate DECIMAL(10,6),
	detailed_address varchar(255),
	foreign key (voyage_id) REFERENCES voyage(voyage_id),
	foreign key (sea_area_id) REFERENCES sea_area(sea_area_id)
)ENGINE=INNODB default charset=utf8mb4 comment='详细位置表';


-- 创建入库单
create table store(
	sample_id int primary key auto_increment, 
	sample_num varchar(64),
	location_id int,
	end_dept decimal(10,2),
	heart_length decimal(10,2),
	store_position varchar(64),
	save_status DECIMAL(10,2),
	remark varchar(255),
	foreign key (location_id) REFERENCES location(location_id)
)ENGINE=INNODB default charset=utf8mb4 comment='入库单';