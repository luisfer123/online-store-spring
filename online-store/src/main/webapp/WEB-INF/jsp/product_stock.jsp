<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="./layers/scripts.jsp" %>
</head>
<body>

	<%@ include file="./layers/navbar.jsp" %>
	
	<div class="container">
		
		<h1>Manage Product Stock</h1>
		
		<c:choose>
			<c:when test="${productStock eq 0 }">
				<h4>This product does not have any item in stock currently.</h4>
			</c:when>
			<c:when test="${productStock eq 1 }">
				<h4>There is one item in stock</h4>
			</c:when>
			<c:when test="${productStock gt 1 }">
				<h4>There are ${productStock } items in stock</h4>
			</c:when>
		</c:choose>
		
		<h3 class="mb-3">Add new product item:</h3>
		
		<c:if test="${param.item_added eq true }">
			<div class="alert alert-success" role="alert">
				Item added!
			</div>
		</c:if>
		
		<form action="<c:url value="/products/${productId }/add_item" />" method="post">
		
			<div class="form-group">
				<label for="ProductItemId">Product Item Id</label>
				<input type="text" name="ProductItemId" id="ProductItemId" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="location">Product location</label>
				<input type="text" name="location" id="location" class="form-control" />
			</div>
			
			<sec:csrfInput/>
			
			<button type="submit" class="btn btn-primary">Add Item</button>
		</form>
		
	</div>

</body>
</html>