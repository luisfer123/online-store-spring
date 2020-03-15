<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<meta charset="UTF-8">
	<title>list of products</title>
	
	<%@ include file="./layers/scripts.jsp"%>
	
	<style type="text/css">
		.img-size {
			max-width: 250px;
	  		width:100%;
	  		height: 250px;
	  		max-height: 100%;
		}
	</style>
</head>
<body>

	<%@ include file="./layers/navbar.jsp"%>

	<div class="container mt-3">
		
		<c:forEach items="${products }" var="product">
			<!-- using a bootstrap 4 card with a bootstrap 4 media object within body card -->
			<div class="card mb-3">
				<h5 class="card-header">${product.name }</h5>
				<div class="card-body">
					<div class="media">
						<img class="align-self-center mr-3 img-size" src="data:image/png;base64,${product.mainImageAsString }" alt="Generic placeholder image">
						<div class="media-body">
							<h5 class="card-title">Price: ${product.price }</h5>
							<p class="card-text">${product.description }</p>
							<a href="<c:url value='/products/${product.id }' />" class="btn btn-primary">Product Details</a>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a href="<c:url value="/products/${product.id }/product_items" />" class="btn btn-primary">Manage Stock</a>
							</sec:authorize>
						</div>
					</div>
				</div>
				<div class="card-footer">
					<a href="<c:url value='/cart/${product.id }/add' />" class="btn btn-primary">Add to Cart</a>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="float-right">
							<a href="#" class="btn btn-secondary">Edit</a>
							<!-- Add button to confirm delete product -->
							<%@ include file="./layers/products/confirm_delete_product.jsp" %>
						</div>
					</sec:authorize>
				</div>
			</div>
		</c:forEach>

		<%@ include file="./layers/products/navigation.jsp" %>
		
	</div>
</body>
</html>