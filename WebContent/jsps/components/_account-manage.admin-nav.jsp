<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="admin-nav col-md-2">
    <nav class="u-margin-top--2rem">
        <label class="u-2x">Tài khoản</label>
        <hr>
        <ul class="vertical-menu--showcase u-margin-bottom--2rem">
            <li class="menu-item u-color-white"><a class="link u-block u-padding--05rem" href="admin?type=grant-right">Cấp quyền ghim</a></li>
            <li class="menu-item u-color-white"><a class="link u-block u-padding--05rem" href="admin?type=ban">Khóa</a></li>
            <li class="menu-item u-color-white"><a class="link u-block u-padding--05rem" href="admin?type=delete">Xóa</a></li>
        </ul>
        <label class="u-2x">Khác</label>
        <hr>
        <ul class="vertical-menu--showcase">
            <li class="menu-item u-color-white"><a class="link u-block u-padding--05rem" href="admin?type=censor">Kiểm duyệt</a></li>
            <li class="menu-item u-color-white"><a class="link u-block u-padding--05rem" href="admin?type=report">Thư tố cáo</a></li>
            <li class="menu-item u-color-white"><a class="link u-block u-padding--05rem" href="admin?type=notify">Gửi thông báo</a></li>
        </ul>
    </nav>
</div>
