<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
<meta charset="UTF-8">
<title>Product Stock</title>

<%@ include file="./layers/scripts.jsp" %>

<style type="text/css">
	div.height {
		height: 300px;
		max-height: 300px
	}
</style>
</head>
<body>

	<%@ include file="./layers/navbar.jsp" %>
	
	<div class="container">
		
		<h1 class="mt-3 text-center">Manage Product Stock</h1>
		
		<c:choose>
			<c:when test="${productStock eq 0 }">
				<h4>This product does not have any item in stock currently.</h4>
			</c:when>
			<c:when test="${productStock eq 1 }">
				<h4>There is one item in stock</h4>
			</c:when>
			<c:when test="${productStock gt 1 }">
				<h4>There are ${productStock } items in stock</h4>
			</c:when>
		</c:choose>
		
		<br />
		
		<p>
			In this page only items that are currently available in stock are shown. To see a complete list of items click 
			<a href="#">here.</a>
		</p>
		
		<br />
		
		<div class="row mt-3">
			<div class="col-md-6 height">
				<h3>Product Items:</h3>
				<div class="overflow-auto height">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Item Id</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items }" var="item">
								<tr>
									<th scope="row">${item.id }</th>
									<th></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- Navigation -->
					<!-- navigationSize: Size of the list of number pages shown in the navigation menu -->
					<c:set value="3" var="navigationSize" />
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li class="page-item">
					      		<a class="page-link" href="<c:url value="/products/${productId }/product_items?pageNumber=${currentPage > 0 ? currentPage - 1 : 0 }" />" aria-label="Previous">
					       			<span aria-hidden="true">&laquo;</span>
					        		<span class="sr-only">Previous</span>
					      		</a>
					    	</li>
							<c:if test="${currentPage-navigationSize > 0 }">
								<li class="page-item">
									<a class="page-link" href="<c:url value="/products/${productId }/product_items?pageNumber=0" />">0</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="#" >...</a>
								</li>
							</c:if>
							<c:forEach begin="${currentPage - navigationSize >= 0 ? currentPage - navigationSize : 0 }" end="${currentPage + navigationSize < numberOfPages ? currentPage + navigationSize : numberOfPages - 1 }" var="i">
								<li class="page-item ${currentPage == i ? 'active' : '' }">
									<a class="page-link" href="<c:url value="/products/${productId }/product_items?pageNumber=${i }" />">${i }</a>
								</li>
							</c:forEach>
							<c:if test="${currentPage+navigationSize < numberOfPages-1 }">
								<li class="page-item">
									<a class="page-link" href="#" >...</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="<c:url value="/products/${productId }/product_items?pageNumber=${numberOfPages-1 }" />">${numberOfPages-1 }</a>
								</li>
							</c:if>
							<li class="page-item">
					      		<a class="page-link" href="<c:url value="/products/${productId }/product_items?pageNumber=${currentPage < numberOfPages-1 ? currentPage + 1 : numberOfPages-1}" />" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							        <span class="sr-only">Next</span>
					      		</a>
					    	</li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="col-md-6 height">
				<h3 class="mb-3">Add new product item:</h3>
				
				<c:if test="${param.item_added eq true }">
					<div class="alert alert-success" role="alert">
						Item added!
					</div>
				</c:if>
				
				<form action="<c:url value="/products/${productId }/add_item" />" method="post">
				
					<div class="form-group">
						<label for="ProductItemId">Product Item Id</label>
						<input type="text" name="ProductItemId" id="ProductItemId" class="form-control" />
					</div>
					
					<div class="form-group">
						<label for="location">Product location</label>
						<input type="text" name="location" id="location" class="form-control" />
					</div>
					
					<sec:csrfInput/>
					
					<button type="submit" class="btn btn-primary">Add Item</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>