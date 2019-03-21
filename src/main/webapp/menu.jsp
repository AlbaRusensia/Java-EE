<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<div style="padding: 5px;">
 
   <a href="<%=request.getContextPath()%>/home">Home</a>
   |
   <a href= "<%=request.getContextPath()%>/product?active=show">Product List</a>
   |
   <a href="<%=request.getContextPath()%>/userInfo">My Account Info</a>
   |
   <a href="<%=request.getContextPath()%>/login">Login</a>
    
</div>  