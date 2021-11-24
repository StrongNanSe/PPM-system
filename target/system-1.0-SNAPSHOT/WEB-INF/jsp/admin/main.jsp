<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
    <h1>TEST</h1>
    <h3>${admin.toString()}</h3>
    <a href="/logout"><input type="button" value="[로그아웃]" /></a>
</body>
</html>