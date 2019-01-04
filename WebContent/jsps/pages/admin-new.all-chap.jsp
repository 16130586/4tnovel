<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Quản trị - chương của tập | 4TNOVEL</title>
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
								<li><a href="${pageContext.request.contextPath}/manage">Giao
										diện chính</a></li>
								<li><span>Chương</span></li>
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
								<h4 class="header-title">Danh sách tác phẩm</h4>
								<div class="data-tables datatable-dark">
									<table class="table-striped" id="dataTable3"
										style="width: 100%; text-align: left;">
										<thead class="text-capitalize">
											<tr>
												<th>&nbsp;Tên</th>
												<th>&nbsp;Số tập</th>
												<th>Số chương</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="novel" items="${novels}">
												<tr>
													<td>
														<p style="cursor: pointer; width: 100%"
															data-toggle="modal" data-target="#seeVol${novel.id }">&nbsp;${novel.name }</p>
														<div id="seeVol${novel.id }" class="modal fade"
															role="dialog">
															<div class="modal-dialog"
																style="max-width: 100%; height: 90vh">
																<!-- Modal content-->
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
																		<table class="table-striped"
																			style="width: 90%; margin: auto">
																			<tr>
																				<th>&nbsp;Tên</th>
																			</tr>
																			<c:forEach var="vol" items="${novel.vols }">
																				<tr>
																					<td>
																						<p style="cursor: pointer; width: 100%;"
																							data-toggle="collapse"
																							data-target=".seeChap${novel.id}-${vol.id}">&nbsp;${vol.title }
																						</p> <c:forEach var="chap" items="${vol.chaps }">
																							<div
																								style="margin-left: 1rem; border-top: 1px solid #00000026"
																								class="collapse seeChap${novel.id }-${vol.id}"
																								onload="this.style.backgroundColor=this.previousSibling.style.backgroundColor">
																								<a target="_blank"
																									style="text-decoration: none; color: black; width: 80%; display: inline-block;"
																									title="${chap.title }"
																									href="${pageContext.request.contextPath}/read?id=${chap.id}">&nbsp;${chap.title }</a>
																								<a class="btn btn-primary"
																									style="display: inline-block;"
																									href="${pageContext.request.contextPath}/fix-chap?id-chap=${chap.id}&admin=1"><i
																									class="fa fa-edit"></i></a>
																								<button type="button"
																									style="display: inline-block;"
																									class="btn btn-secondary" data-toggle="modal"
																									data-target="#delete${chap.id }">
																									<i class="fa fa-trash"></i>
																								</button>
																								<!-- Modal -->
																								<div id="delete${chap.id }" class="modal fade"
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
																													Bạn muốn xóa chương <span
																														class="btn-danger">${chap.title}</span> ?
																												</p>
																											</div>
																											<div class="modal-footer">
																												<form
																													action="${pageContext.request.contextPath}/delete-chap"
																													method="post">
																													<input type="hidden" name="admin" value="1">
																													<input type="hidden" name="id-chap"
																														value="${chap.id }">
																													<button type="submit"
																														class="btn btn-danger btn-medium">Xóa</button>
																												</form>
																												<button
																													onclick="hideModal(this.parentNode.parentNode.parentNode.parentNode)"
																													class="btn btn-default btn-small">Hủy</button>
																											</div>
																										</div>
																									</div>
																								</div>
																							</div>
																						</c:forEach>
																					</td>
																				</tr>
																			</c:forEach>
																		</table>
																	</div>
																	<div class="modal-footer"></div>
																</div>
															</div>
														</div>
													</td>
													<td>&nbsp;${novel.getTotalVols() }</td>
													<td>${novel.getTotalChaps() }</td>

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
