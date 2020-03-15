<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	
	<%@ include file="./layers/scripts.jsp"%>
	
	<style type="text/css">
		div.login {
			height: 600px;
			max-height: 600px;
			width: 600px;
			max-width: 400px;
			border: thin;
		}
	</style>
</head>
<body>

<%@ include file="./layers/navbar.jsp" %>

<div class="container mt-3 login">
	<form method="post" action="<c:url value='/login' />">
		<c:if test="${param.error eq true }">
			<div class="alert alert-danger" role="alert">
  				Username or Password are incorrect! Please try again.
			</div>
		</c:if>
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" name="username" class="form-control" id="username" placeholder="Enter your username">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" name="password" class="form-control" id="password" placeholder="Enter your password">
		</div>
		
		<sec:csrfInput/>
		
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</div>
</body>
</html>