<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=mpvl2mrnbq"></script>
</head>
<body>
    <h1>MAIN</h1>
    <h3>관리자 : ${sessionScope.login.id}</h3>
    <a href="/logout"><input type="button" value="[로그아웃]" /></a><br/>
    <input type="search" id="search" />
    <input type="button" id="button_search" value="[검색]" />
    <div id="table">
        <table border="1">
            <tr>
                <th>NO</th>
                <th>관리번호</th>
            </tr>
            <c:forEach items="${parasolList}" var="parasol" varStatus="index">
                <tr>
                    <td style="text-align: center">${index.count}</td>
                    <td><input type="button" id="button_move_map" value="${parasol.managementNo}" /></td>
                    <input type="hidden" id="latitude${index.count}" value="${parasol.latitude}" />
                    <input type="hidden" id="longitude${index.count}" value="${parasol.longitude}" />
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="map" style="width:50%;height:500px;"></div>
    <script>
        var mapOptions = { // 지도 옵션 중심 설정
            center: new naver.maps.LatLng(37.4420792, 127.1363692),
            zoom: 10
        };

        var map = new naver.maps.Map('map', mapOptions);

        for (var i = 0; i < ${parasolList.size()}; i++) {

            console.log(document.getElementById("latitude" + (i + 1)).value);

            new naver.maps.Marker({ // 마커 표시
                position: new naver.maps.LatLng(document.getElementById("latitude" + (i + 1)).value, document.getElementById("longitude" + (i + 1)).value),
                map: map
            });
        }

        document.getElementById("button_search").addEventListener("click", search, false);

        function search() {
            xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.open("GET", "/parasol?managementNo=" + document.getElementById("search").value, true);
            xmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");

            xmlHttpRequest.send();

            xmlHttpRequest.onreadystatechange = getData;
        }

        function getData() {
            if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
                var dataToJson = JSON.parse(xmlHttpRequest.responseText);
                console.log(dataToJson);
            }

            if (dataToJson != null) {
                if (dataToJson.length > 0) {
                    var script = "<table border='1'><tr><th>INDEX</th><th>관리번호</th></tr>";

                    for (var i = 0; i < dataToJson.length; i++) {
                        script += "<tr><td>" + (i + 1) + "</td><td><a href = /parasol/"
                            + dataToJson[i].id +">" + dataToJson[i].managementNo + "</a></td>";
                    }

                    script += "</table>";

                    document.getElementById("table").innerHTML = script;
                } else {
                    document.getElementById("table").innerHTML = "<h1>검색 결과가 없습니다.</h1>";
                }
            }
        }
    </script>
</body>
</html>