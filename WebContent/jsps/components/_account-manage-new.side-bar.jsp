
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="sidebar-menu">
	<div class="sidebar-header">
		<div class="logo">
			<a href="#"><img
				src="${pageContext.request.contextPath}/resources/template/admin-dashboard/images/icon/logo.png"
				alt="logo"></a>
		</div>
	</div>
	<div class="main-menu">
		<div class="menu-inner">
			<nav>
				<ul class="metismenu" id="menu">
					<li><a href="${pageContext.request.contextPath}/index"><i
							class="fa fa-home"></i><span>Trang chủ</span></a></li>
					<li><a href="javascript:void(0)" aria-expanded="true"><i
							class="fa fa-book"></i><span>Tác phẩm</span></a>
						<ul class="collapse">
							<li><a
								href="${pageContext.request.contextPath}/manage/account/dashboard-chaps">Danh
									sách chương</a></li>
							<li><a
								href="${pageContext.request.contextPath}/manage/account/dashboard-vols">Danh
									sách tập</a></li>
							<li><a
								href="${pageContext.request.contextPath}/manage/account/dashboard-novels">Danh
									sách tác phẩm</a></li>
						</ul></li>
					<li><a href="javascript:void(0)" aria-expanded="true"><i
							class="fa fa-users"></i> <span>Nhóm dịch</span></a>
						<ul class="collapse">
							<li><a
								href="${pageContext.request.contextPath}/manage/account/dashboard-groups">Danh
									sách nhóm</a></li>
						</ul></li>
					<li><a href="#"><i class="fa fa-send-o"></i><span>Thông
								báo</span></a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>