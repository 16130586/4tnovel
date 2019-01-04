<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Quản trị - thống kê hành vi người dùng | 4TNOVEL</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="${pageContext.request.contextPath}/resources/template/admin-dashboard/images/icon/favicon.ico">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
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
								<li><span>Thống kê hành vi người dùng</span></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- page title area end -->
			<div class="main-content-inner">
				<form
					action="${pageContext.request.contextPath}/manage/admin/statistics/behavior"
					method="POST">
					<div class="row mt-3 card">
						<div class="row col-md-12">
							<div class="col-md-6">
								<span>Từ ngày(MM/dd/YYYY):</span> <input class="form-control"
									name="startDate" type="date" value="${startDate}"
									id="example-date-input">
							</div>
							<div class="col-md-6">
								<span>Đến ngày(MM/dd/YYYY):</span> <input class="form-control"
									name="endDate" type="date" value="${endDate}"
									id="example-date-input">
							</div>
						</div>
					</div>
					<div class="row col-md-12 mt-2" style="text-align: right;">
						<button type="submit" class="btn btn-success"
							style="margin-left: auto;">Thống kê</button>
					</div>

				</form>
				<div class="row mt-3 card">
					<div class="row">
						<div class="col-md-12" id="overviewAccessingBehaviorChart"></div>
					</div>
					<div class="row mt-4">
						<div class="col-md-12" id="detailAccessingBehaviorChart"></div>
					</div>
				</div>
				<div class="row mt-3 card">
					<div class="col-md-8">
						<div id="overviewLikeBehaviorChart"></div>
					</div>
					<div class="col-md-4"></div>
				</div>
				<div class="row mt-3 card">
					<div class="col-md-8">
						<div id="overviewFollowBehaviorChart"></div>
					</div>
					<div class="col-md-4"></div>
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

	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<script>
		function extractOnPairJSON(raw){
			var finalDataForChart =[]
			for(var i = 0 ; i < raw.length; i++){
				var entry = [];
				entry.push(raw[i]['k'])
				entry.push(raw[i]['v'])
				finalDataForChart.push(entry)
			}
			return finalDataForChart;
		}
		function extractOnTrippleJSON(raw){
			var finalDataForChart =[]
			for(var i = 0 ; i < raw.length; i++){
				var entry = [];
				entry.push(raw[i]['k'])
				entry.push(raw[i]['v1'])
				entry.push(raw[i]['v2'])
				finalDataForChart.push(entry)
			}
			return finalDataForChart;
		}
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							google.charts.load('current', {
								packages : [ 'corechart', 'line' ]
							});
							google.charts
									.setOnLoadCallback(drawOverviewAccessingBehaviorChart);
							function drawOverviewAccessingBehaviorChart() {

								var data = new google.visualization.DataTable();
								data.addColumn('timeofday',
												'Thời gian trong ngày');
								data.addColumn('number',
										'Lượt truy cập trung bình');
								
								data.addRows(extractOnPairJSON(${dataOverviewViewBehavior}));

								var options = {
									title : 'Tổng quát lượng truy cập',
									hAxis : {
										title : 'Thời điểm'
									},
									vAxis : {
										title : 'Số lượng'
									 	
									}
								};

								var chart = new google.visualization.LineChart(
										document
												.getElementById('overviewAccessingBehaviorChart'));

								chart.draw(data, options);
							}

							google.charts
									.setOnLoadCallback(drawDetailAccessingBehaviorChart);
							function drawDetailAccessingBehaviorChart() {
								var dataArray = extractOnTrippleJSON(${dataDetailViewBehavior})
								dataArray.unshift([ 'Thời gian', 'Thành viên',
											'Khách' ])
								var data = google.visualization.arrayToDataTable(dataArray);

								var options = {
									title : 'Chi tiết lượng truy cập',
									curveType : 'function',
									legend : {
										position : 'bottom'
									}
								};

								var chart = new google.visualization.LineChart(
										document
												.getElementById('detailAccessingBehaviorChart'));

								chart.draw(data, options);
							}

							google.charts
									.setOnLoadCallback(drawOverviewLikeBehavior);

							function drawOverviewLikeBehavior() {

								var data = new google.visualization.DataTable();
								data
										.addColumn('timeofday',
												'Thời gian trong ngày');
								data.addColumn('number',
										'Lượt thích trung bình');

								data.addRows(extractOnPairJSON(${dataDetailOverLike}));

								var options = {
									title : 'Tổng quát lượt thích',
									hAxis : {
										title : 'Thời điểm'
									},
									vAxis : {
										title : 'Số lượng'
									}
								};
								var chart = new google.visualization.LineChart(
										document
												.getElementById('overviewLikeBehaviorChart'));

								chart.draw(data, options);
							}
							google.charts
									.setOnLoadCallback(drawOverviewFollowBehavior);

							function drawOverviewFollowBehavior() {

								var data = new google.visualization.DataTable();
								data
										.addColumn('timeofday',
												'Thời gian trong ngày');
								data.addColumn('number',
										'Lượt theo dõi trung bình');

								data.addRows(extractOnPairJSON(${dataDetailOverFollow}));

								var options = {
									title : 'Tổng quát lượt theo dõi',
									hAxis : {
										title : 'Thời điểm'
									},
									vAxis : {
										title : 'Số lượng'
									}
								};
								var chart = new google.visualization.LineChart(
										document
												.getElementById('overviewFollowBehaviorChart'));

								chart.draw(data, options);
							}

						})
	</script>
</body>

</html>
