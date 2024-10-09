<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Policy</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="styles.css">
    <style>
      .form-container {
    max-width: 600px; /* Adjust the width as needed */
    margin: 0 auto; /* Center the container horizontally */
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-container h1 {
    color: #3880ce;
    font-size: 1.75em;
    margin-bottom: 20px;
}

.form-container .form-group {
    margin-bottom: 15px;
}

.form-container .form-group label {
    font-weight: bold;
    margin-bottom: 5px;
    color: #333;
}

.form-container .form-control {
    width: 100%;
    padding: 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

.form-container .form-control:focus {
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

.form-container .btn-primary {
    background-color: #3880ce;
    border-color: #3880ce;
    color: #fff;
    font-size: 16px;
    padding: 10px;
    border-radius: 4px;
}

.form-container .btn-primary:hover {
    background-color: #0056b3;
    border-color: #0056b3;
}

    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h1 class="text-center">Edit Policy</h1>
            <form action="UpdatePolicyServlet" method="post">
                <input type="hidden" name="policyId" value="${policy.policyId}" />
                <div class="form-group">
                    <label for="policyName">Policy Name:</label>
                    <input type="text" class="form-control" id="policyName" name="policyName" value="${policy.policyName}" required>
                </div>
                <div class="form-group">
                    <label for="category">Category:</label>
                   <!--  <input type="text" class="form-control" id="category" name="category" value="${category.categoryName}" required> -->
                    <select
							class="form-control" id="category" name="category" required>
							<c:forEach var="category" items="${sessionScope.categories}">
								<option value="${category.categoryName}">${category.categoryName}</option>
							</c:forEach>
						</select>
                </div>
                <div class="form-group">
                    <label for="subCategory">Sub Category:</label>
                    <!--  <input type="text" class="form-control" id="subCategory" name="subCategory" value="${subCategory.subCategoryName}" required>-->
                          <select
							class="form-control" id="subCategory" name="subCategory" required>
							<c:forEach var="subCategory"
								items="${sessionScope.subCategories}">
								<option value="${subCategory.subCategoryName}">${subCategory.subCategoryName}</option>
							</c:forEach>
						</select>
                </div>
                <div class="form-group">
                    <label for="amount">Amount:</label>
                    <input type="number" step="0.01" class="form-control" id="amount" name="amount" value="${policy.amount}" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Update Policy</button>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
