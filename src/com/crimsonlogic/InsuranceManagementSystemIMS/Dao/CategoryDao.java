package com.crimsonlogic.InsuranceManagementSystemIMS.Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Category;

public class CategoryDao {
    private Connection connection;

    public CategoryDao() {
        connection = DatabaseConnection.getConnection();
    }

    public void addCategory(String categoryName) {
        String query = "INSERT INTO categories (category_name) VALUES (?)";
        PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, categoryName);
            ps.executeUpdate();
            System.out.println("cat inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
            
       
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }
        }
        return categories;
    }
    
    
//    public void addCategory(Sting categoryname) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO categories (category_name) VALUES (?)");
//            ps.setString(1, category.getCategoryName());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
    public void updateCategory(String categoryName, int categoryId) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE categories SET category_name=?, updated_at=CURRENT_TIMESTAMP WHERE category_id=?");
            ps.setString(1, categoryName);
            ps.setInt(2, categoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
  public void deleteCategory(int categoryId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM categories WHERE category_id=?");
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public boolean updateCategory(String categoryId, String categoryName) {
//        String query = "UPDATE categories SET category_name = ? WHERE category_id = ?";
//        try {
//        	PreparedStatement stmt = connection.prepareStatement(query);       
//            stmt.setString(1, categoryName);
//            stmt.setString(2, categoryId);
//            int rowsAffected = stmt.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public void deleteCategory(String categoryId) {
//        String query = "DELETE FROM categories WHERE category_id = ?";
//        try { PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setString(1, categoryId);
//           stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//           
//        }
//    }
//
//    public Category getCategoryById(int categoryId) {
//        Category category = null;
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories WHERE category_id=?");
//            ps.setInt(1, categoryId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                category = new Category();
//                category.setCategoryId(rs.getInt("category_id"));
//                category.setCategoryName(rs.getString("category_name"));
//                category.setDescription(rs.getString("description"));
//                category.setCreatedAt(rs.getTimestamp("created_at"));
//                category.setUpdatedAt(rs.getTimestamp("updated_at"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return category;
//    }
//
//    public List<Category> getAllCategories() {
//        List<Category> categories = new ArrayList<>();
//        try {
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM categories");
//            while (rs.next()) {
//                Category category = new Category();
//                category.setCategoryId(rs.getInt("category_id"));
//                category.setCategoryName(rs.getString("category_name"));
//                category.setCreatedAt(rs.getTimestamp("created_at"));
//                category.setUpdatedAt(rs.getTimestamp("updated_at"));
//                categories.add(category);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return categories;
//    }
}
