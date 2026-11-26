// Bổ sung chức năng quản lí tài khoản
package com.mycompany.managementstudent.ui;

import com.mycompany.managementstudent.dao.QuanLyTaiKhoanDAO;
import com.mycompany.managementstudent.dao.QuanLyTaiKhoanDAO.TaiKhoan;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QuanLyTaiKhoanDialog extends JDialog {
    private final QuanLyTaiKhoanDAO dao = new QuanLyTaiKhoanDAO();
    private final String current;
    private final DefaultTableModel model = new DefaultTableModel(new String[]{"Mã", "Tên đăng nhập", "Vai trò", "Ngày nhập"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable table = new JTable(model);
    private final JTextField ma = new JTextField(), ten = new JTextField();
    // Bổ sung chức năng quản lí tài khoản
    private final JTextField ngayNhap = new JTextField();
    private final JPasswordField mk = new JPasswordField();
    private final JComboBox<String> role = new JComboBox<>(new String[]{"Admin", "User"});
    private final JButton them = new JButton("Thêm"), sua = new JButton("Sửa"), xoa = new JButton("Xóa"),
            luu = new JButton("Lưu"), huy = new JButton("Hủy"), trong = new JButton("Làm trống");
    private java.util.List<TaiKhoan> rows = new ArrayList<>();
    private TaiKhoan selected;
    private int mode;

    public QuanLyTaiKhoanDialog(JFrame owner, String vaiTro, String username) {
        super(owner, "Quản lý tài khoản", true);
        if (!"Admin".equalsIgnoreCase(vaiTro) || username == null || username.isBlank())
            throw new IllegalArgumentException("Bạn không có quyền truy cập.");
        current = username;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel root = new JPanel(new BorderLayout(12,12)); root.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        JLabel title = new JLabel("QUẢN LÝ TÀI KHOẢN ĐĂNG NHẬP");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f)); title.setForeground(Color.WHITE);
        title.setOpaque(true); title.setBackground(new Color(65,83,105)); title.setBorder(BorderFactory.createEmptyBorder(16,12,16,12));
        root.add(title, BorderLayout.NORTH);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); table.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(table); scroll.setBorder(BorderFactory.createTitledBorder("Danh sách tài khoản"));
        root.add(scroll, BorderLayout.CENTER);
        JPanel right = new JPanel(new BorderLayout(8,8)); right.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));
        JPanel fields = new JPanel(new GridLayout(0,2,8,10));
        fields.add(new JLabel("Mã")); fields.add(ma); fields.add(new JLabel("Tên đăng nhập")); fields.add(ten);
        fields.add(new JLabel("Mật khẩu")); fields.add(mk); fields.add(new JLabel("Vai trò")); fields.add(role);
        // Bổ sung chức năng quản lí tài khoản
        fields.add(new JLabel("Ngày nhập")); fields.add(ngayNhap); ngayNhap.setEditable(false);
        JPanel top = new JPanel(new BorderLayout(4,8)); top.add(fields, BorderLayout.NORTH);
        // Bổ sung chức năng quản lí tài khoản: hiển thị yêu cầu mật khẩu.
        top.add(new JLabel("<html>Mật khẩu: ít nhất 8 ký tự, gồm chữ hoa, chữ số và ký tự đặc biệt.<br>Để trống mật khẩu khi sửa nếu không muốn đổi.</html>"), BorderLayout.CENTER);
        right.add(top, BorderLayout.NORTH);
        JPanel buttons = new JPanel(new GridLayout(0,3,6,6));
        for (JButton b : new JButton[]{them,sua,xoa,luu,huy,trong}) buttons.add(b);
        right.add(buttons, BorderLayout.SOUTH); right.setPreferredSize(new Dimension(370,300)); root.add(right, BorderLayout.EAST);
        setContentPane(root); setSize(950,470); setMinimumSize(new Dimension(820,370)); setLocationRelativeTo(owner);
        table.getSelectionModel().addListSelectionListener(e -> { if (!e.getValueIsAdjusting() && mode == 0) select(); });
        them.addActionListener(e -> { beginAdd(); });
        sua.addActionListener(e -> { if (requireSelection()) { mode=2; state(); ten.requestFocusInWindow(); } });
        xoa.addActionListener(e -> remove()); luu.addActionListener(e -> save());
        huy.addActionListener(e -> { mode=0; clear(); state(); }); trong.addActionListener(e -> clear());
        reload(); state();
    }
    // Bổ sung chức năng quản lí tài khoản: mã và ngày nhập tự động.
    private String formatDate(java.time.LocalDate date) {
        return date == null ? "" : date.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    private void beginAdd() {
        try {
            String next = dao.maDuKien();
            clear(); ma.setText(next); ngayNhap.setText(formatDate(java.time.LocalDate.now()));
            role.setSelectedItem("User"); mode=1; state(); ten.requestFocusInWindow();
        } catch (SQLException ex) { message("Lỗi tạo mã: " + ex.getMessage()); }
    }
    private void select() {
        int i=table.getSelectedRow(); if(i<0 || i>=rows.size()) return;
        selected=rows.get(i); ma.setText(String.valueOf(selected.ma())); ten.setText(selected.ten());
        mk.setText(""); role.setSelectedItem(selected.vaiTro()); ngayNhap.setText(formatDate(selected.ngayNhap()));
    }
    private void clear() { selected=null; table.clearSelection(); ma.setText(""); ngayNhap.setText(""); ten.setText(""); mk.setText(""); role.setSelectedIndex(-1); }
    private void state() {
        boolean edit=mode!=0; ma.setEditable(false); ten.setEditable(edit); mk.setEditable(edit); role.setEnabled(edit);
        table.setEnabled(!edit); them.setEnabled(!edit); sua.setEnabled(!edit); xoa.setEnabled(!edit);
        trong.setEnabled(!edit); luu.setEnabled(edit); huy.setEnabled(edit);
        if (mode==2 && self()) { ten.setEditable(false); role.setEnabled(false); }
    }
    private boolean self() { return selected!=null && current.equalsIgnoreCase(selected.ten()); }
    private boolean requireSelection() { if(selected!=null) return true; message("Vui lòng chọn tài khoản."); return false; }
    private void message(String s) { JOptionPane.showMessageDialog(this,s); }
    private void reload() {
        try { rows=dao.danhSach(); model.setRowCount(0); for(TaiKhoan t:rows) model.addRow(new Object[]{t.ma(),t.ten(),t.vaiTro(),formatDate(t.ngayNhap())}); clear(); }
        catch(SQLException ex) { message("Lỗi tải tài khoản: "+ex.getMessage()); }
    }
    private void save() {
        String name=ten.getText().trim(), password=new String(mk.getPassword()), r=(String)role.getSelectedItem();
        if(name.isBlank() || name.length()>50 || r==null || password.length()>255) {
            message("Kiểm tra tên đăng nhập (tối đa 50 ký tự), mật khẩu và vai trò."); return;
        }
        // Bổ sung chức năng quản lí tài khoản: kiểm tra trước khi lưu.
        if ((mode == 1 || !password.isEmpty()) && !QuanLyTaiKhoanDAO.matKhauHopLe(password)) {
            message(QuanLyTaiKhoanDAO.YEU_CAU_MAT_KHAU); mk.requestFocusInWindow(); return;
        }
        try {
            if(mode==1) { String actual = dao.them(name,password,r); ma.setText(actual); }
            else if(mode==2 && selected!=null) dao.sua(selected.ma(),self()?selected.ten():name,password,self()?selected.vaiTro():r);
            else return;
            mode=0; reload(); state(); message("Lưu tài khoản thành công.");
        } catch(SQLException ex) { message(ex.getErrorCode()==2601 || ex.getErrorCode()==2627 ? "Tên đăng nhập đã tồn tại." : "Lỗi lưu: "+ex.getMessage()); }
    }
    private void remove() {
        if(!requireSelection()) return;
        if(self()) { message("Không thể xóa tài khoản đang đăng nhập!"); return; }
        if(JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa tài khoản này?","Xác nhận",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION) return;
        try { dao.xoa(selected.ma()); reload(); message("Xóa tài khoản thành công."); }
        catch(SQLException ex) { message("Lỗi xóa: "+ex.getMessage()); }
    }
}
