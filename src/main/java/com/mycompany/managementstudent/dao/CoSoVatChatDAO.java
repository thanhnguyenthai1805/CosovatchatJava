package com.mycompany.managementstudent.dao;

import com.mycompany.managementstudent.model.CoSoVatChat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CoSoVatChatDAO {

    public List<CoSoVatChat> findAll() {

        // Tạo một danh sách rỗng để chứa dữ liệu
        List<CoSoVatChat> list = new ArrayList<>();
        
        String sql = "Select * from cosovatchat order by ma";
        try {

            // Bước 1: Kết nối đến database
            Connection conn = ConnectionDB.getConnection();

            // Bước 3: Chuẩn bị câu SQL
            PreparedStatement ps = conn.prepareStatement(sql);

            // Bước 4: Thực hiện câu SQL và nhận kết quả
            ResultSet rs = ps.executeQuery();

            // Bước 5: Đọc từng dòng dữ liệu
            while (rs.next()) {

                // Tạo một đối tượng CoSoVatChat
                CoSoVatChat cs = new CoSoVatChat();

                // Lấy dữ liệu từ database đưa vào đối tượng
                cs.setMa(rs.getInt("ma"));
                cs.setTen(rs.getString("ten"));
                cs.setLoai(rs.getString("loai"));
                cs.setPhongKhuVuc(rs.getString("phongkhuvuc"));
                cs.setSoLuong(rs.getInt("soluong"));
                cs.setTinhTrang(rs.getString("tinhtrang"));

                // Kiểm tra NgayNhap có dữ liệu hay không
                if (rs.getDate("ngaynhap") != null) {

                    cs.setNgayNhap(
                        rs.getDate("ngaynhap").toLocalDate()
                    );
                }

                // Lấy ghi chú
                cs.setGhiChu(rs.getString("GhiChu"));

                // Thêm đối tượng vào danh sách
                list.add(cs);
            }

        } catch (SQLException e) {

            // Hiển thị lỗi nếu có
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Trả danh sách về
        return list;
    }
    public List<String> findAllloai(){
       List<String> dsloai = new ArrayList<>();
       String sql = "select tenloai from loaicosovatchat order by tenloai";
        try(Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                dsloai.add(rs.getString("tenloai"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }return dsloai;
    }
    public boolean insert(CoSoVatChat cs){
        String sql = "INSERT INTO CoSoVatChat "
            + "(Ten, Loai, PhongKhuVuc, SoLuong, TinhTrang, NgayNhap, GhiChu) "
            + "VALUES (  ?, ?, ?, ?, ?, ?,?)";
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps =conn.prepareStatement(sql);
    
            ps.setString(1, cs.getTen());
            ps.setString(2, cs.getLoai());
            ps.setString(3, cs.getPhongKhuVuc());
            ps.setInt(4,cs.getSoLuong() );
            ps.setString(5,cs.getTinhTrang());
            ps.setDate(6, java.sql.Date.valueOf(cs.getNgayNhap()));
            ps.setString(7,cs.getGhiChu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage() );
            return false;
        }
    }
    public boolean  update(CoSoVatChat cs){
        String sql = "update cosovatchat set ten= ?,loai = ?,phongkhuvuc = ? , soluong = ?,tinhtrang = ? , ngaynhap = ?,ghichu = ? where ma = ? ";
        try {
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps =conn.prepareStatement(sql);

            ps.setString(1, cs.getTen());
            ps.setString(2, cs.getLoai());
            ps.setString(3, cs.getPhongKhuVuc());
            ps.setInt(4,cs.getSoLuong() );
            ps.setString(5,cs.getTinhTrang());
            ps.setDate(6, java.sql.Date.valueOf(cs.getNgayNhap()));
            ps.setString(7, cs.getGhiChu());
            ps.setInt(8, cs.getMa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage() );
            return false;
        }          
    }
    public boolean delete(int ma){
        String sql = "delete from cosovatchat where ma =? ";
        try {
            Connection conn =ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,ma);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    public boolean isTenTrung(String ten ,int maboqua){
        String sql = """
                     select count(*)
                     from cosovatchat
                     where lower(ltrim(rtrim(ten))) =lower(ltrim(rtrim(?))) and ma <> ?
                     """;
        try(Connection conn =ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,ten);
            ps.setInt(2, maboqua);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    public List<String> findAllLoai() {
    List<String> dsLoai = new ArrayList<>();

    String sql = "SELECT TenLoai FROM dbo.LoaiCoSoVatChat ORDER BY TenLoai";

    try (
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        while (rs.next()) {
            dsLoai.add(rs.getString("TenLoai"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            null,
            "Lỗi tải danh sách loại: " + e.getMessage()
        );
    }

    return dsLoai;
}
}

    