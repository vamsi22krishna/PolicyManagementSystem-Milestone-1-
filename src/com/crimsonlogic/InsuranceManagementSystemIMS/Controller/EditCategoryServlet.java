package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.CategoryDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Category;

@WebServlet("/EditCategoryServlet")
public class EditCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId").toString());
        String categoryName = request.getParameter("categoryName");

        // Assuming you have a CategoryDAO class for database operations
        CategoryDao categoryDAO = new CategoryDao();
       categoryDAO.updateCategory(categoryName,categoryId);

        
            response.sendRedirect("AdminDashboardServlet");
       
    }
}
