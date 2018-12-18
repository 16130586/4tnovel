<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="forgot-form">
	<h2 class="login-form__title">Quên mật khẩu</h2>
	<hr class="u-margin-bottom--2rem">
	<c:if test="${empty sentMail }">
	<form action="forgot" method="GET">
		<div class="u-full--width u-align-center u-margin-bottom--1rem">
			<input class="u-width--75" name="username" style="padding: .5rem;" placeholder="Nhập tên tài khoản">
		</div>
		<c:if test="${not empty errUsername }">
			<div class="u-full--width u-margin-bottom--1rem">
				<p class="u-width--75 u-paragraph--failed u-centered">${errUsername}</p>
			</div>
		</c:if>
		<div class="u-full--width u-align-center">
			<input type="submit" class="btn btn-green" value="Gửi mail">
		</div>
	</form>
	</c:if>
	<c:if test="${not empty sentMail }">
	<form action="forgot" method="POST">
		<input type="hidden" name="id" value="${accountId }">
			<small style="color: #009624; padding-left: 8.5rem">Mã OTP đã được gửi đến mail của tài khoản của bạn!</small>
		<div class="u-full--width u-align-center u-margin-bottom--1rem">
			<input name="otp" style="padding: .5rem;width: 57.2%;" placeholder="Nhập mã OTP">
			<small style="color: #009624; ">Hết
			hạn sau 5 phút</small>
		</div>
		<c:if test="${not empty errOTP }">
			<div class="u-full--width u-margin-bottom--1rem">
				<p class="u-width--75 u-paragraph--failed u-centered">${errOTP}</p>
			</div>
		</c:if>
		<div class="u-full--width u-align-center u-margin-bottom--1rem">
			<input class="u-width--75" type="password" name="new-password" style="padding: .5rem;" placeholder="Nhập mật khẩu mới">
		</div>
		<c:if test="${not empty errPassword }">
			<div class="u-full--width u-margin-bottom--1rem">
				<p class="u-width--75 u-paragraph--failed u-centered">${errPassword}</p>
			</div>
		</c:if>
		<div class="u-full--width u-align-center">
			<input type="submit" class="btn btn-green" value="Làm mới">
		</div>
	</form>	
	</c:if>
</div>