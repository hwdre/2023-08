<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.bno }</title>
<link rel="stylesheet" href="./css/detail.css">
<link rel="stylesheet" href="c/css/menu.css">
<link rel="stylesheet" href="c/css/detail.css">
<script src="./js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
	function del() {
		let dele = confirm("삭제하시겠습니까?");
		//alert(dele);
		if (dele) {
			location.href = "./delete?bno=${dto.bno}";
		}
	}

	function edit() {
		if (confirm("수정하시겠습니까?")) {
			location.href = "./edit?bno=${dto.bno}";
		}
	}
	$(function(){
		$(".commentBox").hide();
		$("#openComment").click(function(){
			$(".commentBox").show('slow');
			$("#openComment").remove();
		});
	});
</script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<h1>상세보기</h1>
	<div class="detail-content">
		<div class="title">${dto.bno }
			/ ${dto.btitle }
			<c:if
				test="${sessionScope.mid ne null && sessionScope.mid eq dto.m_id }">
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
		<div class="commentsList">
			<c:choose>
				<c:when test="${fn:length(commentList) gt 0 }">
					<div class="comments">
						<c:forEach items="${commentList }" var="c">
							<div class="comment">
								<div class="commentHead">
									<div class="cname">${c.m_name }(${c.m_id })</div>
									<div class="cdate">${c.b_date }</div>
									<div class="cno">${c.c_no }</div>
								</div>
								<div class="commentBody">${c.c_comment }</div>
							</div>
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>댓글이 없어요</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${sessionScope.mid ne null }">
			<button type="button" id="openComment">댓글창 열기</button>
			<div class="commentBox">
				<form action="./comment" method="post">
					<textarea id="commenttextarea" name="comment" placeholder="댓글을 입력해주세요."></textarea>
					<button type="submit" id="comment">글쓰기</button>
					<input type="hidden" name="bno" value="${dto.bno }">
				</form>
			</div>
		</c:if>
</body>
</html>