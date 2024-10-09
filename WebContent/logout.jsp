<%@ page import="java.sql.Connection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<html>
<%
    // Get the current session
        HttpSession sess = request.getSession(false);
    if (sess != null) {
        // Close the database connection if it exists
        Connection dbConnection = (Connection) sess.getAttribute("dbConnection");
        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Invalidate the session
        session.invalidate();
    }

    // Redirect to the login page or home page
    response.sendRedirect("index.jsp");
%>
</html>
