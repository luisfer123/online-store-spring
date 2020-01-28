<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="./layers/scripts.jsp" %>
</head>
<body>

	<%@ include file="./layers/navbar.jsp" %>
	
	<div class="container">
		<img id="photo" src="data:image/png;base64,${productImages[0]}" />
	</div>

</body>
</html>