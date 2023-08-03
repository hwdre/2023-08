<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/join.css?version=0.1">
<script src="./js/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#idCheck").click(function(){
		let id = $("#id").val();
		//console.log(id);
		//console.log(id.length);
		if(id == "" || id.length < 5) {
			$("#resultMSG").text("아이디는 다섯글자 이상이어야 합니다.");
			$("#resultMSG").css("color", "red").css("font-weigth", "bold")
			.css("font-size", "15pt");
		} else {
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
			})
		}
		return false; //멈추기
	});
});
	/* 2023-08-01 버전$("#resultForm").hide();
	$("#idCheck").click(function(){
		let id = $("#id").val();
		$("#resultMSG").css("font-weight", "bold");
		if(id == "" || id.length < 5){
			//alert("아이디는 다섯글자 이상이어야 합니다.");			
			$("#resultMSG").css("color", "red").css('font-size', '18pt').text("아이디는 다섯글자 이상이어야 합니다.");
			$("#id").focus();.css('color', 'black').css('background-color', 'red');
		} else {
			$.ajax({
				url: "./checkID", //checkID컨트롤러로 정보를 보냅니다.
				type: "post",
				data:{"id": id}, //checkID?id=superman 이런 형식으로 
						//checkID라는 컨트롤러로 갑니다.
				dataType: "html", //보낼 정보의 데이터타입이 뭔지 적었습니다.
				success: function(data){
					$("#resultMSG").text("data: " + data);
				}, //통신에 성공했을 때 이 실행문을 실행합니다.
				error: function(request, status, error){
					$("#resultMSG").text("error: " + error);
					//통신에 실패했을 때 에러는 3개가 들어옵니다.
					console.log(error);
				} //통신에 실패했을 때 이 실행문을 실행합니다.
				
			}); //이게 ajax 시작입니다. 컨트롤러로 보내는 것만 구현했습니다.
			
			$("#resultMSG").text("5글자 이상으로 들어왔습니다.");
			$("#resultMSG").css("color", "green");
		}
			return false;
		
	});
});*/
</script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="join-div">
		<form action="./join" method="post">
			<h1>회원가입</h1>
			<hr>
			<div class="label-row">
				<div class="label-name">아이디</div>
				<div class="label-in">
					<input type="text" name="id" id="id">
					<button id="idCheck">중복검사</button>
				</div>
			</div>
			<div class="label-row" style="height: 40px" id="resultForm">
				<div class="label-name"></div>
				<div class="label-in">
					<span id="resultMSG"></span>
				</div>
			</div>
			<div class="label-row">
				<div class="label-name">비밀번호</div>
				<div class="label-in">
					<input type="password" name="pw1"> <input type="password"
						name="pw2">
				</div>
			</div>
			<div class="label-row">
				<div class="label-name">이 름</div>
				<div class="label-in">
					<input type="text" name="name">
				</div>
			</div>
			<div class="label-row">
				<div class="label-name">주 소</div>
				<div class="label-in">
					<input type="text" name="addr">
				</div>
			</div>
			<div class="label-row">
				<div class="label-name">MBTI</div>
				<div class="label-in">
					<select name="mbti">
						<option value="0">선택하세요</option>
						<optgroup label="내향형">
							<option value="ISTJ">ISTJ</option>
							<option value="ISTP">ISTP</option>
							<option value="ISFJ">ISFJ</option>
							<option value="ISFP">ISFP</option>
							<option value="INTJ">INTJ</option>
							<option value="INTP">INTP</option>
							<option value="INFJ">INFJ</option>
							<option value="INFP">INFP</option>
						</optgroup>
						<optgroup label="외향형">
							<option value="ESTJ">ESTJ</option>
							<option value="ESTP">ESTP</option>
							<option value="ESFJ">ESFJ</option>
							<option value="ESFP">ESFP</option>
							<option value="ENTJ">ENTJ</option>
							<option value="ENTP">ENTP</option>
							<option value="ENFJ">ENFJ</option>
							<option value="ENFP">ENFP</option>
						</optgroup>
					</select>
				</div>
			</div>
			<div class="label-row">
				<div class="label-name">생년월일</div>
				<div class="label-in">
					<input type="date" name="birth">
				</div>
			</div>
			<div class="label-row">
				<div class="label-name">성별</div>
				<div class="label-in">
					<input type="radio" name="gender" id="m" value="1"> <label
						for="m">남자</label> <input type="radio" name="gender" id="f"
						value="0"> <label for="f">여자</label>
				</div>
			</div>
			<hr>
			<div class="label-row lrbtn">
				<button type="reset">취소</button>
				<button type="submit">가입하기</button>
			</div>
		</form>
	</div>





</body>
</html>