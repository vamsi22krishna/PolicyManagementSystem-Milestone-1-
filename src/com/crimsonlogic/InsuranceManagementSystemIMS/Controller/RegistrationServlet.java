package com.crimsonlogic.InsuranceManagementSystemIMS.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.InsuranceManagementSystemIMS.Dao.UserDao;
import com.crimsonlogic.InsuranceManagementSystemIMS.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UserDao userDao;

	    public void init() {
	        userDao = new UserDao();
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String firstname=request.getParameter("firstname");
	        String lastname=request.getParameter("lastname");
	        String email=request.getParameter("email");
	        Long phonenumber=Long.parseLong(request.getParameter("phonenumber").toString());
	        
//	        String role = request.getParameter("role");

	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(password);
	        user.setEmail(email);
	        user.setFirstName(firstname);
	        user.setLastName(lastname);
	        user.setPhoneNumber(phonenumber);
	        
//	        user.setRole("customer");

	        userDao.addUser(user);
	        System.out.println(user);
        response.sendRedirect("index.jsp");
	    }
	}
