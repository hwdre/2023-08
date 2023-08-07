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
	
	//댓글 삭제 버튼 만들기 = 반드시 로그인하고, 자신의 글인지 확인하는 검사구문 필요합니다.
	function cdel(cno){
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.href = "./cdel?bno=${dto.bno}&cno=" + cno
		}
	}
	$(function(){
		$(".commentBox").hide();
		$("#openComment").click(function(){
			$(".commentBox").show('slow');
			$("#openComment").remove();
		});
		//댓글 삭제 다른 방법 만듭니다.
		
		$(".cdel").click(function(){
			if(confirm("댓글을 삭제하시겠습니까?")){
				let cno = $(this).parent().siblings(".cid").text();
				let con_comments = $(this).parents(".comment");
				$.ajax({
					url:"./cdelR",
					type:"post",
					data:{bno: ${dto.bno}, cno: cno},
					dataType:"json",
					success:function(data){
						//alert(data);
						if(data.result == 1){
							con_comments.remove(); //변수 삭제
						} else {
							alert("통신에 문제가 생겼습니다.")
						}
					},
					error: function(error){
						alert("에러가 발생했습니다 " + error)
					}
				});
				/*location.href = "./cdel?bno=${dto.bno}&cno=" + cno;
				$.ajax({ //ajax 시작합니다.
				url: "./checkID", //checkID라는 곳으로 갈거에요.
				type: "post", //post방식으로 보낼거에요.
				data: {"id" : id},
				dataType: "json", //html 데이터타입으로 되돌아올거에요.<>같은거
				success: function(data){
					//alert(data.result);
					if(data.result == 1) {
						$("#id").css("background-color", "red")
						$("#id").focus();
						$("#resultMSG").css("color", "red")
						.text("이미 등록된 아이디입니다.");
					} else{
						$("#id").css("background-color", "green");
						$("#resultMSG").css("color", "green")
						.css("font-size", "15pt")
						.text("가입할 수 있습니다.");
					}
					//$("#resultMSG").text("data: " + data);
				}, //통신에 성공했을 때 이 실행문을 실행합니다.
				error: function(request, status, error){
					$("#resultMSG").text("오류가 발생했습니다. 가입할 수 없습니다.");
					//통신에 실패했을 때 에러는 3개가 들어옵니다.
				} //통신에 실패했을 때 이 실행문을 실행합니다.
			})*/
			} //부모로 인식하는 기준은 그냥 바로 위의 div 입니다.
			//예를 들어서 cdel의 부모는 바로 위의 div class인 cname입니다.
			//html을 쓰면 alert 창에 이상한 값이 뜹니다.
			//siblings는 부모인 cname의 형제인 cid를 찾아서 거기의 text를 뽑아서 출력하란 코드입니다.
			//5출력됩니다.
			//cno 변수에 cid의 값을 넣고, cdel을 클릭하면 
			//"./cdel?bno=${dto.bno}&cno=" + cno(이 cno는 let cno의 그 cno)로 가도록 합니다.
					
		});
		//댓글 수정 버튼 만듭니다. = 반드시 로그인하고 자신의 글인지 확인하는 검사 구문이 필요합니다.
		
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
									<div class="cname">
									${c.m_name }(${c.m_id })
									<c:if test = "${sessionScope.mid ne null && sessionScope.mid eq c.m_id}">
										<img alt ="" src="./img/edit.png" class="cedit" onclick="cedit()">&nbsp; 
										<img alt ="" src="./img/delete.png" class="cdel" onclick="cdel1(${c.c_no })">
										<!-- 다른 함수쓰려고 onclick의 이름을 cdel -> cdel1으로 바꾸고 새로 클래스를 
										만들었습니다. -->
									</c:if>
									</div>
									<div class="cdate">${c.b_date }</div>
									<div class="cid">${c.c_no }</div>
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