package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.SubCategoryDao;

/**
 * Servlet implementation class AddSubCategoryServlet
 */
@WebServlet("/AddSubCategoryServlet")
public class AddSubCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String subCategoryName = request.getParameter("newSubCategory");

        try {
            SubCategoryDao subCategoryDao = new SubCategoryDao();
            subCategoryDao.addSubCategory(categoryId, subCategoryName);

//            response.sendRedirect("adminDashboard.jsp");
            response.sendRedirect("AdminDashboardServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp?error=An error occurred while adding the subcategory");
        }
    }
}
