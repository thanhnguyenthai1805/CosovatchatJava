package com.mycompany.managementstudent.ui;

import com.mycompany.managementstudent.dao.CoSoVatChatDAO;
import com.mycompany.managementstudent.model.CoSoVatChat;
import com.mycompany.managementstudent.dao.ConnectionDB;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CoSoVatChatJFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CoSoVatChatJFrame.class.getName());
    private CoSoVatChatDAO dao = new CoSoVatChatDAO();
    List<CoSoVatChat> list = new ArrayList<CoSoVatChat>();
    private int pos = -1;
    private static int check = 0;
    private String vaitro;
    public CoSoVatChatJFrame(String vaitro) {
        initComponents();
        this.vaitro = vaitro;
        System.out.println("Vai trò nhận được: [" + this.vaitro + "]");
        phanquyen();
        loadLoai();
        tblCoso.setDefaultEditor(Object.class, null);
        viewTable();
        
        onOff(true, false);
        this.jPanel4.setBackground(Color.darkGray);

    }
    private void phanquyen(){
        boolean laAdmin = "admin".equalsIgnoreCase(vaitro);
        System.out.println("Vai trò trong JFrame: [" + vaitro + "]");
    System.out.println("Có quyền Admin: " + laAdmin);
        btnAdd.setEnabled(laAdmin);
        btnAddloai.setEnabled(laAdmin);
        btnDelete.setEnabled(laAdmin);
        btnExit.setEnabled(laAdmin);
        btnLamTrong.setEnabled(laAdmin);
        btnSave.setEnabled(laAdmin);
        btnUpdate.setEnabled(laAdmin);
        
       
        
    }
    public void clearForm() {

        this.txtDate.setText("");
        this.txtName.setText("");
        this.cboLoai.setSelectedIndex(0);
        this.txtNote.setText("");
        this.txtNumber.setText("");
        this.cboTinhTrang.setSelectedIndex(0);
        this.txtRoom.setText("");
    }

    private void view() {
        if (pos < 0 || pos >= list.size()) {
            pos = -1;
            clearForm();
            return;
        }
        CoSoVatChat x = list.get(pos);
        txtma.setText(x.getMa());
        txtName.setText(x.getTen());
        cboLoai.setSelectedItem(x.getTenLoai());
        txtRoom.setText(x.getPhongKhuVuc());
        txtNumber.setText("" + x.getSoLuong());
        cboTinhTrang.setSelectedItem(x.getTinhTrang());
        txtDate.setText(
                x.getNgayNhap() == null ? "" : x.getNgayNhap().toString()
        );
        txtNote.setText(x.getGhiChu());

    }

    private void viewTable() {
        list = dao.findAll();
        DefaultTableModel model = (DefaultTableModel) tblCoso.getModel();
        model.setRowCount(0);

        for (CoSoVatChat x : list) {
            model.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getTenLoai(),
                x.getPhongKhuVuc(),
                x.getSoLuong(),
                x.getTinhTrang(),
                x.getNgayNhap() == null ? "" : x.getNgayNhap().toString(),
                x.getGhiChu(),});
        }

    }
    private void loadLoai() {
    cboLoai.removeAllItems();

    cboLoai.addItem("Chọn loại");

    List<String> dsLoai = dao.findAllLoai();

    for (String tenLoai : dsLoai) {
        cboLoai.addItem(tenLoai);
    }

    cboLoai.setSelectedIndex(0);
}

    public void onOff(boolean a, boolean b) {
        this.btnAdd.show(a);
        this.btnUpdate.show(a);
        this.btnDelete.show(a);
        this.btnExit.show(b);

        this.btnSave.show(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCoso = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtName = new javax.swing.JTextPane();
        cboLoai = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRoom = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNumber = new javax.swing.JTextPane();
        cboTinhTrang = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDate = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLamTrong = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnAddloai = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtma = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtSearch = new javax.swing.JTextPane();
        btnSearch = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thai Thanh");
        setBackground(new java.awt.Color(0, 204, 255));

        jLabel5.setBackground(new java.awt.Color(0, 51, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CƠ SỞ VẬT CHẤT NHÀ TRƯỜNG");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Theo dõi phòng học, thiết bị và tình trạng sử dụng");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách cơ sở vật chất"));

        tblCoso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên cơ sở vật chất", "Loại", "Phòng / khu vực", "Số lượng", "Tình trạng", "Ngày nhập", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCoso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCosoMouseClicked(evt);
            }
        });
        tblCoso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCosoKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblCoso);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Thông tin bản ghi"));
        jPanel6.setName(""); // NOI18N

        jLabel2.setText("Tên :");

        jLabel3.setText("Loại :");

        jLabel4.setText("Số lượng :");

        jLabel6.setText("Phòng / khu vực :");

        jLabel7.setText("Ghi chú :");

        jLabel8.setText("Tình trạng :");

        jLabel9.setText("Ngày nhập :");

        jScrollPane1.setViewportView(txtName);

        cboLoai.addActionListener(this::cboLoaiActionPerformed);

        jScrollPane2.setViewportView(txtRoom);

        jScrollPane3.setViewportView(txtNumber);

        cboTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn tình trạng ", "Tốt ", "Đang sử dụng", "Cần sửa chữa", "Hỏng", "Thanh lý" }));
        cboTinhTrang.addActionListener(this::cboTinhTrangActionPerformed);

        jScrollPane4.setViewportView(txtDate);

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane5.setViewportView(txtNote);

        btnAdd.setText("Thêm Mới");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnLamTrong.setText("Làm trống");
        btnLamTrong.addActionListener(this::btnLamTrongActionPerformed);

        btnSave.setText("Lưu");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        btnExit.setText("Exit");
        btnExit.addActionListener(this::btnExitActionPerformed);

        btnAddloai.setText("+");
        btnAddloai.addActionListener(this::btnAddloaiActionPerformed);

        txtma.setEditable(false);
        jScrollPane7.setViewportView(txtma);

        jLabel10.setText("Mã :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnExit)
                            .addGap(107, 107, 107))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane3)
                                .addComponent(cboTinhTrang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4)
                                .addComponent(jScrollPane5)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAddloai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane7))
                            .addGap(44, 44, 44)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(12, 12, 12)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLamTrong)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAddloai))
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel6))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8))
                            .addComponent(cboTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnLamTrong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit)
                    .addComponent(btnSave))
                .addGap(37, 37, 37))
        );

        jLabel11.setText("Tìm kiếm theo tên, loại hoặc phòng:");

        jScrollPane8.setViewportView(txtSearch);

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        jButton1.setText("Load Table");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSearch)
                                .addComponent(jButton1)))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCosoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCosoMouseClicked
        // TODO add your handling code here:
        pos = this.tblCoso.getSelectedRow();
        view();
    }//GEN-LAST:event_tblCosoMouseClicked

    private void tblCosoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCosoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCosoKeyPressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        String name = this.txtName.getText();
        String tenloai = this.cboLoai.getSelectedItem().toString();
        String room = this.txtRoom.getText();
        String txtnumber = this.txtNumber.getText().trim();
        String tinhtrang = this.cboTinhTrang.getSelectedItem().toString();
        String date = this.txtDate.getText();
        String note = this.txtNote.getText();

        if (name.isEmpty() || room.isEmpty() || txtnumber.isEmpty() || date.isEmpty() || note.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        if (tenloai.trim().equals("Chọn loại") || tinhtrang.trim().equals("Chọn tình trạng")) {
            JOptionPane.showConfirmDialog(this, "Vui lòng chọn loại và tình trang");
            return;
        }
        String sql = "SELECT MaLoai FROM LoaiCoSoVatChat WHERE TenLoai = ?";
        String maLoai;

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenloai);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy mã loại!");
                    return;
                }
                maLoai = rs.getString("MaLoai");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }
        int number;
        try {
            number = Integer.parseInt(txtnumber);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên");
            return;
        }
        if (number < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên");
            return;
        }
        java.time.LocalDate ngaynhap;

        try {
            ngaynhap = java.time.LocalDate.parse(date);
        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ! Hãy nhập theo dạng yyyy-MM-dd.");
            return;
        }

        String tenkiemtra = name.trim();
        String maboqua = "0";
        String ma = "0";
        if (check == -1) {
            if (pos < 0 || pos >= list.size()) {
                JOptionPane.showMessageDialog(
                        this, "Chọn bản ghi cần sửa"
                );
                return;
            }

            ma = list.get(pos).getMa();
            maboqua = ma;
        }
        if (dao.isTenTrung(tenkiemtra, maboqua)) {
            JOptionPane.showMessageDialog(this, "Tên cơ sở vật chất đã tồn tại! Vui lòng nhập tên khác.");
            txtName.requestFocus();
            txtName.selectAll();
            return;
        }
        // Tạo đối tượng với mã tự động khi thêm mới,
        // hoặc mã thật khi cập nhật
        CoSoVatChat cs = new CoSoVatChat(
                ma, name, tenloai,maLoai, room, number,
                tinhtrang, ngaynhap, note
        );

        if (check == 1) {
            // Thêm mới: SQL Server tự sinh Ma
            boolean resultAdd = dao.insert(cs);

            if (resultAdd) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                clearForm();
                pos = -1;
                check = 0;
                viewTable();
                onOff(true, false);
            }

        } else if (check == -1) {
            boolean resultUpdate = dao.update(cs);
            if (resultUpdate) {

                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                check = 0;
                pos = -1;
                clearForm();
                viewTable();
                onOff(true, false);

            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLamTrongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamTrongActionPerformed
        // TODO add your handling code here:
        //Làm trống
        clearForm();
    }//GEN-LAST:event_btnLamTrongActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
       
        txtma.setEditable(false);
        if (pos < 0 || pos >= list.size()) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        check = -1;

        onOff(false, true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        txtma.setText("");
        txtma.setEditable(false);
       check = 1;
        clearForm();
        pos = -1;
        onOff(false, true);

    }//GEN-LAST:event_btnAddActionPerformed

    private void cboTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTinhTrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTinhTrangActionPerformed

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboLoaiActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:

        check = 0;
        if (pos >= 0 && pos < list.size()) {
            view();
        } else {
            clearForm();
        }
        pos = -1;
        onOff(true, false);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (pos < 0 || pos >= list.size()) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng cần xóa");
            return;
        }
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String ma = list.get(pos).getMa();
            boolean kq = dao.delete(ma);
            if (kq) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                pos = -1;
                check = 0;
                clearForm();
                viewTable();
                onOff(true, false);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String keyword = txtSearch.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tblCoso.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (CoSoVatChat x : list) {
            String name = x.getTen().toLowerCase();
            String room = x.getPhongKhuVuc().toLowerCase();
            String loai = x.getTenLoai().toLowerCase();
            if (name.contains(keyword) || room.contains(keyword) || loai.contains(keyword)) {
                model.addRow(new Object[]{
                    x.getMa(),
                    x.getTen(),
                    x.getTenLoai(),
                    x.getPhongKhuVuc(),
                    x.getSoLuong(),
                    x.getTinhTrang(),
                    x.getNgayNhap(),
                    x.getGhiChu(),
                    stt++
                });
            }
        }
        if (stt == 1) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.txtSearch.setText("");
        viewTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAddloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddloaiActionPerformed
        // TODO add your handling code here:
        LoaiCoSoVatChatJFrame form = new LoaiCoSoVatChatJFrame();
        form.addWindowListener(
        new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(
                    java.awt.event.WindowEvent e) {

                // Tải lại danh sách loại từ database
                loadLoai();
            }
        }
    );
        form.setLocationRelativeTo(null);
        form.setVisible(true);
        
    }//GEN-LAST:event_btnAddloaiActionPerformed

    public List<String> findAllLoai() {
        List<String> dsLoai = new ArrayList<>();

        String sql = """
        SELECT TenLoai
        FROM dbo.LoaiCoSoVatChat
        ORDER BY TenLoai
        """;

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                dsLoai.add(rs.getString("TenLoai"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Lỗi tải danh sách loại: " + e.getMessage()
            );
        }

        return dsLoai;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LoginCoSoVatChatJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddloai;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLamTrong;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboTinhTrang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblCoso;
    private javax.swing.JTextPane txtDate;
    private javax.swing.JTextPane txtName;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextPane txtNumber;
    private javax.swing.JTextPane txtRoom;
    private javax.swing.JTextPane txtSearch;
    private javax.swing.JTextPane txtma;
    // End of variables declaration//GEN-END:variables
}
