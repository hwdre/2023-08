<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀</title>
<link rel="stylesheet" href="./css/menu.css">
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div style="width: 100%; height: 100vh;">
	<h1>team</h1>
	<c:set var="num" value="1" />
	<div style="margin: 0 auto; width: 900px;height: auto; background-color: gray; text-align: center;">
		<div style="margin: 5px; background-color: yellow; height: 30px; width: 100%;">
		<c:forEach items="${list }" var="i">
			${i }
			<c:if test="${num % 5 == 0 }">
				<br></div>
				<div style="margin: 5px; background-color: yellow; height: 30px; width: 100%;">
			</c:if>
			<c:set var="num" value="${num + 1 }" />
		</c:forEach>
		</div>
	</div>
	</div>
</body>
</html>