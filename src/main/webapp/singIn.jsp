<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/MyStoreSecond/singIn">
		<div class="form-group">
			<label>Login</label> <input type="text" name="login"
				placeholder="Login">
		</div>
		<div class="form-group">
			<label>Password</label> <input type="password" name="password"
				placeholder="Password">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>