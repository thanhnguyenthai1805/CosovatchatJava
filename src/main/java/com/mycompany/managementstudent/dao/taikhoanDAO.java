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
        String sql = "select tendangnhap,matkhau from taikhoan where tendangnhap =? and matkhau = ?";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tendangnhap);
            ps.setString(2, matkhau);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new taikhoan(
                            rs.getString("tendangnhap"),
                            rs.getString("matkhau")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return null;

    }
}
