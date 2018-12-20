<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ABC</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
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
				<%@ include file="/jsps/components/_account-manage.admin-nav.jsp"%>
				<div class="col-md-10" style="max-height: 80vh; overflow-y: scroll;">
					<div>
						<p class="u-3x u-align-center">Kiểm duyệt</p>
					</div>
					<div class="row u-padding--05rem">
						<div class="col-md-8">Tên</div>
						<div class="col-md-2">Ngày đăng</div>
						<div class="col-md-2">Thao tác</div>
					</div>
					<c:forEach var="entity" items="${censorList}">
						<div class="row u-padding--05rem u-margin-top--1rem">
							<button type="button"
								class="col-md-8 btn btn-belike-a u-align-left u-text-overflow--hidden"
								style="color: #36a39e"
								onclick="showOrHide(${entity.getCensorId()})">
								${entity.getTitle()}</button>
							<a href="#"
								class="col-md-2 btn btn-belike-a u-align-left u-text-overflow--hidden">${entity.getCreatedDate()}
							</a>
							<div class="col-md-2">
								<button class="btn btn-success u-margin-right--2rem"
									name="targetID" value="${entity.getCensorId()}">V</button>
								<button class="btn btn-danger" name="targetID"
									value="${entity.getCensorId()}">X</button>
							</div>
							<div id="${entity.getCensorId()}" class="chap-content row u-padding--05rem"
								style="display: none">
								<p style="max-height: 50vh; overflow-y: scroll;">${entity.getContent()}</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<p id="demo"></p>
		</div>
	</div>
	<script>
		function showOrHide(id) {
			var x = document.getElementById(id)
			var chapContents = document.getElementsByClassName('chap-content')
			if (x.style.display == 'none') {
				x.style.display = 'block'
			} else {
				x.style.display = 'none'
			}
			for(var i = 0; i<chapContents.length;i++){
				if(x.id==chapContents[i].id){
					continue;
				}
				chapContents[i].style.display = 'none'
			}
		}
	</script>
</body>
</body>
</html>