<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="account-manage__nav row u-2x">
    <div class="col-md-10 ">
        <ul class="horizontal-menu--showcase ">
            <li class="menu-item u-margin-left--1rem "><a class="link " href="index">Trang chủ</a></li>
            <li class="menu-item u-margin-left--1rem "><a class="link " href="manage">Thông tin</a></li>
            <li class="menu-item u-margin-left--1rem "><a class="link " href="manage?type=my-novel">Truyện của tui</a></li>
            <li class="menu-item u-margin-left--1rem dropdown-menu">
                <a class="link " href="#">Thêm</a>
                <div class="dropdown-content--bottom">
                    <ul class="vertical-menu--showcase">
                        <li class="menu-item "><a class="link " href="add?type=add-novel">+Truyện</a></li>
                        <li class="menu-item "><a class="link " href="add?type=add-vol ">+Tập</a></li>
                        <li class="menu-item "><a class="link " href="add?type=add-chapter">+Chương</a></li>
                    </ul>
                </div>
            </li>
        </ul>

    </div>
    <div class="col-md-2 dropdown-menu ">
        <a class="link " href="# ">Tài khoản</a>
        <div class="dropdown-content--bottom ">
            <ul class="vertical-menu--showcase ">
                <li class="menu-item "><a class="link " href="manage ">displayname</a></li>
                <hr>
                <li class="menu-item "><a class="link " href="manage?type=display-name">Đổi tên</a></li>
                <li class="menu-item "><a class="link " href="manage?type=password">Đổi mật khẩu</a></li>
                <li class="menu-item "><a class="link " href="manage?type=mail">Đổi email</a></li>
            </ul>
        </div>
    </div>
</div>
<hr>
