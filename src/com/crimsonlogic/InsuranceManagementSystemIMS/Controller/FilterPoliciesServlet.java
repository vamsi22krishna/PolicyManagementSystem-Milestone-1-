package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.PolicyDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Policy;

/**
 * Servlet implementation class FilterPoliciesServlet
 */
@WebServlet("/FilterPoliciesServlet")
public class FilterPoliciesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   private PolicyDao policyDao=new PolicyDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        String subCategoryName = request.getParameter("subCategoryName");

        // Fetch filtered policies based on the provided category and subcategory names
      //  List<Policy> filteredPolicies = policyDao.getFilteredPolicies(categoryName, subCategoryName);

        // Set the filtered policies in the session scope
        HttpSession session = request.getSession();
       // session.setAttribute("policy", filteredPolicies);

        // Forward to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher(".jsp");
        dispatcher.forward(request, response);
    }
}
