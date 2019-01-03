<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Sửa chương</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
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
	href="${pageContext.request.contextPath}/resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/local/css/style.css" />
</head>
<body>
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
		<div class="add">
			<div class="u-align-center">
				<p class="u-5x">Sửa chương</p>
				<p class="u-paragraph--sucessed">${sucessed}</p>
			</div>

			<form action="${pageContext.request.contextPath}/fix-chap" method="post">
				<table class="table u-2x u-centered u-width--95">
					<tr>
						<td class="u-width--15 u-align-right u-vertical-align--middle"><label>Tiêu
								đề: <span style="color: red">*</span>
						</label></td>
						<td><input name="title" class="u-width--full" type="text"
							style="padding: .5rem" required value="${fixingChap.title}"></td>
					</tr>
					<tr>
						<td class="u-align-right"><label>Nội dung: <span
								style="color: red">*</span></label></td>
						<td><textarea name="content" class="u-width--full"
								style="padding: .5rem" rows="15" required>${fixingChap.content}</textarea></td>
					</tr>
				</table>
				<div class="u-align-center u-color-white">
					<button type="submit" class="btn btn-primary u-2x">Lưu
						thay đổi</button>
					<input
						name="fixingChapID" type="hidden" value="${fixingChap.id}"></input>
						<input name="admin" value="${admin }" type="hidden">
						<input type="hidden" name="action" value="fix">
					<a href="manage/account/dashboard-chaps" type="button" class="btn btn-danger u-2x">Hủy</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>