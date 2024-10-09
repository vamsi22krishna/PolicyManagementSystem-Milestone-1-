package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.UserPolicyDao;

/**
 * Servlet implementation class UpdateStatusServlet
 */
@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        int policyId = Integer.parseInt(request.getParameter("policyId"));
//        String status = request.getParameter("status");
//
//        try {
//            UserPolicyDao userPolicyDao = new UserPolicyDao();
//            userPolicyDao.updatePolicyStatus(userId, policyId, status);
//
//            // Redirect back to the admin dashboard
//            response.sendRedirect("AdminDashboardServlet");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("adminDashboard.jsp?error=An error occurred while updating the policy status");
//        }
//    }
    
    


        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int policyId = Integer.parseInt(request.getParameter("policyId"));
            String status = request.getParameter("status");
            String reason = request.getParameter("reason");

            try {
                UserPolicyDao userPolicyDao = new UserPolicyDao();
                if ("Approved".equals(status)) {
                    LocalDate startDate = LocalDate.now();
                    LocalDate endDate = startDate.plusYears(1);
                    userPolicyDao.updatePolicyStatus(userId, policyId, status, startDate, endDate, null);
                } else if ("Rejected".equals(status)) {
                    userPolicyDao.updatePolicyStatus(userId, policyId, status, null, null, reason);
                }

                // Redirect back to the admin dashboard
                response.sendRedirect("AdminDashboardServlet");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("adminDashboard.jsp?error=An error occurred while updating the policy status");
            }
        }
    }

