<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buy Policy</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
 .custom-container {
    max-width: 600px; /* Adjust the width as needed */
    margin: 0 auto; /* Center the container horizontally */
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.custom-container h2 {
    color: #3880ce;
    font-size: 1.75em;
    margin-bottom: 20px;
}

.custom-container .form-group {
    margin-bottom: 15px;
}

.custom-container .form-group label {
    font-weight: bold;
    margin-bottom: 5px;
    color: #333;
}

.custom-container .form-control {
    width: 100%;
    padding: 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

.custom-container .form-control:focus {
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

.custom-container .btn-custom {
    background-color: #3880ce;
    border-color: #3880ce;
    color: #fff;
    font-size: 16px;
    padding: 10px;
    border-radius: 4px;
}

.custom-container .btn-custom:hover {
    background-color: #0056b3;
    border-color: #0056b3;
}

    </style>
</head>
<body>
    <div class="container mt-5 custom-container">
        <h2 class="text-center mb-4">Buy Policy</h2>
        <form action="SubmitPolicyRequestServlet" method="post">
            <input type="hidden" name="userId" value="${sessionScope.user.userId}" />
            <input type="hidden" name="userName" value="${sessionScope.user.username}" />
            <input type="hidden" name="policyId" value="${param.policyId}" />
            <input type="hidden" name="policyName" value="${param.policyName}" />
            
            <input type="hidden" name="maxage" value="${param.minAge}" />
            <input type="hidden" name="minage" value="${param.maxAge}" /> 
            
            <input type="hidden" name="amount" value="${param.amount}" />
             <input type="hidden" name="categoryName" value="${param.categoryName}" />
                <input type="hidden" name="subCategoryName" value="${param.subCategoryName}" />
             
            

     <div class="container">
            <div class="form-group">
                <label for="policyId">Policy ID:</label>
                <input type="text" id="policyId" name="policyId" class="form-control" value="${param.policyId}" readonly>
            </div>

            <div class="form-group">
                <label for="policyName">Policy Name:</label>
                <input type="text" id="policyName" name="policyName" class="form-control" value="${param.policyName}" readonly>
            </div>
           
            

            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="text" id="amount" name="amount" class="form-control" value="${param.amount}" readonly>
            </div>
            

            <div class="form-group">
                <label for="age">your Age:</label>
                <input type="number" id="age" name="age" class="form-control" min="0" max="100" required>
            </div>

            <button type="submit" class="btn btn-custom btn-block">Submit</button>
        </form>
 </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
