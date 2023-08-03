<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.bno }</title>
<link rel="stylesheet" href="./css/detail.css">
<link rel="stylesheet" href="c/css/menu.css">
<link rel="stylesheet" href="c/css/detail.css">
<script type="text/javascript">
	function del() {
		let dele = confirm("삭제하시겠습니까?");
		//alert(dele);
		if(dele) {
			location.href="./delete?bno=${dto.bno}";
		}
	}
	
	function edit() {
		if(confirm("수정하시겠습니까?")) {
			location.href="./edit?bno=${dto.bno}";
		}
	}
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
<h1>상세보기</h1>
	<div class="detail-content">
		<div class="title">${dto.bno } / ${dto.btitle }
		<c:if test="${sessionScope.mid ne null && sessionScope.mid eq dto.m_id }">
		<img src="./img/edit.png" onclick="edit()">&nbsp;  
		<img src="./img/delete.png" onclick="del()">
		</c:if>
		</div>
		<div class="namebar">
			<div class="name">${dto.m_name }</div>
			<div class="like">${dto.blike }</div>
			<div class="date">${dto.bdate }</div>
			<div class="ip">${dto.bip }</div>
		</div>
		<div class="content">${dto.bcontent }</div>
	</div>	
</body>
</html>