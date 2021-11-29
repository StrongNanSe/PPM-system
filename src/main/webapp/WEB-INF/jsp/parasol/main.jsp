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
        <form action="/parasol" +  method="GET">
            <input type="search" style="width: 70%" name="managementNo" />
            <input type="submit" value="[검색]" />
        </form>
    </div>
    <div id="table">
        <table border="1" style="width: 210px">
            <tr>
                <th>NO</th>
                <th>관리번호</th>
            </tr>
            <c:choose>
                <c:when test="${markList.size() > 0}">
                    <c:forEach items="${markList}" var="mark" varStatus="index">
                        <tr>
                            <td style="text-align: right">${index.count}</td>
                            <td><input type="button" id="button_move_map${index.count}" style="width: inherit" value="${mark.managementNo}" /></td>
                            <script>
                                document.getElementById("button_move_map${index.count}").addEventListener("click", function () {
                                    let latitude = ${mark.latitude};
                                    let longitude = ${mark.longitude};

                                    moveMap(latitude, longitude);
                                });
                            </script>
                            <input type="hidden" id="id${index.count}" value="${mark.id}" />
                            <input type="hidden" id="managementNo${index.count}" value="${mark.managementNo}" />
                            <input type="hidden" id="latitude${index.count}" value="${mark.latitude}" />
                            <input type="hidden" id="longitude${index.count}" value="${mark.longitude}" />
                            <input type="hidden" id="agentIpAdress${index.count}" value="${mark.agentIpAddress}" />
                            <input type="hidden" id="active${index.count}" value="${mark.active}" />
                            <input type="hidden" id="status${index.count}" value="${mark.status}" />
                            <input type="hidden" id="temperature${index.count}" value="${mark.temperature}" />
                            <input type="hidden" id="dateTime${index.count}" value="${mark.dateTime}" />
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>검색 결과가 없습니다.</p>
                </c:otherwise>
            </c:choose>

        </table>
    </div>
    <div id="map"></div>
    <script>
        let mapOptions = {
            zoom: 10
        };

        let map = new naver.maps.Map('map', mapOptions);

        let markers = [];
        let infowindows = [];

        for (var i = 0; i < ${markList.size()}; i++) {

            let id = document.getElementById("id" + (i + 1)).value;
            let managementNo = document.getElementById("managementNo" + (i + 1)).value;
            let active = document.getElementById("active" + (i + 1)).value;
            let status = document.getElementById("status" + (i + 1)).value;
            let temperature = document.getElementById("temperature" + (i + 1)).value;
            let dateTime = document.getElementById("dateTime" + (i + 1)).value;

            var marker = new naver.maps.Marker({
                position: new naver.maps.LatLng(document.getElementById("latitude" + (i + 1)).value, document.getElementById("longitude" + (i + 1)).value),
                map: map
            });

            var infowindow = new naver.maps.InfoWindow({
                content: '<div class="iw_inner">' +
                    '<a href="/parasol/' + id + '">' + managementNo + '</a>' +
                    '<p>활성: ' + active + '</p>' +
                    '<p>상태: ' + status + '</p>' +
                    '<p>온도: ' + temperature + '</p>' +
                    '<p>일시: ' + dateTime + '</p>' +
                    '</div>'
            });

            markers.push(marker);
            infowindows.push(infowindow);
        }

        function markerClick(index) {
            return function(e) {
                if (infowindows[index].getMap()) {
                    infowindows[index].close();
                } else {
                    infowindows[index].open(map, markers[index]);
                }
            }
        }

        for (var i = 0; i < markers.length; i++) {
            naver.maps.Event.addListener(markers[i], 'click', markerClick(i));
        }

        function moveMap(latitude, longitude) {
            map.setCenter(new naver.maps.LatLng(latitude, longitude));
            map.setZoom(18);
        }
    </script>
</body>
</html>