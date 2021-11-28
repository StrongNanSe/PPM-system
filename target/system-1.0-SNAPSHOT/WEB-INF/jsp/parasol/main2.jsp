<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=mpvl2mrnbq"></script>
    <style>
        a.relative {
            position: relative;
            float: right;
            margin-right: 3%;
            margin-top: -2%;
        }

        #table {
            position: absolute;
            height: 500px;
            width: 230px;
            float: left;
            overflow: auto;
        }

        #map {
            height: 600px;
            width: 1200px;
            float: left;
            margin-left: 15%;
        }
    </style>
</head>
<body>
    <div id="top" style="width: 100%" >
        <h3>관리자 : ${sessionScope.login.id}</h3>
        <a href="/logout" class="relative"><input type="button" value="[로그아웃]" /></a>
    </div>
    <div style="width: 220px;">
        <input type="search" style="width: 70%" id="search" />
        <input type="button" id="button_search" value="[검색]" />
    </div>
    <div id="table">
        <table border="1" style="width: 210px">
            <tr>
                <th>NO</th>
                <th>관리번호</th>
            </tr>
            <c:forEach items="${parasolList}" var="parasol" varStatus="index">
                <tr>
                    <td style="text-align: right">${index.count}</td>
                    <td><input type="button" id="button_move_map${index.count}" style="width: inherit" value="${parasol.managementNo}" /></td>
                    <script>
                        document.getElementById("button_move_map${index.count}").addEventListener("click", function () {
                            let latitude = ${parasol.latitude};
                            let longitude = ${parasol.longitude};

                            console.log(latitude);

                            moveMap(latitude, longitude);
                        });
                    </script>
                    <input type="hidden" id="latitude${index.count}" value="${parasol.latitude}" />
                    <input type="hidden" id="longitude${index.count}" value="${parasol.longitude}" />
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="map"></div>
    <script>
        var mapOptions = { // 지도 옵션 설정
            zoom: 8
        };

        var map = new naver.maps.Map('map', mapOptions);

        var markers = [];

        for (var i = 0; i < ${parasolList.size()}; i++) {
            var marker = new naver.maps.Marker({ // 마커 표시
                position: new naver.maps.LatLng(document.getElementById("latitude" + (i + 1)).value, document.getElementById("longitude" + (i + 1)).value),
                map: map
            });

            markers[i] = marker;
        }

        function moveMap(latitude, longitude) {
            console.log(latitude, longitude);
            map.setCenter(new naver.maps.LatLng(latitude, longitude));
            map.setZoom(18);
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

            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(null);
            }

            if (dataToJson != null) {
                if (dataToJson.length > 0) {
                    var script = "<table border='1' style='width: 210px'><tr><th>NO</th><th>관리번호</th></tr>";

                    for (var i = 0; i < dataToJson.length; i++) {
                        script += "<tr><td style='text-align: right'>" + (i + 1) + "</td>"
                                    + "<td><input type='button' id='button_move_map" + (i + 1) + "' style='width: inherit' value= '" + dataToJson[i].managementNo + "' /></td>"
                                    + "<script>"
                                    + "document.getElementById('button_move_map" + (i + 1) + "').addEventListener('click', function() {"
                                    + "let latitude = " + dataToJson[i].latitude + ";"
                                    + "let longitude = " + dataToJson[i].longitude + ";"
                                    + "console.log(latitude);"
                                    + "moveMap(latitude, longitude);});"
                                    + "<\/script>"
                                    + "<input type='hidden' id='latitude" + (i + 1) + "' value='" + dataToJson[i].latitude + "'/>"
                                    + "<input type='hidden' id='longitude" + (i + 1) + "' value='" + dataToJson[i].longitude + "'/>"
                                    + "</tr>";

                        marker = new naver.maps.Marker({ // 마커 표시
                            position: new naver.maps.LatLng(dataToJson[i].latitude, dataToJson[i].longitude),
                            map: map
                        });

                        markers[i] = marker;
                    }

                    script += "</table>";

                    document.getElementById("table").innerHTML = script;
                } else {
                    document.getElementById("table").innerHTML = "<h3>검색 결과가 없습니다.</h3>";
                }
            }
        }
    </script>
</body>
</html>