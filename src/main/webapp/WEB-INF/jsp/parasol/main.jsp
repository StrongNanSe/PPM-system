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
            <c:forEach items="${parasolList}" var="parasol" varStatus="index">
                <tr>
                    <td style="text-align: right">${index.count}</td>
                    <td><input type="button" id="button_move_map${index.count}" style="width: inherit" value="${parasol.managementNo}" /></td>
                    <script>
                        document.getElementById("button_move_map${index.count}").addEventListener("click", function () {
                            let latitude = ${parasol.latitude};
                            let longitude = ${parasol.longitude};

                            moveMap(latitude, longitude);
                        });

                        var contentString = [
                            '<div class="iw_inner">',
                            '   <a href="/parasol/${parasol.id}">${parasol.managementNo}</a>',
                            '</div>'
                        ].join('');
                    </script>
                    <input type="hidden" id="latitude${index.count}" value="${parasol.latitude}" />
                    <input type="hidden" id="longitude${index.count}" value="${parasol.longitude}" />
                    <input type="hidden" id="id${index.count}" value="${parasol.id}" />
                    <input type="hidden" id="managementNo${index.count}" value="${parasol.managementNo}" />
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="map"></div>
    <script>
        let mapOptions = { // 지도 옵션 설정
            zoom: 10
        };

        let map = new naver.maps.Map('map', mapOptions);

        let markers = [];
        let infowindows = [];

        for (var i = 0; i < ${parasolList.size()}; i++) {

            let id = document.getElementById("id" + (i + 1)).value;
            let managementNo = document.getElementById("managementNo" + (i + 1)).value;

            var marker = new naver.maps.Marker({ // 마커 표시
                position: new naver.maps.LatLng(document.getElementById("latitude" + (i + 1)).value, document.getElementById("longitude" + (i + 1)).value),
                map: map
            });

            var infowindow = new naver.maps.InfoWindow({
                content: '<div class="iw_inner"><a href="/parasol/' + id + '">' + managementNo + '</a></div>'
            });

            markers.push(marker);
            infowindows.push(infowindow);
        }

        function markerClick(index) {
            return function(e) {
                if (infowindows[index].getMap()) {
                    infowindows[index].close();
                } else {
                    console.log(infowindows[index]);
                    infowindows[index].open(map, markers[index]);
                }
            }
        }

        for (var i = 0; i < markers.length; i++) {
            naver.maps.Event.addListener(markers[i], 'click', markerClick(i));
        }

        function moveMap(latitude, longitude) {
            console.log(latitude, longitude);
            map.setCenter(new naver.maps.LatLng(latitude, longitude));
            map.setZoom(18);
        }
    </script>
</body>
</html>