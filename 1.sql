-- Bổ sung chức năng quản lí tài khoản: chạy lại an toàn trên database hiện có.
IF DB_ID(N'Quanlycosovatchat') IS NULL
    create database Quanlycosovatchat;
GO
-- delete database Quanlycosovatchat; -- Giữ lệnh cũ dưới dạng chú thích vì sai cú pháp.
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
	select *
	from loaicosovatchat
	-- delete loaicosovatchat
	-- delete cosovatchat
	

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
select *
from cosovatchat
GO
IF OBJECT_ID(N'dbo.taikhoan', N'U') IS NULL
BEGIN
create table taikhoan(
MaTaiKhoan INT IDENTITY(1,1) PRIMARY KEY,
    TenDangNhap NVARCHAR(50) NOT NULL UNIQUE,
    MatKhau NVARCHAR(255) NOT NULL,
    VaiTro NVARCHAR(20) NOT NULL
        CHECK (VaiTro IN ('Admin', 'User'))
		);
INSERT INTO TaiKhoan (TenDangNhap, MatKhau, VaiTro)
VALUES
    (N'admin', N'Admin12345@', N'Admin'),
    (N'user', N'User12345@', N'User');
END;
GO

select *
from taikhoan
--  taikhoan
GO


-- Bổ sung chức năng quản lí tài khoản
-- Chạy bằng Windows Authentication có quyền ALTER trên database; sao lưu trước.
USE [Quanlycosovatchat];
GO
SET XACT_ABORT ON;
BEGIN TRY
    BEGIN TRANSACTION;
    IF OBJECT_ID(N'dbo.TaiKhoan', N'U') IS NULL
        THROW 50001, N'Chưa có bảng TaiKhoan. Hãy tạo bảng từ script dự án trước.', 1;
    IF EXISTS (SELECT 1 FROM sys.columns WHERE object_id=OBJECT_ID(N'dbo.TaiKhoan') AND name=N'MaTaiKhoan' AND is_identity=1)
    BEGIN
        IF EXISTS (SELECT 1 FROM sys.foreign_keys WHERE referenced_object_id=OBJECT_ID(N'dbo.TaiKhoan') OR parent_object_id=OBJECT_ID(N'dbo.TaiKhoan'))
            THROW 50002, N'Bảng có khóa ngoại; cần kiểm tra trước khi chuyển mã.', 1;
        DECLARE @pk sysname = (SELECT name FROM sys.key_constraints WHERE parent_object_id=OBJECT_ID(N'dbo.TaiKhoan') AND type='PK');
        ALTER TABLE dbo.TaiKhoan ADD MaTaiKhoanMoi NVARCHAR(30) NULL;
        EXEC(N'UPDATE dbo.TaiKhoan SET MaTaiKhoanMoi = N''TK'' + CASE WHEN MaTaiKhoan < 1000 THEN RIGHT(N''000'' + CONVERT(nvarchar(20),MaTaiKhoan),3) ELSE CONVERT(nvarchar(20),MaTaiKhoan) END');
        IF @pk IS NOT NULL
        BEGIN
            DECLARE @dropPk nvarchar(max) = N'ALTER TABLE dbo.TaiKhoan DROP CONSTRAINT ' + QUOTENAME(@pk);
            EXEC sys.sp_executesql @dropPk;
        END;
        EXEC(N'ALTER TABLE dbo.TaiKhoan DROP COLUMN MaTaiKhoan;');
        EXEC sys.sp_rename N'dbo.TaiKhoan.MaTaiKhoanMoi', N'MaTaiKhoan', N'COLUMN';
        -- Tách thành hai batch để SQL Server cập nhật metadata trước khi tạo khóa chính.
        EXEC(N'ALTER TABLE dbo.TaiKhoan ALTER COLUMN MaTaiKhoan NVARCHAR(30) NOT NULL;');
    END;
    IF NOT EXISTS (SELECT 1 FROM sys.columns WHERE object_id=OBJECT_ID(N'dbo.TaiKhoan') AND name=N'MaTaiKhoan' AND system_type_id=231 AND is_identity=0)
        THROW 50003, N'Kiểu mã hiện tại không tương thích; cần kiểm tra cấu trúc bảng.', 1;
    -- Bổ sung chức năng quản lí tài khoản: hoàn tất cả trường hợp mã đã chuyển sang chuỗi.
    IF EXISTS (SELECT 1 FROM sys.columns WHERE object_id=OBJECT_ID(N'dbo.TaiKhoan') AND name=N'MaTaiKhoan' AND is_nullable=1)
        EXEC(N'ALTER TABLE dbo.TaiKhoan ALTER COLUMN MaTaiKhoan NVARCHAR(30) NOT NULL;');
    IF NOT EXISTS (SELECT 1 FROM sys.key_constraints WHERE parent_object_id=OBJECT_ID(N'dbo.TaiKhoan') AND type='PK')
        EXEC(N'ALTER TABLE dbo.TaiKhoan ADD CONSTRAINT PK_TaiKhoan_Ma PRIMARY KEY (MaTaiKhoan);');
    IF COL_LENGTH(N'dbo.TaiKhoan', N'NgayTao') IS NULL
        EXEC(N'ALTER TABLE dbo.TaiKhoan ADD NgayTao DATE NOT NULL CONSTRAINT DF_TaiKhoan_NgayTao DEFAULT (CONVERT(date,GETDATE())) WITH VALUES;');
    COMMIT TRANSACTION;
END TRY
BEGIN CATCH
    IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
    THROW;
END CATCH;
GO
IF COL_LENGTH(N'dbo.TaiKhoan', N'NgayTao') IS NOT NULL
    EXEC(N'SELECT MaTaiKhoan, TenDangNhap, VaiTro, NgayTao FROM dbo.TaiKhoan ORDER BY LEN(MaTaiKhoan), MaTaiKhoan;');
GO
