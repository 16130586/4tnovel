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
        <%@ include file = "/jsps/components/_account-manage.header.jsp" %>
        <div class="account-manage__content u-row--1140 u-centered">
            <div>
                <p class="u-3x u-align-center">Đổi mật khẩu</p>
            </div>
            <form>
                <div class="row u-padding-bottom--1-5rem">
                    <div class="col-md-5 u-align-right">
                        Nhập mật khẩu hiện tại:
                    </div>
                    <div class="col-md-7">
                        <input name="current-pw" class="u-width--50" type="password" required>
                    </div>
                </div>
                <div class="row u-padding-bottom--1-5rem">
                    <div class="col-md-5 u-align-right">
                        Nhập mật khẩu mới:
                    </div>
                    <div class="col-md-7">
                        <input name="new-pw" class="u-width--50" type="password" required>
                    </div>
                </div>
                <div class="row u-padding-bottom--1-5rem">
                    <div class="col-md-5 u-align-right">
                        Nhập lại mật khẩu mới:
                    </div>
                    <div class="col-md-7">
                        <input name="re-new-pw" class="u-width--50" type="password" required>
                    </div>
                </div>
                <div class="row u-padding-bottom--1-5rem">
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
</html>