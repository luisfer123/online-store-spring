<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Button trigger modal -->
<button type="button" class="btn btn-danger" data-toggle="modal"
	data-target="#deleteProductModal${product.id }">Delete</button>

<!-- Modal -->
<div class="modal fade" id="deleteProductModal${product.id }" tabindex="-1" role="dialog"
	aria-labelledby="deleteProductModalLabel${product.id }" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="deleteProductModalLabel${product.id }">Delete Product</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				Are you sure you want to delete this product?
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
				<a href="<c:url value='/products/delete?product_id=${product.id }' />" class="btn btn-danger">Delete</a>
			</div>
		</div>
	</div>
</div>