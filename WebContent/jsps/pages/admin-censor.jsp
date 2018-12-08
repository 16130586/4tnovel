<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ABC</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
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
		<div class="account-manage__content">
			<div class="row u-height--100">
				<%@ include file="/jsps/components/_account-manage.admin-nav.jsp" %>	
				<div class="col-md-10">
					<div>
		                <p class="u-3x u-align-center">Kiểm duyệt</p>
		            </div>
                    <div class="row u-margin-bottom--2rem">
                        <div class="col-md-5"></div>
                        <div class="col-md-2">
                            <form action="" method="post">
                                <input type="hidden" name="id-novel" value="">
                                <button class="btn btn-primary u-color-white u-width--full">Duyệt tất cả</button>
                            </form>
                            <form action="" method="post">
                                <input type="hidden" name="id-novel" value="">
                                <button class="btn btn-danger u-color-white u-width--full">Xóa tất cả</button>
                            </form>
                        </div>
                        <div class="col-md-5"></div>
					</div>	
					<div class="row u-margin-bottom--1rem">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<%@ include file="/jsps/components/_card-novel.jsp" %>
						</div>
						<div class="col-md-1">
							<form action="" method="post">
                                <input type="hidden" name="id-novel" value="">
                                <button class="btn btn-primary u-color-white u-width--full">Duyệt</button>
                            </form>
                            <form action="" method="post">
                                <input type="hidden" name="id-novel" value="">
                                <button class="btn btn-danger u-color-white u-width--full">Xóa</button>
                            </form>
						</div>
						<div class="col-md-1"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</body>
</html>