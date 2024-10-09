package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;
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
 * Servlet implementation class AdminDashboardServlet
 */
@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private PolicyDao policyDao = new PolicyDao();
	private UserDao userDao = new UserDao();
    private UserPolicyDao userPolicyDao=new UserPolicyDao();
    private CategoryDao categoryDao=new CategoryDao();
    private SubCategoryDao subCategoryDao=new SubCategoryDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Retrieve all users (customers)
			// List<User> customers = userService.getAllUsers();
			List<Map<String, Object>> policy = policyDao.getAllPolicies();
			List<UserPolicy> customer = userPolicyDao.getAllUserPolicies();
			
			System.out.println("Number of policy retrieved: " + policy.size());
			System.out.println("Number of policy retrieved: " + customer.size());
			 CategoryDao categoryDao = new CategoryDao();
	         SubCategoryDao subCategoryDao = new SubCategoryDao();

	            List<Category> categories = categoryDao.getAllCategories();
	            List<SubCategory> subCategories = subCategoryDao.getAllSubCategories();

	           
	           
			// Set users in session
			HttpSession session = request.getSession();
			session.setAttribute("policy", policy);
		
			session.setAttribute("customer", customer);
			
			   session.setAttribute("categories", categories);
	            session.setAttribute("subCategories", subCategories);
			request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp?error=An error occurred");
		}
		

	}
}

//// Retrieve policies for the user
// List<Policy> policies = policyService.getPoliciesByUserId(user.getUserId());
// List<Policy> expiredPolicies = new ArrayList<>();
// List<Policy> activePolicies = new ArrayList<>();
//
// for (Policy policy : policies) {
// if (policy.isExpired()) {
// expiredPolicies.add(policy);
// } else {
// activePolicies.add(policy);
// }
// }