Use master
go

CREATE DATABASE COFFEESHOP
GO

USE COFFEESHOP
GO


--ACCOUNT
create table Admin (
	ad_id varchar(10) not null			constraint pk_adminid primary key ,
	username varchar(50) not null,
	pass varchar(50) not null,
	name varchar(50) not null
)
go

create table Account (
	em_id varchar(10) not null			constraint pk_account primary key,
	username varchar(50) not null,
	pass varchar(50) not null,
	name nvarchar(50) not null				
)
go
-- END ACCOUNT

--MENU
create table Product(
	product_id varchar(10)				constraint pk_menuproductid primary key,
	name nvarchar(50) not null,
	price money not null,
	category_id varchar(10) not null
)
go
	
--END MENU

--ORDER
create table Order (
	order_id varchar(10) not null	constraint pk_orderid primary key,
	ordertable int not null,               -- số bàn
	ordertime date not null,               -- thời gian order
	price money not null
)
go

create table OrderDetails(
	order_id varchar(10) not null		constraint fk_order foreign key references Order(order_id),
	food_id varchar(10) not null		constraint fk_foodinmenu foreign key references Product(Product_id),
										constraint pk_orderdetails primary key (order_id, food_id),
)
go
--END ORDER

insert into Admin values
('AD00000001', 'admin', 'admin', N'Trung')
go


insert into Account values
('AC00000001', 'username1', 	'password1',	N'Phạm Thanh A' ),
('AC00000002', 'username2', 	'password2',	N'Nguyễn Khánh D'),
('AC00000003', 'username3', 	'password3',	N'Lý Đông N'),
('AC00000004', 'username4', 	'password4',	N'Bảo N'),
('AC00000005', 'username5', 	'password5',	N'Lương Nhật D'),
('AC00000006', 'username6', 	'password6',	N'Đinh Thanh H')
go