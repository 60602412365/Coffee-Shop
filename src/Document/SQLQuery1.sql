Use master
go

CREATE DATABASE COFFEESHOP
GO

USE COFFEESHOP
GO

DELETE Account

--ACCOUNT
create table Admin (
	ad_id varchar(10) not null			constraint pk_adminid primary key ,
	username varchar(50) not null,
	pass varchar(50) not null,
	name varchar(50) not null
)
go

create table Account (
	em_id varchar(10) not null			constraint pk_accountid primary key,
	username varchar(50) not null,
	pass varchar(50) not null,
	name nvarchar(50) not null,	
	birth date not null,				constraint chk_birthday check(year(birth) <= (year(getdate()) - 18)),
	addr nvarchar(200) null,
	email varchar(50) null,
	phone varchar(20) null,
)
go
-- END ACCOUNT

--MENU
create table Category(
	category_id int						constraint pk_categoryproductid primary key,
	name nvarchar(50) not null,
)



create table Product(
	product_id varchar(10)				constraint pk_menuproductid primary key,
	name nvarchar(50) not null,
	price money not null,
	category_id varchar(10) not null
)
go
	

--END MENU

--ORDER
create table Orders (
	order_id varchar(10) not null	constraint pk_orderid primary key,
	account_id varchar(10) not null	constraint fk_oderaccount foreign key references Account(em_id),
	ordertime date not null,               -- thời gian order
	price money not null,
	customerpay money not null,
	payback money not null

)
go

create table OrderDetails(
	order_id varchar(10) not null		constraint fk_order foreign key references Orders(order_id),
	product_id varchar(10) not null		constraint fk_foodinmenu foreign key references Product(Product_id),
										constraint pk_orderdetails primary key (order_id, product_id),
	quan int not null					constraint chk_order_quantity check(quan >= 0)
)
go
--END ORDER

insert into Admin values
('AD00000001', 'admin', 'admin', N'Trung')
go


insert into Account values
('AC00000001', 'username1', 	'password1',	N'Phạm Thanh A',	'1998-01-01',	N'KTX Khu A - DHQG-HCM',	'example_email1@gmail.com',		'0969876940'),
('AC00000002', 'username2', 	'password2',	N'Nguyễn Khánh D',	'1998-01-02',	N'KTX Khu B - DHQG-HCM',	'example_email2@gmail.com',		'0964753827'),
('AC00000003', 'username3', 	'password3',	N'Lý Đông N',		'1997-01-04',	N'KTX Khu A - DHQG-HCM',	'example_email3@gmail.com',		'0965164474'),
('AC00000004', 'username4', 	'password4',	N'Bảo N',			'2000-01-04',	N'KTX Khu B - DHQG-HCM',	'example_email4@gmail.com',		'01215925627'),
('AC00000005', 'username5', 	'password5',	N'Lương Nhật D',	'2000-05-05',	N'KTX Khu B - DHQG-HCM',	'example_email5@gmail.com',		'01207305775'),
('AC00000006', 'username6', 	'password6',	N'Đinh Thanh H',	'1997-12-12',	N'KTX Khu A - DHQG-HCM',	'example_email6@gmail.com',		'0916466886')
go


insert into Category values		--Loại đồ uống
(0,		N'Nước ngọt'),
(1,		N'Cà phê'),
(2,		N'Trà sửa'),
(3,		N'Sinh tố'),
(4,		N'Những thứ khác')
Go


insert into Product values		-- đồ uống
('F000000030',	N'pepsi',							25,		0),
('F000000031',	N'7up',								25,		0),
('F000000032',	N'water',							25,		0),
('F000000033',	N'black coffee',					30,		1),
('F000000034',	N'coffee milk',						35,		1),
('F000000035',	N'cream coffee',					40,		1),
('F000000036',	N'americano',						40,		1),
('F000000037',	N'durian coffee',					50,		1),
('F000000038',	N'coffee latte',					50,		1),
('F000000039',	N'cappucino',						50,		1),
('F000000040',	N'orange coffee',					50,		1),
('F000000041',	N'tiramisu coffee',					50,		1),
('F000000042',	N'chocolate coffee',				60,		1),
('F000000043',	N'caramel cofffee',					60,		1),
('F000000044',	N'strawberry tea',					30,		2),
('F000000045',	N'lemon tea',						30,		2),
('F000000046',	N'apple tea',						30,		2),
('F000000047',	N'milk tea',						40,		2),
('F000000048',	N'peach tea',						50,		2),
('F000000049',	N'matcha latte',					50,		2),
('F000000050',	N'ginger honey latte',				50,		2),
('F000000051',	N'hot choco',						60,		2),
('F000000052',	N'ice choco',						60,		2),
('F000000053',	N'orange juice (trái nhỏ)',			40,		3),
('F000000054',	N'orange ade',						40,		3),
('F000000055',	N'lemonade',						40,		3),
('F000000056',	N'orange juice (trái lớn)',			40,		3),
('F000000057',	N'orange juice (trái vừa)',			40,		3)
go
