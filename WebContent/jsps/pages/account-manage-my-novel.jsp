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
		<div
			class="account-manage__content u-row--1140 u-centered u-border-full">
			<div>
				<p class="u-3x u-align-center">Truyện của tui</p>
			</div>
			<br>
			<div class="row u-2x u-padding--05rem">
				<div class="col-md-6">Tên truyện</div>
				<div class="col-md-2">Người đăng</div>
				<div class="col-md-2">Nhóm dịch</div>
				<div class="col-md-2">Thao tác</div>
			</div>
			<hr>
			<c:forEach var="i" begin="1" end="${sizeNovels }" step="1">
			<c:set var="novel" value="${account.ownNovels[sizeNovels-i]}"/>
				<section>
					<div class="row u-padding--05rem">
						<button class="col-md-6 btn btn-belike-a u-align-left u-text-overflow--hidden" style="color: #36a39e"
							onclick="showOrHide(${novel.id})">${novel.name }</button>
						<a href="#" class="col-md-2 btn btn-belike-a u-align-left u-text-overflow--hidden">${account.displayedName }</a>
						<a href="#" class="col-md-2 btn btn-belike-a u-align-left u-text-overflow--hidden">${novel.group.name }
							</a>
						<div class="col-md-2">
							<button class="btn btn-primary u-color-white">Sửa</button>
							<button class="btn btn-danger u-color-white">Xóa</button>
						</div>
					</div>
					<div id="${novel.id }"
						style="display: none; background-color: #f0f0f0">
						<c:forEach var="vol" items="${novel.vols }">
							<div class="row u-padding--05rem">
								<button class="col-md-10 btn btn-belike-a u-align-left u-text-overflow--hidden" style="padding-left: 3rem !important"
									onclick="showOrHide('${novel.id}-${vol.id }')">${vol.title}</button>
								<div class="col-md-2">
									<button class="btn btn-primary u-color-white">Sửa</button>
									<button class="btn btn-danger u-color-white">Xóa</button>
								</div>
							</div>
							<div id="${novel.id}-${vol.id }"
								style="display: none; background-color: #e8e8e8">
								<c:forEach var="chap" items="${vol.chaps }">
									<div class="row u-padding--05rem">
										<button class="col-md-10 btn btn-belike-a u-align-left u-text-overflow--hidden" style="padding-left: 5rem !important">${chap.title}
										</button>
										<div class="col-md-2">
											<button class="btn btn-primary u-color-white">Sửa</button>
											<button class="btn btn-danger u-color-white">Xóa</button>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
					</section>
				</c:forEach>
		</div>
	</div>
	<script>
		function showOrHide(id) {
			var x = document.getElementById(id);
			if (x.style.display == 'none') {
				x.style.display = 'block';
			} else {
				x.style.display = 'none';
			}
		}
	</script>
</body>
</html>