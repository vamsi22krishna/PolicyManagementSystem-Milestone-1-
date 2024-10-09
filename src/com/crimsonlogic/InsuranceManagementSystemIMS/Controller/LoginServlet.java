package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.UserDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.Util.DatabaseConnection;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.User;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
    private DatabaseConnection connection=null;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// String role = request.getParameter("role");
		String role = userDao.getRoleFromDatabase(username, password);
		System.out.println(role);

		try {
			// Retrieve user from the database
			User user = userDao.getUserByUsername(username);
			System.out.println(user);

			// Validate user and password
			if (role != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				// Redirect based on the user-selected role
				if ("admin".equals(role)) {
					response.sendRedirect("AdminDashboardServlet");
				} else if ("customer".equals(role)) {
					response.sendRedirect("CustomerDashboardServlet");
				} else {
					response.sendRedirect("login.jsp?error=Unknown role");
				}
			} else {
				request.setAttribute("errorMessage", "Username and password are incorrect.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			

	            // Store the connection in the session
	           HttpSession session = request.getSession();
	            session.setAttribute("dbConnection", connection.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp?error=An error occurred");
		}
	}

}