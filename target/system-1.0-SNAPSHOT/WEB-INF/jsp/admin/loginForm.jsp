<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/include/head.jsp" %>

		<title>공공 파라솔 관리 시스템 - 로그인</title>
	</head>
	<body style="font-family: 'Noto Sans KR', sans-serif;">
		<!-- WRAPPER
			=================================================================================================================-->
		<div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
			<header id="ts-header" class="fixed-top">
				<!--PRIMARY NAVIGATION
				=============================================================================================================-->
				<nav id="ts-primary-navigation" class="navbar navbar-expand-md navbar-light">
					<div class="container">
						<!--Brand Logo-->
						<p style="font-size: 2em; font-weight : 700; color: #005cbf" >공공 파라솔 관리 시스템</p>
					</div>
					<!--end container-->
				</nav>
				<!--end #ts-primary-navigation.navbar-->
			</header>
			<!--end Header-->
			<!--*********************************************************************************************************-->
			<!-- MAIN ***************************************************************************************************-->
			<!--*********************************************************************************************************-->
			<main id="ts-main">
				<section id="breadcrumb" /></section>
				<!--LOGIN / REGISTER SECTION
					=========================================================================================================-->
				<section id="login-register">
					<br><br>
					<div class="container">
						<div class="row">
							<div class="offset-md-2 col-md-8 offset-lg-3 col-lg-6">
								<!--LOGIN / REGISTER TABS
									=========================================================================================-->
								<ul class="nav nav-tabs" id="login-register-tabs" role="tablist">
									<!--Login tab-->
									<li class="nav-item">
										<a class="nav-link active" id="login-tab" data-toggle="tab" role="tab">
											<h3 style="font-weight : 500">관리자 인증</h3>
										</a>
									</li>
								</ul>
								<!--TAB CONTENT
									=========================================================================================-->
								<div class="tab-content">
									<!--LOGIN TAB
										=====================================================================================-->
									<div class="tab-pane active" style="font-weight : 100" id="login" role="tabpanel" aria-labelledby="login-tab">
										<!--Login form-->
										<form action="/login" method="post" class="ts-form" id="form-login">
											<!--Id-->
											<div class="form-group">
												<input type="text"  class="form-control" id="login-email" name="id" placeholder="아이디" required="">
											</div>
											<!--Password-->
											<div class="input-group ts-has-password-toggle">
												<input type="password" class="form-control border-right-0" name="password" placeholder="비밀번호" required="">
												<div class="input-group-append">
													<a href="#" class="input-group-text bg-white border-left-0 ts-password-toggle">
														<i class="fa fa-eye"></i>
													</a>
												</div>
											</div>
											<!--Submit button-->
											<div class="ts-center__vertical justify-content-between">
												<!--Remember checkbox-->
												<div class="custom-control custom-checkbox mb-0">
													<c:if test="${adminMatch eq false}">
														<p style="font-size: 1em" class="text-danger">아이디 또는 비밀번호가 일치 하지 않습니다.</p>
													</c:if>
												</div>
												<!--Submit button-->
												<button style="font-weight : 100" type="submit" class="btn btn-primary">로그인</button>
											</div>
											<hr>
										</form>
									</div>
									<!--end #login.tab-pane-->
								</div>
								<!--end tab-content-->
							</div>
							<!--end offset-4.col-md-4-->
						</div>
						<!--end row-->
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