<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="dropdown-menu">
	<div class="dropdown-btn">
		<i class="fa fa-cog u-3x" onclick="showDropDownContent('user-controls')"></i>
	</div>
	<div id="user-controls" class="dropdown-content--bottom u-transformX-50 card" style="margin-top: 0">
		<ul class="vertical-menu--showcase">
			<li class="menu-item">
				<a class="btn" href="manage">Quản lý</a>
			</li>
			<li class="menu-item ">
				<a class="btn" href="logout">Đăng xuất</a>
			</li>
		</ul>
	</div>
</div>