/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.managementstudent.dao;

import com.mycompany.managementstudent.model.LoaiCoSoVatChat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class LoaiCoSoVatChatDAO {
    public List<LoaiCoSoVatChat> fillAll(){
        List<LoaiCoSoVatChat> list = new ArrayList<>();
        String sql = """
                    
                select maloai,tenloai
                        from loaicosovatchat
                                order by maloai
                """
               ;
        
        try(Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                list.add(new LoaiCoSoVatChat(
                rs.getInt("maloai"),
                rs.getString("tenloai")
                ));
            }
            
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }
    public boolean insert(String tenloai){
        String sql = """
                     insert into loaicosovatchat(tenloai)
                     values (?)
                     
                     """;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenloai.trim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }
    }
    public boolean update(int maloai , String tenloai){
        String sql = """
                     update loaicosovatchat set tenloai =? where maloai = ?
                     """;
        try (Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            
            ps.setString(1,tenloai.trim());
            ps.setInt(2, maloai);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    public boolean delete(int maloai){
        String sql = """
                     delete from loaicosovatchat where maloai =?
                     """;
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maloai);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
