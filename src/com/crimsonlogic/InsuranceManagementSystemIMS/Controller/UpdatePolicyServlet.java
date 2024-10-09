package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/UpdatePolicyServlet")
public class UpdatePolicyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int policyId = Integer.parseInt(request.getParameter("policyId"));
        String policyName = request.getParameter("policyName");
        String categoryName = request.getParameter("category");
        String subCategoryName = request.getParameter("subCategory");
        double amount = Double.parseDouble(request.getParameter("amount"));

        Connection connection = null;
        PreparedStatement psCategory = null;
        PreparedStatement psSubCategory = null;
        PreparedStatement psPolicy = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Update or insert category
            String categoryQuery = "INSERT INTO categories (category_name) VALUES (?) ON CONFLICT (category_name) DO NOTHING RETURNING category_id";
            psCategory = connection.prepareStatement(categoryQuery);
            psCategory.setString(1, categoryName);
            ResultSet rsCategory = psCategory.executeQuery();
            int categoryId = 0;
            if (rsCategory.next()) {
                categoryId = rsCategory.getInt("category_id");
            } else {
                // Fetch category ID if it already exists
                String fetchCategoryIdQuery = "SELECT category_id FROM categories WHERE category_name = ?";
                psCategory = connection.prepareStatement(fetchCategoryIdQuery);
                psCategory.setString(1, categoryName);
                rsCategory = psCategory.executeQuery();
                if (rsCategory.next()) {
                    categoryId = rsCategory.getInt("category_id");
                }
            }

            // Update or insert subcategory
            String subCategoryQuery = "INSERT INTO subCategories (subcategory_name, category_id) VALUES (?, ?) ON CONFLICT (subcategory_name) DO NOTHING RETURNING subcategory_id";
            psSubCategory = connection.prepareStatement(subCategoryQuery);
            psSubCategory.setString(1, subCategoryName);
            psSubCategory.setInt(2, categoryId);
            ResultSet rsSubCategory = psSubCategory.executeQuery();
            int subCategoryId = 0;
            if (rsSubCategory.next()) {
                subCategoryId = rsSubCategory.getInt("subcategory_id");
            } else {
                // Fetch subcategory ID if it already exists
                String fetchSubCategoryIdQuery = "SELECT subcategory_id FROM subCategories WHERE subcategory_name = ?";
                psSubCategory = connection.prepareStatement(fetchSubCategoryIdQuery);
                psSubCategory.setString(1, subCategoryName);
                rsSubCategory = psSubCategory.executeQuery();
                if (rsSubCategory.next()) {
                    subCategoryId = rsSubCategory.getInt("subcategory_id");
                }
            }

            // Update policy
            String policyQuery = "UPDATE policies SET policy_name = ?, subcategory_id = ?, category_id = ?, amount = ? WHERE policy_id = ?";
            psPolicy = connection.prepareStatement(policyQuery);
            psPolicy.setString(1, policyName);
            psPolicy.setInt(2, subCategoryId);
            psPolicy.setInt(3, categoryId);
            psPolicy.setDouble(4, amount);
            psPolicy.setInt(5, policyId);
            psPolicy.executeUpdate();

            connection.commit(); // Commit transaction

            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } 
    }
}

