<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Datatable - srtdash</title>
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
	<div class="page-container">
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
							<span></span> <span></span> <span></span>
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
								<li><span>Tập</span></li>
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
								<h4 class="header-title">Danh sách tập của tác phẩm</h4>
								<div class="data-tables datatable-dark">
									<table class="table-striped" id="dataTable3"
										style="width: 100%; text-align: left;">
										<thead class="text-capitalize">
											<tr>
												<th>&nbsp;Tên</th>
												<th>&nbsp;Số tập</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="novel" items="${novels}">
												<tr>
													<td>
														<p style="width: 100%; cursor: pointer;"
															data-toggle="modal" data-target="#seeVol${novel.id }">&nbsp;&nbsp;${novel.name }</p>

														<div class="modal fade" id="seeVol${novel.id}">
															<div class="modal-dialog"
																style="max-width: 100%; height: 90vh">
																<div class="modal-content"
																	style="max-width: 1140px; margin: auto">
																	<div class="modal-header">
																		<h5 class="modal-title">Danh sách tập của tác
																			phẩm: ${novel.name }</h5>
																		<button type="button" class="close"
																			data-dismiss="modal">
																			<span>&times;</span>
																		</button>
																	</div>
																	<div class="modal-body"
																		style="height: 80vh; overflow-y: auto">
																		<div class="data-tables datatable-dark">
																			<table class="table-striped"
																				style="width: 90%; margin: auto">
																				<tr>
																					<th>&nbsp;Tên</th>
																					<th style="width: 20%; margin: auto">Thao tác</th>
																				</tr>
																				<c:forEach var="vol" items="${novel.vols }">
																					<tr>
																						<td>&nbsp;${vol.title }</td>
																						<td>
																							<button type="button"
																								class="btn btn-primary btn-small"
																								data-toggle="modal"
																								data-target="#edit${vol.id }">
																								<i class="fa fa-edit"></i>
																							</button>
																							<div id="edit${vol.id }" class="modal fade">
																								<div class="modal-dialog"
																									style="max-width: 100%">
																									<div class="modal-content"
																										style="max-width: 768px; margin: auto">
																										<div class="modal-header">
																											<h5 class="modal-title">Sửa tập</h5>
																											<button type="button" class="close"
																												onclick="hideModal(this.parentNode.parentNode.parentNode.parentNode)">
																												<span>&times;</span>
																											</button>
																										</div>
																										<form method="post"
																											action="${pageContext.request.contextPath}/fix-vol">
																											<input type="hidden" name="admin" value="1">
																											<input type="hidden" name="fixedVolID"
																												value="${vol.id }">
																											<div class="modal-body">
																												<table class="table"
																													style="margin: auto; width: 95%">
																													<tr>
																														<td
																															style="width: 15%; padding-top: 15px; text-align: right; vertical-align: middle;"><label>Tiêu
																																đề: <span style="color: red">*</span>
																														</label></td>
																														<td><input name="title" type="text"
																															style="padding: .5rem; width: 100%"
																															required value="${vol.title}"></td>
																													</tr>
																													<tr>
																														<td style="text-align: right;"><label>Tóm
																																tắt:</label></td>
																														<td><textarea name="description"
																																style="padding: .5rem; width: 100%"
																																rows="7">${vol.description}</textarea></td>
																													</tr>
																												</table>
																											</div>
																											<div class="modal-footer">
																												<button type="submit"
																													class="btn btn-primary"
																													style="margin: auto">Sửa</button>
																											</div>
																										</form>
																									</div>
																								</div>
																							</div>
																							<button type="button"
																								class="btn btn-secondary btn-small"
																								data-toggle="modal"
																								data-target="#delete${vol.id }">
																								<i class="fa fa-trash"></i>
																							</button> <!-- Modal -->
																							<div id="delete${vol.id }" class="modal fade"
																								role="dialog">
																								<div class="modal-dialog">

																									<!-- Modal content-->
																									<div class="modal-content">
																										<div class="modal-header">
																											<h4 class="modal-title">Cảnh báo!!!</h4>
																											<button type="button" class="close"
																												onclick="hideModal(this.parentNode.parentNode.parentNode.parentNode)">&times;</button>
																										</div>
																										<div class="modal-body">
																											<p>
																												Bạn muốn xóa tập <span class="btn-danger">${vol.title}</span>
																												cùng toàn bộ chương?
																											</p>
																										</div>
																										<div class="modal-footer">
																										<form method="post" action="${pageContext.request.contextPath}/delete-vol">
																											<input name="admin" value="1" type="hidden">
																											<input name="id-vol" value="${vol.id }" type="hidden">
																											<button type="submit"
																												class="btn btn-danger btn-medium"
																												>Xóa hết</button>
																										</form>
																											<button
																												onclick="hideModal(this.parentNode.parentNode.parentNode.parentNode)"
																												class="btn btn-default btn-small">Hủy</button>
																										</div>
																									</div>
																								</div>
																							</div>

																						</td>
																					</tr>
																				</c:forEach>
																			</table>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</td>
													<td>&nbsp;${novel.getTotalVols() }</td>
												</tr>
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
		function hideModal(x) {
			var id = '#' + x.id;
			$(id).modal('hide');
		}
	</script>
</body>

</html>
