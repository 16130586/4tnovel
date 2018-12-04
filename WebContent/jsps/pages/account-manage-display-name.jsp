<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Đặt lại biệt hiệu</title>
<meta name="viewport" content="width=divice-width, intitial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- the following to include all needed things 
	font
	font awesome icon
	bootstraps custom-mize if you have using button , grid-system
	custom css
-->
<link
	href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese"
	rel="stylesheet">
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>


		<div class="account-manage__content u-row--1140 u-centered">
			<div>
				<p class="u-3x u-align-center">Đổi biệt hiệu</p>
			</div>
			<form action="changeNickname" method="post">
				<div class="row u-padding-bottom--1-5rem">
					<div class="col-md-5 u-align-right">Nhập mật khẩu hiện tại:</div>
					<div class="col-md-7">
						<input name="current-pw" class="u-width--50" type="password"
							required>
						<c:if test="${not empty currentPasswordError}">
							<p class="u-paragraph--failed u-centered">${currentPasswordError}</p>
						</c:if>
					</div>
				</div>

				<div class="row u-padding-bottom--1-5rem">
					<div class="col-md-5 u-align-right">Nhập tên mới:</div>
					<div class="col-md-7">
						<input name="new-name" class="u-width--50" type="text" required>
						<c:if test="${not empty newDisplayNameError}">
							<p class="u-paragraph--failed u-centered">${newDisplayNameError}</p>
						</c:if>
					</div>
				</div>

				<div class="row u-padding-bottom--1-5rem">
					<div class="col-md-5"></div>
					<div class="col-md-7">
						<button class="btn btn-primary u-color-white">Đổi</button>
						<a href="#" class="btn btn-danger u-color-white">Hủy</a>
					</div>
				</div>
				<c:if test="${not empty sucessed}">
					<div class="row u-padding-bottom--1-5rem">
						<p class="u-paragraph--sucessed">${sucessed}</p>
					</div>
				</c:if>
			</form>
		</div>
	</div>
</body>
</body>
</html>