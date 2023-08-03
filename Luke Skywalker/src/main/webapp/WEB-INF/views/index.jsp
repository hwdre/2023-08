<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
</head>
<body>
<%@ include file="menu.jsp" %>
	<h1>i am index</h1>
	<%=request.getHeader("User-Agent") %>
	<p>안녕 난 p태그야^^</p>
</body>
</html>