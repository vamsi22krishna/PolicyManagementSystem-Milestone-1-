package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;


import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Category;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Policy;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.SubCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/EditPolicyServlet")
public class EditPolicyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int policyId = Integer.parseInt(request.getParameter("policyId"));

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT p.policy_id, p.policy_name, p.amount, c.category_name, s.subcategory_name " +
                           "FROM policies p " +
                           "JOIN categories c ON p.category_id = c.category_id " +
                           "JOIN subCategories s ON p.subcategory_id = s.subcategory_id " +
                           "WHERE p.policy_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, policyId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Policy policy = new Policy();
                Category category=new Category();
                SubCategory subCategory=new SubCategory();
                policy.setPolicyId(rs.getInt("policy_id"));
                policy.setPolicyName(rs.getString("policy_name"));
                policy.setAmount(rs.getBigDecimal("amount"));
                category.setCategoryName(rs.getString("category_name"));
                subCategory.setSubCategoryName(rs.getString("subcategory_name"));

                request.setAttribute("policy", policy);
                request.setAttribute("category", category);
                request.setAttribute("subCategory", subCategory);
                RequestDispatcher dispatcher = request.getRequestDispatcher("EditPolicy.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } 
    }
}
