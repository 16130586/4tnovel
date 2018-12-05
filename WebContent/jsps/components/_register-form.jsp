<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="register-form">
	<h2 class="register-form__title">Đăng kí</h2>
	<hr>
	<hr>
	<div class="">
		<form action="register" method="POST">
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
				<label for="email" class="u-2x">Thư điện tử</label>
			</div>
			<div class="u-margin-bottom--1rem">
				<input name="email" type="email" placeholder="Thư điện tử..."
					class="u-width--full u-padding--05rem" required="required" />
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
					placeholder="Nhập lại mật khẩu..." class="u-width--full u-padding--05rem"
					required="required" />
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
				<input type="submit" class="btn btn-green u-width--full u-2x"
					value="Đăng kí" required="required">
			</div>
		</form>
	</div>
</div>