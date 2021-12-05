<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

        <title>공공 파라소 관리 시스템 - 상태 정보</title>
    </head>
    <body style="font-family: 'Noto Sans KR', sans-serif;">
        <!-- WRAPPER
            =================================================================================================================-->
        <div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
            <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
            <!--*********************************************************************************************************-->
            <!-- MAIN ***************************************************************************************************-->
            <!--*********************************************************************************************************-->
            <main id="ts-main">
                <!--PAGE TITLE
                =============================================================================================================-->
                <section id="page-title" style="height: 40px">
                    <div class="container h-50">
                        <div class="ts-title mb-0">
                            <div class="row">
                                <div class="ts-title mb-0 col-sm-10">
                                    <h1 style="font-weight : 500">상태 정보</h1>
                                    <label style="font-weight : 200; font-size: 1.5em">${parasol.managementNo}</label>
                                </div>
                                <div class="ts-title mb-2 col-sm-2" style="text-align: center">
                                    <a class="card btn-outline-primary btn-sm m-1 px-3" href="/parasol/${parasol.id}">상세 정보 조회</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <!--COMPARED PROPERTIES
                =============================================================================================================-->
                <section id="compared-properties">
                    <div class="container">
                        <!--COMPARE TABLE
                        =====================================================================================================-->
                        <div class="ts-compare-items-table">
                            <!--DETAILS
                            =================================================================================================-->
                            <section id="details">
                                <div class="row">
                                    <div class="col ts-row-title">NO</div>
                                    <div class="col ts-row-title">상태</div>
                                    <div class="col ts-row-title">온도</div>
                                    <div class="col ts-row-title">일시</div>
                                </div>
                                <c:forEach items="${parasolStatusList}" var="parasolStatus" varStatus="index">
                                    <div class="row">
                                        <div class="col text-left">${parasolStatusList.size() - index.index}</div>
                                        <div class="col text-left">${parasolStatus.status}</div>
                                        <div class="col text-left">${parasolStatus.temperature}</div>
                                        <div class="col text-left">${parasolStatus.dateTime}</div>
                                    </div>
                                </c:forEach>
                            </section>
                        </div>
                        <!--end ts-compare-items-table-->
                    </div>
                    <!--end container-->
                </section>
            </main>
            <!--end #ts-main-->
        </div>
        <!--end page-->

        <%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
    </body>
</html>