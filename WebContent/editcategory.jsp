<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Category</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Edit Category</h2>
        <c:choose>
            <c:when test="${not empty requestScope.category}">
                <form action="EditCategoryServlet" method="post">
                    <input type="hidden" name="categoryId" value="${category.categoryId}" />
                    <div class="form-group">
                        <label for="categoryName">Category Name:</label>
                        <input type="text" class="form-control" id="categoryName" name="categoryName" value="${category.categoryName}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Category</button>
                </form>
            </c:when>
            <c:otherwise>
                <p>Category not found.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
