<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="./layers/scripts.jsp"%>
</head>
<body>

	<%@ include file="./layers/navbar.jsp"%>
	
	<div class="container">

		<form method="post" action="<c:url value = '/products/add?${_csrf.parameterName}=${_csrf.token}' />" enctype="multipart/form-data">
			<div class="form-group">
				<label for="name">Name</label> 
				<input type="text" name="name" class="form-control" id="name" placeholder="Product Name">
			</div>
			<div class="form-group">
				<label for="price">Price</label> 
				<input type="number" name="price" class="form-control" id="price" placeholder="Price">
			</div>
			<div class="form-group">
				<label for="image" class="form-group">Upload an Image</label>
				<input type="file" id="image" name="image" class="form-control" />
			</div>
			
			<sec:csrfInput/>
			
			<button type="submit" class="btn btn-primary">Save Product</button>
		</form>
	
	</div>

</body>
</html>