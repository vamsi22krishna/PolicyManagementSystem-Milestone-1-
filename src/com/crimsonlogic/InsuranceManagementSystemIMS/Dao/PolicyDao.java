package com.crimsonlogic.InsuranceManagementSystemIMS.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Policy;

public class PolicyDao {
    private Connection connection;

    public PolicyDao() {
        connection = DatabaseConnection.getConnection();
    }

 

    public void deletePolicy(int policyId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM policies WHERE policy_id = ?");
            ps.setInt(1, policyId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Policy getPolicyById(int policyId) {
        Policy policy = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM policies WHERE policy_id = ?");
            ps.setInt(1, policyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                policy = new Policy();
                policy.setPolicyId(rs.getInt("policy_id"));
                policy.setPolicyName(rs.getString("policy_name"));
                policy.setSubCategoryId(rs.getInt("subcategory_id"));
                policy.setCategoryId(rs.getInt("category_id"));
                policy.setAmount(rs.getBigDecimal(("amount")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policy;
    }

    public List<Map<String, Object>> getAllPolicies() {
        List<Map<String, Object>> policies = new ArrayList<>();
        try {
            String query = "SELECT p.policy_id, p.policy_name, c.category_name, s.subcategory_name, p.amount, p.min_age, p.max_age " +
                           "FROM policies p " +
                           "LEFT JOIN categories c ON p.category_id = c.category_id " +
                           "LEFT JOIN subCategories s ON p.subcategory_id = s.subcategory_id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Map<String, Object> policy = new HashMap<>();
                policy.put("policyId", rs.getInt("policy_id"));
                policy.put("policyName", rs.getString("policy_name"));
                policy.put("categoryName", rs.getString("category_name"));
                policy.put("subCategoryName", rs.getString("subcategory_name"));
                policy.put("amount", rs.getBigDecimal("amount"));
                policy.put("minAge", rs.getInt("min_age"));
                policy.put("maxAge", rs.getInt("max_age"));
                policies.add(policy);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }
    
   


}
