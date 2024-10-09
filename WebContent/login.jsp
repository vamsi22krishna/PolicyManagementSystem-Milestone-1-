<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<link rel="stylesheet" href="login.css">
</head>
<body>
	<div class="container login-container">
		<div class="login-header text-center">
			<h2>Insurance Management System Login</h2>
		</div>

		<form action="LoginServlet" method="post" onsubmit="return validateForm();" novalidate>
			<div class="form-group">
				<label for="username">Username:</label>
				<input type="text" id="username" name="username" class="form-control">
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" class="form-control">
			</div>
			<div class="form-group">
				<input type="submit" value="Login" class="btn btn-primary btn-block">
			</div>
			<div id="form-errors" class="error-message" style="display: none;"></div>
		</form>

		<!-- Display server-side error messages if any -->
		<c:if test="${not empty errorMessage}">
			<div class="error-message text-center">
				<p>
					<c:out value="${errorMessage}" />
				</p>
			</div>
		</c:if>

		<div class="text-center">
			<p>
				Need an account? <a href="register.jsp">Register here</a>.
			</p>
		</div>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function validateForm() {
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			var formErrors = document.getElementById("form-errors");

			formErrors.style.display = "none";
			formErrors.innerHTML = "";

			if (username === "") {
				formErrors.style.display = "block";
				formErrors.innerHTML += "<p>Username is required.</p>";
				return false;
			}

			if (password === "") {
				formErrors.style.display = "block";
				formErrors.innerHTML += "<p>Password is required.</p>";
				return false;
			}

			return true;
		}
	</script>
</body>
</html>