<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<%@ include file="./scripts.jsp"%>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Online Store</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Link</a>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" id="logout-link" href="#">Logout</a>
						<form method="post" id="logout-form" action="<c:url value='/logout' />">
							<sec:csrfInput/>
						</form>
					</li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/login' />">Login</a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</nav>

</body>
</html>