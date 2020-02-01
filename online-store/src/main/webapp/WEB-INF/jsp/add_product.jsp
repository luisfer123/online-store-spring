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

<script type="text/javascript">
	$(document).ready(function() {

		// Add input for extra image dinamically (by clicking a button).
		var max_images = 9;
		var count = 1;
		$("#add-other-image-button").click(function(e) {
			e.preventDefault();
			if(count <= max_images) {
				count++;
				$("#add-images").append('<div class="custom-file mt-3"><input type="file" name="image" class="custom-file-input" id="image' + count + '" required><label class="custom-file-label" for="image' + count + '">Add an image</label></div>');
			}

			// Put file's name in the image's input label when a image is selected in the form.
			$('.custom-file-input').on('change', function() { 
			   let fileName = $(this).val().split('\\').pop(); 
			   $(this).next('.custom-file-label').addClass("selected").html(fileName); 
			});
		});

		// Put file's name in the image's input label when a image is selected in the form.
		$('.custom-file-input').on('change', function() { 
		   let fileName = $(this).val().split('\\').pop(); 
		   $(this).next('.custom-file-label').addClass("selected").html(fileName); 
		});
	});
</script>
</head>
<body>

	<%@ include file="./layers/navbar.jsp"%>
	
	<div class="container">
	
		<c:if test="${param.has_products eq false }">
			<div class="alert alert-danger mt-3" role="alert">
				Does not exist any product jet.
			</div>
		</c:if>
	
		<h3 class="mt-3 mb-3">Add a new product to the store</h3>

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
				<label for="description">Product description:</label> 
				<input type="text" name="description" class="form-control" id="description" placeholder="description">
			</div>
			
			<div class="custom-file">
			    <input type="file" name="main_image" class="custom-file-input" id="main_image" required>
			    <label class="custom-file-label" for="main_image">Choose a Thumbnails image for the product</label>
			</div>
			
			<div class="mt-3" id="add-images">
				<button class="btn btn-secondary" id="add-other-image-button">Add another image</button>
				<!-- Inputs for uploading images are going to be added dynamically here -->
			</div>
			
			<sec:csrfInput/>
			
			<button type="submit" class="btn btn-primary mt-3">Save Product</button>
		</form>
	
	</div>

</body>
</html>