<%@page
	import="t4novel.azurewebsites.net.acessviasocial.common.SocialAccessConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="login-form">
	<h2 class="login-form__title">Đăng nhập</h2>
	<hr>
	<div class="login-form__social-login">
		<a href="#" class="u-margin-right--2rem"><i
			class="fab fa-facebook u-5x u-color-blue"> </i></a> <a
			href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=<%=SocialAccessConstants.GOOGLE_REDIRECT_URI%>&response_type=code
    &client_id=<%=SocialAccessConstants.GOOGLE_CLIENT_ID%>&approval_prompt=force"><i
			class="fab fa-google-plus-square u-5x u-color-red"></i></a>
	</div>
	<hr>
	<div class="login-form__traditional u-margin-bottom--2rem">
		<form action="login" method="POST">
			<div class="u-width--full u-margin-bottom--1rem">
				<div class="u-width--full">
					<input name="username" type="text" placeholder="Tài khoản"
						class="u-block u-centered u-padding--05rem" />
					<c:if test="${not empty userNameError}">
						<p class="u-text--error-display">${userNameError}</p>
					</c:if>
				</div>
				<div class="u-width--full">
					<input name="password" type="password" placeholder="Mật khẩu"
						class="u-block u-centered u-padding--05rem" />
					<c:if test="${not empty passwordError}">
						<p class="u-text--error-display">${passwordError}</p>
					</c:if>
				</div>
			</div>
			<c:if test="${not empty banError}">
				<p class="u-text--error-display">${banError}</p>
			</c:if>
			<div class="u-width--full">
				<a href="register" class="btn">Tạo mới tài khoản</a> <input
					type="submit" class="btn btn-green" value="Đăng nhập">
			</div>
		</form>
	</div>
	<div class="u-width--full u-align-center">
		<a href="forgot" class="btn u-color-red">Tôi đã quên mật khẩu!</a>
	</div>
</div>