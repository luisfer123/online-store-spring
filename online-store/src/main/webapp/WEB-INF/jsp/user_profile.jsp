<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>

<%@ include file="./layers/scripts.jsp" %>

<style type="text/css">
	#profileImage {
		max-width: 300px;
  		width:100%;
  		height: 300px;
  		max-height: 100%;
	}
</style>
</head>

<body>

	<%@ include file="./layers/navbar.jsp" %>
	
	<div class="container mt-3">
		
		<div class="row">
			<div class="col-md-4">
				<img id="profileImage" class="mr-3 mt-3" src="data:image/png;base64,${userProfileImage }" alt="Generic placeholder image">

				<p>
					<button class="btn btn-secondary mt-2" type="button" data-toggle="collapse" data-target="#collapseUpdateImageProfile" aria-expanded="false" aria-controls="collapseUpdateImageProfile">
						Change image
					</button>
				</p>
				<div class="collapse" id="collapseUpdateImageProfile">
					<div class="card card-body">
						<form method="post" action="<c:url value='/users/updateImage?${_csrf.parameterName}=${_csrf.token}' />" enctype="multipart/form-data">
							
							<div class="costom-file">
								<input name="new_profile_image" type="file" id="new_profile_image" class="custom-file-input" required>
								<label class="custom-file-label" for="new_profile_image">Choose an image</label>
							</div>
							<sec:csrfInput/>
							
							<button type="submit" class="btn btn-primary">Update</button>
							<button class="btn btn-secondary" type="button" data-toggle="collapse" data-target="#collapseUpdateImageProfile" aria-expanded="false" aria-controls="collapseUpdateImageProfile">
								Cancel
							</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-8">
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
								<form action="<c:url value='/users/update' />" method="post">
									<div class="form-group">
										<label for="newFieldValue">Enter new First Name</label> 
											<input class="form-control" id="newFieldValue"
												name="newFieldValue" placeholder="First Name">
											<input name="fieldToUpdate" id="fieldToUpdate" 
												type="hidden" value="first_name">
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
					<li class="list-group-item">
					    <div class="row">
					    	<div class="col-md-9">
					    		<h5 class="h5">Last Name:</h5>
						    	<p><strong>${user.lastName }</strong></p>
					    	</div>
						    <div class="col-md-3">
						    	<div class="float-right">
						    		<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdateLastName" aria-expanded="false" 
										aria-controls="collapseUpdateLastName">
										Edit
									</button>
								</div>
					    	</div>
					    </div>
					    <div class="collapse" id="collapseUpdateLastName">
							<div class="card card-body">
								<form action="<c:url value='/users/update' />" method="post">
									<div class="form-group">
										<label for="newFieldValue">Enter new Last Name</label> 
											<input class="form-control" id="newFieldValue"
												name="newFieldValue" placeholder="Last Name">
											<input name="fieldToUpdate" id="fieldToUpdate" 
												type="hidden" value="last_name">
									</div>
									<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdateLastName" aria-expanded="false" 
										aria-controls="collapseUpdateLastName">
										Cancel
									</button>
									
									<sec:csrfInput/>
									
									<button type="submit" class="btn btn-primary">Update</button>
								</form>
							</div>
						</div>
					</li>
					<li class="list-group-item">
					    <div class="row">
					    	<div class="col-md-9">
					    		<h5 class="h5">Email:</h5>
						    	<p><strong>${user.email }</strong></p>
					    	</div>
						    <div class="col-md-3">
						    	<div class="float-right">
						    		<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdateEmail" aria-expanded="false" 
										aria-controls="collapseUpdateEmail">
										Edit
									</button>
								</div>
					    	</div>
					    </div>
					    <div class="collapse" id="collapseUpdateEmail">
							<div class="card card-body">
								<form action="<c:url value='/users/update' />" method="post">
									<div class="form-group">
										<label for="newFieldValue">Enter new Email</label> 
										<input class="form-control" id="newFieldValue"
											name="newFieldValue" placeholder="Email">
										<input name="fieldToUpdate" id="fieldToUpdate" 
											type="hidden" value="email">
									</div>
									<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdateEmail" aria-expanded="false" 
										aria-controls="collapseUpdateEmail">
										Cancel
									</button>
									
									<sec:csrfInput/>
									
									<button type="submit" class="btn btn-primary">Update</button>
								</form>
							</div>
						</div>
					</li>
					<li class="list-group-item">
					    <div class="row">
					    	<div class="col-md-9">
					    		<h5 class="h5">Password:</h5>
						    	<p><strong>************</strong></p>
					    	</div>
						    <div class="col-md-3">
						    	<div class="float-right">
						    		<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdatePassword" aria-expanded="false" 
										aria-controls="collapseUpdatePassword">
										Edit
									</button>
								</div>
					    	</div>
					    </div>
					    <div class="collapse" id="collapseUpdatePassword">
							<div class="card card-body">
								<form action="<c:url value='/users/update' />" method="post">
									<div class="form-group">
										<label for="newPassword">Enter new Password</label> 
										<input type="password" class="form-control" id="newPassword"
											name="new_password" placeholder="Password">
									</div>
									<button class="btn btn-secondary" type="button" data-toggle="collapse"
										data-target="#collapseUpdatePassword" aria-expanded="false" 
										aria-controls="collapseUpdatePassword">
										Cancel
									</button>
									
									<sec:csrfInput/>
									
									<button type="submit" class="btn btn-primary">Update</button>
								</form>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>	
	</div>

</body>
</html>