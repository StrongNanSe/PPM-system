<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

        <title>공공 파라소 관리 시스템 - 상세 정보</title>
    </head>
    <body style="font-family: 'Noto Sans KR', sans-serif;">
        <div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
            <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
            <main id="ts-main">
                <section id="page-title">
                    <div class="container">
                        <div class="ts-title mb-0">
                            <div class="row">
                                <div class="ts-title mb-0 col-sm-10">
                                    <h1 style="font-weight : 400">상세 정보</h1>
                                </div>
                                <div class="ts-title mb-2 col-sm-2" style="text-align: center">
                                    <a class="card btn-outline-primary btn-sm m-1 px-3" style="font-size: 1.3em" href="/status/${parasol.id}?managementNo=${parasol.managementNo}">상태 정보 조회</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <section id="agent-info">
                    <div class="container col-md-9">
                        <div class="ts-box col-md-auto">
                            <div class="row col-md-auto">
                                <div class="col-md-12">
                                    <div class="py-2 col-md-12">
                                        <section id="basic-information" class="mb-0 pl-3">
                                            <div class="row">
                                                <div class="ts-title mb-2 col-sm-10">
                                                    <label class="badge badge-light" style="font-size: 1.3em; font-weight: 500">관리번호</label>
                                                    <h3 style="font-size: 1.8em" class="mb-1 border-bottom">${parasol.managementNo}</h3>
                                                </div>
                                                <div class="d-flex align-items-start flex-column" style="text-align: center">
                                                    <label class="badge badge-light p-2" style="text-align: center; font-size: 1.3em; font-weight: 500; width: 55px">활성</label>
                                                    <c:choose>
                                                        <c:when test="${parasol.active == 'Y'}">
                                                            <h3 class="p-2" style="margin: auto" data-toggle="tooltip" data-placement="right" title="상태 변경 : 비활성 버튼">
                                                                <button type="button" class="card ts-item ts-card ts-result border text-primary" style="font-size: 1.5em" data-toggle="modal" data-target="#activeCenter">${parasol.active}</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <h3 class="p-2" style="margin: auto" data-toggle="tooltip" data-placement="right" title="상태 변경 : 활성 버튼">
                                                                <button type="button" class="card ts-item ts-card ts-result border text-danger" style="font-size: 1.5em" data-toggle="modal" data-target="#activeCenter">${parasol.active}</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    </h3>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <label class="badge badge-light" style="font-size: 1.3em; font-weight: 500">관리기관</label>
                                                    <p style="font-size: 1.5em">${parasol.managementAgency}</p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="badge badge-light" style="font-size: 1.3em; font-weight: 500">설치일자</label>
                                                    <p style="font-size: 1.5em">${parasol.installDate}</p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="badge badge-light" style="font-size: 1.3em; font-weight: 500">에이전트 IP 주소</label>
                                                    <p style="font-size: 1.5em">${parasol.agentIpAddress}</p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="badge badge-light" style="font-size: 1.3em; font-weight: 500">위도</label>
                                                    <p style="font-size: 1.5em">${parasol.latitude}</p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="badge badge-light" style="font-size: 1.3em; font-weight: 500">경도</label>
                                                    <p style="font-size: 1.5em">${parasol.longitude}</p>
                                                </div>
                                                <div class="col-sm-auto">
                                                    <label class="badge badge-light" style="font-size: 1.3em; font-weight: 500">설치주소</label>
                                                    <button type="button" class="card ts-item ts-card ts-result border"  data-toggle="modal" data-target="#modalCenter">
                                                        <p style="font-size: 1.5em" title="설치주소 변경 버튼" data-toggle="tooltip" data-placement="bottom">${parasol.installAddress}</p>
                                                    </button>
                                                </div>
                                                <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="ModalLongTitle">주소 수정</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="text" class="form-control" id="editAddressText" value="${parasol.installAddress}" />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                                                                <button type="button" id="editAddressButton" class="btn btn-primary">수정</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal fade" id="checkCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="checkLongTitle">공백은 입력할 수 없습니다.</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" id="checkButton" class="btn btn-primary" data-dismiss="modal">확인</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal fade" id="activeCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="activeLongTitle">활성화 변경</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <c:choose>
                                                                    <c:when test="${parasol.active == 'Y'}">
                                                                        <h3 class="form-check">비활성 상태로 변경하시겠습니까?</h3>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <h3 class="form-check">활성 상태로 변경하시겠습니까?</h3>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                                                                <button type="button" id="editActiveButton" class="btn btn-primary">확인</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
        </div>

        <script>
            document.getElementById("editAddressButton").addEventListener("click", editAddress, false);
            document.getElementById("editActiveButton").addEventListener("click", editActive, false);

            function editAddress() {
                var editAddressText = document.getElementById("editAddressText").value;

                if (editAddressText == "") {
                    $("#checkCenter").modal({keyboard : false})
                } else {
                    sendEdit({"id":"${parasol.id}","installAddress":editAddressText});
                }
            }

            function editActive() {
                if ("${parasol.active}" == "Y") {
                    sendEdit({"id":"${parasol.id}","active":"N"});
                } else {
                    sendEdit({"id":"${parasol.id}","active":"Y"});
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