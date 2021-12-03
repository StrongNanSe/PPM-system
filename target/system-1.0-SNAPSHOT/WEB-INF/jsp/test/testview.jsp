<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

        <title>공공 파라소 관리 시스템 - 상세 정보</title>
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
                    =========================================================================================================-->
                <section id="page-title">
                    <div class="container">
                        <div class="ts-title">
                            <h1 style="font-weight : 500">상세 정보</h1>
                        </div>
                    </div>
                </section>
                <!--AGENT INFO
                    =========================================================================================================-->
                <section id="agent-info">
                    <div class="container">
                        <!--Box-->
                        <div class="ts-box ts-has-talk-arrow">
                            <!--Row-->
                            <div class="row">
                                <!--Description-->
                                <div class="col-md-12">
                                    <div class="py-4">
                                        <section id="basic-information" class="mb-3 pl-3">
                                            <!--Title-->
                                            <div class="ts-title mb-2">
                                                <label>관리번호</label>
                                                <h3 class="mb-1 border-bottom">${parasol.managementNo}</h3>
                                            </div>
                                            <!--Row-->
                                            <div class="row">
                                                <!--관리기관-->
                                                <div class="col-sm-4">
                                                    <label>관리기관</label>
                                                    <p>${parasol.managementAgency}</p>
                                                </div>
                                                <!--설치일자-->
                                                <div class="col-sm-4">
                                                    <label>설치일자</label>
                                                    <p>${parasol.installDate}</p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label>에이전트 IP 주소</label>
                                                    <p>${parasol.agentIpAddress}</p>
                                                </div>
                                                <!--위도-->
                                                <div class="col-sm-4">
                                                    <label>위도</label>
                                                    <p>${parasol.latitude}</p>
                                                </div>
                                                <!--경도-->
                                                <div class="col-sm-4">
                                                    <label>경도</label>
                                                    <p>${parasol.longitude}</p>
                                                </div>
                                                <div class="col-sm-3">
                                                    <a href="javascript:void(0);" onclick="editAddressPopup()" class="card ts-item ts-card ts-result">
                                                        <label>설치주소</label>
                                                        <p>${parasol.installAddress}</p>
                                                    </a>
                                                </div>
                                                <!--end col-md-4-->
                                                <!--Type Select-->
                                            </div>
                                            <!--end row-->
                                        </section>
                                        <!--end basic-info-->
                                    </div>
                                    <!--end py-4-->
                                </div>
                                <!--end Description-->
                            </div>
                            <!--end Row-->
                        </div>
                        <!--end tx-box-->
                    </div>
                    <!--end container-->
                </section>
                <!--end #agent-info-->
            </main>
            <!--end #ts-main-->
        </div>
        <!--end page-->

        <script>
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
                    if (confirm("비활성 상태로 변경하시겠습니까?")) {
                        sendEdit({"id":"${parasol.id}","active":"N"});
                    }
                } else {
                    if (confirm("활성 상태로 변경하시겠습니까?")) {
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

        <%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
    </body>
</html>