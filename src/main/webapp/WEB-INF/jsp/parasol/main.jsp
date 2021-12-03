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
                        <p style="font-size: 2em; font-weight : 700; color: white" >공공 파라솔 관리 시스템</p>
                        <!--Collapsing Navigation-->
                        <div class="collapse navbar-collapse" id="navbarPrimary">
                            <!--RIGHT NAVIGATION MAIN LEVEL
                            =================================================================================================-->
                            <ul class="navbar-nav ml-auto">
                                <!--LOGOUT (Main level)
                                =============================================================================================-->
                                <li class="nav-item">
                                    <a class="btn btn-outline-secondary btn-sm d-block d-sm-inline-block mb-2 mb-sm-0" href="/logout">로그아웃</a>
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
                        <!--Form-->
                        <div id="form-collapse" class="collapse ts-xs-hide-collapse show">
                            <form action="/parasol" method="GET" class="ts-form mb-0 d-flex flex-column flex-sm-row py-2 pl-2 pr-3">
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
                                    <button type="submit" class="btn btn-primary" id="search-btn">검색</button>
                                </div>
                            </form>
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
                            <section id="ts-results" da >
                                <div class="ts-results-wrapper">
                                    <c:forEach items="${markList}" var="mark" varStatus="index">
                                        <div class="ts-result-link " data-ts-id="6" data-ts-ln="5">
                                            <a href="javascript:void(0);" onclick="moveMap(${mark.latitude}, ${mark.longitude})" class="card ts-item ts-card ts-result">
                                                <div class="card-body">
                                                    <figure class="ts-item__info">
                                                        <h4>${mark.managementNo}</h4>
                                                        <aside>
                                                            <i class="fa fa-map-marker mr-2"></i>
                                                            ${mark.active}
                                                        </aside>
                                                    </figure>
                                                </div>
                                            </a>
                                            <input type="hidden" id="id${index.count}" value="${mark.id}" />
                                            <input type="hidden" id="managementNo${index.count}" value="${mark.managementNo}" />
                                            <input type="hidden" id="latitude${index.count}" value="${mark.latitude}" />
                                            <input type="hidden" id="longitude${index.count}" value="${mark.longitude}" />
                                            <input type="hidden" id="agentIpAdress${index.count}" value="${mark.agentIpAddress}" />
                                            <input type="hidden" id="active${index.count}" value="${mark.active}" />
                                            <input type="hidden" id="status${index.count}" value="${mark.status}" />
                                            <input type="hidden" id="temperature${index.count}" value="${mark.temperature}" />
                                            <input type="hidden" id="dateTime${index.count}" value="${mark.dateTime}" />
                                        </div>
                                    </c:forEach>
                                </div>
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

                var HOME_PATH = window.HOME_PATH || '.';

                var marker = new naver.maps.Marker({
                    position: new naver.maps.LatLng(document.getElementById("latitude" + (i + 1)).value, document.getElementById("longitude" + (i + 1)).value),
                    map: map
                });

                if (status == "펼침") {
                    var contentString = [
                        '<div class="iw_inner">' +
                        '    <div margin: auto"><a class="nav-link" href="/parasol/' + id + '">' + managementNo + '</a></div>' +
                        '    <hr>' +
                        '    <p>활성 : ' + active + '</p>' +
                        '    <p>상태 : ' + status + '</p>' +
                        '    <p>온도 : ' + temperature + '</p>' +
                        '    <p>일시 : ' + dateTime + '</p>' +
                        '    <hr>' +
                        '    <div style="margin: auto">' +
                        '       <form style="width: fit-content; margin: auto" action="/control/F" method="POST">' +
                        '           <input type="hidden" name="id" value="' + id + '" />' +
                        '           <button type="submit" class="btn btn-primary ">접기</button>' +
                        '       </form>' +
                        '    </div>' +
                        '    <br>' +
                        '</div>'
                    ].join('');
                } else {
                    var contentString = [
                        '<div class="iw_inner">' +
                        '    <div margin: auto"><a class="nav-link" href="/parasol/' + id + '">' + managementNo + '</a></div>' +
                        '    <hr>' +
                        '    <p>활성 : ' + active + '</p>' +
                        '    <p>상태 : ' + status + '</p>' +
                        '    <p>온도 : ' + temperature + '</p>' +
                        '    <p>일시 : ' + dateTime + '</p>' +
                        '    <hr>' +
                        '    <div style="margin: auto">' +
                        '       <form style="width: fit-content; margin: auto" action="/control/U" method="POST">' +
                        '           <input type="hidden" name="id" value="' + id + '" />' +
                        '           <button type="submit" class="btn btn-primary">펼치기</button>' +
                        '       </form>' +
                        '    </div>' +
                        '    <br>' +
                        '</div>'
                    ].join('');
                }

                var infowindow = new naver.maps.InfoWindow({
                    content: contentString,
                    maxWidth: 200,
                    backgroundColor: "rgb(214,250,223)",
                    borderColor: "rgb(145,217,239)",
                    borderWidth: 3,
                    anchorSize: new naver.maps.Size(10, 10),
                    anchorSkew: true,
                    anchorColor: "rgb(214,250,223)",
                    pixelOffset: new naver.maps.Point(20, -20)
                });

                markers.push(marker);
                infowindows.push(infowindow);
            }

            function sendAction(index) {
                console.log(index);

                xmlHttpRequest = new XMLHttpRequest();

                if ((document.getElementById("action" + (i + 1)).value) == 접기) {
                    xmlHttpRequest.open("POST", "/control/F", true);
                } else {
                    xmlHttpRequest.open("POST", "/control/U", true);
                }

                xmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");
                xmlHttpRequest.send("id=" + document.getElementById("id" + (i + 1)).value);
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

        <%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
    </body>
</html>
