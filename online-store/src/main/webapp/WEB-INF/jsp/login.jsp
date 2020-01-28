<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<%@ include file="./layers/scripts.jsp"%>
</head>
<body>

<%@ include file="./layers/navbar.jsp" %>

<div class="container mt-3">
	<form method="post" action="<c:url value='/login' />">
		<c:if test="${param.error eq true }">
			<div class="alert alert-danger" role="alert">
  				Username or Password are incorrect! Please try again.
			</div>
		</c:if>
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" name="username" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter email">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" name="password" class="form-control" id="password" aria-describedby="emailHelp" placeholder="Enter email">
		</div>
		
		<sec:csrfInput/>
		
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</div>
</body>
</html>