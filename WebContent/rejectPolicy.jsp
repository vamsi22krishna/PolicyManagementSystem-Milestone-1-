<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reject Policy</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .custom-container {
            background-color: #f8f9fa;
            color: #333;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .custom-container h2 {
            margin-bottom: 20px;
        }
        .form-control[readonly] {
            background-color: #e9ecef;
            border: 1px solid #ced4da;
        }
        .btn-custom {
            background-color: #007bff;
            color: #fff;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container mt-5 custom-container">
        <h2 class="text-center mb-4">Reject Policy</h2>
        <form action="UpdateStatusServlet" method="post">
            <input type="hidden" name="userId" value="${param.userId}" />
            <input type="hidden" name="policyId" value="${param.policyId}" />
            <input type="hidden" name="policyName" value="${param.policyName}" />
            <input type="hidden" name="status" value="Rejected" />

            <div class="form-group">
                <label for="userId">User ID:</label>
                <input type="text" id="userId" name="userId" class="form-control" value="${param.userId}" readonly>
            </div>

            <div class="form-group">
                <label for="policyId">Policy ID:</label>
                <input type="text" id="policyId" name="policyId" class="form-control" value="${param.policyId}" readonly>
            </div>

            <div class="form-group">
                <label for="policyName">Policy Name:</label>
                <input type="text" id="policyName" name="policyName" class="form-control" value="${param.policyName}" readonly>
            </div>

            <div class="form-group">
                <label for="reason">Reason for Rejection:</label>
                <textarea id="reason" name="reason" class="form-control" required></textarea>
            </div>

            <button type="submit" class="btn btn-custom btn-block">Submit</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
