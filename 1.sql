IF DB_ID(N'Quanlycosovatchat') IS NULL
    create database Quanlycosovatchat;
GO
USE Quanlycosovatchat;
GO

IF OBJECT_ID(N'dbo.loaicosovatchat', N'U') IS NULL
BEGIN
create table loaicosovatchat(
	maloai varchar(20) primary key,
	tenloai nvarchar(100) not null unique
 );
 insert into loaicosovatchat (maloai,tenloai) values
    ('LOAI001',N'Thiết bị điện tử'),
    ('LOAI002',N'Bàn ghế'),
    ('LOAI003',N'Thiết bị phòng học'),
    ('LOAI004',N'Thiết bị thể thao');
END;
GO
	select * from loaicosovatchat;
GO


IF OBJECT_ID(N'dbo.cosovatchat', N'U') IS NULL
BEGIN
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
('20261025001',N'Tivi', 'LOAI001', N'Phòng học B303', 23, N'Tốt', '2026-08-22', N'Máy dùng được'),

('20261025002',N'Bàn học sinh', 'LOAI002', N'Phòng học A203', 35, N'Cần sửa chữa', '2022-07-20', N'Một số bàn bị lỏng ốc'),

('20261025003',N'Máy chiếu', 'LOAI003', N'Phòng học A203', 1, N'Tốt', '2023-09-01', N'Lắp trên trần'),

('20261025004',N'Máy tính để bàn', 'LOAI001', N'Phòng tin học 1', 30, N'Tốt', '2024-08-15', N'Máy phục vụ giờ thực hành');
END;
GO
select * from cosovatchat;
GO
IF OBJECT_ID(N'dbo.taikhoan', N'U') IS NULL
BEGIN
    create table taikhoan(
        MaTaiKhoan nvarchar(30) primary key,
        TenDangNhap nvarchar(50) not null unique,
        MatKhau nvarchar(255) not null,
        VaiTro nvarchar(20) not null check(VaiTro in ('Admin', 'User')),
        NgayTao date not null default getdate()
    );

    insert into taikhoan(MaTaiKhoan, TenDangNhap, MatKhau, VaiTro) values
    (N'TK001', N'Admin', N'Admin12345@', N'Admin'),
    (N'TK002', N'User', N'User12345@', N'User');
END;
GO

update taikhoan set TenDangNhap = N'Admin', MatKhau = N'Admin12345@'
where TenDangNhap in (N'admin', N'Admin') and VaiTro = N'Admin' and MatKhau = N'12345';

update taikhoan set TenDangNhap = N'User', MatKhau = N'User12345@'
where TenDangNhap in (N'user', N'User') and VaiTro = N'User' and MatKhau = N'12345';
GO

select MaTaiKhoan, TenDangNhap, VaiTro, NgayTao from taikhoan;
GO

IF SUSER_ID(N'thanh') IS NOT NULL
BEGIN
    IF USER_ID(N'thanh') IS NULL
        CREATE USER [thanh] FOR LOGIN [thanh];
    IF IS_ROLEMEMBER(N'db_datareader', N'thanh') = 0
        ALTER ROLE db_datareader ADD MEMBER [thanh];
    IF IS_ROLEMEMBER(N'db_datawriter', N'thanh') = 0
        ALTER ROLE db_datawriter ADD MEMBER [thanh];
END;
GO
