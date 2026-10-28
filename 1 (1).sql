create database Quanlycosovatchat;
GO

USE Quanlycosovatchat;
GO

create table loaicosovatchat(
	maloai varchar(20) primary key,
	tenloai nvarchar(100) not null unique
 );
 insert into loaicosovatchat (maloai,tenloai) values
    ('L001',N'Thiết bị điện tử'),
    ('L002',N'Bàn ghế'),
    ('L003',N'Thiết bị phòng học'),
    ('L004',N'Thiết bị thể thao');
	select *
	from loaicosovatchat

	

create table cosovatchat(
	ma varchar(20)  primary key,
	ten nvarchar(200) not null,
	maloai varchar(20) not null,
	phongkhuvuc nvarchar(200),
	soluong int not null CHECK(soluong >= 0),
	tinhtrang nvarchar(100),
	ngaynhap date default getdate(),
	ghichu nvarchar(500),

	constraint fk_cosovatchat_loai
	     foreign key (maloai)
		 references loaicosovatchat(maloai)

)
insert into cosovatchat(ma,ten,maloai,phongkhuvuc,soluong,tinhtrang,ngaynhap,ghichu)values
('20261025001',N'Tivi', 'L001', N'Phòng học B303', 23, N'Tốt', '2026-08-22', N'Máy dùng được'),

('20261025002',N'Bàn học sinh', 'L002', N'Phòng học A203', 35, N'Cần sửa chữa', '2022-07-20', N'Một số bàn bị lỏng ốc'),

('20261025003',N'Máy chiếu', 'L003', N'Phòng học A203', 1, N'Tốt', '2023-09-01', N'Lắp trên trần'),

('20261025004',N'Máy tính để bàn', 'L001', N'Phòng tin học 1', 30, N'Tốt', '2024-08-15', N'Máy phục vụ giờ thực hành');
GO
select *
from cosovatchat

create table taikhoan(
	tendangnhap nvarchar(100) primary key,
	matkhau nvarchar(100) not null
);
insert into taikhoan(tendangnhap,matkhau) values
('thaithanh','123456789');
select *
from taikhoan

GO
