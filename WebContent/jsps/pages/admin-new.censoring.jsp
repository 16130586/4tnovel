<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Quản trị - kiểm duyệt | 4TNOVEL</title>title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/images/icon/favicon.ico">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/themify-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/metisMenu.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/slicknav.min.css">
<!-- amcharts css -->
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<!-- Start datatable css -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.jqueryui.min.css">
<!-- style css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/typography.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/default-css.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/styles.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/css/responsive.css">
<!-- modernizr css -->
<script
	src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body>
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<!-- preloader area start -->
	<div id="preloader">
		<div class="loader"></div>
	</div>
	<!-- preloader area end -->
	<!-- page container area start -->
	<div class="page-container ">
		<!-- sidebar menu area start -->
		<%@ include file="/jsps/components/_admin-new.side-bar.jsp"%>
		<!-- main content area start -->
		<div class="main-content">
			<!-- header area start -->
			<div class="header-area">
				<div class="row align-items-center">
					<!-- nav and search button -->
					<div class="col-md-6 col-sm-8 clearfix">
						<div class="nav-btn pull-left">
							<span></span><span></span> <span></span>
						</div>
					</div>
				</div>
			</div>
			<!-- header area end -->
			<!-- page title area start -->
			<div class="page-title-area">
				<div class="row align-items-center">
					<div class="col-sm-6">
						<div class="breadcrumbs-area clearfix">
							<h4 class="page-title pull-left">Quản lý</h4>
							<ul class="breadcrumbs pull-left">
								<li><a href="${pageContext.request.contextPath}/manage">Giao diện chính</a></li>
								<li><span>Kiếm duyệt</span></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- page title area end -->
			<div class="main-content-inner">
				<div class="row">
					<!-- Dark table start -->
					<div class="col-12 mt-5">
						<div class="card">
							<div class="card-body">
								<h4 class="header-title">Danh sách cần kiểm duyệt</h4>
								<div class="data-tables datatable-dark">
									<table id="dataTable3" style="width: 100%; text-align: left;">
										<thead class="text-capitalize">
											<tr>
												<th>&nbsp;Tên</th>
												<th>&nbsp;Ngày đăng</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="entity" items="${censorList}">
												<tr data-toggle="modal"
													data-target="#censor${entity.getCensorId()}"
													style="cursor: pointer;" id="row${entity.getCensorId() }">
													<td>&nbsp;${entity.getTitle()}</td>
													<td>&nbsp;${entity.getCreatedDate()}</td>
												</tr>
												<div class="modal" id="censor${entity.getCensorId()}">
													<div class="modal-dialog"
														style="max-width: 1140px; margin: auto;">
														<div class="modal-content">

															<!-- Modal Header -->
															<div class="modal-header">
																<h4 class="modal-title">${entity.getTitle()}</h4>
																<button type="button" class="close" data-dismiss="modal">&times;</button>
															</div>

															<!-- Modal body -->
															<div class="modal-body">
																<div style="overflow-y: scroll; max-height: 75vh">
																	<!-- <p style="max-height: 75vh;">${entity.getContent()}</p> -->
																	<c:set var="newLine" value="\n"/>
																	<c:set var="paragraphs" value="${entity.getContent().split(newLine) }" />
																	<c:forEach var="paragraph" items="${paragraphs }">
																		<p>${paragraph }</p>
																	</c:forEach>
																</div>
															</div>

															<!-- Modal footer -->
															<div class="modal-footer">
																<button class="btn btn-success" value="${entity.getCensorId() }" name="${entity.getClass().getSimpleName() }" onclick="censor(this, 1)" data-dismiss="modal">Duyệt</button>
																<button class="btn btn-default" value="${entity.getCensorId() }" name="${entity.getClass().getSimpleName() }" onclick="censor(this, 0)" data-dismiss="modal">Từ chối</button>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- Dark table end -->
				</div>
			</div>
		</div>
		<!-- main content area end -->
		<!-- footer area start-->
		<footer>
			<div class="footer-area"></div>
		</footer>
		<!-- footer area end-->
	</div>
	<!-- page container area end -->
	<!-- jquery latest version -->
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/vendor/jquery-2.2.4.min.js"></script>
	<!-- bootstrap 4 js -->
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/metisMenu.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/jquery.slicknav.min.js"></script>

	<!-- Start datatable js -->
	<script
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
	<!-- others plugins -->
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/plugins.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/template/admin-dashboard/js/scripts.js"></script>
	<script>
	function removeOneRow(x) {
		x.parentNode.removeChild(x);
	}
	var url = location.origin.concat('${pageContext.request.contextPath}').concat('/manage/admin/dashboard-censoring');
	var xhttp = new XMLHttpRequest();

	function censor(x, isAccept) {
		xhttp.onreadystatechange = function() {
			if(xhttp.readyState == 4) {document.body.style.cursor='default'}
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				//removeOneRow(x.parentNode.parentNode.parentNode.parentNode);
				document.getElementById('row'.concat(x.value)).style.cursor = "default";		
				document.getElementById('row'.concat(x.value)).style.backgroundColor = "#e8e8e8";		
			}
		}
		xhttp.open('POST', url);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send('id='+x.value+'&isAccept='+isAccept+'&stream='+x.attributes.getNamedItem("name").value);
	}
	</script>
</body>

</html>
