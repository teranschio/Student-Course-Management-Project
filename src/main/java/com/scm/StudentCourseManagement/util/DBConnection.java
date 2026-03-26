package com.scm.StudentCourseManagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Railway MySQL connection details
    private static final String URL = "jdbc:mysql://caboose.proxy.rlwy.net:44793/railway?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "VCyzEFwuGITfomspGdLxmewHmTIePygs";

    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Return connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException(e);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            throw e;
        }
    }

    // Optional: quick test method
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            if (con != null) {
                System.out.println("Connected successfully to Railway MySQL!");
            } else {
                System.out.println("Connection failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}