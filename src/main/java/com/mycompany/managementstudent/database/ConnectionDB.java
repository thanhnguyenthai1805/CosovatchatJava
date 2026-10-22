/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.managementstudent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionDB {
    private static  final String URL =  
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Quanlycosovatchat;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USER = "thanh";
    private static final String PASSWORD = "12345678";
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            System.out.println("Ket noi thanh bai");
            System.out.println(e.getMessage());
            return null;
        }
    }
}

