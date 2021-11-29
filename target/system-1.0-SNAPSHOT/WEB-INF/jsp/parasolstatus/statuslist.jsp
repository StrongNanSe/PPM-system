<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상태 정보</title>
</head>
<body>
    <h1>상태 정보</h1>
    <a href="/parasol"><input type="button" value="[메인]"></a>
    <a href="/logout"><input type="button" value="[로그아웃]" /></a>
    <table border="1" >
        <tr>
            <th>NO</th>
            <th>상태</th>
            <th>온도</th>
            <th>일시</th>
        </tr>
        <c:forEach items="${parasolStatusList}" var="parasolStatus" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td>${parasolStatus.status}</td>
                <td>${parasolStatus.temperature}</td>
                <td>${parasolStatus.dateTime}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>