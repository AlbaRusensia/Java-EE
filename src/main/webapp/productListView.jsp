<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>

	<h3>Product List</h3>

	<p style="color: red;">${errorString}</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Price</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${productList}" var="product">
			<tr>
				<td>${product.code}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td><a href="product?active=save&id=${product.id}">Edit</a></td>
				<td><a href="product?active=delete&id=${product.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<a href="<%=request.getContextPath()%>/product?active=save">Create
		Product</a>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>