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
		<div class="account-manage__content">
			<div class="row u-height--100">
				<%@ include file="/jsps/components/_account-manage.admin-nav.jsp" %>	
				<div class="col-md-10">
					<div>
		                <p class="u-3x u-align-center">Khóa tài khoản</p>
		            </div>
					<div class="search">
						<%@ include file="/jsps/components/_search-bar.account.jsp" %>
						
						<form action="" method="post">
							<div class="u-centered u-width--75">
		                        <div class="account-info row">
		                            <input type="hidden" name="id-account" value="">
		                            <div class="col-sm-2 u-align-right"><img class="img" src="http://via.placeholder.com/330x330"></div>
		                            <div class="col-sm-7"><p class="u-2x">Name</p></div>
		                            <div class="col-sm-3 u-align-center"><button class="btn btn-primary u-2x">Khóa tài khoản</button></div>
		                        </div>
		                        <hr>
	                        </div>
						</form>
						
					</div>
				</div>		
			</div>
		</div>
	</div>
</body>
</body>
</html>