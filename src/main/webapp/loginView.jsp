<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>

	<h3>Login Page</h3>
	<p style="color: red;">${errorString}</p>


	<form method="POST" action="${pageContext.request.contextPath}/login">
	<input type="hidden" name="id" value="${user.id}" />
		<table border="0">
			<tr>
				<td>User Name</td>
				<td><input type="text" name="login" value="${user.userName}" />
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"
					value="${user.password}" /></td>
			</tr>
			<tr>
				<td><select name="select">
						<option selected="selected">Gender</option>
						<option value = "${user.gender}">M</option>
						<option value="${user.gender}">F</option>
				</select></td>

			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /> <a
					href="${pageContext.request.contextPath}/">Cancel</a></td>
			</tr>
		</table>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>