<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="./layers/scripts.jsp" %>
</head>
<body>

	<%@ include file="./layers/navbar.jsp" %>
	
	<div class="container mt-3">
		
		<div class="media">
			<img class="mr-3" src=".../64x64" alt="Generic placeholder image">
			<div class="media-body">
				<h3 class="mt-0 mb-3 text-center">Profile data</h3>
				<ul class="list-group">
					<li class="list-group-item">
					    <div class="row">
					    	<div class="col-md-9">
					    		<h5 class="h5">First Name:</h5>
						    	<p><strong>${user.firstName }</strong></p>
					    	</div>
						    <div class="col-md-3">
						    	<div class="float-right">
						    		<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdateFirstName" aria-expanded="false" 
										aria-controls="collapseUpdateFirstName">
										Edit
									</button>
								</div>
					    	</div>
					    </div>
					    <div class="collapse" id="collapseUpdateFirstName">
							<div class="card card-body">
								<form action="<c:url value='/users/update_first_name' />" method="post">
									<div class="form-group">
										<label for="updateFirstName">Enter new First Name</label> 
											<input class="form-control" id="updateFirstName"
												name="updateFirstName" placeholder="First Name">
									</div>
									<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdateFirstName" aria-expanded="false" 
										aria-controls="collapseUpdateFirstName">
										Cancel
									</button>
									
									<sec:csrfInput/>
									
									<button type="submit" class="btn btn-primary">Update</button>
								</form>
							</div>
						</div>
					</li>
					<li class="list-group-item d-flex justify-content-between align-items-center">
					    <div class="container">
					    <h5 class="h5">First Name:</h5>
					    <p><strong>${user.firstName }</strong></p>
					    </div>
					    <button class="btn btn-default">Edit</button>
					</li>
				</ul>
			</div>
</div>
		
	</div>

</body>
</html>