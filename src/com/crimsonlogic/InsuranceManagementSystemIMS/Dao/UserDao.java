package com.crimsonlogic.InsuranceManagementSystemIMS.Dao;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.User;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao() {
        connection = DatabaseConnection.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (user_mobile_number,first_name,last_name,user_email,username, password) VALUES (?, ?,?,?,?,?)");
            ps.setLong(1,user.getPhoneNumber());
            ps.setString(2, user.getFirstName());
            ps.setString(3,user.getLastName() );
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
//            ps.setString(3, user.getRole());
            ps.executeUpdate();
            System.out.println("user added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    public void updateUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users SET username=?, password=?, role=? WHERE user_id=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE user_id=?");
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE user_id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


//
//public class UserDao {
//	
    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try {
        	PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("user_email"));
                    user.setPhoneNumber(rs.getLong(("user_mobile_number")));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }


    public String getRoleFromDatabase(String username, String password) {
        String role = null;
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";

        try {Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

	}
//   
//    // Add other methods for user operations (e.g., add, update, delete)
//


//public List<User> getAllUsers() throws SQLException {
//    String query = "SELECT * FROM users";
//    List<User> users = new ArrayList<>();
//    try (Connection conn = DatabaseConnection.getConnection();
//         PreparedStatement ps = conn.prepareStatement(query);
//         ResultSet rs = ps.executeQuery()) {
//        while (rs.next()) {
//            User user = new User();
//            user.setUserId(rs.getInt("user_id"));
//            user.setUsername(rs.getString("username"));
//            user.setPassword(rs.getString("password"));
//           user.setRole(rs.getString("role"));
//            users.add(user);
//        }
//    }
//    return users;
//}
//public User getUserById(int userId) throws SQLException {
//    String query = "SELECT * FROM users WHERE user_id = ?";
//    User user = null;
//
//    try (Connection conn = DatabaseConnection.getConnection();
//         PreparedStatement ps = conn.prepareStatement(query)) {
//
//        ps.setInt(1, userId);
//        try (ResultSet rs = ps.executeQuery()) {
//            if (rs.next()) {
//                user = new User();
//                user.setUserId(rs.getInt("user_id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password")); // Password should be hashed in production
//                user.setRole(rs.getString("role"));
//            }
//        }
//    }
//    return user;
//}
// // Retrieve user by username
//
