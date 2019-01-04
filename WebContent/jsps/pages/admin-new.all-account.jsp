<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Quản trị - tài khoản | 4TNOVEL</title>
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
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="${pageContext.request.contextPath}/http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
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
								<li><span>Danh sách tài khoản</span></li>
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
								<h4 class="header-title">Danh sách tài khoản</h4>
								<button style="float: right; margin: 1rem !important;"
									type="button" class="btn btn-success btn-lg"
									data-toggle="modal" data-target="#newAccountBtn">
									<i class="fa fa-plus">&nbsp;&nbsp;Thêm tài khoản</i>
								</button>
								<!-- Modal -->
								<form action="${pageContext.request.contextPath}/manage/admin/dashboard-accounts" method="post">
								<div id="newAccountBtn" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title">Thêm mới tài khoản</h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>
											<div class="modal-body">
												<div class="container">
													<div class="row mb-2">
														<div class="col-md-6">Tên tài khoản:</div>
														<div class="col-md-6">
															<input required type="text" name="username" class="w-100">
														</div>
													</div>
													<div class="row mb-2">
														<div class="col-md-6">Mật khẩu:</div>
														<div class="col-md-6">
															<input required type="password" name="password" class="w-100">
														</div>
													</div>
													<div class="row mb-2">
														<div class="col-md-6">Nhập lại mật khẩu:</div>
														<div class="col-md-6">
															<input required type="password" name="re-password" class="w-100">
														</div>
													</div>
													<div class="row mb-2">
														<div class="col-md-6">Gmail:</div>
														<div class="col-md-6">
															<input required type="email" name="email" class="w-100">
														</div>
													</div>
													<div class="row mb-2">
														<div class="col-md-6">Quyền ghim:</div>
														<div class="col-md-6">
															<select name="pin" class="w-100">
																<option value="true">Có</option>
																<option value="false" selected>Không</option>
															</select>
														</div>
													</div>
													<div class="row mb-2">
														<div class="col-md-6">Quyền đăng bài:</div>
														<div class="col-md-6">
															<select name="autoPassPublishment" class="w-100">
																<option value="true">Có</option>
																<option value="false" selected>Không</option>
															</select>
														</div>
													</div>
													<div class="row mb-2">
														<div class="col-md-6">Tình trạng:</div>
														<div class="col-md-6">
															<select name="ban" class="w-100">
																<option value="true">Bị khóa</option>
																<option value="false" selected>Hoạt động</option>
															</select>
														</div>
													</div>
													<div class="row mb-2">
														<div class="col-md-6">Quyền hạn:</div>
														<div class="col-md-6">
															<select name="role" class="w-100">
																<option value="1" selected>Người dùng</option>
																<option value="2" >Quản trị viên</option>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<input type="hidden" name="agreement" value="agree">
												<input type="hidden" name="action" value="create">
												<button type="submit" class="btn btn-sucess"
													>Tạo mới</button>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Hủy</button>
											</div>
										</div>
									</div>
								</div>
								</form>
								<div class="data-tables datatable-dark">
									<table id="dataTable3" class="table-striped"
										style="width: 100%; text-align: left;">
										<thead class="text-capitalize">
											<tr>
												<th>Tên tài khoản</th>
												<th>Thư điện tử</th>
												<th>Ngày tạo</th>
												<th>Quyền ghim</th>
												<th>Quyền đăng bài</th>
												<th>Tình trạng</th>
												<th>Quyền hạn</th>
												<th>Thao tác</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="ac" items="${accounts}">
												<tr>
													<td>${ac.userName}</td>
													<td>${ac.gmail}</td>
													<td>${ac.dateCreate}</td>
													<c:choose>
														<c:when test="${ac.pin}">
															<td>có</td>
														</c:when>
														<c:otherwise>
															<td>không</td>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${ac.isAutoPassPushlishment()}">
															<td>có</td>
														</c:when>
														<c:otherwise>
															<td>không</td>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${ac.ban}">
															<td>bị khóa</td>
														</c:when>
														<c:otherwise>
															<td>hoạt động</td>
														</c:otherwise>
													</c:choose>
													<td style="text-transform: lowercase;">${ac.role.toString()}</td>
													<td>
														<!-- Trigger the modal with a button -->
														<button type="button" class="btn btn-success btn-small"
															data-toggle="modal" data-target="#edit${ac.id}">
															<i class="fa fa-pencil-square-o"></i>
														</button> <!-- Modal -->
														<div id="edit${ac.id}" class="modal fade" role="dialog">
															<div class="modal-dialog">
																<form
																	action="${pageContext.request.contextPath}/manage/admin/dashboard-accounts"
																	method="post">
																	<!-- Modal content-->
																	<div class="modal-content">
																		<div class="modal-header">
																			<h4 class="modal-title">Sửa thông tin tài khoản</h4>
																			<button type="button" class="close"
																				data-dismiss="modal">&times;</button>
																		</div>
																		<div class="modal-body">
																			<div class="container">
																				<div class="row mb-2">
																					<div class="col-md-6">Username:</div>
																					<div class="col-md-6">${ac.userName}</div>
																				</div>
																				<div class="row mb-2">
																					<div class="col-md-6">Gmail:</div>
																					<div class="col-md-6">${ac.gmail}</div>
																				</div>
																				<div class="row mb-2">
																					<div class="col-md-6">Quyền ghim:</div>
																					<div class="col-md-6">
																						<c:choose>
																							<c:when test="${ac.pin}">
																								<select name="pin" class="w-100">
																									<option value="true" selected>Có</option>
																									<option value="false">Không</option>
																								</select>
																							</c:when>
																							<c:otherwise>
																								<select name="pin" class="w-100">
																									<option value="true">Có</option>
																									<option value="false" selected>Không</option>
																								</select>
																							</c:otherwise>
																						</c:choose>
																					</div>
																				</div>
																				<div class="row mb-2">
																					<div class="col-md-6">Quyền đăng bài:</div>
																					<div class="col-md-6">
																						<c:choose>
																							<c:when test="${ac.isAutoPassPushlishment()}">
																								<select name="autoPassPublishment" class="w-100">
																									<option value="true" selected>Có</option>
																									<option value="false">Không</option>
																								</select>
																							</c:when>
																							<c:otherwise>
																								<select name="autoPassPublishment" class="w-100">
																									<option value="true">Có</option>
																									<option value="false" selected>Không</option>
																								</select>
																							</c:otherwise>
																						</c:choose>
																					</div>
																				</div>
																				<div class="row mb-2">
																					<div class="col-md-6">Tình trạng:</div>
																					<div class="col-md-6">
																						<c:choose>
																							<c:when test="${ac.ban}">
																								<select name="ban" class="w-100">
																									<option value="true" selected>Bị khóa</option>
																									<option value="false">Hoạt động</option>
																								</select>
																							</c:when>
																							<c:otherwise>
																								<select name="ban" class="w-100">
																									<option value="true">Bị khóa</option>
																									<option value="false" selected>Hoạt
																										động</option>
																								</select>
																							</c:otherwise>
																						</c:choose>
																					</div>
																				</div>
																				<div class="row mb-2">
																					<div class="col-md-6">Quyền hạn:</div>
																					<div class="col-md-6">
																						<c:choose>
																							<c:when test="${ac.role.toString() eq 'USER'}">
																								<select name="role" class="w-100">
																									<option value="1" selected>User</option>
																									<option value="2">Administrator</option>
																								</select>
																							</c:when>
																							<c:otherwise>
																								<select name="role" class="w-100">
																									<option value="1">User</option>
																									<option value="2" selected>Administrator</option>
																								</select>
																							</c:otherwise>
																						</c:choose>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="modal-footer">
																			<input type="hidden" name="action" value="edit">
																			<input type="hidden" name="id" value="${ac.id}">
																			<button type="submit"
																				class="btn btn-danger btn-small">Cập nhật</button>
																			<button type="button"
																				class="btn btn-default btn-small"
																				data-dismiss="modal">Hủy</button>
																		</div>
																	</div>
																</form>
															</div>
														</div> <!-- Trigger the modal with a button -->
														<button type="button" class="btn btn-danger btn-small"
															data-toggle="modal" data-target="#delete${ac.id}">
															<i class="fa fa-trash"></i>
														</button> <!-- Modal -->
														<div id="delete${ac.id}" class="modal fade" role="dialog">
															<div class="modal-dialog">
																<form
																	action="${pageContext.request.contextPath}/manage/admin/dashboard-accounts"
																	method="post">
																	<!-- Modal content-->
																	<div class="modal-content">
																		<div class="modal-header">
																			<h4 class="modal-title">Cảnh báo!!!</h4>
																			<button type="button" class="close"
																				data-dismiss="modal">&times;</button>
																		</div>
																		<div class="modal-body">
																			<p>
																				Bạn muốn khóa tài khoản <span class="btn-danger">${ac.userName}</span>
																				và toàn bộ tài sản của tài khoản này?
																			</p>
																		</div>
																		<div class="modal-footer">

																			<input type="hidden" name="action" value="delete">
																			<input type="hidden" name="id" value="${ac.id}">
																			<button type="submit"
																				class="btn btn-danger btn-small">Xóa hết</button>

																			<button type="button"
																				class="btn btn-default btn-small"
																				data-dismiss="modal">Hủy</button>
																		</div>
																	</div>
																</form>
															</div>
														</div>
													</td>
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
</body>

</html>
