package com.crimsonlogic.InsuranceManagementSystemIMS.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.PolicyRequest;

public class PolicyRequestDao {
    private Connection connection;

    public PolicyRequestDao() {
        connection = DatabaseConnection.getConnection();
    }

    public void addPolicyRequest(PolicyRequest policyRequest) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PolicyRequests (user_id, policy_id, status) VALUES (?, ?, ?)");
            ps.setInt(1, policyRequest.getUserId());
            ps.setInt(2, policyRequest.getPolicyId());
            ps.setString(3, policyRequest.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePolicyRequest(PolicyRequest policyRequest) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE PolicyRequests SET user_id=?, policy_id=?, status=?, request_date=CURRENT_TIMESTAMP WHERE request_id=?");
            ps.setInt(1, policyRequest.getUserId());
            ps.setInt(2, policyRequest.getPolicyId());
            ps.setString(3, policyRequest.getStatus());
            ps.setInt(4, policyRequest.getRequestId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePolicyRequest(int requestId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PolicyRequests WHERE request_id=?");
            ps.setInt(1, requestId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PolicyRequest getPolicyRequestById(int requestId) {
        PolicyRequest policyRequest = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PolicyRequests WHERE request_id=?");
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                policyRequest = new PolicyRequest();
                policyRequest.setRequestId(rs.getInt("request_id"));
                policyRequest.setUserId(rs.getInt("user_id"));
                policyRequest.setPolicyId(rs.getInt("policy_id"));
                policyRequest.setStatus(rs.getString("status"));
                policyRequest.setRequestDate(rs.getTimestamp("request_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policyRequest;
    }

    public List<PolicyRequest> getAllPolicyRequests() {
        List<PolicyRequest> policyRequests = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PolicyRequests");
            while (rs.next()) {
                PolicyRequest policyRequest = new PolicyRequest();
                policyRequest.setRequestId(rs.getInt("request_id"));
                policyRequest.setUserId(rs.getInt("user_id"));
                policyRequest.setPolicyId(rs.getInt("policy_id"));
                policyRequest.setStatus(rs.getString("status"));
                policyRequest.setRequestDate(rs.getTimestamp("request_date"));
                policyRequests.add(policyRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policyRequests;
    }
}
