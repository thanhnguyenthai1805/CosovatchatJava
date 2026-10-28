package com.mycompany.managementstudent.database;


import java.sql.Connection;

public class TestConnect {

    public static void main(String[] args) {

        Connection conn = ConnectionDB.getConnection();

        if (conn != null) {
            System.out.println("KET NOI THANH CONG");
        } else {
            System.out.println("KET NOI THAT BAI");
        }
    }
}