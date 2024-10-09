<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	if (request.getSession().getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="style.css">
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.dashboard-container {
	max-width: 1200px;
	margin-left: 30px auto;
	margin-right: 30px auto;
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
	background-color: #3880ce;
	border-color: #3880ce;
}

.btn-success:hover {
	background-color: #3880ce;
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

.btn-group form {
	margin-right: 10px; /* Adjust the value as needed */
}

.alert {
	margin-top: 20px;
}

.container1 {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #f9f9f9;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	font-weight: bold;
	margin-bottom: 5px;
	display: block;
	color: #333;
}

.form-control {
	width: 100%;
	padding: 10px;
	line-height:1;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-control:focus {
	border-color: #007bff;
	box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}  .filter-section {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            border-radius: 5px;
        }
        .filter-section .form-group {
            margin-bottom: 15px;
        }
        .filter-section .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
</style>
</head>
<body>
	<div class="container dashboard-container">
		<h2 class="text-center">Admin Dashboard</h2>

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
			<li class="nav-item"><a class="nav-link"
				id="customer-policies-tab" data-toggle="tab"
				href="#customer-policies" role="tab"
				aria-controls="customer-policies" aria-selected="false">Customer
					Policies</a></li>
			<li class="nav-item"><a class="nav-link" id="add-policies-tab"
				data-toggle="tab" href="#add-policies" role="tab"
				aria-controls="add-policies" aria-selected="false">Add Policies</a></li>
			<li class="nav-item"><a class="nav-link" id="all-policies-tab"
				data-toggle="tab" href="#all-policies" role="tab"
				aria-controls="all-policies" aria-selected="false">All Policies</a></li>
			<li class="nav-item"><a class="nav-link" id="categories-tab"
				data-toggle="tab" href="#categories" role="tab"
				aria-controls="categories-policies" aria-selected="false">Categories</a></li>
			<li class="nav-item"><a class="nav-link" id="subcategories-tab"
				data-toggle="tab" href="#subcategories" role="tab"
				aria-controls="subcategories" aria-selected="false">Sub
					Categories</a></li>
		</ul>
		<div class="tab-content" id="dashboardTabsContent">
			<!-- User Details Tab -->
			<div class="tab-pane fade show active" id="user-details"
				role="tabpanel" aria-labelledby="user-details-tab">
				<c:if test="${not empty sessionScope.user}">
					<div class="section-title">User Details</div>
					<div class="user-info card">
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
								<strong>FirstName:</strong>
								<c:out value="${sessionScope.user.firstName}" />
							</p>
							<p>
								<strong>LastName:</strong>
								<c:out value="${sessionScope.user.lastName}" />
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
			<!-- Customer Policies Tab -->
			<div class="tab-pane fade" id="customer-policies" role="tabpanel"
				aria-labelledby="customer-policies-tab">
				<c:if test="${not empty sessionScope.customer}">
					<div class="section-title">Customer Policies</div>
					<div class="table-responsive table-wrapper">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>User ID</th>
									<th>Policy ID</th>
									<th>Policy Name</th>
									<th>Category Name</th>
									<th>SubCategory Name</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Status</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="customer" items="${sessionScope.customer}">
									<tr>
										<td><c:out value="${customer.userId}" /></td>
										<td><c:out value="${customer.policyId}" /></td>
										<td><c:out value="${customer.policyName}" /></td>
										<td><c:out value="${customer.categoryName}" /></td>
										<td><c:out value="${customer.subCategoryName}" /></td>
										<td><c:out value="${customer.startDate}" /></td>
										<td><c:out value="${customer.endDate}" /></td>
										<td><c:out value="${customer.status}" /></td>
										<td>
											<div class="btn-group">
												<form action="UpdateStatusServlet" method="post"
													style="display: inline;">
													<input type="hidden" name="userId"
														value="${customer.userId}" /> <input type="hidden"
														name="policyId" value="${customer.policyId}" /> <input
														type="hidden" name="status" value="Approved" />
													<c:choose>
														<c:when test="${customer.status=='Pending'}">
															<button type="submit" class="btn btn-success">Approved</button>

														</c:when>
													</c:choose>
												</form>
												<form action="rejectPolicy.jsp" method="get"
													style="display: inline;">
													<input type="hidden" name="userId"
														value="${customer.userId}" /> <input type="hidden"
														name="policyId" value="${customer.policyId}" /> <input
														type="hidden" name="policyName"
														value="${customer.policyName}" />
													<c:choose>
														<c:when test="${customer.status=='Pending'}">
															<button type="submit" class="btn btn-danger">Reject</button>

														</c:when>
													</c:choose>
												</form>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
			<!-- All Policies Tab -->
			<div class="tab-pane fade" id="all-policies" role="tabpanel"
				aria-labelledby="all-policies-tab">
				<c:if test="${not empty sessionScope.policy}">
					<div class="section-title">All Policies</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Policy ID</th>
								<th>Policy Name</th>
								<th>Sub-Category Name</th>
								<th>Category Name</th>
								<th>Policy Amount</th>
								<th>Update</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="policy" items="${sessionScope.policy}">
								<tr>
									<td><c:out value="${policy.policyId}" /></td>
									<td><c:out value="${policy.policyName}" /></td>
									<td><c:out value="${policy.subCategoryName}" /></td>
									<td><c:out value="${policy.categoryName}" /></td>
									<td><c:out value="${policy.amount}" /></td>
									<td>
										<form action="EditPolicyServlet" method="get">
											<input type="hidden" name="policyId"
												value="${policy.policyId}" />
											<button type="submit" class="btn btn-primary">Edit</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>

			<!-- Add Policies Tab -->
			<div class="tab-pane fade" id="add-policies" role="tabpanel"
				aria-labelledby="add-policies-tab">
				<div class="section-title">Add New Policy</div>
				<div class="container1 dashboard-container">
					<form action="AddPolicyServlet" method="post">
						<div class="form-group">
							<label for="policyName">Policy Name:</label> <input type="text"
								class="form-control" id="policyName" name="policyName" required>
						</div>
						<div class="form-group">
							<label for="category">Category:</label> <select
								class="form-control" id="category" name="category" required>
								<c:forEach var="category" items="${sessionScope.categories}">
									<option value="${category.categoryName}">${category.categoryName}</option>
								</c:forEach>
							</select>
							<!--   <button type="button" class="btn btn-link" data-toggle="modal" data-target="#addCategoryModal">Add Category</button>-->
						</div>
						<div class="form-group">
							<label for="subCategory">Sub Category:</label> <select
								class="form-control" id="subCategory" name="subCategory"
								required>
								<c:forEach var="subCategory"
									items="${sessionScope.subCategories}">
									<option value="${subCategory.subCategoryName}">${subCategory.subCategoryName}</option>
								</c:forEach>
							</select>
							<!-- <button type="button" class="btn btn-link" data-toggle="modal" data-target="#addSubCategoryModal">Add Subcategory</button>-->
						</div>
						<div class="form-group">
							<label for="amount">Amount:</label> <input type="number"
								class="form-control" id="amount" name="amount" required>
						</div>
						<div class="form-group">
							<label for="minAge">Minimum Age:</label> <input type="number"
								class="form-control" id="minAge" name="minAge" required>
						</div>
						<div class="form-group">
							<label for="maxAge">Maximum Age:</label> <input type="number"
								class="form-control" id="maxAge" name="maxAge" required>
						</div>
						<button type="submit" class="btn btn-primary">Add Policy</button>
					</form>
				</div>
			</div>

			<!-- Categories Tab -->
			<div class="tab-pane fade" id="categories" role="tabpanel"
				aria-labelledby="categories-tab">
				<div class="section-title">Categories</div>
				<div class="btn-group">
					<button type="button" class="btn btn-success" data-toggle="modal"
						data-target="#addCategoryModal">Add Category</button>
				</div>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Category ID</th>
								<th>Category Name</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="category" items="${categories}">
								<tr>
									<td><c:out value="${category.categoryId}" /></td>
									<td><c:out value="${category.categoryName}" /></td>
									<td><a class="btn btn-primary btn-sm"
										href="#">Edit</a>
										<form action="DeleteCategoryServlet" method="post"
											style="display: inline;">
											<input type="hidden" name="categoryId"
												value="${category.categoryId}" />
											<button type="submit" class="btn btn-danger btn-sm">Cancel</button>
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- Subcategories Tab -->
			<div class="tab-pane fade" id="subcategories" role="tabpanel"
				aria-labelledby="subcategories-tab">
				<div class="section-title">Subcategories</div>
				<div class="btn-group">
					<button type="button" class="btn btn-success" data-toggle="modal"
						data-target="#addSubCategoryModal">Add Subcategory</button>
				</div>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Subcategory ID</th>
								<th>Subcategory Name</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="subCategories" items="${subCategories}">
								<tr>
									<td><c:out value="${subCategories.subCategoryId}" /></td>
									<td><c:out value="${subCategories.subCategoryName}" /></td>

									<td><a class="btn btn-primary btn-sm"
										href="edit-subcategory.jsp?subcategoryId=${subcategory.subcategoryId}">Edit</a>
										<form action="delete-subcategory.jsp" method="post"
											style="display: inline;">
											<input type="hidden" name="subcategoryId"
												value="${subcategory.subcategoryId}" />
											<button type="submit" class="btn btn-danger btn-sm">Cancel</button>
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<!-- Policies for Renewal Tab -->
			<div class="tab-pane fade" id="renewal-policies" role="tabpanel"
				aria-labelledby="renewal-policies-tab">
				<c:if test="${not empty sessionScope.renewalPolicies}">
					<div class="section-title">Policies for Renewal</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Policy ID</th>
								<th>Policy Name</th>
								<th>Category Name</th>
								<th>Sub-Category Name</th>
								<th>End Date</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="policy" items="${sessionScope.renewalPolicies}">
								<tr>
									<td><c:out value="${policy.policyId}" /></td>
									<td><c:out value="${policy.policyName}" /></td>
									<td><c:out value="${policy.categoryName}" /></td>
									<td><c:out value="${policy.subCategoryName}" /></td>
									<td><c:out value="${policy.endDate}" /></td>
									<td>
										<form action="RenewPolicyServlet" method="post"
											style="display: inline;">
											<input type="hidden" name="policyId"
												value="${policy.policyId}" />
											<button type="submit" class="btn btn-success">Renew</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>

	<!-- Add Category Modal -->
	<div class="modal fade" id="addCategoryModal" tabindex="-1"
		role="dialog" aria-labelledby="addCategoryModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addCategoryModalLabel">Add New
						Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="AddCategoryServlet" method="post">
						<div class="form-group">
							<label for="newCategory">Category Name:</label> <input
								type="text" class="form-control" id="newCategory"
								name="newCategory" required>
						</div>
						<button type="submit" class="btn btn-primary">Add
							Category</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Add subcategory Modal -->
	<div class="modal fade" id="addSubCategoryModal" tabindex="-1"
		role="dialog" aria-labelledby="addSubCategoryModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addSubCategoryModalLabel">Add New
						Subcategory</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="AddSubCategoryServlet" method="post">
						<div class="form-group">
							<label for="categoryId">Category:</label> <select
								class="form-control" id="categoryId" name="categoryId" required>
								<c:forEach var="category" items="${sessionScope.categories}">
									<option value="${category.categoryId}">${category.categoryName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="newSubCategory">Subcategory Name:</label> <input
								type="text" class="form-control" id="newSubCategory"
								name="newSubCategory" required>
						</div>
						<button type="submit" class="btn btn-primary">Add
							Subcategory</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
