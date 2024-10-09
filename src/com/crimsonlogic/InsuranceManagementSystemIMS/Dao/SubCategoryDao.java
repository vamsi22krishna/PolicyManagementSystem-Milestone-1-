package com.crimsonlogic.InsuranceManagementSystemIMS.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.SubCategory;

public class SubCategoryDao {
    private Connection connection;

    public SubCategoryDao() {
        connection = DatabaseConnection.getConnection();
    }
    
    
    
    public void addSubCategory(int categoryId, String subCategoryName) throws SQLException {
        String query = "INSERT INTO subCategories (category_id, subcategory_name) VALUES (?, ?)";
         PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, subCategoryName);
            ps.executeUpdate();
        System.out.println("sub inserted");
    }

    public List<SubCategory> getAllSubCategories() throws SQLException {
        List<SubCategory> subCategories = new ArrayList<>();
        String query = "SELECT * FROM subCategories";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SubCategory subCategory = new SubCategory();
                subCategory.setSubCategoryId(rs.getInt("subcategory_id"));
                subCategory.setSubCategoryName(rs.getString("subcategory_name"));
                subCategory.setCategoryId(rs.getInt("category_id"));
                subCategories.add(subCategory);
            }
        }
        return subCategories;
    }
}
//
//    public void addSubCategory(SubCategory subCategory) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO subCategories (subcategory_name, category_id) VALUES (?, ?)");
//            ps.setString(1, subCategory.getSubCategoryName());
//            ps.setInt(2, subCategory.getCategoryId());
//           // ps.setString(3, subCategory.getDescription());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateSubCategory(SubCategory subCategory) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("UPDATE subCategories SET subcategory_name=?, category_id=?, description=?, updated_at=CURRENT_TIMESTAMP WHERE subcategory_id=?");
//            ps.setString(1, subCategory.getSubCategoryName());
//            ps.setInt(2, subCategory.getCategoryId());
//            ps.setString(3, subCategory.getDescription());
//            ps.setInt(4, subCategory.getSubCategoryId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteSubCategory(int subCategoryId) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("DELETE FROM subCategories WHERE subcategory_id=?");
//            ps.setInt(1, subCategoryId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public SubCategory getSubCategoryById(int subCategoryId) {
//        SubCategory subCategory = null;
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM subCategories WHERE subcategory_id=?");
//            ps.setInt(1, subCategoryId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                subCategory = new SubCategory();
//                subCategory.setSubCategoryId(rs.getInt("subcategory_id"));
//                subCategory.setSubCategoryName(rs.getString("subcategory_name"));
//                subCategory.setCategoryId(rs.getInt("category_id"));
//                subCategory.setDescription(rs.getString("description"));
//                subCategory.setCreatedAt(rs.getTimestamp("created_at"));
//                subCategory.setUpdatedAt(rs.getTimestamp("updated_at"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return subCategory;
//    }
//
//    public List<SubCategory> getAllSubCategories() {
//        List<SubCategory> subCategories = new ArrayList<>();
//        try {
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM subCategories");
//            while (rs.next()) {
//                SubCategory subCategory = new SubCategory();
//                subCategory.setSubCategoryId(rs.getInt("subcategory_id"));
//                subCategory.setSubCategoryName(rs.getString("subcategory_name"));
//                subCategory.setCategoryId(rs.getInt("category_id"));
//                subCategory.setCreatedAt(rs.getTimestamp("created_at"));
//                subCategory.setUpdatedAt(rs.getTimestamp("updated_at"));
//                subCategories.add(subCategory);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return subCategories;
//    }
//}
