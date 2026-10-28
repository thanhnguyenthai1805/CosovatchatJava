package com.mycompany.managementstudent.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    private static final String URL =
        "jdbc:sqlserver://localhost:1433;"
        + "databaseName=Quanlycosovatchat;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";

    private static final String USER = "thanh";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() {

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {

            System.out.println("Ket noi that bai");
            e.printStackTrace();
            return null;
        }
    }
}