<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="register-form">
	<h2 class="register-form__title">Đăng kí</h2>
	<hr><hr>
	<div class="">
		<form action="register" method="POST">
			<div class = "u-margin-top--1rem u-margin-bottom--1rem">
				<label for="username" class="u-2x">Tên đăng nhập</label>
			</div>
			<div class="u-margin-bottom--1rem">
				<input name="username" type="text" placeholder="Your username..." class="u-width--full u-2x"/>
			</div>
			<div class="u-margin-bottom--1rem">
				<label for="email" class="u-2x">Mật khẩu</label>
			</div>
			<div class="u-margin-bottom--1rem">
				<input name="email" type="email" placeholder="Your email..." class="u-width--full u-2x"/>
			</div>
			<div class="u-margin-bottom--1rem">
				<label for="password" class="u-2x">Mật khẩu</label>
			</div>
			<div class="u-margin-bottom--1rem">
				<input name="password" type="password" placeholder="Your password..." class="u-width--full u-2x"/>
			</div>
			<div class="u-margin-bottom--1rem">
				<label for="re-password" class="u-2x">Nhập lại mật khẩu</label>
			</div>
			<div class="u-margin-bottom--1rem">
				<input name="re-password" type="password" placeholder="Re-type your password..." class="u-width--full u-2x"/>
			</div>
			<div class="u-margin-bottom--1rem">
				<p class="u-color-blue"><input name="agreement" type="checkbox" value="agree"/> Đồng ý với <a class="u-color-blue" href="#">Điều khoản sử dụng</a></p>
			</div>
			<div>
				<input type="submit" class="btn btn-green u-width--full u-2x" value="Đăng kí">
			</div>
		</form>
	</div>
</div>