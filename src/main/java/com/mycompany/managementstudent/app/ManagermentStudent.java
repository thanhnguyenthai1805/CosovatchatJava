/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.managementstudent.app;

import com.mycompany.managementstudent.database.ConnectionDB;
import com.mycompany.managementstudent.ui.LoginCoSoVatChatJFrame;
import java.sql.Connection;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class ManagermentStudent {

    public static void main(String[] args) {
        
        Connection connection = ConnectionDB.getConnection();

        if (connection != null) {
            System.out.println("KET NOI DATABASE THANH CONG!");
        } else {
            System.out.println("KET NOI DATABASE THAT BAI!");
        }
        SwingUtilities.invokeLater(()->{
            LoginCoSoVatChatJFrame login = new LoginCoSoVatChatJFrame();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }
    
    }

