<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
        <script src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=mpvl2mrnbq"></script>

        <title>공공 파라솔 관리 시스템 - 메인</title>
    </head>
    <body style="font-family: 'Noto Sans KR', sans-serif;">
        <!-- WRAPPER
        =====================================================================================================================-->
        <div class="ts-page-wrapper ts-homepage ts-full-screen-page" id="page-top">
            <!--HEADER **************************************************************************************************-->
            <header id="ts-header" class="navbar-dark">
                <!--PRIMARY NAVIGATION
                =============================================================================================================-->
                <nav id="ts-primary-navigation" class="navbar navbar-expand-md navbar-light">
                    <div class="container">
                        <!--Brand Logo-->
                        <p style="font-size: 2.5em; font-weight : 700; color: white" >공공 파라솔 관리 시스템</p>
                        <!--Collapsing Navigation-->
                        <div class="collapse navbar-collapse" id="navbarPrimary">
                            <!--RIGHT NAVIGATION MAIN LEVEL
                            =================================================================================================-->
                            <ul class="navbar-nav ml-auto">
                                <!--LOGOUT (Main level)
                                =============================================================================================-->
                                <li class="nav-item">
                                    <a class="btn btn-outline-secondary btn-sm d-block d-sm-inline-block mb-2 mb-sm-0" style="font-size: 1.5em" href="/logout">로그아웃</a>
                                </li>
                            </ul>
                            <!--end Right navigation-->
                        </div>
                        <!--end navbar-collapse-->
                    </div>
                    <!--end container-->
                </nav>
                <!--end #ts-primary-navigation.navbar-->
            </header>
            <!-- HERO MAP
            =================================================================================================================-->
            <section id="ts-hero" class=" mb-0">
                <!--Fullscreen mode-->
                <div class="ts-full-screen d-flex flex-column">
                    <!-- FORM
                    =========================================================================================================-->
                    <section class="ts-shadow__sm ts-z-index__2 ts-bg-light">
                        <!--Collapse Button-->
                        <div class="position-absolute w-100 ts-bottom__0 ts-z-index__1 text-center ts-h-0">
                            <button type="button" class="ts-circle p-3 bg-white ts-shadow__sm border-0 ts-push-up__50 mt-2" data-toggle="collapse" data-target="#form-collapse">
                                <i class="fa fa-chevron-up ts-text-color-primary ts-visible-on-uncollapsed"></i>
                                <i class="fa fa-chevron-down ts-text-color-primary ts-visible-on-collapsed"></i>
                            </button>
                        </div>
                        <!--검색-->
                        <div id="form-collapse" class="collapse ts-xs-hide-collapse show">
                            <div class="ts-form mb-0 d-flex flex-column flex-sm-row py-2 pl-2 pr-3">
                                <!--Keyword-->
                                <div class="form-group m-1 w-100">
                                    <input type="text" class="form-control" id="keyword" name="managementNo" placeholder="관리번호">
                                </div>
                                <!--Category-->
                                <div class="form-group m-1 w-100">
                                    <select class="custom-select" id="type" name="active">
                                        <option value="X">전체</option>
                                        <option value="Y">활성</option>
                                        <option value="N">비활성</option>
                                    </select>
                                </div>
                                <!--Submit button-->
                                <div class="form-group m-1 ml-auto">
                                    <button type="button" class="btn btn-primary" id="search-btn">검색</button>
                                </div>
                            </div>
                        </div>
                        <!--end Form-->
                    </section>
                    <!--end ts-form__grid-->
                    <!-- RESULTS & MAP
                    =========================================================================================================-->
                    <div class="d-flex h-100">
                        <!-- RESULTS LEFT
                        =====================================================================================================-->
                        <div class="ts-results__vertical ts-results__vertical-list ts-shadow__sm scrollbar-inner bg-white">
                            <!--Results wrapper-->
                            <section id="ts-results">
                                <div id="drawResult"  class="ts-results-wrapper"></div>
                            </section>
                        </div>
                        <!--end ts-results-vertical-->
                        <!-- MAP
                        =====================================================================================================-->
                        <div class="ts-map w-100">
                            <div class="ts-map w-100">
                                <div id="map" class="h-100"></div>
                            </div>
                        </div>
                    </div>
                    <!--end d-flex h-100-->
                </div>
                <!--end full-screen-->
            </section>
            <!--end ts-hero-->
        </div>
        <!--end page-->
        <script>
            search();

            let dataToJson;

            let mapOptions = {
                zoom: 9
            };

            let map = new naver.maps.Map('map', mapOptions);

            let markers = [];
            let infowindows = [];

            function search() {
                searchXmlHttpRequest = new XMLHttpRequest();
                searchXmlHttpRequest.open("GET", "/parasol?managementNo=" + document.getElementById("keyword").value + "&active=" + document.getElementById("type").value, true);
                searchXmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");
                searchXmlHttpRequest.send();
                searchXmlHttpRequest.onreadystatechange = getData;
            }

            function getData() {
                if (searchXmlHttpRequest.readyState == 4 && searchXmlHttpRequest.status == 200) {
                    dataToJson = JSON.parse(searchXmlHttpRequest.responseText);
                }

                if (markers.length > 0) {
                    for (var i = 0; i < markers.length; i ++) {
                        markers[i].setMap(null);
                        infowindows[i].setMap(null);
                    }
                    infowindows = [];
                    markers = [];
                }

                if (dataToJson != null) {
                    if (dataToJson.length > 0) {
                        var script = "";

                        for (var i = 0; i < dataToJson.length; i++) {
                            let id = dataToJson[i].id;
                            let active = dataToJson[i].active;
                            let managementNo = dataToJson[i].managementNo;
                            let status = dataToJson[i].status;
                            let temperature = dataToJson[i].temperature;
                            let dateTime = dataToJson[i].dateTime;
                            let latitude = dataToJson[i].latitude;
                            let longitude = dataToJson[i].longitude;
                            let activeKr = "";
                            let action;
                            let color;

                            if (status == "접힘" || status == null) {
                                action = "U";
                            } else {
                                action = "F";
                            }

                            if (active == "Y") {
                                activeKr = "활성"
                            } else {
                                activeKr = "비활성"
                                color = "text-danger";
                            }

                            script += '<div class="ts-result-link card ts-item ts-card ts-result" data-ts-id="6" data-ts-ln="5">'
                                    + '    <a href="javascript:void(0);" onclick="moveMap(' + latitude + ', ' + longitude + ', ' + i + ')">'
                                    + '        <input type="hidden" id="id' + i + '" value="' + id + '" />'
                                    + '        <input type="hidden" id="active' + i + '" value="' + active + '" />'
                                    + '        <input type="hidden" id="managementNo' + i + '" value="' + managementNo + '" />'
                                    + '        <input type="hidden" id="status' + i + '" value="' + status + '" />'
                                    + '        <input type="hidden" id="temperature' + i + '" value="' + temperature + '" />'
                                    + '        <input type="hidden" id="dateTime' + i + '" value="' + dateTime + '" />'
                                    + '        <input type="hidden" id="latitude' + i + '" value="' + latitude + '" />'
                                    + '        <input type="hidden" id="longitude' + i + '" value="' + longitude + '" />'
                                    + '        <input type="hidden" id="activeKr' + i + '" value="' + activeKr + '" />'
                                    + '        <input type="hidden" id="action' + i + '" value="' + action + '" />'
                                    + '        <div class="card-body">'
                                    + '            <figure class="ts-item__info">'
                                    + '                <h3>' + managementNo + '</h3>'
                                    + '                <aside class="' + color + '" style="font-size: 1em">'
                                    + '                    <i class="fa fa-map-marker mr-2"></i>'
                                    +                      activeKr
                                    + '                </aside>'
                                    + '            </figure>'
                                    + '        </div>'
                                    + '    </a>'
                                    + '</div>';
                        }
                        document.getElementById("drawResult").innerHTML = script;

                        for (var i = 0; i < dataToJson.length; i++) {
                            let statusKr;
                            let color;

                            if (document.getElementById("active" + i).value == "N") {
                                color = "rgb(234,9,9)";
                            } else {
                                color = "rgb(5,148,252)";
                            }

                            if (document.getElementById("status" + i).value == "펼침") {
                                statusKr = "접기";
                            } else {
                                statusKr = "펼치기";
                            }

                            var marker = new naver.maps.Marker({
                                position: new naver.maps.LatLng(document.getElementById("latitude" + i).value, document.getElementById("longitude" + i).value),
                                map: map,
                                animation: naver.maps.Animation.DROP
                            });

                            var contentString = [
                                  '<section id="basic-information" style="margin: auto; width: fit-content" class="mb-1 pl-0">'
                                + '    <div class="mb-2 card ts-item ts-card ts-result" data-toggle="tooltip" data-placement="right" title="상세 정보 보기">'
                                + '        <a style="text-align: center; width: 100%; font-size: 1.5em" href="/parasol/' + document.getElementById("id" + i).value + '">' + document.getElementById("managementNo" + i).value + '</a>'
                                + '    </div>'
                                + '    <div style="text-align: center; margin: auto" class="row">'
                                + '        <div class="col-sm-4">'
                                + '            <label>활성</label>'
                                + '            <p>' + document.getElementById("active" + i).value + '</p>'
                                + '        </div>'
                                + '        <div class="col-sm-4">'
                                + '            <label>상태</label>'
                                + '            <p>' + document.getElementById("status" + i).value + '</p>'
                                + '        </div>'
                                + '        <div class="col-sm-4">'
                                + '            <label>온도</label>'
                                + '            <p>' + document.getElementById("temperature" + i).value + '℃</p>'
                                + '        </div>'
                                + '        <div class="col-sm-12 mb-0 mb-sm-0 btn-sm d-block d-sm-inline-block">'
                                + '            <label>일시</label>'
                                + '            <p>' + document.getElementById("dateTime" + i).value + '</p>'
                                + '        </div>'
                                + '        <div class="col-sm-12 mb-1">'
                                + '           <a href="javascript:void(0);" id="actionButton" onclick="sendAction(document.getElementById(`id' + i + '`).value, document.getElementById(`action' + i + '`).value, ' + i + ');" class="btn btn-primary">' + statusKr + '</a>'
                                + '        </div>'
                                + '    </div>'
                                + '</section>'
                            ].join('');

                            var infowindow = new naver.maps.InfoWindow({
                                content: contentString,
                                maxWidth: 200,
                                backgroundColor: "rgb(214,250,223)",
                                borderColor: color,
                                borderWidth: 3,
                                anchorSize: new naver.maps.Size(10, 10),
                                anchorSkew: true,
                                anchorColor: "rgb(214,250,223)",
                                pixelOffset: new naver.maps.Point(20, -20)
                            });

                            naver.maps.Event.addListener(marker, 'click', markerClick(i));

                            markers.push(marker);
                            infowindows.push(infowindow);
                        }
                    } else {
                        document.getElementById("drawResult").innerHTML = '<div class="ts-result-link " data-ts-id="6" data-ts-ln="5">'
                                                                        + '    <p class="card ts-item ts-card ts-result">'
                                                                        + '        <div class="card-body">'
                                                                        + '            <figure class="ts-item__info">'
                                                                        + '                <h4 class="text-center">검색 결과가 없습니다</h4>'
                                                                        + '            </figure>'
                                                                        + '        </div>'
                                                                        + '    </p>'
                                                                        + '</div>';
                    }
                }
            }

            function sendAction(sendId, action, index) {
                indexI = index;
                sendIdI = sendId;

                document.getElementById("actionButton").setAttribute('class', 'btn btn-outline-secondary btn-sm disabled');
                document.getElementById("actionButton").innerText = "동작중";

                actionXmlHttpRequest = new XMLHttpRequest();

                if (action == "F") {
                    actionXmlHttpRequest.open("POST", "/control/F", true);
                } else {
                    actionXmlHttpRequest.open("POST", "/control/U", true);
                }
                actionXmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");

                actionXmlHttpRequest.ontimeout = function () {
                    actionXmlHttpRequest.send('{"id" : "' + sendId + '"}');
                }

                actionXmlHttpRequest.send('{"id" : "' + sendId + '"}');
                actionXmlHttpRequest.onreadystatechange = responseBysendAction;
            }

            function responseBysendAction() {
                if (actionXmlHttpRequest.readyState == 4 && actionXmlHttpRequest.status == 200) {
                    code = JSON.parse(actionXmlHttpRequest.responseText);
                    console.log(code);

                    StatusXmlHttpRequest = new XMLHttpRequest();
                    StatusXmlHttpRequest.open("GET", "/status/" + sendIdI, true);
                    StatusXmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");
                    StatusXmlHttpRequest.send();
                    StatusXmlHttpRequest.onreadystatechange = function () {
                        if (StatusXmlHttpRequest.readyState == 4 && StatusXmlHttpRequest.status == 200) {
                            status = StatusXmlHttpRequest.responseText;
                            console.log(status);

                            document.getElementById("status" + indexI).setAttribute('value', status);

                            if (status == "펼침") {
                                document.getElementById("action" + indexI).setAttribute('value', "F");
                                document.getElementById("actionButton").innerText = "접기";
                            } else {
                                document.getElementById("action" + indexI).setAttribute('value', "U");
                                document.getElementById("actionButton").innerText = "펼치기";
                            }

                            $('#actionButton').unbind('onclick', false);
                            document.getElementById("actionButton").setAttribute('class', "btn btn-primary");
                        }
                    }
                }
            }

            function markerClick(index) {
                return function (e) {
                    if (markers[index].getAnimation() != null) {
                        markers[index].setAnimation(null);
                    } else {
                        for (var i = 0; i < markers.length; i++) {
                            markers[i].setAnimation(null);
                        }
                        markers[index].setAnimation(naver.maps.Animation.BOUNCE);
                    }

                    if (infowindows[index].getMap()) {
                        infowindows[index].close();
                    } else {
                        infowindows[index].open(map, markers[index]);
                    }
                }
            }

            function moveMap(latitude, longitude, index) {
                map.setCenter(new naver.maps.LatLng(latitude, longitude));
                map.setZoom(18);

                for (var i = 0; i < markers.length; i++) {
                    if (markers[i].getAnimation() != null) {
                        markers[i].setAnimation(null);
                    }
                }

                markers[index].setAnimation(naver.maps.Animation.BOUNCE);
                infowindows[index].open(map, markers[index]);
            }

            document.getElementById("search-btn").addEventListener("click", search, false);
            document.getElementById("")
        </script>

        <%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
    </body>
</html>
