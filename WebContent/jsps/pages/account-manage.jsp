<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý tài khoản</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
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

		<div class="account-manage__content u-centered u-row--1140 ">
			<div>
				<p class="u-3x u-align-center">Thông tin</p>
			</div>
			<div class="row u-padding-bottom--1-5rem">
				<div class="col-md-5 u-align-right">
					<p>Bí danh:</p>
				</div>
				<div class="col-md-7">
					<input class="u-width--50" type="text"
						value="${account.displayedName}" disabled><a
						class="link" href="manage?type=display-name">
							Thay đổi
						</a>
				</div>
			</div>
			<div class="row u-padding-bottom--1-5rem">
				<div class="col-md-5 u-align-right">
				<p>Tài khoản:</p>
				</div>
				<div class="col-md-7">
					<input class="u-width--50" type="text" value="${account.userName }"
						disabled>
				</div>
			</div>
			<div class="row u-padding-bottom--1-5rem">
				<div class="col-md-5 u-align-right">
				<p>Mật khẩu:</p>
				</div>
				<div class="col-md-7">
					<input class="u-width--50" type="password" value="***************"
						disabled><a class="link"
						href="manage?type=password">Thay đổi</a>
				</div>
			</div>
			<div class="row u-padding-bottom--1-5rem">
				<div class="col-md-5 u-align-right">
				<p>Email:</p>
				</div>
				<div class="col-md-7">
					<input class="u-width--50" type="text" value="${account.gmail }"
						disabled><a class="link"
						href="manage?type=mail">Thay đổi</a>
				</div>
			</div>
			<br>
		</div>
	</div>
</body>
</html>