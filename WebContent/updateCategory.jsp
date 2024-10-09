<%@ page import="java.sql.*, javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String categoryId = request.getParameter("categoryId");
    String categoryName = request.getParameter("categoryName");

    try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/yourdbname", "yourusername", "yourpassword");
        PreparedStatement ps = conn.prepareStatement("UPDATE categories SET category_name = ? WHERE category_id = ?");
        ps.setString(1, categoryName);
        ps.setInt(2, Integer.parseInt(categoryId));
        ps.executeUpdate();
        ps.close();
      
    } catch (Exception e) {
        e.printStackTrace();
    }

    response.sendRedirect("categories.jsp?success=Category updated successfully");
%>
