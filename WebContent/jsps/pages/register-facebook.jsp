<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng ký | 4TNOVEL</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/brands.css"
	integrity="sha384-Px1uYmw7+bCkOsNAiAV5nxGKJ0Ixn5nChyW8lCK1Li1ic9nbO5pC/iXaq27X5ENt"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/solid.css"
	integrity="sha384-osqezT+30O6N/vsMqwW8Ch6wKlMofqueuia2H7fePy42uC05rm1G+BUPSd2iBSJL"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/fontawesome.css"
	integrity="sha384-BzCy2fixOYd0HObpx3GMefNqdbA7Qjcc91RgYeDjrHTIEXqiF00jKvgQG0+zY/7I"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/vendors/css/bootstrap-reboot.css" />
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<%@ include file="/jsps/components/_header.jsp"%>
	<div class="register-form">
		<h2 class="register-form__title">Hoàn tất thông tin cho tài khoản
			facebook ${fbName}</h2>
		<hr>
		<hr>
		<div class="">
			<form action="login-facebook" method="POST">
				<div class="u-margin-top--1rem">
					<label for="username" class="u-2x">Tên đăng nhập</label>
				</div>
				<div class="u-margin-bottom--1rem">
					<input name="username" type="text" placeholder="Tên đăng nhập..."
						class="u-width--full u-padding--05rem" required="required" />
					<c:if test="${not empty userNameError}">
						<div>
							<p class="u-text--error-display">${userNameError}</p>
						</div>
					</c:if>
				</div>
				<div>
					<label for="password" class="u-2x">Email</label>
				</div>
				<div class="u-margin-bottom--1rem">
					<input name="email" type="email" placeholder="Thư điện tử..."
						class="u-width--full u-padding--05rem" required="required"
						value="${fbEmail }" />
					<c:if test="${not empty gmailError}">
						<div>
							<p class="u-text--error-display">${gmailError}</p>
						</div>
					</c:if>
				</div>
				<div>
					<label for="password" class="u-2x">Mật khẩu</label>
				</div>
				<div class="u-margin-bottom--1rem">
					<input name="password" type="password" placeholder="Mật khẩu..."
						class="u-width--full u-padding--05rem" required="required" />
					<c:if test="${not empty passwordError}">
						<div>
							<p class="u-text--error-display">${passwordError}</p>
						</div>
					</c:if>
				</div>
				<div>
					<label for="re-password" class="u-2x">Nhập lại mật khẩu</label>
				</div>
				<div class="u-margin-bottom--1rem">
					<input name="re-password" type="password"
						placeholder="Nhập lại mật khẩu..."
						class="u-width--full u-padding--05rem" required="required" />
					<c:if test="${not empty rePasswordError}">
						<div>
							<p class="u-text--error-display">${rePasswordError}</p>
						</div>
					</c:if>
				</div>
				<div class="u-margin-bottom--1rem">
					<p class="u-color-blue">
						<input name="agreement" type="checkbox" value="agree" /> Đồng ý
						với <a class="u-color-blue" href="#">Điều khoản sử dụng</a>
					</p>
					<c:if test="${not empty acceptedRuleError}">
						<div>
							<p class="u-text--error-display">${acceptedRuleError}</p>
						</div>
					</c:if>
				</div>
				<div>
					<input type="hidden" name="fbName" value="${fbName}"> <input
						type="hidden" name="fbID" value="${fbID}"> <input
						type="hidden" name="fbEmail" value="${fbEmail}"> <input
						type="submit" class="btn btn-green u-width--full u-2x"
						value="Đăng kí" required="required">
				</div>
			</form>
		</div>
	</div>
	<%@include file="/jsps/components/_footer.jsp"%>
</body>
</html>