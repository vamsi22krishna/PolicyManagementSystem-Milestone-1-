package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.CategoryDao;

/**
 * Servlet implementation class AddCategoryServlet
 */
@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("newCategory");

        try {
            CategoryDao categoryDao = new CategoryDao();
            categoryDao.addCategory(categoryName);

//            response.sendRedirect("adminDashboard.jsp");
            response.sendRedirect("AdminDashboardServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp?error=An error occurred while adding the category");
        }
    }
}
