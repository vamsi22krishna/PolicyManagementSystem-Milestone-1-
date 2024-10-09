package com.crimsonlogic.InsuranceManagementSystemIMS.Dao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Policy;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.UserPolicy;

public class UserPolicyDao {
    private Connection connection;

    public UserPolicyDao() {
        connection = DatabaseConnection.getConnection();
    }

    public void addUserPolicy(UserPolicy userPolicy) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO userPolicies (user_id, policy_id, start_date, end_date) VALUES (?, ?, ?, ?)");
            ps.setInt(1, userPolicy.getUserId());
            ps.setInt(2, userPolicy.getPolicyId());
            ps.setDate(3, userPolicy.getStartDate());
            ps.setDate(4, userPolicy.getEndDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserPolicy(UserPolicy userPolicy) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE userPolicies SET user_id=?, policy_id=?, start_date=?, end_date=? WHERE user_policy_id=?");
            ps.setInt(1, userPolicy.getUserId());
            ps.setInt(2, userPolicy.getPolicyId());
            ps.setDate(3, userPolicy.getStartDate());
            ps.setDate(4, userPolicy.getEndDate());
//            ps.setInt(5, userPolicy.getUserPolicyId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserPolicy(int userPolicyId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM userPolicies WHERE user_policy_id=?");
            ps.setInt(1, userPolicyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserPolicy getUserPolicyById(int userPolicyId) {
        UserPolicy userPolicy = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM userPolicies WHERE user_policy_id=?");
            ps.setInt(1, userPolicyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userPolicy = new UserPolicy();
//                userPolicy.setUserPolicyId(rs.getInt("user_policy_id"));
                userPolicy.setUserId(rs.getInt("user_id"));
                userPolicy.setPolicyId(rs.getInt("policy_id"));
                userPolicy.setStartDate(rs.getDate("start_date"));
                userPolicy.setEndDate(rs.getDate("end_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userPolicy;
    }

    public List<UserPolicy> getAllUserPolicies() {
        List<UserPolicy> userPolicies = new ArrayList<>();
        String query = "SELECT up.user_id, up.policy_id, up.start_date, up.end_date, " +
                       "up.policy_name, up.category_name, up.subcategory_name,up.status " +
                       "FROM userPolicies up " +
                       "JOIN policies p ON up.policy_id = p.policy_id " +
                       "JOIN categories c ON p.category_id = c.category_id " +
                       "JOIN subCategories s ON p.subcategory_id = s.subcategory_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                UserPolicy userPolicy = new UserPolicy();
                userPolicy.setUserId(rs.getInt("user_id"));
                userPolicy.setPolicyId(rs.getInt("policy_id"));
                userPolicy.setPolicyName(rs.getString("policy_name"));
                userPolicy.setCategoryName(rs.getString("category_name"));
                userPolicy.setSubCategoryName(rs.getString("subcategory_name"));
                userPolicy.setStartDate(rs.getDate("start_date"));
                userPolicy.setEndDate(rs.getDate("end_date"));
                userPolicy.setStatus(rs.getString("status"));
                userPolicies.add(userPolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userPolicies;
    }

    public List<UserPolicy> getUserPolicyByUserId(int userId) {
        List<UserPolicy> userPolicies = new ArrayList<>();
        String query = "SELECT up.user_id, up.policy_id, up.start_date, up.end_date, " +
                       "up.policy_name, up.category_name, up.subcategory_name,up.status " +
                       "FROM userPolicies up " +
                       "JOIN policies p ON up.policy_id = p.policy_id " +
                       "JOIN categories c ON p.category_id = c.category_id " +
                       "JOIN subCategories s ON p.subcategory_id = s.subcategory_id " +
                       "WHERE up.user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserPolicy userPolicy = new UserPolicy();
                    userPolicy.setUserId(rs.getInt("user_id"));
                    userPolicy.setPolicyId(rs.getInt("policy_id"));
                    userPolicy.setPolicyName(rs.getString("policy_name"));
                    userPolicy.setCategoryName(rs.getString("category_name"));
                    userPolicy.setSubCategoryName(rs.getString("subcategory_name"));
                    userPolicy.setStartDate(rs.getDate("start_date"));
                    userPolicy.setEndDate(rs.getDate("end_date"));
                    userPolicy.setStatus(rs.getString("status"));
                    userPolicies.add(userPolicy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userPolicies;
    }
    public void updatePolicyStatus(int userId, int policyId, String status) throws SQLException {
        String query = "UPDATE userPolicies SET status = ? WHERE user_id = ? AND policy_id = ?";
        try {
        	PreparedStatement ps = connection.prepareStatement(query);
        
            ps.setString(1, status);
            ps.setInt(2, userId);
            ps.setInt(3, policyId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addUserPolicy(int userId, String userName, int policyId, String policyName, BigDecimal amount, int age,String categoryName,String subCategoryName) throws SQLException {
        String query = "INSERT INTO userPolicies (user_id, user_name, policy_id, policy_name, policy_amount, user_age, status,category_name,subcategory_name) VALUES (?, ?, ?, ?, ?, ?, 'Pending',?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, userName);
            ps.setInt(3, policyId);
            ps.setString(4, policyName);
            ps.setBigDecimal(5, amount);
            ps.setInt(6, age);
            ps.setString(7, categoryName);
            ps.setString(8, subCategoryName);
            ps.executeUpdate();
        }
    }
    public void updatePolicyStatus(int userId, int policyId, String status, LocalDate startDate, LocalDate endDate, String reason) throws SQLException {
        String query = "UPDATE userPolicies SET status = ?, start_date = ?, end_date = ?, reason = ? WHERE user_id = ? AND policy_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            if (startDate != null) {
                ps.setDate(2, Date.valueOf(startDate));
            } else {
                ps.setNull(2, Types.DATE);
            }
            if (endDate != null) {
                ps.setDate(3, Date.valueOf(endDate));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setString(4, reason);
            ps.setInt(5, userId);
            ps.setInt(6, policyId);
            ps.executeUpdate();
         System.out.println("updated successfully");
        }

}
    
    public List<Map<String, Object>> getRejectedPoliciesByUserId(int userId) throws SQLException {
        List<Map<String, Object>> rejectedPolicies = new ArrayList<>();
        String query = "SELECT up.policy_id, up.policy_name, c.category_name, s.subcategory_name, up.reason " +
                       "FROM userPolicies up " +
                       "JOIN policies p ON up.policy_id = p.policy_id " +
                       "JOIN categories c ON p.category_id = c.category_id " +
                       "JOIN subCategories s ON p.subcategory_id = s.subcategory_id " +
                       "WHERE up.user_id = ? AND up.status = 'Rejected'";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> policy = new HashMap<>();
                policy.put("policyId", rs.getInt("policy_id"));
                policy.put("policyName", rs.getString("policy_name"));
                policy.put("categoryName", rs.getString("category_name"));
                policy.put("subCategoryName", rs.getString("subcategory_name"));
                policy.put("reason", rs.getString("reason"));
                rejectedPolicies.add(policy);
            }
        }
        return rejectedPolicies;
    }
}


