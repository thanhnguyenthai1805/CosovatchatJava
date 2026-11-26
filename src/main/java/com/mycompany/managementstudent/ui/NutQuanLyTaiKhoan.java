// Bổ sung chức năng quản lí tài khoản
package com.mycompany.managementstudent.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class NutQuanLyTaiKhoan {
    private NutQuanLyTaiKhoan() {}
    public static void gan(CoSoVatChatJFrame frame, String vaiTro, String username) {
        if (!"Admin".equalsIgnoreCase(vaiTro) || username == null || username.isBlank()) return;
        JLabel title = find(frame.getContentPane());
        if (title == null) throw new IllegalStateException("Không tìm thấy tiêu đề giao diện chính.");
        Container header = title.getParent();
        JLayeredPane layer = frame.getLayeredPane();
        if (Boolean.TRUE.equals(layer.getClientProperty("quanLyTaiKhoan"))) return;
        layer.putClientProperty("quanLyTaiKhoan", true);
        JButton button = new JButton("Quản lý tài khoản");
        button.addActionListener(e -> new QuanLyTaiKhoanDialog(frame, vaiTro, username).setVisible(true));
        layer.add(button, JLayeredPane.PALETTE_LAYER);
        Runnable position = () -> {
            Rectangle b = SwingUtilities.convertRectangle(header.getParent(), header.getBounds(), layer);
            Dimension d = button.getPreferredSize();
            button.setBounds(b.x+b.width-d.width-16, b.y+16, d.width, Math.max(32,d.height));
        };
        header.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) { position.run(); }
            @Override public void componentMoved(ComponentEvent e) { position.run(); }
        });
        frame.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) { SwingUtilities.invokeLater(position); }
            @Override public void componentShown(ComponentEvent e) { SwingUtilities.invokeLater(position); }
        });
        SwingUtilities.invokeLater(position);
    }
    private static JLabel find(Container parent) {
        for (Component c : parent.getComponents()) {
            if(c instanceof JLabel l && "CƠ SỞ VẬT CHẤT NHÀ TRƯỜNG".equals(l.getText())) return l;
            if(c instanceof Container p) { JLabel l=find(p); if(l!=null) return l; }
        }
        return null;
    }
}
