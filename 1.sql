create database Quanlycosovatchat;

USE Quanlycosovatchat;
GO

create table cosovatchat(
	ma int identity(1,1)  primary key,
	ten nvarchar(200) not null,
	loai nvarchar(200) not null,
	phongkhuvuc nvarchar(200),
	soluong int not null,
	tinhtrang nvarchar(100),
	ngaynhap date,
	ghichu nvarchar(500)
)
insert into cosovatchat(ten,loai,phongkhuvuc,soluong,tinhtrang,ngaynhap,ghichu)values
(N'Tivi', N'Máy tính', N'Phòng học B303', 23, N'Tốt', '2026-08-22', N'Máy dùng được'),

(N'Bàn học sinh', N'Bàn ghế', N'Phòng học A203', 35, N'Cần sửa chữa', '2022-07-20', N'Một số bàn bị lỏng ốc'),

(N'Máy chiếu', N'Thiết bị dạy học', N'Phòng học A203', 1, N'Tốt', '2023-09-01', N'Lắp trên trần'),

(N'Máy tính để bàn', N'Máy tính', N'Phòng tin học 1', 30, N'Tốt', '2024-08-15', N'Máy phục vụ giờ thực hành');
GO
select *
from cosovatchat
drop table cosovatchat
create table taikhoan(
	tendangnhap nvarchar(100) primary key,
	matkhau nvarchar(100)
);
insert into taikhoan(tendangnhap,matkhau) values
('thaithanh','123456789');
select *
from taikhoan
drop table taikhoan
CREATE TABLE dbo.taikhoan (
    tendangnhap NVARCHAR(100) PRIMARY KEY,
    matkhau NVARCHAR(100)
);
delete taikhoan
where tendangnhap='1';
delete from dbo.CoSoVatChat;
DBCC CHECKIDENT ('dbo.CoSoVatChat', RESEED, 0);
GO
create table loaicosovatchat(
	maloai int identity(1,1) primary key,
	tenloai nvarchar(100) unique
 );
 insert into loaicosovatchat (tenloai) values
    (N'Thiết bị điện tử'),
    (N'Bàn ghế'),
    (N'Thiết bị phòng học'),
    (N'Thiết bị thể thao');
	drop table loaicosovatchat
	select *
	from loaicosovatchat
	DBCC CHECKIDENT ('dbo.LoaiCoSoVatChat', RESEED, 0);
	USE Quanlycosovatchat;
	