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
							<a href="#" class="btn btn-primary">Product Details</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		
		Go to page:
		<c:if test="${currentPage-3 > 0 }">
			<a href="<c:url value="/products?page_number=0" />">0 ... </a>
		</c:if>
		<c:forEach begin="${currentPage - 3 >= 0 ? currentPage - 3 : 0 }" end="${currentPage + 3 < numberOfPages ? currentPage + 3 : numberOfPages - 1 }" var="i">
			<c:choose>
				<c:when test="${i != currentPage }">
					<a href="<c:url value="/products?page_number=${i }" />">${i }</a>
				</c:when>
				<c:when test="${currentPage == i }">
					${currentPage }
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage+3 < numberOfPages-1 }">
			<a href="<c:url value="/users?page_number=${numberOfPages-1 }" />"> ... ${numberOfPages-1 } </a>
		</c:if>

	</div>



</body>
</html>