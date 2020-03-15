<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Navigation -->
<c:set value="3" var="navigationSize" />
<nav aria-label="Page navigation">
	<ul class="pagination">
		<li class="page-item">
      		<a class="page-link" href="<c:url value="/users?page_number=${currentPage > 0 ? currentPage - 1 : 0 }" />" aria-label="Previous">
       			<span aria-hidden="true">&laquo;</span>
        		<span class="sr-only">Previous</span>
      		</a>
    	</li>
		<c:if test="${currentPage-navigationSize > 0 }">
			<li class="page-item">
				<a class="page-link" href="<c:url value="/users?page_number=0" />">0</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="#" >...</a>
			</li>
		</c:if>
		<c:forEach begin="${currentPage - navigationSize >= 0 ? currentPage - navigationSize : 0 }" end="${currentPage + navigationSize < numberOfPages ? currentPage + navigationSize : numberOfPages - 1 }" var="i">
			<li class="page-item ${currentPage == i ? 'active' : '' }">
				<a class="page-link" href="<c:url value="/users?page_number=${i }" />">${i }</a>
			</li>
		</c:forEach>
		<c:if test="${currentPage+navigationSize < numberOfPages-1 }">
			<li class="page-item">
				<a class="page-link" href="#" >...</a>
			</li>
			<li class="page-item">
				<a class="page-link" href="<c:url value="/users?page_number=${numberOfPages-1 }" />">${numberOfPages-1 }</a>
			</li>
		</c:if>
		<li class="page-item">
      		<a class="page-link" href="<c:url value="/users?page_number=${currentPage < numberOfPages-1 ? currentPage + 1 : numberOfPages-1}" />" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		        <span class="sr-only">Next</span>
      		</a>
    	</li>
	</ul>
</nav>