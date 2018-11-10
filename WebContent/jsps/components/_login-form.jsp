<%@page import="t4novel.azurewebsites.net.acessviasocial.common.SocialAccessConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login-form">
	<h2 class="login-form__title">Đăng nhập</h2>
	<hr>
	<div class="login-form__social-login">
		<a href="#" class="u-margin-right--2rem"><i
			class="fab fa-facebook u-5x u-color-blue"> </i></a>
		<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=<%=SocialAccessConstants.GOOGLE_REDIRECT_URI %>&response_type=code
    &client_id=<%=SocialAccessConstants.GOOGLE_CLIENT_ID %>&approval_prompt=force"><i
			class="fab fa-google-plus-square u-5x u-color-red"></i></a>
	</div>
	<hr>
	<div class="login-form__traditional u-margin-bottom--2rem">
		<form action="login" method="POST">
			<div class="u-width--full">
				<input name="username" type="text"
					placeholder="your username or email" class="u-block u-centered u-margin-bottom--2rem" /> <input name="password"
					type="password" placeholder="your password" class="u-block u-centered u-margin-bottom--2rem"/>
			</div>
			<div class="u-width--full">
				<a href="register" class="btn">Tạo mới tài khoản</a> <input type="submit"
					class="btn btn-green" value="Đăng nhập">
			</div>
		</form>
	</div>
	<div class="u-width--full u-align-center">
			<a href="forgot" class="btn u-color-red">Tôi đã quên mật khẩu!</a>
		</div>
</div>