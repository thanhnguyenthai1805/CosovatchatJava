package com.mycompany.managementstudent.ui;

import com.mycompany.managementstudent.dao.QuanLyTaiKhoanDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TaikhoanCosovatchatJFrame extends javax.swing.JFrame {
    private final QuanLyTaiKhoanDAO dao = new QuanLyTaiKhoanDAO();
    private DefaultTableModel model;
    private String username;
    private String matKhauCu = "";
    private boolean dangThem;
    private boolean dangSua;

    public TaikhoanCosovatchatJFrame() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !dangThem && !dangSua) {
                chonTaiKhoan();
            }
        });
        trangThai(false);
        them.setEnabled(false);
        sua.setEnabled(false);
        xoa.setEnabled(false);
    }

    public TaikhoanCosovatchatJFrame(JFrame owner, String vaiTro, String username) {
        this();
        if (!"Admin".equalsIgnoreCase(vaiTro) || username == null || username.isBlank()) {
            dispose();
            throw new IllegalArgumentException("Bạn không có quyền truy cập.");
        }
        this.username = username;
        setLocationRelativeTo(owner);
        taiDanhSach();
        trangThai(false);
        owner.setEnabled(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                owner.setEnabled(true);
                owner.toFront();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        title = new javax.swing.JLabel();
        lblMa = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblMk = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        lblList = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        hint = new javax.swing.JLabel();
        ma = new javax.swing.JTextField();
        ten = new javax.swing.JTextField();
        mk = new javax.swing.JTextField();
        role = new javax.swing.JComboBox<>();
        ngayNhap = new javax.swing.JTextField();
        them = new javax.swing.JButton();
        sua = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        luu = new javax.swing.JButton();
        huy = new javax.swing.JButton();
        trong = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý tài khoản");
        title.setText("QUẢN LÝ TÀI KHOẢN ĐĂNG NHẬP");
        lblMa.setText("Mã");
        lblTen.setText("Tên đăng nhập");
        lblMk.setText("Mật khẩu");
        lblRole.setText("Vai trò");
        lblNgay.setText("Ngày nhập");
        lblList.setText("Danh sách tài khoản");
        lblInfo.setText("Thông tin tài khoản");
        hint.setText("<html>Mật khẩu: ít nhất 8 ký tự, gồm chữ hoa, chữ số và ký tự đặc biệt.<br>Để trống mật khẩu khi sửa nếu không muốn đổi.</html>");
        them.setText("Thêm");
        sua.setText("Sửa");
        xoa.setText("Xóa");
        luu.setText("Lưu");
        huy.setText("Hủy");
        trong.setText("Làm trống");
        title.setFont(new java.awt.Font("Segoe UI", 1, 22));
        title.setBackground(new java.awt.Color(65, 83, 105));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setOpaque(true);
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ma.setEditable(false);
        ngayNhap.setEditable(false);
        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "Mã", "Tên đăng nhập", "Vai trò", "Ngày nhập" }) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        table.setRowHeight(25);
        scroll.setViewportView(table);
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { themActionPerformed(evt); }
        });
        sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { suaActionPerformed(evt); }
        });
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { xoaActionPerformed(evt); }
        });
        luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { luuActionPerformed(evt); }
        });
        huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { huyActionPerformed(evt); }
        });
        trong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { trongActionPerformed(evt); }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(title, 0, 920, 32767)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblList, -2, -2, -2)
                .addComponent(scroll, 350, 510, 32767))
                .addGap(16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblInfo, -2, -2, -2)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMa, -2, -2, -2)
                .addComponent(lblTen, -2, -2, -2)
                .addComponent(lblMk, -2, -2, -2)
                .addComponent(lblRole, -2, -2, -2)
                .addComponent(lblNgay, -2, -2, -2))
                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ma, 170, 250, 32767)
                .addComponent(ten, 170, 250, 32767)
                .addComponent(mk, 170, 250, 32767)
                .addComponent(role, 170, 250, 32767)
                .addComponent(ngayNhap, 170, 250, 32767)))
                .addComponent(hint, 0, 370, 32767)
                .addGroup(layout.createSequentialGroup()
                .addComponent(them, 85, 110, 32767)
                .addGap(6)
                .addComponent(sua, 85, 110, 32767)
                .addGap(6)
                .addComponent(xoa, 85, 110, 32767))
                .addGroup(layout.createSequentialGroup()
                .addComponent(luu, 85, 110, 32767)
                .addGap(6)
                .addComponent(huy, 85, 110, 32767)
                .addGap(6)
                .addComponent(trong, 85, 110, 32767)))))
                .addGap(12)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(12)
                .addComponent(title, -2, 56, -2)
                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(lblList, -2, -2, -2)
                .addGap(10)
                .addComponent(scroll, 220, 330, 32767))
                .addGroup(layout.createSequentialGroup()
                .addComponent(lblInfo, -2, -2, -2)
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMa, -2, -2, -2)
                .addComponent(ma, -2, -2, -2))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTen, -2, -2, -2)
                .addComponent(ten, -2, -2, -2))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMk, -2, -2, -2)
                .addComponent(mk, -2, -2, -2))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblRole, -2, -2, -2)
                .addComponent(role, -2, -2, -2))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblNgay, -2, -2, -2)
                .addComponent(ngayNhap, -2, -2, -2))
                .addGap(10)
                .addComponent(hint, -2, 54, -2)
                .addGap(16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(them, -2, -2, -2)
                .addComponent(sua, -2, -2, -2)
                .addComponent(xoa, -2, -2, -2))
                .addGap(8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(luu, -2, -2, -2)
                .addComponent(huy, -2, -2, -2)
                .addComponent(trong, -2, -2, -2))))
                .addGap(12)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        try {
            lamTrong();
            ma.setText(dao.maDuKien());
            ngayNhap.setText(dinhDangNgay(LocalDate.now()));
            role.setSelectedItem("User");
            dangThem = true;
            trangThai(true);
            ten.requestFocusInWindow();
        } catch (SQLException ex) {
            thongBao(ex.getMessage());
        }
    }//GEN-LAST:event_themActionPerformed

    private void suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaActionPerformed
        if (ma.getText().isEmpty()) {
            thongBao("Vui lòng chọn tài khoản.");
            return;
        }
        dangSua = true;
        trangThai(true);
        if (laTaiKhoanDangNhap()) {
            ten.setEditable(false);
            role.setEnabled(false);
        }
    }//GEN-LAST:event_suaActionPerformed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        if (ma.getText().isEmpty()) {
            thongBao("Vui lòng chọn tài khoản.");
            return;
        }
        if (laTaiKhoanDangNhap()) {
            thongBao("Không thể xóa tài khoản đang đăng nhập.");
            return;
        }
        int kq = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa tài khoản này không?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (kq == JOptionPane.YES_OPTION) {
            try {
                dao.xoa(ma.getText());
                taiDanhSach();
                thongBao("Xóa thành công.");
            } catch (SQLException ex) {
                thongBao(ex.getMessage());
            }
        }
    }//GEN-LAST:event_xoaActionPerformed

    private void luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luuActionPerformed
        String tenDangNhap = ten.getText().trim();
        String matKhau = mk.getText();
        if (dangSua && matKhau.equals(matKhauCu)) {
            matKhau = "";
        }
        String vaiTro = (String) role.getSelectedItem();
        if (tenDangNhap.isEmpty() || tenDangNhap.length() > 50 || vaiTro == null) {
            thongBao("Vui lòng kiểm tra tên đăng nhập và vai trò.");
            return;
        }
        if ((dangThem || !matKhau.isEmpty()) && !QuanLyTaiKhoanDAO.matKhauHopLe(matKhau)) {
            thongBao(QuanLyTaiKhoanDAO.YEU_CAU_MAT_KHAU);
            return;
        }
        try {
            if (dangThem) {
                dao.them(tenDangNhap, matKhau, vaiTro);
            } else if (dangSua) {
                dao.sua(ma.getText(), tenDangNhap, matKhau, vaiTro);
            } else {
                return;
            }
            dangThem = false;
            dangSua = false;
            taiDanhSach();
            trangThai(false);
            thongBao("Lưu thành công.");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 2601 || ex.getErrorCode() == 2627) {
                thongBao("Tên đăng nhập hoặc mã tài khoản đã tồn tại.");
            } else {
                thongBao(ex.getMessage());
            }
        }
    }//GEN-LAST:event_luuActionPerformed

    private void huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyActionPerformed
        dangThem = false;
        dangSua = false;
        lamTrong();
        trangThai(false);
    }//GEN-LAST:event_huyActionPerformed

    private void trongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trongActionPerformed
        lamTrong();
    }//GEN-LAST:event_trongActionPerformed

    private String dinhDangNgay(LocalDate ngay) {
        return ngay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void taiDanhSach() {
        try {
            java.util.List<Object[]> ds = dao.danhSach();
            model.setRowCount(0);
            for (Object[] tk : ds) {
                model.addRow(new Object[]{tk[0], tk[1], tk[2], dinhDangNgay((LocalDate) tk[3])});
            }
            lamTrong();
        } catch (SQLException ex) {
            thongBao(ex.getMessage());
        }
    }

    private void chonTaiKhoan() {
        int dong = table.getSelectedRow();
        if (dong >= 0) {
            ma.setText(model.getValueAt(dong, 0).toString());
            ten.setText(model.getValueAt(dong, 1).toString());
            role.setSelectedItem(model.getValueAt(dong, 2).toString());
            ngayNhap.setText(model.getValueAt(dong, 3).toString());
            matKhauCu = "";
            mk.setText("");
            if (username != null) {
                try {
                    matKhauCu = dao.layMatKhau(ma.getText());
                    mk.setText(matKhauCu);
                } catch (SQLException ex) {
                    thongBao(ex.getMessage());
                }
            }
        }
    }

    private boolean laTaiKhoanDangNhap() {
        return username != null && username.equalsIgnoreCase(ten.getText());
    }

    private void lamTrong() {
        table.clearSelection();
        ma.setText("");
        ten.setText("");
        mk.setText("");
        matKhauCu = "";
        role.setSelectedIndex(-1);
        ngayNhap.setText("");
    }

    private void trangThai(boolean dangNhapLieu) {
        ten.setEditable(dangNhapLieu);
        mk.setEditable(dangNhapLieu);
        role.setEnabled(dangNhapLieu);
        table.setEnabled(!dangNhapLieu);
        them.setEnabled(!dangNhapLieu);
        sua.setEnabled(!dangNhapLieu);
        xoa.setEnabled(!dangNhapLieu);
        trong.setEnabled(!dangNhapLieu);
        luu.setEnabled(dangNhapLieu);
        huy.setEnabled(dangNhapLieu);
    }

    private void thongBao(String noiDung) {
        JOptionPane.showMessageDialog(this, noiDung);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TaikhoanCosovatchatJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel title;
    private javax.swing.JLabel lblMa;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblMk;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel hint;
    private javax.swing.JTextField ma;
    private javax.swing.JTextField ten;
    private javax.swing.JTextField mk;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JTextField ngayNhap;
    private javax.swing.JButton them;
    private javax.swing.JButton sua;
    private javax.swing.JButton xoa;
    private javax.swing.JButton luu;
    private javax.swing.JButton huy;
    private javax.swing.JButton trong;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
