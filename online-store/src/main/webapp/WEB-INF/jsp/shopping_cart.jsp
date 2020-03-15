<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="./layers/scripts.jsp" %>
</head>
<body>

	<%@include file="./layers/navbar.jsp" %>
	
	<div class="container">
	
		<c:if test="${emptyCart }">
			<div class="alert alert-primary mt-3" role="alert">
				Cart is empty!
			</div>
		</c:if>
		<c:if test="${!emptyCart }">
			<div class="row mt-3">
				<div class="col-md-8">
						<h3 class="h3 mt-3">Products:</h3>
						<table class="table mt-3">
							<thead>
								<tr>
									<th scope="col">Name</th>
									<th scope="col">Price</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cartProducts }" var="product">
									<tr>
										<th scope="row">${product.name }</th>
										<th>${product.price }</th>
										<th>
											<a href="<c:url value='/cart/${product.id }/delete' />" class="btn btn-danger">Delete</a>
										</th>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
				<div class="col-md-4 mt-3">
					<div class="card" style="width: 18rem;">
						<div class="card-body">
							<h5 class="cart-title">Cart Details</h5>
							
							<a href="<c:url value = '#' />" class="btn btn-primary">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>

</body>
</html>