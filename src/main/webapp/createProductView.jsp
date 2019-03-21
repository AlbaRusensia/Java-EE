<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Product</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>

	<h3>Create Product</h3>

	<p style="color: red;"></p>

	<form method="POST"
		action="<%=request.getContextPath()%>/product?active=write">
		<input type="hidden" name="id" value="${product.id}" />
		<table border="1">
			<tr>
				<td>Code</td>
				<td><input type="text" name="code" value="${product.code}" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" value="${product.name}" /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="text" name="price" value="${product.price}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /> <a
					href="productListView.jsp">Cancel</a></td>
			</tr>
		</table>
	</form>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>