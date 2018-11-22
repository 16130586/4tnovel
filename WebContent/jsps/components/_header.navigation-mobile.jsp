<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navigation-mobile u-margin-left--1rem"
	id="navigation-mobile">
	<div class="dropdown-menu u-inline-block">
		<a class="dropdown-btn"> <i class="fas fa-bars u-3x btn"></i>
		</a>
		<div class="dropdown-content--bottom">
			<ul class="vertical-menu--showcase">
				<li class="menu-item"><a href="index">Trang Chủ</a></li>
				<li class="menu-item"><a href="search">tìm kiếm</a></li>
				<li class="menu-item">
					<div class="dropdown-menu">
						<a class="dropdown-btn" href="#">tác phẩm</a>
						<div class="dropdown-content--right">
							<ul class="horizontal-menu--showcase u-width--18rem">
								<li class="menu-item u-width--full"><a class="link-btn"
									target="_blank" href="see?author=user">sáng tác</a></li>
								<li class="menu-item u-width--full"><a class="link-btn"
									target="_blank" href="see?author=writer">truyện dịch</a></li>
							</ul>
						</div>
					</div>
				</li>
				<li class="menu-item">
					<div class="dropdown-menu">
						<a class="dropdown-btn" href="#">tâm sự</a>
						<div class="dropdown-content--right">
							<ul class="horizontal-menu--showcase u-width--18rem">
								<li class="menu-item u-width--full"><a href="talk?target=group" class="link-btn"
									target="_blank">nhóm</a></li>
								<li class="menu-item u-width--full"><a href="talk?target=one" class="link-btn"
									target="_blank">người lạ</a></li>
							</ul>
						</div>
					</div>
				</li>
				<li class="menu-item"><a href="forum">Thảo luận</a></li>
				<c:if test="${empty account}">
					<li class="menu-item"><a href="login">Đăng nhập</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
