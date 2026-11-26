// Bổ sung chức năng quản lí tài khoản
package com.mycompany.managementstudent.dao;

import com.mycompany.managementstudent.database.ConnectionDB;
import java.sql.*;
import java.util.*;

public class QuanLyTaiKhoanDAO {
    public record TaiKhoan(String ma, String ten, String vaiTro, java.time.LocalDate ngayNhap) {}
    private Connection connect() throws SQLException {
        Connection c = ConnectionDB.getConnection();
        if (c == null) throw new SQLException("Không kết nối được cơ sở dữ liệu.");
        return c;
    }
    public List<TaiKhoan> danhSach() throws SQLException {
        List<TaiKhoan> ds = new ArrayList<>();
        try (Connection c = connect(); PreparedStatement p = c.prepareStatement(
                "SELECT MaTaiKhoan, TenDangNhap, VaiTro, NgayTao FROM TaiKhoan ORDER BY LEN(MaTaiKhoan), MaTaiKhoan"); ResultSet r = p.executeQuery()) {
            while (r.next()) ds.add(new TaiKhoan(r.getString(1), r.getString(2), r.getString(3), r.getObject(4, java.time.LocalDate.class)));
        }
        return ds;
    }
    // Bổ sung chức năng quản lí tài khoản: tìm mã nhỏ nhất còn trống.
    private String timMa(Connection c, boolean khoa) throws SQLException {
        Set<String> used = new HashSet<>();
        String sql = "SELECT MaTaiKhoan FROM dbo.TaiKhoan" + (khoa ? " WITH (TABLOCKX, HOLDLOCK)" : "");
        try (PreparedStatement p = c.prepareStatement(sql); ResultSet r = p.executeQuery()) {
            while (r.next()) used.add(r.getString(1).toUpperCase(Locale.ROOT));
        }
        for (long i = 1; i < Long.MAX_VALUE; i++) {
            String ma = String.format(Locale.ROOT, "TK%03d", i);
            if (!used.contains(ma)) return ma;
        }
        throw new SQLException("Không còn mã tài khoản khả dụng.");
    }
    public String maDuKien() throws SQLException {
        try (Connection c = connect()) { return timMa(c, false); }
    }
    // Bổ sung chức năng quản lí tài khoản: khóa bảng khi cấp mã và lưu.
    // Bổ sung chức năng quản lí tài khoản: kiểm tra độ mạnh mật khẩu.
    public static final String YEU_CAU_MAT_KHAU = "Mật khẩu phải có ít nhất 8 ký tự, gồm chữ hoa, chữ số và ký tự đặc biệt.";
    public static boolean matKhauHopLe(String mk) {
        return mk != null && mk.length() >= 8 && mk.length() <= 255
                && mk.codePoints().anyMatch(Character::isUpperCase)
                && mk.codePoints().anyMatch(Character::isDigit)
                && mk.codePoints().anyMatch(c -> !Character.isLetterOrDigit(c)
                        && !Character.isWhitespace(c) && !Character.isSpaceChar(c)
                        && !Character.isISOControl(c));
    }
    public String them(String ten, String mk, String vaiTro) throws SQLException {
        // Bổ sung chức năng quản lí tài khoản
        if (!matKhauHopLe(mk)) throw new SQLException(YEU_CAU_MAT_KHAU);
        try (Connection c = connect()) {
            c.setAutoCommit(false);
            try {
                String ma = timMa(c, true);
                try (PreparedStatement p = c.prepareStatement(
                    "INSERT INTO dbo.TaiKhoan (MaTaiKhoan, TenDangNhap, MatKhau, VaiTro, NgayTao) VALUES (?, ?, ?, ?, CONVERT(date, GETDATE()))")) {
                    p.setNString(1, ma); p.setNString(2, ten); p.setNString(3, mk); p.setNString(4, vaiTro); p.executeUpdate();
                }
                c.commit(); return ma;
            } catch (SQLException | RuntimeException e) { c.rollback(); throw e; }
        }
    }
    public void sua(String ma, String ten, String mk, String vaiTro) throws SQLException {
        // Bổ sung chức năng quản lí tài khoản
        boolean doi = !mk.isEmpty();
        if (doi && !matKhauHopLe(mk)) throw new SQLException(YEU_CAU_MAT_KHAU);
        String sql = doi ? "UPDATE TaiKhoan SET TenDangNhap=?, VaiTro=?, MatKhau=? WHERE MaTaiKhoan=?"
                         : "UPDATE TaiKhoan SET TenDangNhap=?, VaiTro=? WHERE MaTaiKhoan=?";
        try (Connection c = connect(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setNString(1, ten); p.setNString(2, vaiTro);
            if (doi) p.setNString(3, mk);
            p.setNString(doi ? 4 : 3, ma);
            if (p.executeUpdate() != 1) throw new SQLException("Tài khoản không còn tồn tại.");
        }
    }
    public void xoa(String ma) throws SQLException {
        try (Connection c = connect(); PreparedStatement p = c.prepareStatement("DELETE FROM TaiKhoan WHERE MaTaiKhoan=?")) {
            p.setNString(1, ma);
            if (p.executeUpdate() != 1) throw new SQLException("Tài khoản không còn tồn tại.");
        }
    }
}
