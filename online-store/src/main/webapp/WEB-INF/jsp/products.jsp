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
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<!-- Navigation -->
		<c:set value="3" var="navigationSize" />
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li class="page-item">
		      		<a class="page-link" href="<c:url value="/products?page_number=${currentPage > 0 ? currentPage - 1 : 0 }" />" aria-label="Previous">
		       			<span aria-hidden="true">&laquo;</span>
		        		<span class="sr-only">Previous</span>
		      		</a>
		    	</li>
				<c:if test="${currentPage-navigationSize > 0 }">
					<li class="page-item">
						<a class="page-link" href="<c:url value="/products?page_number=0" />">0</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#" >...</a>
					</li>
				</c:if>
				<c:forEach begin="${currentPage - navigationSize >= 0 ? currentPage - navigationSize : 0 }" end="${currentPage + navigationSize < numberOfPages ? currentPage + navigationSize : numberOfPages - 1 }" var="i">
					<li class="page-item ${currentPage == i ? 'active' : '' }">
						<a class="page-link" href="<c:url value="/products?page_number=${i }" />">${i }</a>
					</li>
				</c:forEach>
				<c:if test="${currentPage+navigationSize < numberOfPages-1 }">
					<li class="page-item">
						<a class="page-link" href="#" >...</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="<c:url value="/products?page_number=${numberOfPages-1 }" />">${numberOfPages-1 }</a>
					</li>
				</c:if>
				<li class="page-item">
		      		<a class="page-link" href="<c:url value="/products?page_number=${currentPage < numberOfPages-1 ? currentPage + 1 : numberOfPages-1}" />" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				        <span class="sr-only">Next</span>
		      		</a>
		    	</li>
			</ul>
		</nav>

	</div>



</body>
</html>