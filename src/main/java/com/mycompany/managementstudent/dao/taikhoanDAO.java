/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.managementstudent.dao;

import com.mycompany.managementstudent.model.taikhoan;
import com.mycompany.managementstudent.database.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class taikhoanDAO {

    public taikhoan dangnhap(String tendangnhap, String matkhau) {
        String sql = """
                     select tendangnhap,matkhau,vaitro from taikhoan 
                     where tendangnhap =? and matkhau = ?""";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tendangnhap);
            ps.setString(2, matkhau);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    taikhoan tk = new taikhoan();
                    tk.setTendangnhap(rs.getString("tendangnhap"));
                    tk.setMatkhau(rs.getString("matkhau"));
                    tk.setVaitro(rs.getString("vaitro"));
                    System.out.println();
                    return tk;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
            null,
            "Lỗi đăng nhập: " + e.getMessage()
        );
        }
        return null;

    }
}
