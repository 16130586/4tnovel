<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ABC</title>
<meta name="viewport" content="width=divice-width, intitial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- the following to include all needed things 
	font
	font awesome icon
	bootstraps custom-mize if you have using button , grid-system
	custom css
-->
	<link
	href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese"
	rel="stylesheet">
    <link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
    <link rel="stylesheet" href="resources/local/css/style.css" />
</head> 
<body>
    <div class="account-manage">
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
        <div class="account-manage__content u-row--1140 u-centered">
            <div>
                <p class="u-3x u-align-center">Đổi tên</p>
            </div>
            <form>
                <div class="row u-padding-bottom--05rem">
                    <div class="col-md-5 u-align-right">
                        Nhập mật khẩu hiện tại:
                    </div>
                    <div class="col-md-7">
                        <input name="current-pw" class="u-width--50" type="password" required>
                    </div>
                </div>
                <div class="row u-padding-bottom--05rem">
                    <div class="col-md-5 u-align-right">
                        Nhập tên mới:
                    </div>
                    <div class="col-md-7">
                        <input name="new-name" class="u-width--50" type="text" required>
                    </div>
                </div>
                <div class="row u-padding-bottom--05rem">
                    <div class="col-md-5"></div>
                    <div class="col-md-7">
                        <button class="btn btn-primary u-color-white">Đổi</button>
                        <a href="#" class="btn btn-danger u-color-white">Hủy</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</body>
</html>