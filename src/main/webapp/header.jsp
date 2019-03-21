<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="background: #E0E0E0; height: 55px; padding: 5px;">
	<div style="float: left">
		<h1>My Site</h1>
	</div>

	<div style="float: right; padding: 10px; text-align: right;">

		<!-- User store in session with attribute: loginedUser -->
		Hello <b>${sessionScope.login}</b> <br /> Search <input name="search">

	</div>

</div>