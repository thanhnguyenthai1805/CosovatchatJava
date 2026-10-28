package com.mycompany.managementstudent.dao;

import com.mycompany.managementstudent.model.LoaiCoSoVatChat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoaiCoSoVatChatDAO {

    public List<LoaiCoSoVatChat> findAll() {

        List<LoaiCoSoVatChat> list = new ArrayList<>();

        String sql =
                "SELECT maloai, tenloai "
                + "FROM loaicosovatchat "
                + "ORDER BY maloai";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(
                    new LoaiCoSoVatChat(
                        rs.getString("maloai"),
                        rs.getString("tenloai")
                    )
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Loi tai danh sach loai: " + e.getMessage()
            );
        }

        return list;
    }

    public boolean insert(LoaiCoSoVatChat loai) {

        String sql =
                "INSERT INTO loaicosovatchat(maloai, tenloai) "
                + "VALUES (?, ?)";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, loai.getMaloai());
            ps.setString(2, loai.getTenloai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Loi them loai: " + e.getMessage()
            );

            return false;
        }
    }

    public boolean update(String maloai, String tenloai) {

        String sql =
                "UPDATE loaicosovatchat "
                + "SET tenloai = ? "
                + "WHERE maloai = ?";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, tenloai.trim());
            ps.setString(2, maloai);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Loi cap nhat loai: " + e.getMessage()
            );

            return false;
        }
    }

    public boolean delete(String maloai) {

        String sql =
                "DELETE FROM loaicosovatchat "
                + "WHERE maloai = ?";

        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, maloai);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Loi xoa loai: " + e.getMessage()
            );

            return false;
        }
    }
}