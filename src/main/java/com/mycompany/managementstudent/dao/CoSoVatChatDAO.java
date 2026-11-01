package com.mycompany.managementstudent.dao;

import com.mycompany.managementstudent.model.CoSoVatChat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CoSoVatChatDAO {

    public List<CoSoVatChat> findAll() {

        List<CoSoVatChat> list = new ArrayList<>();

         String sql = """
    SELECT c.*, l.TenLoai
    FROM CoSoVatChat c
    JOIN LoaiCoSoVatChat l ON c.MaLoai = l.MaLoai
    ORDER BY c.Ma
    """;

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                CoSoVatChat cs = new CoSoVatChat();

                cs.setMa(rs.getString("Ma"));
                cs.setTen(rs.getString("Ten"));
                cs.setMaloai(rs.getString("MaLoai"));
                cs.setTenLoai(rs.getString("TenLoai"));
                cs.setPhongKhuVuc(rs.getString("PhongKhuVuc"));
                cs.setSoLuong(rs.getInt("SoLuong"));
                cs.setTinhTrang(rs.getString("TinhTrang"));

                if (rs.getDate("NgayNhap") != null) {
                    cs.setNgayNhap(
                            rs.getDate("NgayNhap").toLocalDate()
                    );
                }

                cs.setGhiChu(rs.getString("GhiChu"));
               
                list.add(cs);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Loi tai du lieu : " + e.getMessage()
            );
        }

        return list;
    }

    public List<String> findAllLoai() {

        List<String> dsLoai = new ArrayList<>();

        String sql =
                "SELECT tenLoai FROM LoaiCoSoVatChat ORDER BY tenLoai";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                dsLoai.add(rs.getString("tenloai"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Loi tai danh sach loai: " + e.getMessage()
            );
        }

        return dsLoai;
    }

    public boolean insert(CoSoVatChat cs) {

        String sql =
                "INSERT INTO CoSoVatChat "
                + "(Ma, Ten, MaLoai, PhongKhuVuc, SoLuong, "
                + "TinhTrang, NgayNhap, GhiChu) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, cs.getMa());
            ps.setString(2, cs.getTen());
            ps.setString(3, cs.getMaloai());
            ps.setString(4, cs.getPhongKhuVuc());
            ps.setInt(5, cs.getSoLuong());
            ps.setString(6, cs.getTinhTrang());

            if (cs.getNgayNhap() != null) {
                ps.setDate(
                        7,
                        java.sql.Date.valueOf(cs.getNgayNhap())
                );
            } else {
                ps.setNull(7, Types.DATE);
            }

            ps.setString(8, cs.getGhiChu());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Loi them du lieu: " + e.getMessage()
            );
            return false;
        }
    }

    public boolean update(CoSoVatChat cs) {

        String sql =
                "UPDATE CoSoVatChat SET "
                + "Ten=?, "
                + "MaLoai=?, "
                + "PhongKhuVuc=?, "
                + "SoLuong=?, "
                + "TinhTrang=?, "
                + "NgayNhap=?, "
                + "GhiChu=? "
                + "WHERE Ma=?";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, cs.getTen());
            ps.setString(2, cs.getMaloai());
            ps.setString(3, cs.getPhongKhuVuc());
            ps.setInt(4, cs.getSoLuong());
            ps.setString(5, cs.getTinhTrang());

            if (cs.getNgayNhap() != null) {
                ps.setDate(
                        6,
                        java.sql.Date.valueOf(cs.getNgayNhap())
                );
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.setString(7, cs.getGhiChu());
            ps.setString(8, cs.getMa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Loi cap nhat du lieu: " + e.getMessage()
            );
            return false;
        }
    }

    public boolean delete(String ma) {

        String sql =
                "DELETE FROM CoSoVatChat WHERE Ma=?";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, ma);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Loi xoa du lieu: " + e.getMessage()
            );
            return false;
        }
    }

    public boolean isTenTrung(String ten, String maBoQua) {

        String sql =
                "SELECT COUNT(*) "
                + "FROM CoSoVatChat "
                + "WHERE LOWER(LTRIM(RTRIM(Ten))) = LOWER(LTRIM(RTRIM(?))) "
                + "AND (? IS NULL OR Ma <> ?)";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, ten);
            ps.setString(2, maBoQua);
            ps.setString(3, maBoQua);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Loi kiem tra ten trung: " + e.getMessage()
            );
        }

        return false;
    }
    
}