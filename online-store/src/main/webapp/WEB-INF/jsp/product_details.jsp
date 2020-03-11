<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="./layers/scripts.jsp" %>

	<style type="text/css">
		.img-size {
			max-width: 500px;
	  		width:100%;
	  		height: 500px;
	  		max-height: 100%;
		}
	</style>
	
</head>
<body>

	<%@ include file="./layers/navbar.jsp" %>
	<div class="container">
		<c:if test="${param.product_added eq true }">
			<div class="alert alert-success mt-3" role="alert">
				Product added successfully.
			</div>
		</c:if>
	</div>
	
	<!-- A bootstrap media object element is used nested within a bootstrap card element
		 to show the main image at the left and product detail at the right. -->
	<div class="container mt-3">
		<div class="card mb-3">
				<h5 class="card-header">${product.name }</h5>
				<div class="card-body">
					<div class="media">
						<img class="align-self-center mr-3 img-size" src="data:image/png;base64,${mainImage }" alt="Generic placeholder image">
						<div class="media-body">
							<h5 class="card-title">Price: ${product.price }</h5>
							<p class="card-text">${product.description }</p>
							<p><strong>${product.stock } items in stock</strong></p>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a href="<c:url value='/products/${product.id }/product_items' />" class="btn btn-secondary">Manage product stock</a>
							</sec:authorize>
						</div>
					</div>
				</div>
			</div>
			
		<!-- If the product has no extra images to show, only a message is shown to the user -->
		<c:if test="${fn:length(productImages) == 0 }">
			<div class="alert alert-info" role="alert">
				This product does not have additional images to show!
			</div>
		</c:if>
		
		<!-- Carousel bootstrap's element used to show additional images of the product,
			 only when the product has at least one extra image to show. -->
		<c:if test="${fn:length(productImages) != 0 }">
			<div class="alert alert-info" role="alert">
				Additional product's images!
			</div>
			<div id="carouselProductImages" 
					class="carousel slide" 
					data-ride="carousel">
					
				<ol class="carousel-indicators">
					<c:forEach begin="0" end="${fn:length(productImages) - 1 }" var="i">
						<li data-target="#carouselProductImages" data-slide-to="${i }" class="${i == 0 ? 'active' : '' }"></li>
					</c:forEach>
				</ol>
				<div class="carousel-inner">
					<c:forEach begin="0" end="${fn:length(productImages) - 1 }" var="i">
						<div class="carousel-item ${i == 0 ? 'active' : '' }">
							<img 
								class="d-block w-100" 
								src="data:image/png;base64,${productImages[i] }" 
								alt="First slide">
						</div>
					</c:forEach>
				</div>
				<a class="carousel-control-prev" href="#carouselProductImages"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselProductImages"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</c:if>

	</div>
</body>
</html>