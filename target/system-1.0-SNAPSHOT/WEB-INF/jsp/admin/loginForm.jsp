<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/login" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"/></td>
			</tr>
		</table>
		<c:if test="${adminMatch eq false}">
			<h4 style="color:red">아이디 또는 비밀번호가 일치 하지 않습니다.</h4>
		</c:if>
		<input type="submit" style="text-align: center" value="[로그인]" />
	</form>
</body>
</html>