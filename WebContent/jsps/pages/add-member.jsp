<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="account-manage__content u-row--1140 u-centered">
			<div>
				<p class="u-3x u-align-center">Thêm thành viên</p>
			</div>
			<div class="search">
				<%@ include file="/jsps/components/_search-bar.account.jsp"%>
			</div>
			<form action="add-member" method="post">
				<div class="row u-padding-bottom--1-5rem ">
					<div class="col-md-5 u-align-right">Chọn nhóm:</div>
					<div class="col-md-7">
						<select name="id-group">
							<!-- c:for de in option and value -->
							<c:forEach var="group" items="${ownerGroups}">
								<option value="${group.id}">${group.name }</option>
							</c:forEach>
							
						</select>
						<!--  adding link to adding group if ownergroup is null -->
					</div>
				</div>
				<input id="hidenAccountId" type="hidden" name="id-acc" value="${searchResultAccount.id}">
				<div class="row u-padding-bottom--1-5rem">
					<div class="col-md-5"></div>
					<div class="col-md-7">
						<button class="btn btn-primary u-color-white" type="submit">Thêm</button>
						<a href="#" class="btn btn-danger u-color-white">Hủy</a>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>