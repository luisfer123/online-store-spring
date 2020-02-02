<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="<c:url value='/home' />">Online Store</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item dropdown">
	        			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          				Administer
	        			</a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				            <a class="dropdown-item" href="<c:url value='/users' />">Users</a>
				            <a class="dropdown-item" href="<c:url value='/products' />">Products</a>
				            <a class="dropdown-item" href="<c:url value='/products/add' />">Add New Product</a>
				        </div>
			        </li>
		        </sec:authorize>
		        <sec:authorize access="isAuthenticated()">
					<li class="nav-item active">
						<a class="nav-link" href="<c:url value='/products?page_number=0' />">Products<span class="sr-only">(current)</span></a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item active">
						<a class="nav-link" href="<c:url value='#' />">My profile</a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item active">
						<a class="nav-link" href="<c:url value='#' />">Shopping cart</a>
					</li>
				</sec:authorize>
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
					<li class="nav-item">
						<a href="<c:url value="/users/add" />" class="nav-link">Register</a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</nav>
