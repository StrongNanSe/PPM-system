<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
    <h1>조회</h1>
    <a href="/parasol"><input type="button" value="[메인]"></a>
    <table border="1">
        <tr>
            <th>관리번호</th>
            <td>${parasol.managementNo}</td>
        </tr>
        <tr>
            <th>관리기관</th>
            <td>${parasol.managementAgency}</td>
        </tr>
        <tr>
            <th>설치일자</th>
            <td>${parasol.installDate}</td>
        </tr>
        <tr>
            <th>설치주소</th>
            <td>
                ${parasol.installAddress}
                <input type="button" value="[수정]" id="button_editAddress"/>
            </td>
        </tr>
        <tr>
            <th>위도</th>
            <td>${parasol.latitude}</td>
        </tr>
        <tr>
            <th>경도</th>
            <td>${parasol.longitude}</td>
        </tr>
        <tr>
            <th>에이전트 IP 주소</th>
            <td>${parasol.agentIpAddress}</td>
        </tr>
        <tr>
            <th>활성</th>
            <td>
                <c:choose>
                    <c:when test="${parasol.active eq 'Y'}">
                        <input type="button" value="[Y]" id="button-editActive" />
                    </c:when>
                    <c:otherwise>
                        <input type="button" value="[N]" id="button-editActive" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <a href="/status"><input type="button" value="[파라솔 상태 기록 보기]"></a>
</body>
<script>
    document.getElementById("button_editAddress").addEventListener("click", editAddressPopup, false);
    document.getElementById("button-editActive").addEventListener("click", editActivePopup, false);

    function editAddressPopup() {
        edit = prompt("주소 수정", "${parasol.installAddress}");

        if (edit == null) {
            alert("수정을 취소했습니다.");
        } else if (edit == "") {
            alert("공백은 입력할 수 없습니다.");
            editPopup();
        } else {
            if (confirm("(" + edit + ")로 수정 하시겠습니까?")) {
                sendEdit({"id":"${parasol.id}","installAddress":edit});
            }
        }
    }

    function editActivePopup() {
        if ("${parasol.active}" == "Y") {
            if (confirm("비활성 상태로 바꾸시겠습니까?")) {
                sendEdit({"id":"${parasol.id}","active":"N"});
            }
        } else {
            if (confirm("활성 상태로 바꾸시겠습니까?")) {
                sendEdit({"id":"${parasol.id}","active":"Y"});
            }
        }
    }

    function sendEdit(params) {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "/parasol");
        for(var key in params) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);
            form.appendChild(hiddenField);
        }
        document.body.appendChild(form);
        form.submit();
    }
</script>
</html>