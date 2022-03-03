<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<form action="/member/signIn" method="post">
		<table border=1 align=center>
			<tr>
				<th colspan=2>회원가입
			</tr>
			<tr>
				<td>아이디 :
				<td><input type="text" placeholder="아이디 입력" name="id">
			</tr>
			<tr>
				<td>비밀번호 :
				<td><input type="text" placeholder="비밀번호 입력" name="pw">
			</tr>
			<tr>
				<td>이름 :
				<td><input type="text" placeholder="이름 입력" name="name">
			</tr>
			<tr>
				<td>전화번호 :
				<td><input type="text" placeholder="전화번호 입력" name="contact">
			</tr>
			<tr>
				<td colspan=2 align=center>
					<button>회원가입</button> <input type="reset" value="다시 작성">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>