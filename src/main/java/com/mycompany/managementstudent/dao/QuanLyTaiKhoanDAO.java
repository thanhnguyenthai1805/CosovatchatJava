package com.mycompany.managementstudent.dao;

import com.mycompany.managementstudent.database.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiKhoanDAO {
    public static final String YEU_CAU_MAT_KHAU =
            "Mật khẩu phải có ít nhất 8 ký tự, gồm chữ hoa, chữ số và ký tự đặc biệt.";

    private Connection connect() throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        if (conn == null) {
            throw new SQLException("Không kết nối được cơ sở dữ liệu.");
        }
        return conn;
    }

    public List<Object[]> danhSach() throws SQLException {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT MaTaiKhoan, TenDangNhap, VaiTro, NgayTao FROM TaiKhoan ORDER BY LEN(MaTaiKhoan), MaTaiKhoan";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Object[]{rs.getString("MaTaiKhoan"),
                    rs.getString("TenDangNhap"), rs.getString("VaiTro"),
                    rs.getDate("NgayTao").toLocalDate()});
            }
        }
        return list;
    }

    public String layMatKhau(String ma) throws SQLException {
        String sql = "SELECT MatKhau FROM TaiKhoan WHERE MaTaiKhoan=?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ma);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("MatKhau");
                }
            }
        }
        throw new SQLException("Tài khoản không còn tồn tại.");
    }

    private String timMa(Connection conn, String sql) throws SQLException {
        List<String> dsMa = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dsMa.add(rs.getString("MaTaiKhoan"));
            }
        }
        int i = 1;
        String ma = String.format(java.util.Locale.ROOT, "TK%03d", i);
        while (dsMa.contains(ma)) {
            i++;
            ma = String.format(java.util.Locale.ROOT, "TK%03d", i);
        }
        return ma;
    }

    public String maDuKien() throws SQLException {
        try (Connection conn = connect()) {
            return timMa(conn, "SELECT MaTaiKhoan FROM TaiKhoan");
        }
    }

    public static boolean matKhauHopLe(String mk) {
        if (mk == null || mk.length() < 8 || mk.length() > 255) {
            return false;
        }
        boolean coChuHoa = false;
        boolean coChuSo = false;
        boolean coKyTuDacBiet = false;
        for (int i = 0; i < mk.length(); i++) {
            char c = mk.charAt(i);
            if (Character.isUpperCase(c)) {
                coChuHoa = true;
            } else if (Character.isDigit(c)) {
                coChuSo = true;
            } else if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)
                    && !Character.isSpaceChar(c) && !Character.isISOControl(c)) {
                coKyTuDacBiet = true;
            }
        }
        return coChuHoa && coChuSo && coKyTuDacBiet;
    }

    public void them(String ten, String mk, String vaiTro) throws SQLException {
        if (!matKhauHopLe(mk)) {
            throw new SQLException(YEU_CAU_MAT_KHAU);
        }
        String sql = "INSERT INTO TaiKhoan(MaTaiKhoan, TenDangNhap, MatKhau, VaiTro) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect()) {
            conn.setAutoCommit(false);
            try {
                String ma = timMa(conn, "SELECT MaTaiKhoan FROM TaiKhoan WITH (TABLOCKX, HOLDLOCK)");
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setNString(1, ma);
                    ps.setNString(2, ten);
                    ps.setNString(3, mk);
                    ps.setNString(4, vaiTro);
                    ps.executeUpdate();
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    public void sua(String ma, String ten, String mk, String vaiTro) throws SQLException {
        if (!mk.isEmpty() && !matKhauHopLe(mk)) {
            throw new SQLException(YEU_CAU_MAT_KHAU);
        }
        String sql = "UPDATE TaiKhoan SET TenDangNhap=?, VaiTro=? WHERE MaTaiKhoan=?";
        if (!mk.isEmpty()) {
            sql = "UPDATE TaiKhoan SET TenDangNhap=?, VaiTro=?, MatKhau=? WHERE MaTaiKhoan=?";
        }
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ten);
            ps.setNString(2, vaiTro);
            if (mk.isEmpty()) {
                ps.setNString(3, ma);
            } else {
                ps.setNString(3, mk);
                ps.setNString(4, ma);
            }
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Tài khoản không còn tồn tại.");
            }
        }
    }

    public void xoa(String ma) throws SQLException {
        String sql = "DELETE FROM TaiKhoan WHERE MaTaiKhoan=?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ma);
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Tài khoản không còn tồn tại.");
            }
        }
    }
}
