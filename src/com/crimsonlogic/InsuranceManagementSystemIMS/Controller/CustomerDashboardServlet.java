package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.CategoryDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.PolicyDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.SubCategoryDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.UserDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.UserPolicyDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Category;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.Policy;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.SubCategory;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.User;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.UserPolicy;



/**
 * Servlet implementation class CustomerDashboardServlet
 */
@WebServlet("/CustomerDashboardServlet")
public class CustomerDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();
	private UserPolicyDao userPolicyDao = new UserPolicyDao();
	private PolicyDao policyDao = new PolicyDao();
	private CategoryDao categoryDao=new CategoryDao();
	private SubCategoryDao subCategoryDao=new SubCategoryDao();
//	private NotificationService notificationService = new NotificationService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User customer = (User) session.getAttribute("user");
		
		 try {
			List<Map<String, Object>> rejectedPolicies = userPolicyDao.getRejectedPoliciesByUserId(customer.getUserId());
			 session.setAttribute("rejectedPolicies", rejectedPolicies);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		 if (customer == null || !"customer".equals(customer.getRole())) {
		 response.sendRedirect("login.jsp?error=Unauthorized access");
		 return;
		 }
	
//		try {
//			// Retrieve user's policies
	List<Map<String, Object>> policy = policyDao.getAllPolicies();
		List<UserPolicy> policies = userPolicyDao.getUserPolicyByUserId(customer.getUserId());
		try {
			List<Category> categories = categoryDao.getAllCategories();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			List<SubCategory> subCategories = subCategoryDao.getAllSubCategories();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			// Retrieve notifications for the customer
//			List<Notification> notifications = notificationService.getNotificationsByUserId(customer.getUserId());
//
//			// Set attributes in session
//
	session.setAttribute("policies", policies);
		session.setAttribute("policy", policy);
	


//			session.setAttribute("notifications", notifications);
//
//			// Forward to customer dashboard
			request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			response.sendRedirect("login.jsp?error=An error occurred");
//		}
	}
}
