<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
<%@ include file="./layers/scripts.jsp"%>

</head>
<body>

	<%@ include file="./layers/navbar.jsp"%>

	<div class="container">
	
		<c:if test="${param.user_deleted eq true }">
			<div class="alert alert-success mt-3" role="alert">
				User deleted successfully.
			</div>
		</c:if>
	
		<h3 class="mt-3 mb-3">List of users:</h3>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Username</th>
					<th scope="col">Email</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Administrate</th>
				</tr>
			</thead>
			<tbody>
				<c:set value="0" var="i" />
				<c:forEach items="${users }" var="user">
					<c:set value="${i = i+1 }" var="i" />
					<tr>
						<th scope="row">${i }</th>
						<td>${user.username }</td>
						<td>${user.email }</td>
						<td>${user.firstName }</td>
						<td>${user.lastName }</td>
						<td>
							<!-- Button for deleting user is added here with include directive -->
							<%@ include file="./layers/users/modal_confirm_delete_user.jsp" %>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- A menu for navigating the user pages is added here with include directive -->
		<%@ include file="./layers/users/navigation.jsp" %>
		
	</div>

</body>
</html>