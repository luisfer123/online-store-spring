<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>

<%@ include file="./layers/scripts.jsp"%>
</head>
<body>

	<%@ include file="./layers/navbar.jsp"%>

	<div class="container">
	
		<h3 class="mt-3">Registration Form</h3>

		<form class="mt-3" method="post" action="<c:url value="/users/add" />">
			<div class="form-group">
				<label for="firstName">First Name</label> 
				<input type="text" name="firstName" class="form-control" id="firstName" placeholder="First Name"> 
			</div>
			<div class="form-group">
				<label for="lastName">Last Name</label> 
				<input type="text" name="lastName" class="form-control" id="lastName" placeholder="Last Name"> 
			</div>
			<div class="form-group">
				<label for="username">Username</label> 
				<input type="text" name="username" class="form-control" id="username" aria-describedby="userNameHelp" placeholder="Username"> 
				<small id="userNameHelp" class="form-text text-muted">Username must be different for each user.</small>
				<c:if test="${validation_result.getFieldError('username') != null }">
					<small class="form-text text-danger">
						<c:out value="${validation_result.getFieldError('username').getDefaultMessage() }" />
					</small>
				</c:if>
			</div>
			<div class="form-group">
				<label for="email">Email address</label> 
				<input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email"> 
				<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="password">Password</label> 
				<input type="password" name="password" class="form-control" id="password" placeholder="Password">
			</div>
			<div class="form-group">
				<label for="confirmPassword">Repeat Password</label> 
				<input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="Enter Password Again">
			</div>
			
			<sec:csrfInput/>

			<button type="submit" class="btn btn-primary">Register</button>
		</form>

	</div>

</body>
</html>