package com.crimsonlogic.InsuranceManagementSystemIMS.Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class DatabaseConnection {
//    private static final String URL = "jdbc:postgresql://localhost:5432/insuranceIMS";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "crimson@123";
//
//    static {
//        try {
//            // Register the PostgreSQL driver
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            System.err.println("PostgreSQL JDBC driver not found.");
//            e.printStackTrace();
//        }
//    }
//
//    // Method to get a connection
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//}


public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                // Load the database driver
                Class.forName("org.postgresql.Driver");
                // Establish the connection
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/insuranceIMS", "postgres", "crimson@123");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
