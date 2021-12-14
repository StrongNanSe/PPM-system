<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

        <title>공공 파라소 관리 시스템 - 상태 정보</title>
    </head>
    <body style="font-family: 'Noto Sans KR', sans-serif;">
        <div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
            <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
            <main id="ts-main">
                <section id="page-title" style="height: 40px">
                    <div class="container">
                        <div class="ts-title mb-0">
                            <div class="row">
                                <div class="ts-title mb-0 col-sm-10">
                                    <h1 style="font-weight : 400">상태 정보</h1>
                                    <label style="font-weight : 200; font-size: 1.7em">${parasol.managementNo}</label>
                                </div>
                                <div class="ts-title mb-2 col-sm-2" style="text-align: center">
                                    <a class="card btn-outline-primary btn-sm m-1 px-3" style="font-size: 1.3em" href="/parasol/${parasol.id}">상세 정보 조회</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <div id="drawInfo" style="text-align: center"></div>
            </main>
        </div>

        <script>
            pageOver(1);

            function pageOver(pageNo) {
                xmlHttpRequest = new XMLHttpRequest();
                xmlHttpRequest.open("POST", "/status/${parasol.id}", true);
                xmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");

                xmlHttpRequest.send('{"pageNo" : "' + pageNo + '"}');

                console.log('{"pageNo" : "' + pageNo + '"}');

                xmlHttpRequest.onreadystatechange = getData;
            }

            function getData() {
                let drawData;

                if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
                    drawData = xmlHttpRequest.responseText;

                    if (drawData != null) {
                        document.getElementById("drawInfo").innerHTML = drawData;
                    }
                }
            }
        </script>

        <%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
    </body>
</html>