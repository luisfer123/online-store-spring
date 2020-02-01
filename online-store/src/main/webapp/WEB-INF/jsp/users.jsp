<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
						<td><a href="/users/delete?user_id=${user.id }" class="btn btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- Navigation -->
		<c:set value="3" var="navigationSize" />
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li class="page-item">
		      		<a class="page-link" href="<c:url value="/users?page_number=${currentPage > 0 ? currentPage - 1 : 0 }" />" aria-label="Previous">
		       			<span aria-hidden="true">&laquo;</span>
		        		<span class="sr-only">Previous</span>
		      		</a>
		    	</li>
				<c:if test="${currentPage-navigationSize > 0 }">
					<li class="page-item">
						<a class="page-link" href="<c:url value="/users?page_number=0" />">0</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#" >...</a>
					</li>
				</c:if>
				<c:forEach begin="${currentPage - navigationSize >= 0 ? currentPage - navigationSize : 0 }" end="${currentPage + navigationSize < numberOfPages ? currentPage + navigationSize : numberOfPages - 1 }" var="i">
					<li class="page-item ${currentPage == i ? 'active' : '' }">
						<a class="page-link" href="<c:url value="/users?page_number=${i }" />">${i }</a>
					</li>
				</c:forEach>
				<c:if test="${currentPage+navigationSize < numberOfPages-1 }">
					<li class="page-item">
						<a class="page-link" href="#" >...</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="<c:url value="/users?page_number=${numberOfPages-1 }" />">${numberOfPages-1 }</a>
					</li>
				</c:if>
				<li class="page-item">
		      		<a class="page-link" href="<c:url value="/users?page_number=${currentPage < numberOfPages-1 ? currentPage + 1 : numberOfPages-1}" />" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				        <span class="sr-only">Next</span>
		      		</a>
		    	</li>
			</ul>
		</nav>
	</div>

</body>
</html>