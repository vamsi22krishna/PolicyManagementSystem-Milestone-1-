package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.UserPolicyDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.UserPolicy;

/**
 * Servlet implementation class SubmitPolicyRequestServlet
 */
@WebServlet("/SubmitPolicyRequestServlet")
public class SubmitPolicyRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	HttpSession session = request.getSession();
    	
    	
    	int userId = Integer.parseInt(request.getParameter("userId").toString());
        String userName = request.getParameter("userName");
        int policyId = Integer.parseInt(request.getParameter("policyId").toString());
        String policyName = request.getParameter("policyName");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        int age = Integer.parseInt(request.getParameter("age").toString());
        String categoryName=request.getParameter("categoryName");
        String subCategoryName=request.getParameter("subCategoryName");

        try {
            UserPolicyDao userPolicyDao = new UserPolicyDao();
            userPolicyDao.addUserPolicy(userId, userName, policyId, policyName, amount, age,categoryName,subCategoryName);

            // Redirect to a confirmation page or back to the dashboard
            response.sendRedirect("customerDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("buyPolicy.jsp?error=An error occurred while submitting the policy request");
        }
    }
}
