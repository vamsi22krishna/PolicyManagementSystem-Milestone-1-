<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="header.jsp"%>
<%
	if (request.getSession().getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.dashboard-container {
	max-width: 1200px;
	margin: 50px auto;
	background: #fff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.text-center {
	text-align: center;
}

.nav-tabs .nav-link {
	color: #3880ce;
	font-weight: bold;
}

.nav-tabs .nav-link.active {
	color: #fff;
	background-color: #3880ce;
	border-radius: 5px 5px 0 0;
}

.section-title {
	font-size: 1.75em;
	margin-bottom: 20px;
	color: #3880ce;
	border-bottom: 2px solid #3880ce;
	padding-bottom: 10px;
}

.card {
	margin-bottom: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-body p {
	margin: 10px 0;
}

.table th, .table td {
	vertical-align: middle;
	text-align: center;
}

.table thead th {
	background-color: #3880ce;
	color: #fff;
}

.table tbody tr:nth-child(odd) {
	background-color: #f9f9f9;
}

.table tbody tr:hover {
	background-color: #f1f1f1;
}

.btn-success, .btn-danger, .btn-primary {
	transition: background-color 0.3s, transform 0.3s;
}

.btn-success {
	background-color: #28a745;
	border-color: #28a745;
}

.btn-success:hover {
	background-color: #218838;
	transform: scale(1.05);
}

.btn-danger {
	background-color: #dc3545;
	border-color: #dc3545;
}

.btn-danger:hover {
	background-color: #c82333;
	transform: scale(1.05);
}

.btn-primary {
	background-color: #3880ce;
	border-color: #3880ce;
}

.btn-primary:hover {
	background-color: #0056b3;
	transform: scale(1.05);
}

.alert {
	margin-top: 20px;
}
.search-container {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    margin-top: 20px;
    margin-right: 20px; /* Adjust this value to control the distance from the right edge */
}

.search-bar {
    width: 300px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
}

.search-bar::placeholder {
    color: #aaa;
}


</style>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Dashboard</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="container dashboard-container">
		<h2 class="text-center">Customer Dashboard</h2>

		<!-- Display success or error messages -->
		<c:if test="${not empty param.success}">
			<div class="alert alert-success">
				<c:out value="${param.success}" />
			</div>
		</c:if>
		<c:if test="${not empty param.error}">
			<div class="alert alert-danger">
				<c:out value="${param.error}" />
			</div>
		</c:if>

		<!-- Bootstrap Tabs -->
		<ul class="nav nav-tabs" id="dashboardTabs" role="tablist">
			<li class="nav-item"><a class="nav-link active"
				id="user-details-tab" data-toggle="tab" href="#user-details"
				role="tab" aria-controls="user-details" aria-selected="true">User
					Details</a></li>
			<li class="nav-item"><a class="nav-link" id="policies-tab"
				data-toggle="tab" href="#policies" role="tab"
				aria-controls="policies" aria-selected="false">Your Policies</a></li>
			<!-- <li class="nav-item"><a class="nav-link"
				id="renewable-policies-tab" data-toggle="tab"
				href="#renewable-policies" role="tab"
				aria-controls="renewable-policies" aria-selected="false">Renewable
					Policies</a></li>
			<li class="nav-item"><a class="nav-link" id="notifications-tab"
				data-toggle="tab" href="#notifications" role="tab"
				aria-controls="notifications" aria-selected="false">Notifications</a>
			</li> -->
			<li class="nav-item"><a class="nav-link" id="all-policies-tab"
				data-toggle="tab" href="#all-policies" role="tab"
				aria-controls="all-policies" aria-selected="false">Request Policies</a></li>
			<li class="nav-item"><a class="nav-link" id="all-rejected-tab"
				data-toggle="tab" href="#all-rejected" role="tab"
				aria-controls="all-rejected" aria-selected="false">Rejected
					Policies</a></li>
				<!-- 	<li class="nav-item"><a class="nav-link" id="viewcategories-tab"
				data-toggle="tab" href="#all-viewcategories" role="tab"
				aria-controls="all-viewcategories" aria-selected="false">view Categories
					Policies</a></li> -->
		</ul>
		<div class="tab-content" id="dashboardTabsContent">
			<!-- User Details Tab -->
			<div class="tab-pane fade show active" id="user-details"
				role="tabpanel" aria-labelledby="user-details-tab">
				<c:if test="${not empty sessionScope.user}">
					<div class="section-title">User Details</div>
					<div class="card">
						<div class="card-body">
							<p>
								<strong>User ID:</strong>
								<c:out value="${sessionScope.user.userId}" />
							</p>
							<p>
								<strong>Username:</strong>
								<c:out value="${sessionScope.user.username}" />
							</p>
							<p>
								<strong>First Name:</strong>
								<c:out value="${sessionScope.user.firstName}" />
							</p>
							<p>
								<strong>Last Name:</strong>
								<c:out value="${sessionScope.user.lastName}" />
							</p>
							<p>
								<strong>phoneNumber:</strong>
								<c:out value="${sessionScope.user.phoneNumber}" />
							</p>
							<p>
								<strong>Role:</strong>
								<c:out value="${sessionScope.user.role}" />
							</p>
							<p>
								<strong>Email:</strong>
								<c:out value="${sessionScope.user.email}" />
							</p>

						</div>
					</div>
				</c:if>
			</div>

			<!-- Policies Tab -->
			<div class="tab-pane fade" id="policies" role="tabpanel"
				aria-labelledby="policies-tab">
				<c:if test="${not empty sessionScope.policies}">
				<h4>Your Policies</h4>
				<table class="table table-striped">
						<thead>
							<tr>
								<th>PolicyId</th>
								<th>policyName</th>
								<th>CategoryName</th>
								<th>SubCategoryName</th>
								<th>StartDate</th>
								<th>EndDate</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="policy" items="${sessionScope.policies}">
								<tr>
									<td><c:out value="${policy.policyId}" /></td>
									<td><c:out value="${policy.policyName}" /></td>
									<td><c:out value="${policy.categoryName}" /></td>
									<td><c:out value="${policy.subCategoryName}" /></td>
									<td><c:out value="${policy.startDate}" /></td>
									<td><c:out value="${policy.endDate}" /></td>
									<td><c:out value="${policy.status}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty sessionScope.policies}">
					<p>No policies found.</p>
				</c:if>
			</div>



			
			<!-- list all policies -->
			<div class="tab-pane" id="all-policies" role="tabpanel"
				aria-labelledby="all-policies-tab">
				<c:if test="${not empty sessionScope.policy}">
					<div class="section-title">Request Policies</div>
					<div class="search-container">
<input type="text" id="searchInput" class="search-bar" placeholder="Search by keyword">
</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Policy ID</th>
								<th>PolicyName</th>
								<th>SubCategoryName</th>
								<th>CategoryName</th>
								<th>Min Age</th>
								<th>Max Age</th>
								<th>Policy Amount</th>
								<th>Request</th>
							</tr>
						</thead>
						<tbody id="searchbody">
							<c:forEach var="policy" items="${sessionScope.policy}">
								<tr>
									<td><c:out value="${policy.policyId}" /></td>
									<td><c:out value="${policy.policyName}" /></td>
									<td><c:out value="${policy.subCategoryName}" /></td>
									<td><c:out value="${policy.categoryName}" /></td>
									<td><c:out value="${policy.minAge}" /></td>
									<td><c:out value="${policy.maxAge}" /></td>
									<td><c:out value="${policy.amount}" /></td>
									<td>
										<form action="buyPolicy.jsp" method="get">
											<input type="hidden" name="policyId"
												value="${policy.policyId}" /> <input type="hidden"
												name="policyName" value="${policy.policyName}" /> <input
												type="hidden" name="amount" value="${policy.amount}" /> <input
												type="hidden" name="categoryName"
												value="${policy.categoryName}" /> <input type="hidden"
												name="subCategoryName" value="${policy.subCategoryName}" />
											<button type="submit" class="btn btn-primary">Request</button>
										</form>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</c:if>
			</div>
				
			<!-- list all rejected policies -->
			<div class="tab-pane" id="all-rejected" role="tabpanel"
				aria-labelledby="all-rejected-tab">
				<c:if test="${not empty sessionScope.policy}">
					<div class="section-title">All rejected policies</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Policy ID</th>
								<th>Policy Name</th>
								<th>Category Name</th>
								<th>Subcategory Name</th>
								<th>Reason for Rejection</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rejectedPolicies"
								items="${sessionScope.rejectedPolicies}">
								<tr>
									<td><c:out value="${rejectedPolicies.policyId}" /></td>
									<td><c:out value="${rejectedPolicies.policyName}" /></td>
									<td><c:out value="${rejectedPolicies.categoryName}" /></td>
									<td><c:out value="${rejectedPolicies.subCategoryName}" /></td>
									<td><c:out value="${rejectedPolicies.reason}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('searchInput');
        const tableBody = document.getElementById('searchbody');
 
        function filterTable() {
            const searchTerm = searchInput.value.toLowerCase();
            const rows = tableBody.getElementsByTagName('tr');
 
            for (let row of rows) {
                const cells = row.getElementsByTagName('td');
                let match = false;
 
                for (let cell of cells) {
                    if (cell.textContent.toLowerCase().includes(searchTerm)) {
                        match = true;
                        break;
                    }
                }
 
                row.style.display = match ? '' : 'none';
            }
        }
 
        searchInput.addEventListener('input', filterTable);
    });
</script>
			<!-- Bootstrap JS and dependencies -->
			<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
			<script
				src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
			<script
				src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

