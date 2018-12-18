<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navigation-desktop u-margin-left--2rem"
	id="navigation-desktop">
	<ul class="horizontal-menu--showcase ">
		<li class="menu-item u-margin-right--1rem"><a href="index">Trang
				chủ</a></li>
		<li class="menu-item u-margin-right--1rem"><a href="search">Tìm
				kiếm</a></li>
		<li class="menu-item u-margin-right--1rem">
			<div class="dropdown-menu">
				<a class="dropdown-btn btn " href="#">Tác phẩm</a>
				<div class="dropdown-content--bottom">
					<ul class="vertical-menu--showcase">
						<li class="menu-item u-width--full "><a
							href="see3?kind=COMPOSE" class="link-btn" >Sáng
								tác</a></li>
						<li class="menu-item u-width--full "><a
							href="see3?kind=TRANSLATE" class="link-btn" >Truyện
								dịch</a></li>
								<li class="menu-item u-width--full "><a
							href="see3" class="link-btn" >Tất cả truyện</a></li>
					</ul>
				</div>
			</div>
		</li>
		<li class="menu-item u-margin-right--1rem">
			<div class="dropdown-menu">
				<a class="dropdown-btn btn " href="#">Tâm sự</a>
				<div class="dropdown-content--bottom">
					<ul class="vertical-menu--showcase">
						<li class="menu-item u-width--full "><a
							href="talk?target=group" class="link-btn" >Nhóm</a></li>
						<li class="menu-item u-width--full "><a
							href="talk?target=one" class="link-btn" >Người
								lạ</a></li>
					</ul>
				</div>
			</div>
		</li>
		<li class="menu-item u-margin-right--1rem"><a href="forum">Thảo
				luận</a></li>
		<c:if test="${empty account}">
			<li class="menu-item u-margin-right--1rem"><a href="login">Đăng
					nhập</a></li>
		</c:if>
	</ul>
</nav>