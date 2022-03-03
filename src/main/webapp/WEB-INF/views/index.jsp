<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${loginId == null }">
			<form action="/member/login" method="post">
				<table border=1 align=center>
					<tr>
						<th colspan=2>로그인
					</tr>
					<tr>
						<td>아이디 :
						<td><input type="text" placeholder="아이디 입력" name=id>
					</tr>
					<tr>
						<td>비밀번호 :
						<td><input type="text" placeholder="비밀번호 입력" name=pw>
					</tr>
					<tr>
						<td colspan=2 align=center>
							<button>로그인</button> <input type="button" value="회원가입" id="signUp">
						</td>
					</tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<table border=1 align=center>
				<tr>
					<th colspan=2>게시판 페이지
				</tr>
				<tr>
					<td><input type=button value="게시판 이동" id="toBoard">
					<td><input type=button value="로그아웃" id="logout">
				</tr>
			</table>	
		</c:otherwise>
	</c:choose>



	<script>
		$("#signUp").on("click", function() {
			location.href = "/member/signUp";
		})
		
		$("#logout").on("click",function(){
			if(confirm("로그아웃 하시겠습니까?")){
				location.href="/member/logout";
			}
		})
		
		$("#toBoard").on("click",function(){
			location.href="/board/boardList?cpage=1";
		})
	</script>
</body>
</html>