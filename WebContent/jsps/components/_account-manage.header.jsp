<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="account-manage__nav row u-2x">
    <div class="col-md-10 ">
        <ul class="horizontal-menu--showcase ">
            <li class="menu-item u-margin-left--1rem "><a class="link " href="index">Trang chủ</a></li>
            <li class="menu-item u-margin-left--1rem "><a class="link " href="manage">Thông tin</a></li>
            <li class="menu-item u-margin-left--1rem dropdown-menu">
                <a class="link " href="#">Thêm</a>
                <div class="dropdown-content--bottom">
                    <ul class="vertical-menu--showcase">
                        <li class="menu-item "><a class="link " href="add-novel">+Truyện</a></li>
                        <li class="menu-item "><a class="link " href="add-vol">+Tập</a></li>
                        <li class="menu-item "><a class="link " href="add-chapter">+Chương</a></li>
                        <li class="menu-item "><a class="link " href="add-group">+Nhóm</a></li>
                        <li class="menu-item "><a class="link " href="add-member"
                        									 style="white-space: nowrap;">+Thành viên</a></li>
                    </ul>
                </div>
            </li>
                 	<li class="menu-item u-margin-left--1rem"><a class="link" href="manage/account/dashboard-novels">Quản lý</a></li>
      
            <c:if test="${account.role.name() eq 'ADMINISTRATOR'}">
            	<li class="menu-item u-margin-left--1rem "><a class="link" href="manage/admin/statistics/system">Quản trị</a></li>
            </c:if>
         </ul>
    </div>
    <div class="col-md-2 dropdown-menu ">
        <a class="link " href="# ">Tài khoản</a>
        <div class="dropdown-content--bottom ">
            <ul class="vertical-menu--showcase ">
            	<c:if test="${not empty account.displayedName}">
                	<li class="menu-item "><a class="link " href="manage ">${account.displayedName }</a></li>
                </c:if>
                <c:if test="${empty account.displayedName}">
                	<li class="menu-item "><a class="link " href="manage ">${account.userName}</a></li>
                </c:if>
                <hr>
                <li class="menu-item "><a class="link " href="manage?type=display-name">Đổi tên</a></li>
                <li class="menu-item "><a class="link " href="manage?type=password">Đổi mật khẩu</a></li>
                <li class="menu-item "><a class="link " href="manage?type=mail">Đổi email</a></li>
                <li class="menu-item "><a class="link " href="logout">Đăng xuất</a></li>
            </ul>
        </div>
    </div>
</div>
<hr>
