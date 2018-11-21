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
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
		<div class="account-manage__content u-row--1140 u-centered">
			<div>
                <p class="u-3x u-align-center">Thêm thành viên</p>
            </div>
            <form>
            	<div class="row u-padding-bottom--1-5rem">
                    <div class="col-md-5 u-align-right">
                        Chọn nhóm:
                    </div>
                    <div class="col-md-7">
                        <select name="id-group">
                        	<option value="">ABC</option>
                        </select>
                    </div>
                </div>
                
                <div class="search">
                	<%@ include file = "/jsps/components/_search-bar.account.jsp" %>
                	<div class="u-centered u-width--75">
			            <div class="account-info row">
			                <div class="col-sm-2 u-align-right"><img class="img" src="http://via.placeholder.com/330x330"></div>
			                <div class="col-sm-10"><p class="u-2x">Name</p></div>
			                <input type="hidden" name="id-account" value="">
			            </div>
			        </div>
                </div>
                
                <div class="row u-padding-bottom--1-5rem">
                    <div class="col-md-5"></div>
                    <div class="col-md-7">
                        <button class="btn btn-primary u-color-white">Thêm</button>
                        <a href="#" class="btn btn-danger u-color-white">Hủy</a>
                    </div>
                </div>
            </form>
		</div>
	</div>
</body>
</html>